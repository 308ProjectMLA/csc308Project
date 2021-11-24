package com.example.csc308project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.event.ActionEvent;

public class ViewAccessRequestPage {

    final ObservableList<FileRequest>  requestData = FXCollections.observableArrayList(
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File A"),
            new FileRequest("Jane Doe", "jane.doe@example.com", "File A"),
            new FileRequest("Alice Jackson", "alice@example.com", "File B"),
            new FileRequest("asdfjksdfjlaskdjflaskjdflkasjdf;laksjfdlkasjfdlaskjdfklasjfdlkasjfdlksajdflskjdflskdjflaskjdflaskjfd", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C"),
            new FileRequest("Jacob Smith", "jacob.smith@example.com", "File C")
            );

    private final TableView requestTable = new TableView<>();


    private void addApproveButtonToTable() {
        TableColumn<FileRequest, Void> approveCol = new TableColumn("Approve");

        Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>> cellFactory = new Callback<TableColumn<FileRequest, Void>, TableCell<FileRequest, Void>>() {
            @Override
            public TableCell<FileRequest, Void> call(final TableColumn<FileRequest, Void> param) {
                TableCell<FileRequest, Void> cell = new TableCell<FileRequest, Void>() {
                    Button approveButton = new Button("✓");
                    {
                        approveButton.setOnAction((ActionEvent event) -> {
                            FileRequest request = getTableView().getItems().get(getIndex());
                            System.out.println("approve request: " + request);
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
                            FileRequest request = getTableView().getItems().get(getIndex());
                            System.out.println("decline request: " + request);
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

        requestTable.setMaxWidth(700);
        requestTable.setMaxHeight(Main.PAGE_HEIGHT - 200);

        TableColumn nameCol = new TableColumn("Requester Name");
        nameCol.setMinWidth(Main.FIELD_WIDTH);
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(Main.FIELD_WIDTH);
        TableColumn fileCol = new TableColumn("File Name");
        fileCol.setMinWidth(160);


        nameCol.setCellValueFactory(new PropertyValueFactory<FileRequest,String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<FileRequest,String>("email"));
        fileCol.setCellValueFactory(new PropertyValueFactory<FileRequest,String>("fileName"));


        requestTable.setItems(requestData);

        requestTable.getColumns().addAll(nameCol, emailCol, fileCol);

        addApproveButtonToTable();
        addDeclineButtonToTable();

        return requestTable;

    }

    public VBox pageLayout() {
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
            Main.updatePage(managePermissionPage.pageLayout(),"managePermissions");
        });

        header.getChildren().addAll(pageTitle, backButton);

        TableView requestTable = createTable();

        //create page
        pageVBox.getChildren().addAll(header, requestTable);
        pageVBox.setAlignment(Pos.TOP_CENTER);

        return pageVBox;
    }

}
