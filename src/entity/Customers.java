// Hoang Truong
package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customers {
	// properties declaration
    private SimpleIntegerProperty CustomerId;
    private SimpleStringProperty CustFirstName;
    private SimpleStringProperty CustLastName;
    private SimpleStringProperty CustAddress;
    private SimpleStringProperty CustCity;
    private SimpleStringProperty CustProv;
    private SimpleStringProperty CustPostal;
    private SimpleStringProperty CustCountry;
    private SimpleStringProperty CustHomePhone;
    private SimpleStringProperty CustBusPhone;
    private SimpleStringProperty CustEmail;
    private SimpleStringProperty CustPassword;
    private SimpleIntegerProperty AgentId;


    // constructor
    public Customers(
    		int CustomerIdInput,
    		String CustFirstNameInput,
    		String CustLastNameInput,
    		String CustAddressInput,
    		String CustCityInput,
    		String CustProvInput,
    		String CustPostalInput,
    		String CustCountryInput,
    		String CustHomePhoneInput,
    		String CustBusPhoneInput,
    		String CustEmailInput,
    		String CustPasswordInput,
    		int AgentIdInput
    ) {

    	CustomerId = new SimpleIntegerProperty(CustomerIdInput);
    	CustFirstName = new SimpleStringProperty(CustFirstNameInput);
    	CustLastName = new SimpleStringProperty(CustLastNameInput);
    	CustAddress = new SimpleStringProperty(CustAddressInput);
    	CustCity = new SimpleStringProperty(CustCityInput);
    	CustProv = new SimpleStringProperty(CustProvInput);
    	CustPostal = new SimpleStringProperty(CustPostalInput);
    	CustCountry = new SimpleStringProperty(CustCountryInput);
    	CustHomePhone = new SimpleStringProperty(CustHomePhoneInput);
    	CustBusPhone = new SimpleStringProperty(CustBusPhoneInput);
    	CustEmail = new SimpleStringProperty(CustEmailInput);
    	CustPassword = new SimpleStringProperty(CustPasswordInput);
    	AgentId = new SimpleIntegerProperty(AgentIdInput);
    }

// getters and setters

    public int getCustomerId() {
        return CustomerId.get();
    }

    public SimpleIntegerProperty CustomerIdProperty() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId.set(CustomerId);
    }


    public String getCustFirstName() {
        return CustFirstName.get();
    }

    public SimpleStringProperty CustFirstNameProperty() {
        return CustFirstName;
    }

    public void setCustFirstName(String CustFirstName) {
        this.CustFirstName.set(CustFirstName);
    }

    public String getCustLastName() {
        return CustLastName.get();
    }

    public SimpleStringProperty CustLastNameProperty() {
        return CustLastName;
    }

    public void setCustLastName(String CustLastName) {
        this.CustLastName.set(CustLastName);
    }


    public String getCustBusPhone() {
        return CustBusPhone.get();
    }

    public SimpleStringProperty CustBusPhoneProperty() {
        return CustBusPhone;
    }

    public void setCustBusPhone(String CustBusPhone) {
        this.CustBusPhone.set(CustBusPhone);
    }


    public String getCustEmail() {
        return CustEmail.get();
    }

    public SimpleStringProperty CustEmailProperty() {
        return CustEmail;
    }

    public void setCustEmail(String CustEmail) {
        this.CustEmail.set(CustEmail);
    }


    public String getCustAddress() {
        return CustAddress.get();
    }

    public SimpleStringProperty CustAddressProperty() {
        return CustAddress;
    }

    public void setCustAddress(String CustAddress) {
        this.CustAddress.set(CustAddress);
    }



    public int getAgentId() {
        return AgentId.get();
    }

    public SimpleIntegerProperty agentIdProperty() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        this.AgentId.set(agentId);
    }



    public String getCustPassword() {
        return CustPassword.get();
    }

    public SimpleStringProperty CustPasswordProperty() {
        return CustPassword;
    }

    public void setCustPassword(String CustPassword ) {
        this.CustPassword.set(CustPassword);
    }
    
    public String getCustCity() {
        return CustCity.get();
    }

    public SimpleStringProperty CustCityProperty() {
        return CustCity;
    }

    public void setCustCity(String CustCity ) {
        this.CustCity.set(CustCity);
    }
    
    public String getCustProv() {
        return CustProv.get();
    }

    public SimpleStringProperty CustProvProperty() {
        return CustProv;
    }

    public void setCustProv(String CustProv ) {
        this.CustProv.set(CustProv);
    }
    
    //
    
    public String getCustPostal() {
        return CustPostal.get();
    }

    public SimpleStringProperty CustPostalProperty() {
        return CustPostal;
    }

    public void setCustPostal(String CustPostal ) {
        this.CustPostal.set(CustPostal);
    }
    
    //
    
    public String getCustCountry() {
        return CustCountry.get();
    }

    public SimpleStringProperty CustCountryProperty() {
        return CustCountry;
    }

    public void setCustCountry(String CustCountry ) {
        this.CustCountry.set(CustCountry);
    }
    
    //
    
    public String getCustHomePhone() {
        return CustHomePhone.get();
    }

    public SimpleStringProperty CustHomePhoneProperty() {
        return CustHomePhone;
    }

    public void setCustHomePhone(String CustHomePhone ) {
        this.CustHomePhone.set(CustHomePhone);
    }
    //
    
    
    

    // override toString with a simple return String. This can be modified according to needs.
    @Override
    public String toString() {
        return "Customer{ " +
        		"CustomerId    = " + CustomerId    + "," + 
        		"CustFirstName = " + CustFirstName + "," + 
        		"CustLastName  = " + CustLastName  + "," + 
        		"CustAddress   = " + CustAddress   + "," + 
        		"CustCity      = " + CustCity      + "," + 
        		"CustProv      = " + CustProv      + "," + 
        		"CustPostal    = " + CustPostal    + "," + 
        		"CustCountry   = " + CustCountry   + "," + 
        		"CustHomePhone = " + CustHomePhone + "," + 
        		"CustBusPhone  = " + CustBusPhone  + "," + 
        		"CustEmail     = " + CustEmail     + "," + 
        		"CustPassword  = " + CustPassword  + "," + 
        		"AgentId       = " + AgentId       + " }";
    }
}
