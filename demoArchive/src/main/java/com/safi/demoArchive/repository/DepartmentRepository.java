package com.safi.demoArchive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safi.demoArchive.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
