package com.applications.Multi_Tenant.repository;

import com.applications.Multi_Tenant.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PmlRepository extends JpaRepository<Employee, Long> {
}

