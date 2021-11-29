package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

import java.util.List;

public class ModifyPermPage {

    private static final String DEFAULT_MESSAGE = "Please enter the file and information you wish to modify below :";
    private static Label message;

    public VBox pageLayout() {

        message = new Label(DEFAULT_MESSAGE);
        message.setTextFill(Color.WHITE);
        message.setUnderline(true);
        VBox pageVBox = new VBox();
        VBox buttonVBox = new VBox(15);

        Main.updateTitle("Modify Permissions");
        HBox header = new HBox(200);
        Text pageTitle = new Text("Modify Permissions");
        pageTitle.setFill(Color.WHITE);
        pageTitle.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        header.setPadding(new Insets(40, 0 , 60, 0 ));
        header.setAlignment(Pos.TOP_CENTER);

        //back button
        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button backButton = new Button("Back to Manage Permissions");
        backButton.setOnAction(actionEvent ->
            Main.updatePage(managePermissionPage.pageLayout(),"managePermissions"));

        backButton.setId(Main.BUTTON_ID);
        backButton.getStylesheets().add(Main.BUTTON_STYLE);

        header.getChildren().addAll(pageTitle, backButton);

        //File selector
        Text fileTitle = new Text("File to Manage");
        fileTitle.setFill(Color.WHITE);
        ComboBox<String> fileSelector = new ComboBox<>();
        fileSelector.setPromptText("Select file to manage");

        List<File> fileList = FileSelectController.getPermFiles();
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
        groupAddWriteSelector.setPrefWidth(255);
        //group selector add read
        ComboBox<String> groupAddReadSelector = new ComboBox<>();
        groupAddReadSelector.setPromptText("Select group to add read");
        groupAddReadSelector.setPrefWidth(255);
        //group selector remove write
        ComboBox<String> groupRemoveWriteSelector = new ComboBox<>();
        groupRemoveWriteSelector.setPromptText("Select group to remove write");
        groupRemoveWriteSelector.setPrefWidth(255);
        //group selector remove read
        ComboBox<String> groupRemoveReadSelector = new ComboBox<>();
        groupRemoveReadSelector.setPromptText("Select group to remove read");
        groupRemoveReadSelector.setPrefWidth(255);

        //add group names to selectors for groups
        List<String> groupList = GroupController.parseGroup();
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
        userAddWriteSelector.setPrefWidth(255);
        //user selector add read
        ComboBox<String> userAddReadSelector = new ComboBox<>();
        userAddReadSelector.setPromptText("Select user to add read");
        userAddReadSelector.setPrefWidth(255);
        //user selector remove write
        ComboBox<String> userRemoveWriteSelector = new ComboBox<>();
        userRemoveWriteSelector.setPromptText("Select user to remove write");
        userRemoveWriteSelector.setPrefWidth(255);
        //user selector remove read
        ComboBox<String> userRemoveReadSelector = new ComboBox<>();
        userRemoveReadSelector.setPromptText("Select user to remove read");
        userRemoveReadSelector.setPrefWidth(255);

        //add group names to selectors for groups
        List<String> userList = UserController.getAllUsers();
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
        addGroupReadTitle.setFill(Color.WHITE);
        Text addGroupWriteTitle = new Text("Group to add write");
        addGroupWriteTitle.setFill(Color.WHITE);
        Text delGroupReadTitle = new Text("Group to remove read");
        delGroupReadTitle.setFill(Color.WHITE);
        Text delGroupWriteTitle = new Text("Group to remove write");
        delGroupWriteTitle.setFill(Color.WHITE);
        Text addUserReadTitle = new Text("User to add read");
        addUserReadTitle.setFill(Color.WHITE);
        Text addUserWriteTitle = new Text("User to add write");
        addUserWriteTitle.setFill(Color.WHITE);
        Text delUserReadTitle = new Text("User to remove read");
        delUserReadTitle.setFill(Color.WHITE);
        Text delUserWriteTitle = new Text("User to remove write");
        delUserWriteTitle.setFill(Color.WHITE);

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
                if(fileSelector.getValue() == null){
                    message.setText("Error: Please select a file to modify\n");
                }
                else {
                    message.setText("Permission Modification Submitted\n");
                    processGroupPerm(fileSelector.getValue(),
                            groupAddReadSelector.getValue(), groupAddWriteSelector.getValue(),
                            groupRemoveReadSelector.getValue(), groupRemoveWriteSelector.getValue());
                    processUserPerm(fileSelector.getValue(),
                            userAddReadSelector.getValue(), userAddWriteSelector.getValue(),
                            userRemoveReadSelector.getValue(), userRemoveWriteSelector.getValue());
                }
            } catch (Exception e) {
                
            }

        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(actionEvent ->
            Main.updatePage(managePermissionPage.pageLayout(),"managePermissions"));

        saveButton.setId(Main.BUTTON_ID);
        saveButton.getStylesheets().add(Main.BUTTON_STYLE);
        cancelButton.setId(Main.BUTTON_ID);
        cancelButton.getStylesheets().add(Main.BUTTON_STYLE);

        HBox box1 = new HBox(Main.SIDE_PAD);
        box1.getChildren().addAll(saveButton, cancelButton);
        box1.setAlignment(Pos.CENTER);

        buttonVBox.getChildren().addAll(message, fileTitle, fileSelector, combineCols,  box1);
        buttonVBox.setAlignment(Pos.CENTER);

        //create page
        pageVBox.getChildren().addAll(header, buttonVBox);
        pageVBox.setStyle("-fx-background-image: url('file:img/network-background.png');");

        return pageVBox;
    }

    private static void processGroupPerm(String fileName, String gRAddName, String gWAddName,
                                         String gRRemoveName, String gWRemoveName) throws IOException, ParseException {
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
    }

    private static void processUserPerm(String fileName, String uRAddName, String uWAddName,
                                        String uRRemoveName, String uWRemoveName) throws IOException, ParseException {
        ManifestParser manifestParser = new ManifestParser(fileName);
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


