package Scheduler.data;

import java.time.LocalDateTime;
/**provides country information for divisions*/
public class Country {
    private final int id;
    private String name;
    private final LocalDateTime createDate;
    private final String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    /**constructor*/
    public Country(int id, String name, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy){
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**sets country name*/
    public void setName(String name){
        this.name = name;
    }
    /**sets last update time*/
    public void setLastUpdate(LocalDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
    }
    /**sets lastUpdatedBy*/
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**gets countryId*/
    public int getId(){
        return id;
    }
    /** gets country name*/
    public String getName(){
        return name;
    }
    /**gets country create date*/
    public LocalDateTime getCreateDate(){
        return createDate;
    }
    /**gets createdBy*/
    public String getCreatedBy(){
        return createdBy;
    }
    /**gets lastUpdate time*/
    public LocalDateTime getLastUpdate(){
        return lastUpdate;
    }
    /**gets lastUpdatedBy*/
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }
    /**gets name of country instead of super toString result*/
    @Override
    public String toString() {
        return this.getName();
    }
}
