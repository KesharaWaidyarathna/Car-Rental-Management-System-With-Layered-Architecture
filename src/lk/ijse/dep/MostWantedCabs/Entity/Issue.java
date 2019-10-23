package lk.ijse.dep.MostWantedCabs.Entity;

import java.sql.Date;

public class Issue implements SuperEntity{
    private String id;
    private Date date;
    private String vehicleId;
    private String customerId;
    private String statues;

    public Issue(String id, Date date, String vehicleId, String customerId, String statues) {
        this.setId(id);
        this.setDate(date);
        this.setVehicleId(vehicleId);
        this.setCustomerId(customerId);
        this.setStatues(statues);
    }
    public Issue( Date date, String vehicleId, String customerId, String statues) {
        this.setDate(date);
        this.setVehicleId(vehicleId);
        this.setCustomerId(customerId);
        this.setStatues(statues);
    }

    public Issue() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", vehicleId='" + vehicleId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", statues='" + statues + '\'' +
                '}';
    }
}
