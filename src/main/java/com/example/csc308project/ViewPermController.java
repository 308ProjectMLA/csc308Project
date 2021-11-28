package com.example.csc308project;

import javafx.util.Pair;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewPermController {

    private final ArrayList<String> readUsers;
    private final ArrayList<String> writeUsers;
    private final ArrayList<String> readGroups;
    private final ArrayList<String> writeGroups;

    public ViewPermController(String filename) throws IOException, ParseException {
        ManifestParser mp = new ManifestParser(filename);
        Map<String, ArrayList<Pair<String, String>>> raw = mp.readManifest();

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
                readGroups.add(p.getKey());
            }
            if (p.getValue().contains("w")) {
                writeGroups.add(p.getKey());
            }
        }
    }

    public List<String> getReadUsers(){
        return readUsers;
    }
    public List<String> getReadGroups(){
        return readGroups;
    }
    public List<String> getWriteUsers(){
        return writeUsers;
    }
    public List<String> getWriteGroups(){
        return writeGroups;
    }

}
