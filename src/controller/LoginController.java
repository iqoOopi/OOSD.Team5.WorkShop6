// Hoang Truong
package controller;

import java.awt.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
		
	private static Stage stageTest;
	
	private Label labelForgotPassword;
	
	@FXML
	private TextField inputUsername;
	@FXML
	private PasswordField inputPassword;
	
	private Button buttonLogin;
	private Button buttonRegister;
	
//*****VALIDATION METHODS*********************************************
	private Boolean isProvidedStrings(List<String> values) {
		Boolean status = true;
		for (int i=0; i < values.size(); i++) {
			if (values.get(i) == null || values.get(i).isEmpty()) {
				status = false;
			}     		
		}
		
		return status;    	
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		
	}
	
	@FXML
	private void validate(ActionEvent event) {
		String usernameInput = inputUsername.getText();
		String psswdInput = inputPassword.getText();
		
		List<String> loginStrings = new ArrayList<String>();
		loginStrings.add(usernameInput);
		loginStrings.add(psswdInput);
		
		if (!isProvidedStrings( loginStrings )) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please fill all required fields !");
			alert.showAndWait();
		} else {
			// carry-out authentication
		}
		
	}
	
	public static void passStage(Stage stage) {
        stageTest = stage;

    }
	
	
}
