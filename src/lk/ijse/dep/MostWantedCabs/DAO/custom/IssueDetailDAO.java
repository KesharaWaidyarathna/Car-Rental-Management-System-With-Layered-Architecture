package lk.ijse.dep.MostWantedCabs.DAO.custom;

import lk.ijse.dep.MostWantedCabs.DAO.CrudDAO;
import lk.ijse.dep.MostWantedCabs.Entity.IssueDetail;
import lk.ijse.dep.MostWantedCabs.Entity.IssueDetailPK;

public interface IssueDetailDAO extends CrudDAO<IssueDetail, IssueDetailPK> {
    boolean existVehicleId(String driverId)throws Exception;
    String getDriverId(String driverID)throws  Exception;
}
