package lk.ijse.dep.MostWantedCabs.Business.custom.impl;

import lk.ijse.dep.MostWantedCabs.Business.custom.VehicleBO;
import lk.ijse.dep.MostWantedCabs.Business.exception.AlreadyExist;
import lk.ijse.dep.MostWantedCabs.DAO.DAOFactory;
import lk.ijse.dep.MostWantedCabs.DAO.DAOType;
import lk.ijse.dep.MostWantedCabs.DAO.custom.IssueDAO;
import lk.ijse.dep.MostWantedCabs.DAO.custom.QuaryDAO;
import lk.ijse.dep.MostWantedCabs.DAO.custom.VehicleDAO;
import lk.ijse.dep.MostWantedCabs.DTO.VehicleDTO;
import lk.ijse.dep.MostWantedCabs.Entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {

    private VehicleDAO vehicleDAO= DAOFactory.getInstance().getDAO(DAOType.VEHICLE);
    private IssueDAO issueDAO=DAOFactory.getInstance().getDAO(DAOType.ISSUE);
    private QuaryDAO quaryDAO=DAOFactory.getInstance().getDAO(DAOType.QUARY);

    @Override
    public boolean saveVehicle(VehicleDTO vehicle) throws Exception {
        return vehicleDAO.save(new Vehicle(vehicle.getId(),vehicle.getRegisterNo(),vehicle.getCategoryId(),vehicle.getModelName(),vehicle.getStatues(),vehicle.getOwnerId()));
    }

    @Override
    public boolean updateVehicle(VehicleDTO vehicle) throws Exception {
        return vehicleDAO.update(new Vehicle(vehicle.getId(),vehicle.getRegisterNo(),vehicle.getModelName(),vehicle.getStatues(),vehicle.getOwnerId()));
    }

    @Override
    public boolean deleteVehicle(String vehicleId) throws Exception {
        if(issueDAO.existVehicleId(vehicleId)){
            throw new AlreadyExist("Vehicle already exists in an Issue, hence unable to delete !");
        }
        return vehicleDAO.delete(vehicleId);
    }

    @Override
    public List<VehicleDTO> findAllVehicles() throws Exception {
        List<Vehicle>vehicles=vehicleDAO.findAll();
        List<VehicleDTO>vehicleDTOS=new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(new VehicleDTO(vehicle.getId(),vehicle.getRegisterNo(),vehicle.getCategoryId(),vehicle.getModelName(),vehicle.getStatues(),vehicle.getOwnerId()));
        }
        return vehicleDTOS;
    }

    @Override
    public String getLastVehicleId() throws Exception {
        return vehicleDAO.getLastVehicleID();
    }

    @Override
    public VehicleDTO findVehicle(String vehicleId) throws Exception {
        Vehicle vehicle=vehicleDAO.find(vehicleId);
        return new VehicleDTO(vehicle.getId(),vehicle.getRegisterNo(),vehicle.getCategoryId(),vehicle.getModelName(),vehicle.getStatues(),vehicle.getOwnerId());
    }

    @Override
    public List<String> getAllVehicleIds() throws Exception {
        List<Vehicle>vehicles=vehicleDAO.findAll();
        List<String>vehicleID=new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleID.add(vehicle.getId());
        }
        return vehicleID;
    }

    @Override
    public List<String> getAllAvailableVehicleIds() throws Exception {
        String staues="Available";
        List<String>vehicles=vehicleDAO.availableVehicles(staues);
        return vehicles;
    }

    @Override
    public List<VehicleDTO> searchVehicle(String search) throws Exception {
        List<Vehicle>vehicles=quaryDAO.SearchVehicle(search);
        List<VehicleDTO>vehicleDTOS=new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(new VehicleDTO(vehicle.getId(),vehicle.getRegisterNo(),vehicle.getCategoryId(),vehicle.getModelName(),vehicle.getStatues(),vehicle.getOwnerId()));
        }
        return vehicleDTOS;
    }
}
