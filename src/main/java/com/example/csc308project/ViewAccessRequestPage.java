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

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class ViewAccessRequestPage {

    private Text message;

    private ArrayList<String> messages;

    //final ObservableList<FileRequest>  requestData = FXCollections.observableArrayList();

    private final TableView requestTable = new TableView<>();

    // TODO Disable this and make requests persistent
    private void csvReader() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(Main.DATA_DIR + Main.REQ_CSV));
        String line;
        while ((line = csvReader.readLine()) != null) {
            String[] data = line.split(",");

            for(int i = 0; i < data.length; i+=3){
                addRequestToTable(new FileRequest(data[i],  data[i+1], data[2]));
            }
        }
        csvReader.close();
    }

    public void addRequestToTable(FileRequest request){
        Main.requestData.add(request);
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

    private void updateCSV(){
        //look at the request data thing that is in main
            try {
                //FileWriter myWriter2 = new FileWriter(Main.DATA_DIR + "accessRequests.csv");
                //myWriter2.close();
                FileWriter myWriter = new FileWriter(Main.DATA_DIR + Main.REQ_CSV);
                for(int i = 0; i < Main.requestData.size(); i++) {
                    //write it back out to the csv
                    myWriter.write(Main.requestData.get(i).getName() + ",");
                    myWriter.write(Main.requestData.get(i).getFileName() + ",");
                    myWriter.write(Main.requestData.get(i).getPermission() + ",");
                }
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    private void approval(FileRequest currentRequest){
        String name = currentRequest.getName();
        String file = currentRequest.getFileName();
        String permission = currentRequest.getPermission();

        ManifestParser manifestParser = new ManifestParser(file);

        if(permission.equals("r")){
            try {
                manifestParser.addPermission("user", name, 'r');
            } catch (Exception ignored) {}
        }
        if(permission.equals("w")){
            try {
                manifestParser.addPermission("user", name, 'w');
            } catch (Exception ignored) {}
        }

        Main.requestData.remove(currentRequest);

        //remove the current request from csv file, will probably make a function (update csv function)

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
                        approveButton.setId(Main.BUTTON_ID);
                        approveButton.getStylesheets().add(Main.BUTTON_STYLE);

                        approveButton.setOnAction((ActionEvent event) -> {
                            FileRequest currentRequest = getTableView().getItems().get(getIndex());
                            approval(currentRequest); // this just initiates the approval process
                            updateCSV();
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
                        declineButton.setId(Main.BUTTON_ID);
                        declineButton.getStylesheets().add(Main.BUTTON_STYLE);

                        declineButton.setOnAction((ActionEvent event) -> {
                            FileRequest currentRequest = getTableView().getItems().get(getIndex());
                            String name = currentRequest.getName();
                            String file = currentRequest.getFileName();
                            addMessage("Decline Request: " + name + " for file " + file + "\n");
                            Main.requestData.remove(currentRequest); //this is the acutal removal logic but we can make a function
                            //update csv function
                            updateCSV();
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

        requestTable.setItems(Main.requestData);

        requestTable.getColumns().addAll(nameCol, fileCol, permCol);

        addApproveButtonToTable();
        addDeclineButtonToTable();

        return requestTable;
    }

    public VBox pageLayout() {
        //tempDataMaker();
        Main.requestData = FXCollections.observableArrayList();
        try {
            csvReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        backButton.setOnAction(actionEvent ->
            Main.updatePage(managePermissionPage.pageLayout(),"managePermissions"));

        backButton.setId(Main.BUTTON_ID);
        backButton.getStylesheets().add(Main.BUTTON_STYLE);

        header.getChildren().addAll(pageTitle, backButton);

        TableView requestTable2 = createTable();

        //create page
        pageVBox.getChildren().addAll(header, requestTable2, message);
        pageVBox.setAlignment(Pos.TOP_CENTER);
        pageVBox.setStyle("-fx-background-image: url('file:img/network-background.png');");

        return pageVBox;
    }

}
