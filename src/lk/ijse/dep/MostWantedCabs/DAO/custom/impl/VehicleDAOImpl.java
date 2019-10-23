package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;

import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.VehicleDAO;
import lk.ijse.dep.MostWantedCabs.Entity.Vehicle;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public List<Vehicle> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM vehicle");
        List<Vehicle>vehicles=new ArrayList<>();
        while (rst.next()){
            vehicles.add(new Vehicle(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5),rst.getString(6)));
        }
        return vehicles;
    }

    @Override
    public Vehicle find(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM vehicle WHERE id=?", id);
        if(rst.next()){
            return new Vehicle(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5),rst.getString(6));
        }
        return null;
    }

    @Override
    public boolean save(Vehicle entity) throws Exception {
        return CrudUtil.execute("INSERT INTO vehicle VALUES (?,?,?,?,?,?)",entity.getId(),entity.getRegisterNo(),entity.getCategoryId(),entity.getModelName(),entity.getStatues(),
                entity.getOwnerId());
    }

    @Override
    public boolean update(Vehicle entity) throws Exception {
        return CrudUtil.execute("UPDATE vehicle SET registerNo=?,modelName=?,statues=? WHERE id=?",entity.getRegisterNo(),entity.getModelName(),entity.getStatues(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM vehicle WHERE id=?",id);
    }

    @Override
    public String getLastVehicleID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM vehicle ORDER BY id DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean existVehicleCategoryId(String vehicleCategoryId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT categoryId FROM vehicle WHERE categoryId=?", vehicleCategoryId);
        return rst.next();
    }

    @Override
    public boolean existOwnerId(String ownerId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT ownerId FROM vehicle WHERE ownerId=?", ownerId);
        return rst.next();
    }

    @Override
    public List<String> availableVehicles(String statues) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM vehicle WHERE statues=?", statues);
        List<String> vehicles=new ArrayList<>();
        while (rst.next()){
            vehicles.add(rst.getString(1));
        }
        return vehicles;
    }
}
