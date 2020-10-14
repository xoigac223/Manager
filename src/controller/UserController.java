package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.UserData;
import model.UsersTable;
import netscape.security.UserTarget;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class UserController implements Initializable {
    @FXML
    private TableView<UserData> userDataTableView;

    @FXML
    private TableColumn<UserData, String> idColumn;

    @FXML
    private TableColumn<UserData, String> sttColumn;

    @FXML
    private TableColumn<UserData, String> usernameColumn;

    @FXML
    private TableColumn<UserData, String> passwordColumn;

    @FXML
    private TableColumn<UserData, String> desColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
    }

    private ObservableList<UserData> data;
    private UsersTable usersTable = new UsersTable();
    public void loadTable(){
        try {
            data = FXCollections.observableArrayList();
            data = usersTable.getAllUsers();
        } catch (SQLException e){
            e.printStackTrace();
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("id"));
        sttColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("STT"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("Username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("Password"));
        desColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("Description"));
        userDataTableView.setItems(null);
        userDataTableView.setItems(data);
    }


}