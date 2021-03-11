package Scheduler.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**manages contact information for appointments*/
public class Contact {
    private ObservableList<Appointments> appointments = FXCollections.observableArrayList();
    private final Integer id;
    private String name;
    private String email;
    /**constructor*/
    public Contact(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    /**gets appointments*/
    public ObservableList<Appointments> getAppointments() {
        return appointments;
    }
    /**sets appointments*/
    public void setAppointments(ObservableList<Appointments> appointments) {
        this.appointments = appointments;
    }
    /**gets contact id*/
    public Integer getId() {
        return id;
    }
    /**gets contact name*/
    public String getName() {
        return name;
    }
    /**sets contact name*/
    public void setName(String name) {
        this.name = name;
    }
    /**gets contact email*/
    public String getEmail() {
        return email;
    }
    /**sets contact email*/
    public void setEmail(String email) {
        this.email = email;
    }

    /**gets name of contact instead of super toString result*/
    @Override
    public String toString() {
        return this.getName();
    }
}
