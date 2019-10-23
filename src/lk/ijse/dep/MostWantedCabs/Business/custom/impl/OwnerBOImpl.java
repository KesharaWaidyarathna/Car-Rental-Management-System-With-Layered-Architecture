package lk.ijse.dep.MostWantedCabs.Business.custom.impl;

import lk.ijse.dep.MostWantedCabs.Business.custom.OwnerBO;
import lk.ijse.dep.MostWantedCabs.Business.exception.AlreadyExist;
import lk.ijse.dep.MostWantedCabs.DAO.DAOFactory;
import lk.ijse.dep.MostWantedCabs.DAO.DAOType;
import lk.ijse.dep.MostWantedCabs.DAO.custom.OwnerDAO;
import lk.ijse.dep.MostWantedCabs.DAO.custom.VehicleDAO;
import lk.ijse.dep.MostWantedCabs.DTO.OwnerDTO;
import lk.ijse.dep.MostWantedCabs.Entity.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerBOImpl implements OwnerBO {
    private OwnerDAO ownerDAO= DAOFactory.getInstance().getDAO(DAOType.OWNER);
    private VehicleDAO vehicleDAO=DAOFactory.getInstance().getDAO(DAOType.VEHICLE);
    @Override
    public boolean saveOwner(OwnerDTO owner) throws Exception {
        return ownerDAO.save(new Owner(owner.getId(),owner.getName(),owner.getContactNo(),owner.getAddress()));
    }

    @Override
    public boolean updateOwner(OwnerDTO owner) throws Exception {
        return ownerDAO.update(new Owner(owner.getId(),owner.getName(),owner.getContactNo(),owner.getAddress()));
    }

    @Override
    public boolean deleteOwner(String ownerId) throws Exception {
        if(vehicleDAO.existOwnerId(ownerId)){
            throw new AlreadyExist("Owner already exists in vehicle, hence unable to delete !");
        }
        return ownerDAO.delete(ownerId);
    }

    @Override
    public List<OwnerDTO> findAllOwners() throws Exception {
        List<Owner>owners=ownerDAO.findAll();
        List<OwnerDTO>ownerDTOS=new ArrayList<>();
        for (Owner owner : owners) {
            ownerDTOS.add(new OwnerDTO(owner.getId(),owner.getName(),owner.getContactNo(),owner.getAddress()));
        }
        return ownerDTOS;
    }

    @Override
    public String getLastOwnerId() throws Exception {
        return ownerDAO.getLastOwnerID();
    }

    @Override
    public OwnerDTO findOwner(String ownerId) throws Exception {
        Owner owner=ownerDAO.find(ownerId);
        return new OwnerDTO(owner.getId(),owner.getName(),owner.getContactNo(),owner.getAddress());
    }

    @Override
    public List<String> getAllOwnerIds() throws Exception {
        List<Owner>owners=ownerDAO.findAll();
        List<String>owenerId=new ArrayList<>();
        for (Owner owner : owners) {
            owenerId.add(owner.getId());
        }
        return owenerId;
    }
}
