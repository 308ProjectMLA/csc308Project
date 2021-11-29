package com.example.csc308project;

import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ManifestParser {
    private final String fname;

    public static final String GROUP_TAG = "group";
    public static final String PERM_TAG = "perm";
    public static final String USER_TAG = "user";

    public ManifestParser(String filename) {
        fname = Main.DATA_DIR + filename + ".mnf";
    }

    public void createDefaultManifest() {
        JSONObject jo = new JSONObject();

        // Array of users that have permissions
        JSONArray userArray = new JSONArray();
        // Pairs of users and their permissions
        Map<String, String> userPerm = new LinkedHashMap<>(2);

        // Give the user creating the file read and write perms by default
        userPerm.put(USER_TAG, Main.currentUser.getUsername());
        userPerm.put(PERM_TAG, "rw");

        userArray.add(userPerm);

        // Add it to the user array
        jo.put("users", userArray);

        // Array of groups that have permissions
        JSONArray groupArray = new JSONArray();
        // Pairs of groups and their permissions
        Map<String, String> groupPerm = new LinkedHashMap<>(2);

        groupPerm.put(GROUP_TAG, "supervisors");
        groupPerm.put(PERM_TAG, "rw");

        groupArray.add(groupPerm);

        jo.put("groups", groupArray);

        writeJSON(jo);
    }

    // Return a hashmap (key of either USER_TAG or GROUP_TAG) containing a list of user or group permissions in a pair
    public Map<String, ArrayList<Pair<String, String>>> readManifest() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fname));

        JSONObject jo = (JSONObject) obj;

        JSONArray users = (JSONArray) jo.get("users");
        JSONArray groups = (JSONArray) jo.get("groups");

        HashMap<String, ArrayList<Pair<String, String>>> parsed = new HashMap<>(2);
        ArrayList<Pair<String, String>> userList = new ArrayList<>();

        // Put all the users in the userList (in Pair<user, permission>)
        for (Object curr : users) {
            JSONObject curr2 = (JSONObject) curr;

            String username = (String) curr2.get(USER_TAG);
            String permissions = (String) curr2.get(PERM_TAG);

            Pair<String, String> temp = new Pair<>(username, permissions);

            userList.add(temp);
        }
        parsed.put(USER_TAG, userList);

        // Put all the groups in the userList (in Pair<group, permission>)
        ArrayList<Pair<String, String>> groupList = new ArrayList<>();
        for (Object curr : groups) {
            JSONObject curr2 = (JSONObject) curr;

            String groupname = (String) curr2.get(GROUP_TAG);
            String permissions = (String) curr2.get(PERM_TAG);

            Pair<String, String> temp = new Pair<>(groupname, permissions);

            groupList.add(temp);
        }
        parsed.put(GROUP_TAG, groupList);

        return parsed;
    }

    // Type: "user" or "group", name: user or group name, char: 'r' or 'w'
    public boolean addPermission(String type, String name, char permission) throws IOException, ParseException {
        // Read in the JSON file
        Object obj = new JSONParser().parse(new FileReader(fname));
        JSONObject jo = (JSONObject) obj;
        Map<String, String> newPerm = new LinkedHashMap<>(2);

        // Get either the users or groups array
        JSONArray arr = (JSONArray) jo.get(type + "s");
        // The object to remove (for updating) if a listing already exists
        Object toRemove = null;

        for (Object curr : arr) {
            JSONObject internal = (JSONObject) curr;
            String itemName = (String) internal.get(type);
            // See if the group/user already has permissions
            if (itemName!= null && itemName.equals(name)) {
                String permList = (String) internal.get(PERM_TAG);
                // If not in the permission list, add the permission
                if (permList.indexOf(permission) == -1) {
                    permList += permission;
                    // Put the updated line in the mapping
                    newPerm.put(type, name);
                    newPerm.put(PERM_TAG, permList);
                    // Set the existing entry for removal
                    toRemove = internal;
                    break;
                }
                else {
                    // If no update, return false
                    return false;
                }
            }
        }
        // Remove the old entry if it was updated
        if (toRemove != null) {
            arr.remove(toRemove);
        }
        // Create a new entry if none were found
        else {
            newPerm.put(type, name);
            newPerm.put(PERM_TAG, Character.toString(permission));
        }


        arr.add(newPerm);

        writeJSON(jo);

        return true;
    }

    public boolean removePermission(String type, String name, char permissions) throws IOException, ParseException {
        // Read in the JSON file
        Object obj = new JSONParser().parse(new FileReader(fname));
        JSONObject jo = (JSONObject) obj;

        // Get either the users or groups array
        JSONArray arr = (JSONArray) jo.get(type + "s");
        // The object to remove (for updating) if a listing already exists
        JSONObject toRemove = null;

        int i = 0;
        for (; i < arr.size(); i++) {
            JSONObject internal = (JSONObject) arr.get(i);
            // See if the group/user already has permissions
            if (internal.get(type).equals(name)) {
                toRemove = internal;
                break;
            }
        }
        // If it doesn't have the perm, can't remove it
        if (i == arr.size()) {
            return false;
        }
        assert toRemove != null;

        String permList = (String) toRemove.get(PERM_TAG);

        String updatedList = permList.replace(Character.toString(permissions), "");

        // If nothing was removed
        if (updatedList.equals(permList)) {
            return false;
        }

        arr.remove(toRemove);
        // If there are no more permissions for the group/user
        if (updatedList.trim().length() == 0) {
            writeJSON(jo);
            return true;
        }

        // Update the existing entry
        Map<String, String> newPerm = new LinkedHashMap<>(2);
        newPerm.put(type, name);
        newPerm.put(PERM_TAG, updatedList);

        arr.add(newPerm);
        writeJSON(jo);

        return true;
    }

    public boolean checkPermission(String type, String name, char permission) throws IOException, ParseException {
        // Read in the JSON file
        Object obj = new JSONParser().parse(new FileReader(fname));
        JSONObject jo = (JSONObject) obj;

        // Get either the users or groups array
        JSONArray arr = (JSONArray) jo.get(type + "s");

        for (Object curr : arr) {
            JSONObject internal = (JSONObject) curr;
            String itemName = (String) internal.get(type);

            if (itemName != null && itemName.equals(name)) {
                String permList = (String) internal.get(PERM_TAG);

                // If -1, then not in the list
                return permList.indexOf(permission) != -1;
            }
        }

        return false;
    }

    private void writeJSON(JSONObject jason) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fname);
        } catch (Exception ignored) {}

        assert pw != null;
        pw.write(jason.toJSONString());

        pw.flush();
        pw.close();
    }
}
