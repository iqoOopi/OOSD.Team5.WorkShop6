package entity;

import javafx.beans.property.SimpleIntegerProperty;

//Henry
public class ProductsSuppliers {
    private SimpleIntegerProperty ProductSupplierId;
    private SimpleIntegerProperty ProductId;
    private SimpleIntegerProperty SupplierId;

    public ProductsSuppliers(int proId, int supId){
        //int proSupId is not needed for insert
        ProductId=new SimpleIntegerProperty(proId);
        SupplierId=new SimpleIntegerProperty(supId);
    }

    public int getProductSupplierId() {
        return ProductSupplierId.get();
    }

    public SimpleIntegerProperty productSupplierIdProperty() {
        return ProductSupplierId;
    }

    public void setProductSupplierId(int productSupplierId) {
        this.ProductSupplierId.set(productSupplierId);
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
}
