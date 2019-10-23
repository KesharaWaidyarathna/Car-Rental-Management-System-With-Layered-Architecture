package lk.ijse.dep.MostWantedCabs.Business.custom.impl;

import lk.ijse.dep.MostWantedCabs.Business.custom.UserBO;
import lk.ijse.dep.MostWantedCabs.DAO.DAOFactory;
import lk.ijse.dep.MostWantedCabs.DAO.DAOType;
import lk.ijse.dep.MostWantedCabs.DAO.custom.UserDAO;
import lk.ijse.dep.MostWantedCabs.DTO.UserDTO;
import lk.ijse.dep.MostWantedCabs.Entity.User;


public class UserBOImpl implements UserBO {

    UserDAO userDAO= DAOFactory.getInstance().getDAO(DAOType.USER);

    @Override
    public UserDTO findUser(String password) throws Exception {
        User user=userDAO.find(password);
        return new UserDTO(user.getUserName(),user.getPassword(),user.getAddress(),user.getContactNo());
    }

    @Override
    public boolean save(UserDTO userDTO) throws Exception {
        return userDAO.save(new User(userDTO.getUserName(),userDTO.getPassword(),userDTO.getAddress(),userDTO.getContactNo()));
    }

    @Override
    public boolean existUser(String password) throws Exception {
        return userDAO.existUser(password);
    }

    @Override
    public boolean resetPassword(String newPassword, String oldpassword ) throws Exception {
        return userDAO.updatePassword(newPassword,oldpassword);
    }
}
