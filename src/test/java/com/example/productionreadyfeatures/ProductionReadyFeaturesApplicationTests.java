package com.example.productionreadyfeatures;

import com.example.productionreadyfeatures.Client.EmployeeClient;
import com.example.productionreadyfeatures.Client.Impl.EmployeeClientImp;
import com.example.productionreadyfeatures.DTO.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //it is use for all the orders are working correctly.
class ProductionReadyFeaturesApplicationTests {

    @Autowired
    private EmployeeClientImp employeeClientImp;

    @Test
    @Order(3) //last execute
    void getAllEmployee() {
         List<EmployeeDTO> employeeDTOS = employeeClientImp.getAllEmployee();
        System.out.println(employeeDTOS);
    }

    @Test
    @Order(2)// mid execute
    void getEmployee(){
        EmployeeDTO employeeDTO = employeeClientImp.getEmployee(100L);
        System.out.println(employeeDTO);
    }

    @Test
    @Order(1)//1st execute
    void createEmp(){
        EmployeeDTO employeeDTO = new EmployeeDTO(null,"dinesh","dinesh@gmail.com",
                2,"USER",2300.65,LocalDate.of(2025,02,23),true);
        EmployeeDTO saveDTO = employeeClientImp.createNewEmployee(employeeDTO);
        System.out.println(saveDTO);
    }

}
