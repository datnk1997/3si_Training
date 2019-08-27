package com.hrm.mockprojecthrm.controller;

import com.hrm.mockprojecthrm.dto.LeaveDTO;
import com.hrm.mockprojecthrm.payload.ApiResponse;
import com.hrm.mockprojecthrm.security.CurrentUser;
import com.hrm.mockprojecthrm.security.UserPrincipal;
import com.hrm.mockprojecthrm.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @PostMapping(value = "/leave")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR", "ROLE_USER"})
    public ResponseEntity<?> createLeave(@Valid @RequestBody LeaveDTO leaveDTO, BindingResult result,@CurrentUser UserPrincipal userPrincipal) {
        if(result.hasErrors()){
            return new ResponseEntity<>(new ApiResponse(false, "Create Leave False"), HttpStatus.OK);
        }
        leaveService.createLeave(leaveDTO, userPrincipal);
        return new ResponseEntity<>(new ApiResponse(true, "Create Leave Success !!"), HttpStatus.CREATED);
    }

    @GetMapping(value = "/leave")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR", "ROLE_USER"})
    public ResponseEntity<?> getAllLeave() {
        return new ResponseEntity<>(leaveService.getLeaveSubmited(), HttpStatus.OK);
    }

    @PutMapping(value = "/leave")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR"})
    public ResponseEntity<?> updateLeave(@RequestBody LeaveDTO leaveDTO) {
        leaveService.updateLeave(leaveDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Update Leave Success !!"), HttpStatus.OK);
    }

    @GetMapping(value = "/leave-type")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR", "ROLE_USER"})
    public ResponseEntity<?> getAllLeaveType() {
        return new ResponseEntity<>(leaveService.getAllLeaveType(), HttpStatus.OK);
    }

    @GetMapping(value = "/leave/{status}")
    @Secured({"ROLE_ADMIN", "ROLE_USERHR"})
    public ResponseEntity<?> getLeaveByStatus(@PathVariable Integer status) {
        return new ResponseEntity<>(leaveService.getLeaveByStatus(status), HttpStatus.OK);
    }
}
