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


    public ManifestParser(String filename) {
        fname = "data/" + filename + ".mnf";
    }

    public void createDefaultManifest() {
        JSONObject jo = new JSONObject();

        JSONArray userArray = new JSONArray();

        Map<String, String> userPerm = new LinkedHashMap<>(2);

        userPerm.put("username", Main.currentUser.getUsername());
        userPerm.put(PERM_TAG, "rw");

        userArray.add(userPerm);

        jo.put("users", userArray);

        JSONArray groupArray = new JSONArray();
        Map<String, String> groupPerm = new LinkedHashMap<>(2);

        // TODO Want no group perms on file creation
        groupPerm.put(GROUP_TAG, "testGroup");
        groupPerm.put(PERM_TAG, "r");

        groupArray.add(groupPerm);

        jo.put("groups", groupArray);

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
    }

    public void readManifest() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fname));

        JSONObject jo = (JSONObject) obj;

        JSONArray users = (JSONArray) jo.get("user");
        JSONArray groups = (JSONArray) jo.get("groups");

        for (Object curr : groups) {
            JSONObject curr2 = (JSONObject) curr;
            System.out.println(curr2.get(GROUP_TAG));
            System.out.println(curr2.get(PERM_TAG));
        }
    }

    public boolean addPermission(String type, String value, char permission) throws IOException, ParseException {

        assert type.equals("user") || type.equals(GROUP_TAG);

        Object obj = new JSONParser().parse(new FileReader(fname));
        JSONObject jo = (JSONObject) obj;
        Map<String, String> newPerm = new LinkedHashMap<>(2);

        JSONArray arr = (JSONArray) jo.get(type + "s");
        Object toRemove = null;

        for (Object curr : arr) {
            JSONObject internal = (JSONObject) curr;
            // See if the group/user already has permissions
            if (internal.get(type).equals(value)) {
                String permList = (String) internal.get(PERM_TAG);
                // If not in the permission list
                if (permList.indexOf(permission) == -1) {
                    permList += permission;
                    newPerm.put(value, permList);
                    newPerm.put(type, value);
                    newPerm.put(PERM_TAG, permList);
                    toRemove = internal;
                    break;
                }
                else {
                    return false;
                }
            }
        }
        if (toRemove != null) {
            arr.remove(toRemove);
        }
        else {
            newPerm.put(type, value);
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
