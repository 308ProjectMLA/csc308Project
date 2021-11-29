package com.example.csc308project;

import java.util.*;

public class Group {

    private final String groupName;
    private Set<User> users;

    public Group(String name){
        this.groupName = name;
        this.users = new HashSet<>();
    }

    public Set<User> getUsers() {
        return users;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setUsers(Set<User> userSet) {
        this.users = userSet;
    }
}
