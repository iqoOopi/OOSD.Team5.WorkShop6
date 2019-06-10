package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;

public class BookingDetailsDAO {


    public ObservableList<BookingDetails> getAllBookingDetails(){

        ObservableList<BookingDetails> lstBookingDetails = FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travelexperts",
                    "root",
                    "P@ssw0rd");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bookingdetails");

            while (rs.next())
            {
                lstBookingDetails.add(new BookingDetails(rs.getInt("BookingDetailId"),
                        rs.getDouble("ItineraryNo"),
                        rs.getDate("TripStart").toLocalDate(),
                        rs.getDate("TripEnd").toLocalDate(),
                        rs.getString("Description"),
                        rs.getString("Destination"),
                        rs.getDouble("BasePrice"),
                        rs.getDouble("AgencyCommission"),
                        rs.getInt("BookingId"),
                        rs.getString("RegionId"),
                        rs.getString("ClassId"),
                        rs.getString("FeeId"),
                        rs.getInt("ProductSupplierId")));
            }
            conn.close();



        }catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }

        return lstBookingDetails ;

    }




}
