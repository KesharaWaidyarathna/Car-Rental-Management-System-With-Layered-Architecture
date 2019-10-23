package lk.ijse.dep.MostWantedCabs.DAO;

import lk.ijse.dep.MostWantedCabs.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtil {

    public static <T> T execute(String sql, Object... parms) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        for (int i = 0; i <parms.length ; i++) {
            pst.setObject((i+1),parms[i]);
        }
        if(sql.startsWith("SELECT")){
            return (T) pst.executeQuery();
        }
        return (T)((Boolean) (pst.executeUpdate()>0));
    }

}
