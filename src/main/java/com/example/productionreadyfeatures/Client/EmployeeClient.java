package com.example.productionreadyfeatures.Client;

import com.example.productionreadyfeatures.DTO.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployee(Long id);
    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
