package controller;

import animation.FadeInRightTransition;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

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
    }


}
