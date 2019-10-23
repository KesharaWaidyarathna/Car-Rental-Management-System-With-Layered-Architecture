package lk.ijse.dep.MostWantedCabs.Entity;

public class Vehicle implements SuperEntity {
    private String id;
    private String registerNo;
    private String categoryId;
    private String modelName;
    private String statues;
    private String ownerId;

    public Vehicle(String id, String registerNo, String categoryId, String modelName, String statues, String ownerId) {
        this.setId(id);
        this.registerNo = registerNo;
        this.categoryId = categoryId;
        this.modelName = modelName;
        this.statues = statues;
        this.ownerId = ownerId;
    }
    public Vehicle(String registerNo, String categoryId, String modelName, String statues, String ownerId) {
        this.registerNo = registerNo;
        this.categoryId = categoryId;
        this.modelName = modelName;
        this.statues = statues;
        this.ownerId = ownerId;
    }

    public Vehicle() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", registerNo='" + registerNo + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", modelName='" + modelName + '\'' +
                ", statues='" + statues + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
