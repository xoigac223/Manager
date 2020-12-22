package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import org.apache.poi.ss.formula.functions.T;
import service.ThuPhiService;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class HomeController implements Initializable {

    @FXML
    private Label lbSoNhanKhau;

    @FXML
    private Label lbSoHoKhau;

    @FXML
    private Label lbTamTru;

    @FXML
    private Label lbTamVang;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setBarChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setBarChart() throws SQLException {

        XYChart.Series dataSeries = new XYChart.Series();

        ThuPhiService thuPhiService = new ThuPhiService();
        HashMap data = thuPhiService.getTop3();

        Set set= data.entrySet();//Converting to Set so that we can traverse
        Iterator itr=set.iterator();
        while(itr.hasNext()){
            Map.Entry entry=(Map.Entry)itr.next();
            dataSeries.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }

        barChart.getData().addAll(dataSeries);

    }
}
