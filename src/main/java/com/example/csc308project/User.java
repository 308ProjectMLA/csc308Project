package com.example.csc308project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class User {
    
    private String username;
    private String password;
    public final ArrayList<String> groups;

    private static final String USER_FILE = "data/userinfo.mla";

    public User(String user){
        username = user;
        password = null;
        groups = getGroups();
    }

    public User(String user, String pass){
        username = user;
        password = pass;
        groups = getGroups();
    }

    private ArrayList<String> getGroups() {
        BufferedReader br;
        ArrayList<String> ret = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(USER_FILE));


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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //returns list of usernames and passwords with all even elements being usernames and all odd elements being passwords associated with the prior element/username.
    public static ArrayList<String> parseUserInfo() {
        BufferedReader br = null;
        ArrayList<String> tempArr = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader("data/userinfo.mla"));

            String temp;
            while((temp = br.readLine()) != null){
                tempArr.add(temp.split("\\s")[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempArr;
    }

    public static ArrayList<String> getAllUsers(){
        ArrayList<String> fullInfo = parseUserInfo();
        ArrayList<String> userNameList = new ArrayList<>();

        for(int i = 0; i < fullInfo.size(); i = i+2){
            userNameList.add(fullInfo.get(i));
        }

        return userNameList;
    }
}
