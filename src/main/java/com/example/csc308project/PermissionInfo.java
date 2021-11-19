package com.example.csc308project;

import java.util.ArrayList;

public class PermissionInfo {

    ArrayList<String> groupList;
    ArrayList<String> userList;
    String fileName;

    public PermissionInfo(String fileName){
        groupList = new ArrayList<>();
        userList = new ArrayList<>();
        this.fileName = fileName;

    }

    public void addGroup(String groupName){
        if(groupList.contains(groupName)) {
            this.groupList.add(groupName);
        }
    }

    public void addUser(String userName){
        if(userList.contains(userName)) {
            this.userList.add(userName);
        }
    }

    public void removeGroup(String groupName){
        if(groupList.contains(groupName)) {
            this.groupList.remove(groupName);
        }
    }

    public void removeUser(String userName){
        if(userList.contains(userName)) {
            this.userList.remove(userName);
        }
    }

    public ArrayList<String> getGroupList(){
        return groupList;
    }

    public ArrayList<String> getUserList(){
        return userList;
    }

}
