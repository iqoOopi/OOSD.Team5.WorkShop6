//package controller;
package controller;

import dao.BookingsDAO;
import entity.Bookings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.BookingDetails;
import sample.BookingDetailsDAO;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;



public class BookingController {

    //controls data from both Bookings and BookingDetails table.
    String dateString;
    //for Bookings table
    @FXML
    TextField txtBookingId = null;
    @FXML
    TextField cusId=null;
    @FXML
    TextField bookingDate = null;
    @FXML
    private TableView<Bookings> tvBookings;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ListView<Bookings> lvBookings;
    @FXML
    private Label lblMessage;
    @FXML
    private TableColumn<Bookings, Integer> colBookingId;
    @FXML
    private TableColumn<Bookings, LocalDate> colBookingDate;
    @FXML
    private TableColumn<Bookings, String>colBookingNo;
    @FXML
    private TableColumn<Bookings, Double>colTravelerCount;
    @FXML
    private TableColumn<Bookings, Integer>colCusId;
    @FXML
    private TableColumn<Bookings, String>colTripTypeId;


    //for BookingDetails table
    @FXML
    private TableView<BookingDetails>tvBookingDetails;
    @FXML
    private TableColumn<BookingDetails,Integer > colBookingDetailsId;
    @FXML
    private TableColumn<BookingDetails, Double> colItineraryNo;
    @FXML
    private TableColumn<BookingDetails, LocalDate> colTripStart;
    @FXML
    private TableColumn<BookingDetails, LocalDate> colTripEnd;
    //    @FXML
//    private TableColumn<BookingDetails, String> colDescription;
//    @FXML
//    private TableColumn<BookingDetails, String> colDestination;
//    @FXML
//    private TableColumn<BookingDetails, Double> colBasePrice;
    @FXML
    private TextField agencyCommission;
//    @FXML
//    private TableColumn<BookingDetails,Integer> colBookinggId;
    @FXML
    private TableColumn<BookingDetails, String> colRegionId;
    @FXML
    private TableColumn<BookingDetails, String> colClassId;
    @FXML
    private TableColumn<BookingDetails,String> colFeeId;
    @FXML
    private TableColumn<BookingDetails, Integer> colProdSupId;


    //interactables: eg buttons,

    @FXML
    private Button btnShowDetails;

    @FXML
    private SplitPane sp;

    @FXML
    void initialize ()
    {
        fillingBookingTable();

        loadBookings();
        loadBookingDetails();
        tvBookingDetails.setVisible(false);
        sp.setDividerPositions(1);
        sideMenu.setVisible(false);
    }

    @FXML
    private  void fillingBookingTable()
    {
        colBookingId.setCellValueFactory(cellData -> cellData.getValue().bookingIdProperty().asObject());
        colBookingDate.setCellValueFactory(cellData->cellData.getValue().bookingDateProperty());
        colBookingNo.setCellValueFactory(cellData->cellData.getValue().bookingNoProperty());
        colCusId.setCellValueFactory(cellData->cellData.getValue().customerIdProperty().asObject());
        colTravelerCount.setCellValueFactory(cellData->cellData.getValue().travelerCountProperty().asObject());
        colTripTypeId.setCellValueFactory(cellData->cellData.getValue().tripTypeIdProperty());

    }

    @FXML
    private void fillingBookingDetailsTable()
    {
        colBookingDetailsId.setCellValueFactory(cellData->cellData.getValue().bookingDetailIdProperty().asObject());
        colItineraryNo.setCellValueFactory(cellData->cellData.getValue().itineraryNoProperty().asObject());
        colTripStart.setCellValueFactory(cellData->cellData.getValue().tripStartProperty());
        colTripEnd.setCellValueFactory(cellData->cellData.getValue().tripEndProperty());
//        colDescription.setCellValueFactory(cellData->cellData.getValue().descriptionProperty());
//        colDestination.setCellValueFactory(cellData->cellData.getValue().descriptionProperty());
//        colBasePrice.setCellValueFactory(cellData->cellData.getValue().basePriceProperty().asObject());
//        colAgencyCommission.setCellValueFactory(cellData->cellData.getValue().agencyCommissionProperty().asObject());
//        colBookinggId.setCellValueFactory(cellData->cellData.getValue().bookinggIdProperty().asObject());
        colRegionId.setCellValueFactory(cellData->cellData.getValue().regionIdProperty());
        colFeeId.setCellValueFactory(cellData->cellData.getValue().feeIdProperty());
        colProdSupId.setCellValueFactory(cellData->cellData.getValue().productSupplierIdProperty().asObject());
        colClassId.setCellValueFactory(cellData->cellData.getValue().classIdProperty());

    }

    private void loadBookings() {
        BookingsDAO bookingsDAO = new BookingsDAO();

        ObservableList<Bookings> bookingLst = bookingsDAO.getAllBookings();
        tvBookings.setItems(bookingLst);
    }

    private void loadBookingDetails()
    {
        BookingDetailsDAO bDetailDao = new BookingDetailsDAO();

        ObservableList<BookingDetails> bDetailsLst = bDetailDao.getAllBookingDetails();
        tvBookingDetails.setItems(bDetailsLst);
    }


    ///getting mouse row selection values
    @FXML
    public void getSelectedId() {


        tvBookings.setOnMouseClicked(new EventHandler<MouseEvent>() {
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate;
            @Override
            public void handle(MouseEvent event) {
                txtBookingId.setText(Integer.toString((tvBookings.getSelectionModel().getSelectedItem().getBookingId())));
                cusId.setText(Integer.toString((tvBookings.getSelectionModel().getSelectedItem().getCustomerId())));
                selectedDate = convertToDate(tvBookings.getSelectionModel().getSelectedItem().getBookingDate());
                dateString = dFormat.format(selectedDate);
                bookingDate.setText(dateString);
            }
        });


    }
    //date conversion
    public Date convertToDate(LocalDate lDate)
    {

        return (java.sql.Date.valueOf(lDate));
    }

    //adding funcitons to buttons

    @FXML
    private TextArea description;
    @FXML
    private TextField destination;
    @FXML
    private TextField basePrice;
    @FXML
    private Pane sideMenu;
    @FXML
    private SplitPane sideMenuContainer;

    @FXML
    public void showDetails () //on submit button click function
    {
        //show details using booking id
        sp.setDividerPositions(0.325);
        sideMenuContainer.setDividerPositions(0.25);

        sideMenu.setVisible(true);
        tvBookingDetails.setVisible(true);
        BookingDetailsDAO bkDao = new BookingDetailsDAO();
        ObservableList<BookingDetails> bDetailsLst = bkDao.getSelectedBookingDetails(Integer.parseInt(txtBookingId.getText()));

        tvBookingDetails.setItems(bDetailsLst);
        ///setting up extra agency detail
        ArrayList<String>agencyDetail =(bkDao.getAgencyDescription(Integer.parseInt(txtBookingId.getText())));
        agencyCommission.setText(agencyDetail.get(0)); //agency commission
        basePrice.setText(agencyDetail.get(1)); // agency base price
        description.setText(agencyDetail.get(2)); // description
        destination.setText(agencyDetail.get(3)); // destination
        fillingBookingDetailsTable();
    }

    @FXML
    private Button goMenu;
    @FXML
    private Button goProdSup;
    //go to main menu
    @FXML
    public void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));

    }
    //go to prodsupview
    @FXML
    public void  goToProdSup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/prodSupView.fxml"));
    }

}