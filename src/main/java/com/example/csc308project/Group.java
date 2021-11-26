package com.example.csc308project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Group {

    private String groupName;
    private String groupId;
    private HashSet<User> users;

    public Group(String name, String id){
        this.groupName = name;
        this.groupId = id;
        this.users = new HashSet<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setUsers(HashSet<User> userSet) {
        this.users = userSet;
    }

    public void addUser(User u){
        this.users.add(u);
    }

    //enter a group id, and returns an ArrayList with all the members in the group that corresponds to that groupid
    public static ArrayList<String> parseGroupMembers(String id){
        BufferedReader br;
        ArrayList<String> users = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader("data/groupinfo.mla"));

            String temp;
            String[] tempArr;
            while((temp = br.readLine()) != null){
                tempArr = temp.split("\s");
                if(tempArr[0].compareTo(id) == 0){
                    if(tempArr.length > 1) users.addAll(Arrays.asList(tempArr).subList(1, tempArr.length));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    //returns a list of all groups in the system
    public static ArrayList<String> parseGroup(){
        BufferedReader br = null;
        ArrayList<String> groups = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader("data/groupinfo.mla"));

            String temp;
            while((temp = br.readLine()) != null){
                groups.add(temp.split(" ")[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return groups;
    }

    public static boolean isSupervisor(String username) {
        ArrayList<String> groupList = parseGroupMembers("supervisors");

        for (String user : groupList) {
            if (user.equals(username)) {
                return true;
            }
        }

        return false;
    }
}
