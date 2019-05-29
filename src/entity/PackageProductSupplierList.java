/*
 ***********************************************************************************************
 * Course: CMPP 264 Java Programming for OOSD
 * Purpose: Workshop 6 Team 4
 * Date: May 10, 2019.
 * Author: Tim Leslie
 * Description: This class is part of the Workshop 6 Travel Experts GUI project.
 * The PackageProductSupplierList class is a data entity class designed to store PackageId,
 * ProdName, and SupName entities that are associated with a particular PackageId. This data
 * class is used primarily to store data from a complex table join and populate the
 * Product-Supplier table view on the Package Management scene.
 **********************************************************************************************/

package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PackageProductSupplierList {

    private SimpleIntegerProperty PackageId;
    private SimpleStringProperty ProdName;
    private SimpleStringProperty SupName;

    public PackageProductSupplierList(int packageId, String prodName, String supName) {
        PackageId = new SimpleIntegerProperty(packageId);
        ProdName = new SimpleStringProperty(prodName);
        SupName = new SimpleStringProperty(supName);
    }

    public int getPackageId() {
        return PackageId.get();
    }

    public SimpleIntegerProperty packageIdProperty() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        this.PackageId.set(packageId);
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

    public String getSupName() {
        return SupName.get();
    }

    public SimpleStringProperty supNameProperty() {
        return SupName;
    }

    public void setSupName(String supName) {
        this.SupName.set(supName);
    }

    @Override
    public String toString() {
        return "PackageProductSupplierList{" +
                "PackageId=" + PackageId +
                ", ProdName=" + ProdName +
                ", SupName=" + SupName +
                '}';
    }
}
