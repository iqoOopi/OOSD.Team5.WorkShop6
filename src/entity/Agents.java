package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Agents {
    // properties declaration
    private SimpleIntegerProperty AgentId;
    private SimpleStringProperty AgtFirstName;
    private SimpleStringProperty AgtMiddleInitial;
    private SimpleStringProperty AgtLastName;
    private SimpleStringProperty AgtBusPhone;
    private SimpleStringProperty AgtEmail;
    private SimpleStringProperty AgtPosition;
    private SimpleIntegerProperty AgencyId;
    private SimpleStringProperty AgtPassword;


    // constructor
    public Agents(
            int AgentIdInput,
            String AgtFirstNameInput,
            String AgtMiddleInitialInput,
            String AgtLastNameInput,
            String AgtBusPhoneInput,
            String AgtEmailInput,
            String AgtPositionInput,
            int AgencyIdInput,
            String AgtPasswordInput
    ) {

        AgentId = new SimpleIntegerProperty(AgentIdInput);
        AgtFirstName = new SimpleStringProperty(AgtFirstNameInput);
        AgtMiddleInitial = new SimpleStringProperty(AgtMiddleInitialInput);
        AgtLastName = new SimpleStringProperty(AgtLastNameInput);
        AgtBusPhone = new SimpleStringProperty(AgtBusPhoneInput);
        AgtEmail = new SimpleStringProperty(AgtEmailInput);
        AgtPosition = new SimpleStringProperty(AgtPositionInput);
        AgencyId = new SimpleIntegerProperty(AgencyIdInput);
        AgtPassword = new SimpleStringProperty(AgtPasswordInput);
    }

// getters and setters

    public int getAgentId() {
        return AgentId.get();
    }

    public SimpleIntegerProperty agentIdProperty() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        this.AgentId.set(agentId);
    }


    public String getAgtFirstName() {
        return AgtFirstName.get();
    }

    public SimpleStringProperty agtFirstNameProperty() {
        return AgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        this.AgtFirstName.set(agtFirstName);
    }


    public String getAgtMiddleInitial() {
        return AgtMiddleInitial.get();
    }

    public SimpleStringProperty agtMiddleInitialProperty() {
        return AgtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) {
        this.AgtMiddleInitial.set(agtMiddleInitial);
    }

    public String getAgtLastName() {
        return AgtLastName.get();
    }

    public SimpleStringProperty agtLastNameProperty() {
        return AgtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        this.AgtLastName.set(agtLastName);
    }


    public String getAgtBusPhone() {
        return AgtLastName.get();
    }

    public SimpleStringProperty agtBusPhoneProperty() {
        return AgtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        this.AgtBusPhone.set(agtBusPhone);
    }


    public String getAgtEmail() {
        return AgtEmail.get();
    }

    public SimpleStringProperty agtEmailProperty() {
        return AgtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        this.AgtEmail.set(agtEmail);
    }


    public String getAgtPosition() {
        return AgtPosition.get();
    }

    public SimpleStringProperty agtPositionProperty() {
        return AgtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        this.AgtPosition.set(agtPosition);
    }



    public int getAgencyId() {
        return AgencyId.get();
    }

    public SimpleIntegerProperty agencyIdProperty() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        this.AgencyId.set(agencyId);
    }



    public String getAgtPassword() {
        return AgtPassword.get();
    }

    public SimpleStringProperty agtPasswordProperty() {
        return AgtPassword;
    }

    public void setAgtPassword(String agtPassword ) {
        this.AgtPassword.set(agtPassword);
    }

    // override toString with a simple return String. This can be modified according to needs.
    @Override
    public String toString() {
        return "Agent{ " +
        "AgentId="+ AgentId + "," +
        "AgtFirstName="+ AgtFirstName + "," +
        "AgtMiddleInitial="+ AgtMiddleInitial + "," +
        "AgtLastName="+ AgtLastName + "," +
        "AgtBusPhone="+ AgtBusPhone + "," +
        "AgtEmail="+ AgtEmail + "," +
        "AgtPosition="+ AgtPosition + "," +
        "AgencyId="+ AgencyId + "," +
        "AgtPassword="+ AgtPassword +
        '}';
    }
}
