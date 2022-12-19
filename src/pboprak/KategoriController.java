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

public class KategoriController implements Initializable {

    KategoriModel ktg = new KategoriModel();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnSave;

    @FXML
    private TextField fieldKategori;

    @FXML
    private TableColumn<Kategori, String> kategoriKolom;

    @FXML
    private TableView<Kategori> viewKategori;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showKategori();
    }

    @FXML
    void HapusKategori(ActionEvent event) throws IOException {
        Kategori ktg1 = new Kategori(fieldKategori.getText());
        ktg.deleteKategori(ktg1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Kategori.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnHapus.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAwal.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void saveKategori(ActionEvent event) throws IOException {
        Kategori ktg1 = new Kategori(fieldKategori.getText());
        ktg.addKategori(ktg1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Kategori.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public ObservableList<Kategori> getListKategori(){
        ObservableList<Kategori> listKategori = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT * FROM kategori;";
        Statement st;
        ResultSet rs;

        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Kategori ktg1 = new Kategori(rs.getString("nama_kategori"));
                listKategori.add(ktg1);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return listKategori;
    }

    public void showKategori(){
        ObservableList<Kategori> list = getListKategori();
        kategoriKolom.setCellValueFactory(new PropertyValueFactory<Kategori, String>("nama_kategori"));

        viewKategori.setItems(list);
    }

}
