package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;

import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.QuaryDAO;
import lk.ijse.dep.MostWantedCabs.Entity.CustomEntity;
import lk.ijse.dep.MostWantedCabs.Entity.CustomReturnEntity;
import lk.ijse.dep.MostWantedCabs.Entity.Vehicle;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuaryDAOImpl implements QuaryDAO {

    @Override
    public List<CustomEntity> getIssueinfo() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT issue.id,issue.date,issue.customerId,customer.name,issue.vehicleId,vehicle.modelName,issuedetail.driverId,issue.statues FROM issue LEFT JOIN issuedetail  on issue.id = issuedetail.issueId INNER JOIN " +
                "vehicle on issue.vehicleId = vehicle.id INNER JOIN customer on issue.customerId = customer.id GROUP BY issue.id");
        List<CustomEntity>issueinfo=new ArrayList<>();
        while (rst.next()){
            issueinfo.add(new CustomEntity(rst.getString(1),rst.getDate(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8)));
        }
        return issueinfo;
    }

    @Override
    public boolean updateStatues(CustomEntity customEntity) throws Exception {
        return CrudUtil.execute("UPDATE issue SET statues=? WHERE id=?",customEntity.getStatues(),customEntity.getId());
    }

    @Override
    public List<CustomReturnEntity> getReturnInfo() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT issue.date,`return`.returnDate,`return`.issueId,`return`.additionalDistance,`return`.damageCost,vehicle.modelName,customer.name,`return`.total FROM `return` INNER JOIN issue on `return`.issueId = issue.id INNER JOIN vehicle on issue.vehicleId = vehicle.id INNER JOIN customer on issue.customerId =customer.id ORDER BY `return`.issueId");
        List<CustomReturnEntity>returnEntities=new ArrayList<>();
        while (rst.next()){
            returnEntities.add(new CustomReturnEntity(rst.getDate(1),rst.getDate(2),rst.getString(3),rst.getString(4),rst.getDouble(5),
                    rst.getString(6),rst.getString(7),rst.getDouble(8)));
        }
        return returnEntities;
    }

    @Override
    public List<Vehicle> SearchVehicle(String search) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM vehicle WHERE id LIKE ? OR registerNo LIKE ? OR categoryId LIKE ? OR modelName LIKE ? OR statues LIKE ? OR ownerId LIKE ? GROUP by id", search, search, search, search, search, search);
        List<Vehicle> vehicles=new ArrayList<>();
        while (rst.next()){
            vehicles.add(new Vehicle(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
        }
        return vehicles;
    }


}
