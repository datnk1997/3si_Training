package com.hrm.mockprojecthrm.repository;

import com.hrm.mockprojecthrm.entity.Leave;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends CrudRepository<Leave, Long> {
 List<Leave> findByStatus(Integer status);
}
