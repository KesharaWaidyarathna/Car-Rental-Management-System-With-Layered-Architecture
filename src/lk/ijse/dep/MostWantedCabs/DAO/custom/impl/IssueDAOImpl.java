package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;
import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.IssueDAO;
import lk.ijse.dep.MostWantedCabs.Entity.Issue;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IssueDAOImpl implements IssueDAO {

    @Override
    public List<Issue> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM issue");
        List<Issue>issues=new ArrayList<>();
        while (rst.next()){
            issues.add(new Issue(rst.getString(1),rst.getDate(2),rst.getString(3),
                    rst.getString(4),rst.getString(5)));
        }
        return issues;
    }

    @Override
    public Issue find(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM issue WHERE id=?", id);
        if(rst.next()){
            return new Issue(rst.getString(1),rst.getDate(2),rst.getString(3),
                    rst.getString(4),rst.getString(5));
        }
        return null;
    }

    @Override
    public boolean save(Issue entity) throws Exception {
        return CrudUtil.execute("INSERT INTO issue VALUES (?,?,?,?,?)",entity.getId(),entity.getDate(),entity.getVehicleId(),entity.getCustomerId(),entity.getStatues());
    }

    @Override
    public boolean update(Issue entity) throws Exception {
        return CrudUtil.execute("UPDATE issue SET date=?,vehicleId=?,customerId=?,statues=? WHERE id=?",entity.getDate(),entity.getVehicleId(),entity.getCustomerId(),entity.getStatues(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM issue WHERE id=?",id);
    }

    @Override
    public String getLastIssueID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM issue ORDER BY id DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean existCustomerId(String customerId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT customerId FROM issue WHERE customerId=?", customerId);
        return rst.next();
    }

    @Override
    public boolean existVehicleId(String vehicleId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT vehicleId FROM issue WHERE vehicleId=?", vehicleId);
        return rst.next();
    }

    @Override
    public List<Issue> findallIssueIds(String statues) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Issue WHERE Issue.statues=?",statues);
        List<Issue>issues=new ArrayList<>();
        while (rst.next()){
            issues.add(new Issue(rst.getString(1),rst.getDate(2),rst.getString(3),
                    rst.getString(4),rst.getString(5)));
        }
        return issues;
    }
}
