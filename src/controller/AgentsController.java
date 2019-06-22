package controller;

import java.awt.event.ItemListener;
import java.io.FileNotFoundException;


import dao.AgentsDAO;
import dao.CustomersDAO;
import entity.Agent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AgentsController {
	
	@FXML
	private TableView<Agent> 	uxListViewAgt;
	@FXML
    private TableColumn<Agent, String> agtIdColumn;
    @FXML
    private TableColumn<Agent, String> agtFirstNameColumn;
    @FXML
    private TableColumn<Agent, String> agtLastNameColumn;
	@FXML
	private Button 		uxBtnSave,uxBtnAddAgent, uxBtnCancel,uxBtnAddNewAgt;
	@FXML
	private TextField 	uxTextAgtFirstName,uxTextAgtEmail,
						uxTextAgtPhone,uxTextAgtLastName;
	@FXML
	private ComboBox	uxComboBoxAgtPosition,uxComboBoxAgtAgency;
	@FXML
	private ImageView uxImageAgt;
	@FXML
	private Label uxLabelAgency;
	
	private String selectedAgentId = null;
	
	// The selected agent
	private int selectedIndex = -1;
	
	@FXML
    private void initialize() {
		
		agtIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
    	agtFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        agtLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        		
		//add agents to listview
		uxListViewAgt.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayAgent((Agent) newValue));
		
		uxComboBoxAgtAgency.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
			displayAgencyName((String)newValue);
	    });
		
		setAgentDisplay();
	}	
	
	public void displayAgent(Agent selectedAgt){
    	if(uxListViewAgt.getSelectionModel().getSelectedItem() != null){
    		
    		uxTextAgtFirstName.setText(selectedAgt.getFirstName());
    		uxTextAgtLastName.setText(selectedAgt.getLastName());
    		uxTextAgtPhone.setText(selectedAgt.getPhone());
    		uxComboBoxAgtPosition.setValue(selectedAgt.getPosition());
        	uxTextAgtEmail.setText(selectedAgt.getEmail());
        	uxComboBoxAgtAgency.setValue(selectedAgt.getAgency());
        	
        	selectedAgentId = selectedAgt.getId();
        	
        	selectedIndex = uxListViewAgt.getSelectionModel().getSelectedIndex();
        	
        	Image image = new Image(getClass().getResourceAsStream("/ProfilePictures_Agents/default.jpg"));
        	String AgtIdPic=Integer.toString(selectedIndex+1)+".jpg";
        	        	
        	try {
        		image = new Image(getClass().getResourceAsStream("/ProfilePictures_Agents/" + AgtIdPic));        		
            } catch (Exception e) {
                             
            }
                    	
            uxImageAgt.setImage(image);
            
    	}
    	
    }
	
	public void setAgentDisplay(){
		uxListViewAgt.setItems(AgentsDAO.getAgents());
    	
        ObservableList<String> options = 
        	    FXCollections.observableArrayList(
        	        "Junior Agent",
        	        "Intermediate Agent",
        	        "Senior Agent"
        	    );
        uxComboBoxAgtPosition.setItems(options);
        uxComboBoxAgtAgency.setItems(AgentsDAO.getAgencies());
        uxListViewAgt.getSelectionModel().selectFirst();
    }
	
	public void displayAgencyName(String agencyId) {
		int inputId = Integer.parseInt(agencyId);
		String labelDisplay= "";
		
		switch(inputId) {
			case 1:
				labelDisplay= "Calgary";
				break;
			case 2:
				labelDisplay= "Okotoks";
				break;
		}
		uxLabelAgency.setText(labelDisplay);
	}
	
	@FXML
    public void saveAgent(){
		
		String inputAgtId = selectedAgentId;		
		String inputAgtFirstName = uxTextAgtFirstName.getText();
		String inputAgtLastName = uxTextAgtLastName.getText();
		String inputAgtPhone = uxTextAgtPhone.getText();
		String inputAgtPosition = uxComboBoxAgtPosition.getValue().toString();
		String inputAgtEmail = uxTextAgtEmail.getText();
		String inputAgtAgency = uxComboBoxAgtAgency.getValue().toString();
		
		boolean updateState = false;
		
		try {
	    	updateState = AgentsDAO.updateAgent(
					inputAgtId, 
					inputAgtFirstName, 
					inputAgtLastName, 
					inputAgtPhone, 
					inputAgtEmail, 
					inputAgtPosition, 
					inputAgtAgency);
		} catch(Exception e) {
			e.printStackTrace();  
		}
		
		if (updateState) {
			setAgentDisplay();
		} 
    	
	}
	
	@FXML
    public void addAgentClicked(){
		uxLabelAgency.setText("");
		uxBtnSave.setVisible(false);
		uxBtnAddAgent.setVisible(false);
		uxBtnAddNewAgt.setVisible(true);
		uxTextAgtFirstName.setText("");
		uxTextAgtLastName.setText("");
		uxTextAgtPhone.setText("");
		uxTextAgtEmail.setText("");
		uxComboBoxAgtPosition.setValue(null);
		uxComboBoxAgtAgency.setValue(null);
    	
		uxListViewAgt.setDisable(true);
		
		Image image = new Image(getClass().getResourceAsStream("/ProfilePictures_Agents/default.jpg"));
		uxImageAgt.setImage(image); 
	 
	}
	
	@FXML
    public void addNewAgent() {
		// need validations
		String inputAgtFirstName = uxTextAgtFirstName.getText();
		String inputAgtLastName = uxTextAgtLastName.getText();
		String inputAgtPhone = uxTextAgtPhone.getText();
		String inputAgtPosition = uxComboBoxAgtPosition.getValue().toString();
		String inputAgtEmail = uxTextAgtEmail.getText();
		String inputAgtAgency = uxComboBoxAgtAgency.getValue().toString();
		
		boolean updateState = false;
		
		try {
	    	updateState = AgentsDAO.addAgent(
					inputAgtFirstName, 
					inputAgtLastName, 
					inputAgtPhone, 
					inputAgtEmail, 
					inputAgtPosition, 
					inputAgtAgency);
		} catch(Exception e) {
			e.printStackTrace();  
		}
		
		if (updateState) {
			setAgentDisplay();
			resetView();
		} 
	}
	
	@FXML
	public void resetView() {
		
		uxBtnSave.setVisible(true);
		uxListViewAgt.setDisable(false);
		uxBtnAddAgent.setVisible(true);
		uxBtnAddNewAgt.setVisible(false);
		setAgentDisplay();
		
	}
	
	
}
