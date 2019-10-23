package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;

import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.IssueDetailDAO;
import lk.ijse.dep.MostWantedCabs.Entity.Issue;
import lk.ijse.dep.MostWantedCabs.Entity.IssueDetail;
import lk.ijse.dep.MostWantedCabs.Entity.IssueDetailPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IssueDetailDAOImpl implements IssueDetailDAO {

    @Override
    public List<IssueDetail> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM issuedetail");
        List<IssueDetail>issueDetails=new ArrayList<>();
        while (rst.next()){
            issueDetails.add(new IssueDetail(rst.getString(1),rst.getString(2)));
        }
        return issueDetails;
    }

    @Override
    public IssueDetail find(IssueDetailPK issueDetailPK) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM issuedetail WHERE issueId=?", issueDetailPK.getIssueId());
        if(rst.next()){
            return new IssueDetail(rst.getString(1),rst.getString(2));
        }
        return null;
    }

    @Override
    public boolean save(IssueDetail entity) throws Exception {
        return CrudUtil.execute("INSERT INTO issuedetail VALUES (?,?)",entity.getIssueDetailPK().getIssueId(),entity.getIssueDetailPK().getDriverId());
    }

    @Override
    public boolean update(IssueDetail entity) throws Exception {
        return CrudUtil.execute("UPDATE issuedetail SET driverId=? WHERE issueId=?",entity.getIssueDetailPK().getDriverId(),entity.getIssueDetailPK().getIssueId());
    }

    @Override
    public boolean delete(IssueDetailPK issueDetailPK) throws Exception {
        return CrudUtil.execute("DELETE FROM issuedetail WHERE issueId=?",issueDetailPK);
    }

    @Override
    public boolean existVehicleId(String driverId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT driverId FROM issuedetail WHERE driverId=?", driverId);
        return rst.next();
    }

    @Override
    public String getDriverId(String issueId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT driverId FROM issuedetail WHERE issueId=?",issueId);
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
