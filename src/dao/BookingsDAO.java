//package dao;
package dao;

import entity.Bookings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BookingsDAO {

    //database actions...

    public ObservableList<Bookings> getAllBookings(){

        ObservableList<Bookings> lstBookings = FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travelexperts",
                    "root",
                    "P@ssw0rd");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bookings");

            while (rs.next())
            {
                lstBookings.add(new Bookings(rs.getInt("BookingId"),
                        rs.getDate("BookingDate").toLocalDate(),
                        rs.getString("BookingNo"),
                        rs.getDouble("TravelerCount"),
                        rs.getInt("CustomerId"),
                        rs.getString("TripTypeId"),
                        rs.getInt("PackageId")));
            }
            conn.close();



        }catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }

        return lstBookings;

    }

    //save updated package
    public void updateBookings (String pkgId, Integer bkId)
    {
        String sql = "UPDATE bookings SET packageId=? WHERE bookingId=?";
        try(Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1,Integer.parseInt(pkgId));
            pstmt.setInt(2,bkId);
            pstmt.execute();
            System.out.println(Integer.toString(bkId) + " " + pkgId);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"Can't Update Package");
        }
    }



}
