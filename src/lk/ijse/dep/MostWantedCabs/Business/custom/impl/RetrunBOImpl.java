package lk.ijse.dep.MostWantedCabs.Business.custom.impl;

import lk.ijse.dep.MostWantedCabs.Business.custom.ReturnBO;
import lk.ijse.dep.MostWantedCabs.DAO.DAOFactory;
import lk.ijse.dep.MostWantedCabs.DAO.DAOType;
import lk.ijse.dep.MostWantedCabs.DAO.custom.*;
import lk.ijse.dep.MostWantedCabs.DB.DBConnection;
import lk.ijse.dep.MostWantedCabs.DTO.ReturnDTO;
import lk.ijse.dep.MostWantedCabs.DTO.ReturnInfoDTO;
import lk.ijse.dep.MostWantedCabs.Entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetrunBOImpl implements ReturnBO {

    private QuaryDAO quaryDAO= DAOFactory.getInstance().getDAO(DAOType.QUARY);
    private ReturnDAO returnDAO=DAOFactory.getInstance().getDAO(DAOType.RETURN);
    private IssueDAO issueDAO = DAOFactory.getInstance().getDAO(DAOType.ISSUE);
    private IssueDetailDAO issueDetailDAO=DAOFactory.getInstance().getDAO(DAOType.ISSUE_DETAIL);
    private DriverDAO driverDAO=DAOFactory.getInstance().getDAO(DAOType.DRIVER);
    private VehicleDAO vehicleDAO=DAOFactory.getInstance().getDAO(DAOType.VEHICLE);
    @Override


    public List<ReturnInfoDTO> getReturnInfo() throws Exception {
        List<CustomReturnEntity>returnEntities=quaryDAO.getReturnInfo();
        List<ReturnInfoDTO>infoDTOS=new ArrayList<>();
        for (CustomReturnEntity returnEntity : returnEntities) {
            infoDTOS.add(new ReturnInfoDTO(returnEntity.getIssueDate(),returnEntity.getReturnDate(),returnEntity.getIssueId(),returnEntity.getAdditionalKilometers(),
                    returnEntity.getCostOfDamage(),returnEntity.getVehicleModel(),returnEntity.getCustomerModel(),returnEntity.getTotal()));
        }
        return infoDTOS;
    }

    @Override
    public boolean saveReturn(ReturnDTO returnDTO) throws Exception {
        String staues="Available";

        Connection connection= DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean result = returnDAO.save(new Return(returnDTO.getIssueId(), returnDTO.getReturnDate(), returnDTO.getAdditionalDistance(), returnDTO.getDamageCost(), returnDTO.getTotal()));

            if(!result){
                connection.rollback();
                throw new RuntimeException("Something went wrong !");
            }

            Issue issue=issueDAO.find(returnDTO.getIssueId());

            Vehicle vehicle=vehicleDAO.find(issue.getVehicleId());
            result=vehicleDAO.update(new Vehicle(vehicle.getId(),vehicle.getRegisterNo(),vehicle.getCategoryId(),vehicle.getModelName(),staues,vehicle.getOwnerId()));

            if(!result){
                connection.rollback();
                throw new RuntimeException("Something went wrong !");
            }

            String driverId=issueDetailDAO.getDriverId(returnDTO.getIssueId());
            if(!(driverId ==null)){
                Driver driver=driverDAO.find(driverId);
                result=driverDAO.update(new Driver(driver.getId(),driver.getName(),driver.getAddress(),driver.getContactNo(),driver.getLicenseNo(),driver.getSalaryPerDay(),staues));
            }

            if(!result){
                connection.rollback();
                throw new RuntimeException("Something went wrong !");
            }

            staues="Returned";
            result=issueDAO.update(new Issue(issue.getId(),issue.getDate(),issue.getVehicleId(),issue.getCustomerId(),staues));

            if(!result){
                connection.rollback();
                throw new RuntimeException("Something went wrong !");
            }
            connection.commit();
            return true;

        }catch (Throwable e){
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
}
