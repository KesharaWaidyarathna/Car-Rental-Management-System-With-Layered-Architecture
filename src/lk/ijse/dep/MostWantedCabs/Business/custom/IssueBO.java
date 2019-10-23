package lk.ijse.dep.MostWantedCabs.Business.custom;

import lk.ijse.dep.MostWantedCabs.Business.SuperBO;
import lk.ijse.dep.MostWantedCabs.DTO.IssueDTO;
import lk.ijse.dep.MostWantedCabs.DTO.IssueDetailDTO;
import lk.ijse.dep.MostWantedCabs.DTO.IssueInfoDTO;
import lk.ijse.dep.MostWantedCabs.Entity.IssueDetail;

import java.util.List;

public interface IssueBO extends SuperBO {

    String getLastIssueId()throws Exception;

    List<String> getAllIssueIds()throws Exception;

    boolean issueVehicle(IssueDTO issue)throws Exception;

    List<String> findAllNotReturnId(String status)throws Exception;

    boolean updateIssue(IssueInfoDTO infoDTOS)throws Exception;



    IssueDTO findIssue(String issueId) throws Exception;


    List<IssueInfoDTO> getIssueInfo()throws Exception;



}
