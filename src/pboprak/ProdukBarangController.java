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

public class ProdukBarangController implements Initializable {

    BarangModel brg = new BarangModel();

    @FXML
    private TableColumn<Barang, String> Barcodefield;

    @FXML
    private TableColumn<Barang, String> ExpiredField;

    @FXML
    private TableColumn<Barang, Double> DiskonField;

    @FXML
    private TableColumn<Barang, Double> HargaField;

    @FXML
    private TableColumn<Barang, Integer> JumlahField;

    @FXML
    private TableColumn<Barang, String> KategoriField;

    @FXML
    private TableColumn<Barang, String> NamaField;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnKembali;

    @FXML
    private Button btnSimpan;

    @FXML
    private TextField fieldBarcode;

    @FXML
    private TextField fieldDiskon;

    @FXML
    private TextField fieldExpired;

    @FXML
    private TextField fieldHarga;

    @FXML
    private TextField fieldJumlah;

    @FXML
    private TextField fieldKategori;

    @FXML
    private TextField fieldNama;

    @FXML
    private TableView<Barang> viewBarang;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showBarang();
    }

    @FXML
    void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAwal.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnKembali.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void hapusBarang(ActionEvent event) throws IOException {
        Barang brg1 = new Barang(fieldBarcode.getText());
        brg.deleteBarang(brg1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProdukBarang.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnHapus.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void simpanBarang(ActionEvent event) throws IOException {
        Barang brg1 = new Barang(fieldNama.getText(), Double.parseDouble(fieldHarga.getText()),
                                Double.parseDouble(fieldDiskon.getText()), Integer.parseInt(fieldJumlah.getText()),
                                fieldBarcode.getText(), fieldExpired.getText(), fieldKategori.getText());
        brg.addBarang(brg1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProdukBarang.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnSimpan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public ObservableList<Barang> getListBarang(){
        ObservableList<Barang> listBarang = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT * FROM barang;";
        Statement st;
        ResultSet rs;

        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Barang brg1 = new Barang(rs.getString("nama_produk"), rs.getDouble("harga"), 
                                        rs.getDouble("diskon"), rs.getInt("jumlah"), 
                                        rs.getString("barcode"), rs.getString("expired"),
                                        rs.getString("kategori"));
                listBarang.add(brg1);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return listBarang;
    }

    public void showBarang(){
        ObservableList<Barang> list = getListBarang();
        NamaField.setCellValueFactory(new PropertyValueFactory<Barang, String>("nama_produk"));
        HargaField.setCellValueFactory(new PropertyValueFactory<Barang, Double>("harga"));
        DiskonField.setCellValueFactory(new PropertyValueFactory<Barang, Double>("diskon"));
        JumlahField.setCellValueFactory(new PropertyValueFactory<Barang, Integer>("jumlah"));
        Barcodefield.setCellValueFactory(new PropertyValueFactory<Barang, String>("barcode"));
        ExpiredField.setCellValueFactory(new PropertyValueFactory<Barang, String>("expired"));
        KategoriField.setCellValueFactory(new PropertyValueFactory<Barang, String>("category"));

        viewBarang.setItems(list);
    }
}
