package com.example.productionreadyfeatures.Client.Impl;

import com.example.productionreadyfeatures.Client.EmployeeClient;
import com.example.productionreadyfeatures.DTO.EmployeeDTO;
import com.example.productionreadyfeatures.advice.ApiResponse;
import com.example.productionreadyfeatures.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.ParameterizedType;
import java.util.List;
@RequiredArgsConstructor //this basically add required constructor.
@Service
public class EmployeeClientImp implements EmployeeClient {

    private final RestClient restClient;

    private static final Logger log = LoggerFactory.getLogger(EmployeeClientImp.class);

    @Override
    public List<EmployeeDTO> getAllEmployee() {


//        log.error("error log");
//        log.warn("warn log");
//        log.debug("debug log");
//        log.trace("trace log");
//        log.info("info log");


        /*  ERROR (highest priority)
            WARN
            INFO (default level)
            DEBUG
            TRACE (lowest priority)*/

        try {
            log.trace("trying to retrieve the data ");
            ApiResponse<List<EmployeeDTO>> employeeDTOS = restClient
                    .get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes())); //we are get the error from the server.(EmployeeData (pro))
                        throw new ResourceNotFoundException("could not get all employee.");
                    })
                    .body(new ParameterizedTypeReference<>(){});
            log.debug("Successfully retrieve all data");
            log.trace("{},{},{}",employeeDTOS.getData(),5,"hello");
            return employeeDTOS.getData();
        }catch (Exception e){
            log.error("Exception occurred in getAllEmployee.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployee(Long empId) {
        log.trace("trying to get employee by id: {}",empId);
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient.get()
                    .uri("employees/{employeeId}",empId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not get the given employee.");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOApiResponse.getData();
        }catch (Exception e){
            log.error("Exception occurred in getEmployeeById : ",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("trying to create new employee");
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.debug("4xx clint error occurred.");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee.");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.trace("Successfully created new Employee");
            return employeeDTOApiResponse.getBody().getData();
        }catch (Exception e){
            log.error("Exception occurred in createNewEmployee : ",e);
            throw new RuntimeException(e);
        }
    }


}
