package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import dao.ProductsDAO;
import dao.RelatedSuppliersDAO;
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

    //0 for nothing, 1 for edit, 2 for add new
    int mode;

    //Init DAOs, why use DAO: http://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm
    ProductsDAO productsDao = new ProductsDAO();
    RelatedSuppliersDAO relatedSuppliersDAO = new RelatedSuppliersDAO();

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
        tvProduct.setItems(productsDao.LoadAllProducts());


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
        //edit btn clicked, enter edit mode  1
        btnProdEdit.setOnAction(event -> {
            SetBtnPanelStatusOnItemSelected(false);
            mode=1;
            tfProdName.setText(tvProduct.getSelectionModel().getSelectedItem().getProdName());
            vBoxProEditPanel.setVisible(true);
        });

        //add btn clicked, enter add mode 2
        btnProdAdd.setOnAction(event ->{
            SetBtnPanelStatusOnItemSelected(false);
            mode=2;
            vBoxProEditPanel.setVisible(true);
        });

        //delete btn clicked, mode reset to 0
        btnProdDelete.setOnAction(event -> {
            mode=0;
            DeleteSelectProduct(tvProduct.getSelectionModel().getSelectedItem().getProductId());
        });


        //Listeners for product detail edit panel btns
        btnProdEditClear.setOnAction(event->tfProdName.clear());
        btnProdEditCancel.setOnAction(event -> SetBtnPanelStatusOnItemSelected(false));
        btnProdEditSave.setOnAction(event ->BtnProdEditSaveClickedEvent(mode));
    }

    private void DeleteSelectProduct(int productId) {

    }

    private void BtnProdEditSaveClickedEvent(int m) {
    }

    private void SetBtnPanelStatusOnItemSelected(boolean selected){
        //set mode to none
        mode = 0;

        //set Product control buttons status
        btnProdEdit.setDisable(!selected);
        btnProdDelete.setDisable(!selected);
        btnProdAdd.setDisable(false);

        //set status for prod edit panel
        vBoxProEditPanel.setVisible(false);
        tfProdName.clear();
    }

    private void LoadRelatedSuppliers(int id){
        SetBtnPanelStatusOnItemSelected(true);
        tvSuppliers.setItems(relatedSuppliersDAO.LoadAllRelatedSuppliers(id));
    }


}
