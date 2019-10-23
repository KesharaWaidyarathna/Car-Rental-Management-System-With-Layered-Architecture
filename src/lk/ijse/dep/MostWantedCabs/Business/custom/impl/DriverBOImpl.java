package lk.ijse.dep.MostWantedCabs.Business.custom.impl;

import lk.ijse.dep.MostWantedCabs.Business.custom.DriverBO;
import lk.ijse.dep.MostWantedCabs.Business.exception.AlreadyExist;
import lk.ijse.dep.MostWantedCabs.DAO.DAOFactory;
import lk.ijse.dep.MostWantedCabs.DAO.DAOType;
import lk.ijse.dep.MostWantedCabs.DAO.custom.DriverDAO;
import lk.ijse.dep.MostWantedCabs.DAO.custom.IssueDetailDAO;
import lk.ijse.dep.MostWantedCabs.DTO.DriverDTO;
import lk.ijse.dep.MostWantedCabs.Entity.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverBOImpl implements DriverBO {
    private DriverDAO driverDAO= DAOFactory.getInstance().getDAO(DAOType.DRIVER);
    private IssueDetailDAO issueDetailDAO= DAOFactory.getInstance().getDAO(DAOType.ISSUE_DETAIL);

    @Override
    public boolean saveDriver(DriverDTO driver) throws Exception {
        return driverDAO.save(new Driver(driver.getId(),driver.getName(),driver.getAddress(),driver.getContactNo(),driver.getLicenseNo(),driver.getSalaryPerDay(),driver.getStatues()));
    }

    @Override
    public boolean updateDriver(DriverDTO driver) throws Exception {
        return driverDAO.update(new Driver(driver.getId(),driver.getName(),driver.getAddress(),driver.getContactNo(),driver.getLicenseNo(),driver.getSalaryPerDay(),driver.getStatues()));
    }

    @Override
    public boolean deleteDriver(String driverId) throws Exception {
        if(issueDetailDAO.existVehicleId(driverId)){
            throw new AlreadyExist("Deiver already exists in an Issue detail, hence unable to delete !");
        }
        return driverDAO.delete(driverId);
    }

    @Override
    public List<DriverDTO> findAllDrivers() throws Exception {
        List<Driver>drivers=driverDAO.findAll();
        List<DriverDTO>driverDTOS=new ArrayList<>();
        for (Driver driver : drivers) {
            driverDTOS.add(new DriverDTO(driver.getId(),driver.getName(),driver.getAddress(),driver.getContactNo(),driver.getLicenseNo(),driver.getSalaryPerDay(),driver.getStatues()));
        }
        return driverDTOS;
    }

    @Override
    public String getLastDriverId() throws Exception {
        return driverDAO.getLastDriverID();
    }

    @Override
    public DriverDTO findDriver(String driverId) throws Exception {
        Driver driver=driverDAO.find(driverId);
        return new DriverDTO(driver.getId(),driver.getName(),driver.getAddress(),driver.getContactNo(),driver.getLicenseNo(),driver.getSalaryPerDay(),driver.getStatues());
    }

    @Override
    public List<String> getAllDriverIds() throws Exception {
        List<Driver>drivers=driverDAO.findAll();
        List<String>driverIds=new ArrayList<>();
        for (Driver driver : drivers) {
            driverIds.add(driver.getId());
        }
        return driverIds;
    }

    @Override
    public List<String> getAllAvailableDrivers() throws Exception {
        String staues="Available";
        List<String>drivers=driverDAO.availableDrivers(staues);
        return drivers;
    }
}
