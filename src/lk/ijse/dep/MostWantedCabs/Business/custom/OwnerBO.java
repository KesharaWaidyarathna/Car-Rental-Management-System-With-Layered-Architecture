package lk.ijse.dep.MostWantedCabs.Business.custom;

import lk.ijse.dep.MostWantedCabs.Business.SuperBO;
import lk.ijse.dep.MostWantedCabs.DTO.OwnerDTO;
import java.util.List;

public interface OwnerBO extends SuperBO {
    boolean saveOwner(OwnerDTO owner)throws Exception;

    boolean updateOwner(OwnerDTO owner)throws Exception;

    boolean deleteOwner(String ownerId) throws Exception;

    List<OwnerDTO> findAllOwners() throws Exception;

    String getLastOwnerId()throws Exception;

    OwnerDTO findOwner(String ownerId) throws Exception;

    List<String> getAllOwnerIds()throws Exception;
}
