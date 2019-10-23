package lk.ijse.dep.MostWantedCabs.DTO;

public class IssueDetailDTO {
    private String issueId;
    private String driverId;

    public IssueDetailDTO(String issueId, String driverId) {
        this.setIssueId(issueId);
        this.setDriverId(driverId);
    }

    public IssueDetailDTO() {
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "IssueDetailDTO{" +
                "issueId='" + issueId + '\'' +
                ", driverId='" + driverId + '\'' +
                '}';
    }
}
