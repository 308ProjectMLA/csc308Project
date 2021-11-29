package com.example.csc308project;

import java.util.ArrayList;
import java.util.List;

public class ViewFileController {

    private ViewFileController() {
        throw new IllegalStateException();
    }

    public static boolean allowEdit(String file) {
        ManifestParser mp = new ManifestParser(file.replace(".txt", ""));

        boolean userTrue = false;
        try {
            userTrue = mp.checkPermission(ManifestParser.USER_TAG, Main.currentUser.getUsername(), 'w');
        } catch (Exception ignored) {}

        boolean groupTrue = false;
        try {
            List<String> groupList = Main.currentUser.groups;
            for (String group : groupList) {
                if (mp.checkPermission(ManifestParser.GROUP_TAG, group, 'w')) {
                    groupTrue = true;
                    break;
                }
            }
        } catch (Exception ignored) {}

        return userTrue || groupTrue;
    }
}
