package dao;

import entity.ProductsSuppliersViewModule;
import entity.Suppliers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static utility.Tools.infoBox;

//By Henry
public class RelatedSuppliersDAO {
    public ObservableList<ProductsSuppliersViewModule> LoadAllRelatedSuppliers(int id){
        String sql = "SELECT ps.SupplierId, s.SupName, ps.ProductSupplierId " +
                "FROM Products_Suppliers ps JOIN Suppliers s ON " +
                "ps.SupplierId = s.SupplierId" +
                " WHERE ProductId=?"+
                " ORDER BY s.SupName";
        ObservableList<ProductsSuppliersViewModule> ProductsSuppliersViewModuleList = FXCollections.observableArrayList();
        try (Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                ProductsSuppliersViewModuleList.add(
                        new ProductsSuppliersViewModule(
                                id,                     //product Id
                                rs.getInt(1),           //supplier id
                                rs.getString(2),        //supplier name
                                rs.getInt(3)            //productsSupplier Id
                        )
                );
            }
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Read Products");
        }
        return ProductsSuppliersViewModuleList;
    }

    public void DeleteById(int id){
        String sql = "DELETE FROM Products_Suppliers WHERE ProductSupplierId = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.execute();
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Delete Linked Supplier");
        }
    }

    public ObservableList<Suppliers> LoadNonSelectedSuppliers(int productId) {
        String sql1 = "SELECT SupplierId " +
                "FROM Products_Suppliers" +
                " WHERE ProductId=?";

        String sql = "SELECT SupplierId, SupName " +
                "FROM Suppliers" +
                " WHERE SupplierId NOT IN("+
                sql1+") "+
                "ORDER BY SupName";

        ObservableList<Suppliers> nonSelectedSuppliers = FXCollections.observableArrayList();
        try (Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                nonSelectedSuppliers.add(
                        new Suppliers(
                                rs.getInt(1),           //supplier id
                                rs.getString(2)         //supplier name
                        )
                );
            }
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Read Suppliers");
        }
        return nonSelectedSuppliers;

    }
}
