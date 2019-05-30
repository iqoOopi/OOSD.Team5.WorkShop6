package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductsSuppliersViewModule {
    private SimpleIntegerProperty ProductId;
    private SimpleIntegerProperty SupplierId;
    private SimpleStringProperty SupName;

    public ProductsSuppliersViewModule(int productId, int supplierId, String supName) {
        ProductId = new SimpleIntegerProperty(productId);
        SupplierId = new SimpleIntegerProperty(supplierId);
        SupName = new SimpleStringProperty(supName);
    }


    public int getProductId() {
        return ProductId.get();
    }

    public SimpleIntegerProperty productIdProperty() {
        return ProductId;
    }

    public void setProductId(int productId) {
        this.ProductId.set(productId);
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
