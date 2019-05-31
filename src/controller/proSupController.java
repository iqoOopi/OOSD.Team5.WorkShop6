package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import entity.*;
import entity.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TextField tfProdName;

    @FXML
    private Button btnProdEdit;

    @FXML
    private Button btnProdDelete;

    @FXML
    private Button btnProdAdd;


    @FXML
    private Button btnProdEditSave;

    @FXML
    private Button btnProdEditClear;

    @FXML
    private Button btnProdEditCancel;

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
        assert tfProdName != null : "fx:id=\"tfProdName\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnProdEdit != null : "fx:id=\"btnProdEdit\" was not injected: check your FXML file 'prodSupView.fxml'.";

        //**********************************************************************
        //product panel side
        tcProId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        tcProName.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());

        //populate products table
        LoadProducts();

        //set initial status for all the products side controls
        SetBtnPanelStatusOnItemSelected(false);

        //set listener for ProdPanelBtns
        setProdPanelBtnListener();

        //**********************************************************************
        //related supplier side

        tcRelSupId.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty().asObject());
        tcRelSupName.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

        //load related supplier table when one product get selected
        tvProduct.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> LoadRelatedSuppliers(newValue.getProductId()));
    }

    private void setProdPanelBtnListener() {
        btnProdEdit.setOnAction(event -> {
            tfProdName.setText(tvProduct.getSelectionModel().getSelectedItem().getProdName());
            vBoxProEditPanel.setVisible(true);
        });
        btnProdEditCancel.setOnAction(event -> SetBtnPanelStatusOnItemSelected(false));
        //missing save btn function
    }

    private void SetBtnPanelStatusOnItemSelected(boolean selected){
        //set Product control buttons status
        btnProdEdit.setDisable(!selected);
        btnProdDelete.setDisable(!selected);
        btnProdAdd.setDisable(false);

        //set status for prod edit panel
        vBoxProEditPanel.setVisible(false);
        tfProdName.clear();
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
        SetBtnPanelStatusOnItemSelected(true);

        ObservableList<ProductsSuppliersViewModule> ProductsSuppliersViewModuleList = FXCollections.observableArrayList();
        try (Connection conn = DBHelper.getConnection();Statement stmt = conn.createStatement()) {
            String sql = "SELECT ps.SupplierId, s.SupName " +
                    "FROM Products_Suppliers ps JOIN Suppliers s ON " +
                    "ps.SupplierId = s.SupplierId" +
                    " WHERE ProductId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                ProductsSuppliersViewModuleList.add(
                        new ProductsSuppliersViewModule(
                                id,//product Id
                                rs.getInt(1),//supplier id
                                rs.getString(2))//supplier name
                );
            }
            tvSuppliers.setItems(ProductsSuppliersViewModuleList);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }


}
