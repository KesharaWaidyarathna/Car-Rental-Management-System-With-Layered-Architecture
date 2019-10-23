package lk.ijse.dep.MostWantedCabs.Business.custom.impl;

import lk.ijse.dep.MostWantedCabs.Business.custom.VehicleCategoryBO;
import lk.ijse.dep.MostWantedCabs.Business.exception.AlreadyExist;
import lk.ijse.dep.MostWantedCabs.DAO.DAOFactory;
import lk.ijse.dep.MostWantedCabs.DAO.DAOType;
import lk.ijse.dep.MostWantedCabs.DAO.custom.VehicleCategoryDAO;
import lk.ijse.dep.MostWantedCabs.DAO.custom.VehicleDAO;
import lk.ijse.dep.MostWantedCabs.DTO.VehicleCategoryDTO;
import lk.ijse.dep.MostWantedCabs.Entity.VehicleCategory;

import java.util.ArrayList;
import java.util.List;

public class VehicleCategoryBOImpl implements VehicleCategoryBO {
    private VehicleCategoryDAO vehicleCategoryDAO= DAOFactory.getInstance().getDAO(DAOType.VEHICLE_CATEGORY);
    private VehicleDAO vehicleDAO=DAOFactory.getInstance().getDAO(DAOType.VEHICLE);


    @Override
    public boolean saveVehicleCategory(VehicleCategoryDTO vehicleCategory) throws Exception {
        return vehicleCategoryDAO.save(new VehicleCategory(vehicleCategory.getId(),vehicleCategory.getName(),vehicleCategory.getRentalForDay(),vehicleCategory.getRenatlForKM(),vehicleCategory.getKilometerPerDay()));
    }

    @Override
    public boolean updateVehicleCategory(VehicleCategoryDTO vehicleCategory) throws Exception {
        return vehicleCategoryDAO.update(new VehicleCategory(vehicleCategory.getId(),vehicleCategory.getName(),vehicleCategory.getRentalForDay(),vehicleCategory.getRenatlForKM(),vehicleCategory.getKilometerPerDay()));
    }

    @Override
    public boolean deleteVehicleCategory(String vehicleCategoryId) throws Exception {
        if(vehicleDAO.existVehicleCategoryId(vehicleCategoryId)){
            throw new AlreadyExist("Vehicle Category already exists in vehicle, hence unable to delete !");
        }
        return vehicleCategoryDAO.delete(vehicleCategoryId);
    }

    @Override
    public List<VehicleCategoryDTO> findAllVehicleCategories() throws Exception {
        List<VehicleCategory>vehicleCategories=vehicleCategoryDAO.findAll();
        List<VehicleCategoryDTO>vehicleCategoryDTOS=new ArrayList<>();
        for (VehicleCategory vehicleCategory : vehicleCategories) {
            vehicleCategoryDTOS.add(new VehicleCategoryDTO(vehicleCategory.getId(),vehicleCategory.getName(),vehicleCategory.getRentalForDay(),vehicleCategory.getRenatlForKM(),vehicleCategory.getKilometerPerDay()));
        }
        return vehicleCategoryDTOS;
    }

    @Override
    public String getLastVehicleCategoryId() throws Exception {
        return vehicleCategoryDAO.getLastCategoryID();
    }

    @Override
    public VehicleCategoryDTO findVehicleCategory(String vehicleCategoryId) throws Exception {
        VehicleCategory vehicleCategory=vehicleCategoryDAO.find(vehicleCategoryId);
        return new VehicleCategoryDTO(vehicleCategory.getId(),vehicleCategory.getName(),vehicleCategory.getRentalForDay(),vehicleCategory.getRenatlForKM(),vehicleCategory.getKilometerPerDay());
    }

    @Override
    public List<String> getAllVehicleCategoryIds() throws Exception {
        List<VehicleCategory>vehicleCategories=vehicleCategoryDAO.findAll();
        List<String>vehicleCategoryId=new ArrayList<>();
        for (VehicleCategory vehicleCategory : vehicleCategories) {
            vehicleCategoryId.add(vehicleCategory.getId());
        }
        return vehicleCategoryId;
    }
}
