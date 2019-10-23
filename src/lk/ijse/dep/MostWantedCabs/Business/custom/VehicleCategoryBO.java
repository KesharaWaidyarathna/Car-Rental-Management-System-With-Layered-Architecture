package lk.ijse.dep.MostWantedCabs.Business.custom;

import lk.ijse.dep.MostWantedCabs.Business.SuperBO;
import lk.ijse.dep.MostWantedCabs.DTO.VehicleCategoryDTO;

import java.util.List;

public interface VehicleCategoryBO extends SuperBO {

    boolean saveVehicleCategory(VehicleCategoryDTO vehicleCategory)throws Exception;

    boolean updateVehicleCategory(VehicleCategoryDTO vehicleCategory)throws Exception;

    boolean deleteVehicleCategory(String vehicleCategoryId) throws Exception;

    List<VehicleCategoryDTO> findAllVehicleCategories() throws Exception;

    String getLastVehicleCategoryId()throws Exception;

    VehicleCategoryDTO findVehicleCategory(String vehicleCategoryId) throws Exception;

    List<String> getAllVehicleCategoryIds()throws Exception;
}
