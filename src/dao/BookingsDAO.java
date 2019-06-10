//package dao;
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;

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



}
