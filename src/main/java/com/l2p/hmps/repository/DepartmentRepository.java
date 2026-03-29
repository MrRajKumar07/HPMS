package com.l2p.hmps.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.l2p.hmps.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}