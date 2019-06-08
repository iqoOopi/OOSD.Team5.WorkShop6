/*
 ********************************************************************************************
 * Course: CMPP 264 Java Programming for OOSD
 * Purpose: Day 6 Assignment
 * Date: May 16, 2019.
 * Author: Tim Leslie
 * Description: This DBHelper class contains a static method which establishes a connection
 * to the TravelExperts database.
 *******************************************************************************************/
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.DBHelper;
import dao.PackagesDAO;
import entity.*;
import entity.Package;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PackageController {

    private static Stage stageTest;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML // fx:id="mnuBar"
    private MenuBar mnuBar; // Value injected by FXMLLoader

    @FXML // fx:id="mnuFile"
    private Menu mnuFile; // Value injected by FXMLLoader

    @FXML // fx:id="mnuMain"
    private MenuItem mnuMain; // Value injected by FXMLLoader

    @FXML
    private MenuItem mnuProductManagement;


    @FXML // fx:id="mnuExit"
    private MenuItem mnuExit; // Value injected by FXMLLoader

    @FXML // fx:id="mnuEdit"
    private Menu mnuEdit; // Value injected by FXMLLoader

    @FXML // fx:id="mnuSave"
    private MenuItem mnuSave; // Value injected by FXMLLoader

    @FXML // fx:id="mnuDelete"
    private MenuItem mnuDelete; // Value injected by FXMLLoader

    @FXML // fx:id="mnuUpdate"
    private MenuItem mnuUpdate; // Value injected by FXMLLoader


    @FXML
    private TableView<Package> tvPackages;


    @FXML
    private TableColumn<Package, Integer> colPackageId;

    @FXML
    private TableColumn<Package, String> colPkgName;

    @FXML
    private TableColumn<Package, LocalDate> colStartDate;

    @FXML
    private TableColumn<Package, LocalDate> colEndDate;

    @FXML
    private TableColumn<Package, String> colDescription;

    @FXML
    private TableColumn<Package, String> colBasePrice;

    @FXML
    private TableColumn<Package, String> colCommission;

    @FXML
    private Button btnAddPackage;

    @FXML
    private Button btnUpdatePackage;

    @FXML
    private Button btnSavePackage;

    @FXML
    private Button btnDeleteProductSupplier;


    @FXML
    private Button btnMainScene;

    @FXML
    private GridPane gpPackages;

    @FXML
    private TextField tfPackageId;

    @FXML
    private TextField tfPackageName;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private Button btnAddProductSupplier;

    @FXML
    private Button btnEditProductSupplier;

    @FXML
    private Button btnDeletePackage;


    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfBasePrice;

    @FXML
    private TextField tfAgencyCommission;

    @FXML
    private TableView<PackageProductSupplierList> tvProductSupplier;


    @FXML
    private TableColumn<PackageProductSupplierList, Integer> colId;

    @FXML
    private TableColumn<PackageProductSupplierList, String> colProduct;

    @FXML
    private TableColumn<PackageProductSupplierList, String> colSupplier;

    @FXML
    private TableView<PackageProductSupplierList> tvProductSupplierAvailable;

    @FXML
    private TableColumn<PackageProductSupplierList, Integer> colId1;

    @FXML
    private TableColumn<PackageProductSupplierList, String> colProduct1;

    @FXML
    private TableColumn<PackageProductSupplierList, String> colSupplier1;

    // initialize PackagesDAO object in order to be able to call data access methods in the class
    PackagesDAO packagesDAO = new PackagesDAO();

    @FXML
    void initialize() {
        assert colPackageId != null : "fx:id=\"colPackageId\" was not injected: check your FXML file 'package.fxml'.";
        assert colPkgName != null : "fx:id=\"colPackageName\" was not injected: check your FXML file 'package.fxml'.";
        assert colStartDate != null : "fx:id=\"colStartDate\" was not injected: check your FXML file 'package.fxml'.";
        assert colEndDate != null : "fx:id=\"colEndDate\" was not injected: check your FXML file 'package.fxml'.";
        assert colDescription != null : "fx:id=\"colDescription\" was not injected: check your FXML file 'package.fxml'.";
        assert colBasePrice != null : "fx:id=\"colBasePrice\" was not injected: check your FXML file 'package.fxml'.";
        assert colCommission != null : "fx:id=\"colCommission\" was not injected: check your FXML file 'package.fxml'.";


        // set up table column cell factories for tvPackages
        colPackageId.setCellValueFactory(cellData -> cellData.getValue().packageIdProperty().asObject());
        colPkgName.setCellValueFactory(cellData -> cellData.getValue().pkgNameProperty());
        colStartDate.setCellValueFactory(cellData -> cellData.getValue().pkgStartDateProperty());
        colEndDate.setCellValueFactory(cellData -> cellData.getValue().pkgEndDateProperty());
        colDescription.setCellValueFactory(cellData -> cellData.getValue().pkgDescProperty());
        colBasePrice.setCellValueFactory(cellData -> Bindings.format("%.2f", cellData.getValue().pkgBasePriceProperty()));
        colCommission.setCellValueFactory(cellData -> Bindings.format("%.2f", cellData.getValue().pkgAgencyCommissionProperty()));

        // set up table column cell factories for tvProductSupplier
        colId.setCellValueFactory(cellData -> cellData.getValue().packageIdProperty().asObject());
        colProduct.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());
        colSupplier.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

        // set up table column cell factories for tvProductSupplierAvailable
        colId1.setCellValueFactory(cellData -> cellData.getValue().packageIdProperty().asObject());
        colProduct1.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());
        colSupplier1.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());


        loadPackages();

        // disable package fields
        disablePackageFields();


        // initialize buttons
        // disable Save button btnSavePackage
        btnSavePackage.setDisable(true);
        btnAddProductSupplier.setDisable(true);

        // listen for changes in the tableview and show package details when changed
        tvPackages.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPackageDetails(newValue));



        mnuExit.setOnAction(e ->
        {
            System.exit(0);
        });

        mnuMain.setOnAction(e ->
        {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
                stageTest.setTitle("Main Scene");
                Scene packageScene = new Scene(root, 1200, 700);

                stageTest.setScene(packageScene);
                stageTest.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        tvProductSupplierAvailable.setOnMouseClicked(e->
        {
            btnAddProductSupplier.setDisable(false);
        });

        mnuProductManagement.setOnAction(e ->
        {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../view/prodSupView.fxml"));
                stageTest.setTitle("Main Scene");
                Scene prodSupScene = new Scene(root, 1200, 700);

                stageTest.setScene(prodSupScene);
                stageTest.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });


        btnAddPackage.setOnAction(e ->
        {
            clearPackageFields();

            // disable Add button and Update button
            btnAddPackage.setDisable(true);
            btnUpdatePackage.setDisable(true);

            // enable Save button
            btnSavePackage.setDisable(false);

            // enable package fields
            enablePackageFields();
            // disable PackageId field
            tfPackageId.setDisable(true);

        });

        btnDeletePackage.setOnAction(e->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you want to delete this package?");
            alert.showAndWait();

            packagesDAO.deletePackage(tvPackages.getSelectionModel().getSelectedItem());

            loadPackages();
        });

        btnAddProductSupplier.setOnAction (e->
        {
            Package pkg = tvPackages.getSelectionModel().getSelectedItem();
            PackageProductSupplierList pPS = tvProductSupplierAvailable.getSelectionModel().getSelectedItem();
            packagesDAO.addProductSupplier(pkg, pPS);

            updateAvailableDisplay(pkg);
            updatePackageDisplay(pkg);
            updatePackagePSTable(pkg);
        });

        btnDeleteProductSupplier.setOnAction(e->
        {
            Package pkg = tvPackages.getSelectionModel().getSelectedItem();
            PackageProductSupplierList pPS = tvProductSupplier.getSelectionModel().getSelectedItem();
            packagesDAO.deleteProductSupplier(pkg, pPS);

            updateAvailableDisplay(pkg);
            updatePackageDisplay(pkg);
            updatePackagePSTable(pkg);
        });


        btnSavePackage.setOnAction(e->
        {
            // validate Package text fields
            // validation code
            Validation v = new Validation();
            String errorMsg = "";

            //errorMsg += v.isProvided(tfPackageId.getText(), "Package Id");
            errorMsg += v.isProvided(tfPackageName.getText(), "Package Name");
            errorMsg += v.isProvided(dpStartDate.getValue().toString(), "Package Start Date");
            errorMsg += v.isProvided(dpEndDate.getValue().toString(), "Package End Date");
            errorMsg += v.isProvided(tfDescription.getText(), "Package Description");
            errorMsg += v.isProvided(tfBasePrice.getText(), "Package Base Price");
            errorMsg += v.isProvided(tfAgencyCommission.getText(), "Package Agency Commission");

            if (!errorMsg.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Invalid information");
                alert.setContentText(errorMsg);
                alert.showAndWait();
                return;
            }


            packagesDAO.savePackage(new Package(1, tfPackageName.getText(), dpStartDate.getValue(),
                    dpEndDate.getValue(), tfDescription.getText(), Double.parseDouble(tfBasePrice.getText()),
                    Double.parseDouble(tfAgencyCommission.getText())));
            // reload table
            loadPackages();
            // reset button
            btnSavePackage.setDisable(true);
            btnUpdatePackage.setDisable(false);
            btnAddPackage.setDisable(false);

            disablePackageFields();
        });

    }

    private void disablePackageFields() {
        tfPackageId.setDisable(true);
        tfPackageName.setDisable(true);
        dpStartDate.setDisable(true);
        dpEndDate.setDisable(true);
        tfDescription.setDisable(true);
        tfBasePrice.setDisable(true);
        tfAgencyCommission.setDisable(true);
    }

    private void enablePackageFields() {
        tfPackageId.setDisable(false);
        tfPackageName.setDisable(false);
        dpStartDate.setDisable(false);
        dpEndDate.setDisable(false);
        tfDescription.setDisable(false);
        tfBasePrice.setDisable(false);
        tfAgencyCommission.setDisable(false);
    }


    private void clearPackageFields() {
        tfPackageId.setText("");
        tfPackageName.setText("");
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);
        tfDescription.setText("");
        tfBasePrice.setText("");
        tfAgencyCommission.setText("");
    }

    private void loadPackages() {

        ObservableList<Package> packageList = packagesDAO.getAllPackages();
        tvPackages.setItems(packageList);
    }

    // method to populate text fields with information corresponding
    // to the package passed as an argument.
    private void showPackageDetails(Package pkg) {
        System.out.println("Starting showPackageDetails");

        // reset buttons to Edit enabled and Save disabled
        // this is necessary in case the user changes the Agent selection before saving an
        // existing Agent change
        //btnSave.setDisable(true);
        //btnEdit.setDisable(false);
        updatePackageDisplay(pkg);

        updatePackagePSTable(pkg);

        updateAvailableDisplay(pkg);


        // after the package information has been displayed make sure the fields are not editable
        // setPackageUnEditable();
    }

    private void updateAvailableDisplay(Package pkg) {

        ObservableList<PackageProductSupplierList> packagePSList = packagesDAO.getAvailableProductsSuppliers(pkg);

        tvProductSupplierAvailable.setItems(packagePSList);

        // not working presently
        if (tvProductSupplierAvailable.getSelectionModel().isEmpty()) {
            btnAddProductSupplier.setDisable(true);
        }
        else
            btnAddProductSupplier.setDisable(false);

    }

    private void updatePackagePSTable(Package pkg) {
        System.out.println("Starting updatePackagePSTable");
        System.out.println(pkg);

        ObservableList<PackageProductSupplierList> packagePSList = packagesDAO.getRelatedProductsSuppliers(pkg);

        tvProductSupplier.setItems(packagePSList);
    }

    private void updatePackageDisplay(Package pkg) {
        System.out.println("Starting updatePackageText");

        if (pkg != null) { // if the agent object reference is not null then use agent information
            tfPackageId.setText(Integer.toString(pkg.getPackageId()));
            tfPackageName.setText(pkg.getPkgName());
            dpStartDate.setValue(pkg.getPkgStartDate());
            dpEndDate.setValue(pkg.getPkgEndDate());
            tfDescription.setText(pkg.getPkgDesc());
            tfBasePrice.setText(Double.toString(pkg.getPkgBasePrice()));
            tfAgencyCommission.setText(Double.toString(pkg.getPkgAgencyCommission()));
        }
        else { // if the agent reference is null then populate text fields with empty strings
            tfPackageId.setText("");
            tfPackageName.setText("");
            dpStartDate.setValue(null);
            dpEndDate.setValue(null);
            tfDescription.setText("");
            tfBasePrice.setText("");
            tfAgencyCommission.setText("");
        }
    }

    void savePackages(ActionEvent event) {

        int cmbIndex = tvPackages.getSelectionModel().getSelectedIndex();

        // validation code
        Validation v = new Validation();
        String errorMsg = "";

        errorMsg += v.isProvided(tfPackageId.getText(), "Package Id");
        errorMsg += v.isProvided(tfPackageName.getText(), "Package Name");
        errorMsg += v.isProvided(dpStartDate.getValue().toString(), "Package Start Date");
        errorMsg += v.isProvided(dpEndDate.getValue().toString(), "Package End Date");
        errorMsg += v.isProvided(tfDescription.getText(), "Package Description");
        errorMsg += v.isProvided(tfBasePrice.getText(), "Package Base Price");
        errorMsg += v.isInteger(tfAgencyCommission.getText(), "Package Agency Commission");

        if (!errorMsg.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid information");
            alert.setContentText(errorMsg);
            alert.showAndWait();
            return;
        }


/*
        Connection conn = DBHelper.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE `packages` SET `PackageId`=?," +
                    "`PkgName`=?,`AgtLastName`=?,`AgtBusPhone`=?,`AgtEmail`=?,`AgtPosition`=?," +
                    "`AgencyId`=?  WHERE AgentId=?");
            stmt.setInt(1, Integer.parseInt(tfPackageId.getText()));
            stmt.setString(2, tfPackageName.getText());
            stmt.setDate(3, Date.valueOf(dpStartDate.getValue()));
            stmt.setDate(4, Date.valueOf(dpEndDate.getValue()));
            stmt.setString(5, tfDescription.getText());
            stmt.setDouble(6, Double.parseDouble(tfBasePrice.getText()));
            stmt.setDouble(7, Double.parseDouble(tfAgencyCommission.getText()));
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error updating the database", ButtonType.OK);
                alert.showAndWait();
            }
            conn.close();
*/
            loadPackages();
            tvPackages.getSelectionModel().select(cmbIndex);
            showPackageDetails(tvPackages.getSelectionModel().getSelectedItem());

            // set buttons
            //btnSave.setDisable(true);
            //btnEdit.setDisable(false);

            // disable text fields
            //setAgentUnEditable();

/*
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }



    public static void passStage(Stage stage) {
        stageTest = stage;

    }

}
