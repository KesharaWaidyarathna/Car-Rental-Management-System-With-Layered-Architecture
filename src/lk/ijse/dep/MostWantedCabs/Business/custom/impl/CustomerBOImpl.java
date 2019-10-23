package lk.ijse.dep.MostWantedCabs.Business.custom.impl;

import lk.ijse.dep.MostWantedCabs.Business.custom.CustomerBO;
import lk.ijse.dep.MostWantedCabs.Business.exception.AlreadyExist;
import lk.ijse.dep.MostWantedCabs.DAO.DAOFactory;
import lk.ijse.dep.MostWantedCabs.DAO.DAOType;
import lk.ijse.dep.MostWantedCabs.DAO.custom.CustomerDAO;
import lk.ijse.dep.MostWantedCabs.DAO.custom.IssueDAO;
import lk.ijse.dep.MostWantedCabs.DTO.CustomerDTO;
import lk.ijse.dep.MostWantedCabs.Entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO= DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
    private IssueDAO issueDAO=DAOFactory.getInstance().getDAO(DAOType.ISSUE);

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.save(new Customer(customer.getId(),customer.getName(),customer.getAddress(),customer.getLicenseNo(),customer.getContactNo()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer(customer.getId(),customer.getName(),customer.getAddress(),customer.getLicenseNo(),customer.getContactNo()));
    }

    @Override
    public boolean deleteCustomer(String customerId) throws Exception {
        if(issueDAO.existCustomerId(customerId)){
            throw new AlreadyExist("Customer already exists in an Issue, hence unable to delete !");
        }
        return customerDAO.delete(customerId);
    }

    @Override
    public List<CustomerDTO> findAllCustomers() throws Exception {
        List<Customer> alcustomer=customerDAO.findAll();
        List<CustomerDTO>customerDTOS=new ArrayList<>();
        for (Customer customer : alcustomer) {
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getLicenseNo(),customer.getContactNo()));
        }
        return customerDTOS;
    }

    @Override
    public String getLastCustomerId() throws Exception {
        return customerDAO.getLasCustomerId();
    }

    @Override
    public CustomerDTO findCustomer(String customerId) throws Exception {
        Customer customer=customerDAO.find(customerId);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getLicenseNo(),customer.getContactNo());
    }

    @Override
    public List<String> getAllCustomerIds() throws Exception {
        List<Customer>customer=customerDAO.findAll();
        List<String>customerid=new ArrayList<>();
        for (Customer customer1 : customer) {
            customerid.add(customer1.getId());
        }
        return customerid;
    }
}
