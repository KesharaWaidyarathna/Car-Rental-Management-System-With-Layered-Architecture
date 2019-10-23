package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;

import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.ReturnDAO;
import lk.ijse.dep.MostWantedCabs.Entity.Return;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReturnDAOImpl implements ReturnDAO {
    @Override
    public List<Return> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `return`");
        List<Return>returns=new ArrayList<>();
        while (rst.next()){
            returns.add(new Return(rst.getString(1),rst.getDate(2),rst.getInt(3),
                    rst.getDouble(4),rst.getDouble(5)));
        }
        return returns;
    }

    @Override
    public Return find(String issueid) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `return` WHERE issueId=?", issueid);
        if(rst.next()){
            return new Return(rst.getString(1),rst.getDate(2),rst.getInt(3),
                    rst.getDouble(4),rst.getDouble(5));
        }
        return null;
    }

    @Override
    public boolean save(Return entity) throws Exception {
        return CrudUtil.execute("INSERT INTO `return` VALUES (?,?,?,?,?)",entity.getIssueId(),entity.getReturnDate(),entity.getAdditionalDistance(),entity.getDamageCost(),entity.getTotal());
    }

    @Override
    public boolean update(Return entity) throws Exception {
        return CrudUtil.execute("UPDATE `return` SET returnDate=?,additionalDistance=?,damageCost=?,total=? WHERE issueId=?",entity.getReturnDate(),entity.getAdditionalDistance(),entity.getDamageCost(),entity.getTotal(),entity.getIssueId());
    }

    @Override
    public boolean delete(String issueid) throws Exception {
        return CrudUtil.execute("DELETE FROM `return` WHERE id=?",issueid);
    }
}
