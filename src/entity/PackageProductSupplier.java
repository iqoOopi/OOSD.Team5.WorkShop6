/******************************************************************************************
 * Course: CMPP 264 Java Programming for OOSD
 * Purpose: Workshop 6 Team 4
 * Date: May 10, 2019.
 * Author: Tim Leslie
 * Description: This class is part of the Workshop 6 Travel Experts GUI project.
 * The PackageProductSupplier class is a data entity class designed to store Product-Supplier
 * entities that are associated with particular PackageId's.
 ******************************************************************************************/
package entity;

import javafx.beans.property.SimpleIntegerProperty;

public class PackageProductSupplier {

    private SimpleIntegerProperty PackagePSId;
    private SimpleIntegerProperty ProductSupplierId;

    public PackageProductSupplier() {
    }

    public PackageProductSupplier(int packageId, int productSupplierId) {
        PackagePSId = new SimpleIntegerProperty(packageId);
        ProductSupplierId = new SimpleIntegerProperty(productSupplierId);
    }

    public int getPackageId() {
        return PackagePSId.get();
    }

    public SimpleIntegerProperty packagePSIdProperty() {
        return PackagePSId;
    }

    public void setPackageId(int packageId) {
        this.PackagePSId.set(packageId);
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
}
