
package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

//Henry
public class Product {
    private SimpleIntegerProperty ProductId;
    private SimpleStringProperty ProdName;

    public Product(int id, String prodName)
    {
         ProductId=new SimpleIntegerProperty(id);
         ProdName=new SimpleStringProperty(prodName);
    }

    public Product(String prodName)
    {
        ProdName=new SimpleStringProperty(prodName);
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

    public String getProdName() {
        return ProdName.get();
    }

    public SimpleStringProperty prodNameProperty() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        this.ProdName.set(prodName);
    }
}
