package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.AdminService;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private AdminService adminTable = new AdminService();
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private Button loginButton;

    @FXML
    private Label dbStatus;
    @FXML
    private Label loginStatus;

    public boolean isLogin(String userName, String passWord) throws SQLException {
        return adminTable.isExists(userName, passWord);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (adminTable.isDatabaseConnected()){
            this.dbStatus.setText("Kết nối thành công");
        } else {
            this.dbStatus.setText("Kết nối thất bại");
        }
    }

    @FXML
    public void login(ActionEvent event){
        try {
            if (adminTable.isExists(this.userName.getText(), this.passWord.getText())) {
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close();
                adminLogin();
            } else {
                this.loginStatus.setText("Sai tên đăng nhập hoặc mật khẩu!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void adminLogin(){
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminRoot = (Pane)adminLoader.load(getClass().getResource("/view/admin.fxml"));
            AdminController adminController = (AdminController)adminLoader.getController();
            Scene scene = new Scene(adminRoot);
            adminStage.setScene(scene);
            adminStage.setTitle("Bảng điều khiển");
            adminStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
