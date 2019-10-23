package lk.ijse.dep.MostWantedCabs.DAO.custom.impl;
import lk.ijse.dep.MostWantedCabs.DAO.CrudUtil;
import lk.ijse.dep.MostWantedCabs.DAO.custom.OwnerDAO;
import lk.ijse.dep.MostWantedCabs.Entity.Owner;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {
    @Override
    public List<Owner> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM owner");
        List<Owner>owners=new ArrayList<>();
        while (rst.next()){
            owners.add(new Owner(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4)));
        }
        return owners;
    }

    @Override
    public Owner find(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM owner WHERE id=?", id);
        if(rst.next()){
            return new Owner(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(Owner entity) throws Exception {
        return CrudUtil.execute("INSERT INTO owner VALUES (?,?,?,?)",entity.getId(),entity.getName(),entity.getContactNo(),entity.getAddress());
    }

    @Override
    public boolean update(Owner entity) throws Exception {
        return CrudUtil.execute("UPDATE owner SET name=?,contactNo=?,address=? WHERE id=?",entity.getName(),entity.getContactNo(),entity.getAddress(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM owner WHERE id=?",id);
    }


    @Override
    public String getLastOwnerID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM owner ORDER BY id DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
