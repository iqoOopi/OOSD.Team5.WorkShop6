import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class proSupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> tvProduct;

    @FXML
    private VBox vBoxProEditPanel;

    @FXML
    private TableView<?> tvSuppliers;

    @FXML
    private VBox vBoxProEditPanel1;

    @FXML
    private Label vBoxSupInfoPanel;

    @FXML
    void initialize() {
        assert tvProduct != null : "fx:id=\"tvProduct\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert vBoxProEditPanel != null : "fx:id=\"vBoxProEditPanel\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert tvSuppliers != null : "fx:id=\"tvSuppliers\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert vBoxProEditPanel1 != null : "fx:id=\"vBoxProEditPanel1\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert vBoxSupInfoPanel != null : "fx:id=\"vBoxSupInfoPanel\" was not injected: check your FXML file 'products_supplier_view.fxml'.";

    }
}
