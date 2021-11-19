package com.example.csc308project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class ManifestParser {

    public void createDefaultManifest(String filename) {
        JSONObject jo = new JSONObject();

        JSONArray ja = new JSONArray();

        Map<String, String> userPerm = new LinkedHashMap<>(2);

        userPerm.put("username", Main.username);
        userPerm.put("perm", "rw");

        ja.add(userPerm);

        jo.put("users", ja);

        JSONArray ja2 = new JSONArray();
        Map<String, String> groupPerm = new LinkedHashMap<>(2);

        groupPerm.put("group", "testGroup");
        groupPerm.put("perm", "r");

        ja2.add(groupPerm);

        jo.put("groups", ja2);

        PrintWriter pw = null;
        try {
            pw = new PrintWriter("data/" + filename + ".mnf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert pw != null;
        pw.write(jo.toJSONString());

        pw.flush();
        pw.close();
    }
}
