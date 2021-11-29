package com.example.csc308project;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewFileController {

    private static final Logger LOGGER = Logger.getLogger( ViewFileController.class.getName());
    private ViewFileController() {
        throw new IllegalStateException();
    }

    public static boolean allowEdit(String file) {
        ManifestParser mp = new ManifestParser(file.replace(".txt", ""));

        boolean userTrue = false;
        try {
            userTrue = mp.checkPermission(ManifestParser.USER_TAG, Main.getCurrentUser().getUsername(), 'w');
        } catch (Exception ignored) {
            LOGGER.log(Level.WARNING, "Exception thrown");
        }

        boolean groupTrue = false;
        try {
            List<String> groupList = Main.getCurrentUser().groups;
            for (String group : groupList) {
                if (mp.checkPermission(ManifestParser.GROUP_TAG, group, 'w')) {
                    groupTrue = true;
                    break;
                }
            }
        } catch (Exception ignored) {
            LOGGER.log(Level.WARNING, "Exception thrown");
        }

        return userTrue || groupTrue;
    }
}
