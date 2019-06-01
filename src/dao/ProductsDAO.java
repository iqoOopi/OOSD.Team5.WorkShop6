package dao;

import entity.DBHelper;
import entity.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static utility.Tools.infoBox;

//By Henry, DAO Class for Products
public class ProductsDAO {
    public ObservableList<Products> LoadAllProducts() {
        ObservableList<Products> productsList = FXCollections.observableArrayList();
        try (Connection conn = DBHelper.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT ProductId, ProdName FROM products");
            while (rs.next())
            {
                productsList.add(
                        new Products(
                                rs.getInt(1),
                                rs.getString(2))
                );
            }
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Read Products");
        }
        return productsList;
    }


}
