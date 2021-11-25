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
        header.setPadding(new Insets(40, 0 , 100, 0 ));
        header.setAlignment(Pos.TOP_CENTER);

        //back button
        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button backButton = new Button("Back to Manage Permissions");
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(managePermissionPage.pageLayout());
        });

        header.getChildren().addAll(pageTitle, backButton);

        //create page content
        Text fileTitle = new Text("File to Manage");
        ComboBox<String> fileSelector = new ComboBox<>();
        fileSelector.setPromptText("Select file to manage");

        ArrayList<File> fileList = FileSelectController.getPermFiles();
        // Sort the files by name
        fileList.sort((file1, file2) -> {
            assert !file1.getName().isBlank() && !file2.getName().isBlank();

            return file1.getName().compareTo(file2.getName());
        });

        for (File file : fileList) {
            String fileName = file.getName();
            fileSelector.getItems().add(fileName.substring(0, fileName.length() - 4));
        }

        Text addGroupTitle = new Text("Group to add");
        TextField groupAdd  = new TextField();
        groupAdd.setPromptText("Enter group to add");
        groupAdd.setMaxWidth(Main.FIELD_WIDTH);

        Text delGroupTitle = new Text("Group to remove");
        TextField groupDel  = new TextField();
        groupDel.setPromptText("Enter group to remove");
        groupDel.setMaxWidth(Main.FIELD_WIDTH);

        Text addUserTitle = new Text("User to add");
        TextField userAdd  = new TextField();
        userAdd.setPromptText("Enter user to add");
        userAdd.setMaxWidth(Main.FIELD_WIDTH);

        Text removeUserTitle = new Text("User to remove");
        TextField userDel  = new TextField();
        userDel.setPromptText("Enter user to remove");
        userDel.setMaxWidth(Main.FIELD_WIDTH);

        Button saveButton = new Button("Submit");
        saveButton.setOnAction(actionEvent -> {
            String fileName = fileSelector.getValue();
            String gdName = groupDel.getCharacters().toString();
            String gaName = groupAdd.getCharacters().toString();
            String udName = userDel.getCharacters().toString();
            String uaName = userAdd.getCharacters().toString();
            try {
                processPermChange(fileName, gdName, gaName, udName, uaName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(actionEvent -> {
            Main.updatePage(managePermissionPage.pageLayout());
        });

        HBox box1 = new HBox(Main.SIDE_PAD);
        box1.getChildren().addAll(saveButton, cancelButton);
        box1.setAlignment(Pos.CENTER);

        buttonVBox.getChildren().addAll(message, fileTitle, fileSelector, addGroupTitle, groupAdd,
                delGroupTitle, groupDel, addUserTitle, userAdd, removeUserTitle, userDel, box1);
        buttonVBox.setAlignment(Pos.CENTER);

        //create page
        pageVBox.getChildren().addAll(header, buttonVBox);

        return pageVBox;
    }

    // returns if the user is valid and part of the system
    private static boolean validUser(String user){
        if(user == null || user.trim().strip() == ""){
            return true;
        }
        ArrayList<String> userList = User.getAllUsers();
        if(!userList.contains(user)){
            message.setText(message.getText() + "Error: " + user + " is not a valid user");
            return false;
        }
        return true;
    }

    // returns if the group is valid and part of the system
    private static boolean validGroup(String group){
        if(group == null || group.trim().strip() == ""){
            return true;
        }
        ArrayList<String> groupList = Group.parseGroup();
        if(!groupList.contains(group)){
            message.setText(message.getText() + "Error: " + group + " is not a valid group");
            return false;
        }
        return true;
    }

    private static void processPermChange(String fileName, String gDelName, String gAddName, String uDelName, String uAddName) throws IOException, ParseException {
        message.setText("Permission Modification Submitted\n\n");

        if(fileName == null){
            message.setText(message.getText() + "Error: Please select a file to modify\n");
            return;
        }
        if(gDelName == "" && gAddName == "" && uDelName == "" && uAddName == ""){
            message.setText(message.getText() + "Error: Please enter permission info to update\n");
            return ;
        }
        if (!validGroup(gAddName) || !validGroup(gDelName) || !validUser(uAddName) || !validUser(uDelName)){
            return;
        }
        System.out.println("MADE IT PAST VALIDATIONS");

        ManifestParser manifestParser = new ManifestParser(fileName);
        if(gAddName != null && gAddName.trim().strip() != ""){

            boolean updatedR = manifestParser.addPermission("group", gAddName, 'r' );
            boolean updatedW = manifestParser.addPermission("group", gAddName, 'w' );
            if (updatedR == false || updatedW ==false)
                message.setText(message.getText() + "Error: Group addition unsuccessful of " + gAddName + " try again\n");
            else
                message.setText(message.getText() + "Success: Group " + gAddName + " was successfully added to " + fileName + "\n");

        }
        if(gDelName != null && gDelName.trim().strip() != ""){
            boolean updatedR = manifestParser.removePermission("group", gDelName, 'r' );
            boolean updatedW = manifestParser.removePermission("group", gDelName, 'w' );
            if (updatedR == false || updatedW == false)
                message.setText(message.getText() + "Error: Group removal unsuccessful of " + gDelName + " try again\n");
            else
                message.setText(message.getText() + "Success: Group " + gDelName + " was successfully removed from " + fileName + "\n");

        }
        if(uAddName != null && uAddName.trim().strip() != ""){
            boolean updatedR = manifestParser.addPermission("user", uAddName, 'r');
            boolean updatedW = manifestParser.addPermission("user", uAddName, 'w');
            if (updatedR == false || updatedW == false)
                message.setText(message.getText() + "Error: User addition unsuccessful of " + uAddName + ", try again\n" );
            else
                message.setText(message.getText() + "Success: User " + uAddName + " was successfully added to " + fileName +"\n");

        }
        if(uDelName != null && uDelName.trim().strip() != ""){
            boolean updatedR = manifestParser.removePermission("user", uDelName, 'r');
            boolean updatedW = manifestParser.removePermission("user", uDelName, 'w');
            if (updatedR == false || updatedW == false)
                message.setText(message.getText() + "Error: User removal unsuccessful of " + uDelName + " try again\n\n");
            else
                message.setText(message.getText() + "Success: User " + uDelName + " was successfully removed from " + fileName + "\n");
        }

    }





}
