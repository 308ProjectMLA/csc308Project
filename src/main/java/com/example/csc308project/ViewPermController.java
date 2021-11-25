package com.example.csc308project;

import javafx.util.Pair;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewPermController {

    private final String fname;
    private final ArrayList<String> readUsers;
    private final ArrayList<String> writeUsers;
    private final ArrayList<String> readGroups;
    private final ArrayList<String> writeGroups;
    public static final String READ_KEY = "read";
    public static final String WRITE_KEY = "write";

    // TODO Function that takes output from ManifestParser.readManifest (maybe in constructor)
    // Another set of functions that returns a list of users and groups based on either read or write perms

    public ViewPermController(String filename) throws IOException, ParseException {
        fname = filename + ".mnf";

        ManifestParser mp = new ManifestParser(filename);
        HashMap<String, ArrayList<Pair<String, String>>> raw = mp.readManifest();

        readUsers = new ArrayList<>();
        writeUsers = new ArrayList<>();
        for (Pair<String, String> p : raw.get(ManifestParser.USER_TAG)) {
            if (p.getValue().contains("r")) {
                readUsers.add(p.getKey());
            }
            if (p.getValue().contains("w")) {
                writeUsers.add(p.getKey());
            }
        }

        readGroups = new ArrayList<>();
        writeGroups = new ArrayList<>();
        for (Pair<String, String> p : raw.get(ManifestParser.GROUP_TAG)) {
            if (p.getValue().contains("r")) {
                readUsers.add(p.getKey());
            }
            if (p.getValue().contains("w")) {
                writeUsers.add(p.getKey());
            }
        }
    }

    public ArrayList<String> getReadUsers(){
        return readUsers;
    }
    public ArrayList<String> getReadGroups(){
        return readGroups;
    }
    public ArrayList<String> getWriteUsers(){
        return writeUsers;
    }
    public ArrayList<String> getWriteGroups(){
        return writeGroups;
    }

}
