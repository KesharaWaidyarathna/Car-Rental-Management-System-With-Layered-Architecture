package lk.ijse.dep.MostWantedCabs.Business.custom;

import lk.ijse.dep.MostWantedCabs.Business.SuperBO;
import lk.ijse.dep.MostWantedCabs.DTO.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customer)throws Exception;

    boolean updateCustomer(CustomerDTO customer)throws Exception;

    boolean deleteCustomer(String customerId) throws Exception;

    List<CustomerDTO> findAllCustomers() throws Exception;

    String getLastCustomerId()throws Exception;

    CustomerDTO findCustomer(String customerId) throws Exception;

    List<String> getAllCustomerIds()throws Exception;
}

