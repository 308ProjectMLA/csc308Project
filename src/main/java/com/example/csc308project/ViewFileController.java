package com.example.csc308project;

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
            // TODO Fix once groups implemented
            // for (group in groups) {
            groupTrue = mp.checkPermission(ManifestParser.USER_TAG, Main.currentUser.getUsername(), 'w');
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userTrue || groupTrue;
    }
}
