/******************************************************************************************
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

        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/package.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Travel Experts Package Maintenance");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();*/
        //controller = loader.getController();
        //controller.setMain(this);


        //Parent root2 = FXMLLoader.load(getClass().getResource("../view/package.fxml"));
        //primaryStage.setTitle("Travel Experts Package Management");
        //primaryStage.setScene(new Scene(root2, 800, 600));
        //primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
