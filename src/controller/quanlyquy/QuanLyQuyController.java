package controller.quanlyquy;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import datatable.KhoanThuData;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import service.KhoanThuService;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;


public class QuanLyQuyController implements Initializable {
    ObservableList<String> comboFilter = FXCollections.observableArrayList("Tất cả", "Ngày", "Tháng");
    ObservableList<String> comboLoaiKhoanThu = FXCollections.observableArrayList("Tất cả", "Bắt buộc", "Không bắt buộc");
    ObservableList<String> comboMonth = FXCollections.observableArrayList("Tất cả", "January", "February"
            , "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    @FXML
    private TableView<KhoanThuData> khoanThuTableView;

    @FXML
    private TableColumn<KhoanThuData, String> idColumn;

    @FXML
    private TableColumn<KhoanThuData, String> sttColumn;

    @FXML
    private TableColumn<KhoanThuData, String> tenKhoanThuColumn;

    @FXML
    private TableColumn<KhoanThuData, String> batBuocColumn;

    @FXML
    private TableColumn<KhoanThuData, String> soTienColumn;

    @FXML
    private TableColumn<KhoanThuData, String> thoiGianColumn;

    @FXML
    private TableColumn<KhoanThuData, String> hanNopColumn;

    @FXML
    private AnchorPane unclear, loadPane;

    @FXML
    private StackPane trans;

    @FXML
    private ComboBox filter, month, loaiKhoanThuComboBox;

    @FXML
    private JFXTextField date, year, searchText;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private JFXButton btnClose;

    String id = "", stt = "", tenKhoanThu = "", batBuoc = "", soTien = "", thoiGian = "", hanNop = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFilter();
        setComboLoaiKhoanThu();
        setDate();
        setMonth();
        setYear();
        setStyleTable();
        loadTable();
    }

    private ObservableList<KhoanThuData> data;
    private KhoanThuService khoanThuService = new KhoanThuService();

    @FXML
    private void addClicked() throws IOException {
        unclear.setEffect(new GaussianBlur(10));
        btnClose.setLayoutX(310);
        new FadeInRightTransition(trans).play();
        FXMLLoader addNhanKhauLoader = new FXMLLoader();
        AnchorPane pane = (AnchorPane) addNhanKhauLoader.load(getClass().getResource("/view/khoanthu/addkhoanthu.fxml").openStream());
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void thuPhiClicked() throws IOException {
        if (id.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Chọn một khoản thu!");
            alert.showAndWait();
        } else {
            openThuPhi();
        }
    }

    public void openThuPhi() throws IOException {
        unclear.setEffect(new GaussianBlur(10));
        btnClose.setLayoutX(690);
        new FadeInRightTransition(trans).play();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/khoanthu/thuphi.fxml"));
        loader.setControllerFactory(c -> {
            return new ThuPhiController(id, stt, tenKhoanThu, batBuoc, soTien, thoiGian, hanNop);
        });
        AnchorPane pane = loader.load();
        ThuPhiController thuPhiController = loader.getController();
        loadPane.getChildren().setAll(pane);
    }
    @FXML
    private void closeClicked(ActionEvent event) {
        loadTable();
        unclear.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        clearParameter();
    }

    private void setFilter() {
        filter.setValue("Tất cả");
        filter.setItems(comboFilter);
    }

    private void setComboLoaiKhoanThu() {
        loaiKhoanThuComboBox.setValue("Tất cả");
        loaiKhoanThuComboBox.setItems(comboLoaiKhoanThu);
    }

    private void setDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        this.date.setText(sdf.format(date));

        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        datePicker.setValue(LocalDate.parse(sdf1.format(date1)));
    }

    private void setMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");

        month.setValue(sdf.format(date));
        month.setItems(comboMonth);
    }

    private void setYear() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        year.setText(sdf.format(date));
    }

    private void setStyleTable() {
        sttColumn.setStyle("-fx-alignment: CENTER");
        batBuocColumn.setStyle("-fx-alignment: CENTER");
        thoiGianColumn.setStyle("-fx-alignment: CENTER");
        soTienColumn.setStyle("-fx-alignment: CENTER");
        hanNopColumn.setStyle("-fx-alignment: CENTER");
    }

    @FXML
    private void refreshClicked(ActionEvent event) {
        loadTable();
    }

    @FXML
    private void filterClicked(ActionEvent event) {
        if (filter.getSelectionModel().getSelectedItem().toString().equals("Ngày")) {
            month.setVisible(false);
            date.setVisible(true);
            datePicker.setVisible(true);
            year.setVisible(false);
            setDate();
        } else if (filter.getSelectionModel().getSelectedItem().toString().equals("Tháng")) {
            month.setVisible(true);
            date.setVisible(false);
            datePicker.setVisible(false);
            year.setVisible(true);
            setMonth();
            setYear();
        } else {
            month.setVisible(false);
            date.setVisible(false);
            datePicker.setVisible(false);
            year.setVisible(false);
        }
    }

    @FXML
    private void dateClicked(ActionEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String dateText = sdf.format(java.sql.Date.valueOf(datePicker.getValue()));
        date.setText(dateText);
    }

    private void loadTable() {
        try {
            data = FXCollections.observableArrayList();
            animationFade(khoanThuTableView);
            if (filter.getSelectionModel().getSelectedItem().toString().equals("Ngày")) {
                System.out.println(datePicker.getValue().toString());
                data = khoanThuService.filterDate(datePicker.getValue().toString(), searchText.getText(), loaiKhoanThuComboBox.getSelectionModel().getSelectedItem().toString());
            } else if (filter.getSelectionModel().getSelectedItem().toString().equals("Tháng")) {
                data = khoanThuService.filterMonth(month.getSelectionModel().getSelectedItem().toString(), year.getText(), searchText.getText(), loaiKhoanThuComboBox.getSelectionModel().getSelectedItem().toString());
            } else {
                data = khoanThuService.filterAll(searchText.getText(), loaiKhoanThuComboBox.getSelectionModel().getSelectedItem().toString());
            }
            idColumn.setCellValueFactory(new PropertyValueFactory<KhoanThuData, String>("id"));
            sttColumn.setCellValueFactory(new PropertyValueFactory<KhoanThuData, String>("stt"));
            tenKhoanThuColumn.setCellValueFactory(new PropertyValueFactory<KhoanThuData, String>("tenKhoanThu"));
            batBuocColumn.setCellValueFactory(new PropertyValueFactory<KhoanThuData, String>("batBuoc"));
            soTienColumn.setCellValueFactory(new PropertyValueFactory<KhoanThuData, String>("soTien"));
            thoiGianColumn.setCellValueFactory(new PropertyValueFactory<KhoanThuData, String>("thoiGian"));
            hanNopColumn.setCellValueFactory(new PropertyValueFactory<KhoanThuData, String>("hanNop"));
            khoanThuTableView.setItems(null);
            khoanThuTableView.setItems(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void animationFade(Node e) {
        FadeTransition x = new FadeTransition(new Duration(1000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }

    private void clearParameter() {
        id = "";
        stt = "";
        tenKhoanThu = "";
        batBuoc = "";
        soTien = "";
        thoiGian = "";
        hanNop = "";
    }

    @FXML
    private void rowClicked(MouseEvent event) throws IOException {
        try {
            if (event.getClickCount() == 1) {
                id = khoanThuTableView.getSelectionModel().getSelectedItem().getId();
                stt = khoanThuTableView.getSelectionModel().getSelectedItem().getStt();
                tenKhoanThu = khoanThuTableView.getSelectionModel().getSelectedItem().getTenKhoanThu();
                batBuoc = khoanThuTableView.getSelectionModel().getSelectedItem().getBatBuoc();
                soTien = khoanThuTableView.getSelectionModel().getSelectedItem().getSoTien();
                thoiGian = khoanThuTableView.getSelectionModel().getSelectedItem().getThoiGian();
                hanNop = khoanThuTableView.getSelectionModel().getSelectedItem().getHanNop();
            } else if (event.getClickCount() == 2) {
                id = khoanThuTableView.getSelectionModel().getSelectedItem().getId();
                stt = khoanThuTableView.getSelectionModel().getSelectedItem().getStt();
                tenKhoanThu = khoanThuTableView.getSelectionModel().getSelectedItem().getTenKhoanThu();
                batBuoc = khoanThuTableView.getSelectionModel().getSelectedItem().getBatBuoc();
                soTien = khoanThuTableView.getSelectionModel().getSelectedItem().getSoTien();
                thoiGian = khoanThuTableView.getSelectionModel().getSelectedItem().getThoiGian();
                hanNop = khoanThuTableView.getSelectionModel().getSelectedItem().getHanNop();
                openThuPhi();
            } else if (event.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(khoanThuTableView, event.getScreenX(), event.getScreenY());
            }
        } catch (Exception e) {

        }
    }

}