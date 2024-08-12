package com.applications.Multi_Tenant.controller;

import com.applications.Multi_Tenant.entity.Employee;
import com.applications.Multi_Tenant.service.DataTransferService;
import com.applications.Multi_Tenant.tenantmangement.TenantContext;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class EmployeeController {


    private final DataTransferService dataTransferService;

    public EmployeeController(DataTransferService dataTransferService) {
        this.dataTransferService = dataTransferService;
    }

    @PostMapping("/employee")
    public Employee savedEmployee(@RequestBody Employee employee) {
        return dataTransferService.saveEmployee(employee);
    }


    @PostMapping("/transfer")
    public void transferData() {
        TenantContext.setTenantId("pml");
//        currentTenantIdentifierResolver.resolveCurrentTenantIdentifier();
        List<Employee> employees = dataTransferService.transferData();
        TenantContext.clear();

        TenantContext.setTenantId("sagar-travel");
        dataTransferService.saveData(employees);
        TenantContext.clear();
    }
}