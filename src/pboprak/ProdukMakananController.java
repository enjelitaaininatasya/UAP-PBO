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

public class ProdukMakananController implements Initializable {

    MakananModel mkn = new MakananModel();

    @FXML
    private TableColumn<Makanan, Integer> DayaKolom;

    @FXML
    private TableColumn<Makanan, Double> DiskonKolom;

    @FXML
    private TableColumn<Makanan, Double> HargaKolom;

    @FXML
    private TableColumn<Makanan, Integer> JumlahKolom;

    @FXML
    private TableColumn<Makanan, String> NamaKolom;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnSimpan;

    @FXML
    private TextField fieldDaya;

    @FXML
    private TextField fieldDiskon;

    @FXML
    private TextField fieldHarga;

    @FXML
    private TextField fieldId;

    @FXML
    private TextField fieldJumlah;

    @FXML
    private TextField fieldNama;

    @FXML
    private TableColumn<Makanan, Integer> idKolom;

    @FXML
    private TableView<Makanan> viewMakanan;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showMakanan();
    }

    @FXML
    void HapusMakanan(ActionEvent event) throws IOException {
        Makanan mkn1 = new Makanan(Integer.parseInt(fieldId.getText()));
        mkn.deleteMakanan(mkn1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProdukMakanan.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnHapus.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void KembaliMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAwal.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void SimpanMakanan(ActionEvent event) throws IOException {
        Makanan mkn1 = new Makanan(fieldNama.getText(), Double.parseDouble(fieldHarga.getText()),
                                    Double.parseDouble(fieldDiskon.getText()), Integer.parseInt(fieldJumlah.getText()),
                                    Integer.parseInt(fieldId.getText()), Integer.parseInt(fieldDaya.getText()));
        mkn.addMakanan(mkn1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProdukMakanan.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnSimpan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public ObservableList<Makanan> getListMakanan(){
        ObservableList<Makanan> listMakanan = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT * FROM makanan;";
        Statement st;
        ResultSet rs;

        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Makanan mkn = new Makanan(rs.getString("nama_produk"), rs.getDouble("harga"),
                                            rs.getDouble("diskon"), rs.getInt("jumlah"),
                                            rs.getInt("id"), rs.getInt("daya_tahan"));
                listMakanan.add(mkn);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return listMakanan;
    }

    public void showMakanan(){
        ObservableList<Makanan> list = getListMakanan();
        NamaKolom.setCellValueFactory(new PropertyValueFactory<Makanan, String>("nama_produk"));
        HargaKolom.setCellValueFactory(new PropertyValueFactory<Makanan, Double>("harga"));
        DiskonKolom.setCellValueFactory(new PropertyValueFactory<Makanan, Double>("diskon"));
        JumlahKolom.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("jumlah"));
        idKolom.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("id"));
        DayaKolom.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("daya_tahan"));

        viewMakanan.setItems(list);
    }

}
