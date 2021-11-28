package com.example.csc308project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private UserController() {
        throw new IllegalStateException();
    }

    //returns list of usernames and passwords with all even elements being usernames and all odd elements being passwords associated with the prior element/username.
    public static List<String> parseUserInfo() {
        BufferedReader br = null;
        ArrayList<String> tempArr = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(Main.DATA_DIR + "userinfo.mla"));

            String temp;
            while((temp = br.readLine()) != null){
                tempArr.add(temp.split("\\s")[0]);
            }

            br.close();

        } catch (Exception ignored) {
            
        }

        return tempArr;
    }

    public static List<String> getAllUsers(){
        List<String> fullInfo = parseUserInfo();
        ArrayList<String> userNameList = new ArrayList<>();

        for(int i = 0; i < fullInfo.size(); i = i+2){
            userNameList.add(fullInfo.get(i));
        }

        return userNameList;
    }
}
