package com.example.csc308project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    
    private final String username;
    public final List<String> groups;
    private static final String USER_FILE = Main.DATA_DIR + "userinfo.mla";
    private static final Logger LOGGER = Logger.getLogger( User.class.getName());

    public User(String user){
        username = user;
        groups = getGroups();
    }

    // Returns a list of groups that a user is in
    private ArrayList<String> getGroups() {
        ArrayList<String> ret = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))){
            String userLine = br.readLine();
            while (userLine != null) {
                if (userLine.split("\\s")[0].equals(username)) {
                    break;
                }

                userLine = br.readLine();
            }

            if (userLine != null) {
                String[] splitUser = userLine.split("\\s");
                if (splitUser.length >= 1) {
                    ret.addAll(Arrays.asList(splitUser).subList(1, splitUser.length));
                }
            }
        } catch (Exception ignored) {
            LOGGER.log(Level.WARNING, "Exception thrown");
        }

        return ret;
    }

    public String getUsername() {
        return username;
    }
}
