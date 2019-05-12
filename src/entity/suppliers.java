package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

//Henry
public class suppliers {
    private SimpleIntegerProperty SupplierId;
    private SimpleStringProperty SupName;
    public suppliers (int supId, String supName){
        SupplierId = new SimpleIntegerProperty(supId);
        SupName = new SimpleStringProperty(supName);
    }

    public int getSupplierId() {
        return SupplierId.get();
    }

    public SimpleIntegerProperty supplierIdProperty() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        this.SupplierId.set(supplierId);
    }

    public String getSupName() {
        return SupName.get();
    }

    public SimpleStringProperty supNameProperty() {
        return SupName;
    }

    public void setSupName(String supName) {
        this.SupName.set(supName);
    }
}
