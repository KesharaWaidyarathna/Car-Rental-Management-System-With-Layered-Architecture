package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;

import lk.ijse.dep.MostWantedCabs.Controller.vehicles;
import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.DriverDAO;
import lk.ijse.dep.MostWantedCabs.Entity.Driver;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {

    @Override
    public List<Driver> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM driver");
        List<Driver>drivers=new ArrayList<>();
        while (rst.next()){
            drivers.add(new Driver(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5),rst.getDouble(6),rst.getString(7)));
        }
        return drivers;
    }

    @Override
    public Driver find(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM driver WHERE id=?", id);
        if(rst.next()){
            return new Driver(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5),rst.getDouble(6),rst.getString(7));
        }
        return null;
    }

    @Override
    public boolean save(Driver entity) throws Exception {
        return CrudUtil.execute("INSERT INTO driver VALUES (?,?,?,?,?,?,?)",entity.getId(),entity.getName(),entity.getAddress(),entity.getContactNo(),entity.getLicenseNo(),
                entity.getSalaryPerDay(),entity.getStatues());
    }

    @Override
    public boolean update(Driver entity) throws Exception {
        return CrudUtil.execute("UPDATE driver SET name=?, address=?, contactNo=?, licenseNo=?, salaryPerDay=?, statues=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getContactNo(),entity.getLicenseNo(),
                entity.getSalaryPerDay(),entity.getStatues(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM driver WHERE id=?",id);
    }

    @Override
    public String getLastDriverID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM driver ORDER BY id DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<String> availableDrivers(String statues) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM driver WHERE statues=?", statues);
        List<String> drivers=new ArrayList<>();
        while (rst.next()){
            drivers.add(rst.getString(1));
        }
        return drivers;
    }
}
