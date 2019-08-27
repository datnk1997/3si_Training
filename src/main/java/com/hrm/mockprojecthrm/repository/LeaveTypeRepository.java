package com.hrm.mockprojecthrm.repository;

import com.hrm.mockprojecthrm.entity.LeaveType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends CrudRepository<LeaveType, Long> {

}
