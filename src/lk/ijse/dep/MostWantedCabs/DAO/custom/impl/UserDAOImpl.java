package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;

import lk.ijse.dep.MostWantedCabs.Controller.customers;
import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.UserDAO;
import lk.ijse.dep.MostWantedCabs.Entity.Customer;
import lk.ijse.dep.MostWantedCabs.Entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> findAll() throws Exception {
        ResultSet rst= CrudUtil.execute("SELECT * FROM user");
        List<User>users=new ArrayList<>();
        while (rst.next()){
            users.add(new User(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4)));
        }
        return users;
    }

    @Override
    public User find(String password) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM user WHERE password=?", password);
        if(rst.next()){
            return new User(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(User entity) throws Exception {
        return CrudUtil.execute("INSERT INTO user VALUES (?,?,?,?)",entity.getUserName(),entity.getPassword(),entity.getAddress(),entity.getContactNo());
    }

    @Override
    public boolean update(User entity) throws Exception {
        return CrudUtil.execute("UPDATE user SET userName=?,address=?,contactNo=? WHERE password=?",entity.getUserName(),entity.getAddress(),entity.getContactNo(),entity.getPassword());
    }

    @Override
    public boolean delete(String password) throws Exception {
        return CrudUtil.execute("DELETE FROM user WHERE password=?",password);
    }

    @Override
    public boolean updatePassword(String newpassword,String oldpassword) throws Exception {
        return CrudUtil.execute("UPDATE user SET password=? WHERE password=?",newpassword,oldpassword);
    }

    @Override
    public boolean existUser(String password) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT password FROM user WHERE password=?", password);
        return rst.next();
    }
}
