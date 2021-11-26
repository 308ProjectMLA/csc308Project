package com.example.csc308project;

import java.util.ArrayList;

public class ViewFileController {

    public static boolean allowEdit(String file) {
        ManifestParser mp = new ManifestParser(file.replace(".txt", ""));

        boolean userTrue = false;
        try {
            userTrue = mp.checkPermission(ManifestParser.USER_TAG, Main.currentUser.getUsername(), 'w');
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean groupTrue = false;
        try {
            ArrayList<String> groupList = Main.currentUser.groups;
            for (String group : groupList) {
                if (mp.checkPermission(ManifestParser.GROUP_TAG, group, 'w')) {
                    groupTrue = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userTrue || groupTrue;
    }
}
