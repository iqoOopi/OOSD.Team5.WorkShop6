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


    public static void passStage(Stage stage) {
        stageTest = stage;
    }

    @FXML
    void initialize() {
        assert btnPackageManagement != null : "fx:id=\"btnPackageManagement\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnProdManag != null : "fx:id=\"btnProdManag\" was not injected: check your FXML file 'sample.fxml'.";
    }

    @FXML
    void goToPackageManagement(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/package.fxml"));
        stageTest.setTitle("Travel Experts Package Maintenance");
        Scene packageScene = new Scene(root, 1200, 650);

        stageTest.setScene(packageScene);
        stageTest.show();
    }

    @FXML
    void gotoProdMng(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/prodSupView.fxml"));
        stageTest.setTitle("Travel Experts Products Maintenance");
        Scene prodSupScene = new Scene(root, 800, 600);

        stageTest.setScene(prodSupScene);
        stageTest.show();
    }


}
