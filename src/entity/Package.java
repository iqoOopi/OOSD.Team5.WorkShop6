 /*
 *******************************************************************************************
 * Course: CMPP 264 Java Programming for OOSD
 * Purpose: Workshop 6 Team 4
 * Date: May 10, 2019.
 * Author: Tim Leslie
 * Description: This class is part of the Workshop 6 Travel Experts GUI project.
 * The Package class is a data entity to store travel package information.
 ******************************************************************************************/
package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

 public class Package {

    // properties declaration
    private SimpleIntegerProperty PackageId;
    private SimpleStringProperty PkgName;
    private SimpleObjectProperty<LocalDate> PkgStartDate;
    private SimpleObjectProperty<LocalDate> PkgEndDate;
    private SimpleStringProperty PkgDesc;
    private SimpleDoubleProperty PkgBasePrice;
    private SimpleDoubleProperty PkgAgencyCommission;

    // default constructor
    public Package() {};

    // constructor
    public Package(int packageId, String pkgName,
                   LocalDate pkgStartDate, LocalDate pkgEndDate,
                   String pkgDesc, double pkgBasePrice,
                   double pkgAgencyCommission) {

        PackageId = new SimpleIntegerProperty(packageId);
        PkgName = new SimpleStringProperty(pkgName);
        //PkgStartDate = new SimpleObjectProperty<LocalDate>(pkgStartDate);
        PkgStartDate = new SimpleObjectProperty<>(pkgStartDate);
        PkgEndDate = new SimpleObjectProperty<>(pkgEndDate);
        PkgDesc = new SimpleStringProperty(pkgDesc);
        PkgBasePrice = new SimpleDoubleProperty(pkgBasePrice);
        PkgAgencyCommission = new SimpleDoubleProperty(pkgAgencyCommission);

    }

    // getters and setters
    public int getPackageId() {
        return PackageId.get();
    }

    public SimpleIntegerProperty packageIdProperty() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        this.PackageId.set(packageId);
    }

    public String getPkgName() {
        return PkgName.get();
    }

    public SimpleStringProperty pkgNameProperty() {
        return PkgName;
    }

    public void setPkgName(String pkgName) {
        this.PkgName.set(pkgName);
    }

     public LocalDate getPkgStartDate() {
         return PkgStartDate.get();
     }

     public SimpleObjectProperty<LocalDate> pkgStartDateProperty() {
         return PkgStartDate;
     }

     public SimpleObjectProperty<LocalDate> pkgStartDate() {
        return PkgStartDate;
    }

    public void setPkgStartDate(SimpleObjectProperty<LocalDate> pkgStartDate) {
        PkgStartDate = pkgStartDate;
    }

     public LocalDate getPkgEndDate() {
         return PkgEndDate.get();
     }

     public SimpleObjectProperty<LocalDate> pkgEndDateProperty() {
        return PkgEndDate;
    }

    public void setPkgEndDate(SimpleObjectProperty<LocalDate> pkgEndDate) {
        PkgEndDate = pkgEndDate;
    }


    public String getPkgDesc() {
        return PkgDesc.get();
    }

    public SimpleStringProperty pkgDescProperty() {
        return PkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.PkgDesc.set(pkgDesc);
    }

    public double getPkgBasePrice() {
        return PkgBasePrice.get();
    }

    public SimpleDoubleProperty pkgBasePriceProperty() {
        return PkgBasePrice;
    }

    public void setPkgBasePrice(double pkgBasePrice) {
        this.PkgBasePrice.set(pkgBasePrice);
    }

    public double getPkgAgencyCommission() {
        return PkgAgencyCommission.get();
    }

    public SimpleDoubleProperty pkgAgencyCommissionProperty() {
        return PkgAgencyCommission;
    }

    public void setPkgAgencyCommission(double pkgAgencyCommission) {
        this.PkgAgencyCommission.set(pkgAgencyCommission);
    }

    // override toString with a simple return String. This can be modified according to needs.
    @Override
    public String toString() {
        return "Package{" +
                "PkgName=" + PkgName +
                ", PkgBaseprice=" + PkgBasePrice +
                '}';
    }
}

