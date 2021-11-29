package com.example.csc308project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupController {

    private GroupController() {
        throw new IllegalStateException();
    }

    //enter a group id, and returns an ArrayList with all the members in the group that corresponds to that groupid
    private static List<String> parseGroupMembers(){
        BufferedReader br;
        ArrayList<String> users = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(Main.DATA_DIR + "groupinfo.mla"));

            String temp;
            String[] tempArr;
            while((temp = br.readLine()) != null){
                tempArr = temp.split("\\s");
                if(tempArr[0].compareTo("supervisors") == 0 && tempArr.length > 1){
                    users.addAll(Arrays.asList(tempArr).subList(1, tempArr.length));
                }
            }

            br.close();

        } catch (Exception ignored) {}

        return users;
    }

    //returns a list of all groups in the system
    public static List<String> parseGroup(){
        BufferedReader br = null;
        ArrayList<String> groups = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(Main.DATA_DIR + "groupinfo.mla"));

            String temp;
            while((temp = br.readLine()) != null){
                groups.add(temp.split(" ")[0]);
            }

            br.close();
        } catch (Exception ignored) {}

        return groups;
    }

    public static boolean isSupervisor(String username) {
        List<String> groupList = parseGroupMembers();

        for (String user : groupList) {
            if (user.equals(username)) {
                return true;
            }
        }

        return false;
    }
}
