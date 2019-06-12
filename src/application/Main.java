/*
 ********************************************************************************************
 * Course: CMPP 264 Java Programming for OOSD
 * Purpose: Day 6 Assignment
 * Date: May 16, 2019.
 * Author: Tim Leslie
 * Description: This DBHelper class contains a static method which establishes a connection
 * to the TravelExperts database.
 *******************************************************************************************/
package application;

import controller.Controller;
import controller.PackageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        PackageController.passStage(primaryStage);
        Controller.passStage(primaryStage);

//        Parent root = FXMLLoader.load(getClass().getResource("../view/authentication.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
        primaryStage.setTitle("Travel Experts Database Management");
        primaryStage.setScene(new Scene(root, 806, 489));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
