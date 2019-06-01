/*
 ********************************************************************************************
 * Course: CMPP 264 Java Programming for OOSD
 * Purpose: Day 6 Assignment
 * Date: May 16, 2019.
 * Author: Tim Leslie
 * Description: This PackagesDAO class contains the database interaction methods (CRUD
 * functionality) required for the Package class and database.
 *
 *******************************************************************************************/
package dao;

import entity.Package;
import entity.PackageProductSupplierList;
import entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static utility.Tools.infoBox;

public class PackagesDAO {

    public ObservableList<Package> getAllPackages() {

        ObservableList<Package> packageList = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM packages");
            while (rs.next())
            {
                packageList.add(new Package(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getDate(4).toLocalDate(),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7)
                ));
            }
            conn.close();
            // tvPackages.setItems(packageList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageList;
    }

    public ObservableList<PackageProductSupplierList> getRelatedProductsSuppliers (Package pkg) {

        ObservableList<PackageProductSupplierList> relatedPSList = FXCollections.observableArrayList();

        Connection conn = DBHelper.getConnection();

        try {
            String sql2 = "SELECT pps.ProductSupplierId, ProdName, SupName FROM packages_products_suppliers pps " +
                    "INNER JOIN products_suppliers ps ON pps.ProductSupplierId=ps.ProductSupplierId " +
                    "INNER JOIN products p ON ps.ProductId=p.ProductId " +
                    "INNER JOIN suppliers s ON ps.SupplierId=s.SupplierId " +
                    "WHERE PackageId=?";
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setInt(1, pkg.getPackageId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                relatedPSList.add(new PackageProductSupplierList(rs.getInt(1), rs.getString(2),
                        rs.getString(3)));
                System.out.println(rs.getInt(1));
            }
            conn.close();

            //tvProductSupplier.setItems(packagePSList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatedPSList;

    }


    public ObservableList<Product> LoadAllProducts() {
        ObservableList<Product> productsList = FXCollections.observableArrayList();
        try (Connection conn = DBHelper.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT ProductId, ProdName FROM products");
            while (rs.next())
            {
                productsList.add(
                        new Product(
                                rs.getInt(1),
                                rs.getString(2))
                );
            }
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Read Products");
        }
        return productsList;
    }

    public void deletePackageById(int id){

        String sql = "DELETE FROM Packages WHERE PackageId = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.execute();
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Delete Package");
        }
    }

    public void saveNewPackage(Package newPackage){
        String sql = "INSERT INTO Products (ProdName) VALUES (?)";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,newPackage.getPkgName());
            pstmt.execute();
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Save Package");
        }

    }

    public void updatePackage (Package editedPackage){
        String sql = "UPDATE Packages SET ProdName=? WHERE PackageId = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,editedPackage.getPkgName());
            pstmt.setInt(2,editedPackage.getPackageId());
            pstmt.execute();
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Update Package");
        }
    }


}
