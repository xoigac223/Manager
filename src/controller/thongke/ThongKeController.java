package controller.thongke;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXTextField;
import controller.quanlyquy.ThuPhiController;
import datatable.KhoanThuData;
import datatable.ThongKeData;
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
import service.ThongKeService;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {
    @FXML
    private TableView<ThongKeData> thongKeTableView;

    @FXML
    private TableColumn<ThongKeData, String> idColumn;

    @FXML
    private TableColumn<ThongKeData, String> sttColumn;

    @FXML
    private TableColumn<ThongKeData, String> hoTenColumn;

    @FXML
    private TableColumn<ThongKeData, String> diaChiColumn;

    @FXML
    private TableColumn<ThongKeData, String> soTienColumn;

    @FXML
    private JFXTextField searchText;

    @FXML
    private AnchorPane unclear, loadPane;

    @FXML
    private StackPane trans;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private Button btnClose;

    private ObservableList<ThongKeData> data;
    private ThongKeService thongKeService = new ThongKeService();
    private String idHoKhau = "", hoTen = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setStyleTable();
        loadTable();
    }

    @FXML
    private void closeClicked(ActionEvent event) {
        loadTable();
        unclear.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        clearParameter();
    }

    @FXML
    private void chiTietClicked() throws IOException {
        if (idHoKhau.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Chọn một hộ khẩu");
            alert.showAndWait();
        } else {
            openThongKe();
        }
    }

    @FXML
    private void rowClicked(MouseEvent event) throws IOException {
        try {
            if (event.getClickCount() == 1) {
                idHoKhau = thongKeTableView.getSelectionModel().getSelectedItem().getId();
                hoTen = thongKeTableView.getSelectionModel().getSelectedItem().getHoTenChuHo();
            } else if (event.getClickCount() == 2) {
                idHoKhau = thongKeTableView.getSelectionModel().getSelectedItem().getId();
                hoTen = thongKeTableView.getSelectionModel().getSelectedItem().getHoTenChuHo();
                openThongKe();
            } else if (event.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(thongKeTableView, event.getScreenX(), event.getScreenY());
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private void refreshClicked(ActionEvent event){
        loadTable();
    }

    private void setStyleTable() {
        soTienColumn.setStyle("-fx-alignment: CENTER");
    }

    private void loadTable() {
        try {
            data = FXCollections.observableArrayList();
            animationFade(thongKeTableView);
            data = thongKeService.filter(searchText.getText());
            idColumn.setCellValueFactory(new PropertyValueFactory<ThongKeData, String>("id"));
            sttColumn.setCellValueFactory(new PropertyValueFactory<ThongKeData, String>("stt"));
            hoTenColumn.setCellValueFactory(new PropertyValueFactory<ThongKeData, String>("hoTenChuHo"));
            diaChiColumn.setCellValueFactory(new PropertyValueFactory<ThongKeData, String>("diaChiHienTai"));
            soTienColumn.setCellValueFactory(new PropertyValueFactory<ThongKeData, String>("tongSoTien"));
            thongKeTableView.setItems(null);
            thongKeTableView.setItems(data);
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
        idHoKhau = "";
        hoTen = "";
    }

    public void openThongKe() throws IOException {
        unclear.setEffect(new GaussianBlur(10));
        btnClose.setLayoutX(470);
        new FadeInRightTransition(trans).play();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/thongke/thongketheohokhau.fxml"));
        loader.setControllerFactory(c -> {
            return new ThongKeTheoHoKhauController(idHoKhau, hoTen);
        });
        AnchorPane pane = loader.load();
        ThongKeTheoHoKhauController thongKeTheoHoKhauController = loader.getController();
        loadPane.getChildren().setAll(pane);
    }
}
