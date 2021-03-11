package Scheduler.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/**provides information for customer appointments*/
public class User {
    private ObservableList<Appointments> appointments= FXCollections.observableArrayList();
    private final Integer id;
    private String name;
    private String password;
    private final LocalDateTime createDate;
    private final String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    /**constructor*/
    public User(Integer id, String name,
                String password, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**gets appointments*/
    public ObservableList<Appointments> getAppointments() {
        return appointments;
    }
    /**sets appointments*/
    public void setAppointments(ObservableList<Appointments> appointments) {
        this.appointments = appointments;
    }
    /**gets userId*/
    public Integer getId() {
        return id;
    }
    /**gets user name*/
    public String getName() {
        return name;
    }
    /**sets user name*/
    public void setName(String name) {
        this.name = name;
    }
    /**gets user password*/
    public String getPassword() {
        return password;
    }
    /** sets user password*/
    public void setPassword(String password) {
        this.password = password;
    }
    /**gets createDate*/
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    /**gets createdBy*/
    public String getCreatedBy() {
        return createdBy;
    }
    /**gets lastUpdate*/
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    /**sets lastUpdate*/
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**gets lastUpdatedBy*/
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**sets lastUpdatedBy*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}

