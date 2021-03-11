package Scheduler.landing;

import Scheduler.Main;
import Scheduler.data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class landingController implements Initializable {
    //Customer table view
    @FXML private TableView<Customer> customersView;
    @FXML private TableColumn<Customer, Integer> customerId;
    @FXML private TableColumn<Customer, String> customerName;
    @FXML private TableColumn<Customer, String> customerAddress;
    @FXML private TableColumn<Customer, String> customerPostal;
    @FXML private TableColumn<Customer, String> customerPhone;
    @FXML private TableColumn<Customer, LocalDateTime> customerCreateDate;
    @FXML private TableColumn<Customer, String> customerCreatedBy;
    @FXML private TableColumn<Customer, LocalDateTime> customerLastUpdate;
    @FXML private TableColumn<Customer, String> customerLastUpdatedBy;
    @FXML private TableColumn<Customer, Integer> customerDivisionId;
    @FXML private TableColumn<Customer, firstLevelDiv> customerDivision;
    @FXML private TableColumn<Customer, Country> customerCountry;


    //appointments table view
    @FXML private TableView<Appointments> appointmentsView;
    @FXML private TableColumn<Appointments, Integer> appId;
    @FXML private TableColumn<Appointments, String> appTitle;
    @FXML private TableColumn<Appointments, String> appDescription;
    @FXML private TableColumn<Appointments, String> appLocation;
    @FXML private TableColumn<Appointments, String> appType;
    @FXML private TableColumn<Appointments, LocalDateTime> appStart;
    @FXML private TableColumn<Appointments, LocalDateTime> appEnd;
    @FXML private TableColumn<Appointments, Integer> appCustId;
    @FXML private TableColumn<Appointments, Integer> appContactId;

    //buttons
    @FXML private Button updateCustomer;
    @FXML private Button deleteCustomer;
    @FXML private Button updateApp;
    @FXML private Button deleteApp;
    @FXML private Button reports;
    @FXML private Button exit;
    @FXML private Button save;
    @FXML private Button cancel;

    //RadioButtons
    @FXML private RadioButton january;
    @FXML private RadioButton february;
    @FXML private RadioButton march;
    @FXML private RadioButton april;
    @FXML private RadioButton may;
    @FXML private RadioButton june;
    @FXML private RadioButton july;
    @FXML private RadioButton august;
    @FXML private RadioButton september;
    @FXML private RadioButton october;
    @FXML private RadioButton november;
    @FXML private RadioButton december;
    @FXML private RadioButton week1;
    @FXML private RadioButton week2;
    @FXML private RadioButton week3;
    @FXML private RadioButton week4;
    @FXML private RadioButton week5;
    @FXML private RadioButton week6;

    //textfields
    @FXML private TextField cusId;
    @FXML private TextField cusName;
    @FXML private TextField cusAddress;
    @FXML private TextField cusPostal;
    @FXML private TextField cusPhone;
    @FXML private TextField aTitle;
    @FXML private TextField aDescription;
    @FXML private TextField aLocation;
    @FXML private TextField aType;
    @FXML private TextField aStart;
    @FXML private TextField aEnd;
    @FXML private TextField aCusId;
    @FXML private TextField aUserId;
    @FXML private TextField aId;

    @FXML private ComboBox<Contact> contacts;
    @FXML private ComboBox<firstLevelDiv> divs;
    @FXML private ComboBox<Country> countries;
    private ToggleGroup months;
    private ToggleGroup weeks;
    private int kind =0;
    ArrayList<RadioButton> monthsList = new ArrayList<>();
    ArrayList<RadioButton> weeksList=new ArrayList<>();
    /**initialize form*/
    @Override
    public void initialize(URL url, ResourceBundle rb){
        months=new ToggleGroup();
        weeks=new ToggleGroup();
        monthsList.add(january);
        monthsList.add(february);
        monthsList.add(march);
        monthsList.add(april);
        monthsList.add(may);
        monthsList.add(june);
        monthsList.add(july);
        monthsList.add(august);
        monthsList.add(september);
        monthsList.add(october);
        monthsList.add(november);
        monthsList.add(december);
        january.setToggleGroup(months);
        february.setToggleGroup(months);
        march.setToggleGroup(months);
        april.setToggleGroup(months);
        may.setToggleGroup(months);
        june.setToggleGroup(months);
        july.setToggleGroup(months);
        august.setToggleGroup(months);
        september.setToggleGroup(months);
        october.setToggleGroup(months);
        november.setToggleGroup(months);
        december.setToggleGroup(months);
        weeksList.add(week1);
        weeksList.add(week2);
        weeksList.add(week3);
        weeksList.add(week4);
        weeksList.add(week5);
        weeksList.add(week6);
        week1.setToggleGroup(weeks);
        week2.setToggleGroup(weeks);
        week3.setToggleGroup(weeks);
        week4.setToggleGroup(weeks);
        week5.setToggleGroup(weeks);
        week6.setToggleGroup(weeks);


        cusId.setDisable(true);
        aId.setDisable(true);
        //customer view loading
        customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        customerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        customerPostal.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        customerCreateDate.setCellValueFactory(new PropertyValueFactory<Customer, LocalDateTime>("createDate"));
        customerCreatedBy.setCellValueFactory(new PropertyValueFactory<Customer, String>("createdBy"));
        customerLastUpdate.setCellValueFactory(new PropertyValueFactory<Customer, LocalDateTime>("lastUpdate"));
        customerLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastUpdatedBy"));
        customerDivisionId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("divisionId"));
        customerDivision.setCellValueFactory(new PropertyValueFactory<Customer, firstLevelDiv>("div"));
        customerCountry.setCellValueFactory(new PropertyValueFactory<Customer, Country>("country"));
        customerId.getCellValueFactory();

        appId.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("id"));
        appTitle.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
        appLocation.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
        appDescription.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
        appType.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
        appStart.setCellValueFactory(new PropertyValueFactory<Appointments, LocalDateTime>("start"));
        appEnd.setCellValueFactory(new PropertyValueFactory<Appointments, LocalDateTime>("end"));
        appCustId.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerId"));
        appContactId.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("contactId"));

        customersView.setItems(loadData());
        /**lambda expression: located "customersView.getSelectionModel().selectedItemProperty().addListener"
         * I used a lambda expression within the addListener property event to make the code more readable
         * and easier to manage. The lambda takes three values(observable,oldVal,newVal) and changes the
         * displayed appointments based on the customer selected.
         */
        customersView.getSelectionModel().selectedItemProperty().addListener((obs,old,newV)->
                appointmentsView.setItems(loadAppointmentsData(newV.getId()))
                );
        ObservableList<Country> countriesRes = Main.loadCountries();
        countries.setItems(countriesRes);
        countries.getSelectionModel().selectedItemProperty().addListener((obs,old,newV)->
                divs.setItems(Main.loadDivs(newV.getId()))
        );
        contacts.setItems(loadContactsData());


    }
    /**loads customer data for tableview*/
    public ObservableList<Customer> loadData(){
        return Main.loadCustomersData();
    }
    /**loads appointment data for tableview*/
    public ObservableList<Appointments> loadAppointmentsData(int id){
        return Main.loadAppointmentsData(id);
    }
    /**load contacts data */
    public ObservableList<Contact> loadContactsData(){
        return Main.loadContacts();
    }
    /**updates customer info*/
    public void updateCustomer(ActionEvent event) {
        Customer customer = customersView.getSelectionModel().getSelectedItem();
        cusId.setText(String.valueOf(customer.getId()));
        cusName.setText(customer.getName());
        cusPhone.setText(customer.getPhone());
        cusPostal.setText(customer.getPostalCode());
        cusAddress.setText(customer.getAddress());
        firstLevelDiv div = Main.loadDiv(customer.getDivisionId());
        divs.getSelectionModel().select(div);
        Country country = Main.loadCountry(div.getCountryId());
        countries.getSelectionModel().select(country);
        disableAppFields();
        kind=1;
    }
    /**deletes a customer*/
    public void deleteCustomer(ActionEvent event){
        ObservableList<Customer> customers = customersView.getItems();
        Customer customer = customersView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Main.deleteCustomer(customer)){
            customers.remove(customer);
            alert.setHeaderText("Confirmation Alert");
            String s ="Customer deleted successfully";
            alert.setContentText(s);
        }
        else{
            alert.setHeaderText("Error Alert");
            String s ="Customer delete failed.";
            alert.setContentText(s);
        }
        alert.show();
    }
    /**adds customer to database*/
    public void addCustomer(){
        Integer divId=null;
        String nameC = cusName.getText();
        String addressC = cusAddress.getText();
        String postalC = cusPostal.getText();
        String phoneC = cusPhone.getText();
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = Main.getUser().getName();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdatedBy = Main.getUser().getName();
        try {
            divId = divs.getSelectionModel().getSelectedItem().getId();
        }catch(Exception e){
            //ignore
        }
        if(nameC.isEmpty() && addressC.isEmpty() && postalC.isEmpty() && phoneC.isEmpty() && divId==null){
            return;
        } else if (nameC.isEmpty() || addressC.isEmpty() || postalC.isEmpty() || phoneC.isEmpty() || divId==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error Alert");
            String s ="All customer fields must either be entered or empty.";
            alert.setContentText(s);
            alert.show();
            return;

        }
        Customer customer = new Customer(null,nameC,addressC,postalC,phoneC,createDate,createdBy,lastUpdate,lastUpdatedBy,divId);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Main.addCustomer(customer)){//TODO generate new customer id in tableview
            ObservableList<Customer> customers = customersView.getItems();
            customersView.getSelectionModel().selectAll();
            customers.removeAll(customersView.getSelectionModel().getSelectedItems());
            customers.setAll(Main.loadCustomersData());
            alert.setHeaderText("Confirmation Alert");
            String s ="Customer added successfully";
            alert.setContentText(s);
            clearAllFields();
        }
        else{
            alert.setHeaderText("Error Alert");
            String s ="Customer add failed.";
            alert.setContentText(s);
        }
        alert.show();

    }
    /**updates appointment*/
    public void updateAppointment(ActionEvent event){
        if(appointmentsView.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error Alert");
            String s ="Select an appointment to update.";
            alert.setContentText(s);
            alert.show();
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Appointments app = appointmentsView.getSelectionModel().getSelectedItem();
        aId.setText(String.valueOf(app.getId()));
        contacts.getSelectionModel().select(Main.loadContact(app.getContactId()));
        aTitle.setText(app.getTitle());
        aDescription.setText(app.getDescription());
        aLocation.setText(app.getLocation());
        aType.setText(app.getType());
        aStart.setText(app.getStart().format(formatter));
        aEnd.setText(app.getEnd().format(formatter));
        System.out.println(app.getEnd());
        aCusId.setText(String.valueOf(app.getCustomerId()));
        aUserId.setText(String.valueOf(app.getUserId()));
        kind=1;
        disableCustFields();

    }
    /**deletes an appointment*/
    public void deleteAppointment(ActionEvent event){
        ObservableList<Appointments> appointments = appointmentsView.getItems();
        Appointments app = appointmentsView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Main.deleteAppointment(app)){
            appointments.remove(app);
            appointmentsView.getSelectionModel().clearSelection();
            alert.setHeaderText("Confirmation Alert");
            String s ="Appointment ID:"+app.getId()+" Type:"+app.getType()+" has successfully been canceled.";
            alert.setContentText(s);
        }
        else{
            alert.setHeaderText("Error Alert");
            String s ="Appointment delete failed.";
            alert.setContentText(s);
        }
        alert.show();
    }
    /**adds appointment to customer*/
    public void addAppointment(){//TODO check against est time zone for business hours
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Integer id=null;
        Integer userId=null;
        Integer customerId=null;
        Integer contactId=null;
        LocalDateTime start=null;
        LocalDateTime end=null;
        boolean IdException = false;
        if(!aId.getText().isEmpty())id=Integer.parseInt(aId.getText());
        try {
            userId = Integer.parseInt(aUserId.getText());
            contactId = contacts.getSelectionModel().getSelectedItem().getId();
            if(!Main.checkUser(userId)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Error Alert");
                String s ="You have entered a user id that does not exist.";
                alert.setContentText(s);
                alert.show();
                return;
            }
        }catch(Exception e){
            IdException=true;
        }
        String title = aTitle.getText();
        String description = aDescription.getText();
        String location = aLocation.getText();
        String type = aType.getText();
        try{
            boolean isCustomer = false;//TODO modify for correct zone time conversions

            start=LocalDateTime.parse(aStart.getText(),formatter).atZone(ZoneId.systemDefault()).toLocalDateTime();
            end =LocalDateTime.parse(aEnd.getText(),formatter).atZone(ZoneId.systemDefault()).toLocalDateTime();
            System.out.println(end.toString()+"adding time");
            customerId = Integer.parseInt(aCusId.getText());
            ObservableList<Customer> customers = customersView.getItems();
            for(Customer c:customers){
                if(c.getId()==customerId)isCustomer=true;
            }
            if(!isCustomer){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Error Alert");
                String s ="You cannot add an appointment for a customer that doesn't exist.";
                alert.setContentText(s);
                alert.show();
                return;
            }

            ZonedDateTime chkStart = start.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
            ZonedDateTime chkEnd = end.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
            if((chkStart.getHour()<8 || chkEnd.getHour()<8) || (chkStart.getHour() >=22 || chkEnd.getHour()>22)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Error Alert");
                String s ="You have entered an appointment outside of business hours. business hours are 8:00a.m. to 10:00p.m. (EST)";
                alert.setContentText(s);
                alert.show();
                return;
            }

        } catch(Exception e){
        }
        if(title.isEmpty() && description.isEmpty() && location.isEmpty() && type.isEmpty() && contactId==null
        && start==null && end==null && customerId==null && userId==null){
            return;
        }else if(title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty() || contactId==null ||
            start==null || end==null || customerId==null || userId==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(start==null || end==null){

                alert.setHeaderText("Error Alert");
                String s ="Please check the format of your start/end times.";
                alert.setContentText(s);
                alert.show();
                return;
            }
            else if(IdException){
                alert.setHeaderText("Error Alert");
                String s ="Please input integer values for the user and contact fields.";
                alert.setContentText(s);
                alert.show();
                return;
            }
            alert.setHeaderText("Error Alert");
            String s ="All appointment fields must either be entered or empty.";
            alert.setContentText(s);
            alert.show();
            return;
        }
            Appointments app = new Appointments(id, title, description, location, type, start, end, LocalDateTime.now(), Main.getUser().getName(),
                    LocalDateTime.now(), Main.getUser().getName(), customerId, userId, contactId);
                System.out.println(app.getEnd().toString());
            try{
                boolean isOverlap = Main.chkOverlap(app);
                System.out.println("trying overlap method");
                if(isOverlap){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Error Alert");
                    String s ="That appointment is not available. please choose a different time.";
                    alert.setContentText(s);
                    alert.show();
                    return;
                }
            }catch(Exception e){
                //ignore
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (Main.addAppointment(app)) {
                ObservableList<Appointments> apps = appointmentsView.getItems();
                appointmentsView.getSelectionModel().selectAll();
                apps.removeAll(appointmentsView.getSelectionModel().getSelectedItems());
                apps.setAll(Main.loadAppointmentsData(app.getCustomerId()));
                alert.setHeaderText("Confirmation Alert");
                String s = "Appointment added successfully";
                alert.setContentText(s);
                clearAllFields();
            }else{
                alert.setHeaderText("Confirmation Alert");
                String s = "Appointment add failed.";
                alert.setContentText(s);
            }
            alert.show();


    }
    /**updates database with appropriate info for appointment or customer
     * also adds new customers/appointments
     * */
    public void saveUpdate(ActionEvent event){
        if(updateApp.isDisable()){
           if(kind==1){
               int id = Integer.parseInt(cusId.getText());
               Integer divId=null;
               String nameC = cusName.getText();
               String addressC = cusAddress.getText();
               String postalC = cusPostal.getText();
               String phoneC = cusPhone.getText();
               LocalDateTime createDate = LocalDateTime.now();
               String createdBy = Main.getUser().getName();
               LocalDateTime lastUpdate = LocalDateTime.now();
               String lastUpdatedBy = Main.getUser().getName();
               try {
                   divId = divs.getSelectionModel().getSelectedItem().getId();
               }catch(Exception e){
                   //ignore
               }
               if(nameC.isEmpty() && addressC.isEmpty() && postalC.isEmpty() && phoneC.isEmpty() && divId==null){
                   return;
               } else if (nameC.isEmpty() || addressC.isEmpty() || postalC.isEmpty() || phoneC.isEmpty() || divId==null) {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText("Error Alert");
                   String s ="All customer fields must either be entered or empty.";
                   alert.setContentText(s);
                   alert.show();
                   return;

               }
               Customer customer = new Customer(id,nameC,addressC,postalC,phoneC,createDate,createdBy,lastUpdate,lastUpdatedBy,divId);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               if(Main.updateCustomer(customer)){
                   ObservableList<Customer> customers = customersView.getItems();
                   customers.remove(customersView.getSelectionModel().getSelectedItem());
                   customers.add(customer);
                   alert.setHeaderText("Confirmation Alert");
                   String s ="Customer updated successfully";
                   alert.setContentText(s);
                   clearAllFields();
                   kind=0;
               }
               else{
                   alert.setHeaderText("Error Alert");
                   String s ="Customer update failed.";
                   alert.setContentText(s);
               }
               alert.show();

           }
           else if(kind==0){
               addCustomer();
           }
            enableAllFields();
        }
        else if(updateCustomer.isDisable()){
            if(kind==1){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                int id = Integer.parseInt(aId.getText());
                Integer userId=null;
                Integer customerId=null;
                Integer contactId=null;
                LocalDateTime start=null;
                LocalDateTime end=null;
                try {
                    contactId = contacts.getSelectionModel().getSelectedItem().getId();
                }catch(Exception e){
                    //ignore
                }
                String title = aTitle.getText();
                String description = aDescription.getText();
                String location = aLocation.getText();
                String type = aType.getText();
                try{
                    start=LocalDateTime.parse(aStart.getText(),formatter);
                    end =LocalDateTime.parse(aEnd.getText(),formatter);
                    customerId = Integer.parseInt(aCusId.getText());
                    userId = Integer.parseInt(aUserId.getText());
                } catch(Exception e){
                    //ignore
                }
                if(start==null || end==null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Error Alert");
                    String s ="Please check the format of your start/end times.";
                    alert.setContentText(s);
                    alert.show();
                    return;
                }
                if(!Main.checkUser(userId)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Error Alert");
                    String s ="You have entered a user id that does not exist.";
                    alert.setContentText(s);
                    alert.show();
                    return;
                }
                ZonedDateTime chkStart = start.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
                ZonedDateTime chkEnd = end.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
                if((chkStart.getHour()<8 || chkEnd.getHour()<8) || (chkStart.getHour() >=22 || chkEnd.getHour()>22)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Error Alert");
                    String s ="You have entered an appointment outside of business hours. business hours are 8:00a.m. to 10:00p.m. (EST)";
                    alert.setContentText(s);
                    alert.show();
                    return;
                }

                if(title.isEmpty() && description.isEmpty() && location.isEmpty() && type.isEmpty() && contactId==null
                        && start==null && end==null && customerId==null && userId==null){
                    return;
                }else if(title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty() || contactId==null
                        || start==null || end==null || customerId==null || userId==null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Error Alert");
                    String s ="All appointment fields must either be entered or empty. kind=1";
                    alert.setContentText(s);

                    alert.show();
                    return;
                }
                Appointments app = new Appointments(id,title,description,location,type,start,end,LocalDateTime.now(),Main.getUser().getName(),
                        LocalDateTime.now(),Main.getUser().getName(),customerId,userId,contactId);
                try{
                    boolean isOverlap = Main.chkOverlap(app);
                    if(isOverlap){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Error Alert");
                        String s ="That appointment is not available. please choose a different time.";
                        alert.setContentText(s);
                        alert.show();
                        return;
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if(Main.updateAppointment(app)){
                    ObservableList<Appointments> apps = appointmentsView.getItems();
                    apps.remove(appointmentsView.getSelectionModel().getSelectedItem());
                    apps.add(app);
                    alert.setHeaderText("Confirmation Alert");
                    String s ="Appointment updated successfully";
                    alert.setContentText(s);
                    clearAllFields();
                    kind=0;
                }
                else{
                    alert.setHeaderText("Error Alert");
                    String s ="Appointment update failed.";
                    alert.setContentText(s);
                }
                alert.show();
            }
            else if(kind==0){
                addAppointment();
            }
        }
        else {
            addCustomer();
            addAppointment();
        }
    }
    /**exits current screen/application*/
    public void Exit(ActionEvent event) throws IOException {
        Parent landingView = FXMLLoader.load(getClass().getResource("/Scheduler/main.fxml"));
        Scene landingScene = new Scene(landingView);
        Stage landingWin = (Stage)((Node)event.getSource()).getScene().getWindow();
        landingWin.setScene(landingScene);
        landingWin.show();
    }
    /**cancels update*/
    public void cancelUpdate(ActionEvent event){
        clearAllFields();
        enableAllFields();
    }
    /**disable/clear appointment fields while working on customer*/
    public void disableAppFields(){
        aTitle.setDisable(true);
        aDescription.setDisable(true);
        aLocation.setDisable(true);
        aType.setDisable(true);
        aStart.setDisable(true);
        aEnd.setDisable(true);
        aCusId.setDisable(true);
        aUserId.setDisable(true);
        aId.setDisable(true);
        contacts.setDisable(true);
        aTitle.clear();
        aDescription.clear();
        aLocation.clear();
        aType.clear();
        aStart.clear();
        aEnd.clear();
        aCusId.clear();
        aUserId.clear();
        contacts.getSelectionModel().clearSelection();
        january.setDisable(true);
        february.setDisable(true);
        march.setDisable(true);
        april.setDisable(true);
        may.setDisable(true);
        june.setDisable(true);
        july.setDisable(true);
        august.setDisable(true);
        september.setDisable(true);
        october.setDisable(true);
        november.setDisable(true);
        december.setDisable(true);
        week1.setDisable(true);
        week2.setDisable(true);
        week3.setDisable(true);
        week4.setDisable(true);
        week5.setDisable(true);
        updateApp.setDisable(true);
        deleteApp.setDisable(true);
    }
    /**disable customer fields while working on appointment*/
    public void disableCustFields(){
        updateCustomer.setDisable(true);
        deleteCustomer.setDisable(true);
        cusId.setDisable(true);
        cusName.setDisable(true);
        cusAddress.setDisable(true);
        cusPostal.setDisable(true);
        cusPhone.setDisable(true);
        divs.setDisable(true);
        countries.setDisable(true);
        cusId.clear();
        cusName.clear();
        cusAddress.clear();
        cusPostal.clear();
        cusPhone.clear();
        divs.getSelectionModel().clearSelection();
    }
    /**enable all fields after save*/
    public void enableAllFields(){
        aTitle.setDisable(false);
        aDescription.setDisable(false);
        aLocation.setDisable(false);
        aType.setDisable(false);
        aStart.setDisable(false);
        aEnd.setDisable(false);
        aCusId.setDisable(false);
        aUserId.setDisable(false);
        contacts.setDisable(false);
        january.setDisable(false);
        february.setDisable(false);
        march.setDisable(false);
        april.setDisable(false);
        may.setDisable(false);
        june.setDisable(false);
        july.setDisable(false);
        august.setDisable(false);
        september.setDisable(false);
        october.setDisable(false);
        november.setDisable(false);
        december.setDisable(false);
        week1.setDisable(false);
        week2.setDisable(false);
        week3.setDisable(false);
        week4.setDisable(false);
        week5.setDisable(false);
        updateApp.setDisable(false);
        deleteApp.setDisable(false);
        updateCustomer.setDisable(false);
        deleteCustomer.setDisable(false);
        cusName.setDisable(false);
        cusAddress.setDisable(false);
        cusPostal.setDisable(false);
        cusPhone.setDisable(false);
        divs.setDisable(false);
        countries.setDisable(false);
    }
    /**clears all fields for customer and appointment if cancel update button is activated*/
    public void clearAllFields(){
        cusId.clear();
        cusName.clear();
        cusAddress.clear();
        cusPostal.clear();
        cusPhone.clear();
        divs.getSelectionModel().clearSelection();
        aTitle.clear();
        aDescription.clear();
        aLocation.clear();
        aType.clear();
        aStart.clear();
        aEnd.clear();
        aCusId.clear();
        aUserId.clear();
    }
    /**filters appointments by month*/
    public void filterAppointmentsByMonth(){
        weeks.selectToggle(null);
        ObservableList<Appointments> apps=null;
        int i=0;
        while(i<=11){
            if(this.months.getSelectedToggle().equals(monthsList.get(i))) {
                apps=Main.appsByMonth(i+1);
                break;
            }
            i++;
        }
        ObservableList<Appointments> appsView = appointmentsView.getItems();
        appsView.setAll(apps);

    }
    /**filter appointments by selected week of selected month*/
    public void filterAppointmentsByWeek(){
        int week=0;
        while(week<=5){
            if(this.weeks.getSelectedToggle().equals(weeksList.get(week))) {
                week++;
                break;
            }
            week++;
        }

        try{
            boolean selected = months.getSelectedToggle().isSelected();
            if(selected) {
                ObservableList<Appointments> myapps=null;
                int i=0;
                while(i<=11){
                    if(this.months.getSelectedToggle().equals(monthsList.get(i))) {
                        myapps=Main.appsByMonth(i+1);
                        break;
                    }
                    i++;
                }
                ObservableList<Appointments> appsView = appointmentsView.getItems();
                appsView.setAll(myapps);
                Calendar calendar = Calendar.getInstance();
                ObservableList<Appointments> apps = appointmentsView.getItems();
                ObservableList<Appointments> temp = FXCollections.observableArrayList();
                for (Appointments a : apps) {
                    Timestamp t = Timestamp.valueOf(a.getStart());
                    Instant instant = t.toInstant();
                    calendar.setTime(Date.from(instant));
                    if (calendar.get(Calendar.WEEK_OF_MONTH) == week) {
                        temp.add(a);
                    }
                }
                apps.setAll(temp);
            }

        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error Alert");
            String s ="Please select a month to filter appointments by week of the month.";
            alert.setContentText(s);
            alert.show();
        }
    }
    /**generates reports*/
    public void reportsPage(ActionEvent event) throws IOException {
        Parent landingView = FXMLLoader.load(getClass().getResource("report.fxml"));
        Scene landingScene = new Scene(landingView);

        Stage landingWin = (Stage) ((Node) event.getSource()).getScene().getWindow();
        landingWin.setScene(landingScene);
        landingWin.show();
    }
}
