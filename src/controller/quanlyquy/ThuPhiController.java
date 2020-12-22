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
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.KhoanThuService;
import service.ThuPhiService;

import java.io.FileOutputStream;
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
        thuPhiTableView.setEditable(true);
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




    @FXML
    private void exportClicked(){
        try{
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet();
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("STT");
            header.createCell(1).setCellValue("Họ và tên chủ hộ");
            header.createCell(2).setCellValue("Số thành viên");
            header.createCell(3).setCellValue("Địa chỉ hiện nay");
            header.createCell(4).setCellValue("Số tiền đã nộp");
            header.createCell(5).setCellValue("Thời gian nộp");
            header.createCell(6).setCellValue("Kí tên");

            ObservableList<ThuPhiData> dataExcel = FXCollections.observableArrayList();
            dataExcel = khoanThuService.getThuPhi(id, soTien);
            int i = 1;
            for (ThuPhiData e: dataExcel){
                XSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(e.getStt());
                row.createCell(1).setCellValue(e.getHoTenChuHo());
                row.createCell(2).setCellValue(e.getSoThanhVien());
                row.createCell(3).setCellValue(e.getDiaChiHienNay());
                row.createCell(4).setCellValue(e.getSoTienNop());
                row.createCell(5).setCellValue(e.getThoiGianNop());
                i++;
            }
            String filename = tenKhoanThu + ".xlsx";
            FileOutputStream fileOut = new FileOutputStream(filename);
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thành công");
            alert.setHeaderText(null);
            alert.setContentText("Lưu file thành công");
            alert.showAndWait();

        } catch (Exception e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thất bại");
            alert.setHeaderText(null);
            alert.setContentText("Lưu file thất bại vui lòng thử lại");
            alert.showAndWait();

        }
    }
}


