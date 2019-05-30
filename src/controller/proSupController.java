package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import entity.*;
import entity.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        assert tvProduct != null : "fx:id=\"tvProduct\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tcProId != null : "fx:id=\"tcProId\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tcProName != null : "fx:id=\"tcProName\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert vBoxProEditPanel != null : "fx:id=\"vBoxProEditPanel\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tvSuppliers != null : "fx:id=\"tvSuppliers\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tcRelSupId != null : "fx:id=\"tcRelSupId\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tcRelSupName != null : "fx:id=\"tcRelSupName\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert vBoxProEditPanel1 != null : "fx:id=\"vBoxProEditPanel1\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert vBoxSupInfoPanel != null : "fx:id=\"vBoxSupInfoPanel\" was not injected: check your FXML file 'prodSupView.fxml'.";


        tcProId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        tcProName.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());

        tcRelSupId.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty().asObject());
        tcRelSupName.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

        LoadProducts();


    }

    private void LoadProducts() {
        ObservableList<Products> productsList = FXCollections.observableArrayList();
        try (Connection conn = DBHelper.getConnection();Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT ProductId, ProdName FROM products");
            while (rs.next())
            {
                productsList.add(
                        new Products(
                                rs.getInt(1),
                                rs.getString(2))
                );
            }
            tvProduct.setItems(productsList);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private void LoadRelatedSuppliers(int id){
        ObservableList<ProductsSuppliersViewModule> ProductsSuppliersViewModuleList = FXCollections.observableArrayList();

    }


}
