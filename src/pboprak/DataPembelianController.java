package pboprak;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import static db.DBHelper.getConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DataPembelianController implements Initializable {

    PenjualanModel pjl = new PenjualanModel();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnSimpan;

    @FXML
    private TextField fieldBarcode;

    @FXML
    private TextField fieldJumlah;

    @FXML
    private TextField fieldNama;

    @FXML
    private TableColumn<Penjualan, String> viewBarcode;

    @FXML
    private TableView<Penjualan> viewData;

    @FXML
    private TableColumn<Penjualan, Integer> viewJumlah;

    @FXML
    private TableColumn<Penjualan, String> viewNama;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showPenjualan();
    }

    @FXML
    void HapusData(ActionEvent event) throws IOException {
        Penjualan pjl1 = new Penjualan(fieldBarcode.getText());
        pjl.deletePenjualan(pjl1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPembelian.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnHapus.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAwal.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void simpanData(ActionEvent event) throws IOException {
        Penjualan pjl1 = new Penjualan(Integer.parseInt(fieldJumlah.getText()), fieldNama.getText(),
                                        fieldBarcode.getText());
        pjl.addPenjualan(pjl1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPembelian.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnSimpan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public ObservableList<Penjualan> getListPenjualan(){
        ObservableList<Penjualan> listPenjualan = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT * FROM data_pembelian;";
        Statement st;
        ResultSet rs;

        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Penjualan pjl1 = new Penjualan(rs.getInt("jumlah"), rs.getString("nama"),
                                                rs.getString("kode"));
                listPenjualan.add(pjl1);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return listPenjualan;
    }

    public void showPenjualan(){
        ObservableList<Penjualan> list = getListPenjualan();
        viewBarcode.setCellValueFactory(new PropertyValueFactory<Penjualan, String>("idBarcode"));
        viewNama.setCellValueFactory(new PropertyValueFactory<Penjualan, String>("namaProduk"));
        viewJumlah.setCellValueFactory(new PropertyValueFactory<Penjualan, Integer>("jumlahProduk"));

        viewData.setItems(list);
    }

}
