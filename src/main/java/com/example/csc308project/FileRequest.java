package com.example.csc308project;

import javafx.beans.property.SimpleStringProperty;

public class FileRequest {

    private final SimpleStringProperty name;
    private final SimpleStringProperty fileName;

    public FileRequest(String name, String fileName) {
        this.name = new SimpleStringProperty(name);
        this.fileName = new SimpleStringProperty(fileName);
    }

    public String getName() { return name.get(); }
    public void setName(String newNal) { name.set(newNal); }

    public String getFileName() { return fileName.get(); }
    public void setFileName(String newNal) { fileName.set(newNal); }

}
