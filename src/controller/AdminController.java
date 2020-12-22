package controller;

import animation.FadeInRightTransition;
import animation.FadeInTransition;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Label exit, minimize;

    @FXML
    private Label dateLabel, timeLabel;
    @FXML
    private JFXButton btnHome, btnUserManagement, btnQuanLyQuy, btnHoKhau, btnThongKe;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane loadPane,blur, cobain;

    @FXML
    private StackPane trans;

    @FXML
    private ImageView imageUser;

    @FXML
    private VBox admin;

    @FXML
    private void setBackgroundUserManagement(javafx.scene.input.MouseEvent event){
        btnHome.setStyle("-fx-background-color: #ECF0F1");
        btnHoKhau.setStyle("-fx-background-color: #ECF0F1");
        btnQuanLyQuy.setStyle("-fx-background-color: #ECF0F1");
        btnThongKe.setStyle("-fx-background-color: #ECF0F1");
        btnUserManagement.setStyle("-fx-background-color: #D2D7D3");
    }
    @FXML
    private void setBackgroundQuanLyQuy(javafx.scene.input.MouseEvent event){
        btnHome.setStyle("-fx-background-color: #ECF0F1");
        btnHoKhau.setStyle("-fx-background-color: #ECF0F1");
        btnThongKe.setStyle("-fx-background-color: #ECF0F1");
        btnUserManagement.setStyle("-fx-background-color: #ECF0F1");
        btnQuanLyQuy.setStyle("-fx-background-color: #D2D7D3");
    }

    @FXML
    private void setBackgroundThongKe(javafx.scene.input.MouseEvent event){
        btnHome.setStyle("-fx-background-color: #ECF0F1");
        btnHoKhau.setStyle("-fx-background-color: #ECF0F1");
        btnQuanLyQuy.setStyle("-fx-background-color: #ECF0F1");
        btnUserManagement.setStyle("-fx-background-color: #ECF0F1");
        btnThongKe.setStyle("-fx-background-color: #D2D7D3");
    }

    @FXML
    private void setBackgroundHome(javafx.scene.input.MouseEvent event){
        btnThongKe.setStyle("-fx-background-color: #ECF0F1");
        btnHoKhau.setStyle("-fx-background-color: #ECF0F1");
        btnQuanLyQuy.setStyle("-fx-background-color: #ECF0F1");
        btnUserManagement.setStyle("-fx-background-color: #ECF0F1");
        btnHome.setStyle("-fx-background-color: #D2D7D3");
    }

    @FXML
    private void userManagementClicked() throws IOException{
        userManagementMenu();
    }

    @FXML
    private void quanLyQuyClicked() throws IOException{
        quanLyQuyMenu();
    }

    @FXML
    private void thongKeClicked() throws IOException {
        thongKeMenu();
    }

    @FXML
    private void homeClicked() throws IOException {
        homeMenu();
    }

    public void thongKeMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInRightTransition(rootPane).play();
            FXMLLoader adminLoader = new FXMLLoader();
            AnchorPane pane = (AnchorPane)adminLoader.load(getClass().getResource("/view/thongke/thongke.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void userManagementMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInRightTransition(rootPane).play();
            FXMLLoader adminLoader = new FXMLLoader();
            AnchorPane pane = (AnchorPane)adminLoader.load(getClass().getResource("/view/nhankhau/nhankhau.fxml").openStream());
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void quanLyQuyMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInRightTransition(rootPane).play();
            FXMLLoader adminLoader = new FXMLLoader();
            AnchorPane pane = (AnchorPane)adminLoader.load(getClass().getResource("/view/khoanthu/quanlykhoanthu.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void printTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                                timeLabel.setText(simpleDateFormat.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        printTime();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        dateLabel.setText(sdf.format(date));
        try {
            homeMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void homeMenu() throws IOException{
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            FXMLLoader adminLoader = new FXMLLoader();
            AnchorPane pane = (AnchorPane)adminLoader.load(getClass().getResource("/view/home.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
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

    @FXML
    private void setHover(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void setDefault(javafx.scene.input.MouseEvent event){
        exit.setStyle("-fx-background-color:  #4183D7;");
    }

    @FXML
    private void setDefault2(javafx.scene.input.MouseEvent event){
        minimize.setStyle("-fx-background-color:  #4183D7;");
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
    private void logoutClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(root);
            app_stage.setScene(scene);
            app_stage.setTitle("Manager");
            app_stage.setResizable(false);
            app_stage.show();
        }
    }
}
