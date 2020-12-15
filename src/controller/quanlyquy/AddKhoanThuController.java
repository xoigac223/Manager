package controller.quanlyquy;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.KhoanThuModel;
import service.KhoanThuService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AddKhoanThuController implements Initializable {

    ObservableList<String> comboBatBuoc = FXCollections.observableArrayList("Có","Không");

    @FXML
    private DatePicker thoiGianPicker, hanNopPicker;

    @FXML
    private ComboBox batBuocComboBox;

    @FXML
    private TextField tenKhoanThuText, soTienText, thoiGianText, hanNopText;

    @FXML
    private void thoiGianClicked(ActionEvent event){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String thoiGianText = sdf.format(Date.valueOf(thoiGianPicker.getValue()));
        this.thoiGianText.setText(thoiGianText);
    }

    @FXML
    private void hanNopClicked(ActionEvent event){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String hanNopText = sdf.format(Date.valueOf(hanNopPicker.getValue()));
        this.hanNopText.setText(hanNopText);
    }

    @FXML
    private void themClicked(ActionEvent event) throws IOException, ParseException {
        inputMethod();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComBoBox();
        soTienText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}([\\.]\\d{0,4})?")) {
                    soTienText.setText(oldValue);
                }
            }
        });
    }

    private void setComBoBox(){
        batBuocComboBox.setValue("Có");
        batBuocComboBox.setItems(comboBatBuoc);
    }

    private void clear(){
        tenKhoanThuText.setText("");
        soTienText.setText("");
        batBuocComboBox.setValue("Không");
        thoiGianText.setText("");
        hanNopText.setText("");
    }
    @FXML
    private void batBuocClicked(){
        if (batBuocComboBox.getSelectionModel().getSelectedItem().toString().equals("Không")) {
            soTienText.setText("0");
            soTienText.setDisable(true);
        } else {
            soTienText.setText("");
            soTienText.setDisable(false);
        }
    }

    private void inputMethod(){
        if(tenKhoanThuText.getText().equals("")||thoiGianText.getText().equals("")
                ||hanNopText.getText().equals("")||soTienText.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Nhập đầy đủ các thông tin");
            alert.showAndWait();
        }
        else{
            LocalDate localDate1 = thoiGianPicker.getValue();
            Instant instant1 = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date1 = Date.from(instant1);
            java.sql.Date thoiGian = new java.sql.Date(date1.getTime());

            LocalDate localDate2 = hanNopPicker.getValue();
            Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date2 = Date.from(instant2);
            java.sql.Date hanNop = new java.sql.Date(date2.getTime());

            KhoanThuModel khoanThuModel = new KhoanThuModel(tenKhoanThuText.getText(), Double.valueOf(soTienText.getText()), thoiGian, hanNop);
            String batBuoc = batBuocComboBox.getSelectionModel().getSelectedItem().toString();
            if (batBuoc.equals("Không")) khoanThuModel.setBatBuoc(false);
            else khoanThuModel.setBatBuoc(true);

            KhoanThuService khoanThuService = new KhoanThuService();
            int rs = 0;
            try {
                rs = khoanThuService.addKhoanThu(khoanThuModel);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(rs == 1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setHeaderText(null);
                alert.setContentText("Thêm thành công");
                alert.showAndWait();
                clear();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Thêm thất bại! Vui lòng thử lại");
                alert.showAndWait();
            }
        }
    }

}
