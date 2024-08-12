package com.applications.Multi_Tenant.service;
import com.applications.Multi_Tenant.entity.Employee;
import com.applications.Multi_Tenant.repository.PmlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DataTransferService {

    private final PmlRepository pmlRepository;


    public DataTransferService(PmlRepository pmlRepository) {
        this.pmlRepository = pmlRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return pmlRepository.save(employee);
    }


    public List<Employee> transferData() {

        return pmlRepository.findAll();
    }


    @Transactional
    public void saveData(List<Employee> employees) {
        pmlRepository.saveAll(employees);
    }

}