package Scheduler.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**provides customer information for country*/
public class firstLevelDiv {
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final Integer id;
    private String division;
    private final LocalDateTime createDate;
    private final String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;
    private int countryId;

    /**constructor*/
    public firstLevelDiv(Integer id, String division, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdateBy, int countryId) {
        this.id = id;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.countryId = countryId;
    }
    /**gets division customers*/
    public ObservableList<Customer> getCustomers() {
        return customers;
    }
    /**sets divison customers*/
    public void setCustomers(ObservableList<Customer> customers) {
        this.customers = customers;
    }
    /**getsdivisionId*/
    public Integer getId() {
        return id;
    }
    /**gets division*/
    public String getDivision() {
        return division;
    }
    /**sets division*/
    public void setDivision(String division) {
        this.division = division;
    }
    /**gets createDate time*/
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    /**getscreatedBy*/
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
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }
    /**sets lastUpdatedBy*/
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
    /**gets division countryId*/
    public int getCountryId() {
        return countryId;
    }
    /**sets division countryId*/
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**getsthe name of the division instead of super tostring result*/
    @Override
    public String toString() {
        return this.getDivision();
    }
}
