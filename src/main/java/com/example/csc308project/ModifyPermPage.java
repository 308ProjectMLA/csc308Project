package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ModifyPermPage {

    private static Label message;

    private final static String defaultMessage = "Please enter the file and information you wish to modify below :";

    public VBox pageLayout() {

        message = new Label(defaultMessage);
        message.setUnderline(true);
        VBox pageVBox = new VBox();
        VBox buttonVBox = new VBox(15);

        Main.updateTitle("Modify Permissions");
        HBox header = new HBox(200);
        Text pageTitle = new Text("Modify Permissions");
        pageTitle.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        header.setPadding(new Insets(40, 0 , 60, 0 ));
        header.setAlignment(Pos.TOP_CENTER);

        //back button
        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button backButton = new Button("Back to Manage Permissions");
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(managePermissionPage.pageLayout(),"managePermissions");
        });

        header.getChildren().addAll(pageTitle, backButton);

        //File selector
        Text fileTitle = new Text("File to Manage");
        ComboBox<String> fileSelector = new ComboBox<>();
        fileSelector.setPromptText("Select file to manage");

        ArrayList<File> fileList = FileSelectController.getPermFiles();
        fileList.sort((file1, file2) -> {
            assert !file1.getName().isBlank() && !file2.getName().isBlank();
            return file1.getName().compareTo(file2.getName());
        });
        for (File file : fileList) {
            String fileName = file.getName();
            fileSelector.getItems().add(fileName.substring(0, fileName.length() - 4));
        }

        //GROUPS
        //group selector add write
        ComboBox<String> groupAddWriteSelector = new ComboBox<>();
        groupAddWriteSelector.setPromptText("Select group to add write");
        groupAddWriteSelector.setMinWidth(215);
        //group selector add read
        ComboBox<String> groupAddReadSelector = new ComboBox<>();
        groupAddReadSelector.setPromptText("Select group to add read");
        groupAddReadSelector.setMinWidth(215);
        //group selector remove write
        ComboBox<String> groupRemoveWriteSelector = new ComboBox<>();
        groupRemoveWriteSelector.setPromptText("Select group to remove write");
        groupRemoveWriteSelector.setMinWidth(215);
        //group selector remove read
        ComboBox<String> groupRemoveReadSelector = new ComboBox<>();
        groupRemoveReadSelector.setPromptText("Select group to remove read");
        groupRemoveReadSelector.setMinWidth(215);

        //add group names to selectors for groups
        ArrayList<String> groupList = Group.parseGroup();
        groupList.sort((group1, group2) -> {
            assert !group1.isBlank() && !group2.isBlank();
            return group1.compareTo(group2);
        });
        for (String group : groupList) {
            groupAddWriteSelector.getItems().add(group);
            groupAddReadSelector.getItems().add(group);
            groupRemoveWriteSelector.getItems().add(group);
            groupRemoveReadSelector.getItems().add(group);
        }

        //USERS
        //user selector add write
        ComboBox<String> userAddWriteSelector = new ComboBox<>();
        userAddWriteSelector.setPromptText("Select user to add write");
        userAddWriteSelector.setMinWidth(215);
        //user selector add read
        ComboBox<String> userAddReadSelector = new ComboBox<>();
        userAddReadSelector.setPromptText("Select user to add read");
        userAddReadSelector.setMinWidth(215);
        //user selector remove write
        ComboBox<String> userRemoveWriteSelector = new ComboBox<>();
        userRemoveWriteSelector.setPromptText("Select user to remove write");
        userRemoveWriteSelector.setMinWidth(215);
        //user selector remove read
        ComboBox<String> userRemoveReadSelector = new ComboBox<>();
        userRemoveReadSelector.setPromptText("Select user to remove read");
        userRemoveReadSelector.setMinWidth(215);

        //add group names to selectors for groups
        ArrayList<String> userList = User.getAllUsers();
        userList.sort((user1, user2) -> {
            assert !user1.isBlank() && !user2.isBlank();
            return user1.compareTo(user2);
        });
        for (String user : userList) {
            userAddWriteSelector.getItems().add(user);
            userAddReadSelector.getItems().add(user);
            userRemoveWriteSelector.getItems().add(user);
            userRemoveReadSelector.getItems().add(user);
        }

        //Titles
        Text addGroupReadTitle = new Text("Group to add read");
        Text addGroupWriteTitle = new Text("Group to add write");
        Text delGroupReadTitle = new Text("Group to remove read");
        Text delGroupWriteTitle = new Text("Group to remove write");
        Text addUserReadTitle = new Text("User to add read");
        Text addUserWriteTitle = new Text("User to add write");
        Text delUserReadTitle = new Text("User to remove read");
        Text delUserWriteTitle = new Text("User to remove write");

        VBox leftCol = new VBox(10);
        leftCol.getChildren().addAll(addGroupReadTitle, groupAddReadSelector, delGroupReadTitle, groupRemoveReadSelector,
        addUserReadTitle, userAddReadSelector, delUserReadTitle, userRemoveReadSelector);

        VBox rightCol = new VBox(10);
        rightCol.getChildren().addAll(addGroupWriteTitle, groupAddWriteSelector, delGroupWriteTitle, groupRemoveWriteSelector,
                addUserWriteTitle, userAddWriteSelector, delUserWriteTitle, userRemoveWriteSelector);

        HBox combineCols = new HBox(50);
        combineCols.getChildren().addAll(leftCol, rightCol);
        combineCols.setAlignment(Pos.CENTER);


        Button saveButton = new Button("Submit");
        saveButton.setOnAction(actionEvent -> {
            try {
                processPermChange(fileSelector.getValue(),
                        groupAddReadSelector.getValue(), groupAddWriteSelector.getValue(),
                        groupRemoveReadSelector.getValue(), groupRemoveWriteSelector.getValue(),
                        userAddReadSelector.getValue(), userAddWriteSelector.getValue(),
                        userRemoveReadSelector.getValue(), userRemoveWriteSelector.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(actionEvent -> {
            Main.updatePage(managePermissionPage.pageLayout(),"managePermissions");
        });

        HBox box1 = new HBox(Main.SIDE_PAD);
        box1.getChildren().addAll(saveButton, cancelButton);
        box1.setAlignment(Pos.CENTER);

        buttonVBox.getChildren().addAll(message, fileTitle, fileSelector, combineCols,  box1);
        buttonVBox.setAlignment(Pos.CENTER);

        //create page
        pageVBox.getChildren().addAll(header, buttonVBox);
        pageVBox.setStyle("-fx-background-color: #9da5b0;");


        return pageVBox;
    }

    private static void processPermChange(String fileName, String gRAddName, String gWAddName,
                                          String gRRemoveName, String gWRemoveName,
                                          String uRAddName, String uWAddName,
                                          String uRRemoveName, String uWRemoveName) throws IOException, ParseException {
        message.setText("Permission Modification Submitted\n");

        if(fileName == null){
            message.setText(message.getText() + "Error: Please select a file to modify\n");
            return;
        }
        ManifestParser manifestParser = new ManifestParser(fileName);
        if(gRAddName != null){
            boolean updated = manifestParser.addPermission("group", gRAddName, 'r' );
            if (!updated)
                message.setText(message.getText() + "Error: Group " + gRAddName + " already has read access to " + fileName + "\n");
            else
                message.setText(message.getText() + "Success: Group " + gRAddName + " granted read access to " + fileName + "\n");
        }
        if(gWAddName != null){
            boolean updated = manifestParser.addPermission("group", gWAddName, 'w' );
            if (!updated)
                message.setText(message.getText() + "Error: Group " + gWAddName + " already has write access to " + fileName + "\n");
            else
                message.setText(message.getText() + "Success: Group " + gWAddName + " granted write access to " + fileName + "\n");
        }
        if(gRRemoveName != null){
            boolean updated = manifestParser.removePermission("group", gRRemoveName, 'r' );
            if (!updated)
                message.setText(message.getText() + "Error: Group " + gRRemoveName + " already does not have read access to " + fileName + "\n");
            else
                message.setText(message.getText() + "Success: Group " + gRRemoveName + " read access removed from " + fileName + "\n");
        }
        if(gWRemoveName != null){
            boolean updated = manifestParser.removePermission("group", gWRemoveName, 'w' );
            if (!updated)
                message.setText(message.getText() + "Error: Group " + gWRemoveName +  " already does not have write access to " + fileName + "\n");
            else
                message.setText(message.getText() + "Success: Group " + gWRemoveName + " write access removed from " + fileName + "\n");
        }
        if(uRAddName != null){
            boolean updated = manifestParser.addPermission("user", uRAddName, 'r' );
            if (!updated)
                message.setText(message.getText() + "Error: User " + uRAddName + " already has read access to " + fileName + "\n");
            else
                message.setText(message.getText() + "Success: User " + uRAddName + " granted read access to " + fileName + "\n");
        }
        if(uWAddName != null){
            boolean updated = manifestParser.addPermission("user", uWAddName, 'w' );
            if (!updated)
                message.setText(message.getText() + "Error: User " + uWAddName + " already has write access to " + fileName + "\n");
            else
                message.setText(message.getText() + "Success: User " + uWAddName + " granted write access to " + fileName + "\n");
        }
        if(uRRemoveName != null){
            boolean updated = manifestParser.removePermission("user", uRRemoveName, 'r' );
            if (!updated)
                message.setText(message.getText() + "Error: User " + uRRemoveName + " already does not have read access to " + fileName + "\n");
            else
                message.setText(message.getText() + "Success: User " + uRRemoveName + " read access removed from " + fileName + "\n");
        }
        if(uWRemoveName != null){
            boolean updated = manifestParser.removePermission("user", uWRemoveName, 'w' );
            if (!updated)
                message.setText(message.getText() + "Error: User " + uWRemoveName + " already does not have write access to " + fileName + "\n");
            else
                message.setText(message.getText() + "Success: User " + uWRemoveName + " write access removed from " + fileName + "\n");
        }
    }

}
