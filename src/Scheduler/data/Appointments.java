package Scheduler.data;

import java.time.LocalDateTime;

/**manages appointments for customers/users/contacts*/
public class Appointments {
    private final Integer id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private final LocalDateTime createDate;
    private final String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;
    /**constructor*/
    public Appointments(Integer id, String title, String description, String location,
                        String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy,
                        LocalDateTime lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }
    /**
     * gets appointment id
     */
    public Integer getId() {
        return id;
    }

    /**
     * gets appointment title
     */
    public String getTitle() {
        return title;
    }
    /**gets description*/
    public String getDescription(){return description;}
    /**sets description*/
    public void setDescription(String d){description=d;}
    /**gets appointment location*/
    public String getLocation() {
        return location;
    }
    /** sets appointment location*/
    public void setLocation(String location) {
        this.location = location;
    }
    /**gets appointment type*/
    public String getType() {
        return type;
    }
    /**sets appointment type*/
    public void setType(String type) {
        this.type = type;
    }
    /**gets appointment start time*/
    public LocalDateTime getStart() {
        return start;
    }
    /**sets appointment start time*/
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /**gets appointment end*/
    public LocalDateTime getEnd() {
        return end;
    }
    /**sets appointment end*/
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    /**getscreate date*/
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    /**gets createdBy*/
    public String getCreatedBy() {
        return createdBy;
    }
    /**gets last update time*/
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    /**sets last update time*/
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**gets last updated by*/
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**sets lastUpdateBy*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**gets customerId*/
    public int getCustomerId() {
        return customerId;
    }
    /**setsCustomerId*/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /** getsuserId*/
    public int getUserId() {
        return userId;
    }
    /**sets userId*/
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**gets contactId*/
    public int getContactId() {
        return contactId;
    }
    /**sets contactId*/
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
