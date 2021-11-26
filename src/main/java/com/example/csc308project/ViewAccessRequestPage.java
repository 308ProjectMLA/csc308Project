package com.example.csc308project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ViewAccessRequestPage {

    Text messages;

    final ObservableList<FileRequest>  requestData = FXCollections.observableArrayList();

    private final TableView requestTable = new TableView<>();

    private void tempDataMaker(){
        addRequestToTable(new FileRequest("Jacob Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jane Smith",  "fileB", "r"));
        addRequestToTable(new FileRequest("Jack Smith",  "fileB", "w"));
        addRequestToTable(new FileRequest("Jake Smith",  "fileA", "w"));
        addRequestToTable(new FileRequest("Jill Smith",  "fileB", "r"));
    }

    public void addRequestToTable(FileRequest request){
        requestData.add(request);
    }

//    private void popUp(Label label)
//    {
//        Popup popup = new Popup();
//        label.setStyle(" -fx-background-color: gray;");
//
//        label.setMinWidth(200);
//        label.setMinHeight(100);
//        label.setAlignment(Pos.CENTER);
//
//        popup.show(Main.stage);
//
//        Button exit = new Button("X");
//        exit.setOnAction((ActionEvent event) -> {
//            popup.hide();
//        });
//        exit.setAlignment(Pos.TOP_RIGHT);
//
//        popup.getContent().addAll(label, exit);
//        popup.centerOnScreen();
//    }



    private void addApproveButtonToTable() {
        TableColumn<FileRequest, Void> approveCol = new TableColumn("Approve");

        Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>> cellFactory = new Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>>() {
            @Override
            public TableCell<FileRequest, Void> call(final TableColumn<FileRequest, Void> param) {
                TableCell<FileRequest, Void> cell = new TableCell<FileRequest, Void>() {
                    Button approveButton = new Button("✓");
                    {
                        approveButton.setOnAction((ActionEvent event) -> {
                            FileRequest currentRequest = getTableView().getItems().get(getIndex());
                            System.out.println("approve request: " + currentRequest);
                            String name = currentRequest.getName();
                            String file = currentRequest.getFileName();
                            //ADD USER TO PERMISSION, return true if completed
                            ManifestParser manifestParser = new ManifestParser(file);
                            try {
                                manifestParser.addPermission("user", name, 'r');
                                manifestParser.addPermission("user", name, 'w');
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            requestData.remove(currentRequest);
                            messages.setText("Approved: " + name + " for file " + file);
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
        TableColumn<FileRequest, Void> declineCol = new TableColumn("Decline");

        Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>> cellFactory = new Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>>() {
            @Override
            public TableCell<FileRequest, Void> call(final TableColumn<FileRequest, Void> param) {
                TableCell<FileRequest, Void> cell = new TableCell<FileRequest, Void>() {
                    Button declineButton = new Button("X");
                    {
                        declineButton.setOnAction((ActionEvent event) -> {
                            FileRequest currentRequest = getTableView().getItems().get(getIndex());
                            String name = currentRequest.getName();
                            String file = currentRequest.getFileName();
                            messages.setText("Declined Request: " + name + " for " + file);
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
        nameCol.setMinWidth(200);
        TableColumn fileCol = new TableColumn("File Name");
        fileCol.setMinWidth(284);
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
        messages = new Text("");
        VBox pageVBox = new VBox();

        //create page title
        Main.updateTitle("Access Requests");

        HBox header = new HBox(200);
        Text pageTitle = new Text("Access Requests");
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

        TableView requestTable = createTable();

        //create page
        pageVBox.getChildren().addAll(header, requestTable, messages);
        pageVBox.setAlignment(Pos.TOP_CENTER);
        pageVBox.setStyle("-fx-background-color: #9da5b0;");

        return pageVBox;
    }

}
