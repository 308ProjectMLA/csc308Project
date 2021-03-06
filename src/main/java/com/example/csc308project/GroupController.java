package com.example.csc308project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupController {

    private static final Logger LOGGER = Logger.getLogger( GroupController.class.getName());

    private GroupController() {
        throw new IllegalStateException();
    }

    // Returns a list of all users in the supervisor group
    private static List<String> parseSupervisorMembers(){
        ArrayList<String> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Main.DATA_DIR + "groupinfo.mla"))){
            String temp;
            String[] tempArr;
            while((temp = br.readLine()) != null){
                tempArr = temp.split("\\s");
                if(tempArr[0].compareTo("supervisors") == 0 && tempArr.length > 1){
                    users.addAll(Arrays.asList(tempArr).subList(1, tempArr.length));
                }
            }
        } catch (Exception ignored) {
            LOGGER.log(Level.WARNING, "Exception thrown");
        }

        return users;
    }

    //returns a list of all groups in the system
    public static List<String> parseGroup(){
        ArrayList<String> groups = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Main.DATA_DIR + "groupinfo.mla"));){
            String temp;
            while((temp = br.readLine()) != null){
                groups.add(temp.split(" ")[0]);
            }
        } catch (Exception ignored) {
            LOGGER.log(Level.WARNING, "Exception thrown");
        }

        return groups;
    }

    public static boolean isSupervisor(String username) {
        List<String> groupList = parseSupervisorMembers();

        for (String user : groupList) {
            if (user.equals(username)) {
                return true;
            }
        }

        return false;
    }
}
