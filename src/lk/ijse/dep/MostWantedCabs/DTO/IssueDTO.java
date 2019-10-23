package lk.ijse.dep.MostWantedCabs.DTO;

import java.sql.Date;
import java.util.List;

public class IssueDTO {
    private String id;
    private Date date;
    private String vehicleId;
    private String customerId;
    private String statues;
    private List<IssueDetailDTO>issueDetail;

    public IssueDTO(String id, Date date, String vehicleId, String customerId, String statues, List<IssueDetailDTO> issueDetail) {
        this.setId(id);
        this.setDate(date);
        this.setVehicleId(vehicleId);
        this.setCustomerId(customerId);
        this.setStatues(statues);
        this.setIssueDetail(issueDetail);
    }
    public IssueDTO( Date date, String vehicleId, String customerId, String statues, List<IssueDetailDTO> issueDetail) {
        this.setDate(date);
        this.setVehicleId(vehicleId);
        this.setCustomerId(customerId);
        this.setStatues(statues);
        this.setIssueDetail(issueDetail);
    }

    public IssueDTO(String id, Date date, String vehicleId, String customerId, String statues) {
        this.setId(id);
        this.setDate(date);
        this.setVehicleId(vehicleId);
        this.setCustomerId(customerId);
        this.setStatues(statues);
    }

    public IssueDTO() {
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

    public List<IssueDetailDTO> getIssueDetail() {
        return issueDetail;
    }

    public void setIssueDetail(List<IssueDetailDTO> issueDetail) {
        this.issueDetail = issueDetail;
    }

    @Override
    public String toString() {
        return "IssueDTO{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", vehicleId='" + vehicleId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", statues='" + statues + '\'' +
                ", issueDetail=" + issueDetail +
                '}';
    }
}
