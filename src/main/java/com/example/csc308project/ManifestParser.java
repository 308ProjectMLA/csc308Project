package com.example.csc308project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class ManifestParser {
    private final String fname;

    public static final String GROUP_TAG = "group";
    public static final String PERM_TAG = "perm";
    public static final String USER_TAG = "user";

    public ManifestParser(String filename) {
        fname = "data/" + filename + ".mnf";
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

        // TODO Want no group perms on file creation
        groupPerm.put(GROUP_TAG, "testGroup");
        groupPerm.put(PERM_TAG, "r");

        groupArray.add(groupPerm);

        jo.put("groups", groupArray);

        // Attempt to open the file
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fname);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert pw != null;
        // Write out the JSON
        pw.write(jo.toJSONString());

        pw.flush();
        pw.close();
    }

    public void readManifest() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fname));

        JSONObject jo = (JSONObject) obj;

        JSONArray users = (JSONArray) jo.get("users");
        JSONArray groups = (JSONArray) jo.get("groups");

        for (Object curr : groups) {
            JSONObject curr2 = (JSONObject) curr;
            System.out.println(curr2.get(GROUP_TAG));
            System.out.println(curr2.get(PERM_TAG));
        }
    }

    // Type: "user" or "group", name: user or group name, char: 'r' or 'w'
    public boolean addPermission(String type, String name, char permission) throws IOException, ParseException {
        // Only two types: user and group
        assert type.equals(USER_TAG) || type.equals(GROUP_TAG);

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
            // See if the group/user already has permissions
            if (internal.get(type).equals(name)) {
                String permList = (String) internal.get(PERM_TAG);
                // If not in the permission list, add the permission
                if (permList.indexOf(permission) == -1) {
                    permList += permission;
                    // Put the updated line in the mapping
                    newPerm.put(name, permList);
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

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fname);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert pw != null;
        pw.write(jo.toJSONString());

        pw.flush();
        pw.close();

        return true;
    }
}
