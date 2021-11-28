package com.example.csc308project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Group {

    private final String groupName;
    private HashSet<User> users;

    public Group(String name){
        this.groupName = name;
        this.users = new HashSet<>();
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
}
