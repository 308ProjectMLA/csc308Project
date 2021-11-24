package com.example.csc308project;

import java.util.HashSet;

public class PermissionInfo {

    HashSet<Group> groupList;
    HashSet<User> userList;
    String fileName;

    public PermissionInfo(String fileName){
        groupList = new HashSet<>();
        userList = new HashSet<>();
        this.fileName = fileName;
    }

    //groupId is the name of the group according to the groupinfo.mla file. Must be without spaces.
    public void addGroup(String groupName, String groupId){
        Group.parseGroup().contains(groupName);
        Group g = new Group(groupName, groupId.replace(" ", ""));
        this.groupList.add(g);
    }

    public void addUser(String userName){
        User u = new User(userName);
        this.userList.add(u);
    }

    //removes group according to groupName
    public void removeGroup(String groupName){
        groupList.removeIf(g -> g.getGroupName().compareTo(groupName) == 0);
    }

    //removes group according to userName
    public void removeUser(String userName){
        userList.removeIf(u -> u.getUsername().compareTo(userName) == 0);
    }

    public HashSet<Group> getGroupList(){
        return groupList;
    }

    public HashSet<User> getUserList(){
        return userList;
    }

}
