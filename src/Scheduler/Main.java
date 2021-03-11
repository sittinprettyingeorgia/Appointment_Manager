package Scheduler;

import Scheduler.data.*;
import Scheduler.database.DBConnection;
import Scheduler.database.Query;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/** Main class has primary control of the program*/
public class Main extends Application {
    private static Connection conn;
    private static PreparedStatement statement;
    private static User userGlobal;

    /**
     * manages database connection start and close
     */
    public static void main(String[] args) {
        DBConnection.startConnection();
        //ObservableList<Country> countries = DBCountries.getAllCountries();
        launch(args);
        DBConnection.closeConnection();
    }

    /**
     * starts application
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        root.setStyle("-fx-border-style:solid;-fx-border-color:blue;-fx-border-width:5px");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }

    /**
     * allows user login and updates log report
     */
    public static boolean login(String user, String pass) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        boolean log = false;
        if (user != null && pass != null) {
            String sql = "Select * from users where User_Name='" + user + "'and password='" + pass + "'";
            try {
                conn = DBConnection.getConnection();
                Query.setStatement(conn, sql);
                statement = Query.getStatement();
                ResultSet res = statement.executeQuery();
                while(res.next()) {
                    int myId = res.getInt("User_ID");
                    String password = res.getString("Password");
                    String name = res.getString("User_Name");
                    LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                    String createdBy = res.getString("Created_By");
                    LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                    String lastUpdatedBy = res.getString("Last_Updated_By");
                    userGlobal = new User(myId, name, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
                    log = true;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Comm.loginResult(log);
        }
            //Write code that provides the ability to track user activity by recording all user log-in attempts,
            // dates, and time stamps and whether each attempt was successful in a file named login_activity.txt.
            // Append each new record to the existing file, and save to the root folder of the application.
        try {
           FileWriter file = new FileWriter("C:\\Users\\Shunpike\\IdeaProjects\\c195(MitchellBlake)\\logReport.txt",true);
            PrintWriter pw = new PrintWriter(file);
            ZonedDateTime zone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

            pw.println("USER: "+user);
            pw.println("Date: "+zone.format(date));
            pw.println("Time: "+zone.format(time));
            pw.println("Successful: "+log);
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return log;
    }

    public static Appointments upcomingAppointment(LocalDateTime log){
        ObservableList<Appointments> apps = appsByMonth(log.getMonthValue());
        Appointments app = null;
        for(Appointments a:apps){
            if((a.getStart().isAfter(log) || a.getStart().isEqual(log)) && a.getStart().isBefore(log.plusMinutes(15)))
                app = new Appointments(a.getId(),a.getTitle(),a.getDescription(),a.getLocation(),a.getType(),
                        a.getStart(),a.getEnd(),a.getCreateDate(),a.getCreatedBy(),a.getLastUpdate(),a.getLastUpdatedBy(),
                        a.getCustomerId(),a.getUserId(),a.getContactId());
        }
        return app;
    }
    /**
     * loads customers data
     */
    public static ObservableList<Customer> loadCustomersData() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "Select * from customers";
        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                int id = res.getInt("Customer_ID");
                String name = res.getString("Customer_Name");
                String address = res.getString("Address");
                String postal = res.getString("Postal_Code");
                String phone = res.getString("Phone");
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = res.getString("Last_Updated_By");
                int divId = res.getInt("Division_Id");
                Customer temp = new Customer(id, name, address, postal, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divId);
                firstLevelDiv div = loadDiv(divId);
                Country country = loadCountry(div.getCountryId());
                temp.setDiv(div);
                temp.setCountry(country);
                customers.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * loads appointments data
     */
    public static ObservableList<Appointments> loadAppointmentsData(int customerId) {
        ObservableList<Appointments> apps = FXCollections.observableArrayList();
        String sql = "Select * from appointments where Customer_Id='" + customerId + "'";
        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int id = res.getInt("Appointment_Id");
                String title = res.getString("Title");
                String des = res.getString("Description");
                String loc = res.getString("Location");
                String type = res.getString("Type");

                LocalDateTime start = res.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = res.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate=res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = res.getString("Last_Updated_By");
                int custId = res.getInt("Customer_Id");
                int userId = res.getInt("User_Id");
                int conId = res.getInt("Contact_Id");
                Appointments temp = new Appointments(id, title, des, loc, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, custId, userId, conId);
                apps.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apps;
    }

    /**
     * load first level divisions
     */
    public static ObservableList<firstLevelDiv> loadDivs(int countryId) {
        ObservableList<firstLevelDiv> divs = FXCollections.observableArrayList();
        String sql = "Select * from first_level_divisions where Country_ID=" + countryId;
        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int id = res.getInt("Division_ID");
                String name = res.getString("Division");
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = res.getString("Last_Updated_By");
                firstLevelDiv temp = new firstLevelDiv(id, name, createDate, createdBy, lastUpdate, lastUpdatedBy, countryId);
                divs.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*if(divs==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error Alert");
            String s ="You have not selected a first level division or country.";
            alert.setContentText(s);
            alert.show();
        }*/
        return divs;
    }

    /**
     * load a first level division
     */
    public static firstLevelDiv loadDiv(int divId) {
        firstLevelDiv div = null;
        String sql = "Select * from first_level_divisions where Division_ID=" + divId;
        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while(res.next()) {
                int id = res.getInt("Division_ID");
                String name = res.getString("Division");
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = res.getString("Last_Updated_By");
                int countryId = res.getInt("Country_ID");
                div = new firstLevelDiv(id, name, createDate, createdBy, lastUpdate, lastUpdatedBy, countryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return div;
    }

    /**
     * load Countries
     */
    public static ObservableList<Country> loadCountries() {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String sql = "Select * from countries";
        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int id = res.getInt("Country_ID");
                String name = res.getString("Country");
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdateBy = res.getString("Last_Updated_By");
                Country temp = new Country(id, name, createDate, createdBy, lastUpdate, lastUpdateBy);
                countries.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    /**
     * load a country
     */
    public static Country loadCountry(int countryId) {
        Country country = null;
        Connection conn = DBConnection.getConnection();
        String sql = "Select * from countries where Country_ID=" + countryId;
        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while(res.next()) {
                int id = res.getInt("Country_ID");
                String name = res.getString("Country");
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdateBy = res.getString("Last_Updated_By");
                country = new Country(id, name, createDate, createdBy, lastUpdate, lastUpdateBy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    /**
     * adds customer to database
     */
    public static boolean addCustomer(Customer customer) {
        boolean log = false;
        String sql = "Insert into customers Values(" + null + ",'" + customer.getName() + "', '" + customer.getAddress() + "', '"
                + customer.getPostalCode() + "', '" + customer.getPhone() + "', ?, '" +
                customer.getCreatedBy() + "', ?, '" + customer.getLastUpdatedBy() + "', " +
                customer.getDivisionId() + ")";
        ZonedDateTime temp = customer.getCreateDate().atZone(ZoneId.systemDefault());
        ZonedDateTime createDate = temp.withZoneSameInstant(ZoneId.of("UTC"));
        temp = customer.getLastUpdate().atZone(ZoneId.systemDefault());
        ZonedDateTime lastUpdate = temp.withZoneSameInstant(ZoneId.of("UTC"));

        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            statement.setTimestamp(1,Timestamp.from(createDate.toInstant()));
            statement.setTimestamp(2,Timestamp.from(lastUpdate.toInstant()));
            boolean res = statement.execute();
            int count = statement.getUpdateCount();
            if (count > 0) log = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return log;
    }

    /**
     * updates a customer
     */
    public static boolean updateCustomer(Customer customer) {
        boolean log = false;
        String sql = "update customers set Customer_Name='" + customer.getName() + "', Address= '" + customer.getAddress() + "', Postal_Code= '"
                + customer.getPostalCode() + "', Phone = '" + customer.getPhone() + "', Create_Date= ?,  Created_By ='" +
                customer.getCreatedBy() + "', Last_Update= ?, Last_Updated_By= '" + customer.getLastUpdatedBy() +
                "', Division_ID=" + customer.getDivisionId() + " where Customer_ID=" + customer.getId();

        ZonedDateTime temp = customer.getCreateDate().atZone(ZoneId.systemDefault());
        ZonedDateTime createDate = temp.withZoneSameInstant(ZoneId.of("UTC"));
        temp = customer.getLastUpdate().atZone(ZoneId.systemDefault());
        ZonedDateTime lastUpdate = temp.withZoneSameInstant(ZoneId.of("UTC"));
        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            statement.setTimestamp(1,Timestamp.from(createDate.toInstant()));
            statement.setTimestamp(2,Timestamp.from(lastUpdate.toInstant()));
            boolean res = statement.execute();
            int count = statement.getUpdateCount();
            if (count > 0) log = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return log;
    }

    /**
     * deletes customer
     */
    public static boolean deleteCustomer(Customer customer) {
        boolean log = false;
        if (deleteAllAppointments(customer)) {
            String sql = "delete from customers where Customer_ID=" + customer.getId();
            try {
                conn = DBConnection.getConnection();
                Query.setStatement(conn, sql);
                statement = Query.getStatement();
                boolean res = statement.execute();
                int count = statement.getUpdateCount();
                if (count > 0) log = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return log;
    }

    /**
     * delete appointment
     */
    public static boolean deleteAppointment(Appointments app) {
        boolean log = false;
        try{
        String sql = "delete from appointments where Appointment_ID=" + app.getId();

            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            boolean res = statement.execute();
            int count = statement.getUpdateCount();
            if (count > 0) log = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return log;
    }

    /**
     * deletes all appointments for a customer
     */
    public static boolean deleteAllAppointments(Customer customer) {
        boolean log = true;
        String sql = "delete from appointments where Customer_ID=" + customer.getId();
        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            boolean res = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            log = false;
        }

        return log;
    }

    /**
     * updates an appointment
     */
    public static boolean updateAppointment(Appointments app) {
        boolean log = false;
        String sql = "update appointments set Title='" + app.getTitle() + "', Description= '" + app.getDescription() + "', Location= '"
                + app.getLocation() + "', Type= '" + app.getType() + "', Start=?,  End=?, Create_Date=?, Created_By= '" + app.getCreatedBy() +
                "', Last_Update=?, Last_Updated_By='" + app.getLastUpdatedBy() + "', Customer_ID="
                + app.getCustomerId() + ", User_ID=" + app.getUserId() + ", Contact_ID=" + app.getContactId() + " where Appointment_ID="
                + app.getId();

        ZonedDateTime temp = app.getStart().atZone(ZoneId.systemDefault());
        ZonedDateTime start = temp.withZoneSameInstant(ZoneId.of("UTC"));
        temp = app.getEnd().atZone(ZoneId.systemDefault());
        ZonedDateTime end = temp.withZoneSameInstant(ZoneId.of("UTC"));
        temp = app.getCreateDate().atZone(ZoneId.systemDefault());
        ZonedDateTime createDate = temp.withZoneSameInstant(ZoneId.of("UTC"));
        temp = app.getLastUpdate().atZone(ZoneId.systemDefault());
        ZonedDateTime lastUpdate = temp.withZoneSameInstant(ZoneId.of("UTC"));


        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            statement.setTimestamp(1, Timestamp.from(start.toInstant()));
            statement.setTimestamp(2, Timestamp.from(end.toInstant()));
            statement.setTimestamp(3, Timestamp.from(createDate.toInstant()));
            statement.setTimestamp(4, Timestamp.from(lastUpdate.toInstant()));
            boolean res = statement.execute();
            int count = statement.getUpdateCount();
            if (count > 0) log = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return log;
    }

    /**
     * adds appointment
     */
    public static boolean addAppointment(Appointments app) {
        boolean log = false;

        String sql = "Insert into appointments Values(" + null + ", '" + app.getTitle() + "', '" + app.getDescription() + "', '"
                + app.getLocation() + "', '" + app.getType() + "', ?, ?, ?, '" + app.getCreatedBy() + "', ?, '" +
                app.getLastUpdatedBy() + "', " + app.getCustomerId() + ", " + app.getUserId() + ", " + app.getContactId() + ")";

        ZonedDateTime temp = app.getStart().atZone(ZoneId.systemDefault());
        ZonedDateTime start = temp.withZoneSameInstant(ZoneId.of("UTC"));
        temp = app.getEnd().atZone(ZoneId.systemDefault());
        ZonedDateTime end = temp.withZoneSameInstant(ZoneId.of("UTC"));
        temp = app.getCreateDate().atZone(ZoneId.systemDefault());
        ZonedDateTime createDate = temp.withZoneSameInstant(ZoneId.of("UTC"));
        temp = app.getLastUpdate().atZone(ZoneId.systemDefault());
        ZonedDateTime lastUpdate = temp.withZoneSameInstant(ZoneId.of("UTC"));

        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            statement.setTimestamp(1, Timestamp.from(start.toInstant()));
            statement.setTimestamp(2, Timestamp.from(end.toInstant()));
            statement.setTimestamp(3, Timestamp.from(createDate.toInstant()));
            statement.setTimestamp(4, Timestamp.from(lastUpdate.toInstant()));
            boolean res = statement.execute();
            int count = statement.getUpdateCount();
            if (count > 0) log = true;
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return log;
    }

    /**
     * gets current user
     */
    public static User getUser() {
        return userGlobal;
    }

    /**
     * checks if user exists
     */
    public static boolean checkUser(int id) {
        boolean result = false;
        String sql = "select * from users where User_ID=" + id;
        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                result=true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * checks if customer exists
     */
    public static Customer checkCustomer(int id) {
        Customer customer = null;
        String sql = "select * from customers where Customer_ID=" + id;
        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String name = res.getString("Customer_Name");
                String address = res.getString("Address");
                String postal = res.getString("Postal_Code");
                String phone = res.getString("Phone");
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = res.getString("Last_Updated_By");
                int divId = res.getInt("Division_Id");
                customer = new Customer(id, name, address, postal, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    /**
     * gets contacts
     */
    public static ObservableList<Contact> loadContacts() {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String sql = "Select * from contacts";
        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int id = res.getInt("Contact_ID");
                String name = res.getString("Contact_Name");
                String email = res.getString("Email");
                Contact contact = new Contact(id, name, email);
                contacts.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    /**
     * load a specific contact
     */
    public static Contact loadContact(int id) {
        Contact contact = null;
        Connection conn = DBConnection.getConnection();
        String sql = "Select * from contacts where Contact_ID=" + id;
        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String name = res.getString("Contact_Name");
                String email = res.getString("Email");
                contact = new Contact(id, name, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    /**
     * gets appointments by month
     */
    public static ObservableList<Appointments> appsByMonth(int month) {
        ObservableList<Appointments> apps = FXCollections.observableArrayList();
        String sql = "select * from appointments where month(Start)=" + month;
        Connection conn = DBConnection.getConnection();
        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int id = res.getInt("Appointment_Id");
                String title = res.getString("Title");
                String des = res.getString("Description");
                String loc = res.getString("Location");
                String type = res.getString("Type");
                LocalDateTime start = res.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = res.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = res.getString("Last_Updated_By");
                int custId = res.getInt("Customer_Id");
                int userId = res.getInt("User_Id");
                int conId = res.getInt("Contact_Id");
                Appointments temp = new Appointments(id, title, des, loc, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, custId, userId, conId);
                apps.add(temp);
            }
        } catch (SQLException r) {
            r.printStackTrace();
        }

        return apps;
    }
    /** check if there are overlap between appointments before adding/updating appointment*/
    public static boolean chkOverlap(Appointments app){
        boolean result=false;
        ObservableList<Appointments> apps = FXCollections.observableArrayList();
        String sql ="select * from appointments where month(Start)="+app.getStart().getMonthValue();
        Connection conn = DBConnection.getConnection();
        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int id = res.getInt("Appointment_Id");
                String title = res.getString("Title");
                String des = res.getString("Description");
                String loc = res.getString("Location");
                String type = res.getString("Type");
                LocalDateTime start = res.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = res.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = res.getString("Last_Updated_By");
                int custId = res.getInt("Customer_Id");
                int userId = res.getInt("User_Id");
                int conId = res.getInt("Contact_Id");
                Appointments temp = new Appointments(id, title, des, loc, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, custId, userId, conId);
                apps.add(temp);
            }
        } catch (SQLException r) {
            r.printStackTrace();
        }
        LocalDateTime chkStart = app.getStart();
        LocalDateTime chkEnd = app.getEnd();
        for(Appointments a:apps){
            if((chkStart.isBefore(a.getEnd()) || chkStart.isEqual(a.getEnd())) && ((a.getStart().isBefore(chkEnd)) ||
                    a.getStart().isEqual(chkEnd))) result=true;
        }
        return result;
    }

    /** 1st Lambda function within generate reports method:
     * apps.compute
     *  This lambda in combination with the hashmap allows easier assignment of appointmets based
     *  on month and quick access when generating reports.
     *
     * 2nd lambda function: contactApps lambda
     * this lambda function uses a comparator to sort the appointments which are already grouped by
     * contact id in chronological order. allows quick access and makes code easy to understand.
     */
    public static ArrayList<String> generateReports(){
        ArrayList<String> results = new ArrayList<>();
        HashMap<Integer, ArrayList<Appointments>> appsByMonth = new HashMap<>();
        HashMap<String, ArrayList<Appointments>> appsType = new HashMap<>();
        HashMap<Integer, ArrayList<Appointments>> contactApps = new HashMap<>();
        String sql = "Select * from appointments";
        Connection conn = DBConnection.getConnection();

        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int id = res.getInt("Appointment_Id");
                String title = res.getString("Title");
                String des = res.getString("Description");
                String loc = res.getString("Location");
                String type = res.getString("Type");
                LocalDateTime start = res.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = res.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = res.getString("Last_Updated_By");
                int custId = res.getInt("Customer_Id");
                int userId = res.getInt("User_Id");
                int conId = res.getInt("Contact_Id");
                Appointments temp = new Appointments(id, title, des, loc, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, custId, userId, conId);

                appsByMonth.compute(temp.getStart().getMonthValue(),(k,v)->(v==null)?new ArrayList<>():v).add(temp);
                appsType.compute(temp.getType(),(k,v)->(v==null)?new ArrayList<>():v).add(temp);
                contactApps.compute(temp.getContactId(),(k,v)->(v==null)?new ArrayList<>():v).add(temp);

            }
        } catch (SQLException r) {
            r.printStackTrace();
        }
        //display appointment amounts by type and month
        results.add("Report 1:::::::::::::::::::::::::::::::::::\n");
        appsType.forEach((k,v)-> {results.add("Appointments by Type:"+k+" = "+v.size()); });
        String[] months= new String[12];
        months[0] = "january";
        months[1] = "february";
        months[2] = "march";
        months[3] = "april";
        months[4] = "may";
        months[5] = "june";
        months[6] = "july";
        months[7] = "august";
        months[8] = "september";
        months[9] = "october";
        months[10] = "november";
        months[11] = "december";
        results.add("\n");
        for(int i=1;i<months.length;i++){
            if(appsByMonth.containsKey(i)) {
                results.add("Appointments by Month: " + months[i-1]+ "= "+appsByMonth.get(i).size());
            }
            else{
                results.add("Appointments by Month: "+ months[i-1]+"= 0");
            }

        }
        results.add("\n");
        //display customers created in current month
        HashMap<Integer, Integer> customers = new HashMap<>();
        sql="select * from customers";
        try {
            conn = DBConnection.getConnection();
            Query.setStatement(conn, sql);
            statement = Query.getStatement();
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                int id = res.getInt("Customer_ID");
                String name = res.getString("Customer_Name");
                String address = res.getString("Address");
                String postal = res.getString("Postal_Code");
                String phone = res.getString("Phone");
                LocalDateTime createDate = res.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = res.getString("Created_By");
                LocalDateTime lastUpdate = res.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = res.getString("Last_Updated_By");
                int divId = res.getInt("Division_Id");
                Customer temp = new Customer(id, name, address, postal, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divId);
                customers.compute(temp.getCreateDate().getMonthValue(),(k,v)-> (v==null)?1:v+1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //a schedule for each contact in your organization that includes appointment ID, title,
        // type and description, start date and time, end date and time, and customer ID
        sql="select * from contacts";
        HashMap<Integer, ArrayList<Contact>> contacts = new HashMap<>();
        try {
            Query.setStatement(conn, sql);
            PreparedStatement statement = Query.getStatement();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int id = res.getInt("Contact_ID");
                String name = res.getString("Contact_Name");
                String email = res.getString("Email");
                Contact contact = new Contact(id,name,email);
                contacts.compute(contact.getId(),(k,v)->(v==null)?new ArrayList<>():v).add(contact);
            }
        } catch (SQLException r) {
            r.printStackTrace();
        }
        /** 2nd lambda function: contactApps.forEach((k,v)->v.sort(Comparator.comparing(Appointments::getStart)));
         * this lambda function uses a comparator to sort the appointments which are already grouped by
         * contact id in chronological order.
         */
        contactApps.forEach((k,v)->v.sort(Comparator.comparing(Appointments::getStart)));
        results.add("Report 2::::::::::::::::::::::::::::::::::::::::::\n");
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        contactApps.forEach((k,v)-> {
                    results.add("\n\nAppointments by Contact: " + contacts.get(k).get(0).getName());
                    v.forEach((a) -> {
                        results.add("\nAppointment ID:" + a.getId());
                        results.add("Appointment Title:" + a.getTitle());
                        results.add("Appointment Type:" + a.getType());
                        results.add("Appointment Description:" + a.getDescription());
                        results.add("Appointment Start:" + a.getStart().format(date));
                        results.add("Appointment End:" + a.getStart().format(date));
                        results.add("Customer ID:" + a.getCustomerId());
                    });
            }
        );
        results.add("\n\nReport 3::::::::::::::::::::::::::::::::::::::::::::::::");
        if(customers.containsKey(LocalDateTime.now().getMonthValue())){
            results.add("\nCustomers created this month="+customers.get(LocalDateTime.now().getMonthValue()));
        }
        else {
            results.add("\nCustomers created this month=0");
        }

        return results;
    }
}
