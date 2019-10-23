package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;

import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.CustomerDAO;
import lk.ijse.dep.MostWantedCabs.Entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> findAll() throws Exception {
        ResultSet rst= CrudUtil.execute("SELECT * FROM customer");
        List<Customer>customers=new ArrayList<>();
        while (rst.next()){
            customers.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5)));
        }
        return customers;
    }

    @Override
    public Customer find(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE id=?", id);
        if(rst.next()){
            return new Customer(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5));
        }
        return null;
    }

    @Override
    public boolean save(Customer entity) throws Exception {
       return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?)",entity.getId(),entity.getName(),entity.getAddress(),entity.getLicenseNo(),entity.getContactNo());

    }

    @Override
    public boolean update(Customer entity) throws Exception {
        return CrudUtil.execute("UPDATE customer SET name=?,address=?,licenseNo=?,contactNo=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getLicenseNo(),entity.getContactNo(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM customer WHERE id=?",id);
    }

    @Override
    public String getLasCustomerId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM customer ORDER BY id DESC LIMIT 1");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
