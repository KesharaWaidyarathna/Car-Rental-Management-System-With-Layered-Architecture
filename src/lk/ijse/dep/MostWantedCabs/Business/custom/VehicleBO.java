package lk.ijse.dep.MostWantedCabs.Business.custom;

import lk.ijse.dep.MostWantedCabs.Business.SuperBO;
import lk.ijse.dep.MostWantedCabs.DTO.VehicleDTO;
import java.util.List;

public interface VehicleBO extends SuperBO {
    boolean saveVehicle(VehicleDTO vehicle)throws Exception;

    boolean updateVehicle(VehicleDTO vehicle)throws Exception;

    boolean deleteVehicle(String vehicleId) throws Exception;

    List<VehicleDTO> findAllVehicles() throws Exception;

    String getLastVehicleId()throws Exception;

    VehicleDTO findVehicle(String vehicleId) throws Exception;

    List<String> getAllVehicleIds()throws Exception;

    List<String> getAllAvailableVehicleIds()throws Exception;

    List<VehicleDTO> searchVehicle(String search)throws Exception;

}
