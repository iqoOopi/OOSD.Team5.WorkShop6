package dao;

import entity.DBHelper;
import entity.ProductsSuppliersViewModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static utility.Tools.infoBox;

//By Henry
public class RelatedSuppliersDAO {
    public ObservableList<ProductsSuppliersViewModule> LoadAllRelatedSuppliers(int id){
        ObservableList<ProductsSuppliersViewModule> ProductsSuppliersViewModuleList = FXCollections.observableArrayList();
        try (Connection conn = DBHelper.getConnection(); Statement stmt = conn.createStatement()) {
            String sql = "SELECT ps.SupplierId, s.SupName " +
                    "FROM Products_Suppliers ps JOIN Suppliers s ON " +
                    "ps.SupplierId = s.SupplierId" +
                    " WHERE ProductId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                ProductsSuppliersViewModuleList.add(
                        new ProductsSuppliersViewModule(
                                id,//product Id
                                rs.getInt(1),//supplier id
                                rs.getString(2))//supplier name
                );
            }
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Read Products");
        }
        return ProductsSuppliersViewModuleList;
    }
}
