package lk.ijse.dep.MostWantedCabs.Business.custom.impl;

import lk.ijse.dep.MostWantedCabs.Business.custom.IssueBO;
import lk.ijse.dep.MostWantedCabs.DAO.DAOFactory;
import lk.ijse.dep.MostWantedCabs.DAO.DAOType;
import lk.ijse.dep.MostWantedCabs.DAO.custom.*;
import lk.ijse.dep.MostWantedCabs.DB.DBConnection;
import lk.ijse.dep.MostWantedCabs.DTO.IssueDTO;
import lk.ijse.dep.MostWantedCabs.DTO.IssueDetailDTO;
import lk.ijse.dep.MostWantedCabs.DTO.IssueInfoDTO;
import lk.ijse.dep.MostWantedCabs.Entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IssueBOImpl implements IssueBO {

    private IssueDAO issueDAO = DAOFactory.getInstance().getDAO(DAOType.ISSUE);
    private QuaryDAO quaryDAO=DAOFactory.getInstance().getDAO(DAOType.QUARY);
    private IssueDetailDAO issueDetailDAO=DAOFactory.getInstance().getDAO(DAOType.ISSUE_DETAIL);
    private DriverDAO driverDAO=DAOFactory.getInstance().getDAO(DAOType.DRIVER);
    private VehicleDAO vehicleDAO=DAOFactory.getInstance().getDAO(DAOType.VEHICLE);

    @Override
    public String getLastIssueId() throws Exception {
        return issueDAO.getLastIssueID();
    }

    @Override
    public List<String> getAllIssueIds() throws Exception {
        List<Issue>issues=issueDAO.findAll();
        List<String>issueIds=new ArrayList<>();
        for (Issue issue : issues) {
            issueIds.add(issue.getId());
        }
        return issueIds;
    }

    @Override
    public boolean issueVehicle(IssueDTO issue) {
        String driverId="";
        String staues="Not Available";

        Connection connection= DBConnection.getInstance().getConnection();
        try {

            connection.setAutoCommit(false);
            boolean result=issueDAO.save(new Issue(issue.getId(),issue.getDate(),issue.getVehicleId(),issue.getCustomerId(),issue.getStatues()));

            if(!result){
                connection.rollback();
                throw new RuntimeException("Something wrong !");
            }

            for (IssueDetailDTO detailDTO : issue.getIssueDetail()) {
                driverId=detailDTO.getDriverId();
            }

            if(!driverId.equals("Without Driver")){

                for (IssueDetailDTO detailDTO : issue.getIssueDetail()) {

                    result=issueDetailDAO.save(new IssueDetail(detailDTO.getIssueId(),detailDTO.getDriverId()));
                    driverId=detailDTO.getDriverId();
                }

                if(!result){
                    connection.rollback();
                    throw new RuntimeException("Something wrong !");
                }

                Driver driver=driverDAO.find(driverId);
                result= driverDAO.update(new Driver(driver.getId(),driver.getName(),driver.getAddress(),driver.getContactNo(),
                        driver.getLicenseNo(),driver.getSalaryPerDay(),staues));


                if(!result){
                    connection.rollback();
                    throw new RuntimeException("Something wrong !");
                }
            }

            Vehicle vehicle=vehicleDAO.find(issue.getVehicleId());
            result=vehicleDAO.update(new Vehicle(vehicle.getId(),vehicle.getRegisterNo(),vehicle.getCategoryId(),vehicle.getModelName(),
                    staues,vehicle.getOwnerId()));

            if(!result){
                connection.rollback();
                throw new RuntimeException("Something wrong !");
            }

            connection.commit();
            return true;

        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> findAllNotReturnId(String status) throws Exception {
        List<Issue>issuId=issueDAO.findallIssueIds(status);
        List<String>id=new ArrayList<>();
        for (Issue issue : issuId) {
            id.add(issue.getId());
        }
        return id;
    }

    @Override
    public boolean updateIssue(IssueInfoDTO infoDTOS) throws Exception {
        return quaryDAO.updateStatues(new CustomEntity(infoDTOS.getId(),infoDTOS.getStatues()));
    }

    @Override
    public IssueDTO findIssue(String issueId) throws Exception {
        Issue issue=issueDAO.find(issueId);
        return new IssueDTO(issue.getId(),issue.getDate(),issue.getVehicleId(),issue.getCustomerId(),issue.getStatues());
    }

    @Override
    public List<IssueInfoDTO> getIssueInfo() throws Exception {
        List<CustomEntity>issueifo=quaryDAO.getIssueinfo();
        List<IssueInfoDTO>issueInfoDTOS=new ArrayList<>();
        for (CustomEntity customEntity : issueifo) {
            issueInfoDTOS.add(new IssueInfoDTO(customEntity.getId(),customEntity.getDate(),customEntity.getCustomerId(),customEntity.getCustomerName(),customEntity.getVehicleId(),customEntity.getVehicleModel(),customEntity.getDriverStatues(),customEntity.getStatues()));
        }
        return issueInfoDTOS;
    }
}
