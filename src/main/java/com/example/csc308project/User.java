package com.example.csc308project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    
    private String username;
    private String password;

    public User(String user){
        username = user;
        password = null;
    }

    public User(String user, String pass){
        username = user;
        password = pass;
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
                tempArr.add(temp);
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
