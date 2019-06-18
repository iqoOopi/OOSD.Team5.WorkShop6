package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;

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

    public ObservableList<BookingDetails> getSelectedBookingDetails(Integer id ){

        ObservableList<BookingDetails> lstBookingDetails = FXCollections.observableArrayList();
        String sql = "SELECT * FROM bookingdetails WHERE BookingId=?";
        try(Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

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




        }catch(SQLException  e) {
            e.printStackTrace();

        }

        return lstBookingDetails ;

    }


    public ArrayList<String> getAgencyDescription (int id)
    {
        ArrayList<Double> dCommission = new ArrayList<Double>();
        ArrayList<Double> dBasePrice = new ArrayList<Double>();
        ArrayList<String> sDescription = new ArrayList<String>();
        ArrayList<String> sDestination = new ArrayList<String>();


        //ObservableList<BookingDetails> lstBookingDetails = FXCollections.observableArrayList();
        String sql = "SELECT * FROM bookingdetails WHERE BookingId=?";
        try(Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
//                lstBookingDetails.add(new BookingDetails(rs.getInt("BookingDetailId"),
//                        rs.getDouble("ItineraryNo"),
//                        rs.getDate("TripStart").toLocalDate(),
//                        rs.getDate("TripEnd").toLocalDate(),
//                        rs.getString("Description"),
//                        rs.getString("Destination"),
//                        rs.getDouble("BasePrice"),
//                        rs.getDouble("AgencyCommission"),
//                        rs.getInt("BookingId"),
//                        rs.getString("RegionId"),
//                        rs.getString("ClassId"),
//                        rs.getString("FeeId"),
//                        rs.getInt("ProductSupplierId")));
                dCommission.add((rs.getDouble("AgencyCommission")));
                dBasePrice.add(rs.getDouble("BasePrice"));
                sDescription.add(rs.getString("Description"));
                sDestination.add(rs.getString("Destination"));

            }




        }catch(SQLException  e) {
            e.printStackTrace();

        }

        String aCommission = dCommission.get(0).toString();
        String aBasePrice = dBasePrice.get(0).toString();
        String aDescription = sDescription.get(0);
        String aDestination = sDestination.get(0);

        ArrayList<String> agencyDetail = new ArrayList<String>();
        agencyDetail.add(aCommission);
        agencyDetail.add(aBasePrice);
        agencyDetail.add(aDescription);
        agencyDetail.add(aDestination);
        return agencyDetail;

    }


}
