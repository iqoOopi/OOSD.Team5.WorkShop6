/*
 *******************************************************************************************
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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller {

    private static Stage stageTest;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnPackageManagement;

    @FXML
    private Button btnProdManag;

    @FXML
    private BorderPane bpMenuBar;

    @FXML
    private Button btnBookingManagement;


    public static void passStage(Stage stage) {
        stageTest = stage;
    }

    @FXML
    void initialize() {
        assert bpMenuBar != null : "fx:id=\"bpMenuBar\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnProdManag != null : "fx:id=\"btnProdManag\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnPackageManagement != null : "fx:id=\"btnPackageManagement\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnBookingManagement != null : "fx:id=\"btnBookingManagement\" was not injected: check your FXML file 'sample.fxml'.";

        btnBookingManagement.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../view/bookings_view.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bpMenuBar.setCenter(root);
        });

    }

    @FXML
    void goToPackageManagement(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/package.fxml"));
//        stageTest.setTitle("Travel Experts Package Maintenance");
//        Scene packageScene = new Scene(root, 1200, 650);
//
//        stageTest.setScene(packageScene);
//        stageTest.show();

        bpMenuBar.setCenter(root);
    }

    @FXML
    void gotoProdMng(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/prodSupView.fxml"));
//        stageTest.setTitle("Travel Experts Products Maintenance");
//        Scene prodSupScene = new Scene(root, 800, 600);
        bpMenuBar.setCenter(root);

//        stageTest.setScene(prodSupScene);
//        stageTest.show();
    }




}
