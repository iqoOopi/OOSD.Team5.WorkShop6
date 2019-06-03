package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ProductsDAO;
import dao.RelatedSuppliersDAO;
import entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class proSupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Product> tvProduct;

    @FXML
    private TableColumn<Product, Integer> tcProId;

    @FXML
    private TableColumn<Product, String> tcProName;

    @FXML
    private VBox vBoxProEditPanel;

    @FXML
    private TableView<ProductsSuppliersViewModule> tvSuppliers;

    @FXML
    private TableColumn<ProductsSuppliersViewModule, Integer> tcRelSupId;

    @FXML
    private TableColumn<ProductsSuppliersViewModule, String> tcRelSupName;

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
    private Button btnSupDelete;

    @FXML
    private Button btnSupAdd;

    @FXML
    private VBox vBoxSupEditPanel;

    @FXML
    private ComboBox<Suppliers> cbSupName;

    @FXML
    private Button btnSupSave;

    @FXML
    private Button btnSupCancel;

    //By Henry

    //0 for nothing, 1 for edit, 2 for add new
    int mode;
    Product selectedProd;
    ProductsSuppliersViewModule selectedRelatedSupplier;

    //Init DAOs, why use DAO: http://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm
    ProductsDAO productsDao = new ProductsDAO();
    RelatedSuppliersDAO relatedSuppliersDAO = new RelatedSuppliersDAO();

    @FXML
    void initialize() {
        assert tvProduct != null : "fx:id=\"tvProduct\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tcProId != null : "fx:id=\"tcProId\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tcProName != null : "fx:id=\"tcProName\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnProdEdit != null : "fx:id=\"btnProdEdit\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnProdDelete != null : "fx:id=\"btnProdDelete\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnProdAdd != null : "fx:id=\"btnProdAdd\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert vBoxProEditPanel != null : "fx:id=\"vBoxProEditPanel\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tfProdName != null : "fx:id=\"tfProdName\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnProdEditSave != null : "fx:id=\"btnProdEditSave\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnProdEditClear != null : "fx:id=\"btnProdEditClear\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnProdEditCancel != null : "fx:id=\"btnProdEditCancel\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tvSuppliers != null : "fx:id=\"tvSuppliers\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tcRelSupId != null : "fx:id=\"tcRelSupId\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert tcRelSupName != null : "fx:id=\"tcRelSupName\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnSupDelete != null : "fx:id=\"btnSupDelete\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnSupAdd != null : "fx:id=\"btnSupAdd\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert vBoxSupEditPanel != null : "fx:id=\"vBoxSupEditPanel\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert cbSupName != null : "fx:id=\"cbSupName\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnSupSave != null : "fx:id=\"btnSupSave\" was not injected: check your FXML file 'prodSupView.fxml'.";
        assert btnSupCancel != null : "fx:id=\"btnSupCancel\" was not injected: check your FXML file 'prodSupView.fxml'.";

        //**********************************************************************
        //product panel side
        tcProId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        tcProName.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());

        //populate products table
        LoadTVProduct();

        //set initial status for all the products side controls
        SetProdBtnPanelStatusOnItemSelected(false);

        //set listener to Product Table and load related supplier table when one product get selected
        AddListenerToProdSelected();

        //set listener for ProdPanelBtns
        setClickActionForProdSideBtns();

        //**********************************************************************
        //related supplier side

        //set inital stauts of Sup related btns and panel
        SetRelSupBtnPanelStatusOnItemSelected(false);

        tcRelSupId.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty().asObject());
        tcRelSupName.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());



        //set listener to RelatedSupplier Table and enable delete btn
        AddListenerToSupSelected();

        //set Listeners for Sup side btns
        setClickActionForSupSideBtns();
    }

    //Products Side ***********************
    private void LoadTVProduct() {
        tvProduct.setItems(productsDao.LoadAllProducts());
    }

    private void setClickActionForProdSideBtns() {
        //edit btn clicked, enter edit mode  1
        btnProdEdit.setOnAction(event -> {
            SetProdBtnPanelStatusOnItemSelected(false);
            mode = 1;
            tfProdName.setText(selectedProd.getProdName());
            vBoxProEditPanel.setVisible(true);
        });

        //add btn clicked, enter add mode 2
        btnProdAdd.setOnAction(event ->{
            SetProdBtnPanelStatusOnItemSelected(false);
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
        btnProdEditCancel.setOnAction(event -> SetProdBtnPanelStatusOnItemSelected(false));
        btnProdEditSave.setOnAction(event -> InsertOrUpdateProd(mode));
    }

    private void AddListenerToProdSelected() {
        tvProduct.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue!=null){
                        selectedProd = newValue;
                        SetProdBtnPanelStatusOnItemSelected(true);
                        LoadRelatedSuppliers(newValue.getProductId());
                    }
                    SetRelSupBtnPanelStatusOnItemSelected(false);
                });
    }

    private void DeleteSelectProduct(int productId) {
        productsDao.DeleteProductById(productId);
        LoadTVProduct();

    }

    private void InsertOrUpdateProd(int mode) {
        switch (mode) {
            case 1:
                //1 for edit
                Product editedProd = new Product(selectedProd.getProductId(),tfProdName.getText());
                productsDao.UpdateProduct(editedProd);
                break;
            case 2:
                //2 for add new
                Product newProd=new Product(tfProdName.getText());
                productsDao.SaveNewProduct(newProd);
                break;
            default:
                //do nothing
        }
        LoadTVProduct();
    }

    private void SetProdBtnPanelStatusOnItemSelected(boolean selected){
        //set mode to none
        mode = 0;
//        //set selectedProd to null
//        selectedProd=null;

        //set Product control buttons status
        btnProdEdit.setDisable(!selected);
        btnProdDelete.setDisable(!selected);
        btnProdAdd.setDisable(false);

        //set status for prod edit panel
        vBoxProEditPanel.setVisible(false);
        tfProdName.clear();
    }

    //Related Supplier Side ***********************

    private void LoadRelatedSuppliers(int id){
        tvSuppliers.setItems(relatedSuppliersDAO.LoadAllRelatedSuppliers(id));
    }

    private void SetRelSupBtnPanelStatusOnItemSelected(boolean selected){
        btnSupDelete.setDisable(!selected);
        vBoxSupEditPanel.setVisible(false);
        tvSuppliers.getSelectionModel().clearSelection();

    }

    private void AddListenerToSupSelected() {
        tvSuppliers.getSelectionModel().selectedItemProperty().addListener(observable -> btnSupDelete.setDisable(false));
    }

    private void setClickActionForSupSideBtns() {
        btnSupDelete.setOnAction(event ->relatedSuppliersDAO.DeleteById(tvSuppliers.getSelectionModel().getSelectedItem().getProductSupplierId()));
        btnSupAdd.setOnAction(event -> {
            SetRelSupBtnPanelStatusOnItemSelected(false);
            vBoxSupEditPanel.setVisible(true);
            loadComboBoxSup(selectedProd.getProductId());
        });
        btnSupCancel.setOnAction(event -> SetRelSupBtnPanelStatusOnItemSelected(false));
    }

    private void loadComboBoxSup(int productId) {
        cbSupName.setItems(relatedSuppliersDAO.LoadNonSelectedSuppliers(productId));


    }


}
