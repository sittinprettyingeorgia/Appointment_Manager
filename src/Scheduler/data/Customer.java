package Scheduler.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**provides customer information for appointments/divisions/etc*/
public class Customer {
    //id is generated through id class
    private Country country=null;
    private firstLevelDiv div=null;
    private ObservableList<Appointments> appointments = FXCollections.observableArrayList();
    private final Integer id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    //create date is permanent
    private final LocalDateTime createDate;
    //created by is permanenet
    private final String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;
    /**constructor*/
    public Customer(Integer id, String name, String address, String postalCode, String phone, LocalDateTime createDate, String createdBy,
                    LocalDateTime lastUpdate, String lastUpdateBy, int divisionId){
        this.id=id;
        this.name=name;
        this.address=address;
        this.postalCode=postalCode;
        this.phone=phone;
        this.createDate=createDate;
        this.createdBy=createdBy;
        this.lastUpdate=lastUpdate;
        this.lastUpdatedBy=lastUpdateBy;
        this.divisionId=divisionId;
    }
    /**gets customer country*/
    public Country getCountry(){
        return country;
    }
    /**sets customer country*/
    public void setCountry(Country country){
        this.country=country;
    }
    /**gets customer division*/
    public firstLevelDiv getDiv() {
        return div;
    }
    /**sets customer division*/
    public void setDiv(firstLevelDiv div) {
        this.div = div;
    }
    /**gets customer appointments*/
    public ObservableList<Appointments> getAppointments() {
        return appointments;
    }
    /**sets customer appointments*/
    public void setAppointments(ObservableList<Appointments> appointments) {
        this.appointments = appointments;
    }
    /**sets customer name*/
    public void setName(String name){
        this.name = name;
    }
    /**sets customer address*/
    public void setAddress(String address){
        this.address=address;
    }
    /**sets customer postal code*/
    public void setPostalCode(String postalCode){
        this.postalCode=postalCode;
    }
    /**sets customer phone number*/
    public void setPhone(String phone){
        this.phone=phone;
    }
    /**sets lastUpdate time*/
    public void setLastUpdate(LocalDateTime lastUpdate){
        this.lastUpdate=lastUpdate;
    }
    /**sets lastUpdatedBy*/
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy=lastUpdatedBy;
    }
    /**sets customer divisionId*/
    public void setDivisionId(int divisionId){
        this.divisionId=divisionId;
    }
    /**gets customerId*/
    public Integer getId(){
        return id;
    }
    /**getscustomer name*/
    public String getName(){
        return name;
    }
    /**gets customer address*/
    public String getAddress(){
        return address;
    }
    /**gets customer postal code*/
    public String getPostalCode(){
        return postalCode;
    }
    /**gets customer phone*/
    public String getPhone(){
        return phone;
    }
    /**gets customer createDate*/
    public LocalDateTime getCreateDate(){
        return createDate;
    }
    /**gets customer createdBy*/
    public String getCreatedBy(){
        return createdBy;
    }
    /**gets lastUpdate time*/
    public LocalDateTime getLastUpdate(){
        return lastUpdate;
    }
    /**gets lastUpdateBy*/
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }
    /**gets customer divisionId*/
    public int getDivisionId(){
        return divisionId;
    }

}



