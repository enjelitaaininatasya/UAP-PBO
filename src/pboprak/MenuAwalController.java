package pboprak;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuAwalController implements Initializable{

    @FXML
    private Button btnBarang;

    @FXML
    private Button btnData;

    @FXML
    private Button btnKategori;

    @FXML
    private Button btnMakanan;

    @FXML
    private ImageView viewGambar;

    @FXML
    void goBarang(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProdukBarang.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnBarang.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPembelian.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnData.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goKategori(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Kategori.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnKategori.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void goMakanan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProdukMakanan.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) btnMakanan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        try (InputStream stream = new FileInputStream("C:\\Users\\HP\\OneDrive\\Pictures\\Saved Pictures\\kasir.png")) {
            Image image = new Image(stream);  
            viewGambar.setImage(image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


