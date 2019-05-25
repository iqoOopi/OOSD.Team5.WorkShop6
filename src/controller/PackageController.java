/******************************************************************************************
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.EventListener;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import entity.Package;
import entity.DBHelper;
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
    private TableColumn<Package, Double> colBasePrice;

    @FXML
    private TableColumn<Package, Double> colCommission;

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

/*

    @FXML
    private TextField tfPackageStartDate;

    @FXML
    private TextField tfPackageEndDate;
*/

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfBasePrice;

    @FXML
    private TextField tfAgencyCommission;



/*    @FXML
    void goToMainScene(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
        stageTest.setTitle("Main Scene");
        Scene packageScene = new Scene(root, 800, 600);

        stageTest.setScene(packageScene);
        stageTest.show();

    }*/


    @FXML
    void initialize() {
        assert colPackageId != null : "fx:id=\"colPackageId\" was not injected: check your FXML file 'package.fxml'.";
        assert colPkgName != null : "fx:id=\"colPackageName\" was not injected: check your FXML file 'package.fxml'.";
        assert colStartDate != null : "fx:id=\"colStartDate\" was not injected: check your FXML file 'package.fxml'.";
        assert colEndDate != null : "fx:id=\"colEndDate\" was not injected: check your FXML file 'package.fxml'.";
        assert colDescription != null : "fx:id=\"colDescription\" was not injected: check your FXML file 'package.fxml'.";
        assert colBasePrice != null : "fx:id=\"colBasePrice\" was not injected: check your FXML file 'package.fxml'.";
        assert colCommission != null : "fx:id=\"colCommission\" was not injected: check your FXML file 'package.fxml'.";

        colPackageId.setCellValueFactory(cellData -> cellData.getValue().packageIdProperty().asObject());
        colPkgName.setCellValueFactory(cellData -> cellData.getValue().pkgNameProperty());
        colStartDate.setCellValueFactory(cellData -> cellData.getValue().pkgStartDateProperty());
        colEndDate.setCellValueFactory(cellData -> cellData.getValue().pkgEndDateProperty());
        colDescription.setCellValueFactory(cellData -> cellData.getValue().pkgDescProperty());
        colBasePrice.setCellValueFactory(cellData -> cellData.getValue().pkgBasePriceProperty().asObject());
        colCommission.setCellValueFactory(cellData -> cellData.getValue().pkgAgencyCommissionProperty().asObject());

        loadPackages();

        // listen for changes in combobox and show package details when changed
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
                Scene packageScene = new Scene(root, 800, 600);

                stageTest.setScene(packageScene);
                stageTest.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

    }


    private void loadPackages() {
        ObservableList<Package> data = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM packages");
            while (rs.next())
            {
                data.add(new Package(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getDate(4).toLocalDate(),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7)
                ));
            }
            conn.close();
            tvPackages.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // method to populate text fields with information corresponding
    // to the agent passed as an argument.
    private void showPackageDetails(Package pkg) {

        // reset buttons to Edit enabled and Save disabled
        // this is necessary in case the user changes the Agent selection before saving an
        // existing Agent change
        //btnSave.setDisable(true);
        //btnEdit.setDisable(false);


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

        // after the agent information has been displayed make sure the fields are not editable
        // setAgentUnEditable();
    }


    public static void passStage(Stage stage) {
        stageTest = stage;

    }

}
