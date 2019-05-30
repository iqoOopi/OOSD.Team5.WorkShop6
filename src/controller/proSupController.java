package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Products;
import entity.ProductsSuppliersViewModule;
import entity.Suppliers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class proSupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Products> tvProduct;

    @FXML
    private TableColumn<Products, Integer> tcProId;

    @FXML
    private TableColumn<Products, String> tcProName;

    @FXML
    private VBox vBoxProEditPanel;

    @FXML
    private TableView<ProductsSuppliersViewModule> tvSuppliers;

    @FXML
    private TableColumn<ProductsSuppliersViewModule, Integer> tcRelSupId;

    @FXML
    private TableColumn<ProductsSuppliersViewModule, String> tcRelSupName;

    @FXML
    private VBox vBoxProEditPanel1;

    @FXML
    private Label vBoxSupInfoPanel;

    @FXML
    void initialize() {
        assert tvProduct != null : "fx:id=\"tvProduct\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert tcProId != null : "fx:id=\"tcProId\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert tcProName != null : "fx:id=\"tcProName\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert vBoxProEditPanel != null : "fx:id=\"vBoxProEditPanel\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert tvSuppliers != null : "fx:id=\"tvSuppliers\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert tcRelSupId != null : "fx:id=\"tcRelSupId\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert tcRelSupName != null : "fx:id=\"tcRelSupName\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert vBoxProEditPanel1 != null : "fx:id=\"vBoxProEditPanel1\" was not injected: check your FXML file 'products_supplier_view.fxml'.";
        assert vBoxSupInfoPanel != null : "fx:id=\"vBoxSupInfoPanel\" was not injected: check your FXML file 'products_supplier_view.fxml'.";


        tcProId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        tcProName.setCellValueFactory(cellData->cellData.getValue().prodNameProperty());

        tcRelSupId.setCellValueFactory(cellData->cellData.getValue().supplierIdProperty().asObject());
        tcRelSupName.setCellValueFactory(cellData->cellData.getValue().supNameProperty());


    }
}
