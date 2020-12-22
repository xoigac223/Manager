package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.AdminService;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
    private Label loginStatus, exit, minimize;

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
            adminStage.initStyle(StageStyle.TRANSPARENT);
            adminStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void quenMatKhauClicked(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quên mật khẩu");
        alert.setHeaderText(null);
        alert.setContentText("Liên hệ: https://www.facebook.com/xoigac223");
        alert.showAndWait();
    }

    @FXML
    private void setHover(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void setDefault(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color:  #ffffff;");
    }

    @FXML
    private void setDefault2(javafx.scene.input.MouseEvent event){
        minimize.setStyle("-fx-background-color:  #ffffff;");
    }

    @FXML
    private void setHover2(){
        minimize.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void handleMinimizeClicked(MouseEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleExitClicked(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(0);
        }
    }

}
