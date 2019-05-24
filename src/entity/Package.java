 /******************************************************************************************
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

 public class Package {

    // properties declaration
    private SimpleIntegerProperty PackageId;
    private SimpleStringProperty PkgName;
    private SimpleObjectProperty<LocalDate> PkgStartDate;
    private SimpleObjectProperty<LocalDate> PkgEndDate;
    private SimpleStringProperty PkgDesc;
    private SimpleDoubleProperty PkgBaseprice;
    private SimpleIntegerProperty PkgAgencyCommission;


    // constructor
    public Package(int packageId, String pkgName,
                   LocalDate pkgStartDate, LocalDate pkgEndDate,
                   String pkgDesc, Double pkgBaseprice,
                   int pkgAgencyCommission) {

        PackageId = new SimpleIntegerProperty(packageId);
        PkgName = new SimpleStringProperty(pkgName);
        PkgStartDate = new SimpleObjectProperty<LocalDate>(pkgStartDate);
        PkgEndDate = new SimpleObjectProperty<LocalDate>(pkgEndDate);
        PkgDesc = new SimpleStringProperty(pkgDesc);
        PkgBaseprice = new SimpleDoubleProperty(pkgBaseprice);
        PkgAgencyCommission = new SimpleIntegerProperty(pkgAgencyCommission);

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

    public SimpleObjectProperty<LocalDate> getPkgStartDate() {
        return PkgStartDate;
    }

    public void setPkgStartDate(SimpleObjectProperty<LocalDate> pkgStartDate) {
        PkgStartDate = pkgStartDate;
    }

    public SimpleObjectProperty<LocalDate> getPkgEndDate() {
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

    public double getPkgBaseprice() {
        return PkgBaseprice.get();
    }

    public SimpleDoubleProperty pkgBasepriceProperty() {
        return PkgBaseprice;
    }

    public void setPkgBaseprice(double pkgBaseprice) {
        this.PkgBaseprice.set(pkgBaseprice);
    }

    public int getPkgAgencyCommission() {
        return PkgAgencyCommission.get();
    }

    public SimpleIntegerProperty pkgAgencyCommissionProperty() {
        return PkgAgencyCommission;
    }

    public void setPkgAgencyCommission(int pkgAgencyCommission) {
        this.PkgAgencyCommission.set(pkgAgencyCommission);
    }

    // override toString with a simple return String. This can be modified according to needs.
    @Override
    public String toString() {
        return "Package{" +
                "PkgName=" + PkgName +
                ", PkgBaseprice=" + PkgBaseprice +
                '}';
    }
}

