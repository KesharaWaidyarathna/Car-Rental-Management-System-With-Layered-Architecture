package lk.ijse.dep.MostWantedCabs.Business.custom;

import lk.ijse.dep.MostWantedCabs.Business.SuperBO;
import lk.ijse.dep.MostWantedCabs.DTO.DriverDTO;

import java.util.List;

public interface DriverBO extends SuperBO {
    boolean saveDriver(DriverDTO driver)throws Exception;

    boolean updateDriver(DriverDTO driver)throws Exception;

    boolean deleteDriver(String driverId) throws Exception;

    List<DriverDTO> findAllDrivers() throws Exception;

    String getLastDriverId()throws Exception;

    DriverDTO findDriver(String driverId) throws Exception;

    List<String> getAllDriverIds()throws Exception;

    List<String> getAllAvailableDrivers()throws Exception;
}
