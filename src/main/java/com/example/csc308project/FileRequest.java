package com.example.csc308project;

import javafx.beans.property.SimpleStringProperty;

public class FileRequest {

    private final SimpleStringProperty name;
    private final SimpleStringProperty email;
    private final SimpleStringProperty fileName;
    private final boolean isApproved;

    public FileRequest(String name, String email, String fileName) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.fileName = new SimpleStringProperty(fileName);
        isApproved = false;
    }

    public String getName() { return name.get(); }
    public void setName(String newNal) { name.set(newNal); }

    public String getEmail() {
        return email.get();
    }
    public void setEmail(String newVal) {
        email.set(newVal);
    }

    public String getFileName() { return fileName.get(); }
    public void setFileName(String newNal) { fileName.set(newNal); }
}
