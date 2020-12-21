package controller.quanlyquy;


import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import datatable.EditCell;
import datatable.MyDoubleStringConverter;
import datatable.ThuPhiData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import model.KhoanThuModel;
import model.ThuPhiModel;
import service.KhoanThuService;
import service.ThuPhiService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThuPhiController implements Initializable {
    @FXML
    private Label labelName;

    @FXML
    private TableView thuPhiTableView;

    @FXML
    TableColumn<ThuPhiData, String> idHoKhauColumn;

    @FXML
    TableColumn<ThuPhiData, String> sttColumn;

    @FXML
    TableColumn<ThuPhiData, String> tenChuHoColumn;

    @FXML
    TableColumn<ThuPhiData, String> soThanhVienColumn;

    @FXML
    TableColumn<ThuPhiData, Double> soTienNopColumn;

    @FXML
    TableColumn<ThuPhiData, String> diaChiHienNayColumn;

    @FXML
    TableColumn<ThuPhiData, String> thoiGianNopColumn;

    @FXML
    TableColumn<ThuPhiData, JFXDatePicker> selectedColoum;

    @FXML
    JFXCheckBox cboxSelectAll;

    private static final String DATE_PATTERN = "dd/MM/yyyy";

    @FXML
    public void saveClicked() throws ParseException, SQLException {
        for (ThuPhiData thuPhiData : data){
            if (thuPhiData.getThoiGianNop() != null){
                DateFormat format = new SimpleDateFormat("yyyy-M-dd");
                java.util.Date date = format.parse(thuPhiData.getThoiGianNop());
                java.sql.Date thoiGianNop = new java.sql.Date(date.getTime());
                ThuPhiModel thuPhiModel = new ThuPhiModel(Integer.valueOf(thuPhiData.getIdHoKhau()), Integer.valueOf(id), thuPhiData.getSoTienNop(), thoiGianNop);
                thuPhiService.addThuPhi(thuPhiModel);
            }
        }
        loadTable();
    }
    @FXML
    public void rowClicked(){

    }
    @FXML
    public void refreshClicked(){
        loadTable();
    }
    private ObservableList<ThuPhiData> data;
    private KhoanThuService khoanThuService = new KhoanThuService();
    private ThuPhiService thuPhiService = new ThuPhiService();
    private String id ,stt, tenKhoanThu , batBuoc , soTien , thoiGian , hanNop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableEditable();
        loadTable();
        if (batBuoc.equals("1")){
            labelName.setText(" " + tenKhoanThu + " - Có bắt buộc - " + soTien + "/người");
        } else {
            labelName.setText(" " + tenKhoanThu + " - Không bắt buộc");
        }
    }

    public ThuPhiController(String id, String stt, String tenKhoanThu, String batBuoc, String soTien, String thoiGian, String hanNop) {
        this.id = id;
        this.stt = stt;
        this.tenKhoanThu = tenKhoanThu;
        this.batBuoc = batBuoc;
        this.soTien = soTien;
        this.thoiGian = thoiGian;
        this.hanNop = hanNop;
    }

    public void loadTable(){
        try {
            data = FXCollections.observableArrayList();
            data = khoanThuService.getThuPhi(id, soTien);
        } catch (SQLException e){
            e.printStackTrace();
        }
        idHoKhauColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("idHoKhau"));
        sttColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("stt"));
        tenChuHoColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("hoTenChuHo"));
        soThanhVienColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("soThanhVien"));
        soTienNopColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, Double>("soTienNop"));
        if (batBuoc.equals("0")){
            soTienNopColumn.setEditable(true);
            soTienNopColumn.setCellFactory(
                    EditCell.<ThuPhiData, Double>forTableColumn(
                            new MyDoubleStringConverter()));
            soTienNopColumn.setOnEditCommit(event -> {
                final Double value = event.getNewValue() != null
                        ? event.getNewValue() : event.getOldValue();
                ((ThuPhiData) event.getTableView().getItems()
                        .get(event.getTablePosition().getRow())).setSoTienNop(value);
                thuPhiTableView.refresh();
            });
        }
        diaChiHienNayColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("diaChiHienNay"));
        thoiGianNopColumn.setCellValueFactory(new PropertyValueFactory<ThuPhiData, String>("thoiGianNop"));
        selectedColoum.setCellValueFactory(new PropertyValueFactory<ThuPhiData, JFXDatePicker>("selected"));

        for(ThuPhiData thuPhiData : data){
            JFXDatePicker datePicker = new JFXDatePicker();
            datePicker.setOnAction(event -> {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
                String thoiGianText = sdf.format(Date.valueOf(datePicker.getValue()));
                thuPhiData.setThoiGianNop(thoiGianText);
                if (batBuoc.equals("1")) {
                    thuPhiData.setSoTienNop(Double.valueOf(thuPhiData.getSoTienPhaiNop()));
                }
            });
            if (thuPhiData.getThoiGianNop() != null){
                datePicker.setValue(LocalDate.parse(thuPhiData.getThoiGianNop()));
            }
            thuPhiData.setSelected(datePicker);
        }

        thuPhiTableView.setItems(null);
        thuPhiTableView.setItems(data);
    }

    private void setTableEditable() {
        thuPhiTableView.setEditable(true);
        // allows the individual cells to be selected
        thuPhiTableView.getSelectionModel().cellSelectionEnabledProperty().set(true);
        // when character or numbers pressed it will start edit in editable
        // fields
        thuPhiTableView.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                editFocusedCell();
            } else if (event.getCode() == KeyCode.RIGHT
                    || event.getCode() == KeyCode.TAB) {
                thuPhiTableView.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                // work around due to
                // TableView.getSelectionModel().selectPrevious() due to a bug
                // stopping it from working on
                // the first column in the last row of the table
                selectPrevious();
                event.consume();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void editFocusedCell() {
        final TablePosition<ThuPhiData, ?> focusedCell = thuPhiTableView
                .getFocusModel().getFocusedCell();
        thuPhiTableView.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }

    @SuppressWarnings("unchecked")
    private void selectPrevious() {
        if (thuPhiTableView.getSelectionModel().isCellSelectionEnabled()) {
            // in cell selection mode, we have to wrap around, going from
            // right-to-left, and then wrapping to the end of the previous line
            TablePosition<ThuPhiController, ?> pos = thuPhiTableView.getFocusModel()
                    .getFocusedCell();
            if (pos.getColumn() - 1 >= 0) {
                // go to previous row
                thuPhiTableView.getSelectionModel().select(pos.getRow(),
                        getTableColumn(pos.getTableColumn(), -1));
            } else if (pos.getRow() < thuPhiTableView.getItems().size()) {
                // wrap to end of previous row
                thuPhiTableView.getSelectionModel().select(pos.getRow() - 1,
                        thuPhiTableView.getVisibleLeafColumn(
                                thuPhiTableView.getVisibleLeafColumns().size() - 1));
            }
        } else {
            int focusIndex = thuPhiTableView.getFocusModel().getFocusedIndex();
            if (focusIndex == -1) {
                thuPhiTableView.getSelectionModel().select(thuPhiTableView.getItems().size() - 1);
            } else if (focusIndex > 0) {
                thuPhiTableView.getSelectionModel().select(focusIndex - 1);
            }
        }
    }

    private TableColumn<ThuPhiData, ?> getTableColumn(
            final TableColumn<ThuPhiController, ?> column, int offset) {
        int columnIndex = thuPhiTableView.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return thuPhiTableView.getVisibleLeafColumn(newColumnIndex);
    }
}


