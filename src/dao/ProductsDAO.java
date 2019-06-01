package dao;

import entity.DBHelper;
import entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static utility.Tools.infoBox;

//By Henry, DAO Class for Products
public class ProductsDAO {
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

    public void DeleteProductById(int id){
        String sql = "DELETE FROM Products WHERE ProductId = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.execute();
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Delete Product");
        }
    }

    public void SaveNewProduct(Product newProd){
        String sql = "INSERT INTO Products (ProdName) VALUES (?)";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,newProd.getProdName());
            pstmt.execute();
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Save Product");
        }

    }

    public void UpdateProduct (Product editedProd){
        String sql = "UPDATE Products SET ProdName=? WHERE ProductId = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,editedProd.getProdName());
            pstmt.setInt(2,editedProd.getProductId());
            pstmt.execute();
        }catch(SQLException e){
            infoBox(e.getMessage(),"Can't Update Product");
        }
    }


}
