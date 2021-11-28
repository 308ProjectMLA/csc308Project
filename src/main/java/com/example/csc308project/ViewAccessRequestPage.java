package com.example.csc308project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class ViewAccessRequestPage {

    private Text message;

    private ArrayList<String> messages;

    final ObservableList<FileRequest>  requestData = FXCollections.observableArrayList();

    private final TableView requestTable = new TableView<>();

    private void tempDataMaker(){
        addRequestToTable(new FileRequest("Jacob Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jane Smith",  "fileB", "r"));
        addRequestToTable(new FileRequest("Jack Smith",  "fileB", "w"));
        addRequestToTable(new FileRequest("Jake Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jill Smith",  "fileB", "r"));
        addRequestToTable(new FileRequest("Jacob Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jacob Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jane Smith",  "fileB", "r"));
        addRequestToTable(new FileRequest("Jack Smith",  "fileB", "w"));
        addRequestToTable(new FileRequest("Jake Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jill Smith",  "fileB", "r"));
        addRequestToTable(new FileRequest("Jacob Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jacob Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jacob Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jane Smith",  "fileB", "r"));
        addRequestToTable(new FileRequest("Jack Smith",  "fileB", "w"));
        addRequestToTable(new FileRequest("Jake Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jill Smith",  "fileB", "r"));
        addRequestToTable(new FileRequest("Jacob Smith",  "fileA", "w"));
    }

    public void addRequestToTable(FileRequest request){
        requestData.add(request);
    }

    private void addMessage(String messageText){
        message.setText("\n");
        message.setFill(Color.WHITE);
        messages.add(0, messageText);

        int i = 0;
        while(i < messages.size() && i < 5){
            message.setText(message.getText() + messages.get(i));
            i++;
        }

    }

    private void approval(FileRequest currentRequest){
        System.out.println("approve request: " + currentRequest);
        String name = currentRequest.getName();
        String file = currentRequest.getFileName();
        String permission = currentRequest.getPermission();

        ManifestParser manifestParser = new ManifestParser(file);

        if(permission.equals("r")){
            try {
                manifestParser.addPermission("user", name, 'r');
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(permission.equals("w")){
            try {
                manifestParser.addPermission("user", name, 'w');
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        requestData.remove(currentRequest);
        addMessage("Approved: " + name + " for file " + file + "\n");
    }

    private void addApproveButtonToTable() {
        TableColumn<FileRequest, Void> approveCol = new TableColumn<>("Approve");

        Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>> cellFactory = new Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>>() {
            @Override
            public TableCell<FileRequest, Void> call(final TableColumn<FileRequest, Void> param) {
                TableCell<FileRequest, Void> cell = new TableCell<FileRequest, Void>() {
                    Button approveButton = new Button("✓");
                    {
                        approveButton.setId("round-yellow");
                        approveButton.getStylesheets().add("file:cssfiles/yellowbutton.css");

                        approveButton.setOnAction((ActionEvent event) -> {
                            FileRequest currentRequest = getTableView().getItems().get(getIndex());
                            approval(currentRequest);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(approveButton);
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        };

        approveCol.setCellFactory(cellFactory);
        requestTable.getColumns().add(approveCol);

    }
    private void addDeclineButtonToTable() {
        TableColumn<FileRequest, Void> declineCol = new TableColumn<>("Decline");

        Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>> cellFactory = new Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>>() {
            @Override
            public TableCell<FileRequest, Void> call(final TableColumn<FileRequest, Void> param) {
                TableCell<FileRequest, Void> cell = new TableCell<FileRequest, Void>() {
                    Button declineButton = new Button("X");
                    {
                        declineButton.setId("round-yellow");
                        declineButton.getStylesheets().add("file:cssfiles/yellowbutton.css");

                        declineButton.setOnAction((ActionEvent event) -> {
                            FileRequest currentRequest = getTableView().getItems().get(getIndex());
                            String name = currentRequest.getName();
                            String file = currentRequest.getFileName();
                            addMessage("Decline Request: " + name + " for file " + file + "\n");
                            requestData.remove(currentRequest);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(declineButton);
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        };

        declineCol.setCellFactory(cellFactory);
        requestTable.getColumns().add(declineCol);
    }


    public TableView createTable(){

        requestTable.setMaxWidth(Main.WINDOW_WIDTH - 100);
        requestTable.setMaxHeight(Main.PAGE_HEIGHT - 200);

        TableColumn nameCol = new TableColumn("Requester Name");
        nameCol.setMinWidth(188);
        TableColumn fileCol = new TableColumn("File Name");
        fileCol.setMinWidth(280);
        TableColumn permCol = new TableColumn("Permissions");

        nameCol.setCellValueFactory(new PropertyValueFactory<FileRequest,String>("name"));
        fileCol.setCellValueFactory(new PropertyValueFactory<FileRequest,String>("fileName"));
        permCol.setCellValueFactory(new PropertyValueFactory<FileRequest,String>("permission"));

        requestTable.setItems(requestData);

        requestTable.getColumns().addAll(nameCol, fileCol, permCol);

        addApproveButtonToTable();
        addDeclineButtonToTable();

        return requestTable;
    }

    public VBox pageLayout() {
        tempDataMaker();
        message = new Text("");
        messages = new ArrayList<>();
        VBox pageVBox = new VBox();

        //create page title
        Main.updateTitle("Access Requests");

        HBox header = new HBox(200);
        Text pageTitle = new Text("Access Requests");
        pageTitle.setFill(Color.WHITE);
        pageTitle.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        header.setPadding(new Insets(40, 0 , 100, 0 ));
        header.setAlignment(Pos.TOP_CENTER);

        //back button
        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button backButton = new Button("Back to Manage Permissions");
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(managePermissionPage.pageLayout(),"managePermissions");
        });

        backButton.setId("round-yellow");
        backButton.getStylesheets().add("file:cssfiles/yellowbutton.css");

        header.getChildren().addAll(pageTitle, backButton);

        TableView requestTable = createTable();

        //create page
        pageVBox.getChildren().addAll(header, requestTable, message);
        pageVBox.setAlignment(Pos.TOP_CENTER);
        pageVBox.setStyle("-fx-background-image: url('file:img/network-background.png');");

        return pageVBox;
    }

}
