package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;

import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.VehicleCategoryDAO;
import lk.ijse.dep.MostWantedCabs.Entity.VehicleCategory;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleCategoryDAOImpl implements VehicleCategoryDAO {

    @Override
    public List<VehicleCategory> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM vehiclecategory");
        List<VehicleCategory>vehicleCategories=new ArrayList<>();
        while (rst.next()){
            vehicleCategories.add(new VehicleCategory(rst.getString(1),rst.getString(2),rst.getDouble(3),
                    rst.getDouble(4),rst.getInt(5)));
        }
        return vehicleCategories;
    }

    @Override
    public VehicleCategory find(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM vehiclecategory WHERE id=?", id);
        if(rst.next()){
            return new VehicleCategory(rst.getString(1),rst.getString(2),rst.getDouble(3),
                    rst.getDouble(4),rst.getInt(5));
        }
        return null;
    }

    @Override
    public boolean save(VehicleCategory entity) throws Exception {
        return CrudUtil.execute("INSERT INTO vehiclecategory VALUES (?,?,?,?,?)",entity.getId(),entity.getName(),entity.getRentalForDay(),entity.getRenatlForKM(),entity.getKilometerPerDay());
    }

    @Override
    public boolean update(VehicleCategory entity) throws Exception {
        return CrudUtil.execute("UPDATE vehiclecategory SET name=?,rentalForDay=?,rentalForKM=?, kilometerePerDay=? WHERE id=?",entity.getName(),entity.getRentalForDay(),entity.getRenatlForKM(),entity.getKilometerPerDay(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM vehiclecategory WHERE id=?",id);
    }

    @Override
    public String getLastCategoryID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM vehiclecategory ORDER BY id DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
