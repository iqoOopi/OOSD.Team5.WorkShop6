package controller;

import java.util.Optional;

import dao.AgentsDAO;
import dao.CustomersDAO;
import entity.Agent;
import entity.Customer;
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

public class CustomersController {
	@FXML
	private TableView<Customer> 	uxListViewCust;
	@FXML
    private TableColumn<Customer, String> custIdColumn,custAgentIdColumn,custLastNameColumn,custFirstNameColumn;
	@FXML
	private Button 		uxBtnAddNew,uxBtnAdd,uxBtnSave,uxBtnAddAgent, uxBtnCancel,uxBtnAddNewAgt;
	@FXML
	private TextField 	uxTextEmail,uxTextBusiPhone,uxTextHomePhone,uxTextPC,uxTextCity,
						uxTextProvince,uxTextAddress,uxTextLastName,uxTextCustFistName,uxTextCountry;
	@FXML
	private ComboBox	uxComboAgentId;
	@FXML
	private ImageView uxImageCust;
	@FXML
	private Label uxLabelEgtFullName;
	
	// The selected customer
	private int selectedIndex = -1;
	
	private ObservableList<Agent> allAgents = null;
	private String selectedCustId = null; 
	
	@FXML
    private void initialize() {
		
		uxLabelEgtFullName.setText("");    
		custIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        custFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        custLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        custAgentIdColumn.setCellValueFactory(cellData -> cellData.getValue().agentIdProperty());
        
        
		//add agents to listview
        uxListViewCust.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayCustomer((Customer) newValue));
		
//        uxComboAgentId.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
//        	displayAgentFullname((String)newValue);
//	    });
        
        setCustomerDisplay();
	}
	
	public void setCustomerDisplay(){
		
		uxListViewCust.setItems(CustomersDAO.getCustomers());
        uxComboAgentId.setItems(AgentsDAO.getAllAgentIds());
        uxListViewCust.getSelectionModel().selectFirst();
        allAgents = AgentsDAO.getAgents();
    }
	
	public void displayCustomer(Customer selectedCust){
    	if(uxListViewCust.getSelectionModel().getSelectedItem() != null){
    		
    		uxTextEmail.setText(selectedCust.getEmail());
    		uxTextBusiPhone.setText(selectedCust.getBusPhone());
    		uxTextHomePhone.setText(selectedCust.getHomePhone());
    		uxTextPC.setText(selectedCust.getPostal());
    		uxTextCity.setText(selectedCust.getCity());
    		uxTextProvince.setText(selectedCust.getProvince());
    		uxTextAddress.setText(selectedCust.getAddress());
    		uxTextLastName.setText(selectedCust.getLastName());
    		uxTextCustFistName.setText(selectedCust.getFirstName());
    		uxTextCountry.setText(selectedCust.getCountry());
    		
        	selectedIndex = uxListViewCust.getSelectionModel().getSelectedIndex();
        	
        	selectedCustId = selectedCust.getId();
        	
        	Image image = new Image(getClass().getResourceAsStream("/ProfilePictures_Customers/default.png"));
        	String AgtIdPic = selectedCust.getFirstName()+".jpg";
        	        	
        	try {
        		image = new Image(getClass().getResourceAsStream("/ProfilePictures_Customers/" + AgtIdPic));        		
            } catch (Exception e) {
                             
            }
                    	
        	uxImageCust.setImage(image);        	
        	
        	if (selectedCust.getAgentId().toString() != null) {
        		String agtId = selectedCust.getAgentId();
        		uxComboAgentId.setValue(agtId);
//            	displayAgentFullname(agtId);
        	}
        	      	            
    	}
    	
    }
	
	public void displayAgentFullname(String inputAgtId) {
		
		if ( !inputAgtId.isEmpty() ) {
			allAgents.forEach( (agt) -> {
	    		
	    		String agtId = agt.getId();
	    		
	    		if ( inputAgtId.equals(agtId) ) {
	    			
	    			String firstName = agt.getFirstName();
	    			String lastName = agt.getLastName();
	    			String agtFullname = firstName + " " + lastName;
	    			
	    			uxLabelEgtFullName.setText(agtFullname);
	    		}
	    		
	    	} );
		}
	}
	
	@FXML
    public void saveCustomer(){
		
		String inputCustId = selectedCustId;		
		String inputCustFirstName = uxTextCustFistName.getText();
		String inputCustLastName = uxTextLastName.getText();
		String inputCustAddress = uxTextAddress.getText();
		String inputCustCity = uxTextCity.getText();
		String inputCustProv = uxTextProvince.getText();
		String inputCustPostal = uxTextPC.getText();
		String inputCustCountry = uxTextCountry.getText();
		String inputCustHomePhone = uxTextHomePhone.getText();
		String inputCustBusPhone = uxTextBusiPhone.getText();
		String inputCustEmail = uxTextEmail.getText();
		String inputAgentId = uxComboAgentId.getValue().toString();
		
		boolean updateState = false;
		
		try {
	    	updateState = CustomersDAO.updateCustomer(
	    			inputCustId,
					inputCustFirstName,
					inputCustLastName,
					inputCustAddress,
					inputCustCity,
					inputCustProv,
					inputCustPostal,
					inputCustCountry,
					inputCustHomePhone,
					inputCustBusPhone,
					inputCustEmail,
					inputAgentId);
		} catch(Exception e) {
			e.printStackTrace();  
		}
		
		if (updateState) {
			setCustomerDisplay();
		} 
		
	}
	
	@FXML
    public void addNewClicked(){
		
		uxTextEmail.setText("");
		uxTextBusiPhone.setText("");
		uxTextHomePhone.setText("");
		uxTextPC.setText("");
		uxTextCity.setText("");
		uxTextProvince.setText("");
		uxTextAddress.setText("");
		uxTextLastName.setText("");
		uxTextCustFistName.setText("");
		uxTextCountry.setText("");
		uxLabelEgtFullName.setText("");
		uxListViewCust.setDisable(true);
		uxBtnSave.setVisible(false);
		uxBtnAddNew.setVisible(false);
		uxBtnAdd.setVisible(true);
		uxComboAgentId.setValue(null);
		
		Image image = new Image(getClass().getResourceAsStream("/ProfilePictures_Customers/default.png"));
		uxImageCust.setImage(image); 
	 
	}
	
	@FXML
    public void addNewCust() {
		
		// need validations
		String inputCustFirstName = uxTextCustFistName.getText();
		String inputCustLastName = uxTextLastName.getText();
		String inputCustAddress = uxTextAddress.getText();
		String inputCustCity = uxTextCity.getText();
		String inputCustProv = uxTextProvince.getText();
		String inputCustPostal = uxTextPC.getText();
		String inputCustCountry = uxTextCountry.getText();
		String inputCustHomePhone = uxTextHomePhone.getText();
		String inputCustBusPhone = uxTextBusiPhone.getText();
		String inputCustEmail = uxTextEmail.getText();
		String inputAgentId = uxComboAgentId.getValue().toString();
		
		boolean insertState = false;
		
		try {
	    	insertState = CustomersDAO.addCustomer(
	    			inputCustFirstName,
					inputCustLastName,
					inputCustAddress,
					inputCustCity,
					inputCustProv,
					inputCustPostal,
					inputCustCountry,
					inputCustHomePhone,
					inputCustBusPhone,
					inputCustEmail,
					inputAgentId);
		} catch(Exception e) {
			e.printStackTrace();  
		}
		
		if (insertState) {
			resetView();
		} 
	}
	
	@FXML
    public void cancelClicked(){		
		
		resetView();
	 
	}
	
	public void resetView() {
		
		uxListViewCust.setDisable(false);
		uxBtnSave.setVisible(true);
		uxBtnAddNew.setVisible(true);
		uxBtnAdd.setVisible(false);
		uxComboAgentId.setValue(null);
		
		setCustomerDisplay();
	}
	
	
}
