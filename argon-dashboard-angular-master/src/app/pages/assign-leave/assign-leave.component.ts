import {Component, OnInit} from '@angular/core';
import {Leave} from 'src/app/models/leave';
import {LeaveTypeService} from 'src/app/services/leave-type.service';
import {LeaveType} from 'src/app/models/leaveType';
import {LeaveService} from 'src/app/services/leave.service';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-assign-leave',
  templateUrl: './assign-leave.component.html',
  styleUrls: ['./assign-leave.component.scss']
})
export class AssignLeaveComponent implements OnInit {
  private leave: Leave = new Leave;
  private addForm: FormGroup;
  private leaveTypes: LeaveType[] = [];
  private message: string;

  constructor(
    private leaveTypeService: LeaveTypeService,
    private leaveService: LeaveService,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [0],
      userId: [1],
      status: [0],
      leaveTypeId: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      comment: ['', Validators.required],
      isDelete: [false, Validators.required],
    });
    this.leaveTypeService.getListLeaveType().subscribe(data => {
      this.leaveTypes = data;
    });
  }

  onSubmit() {
    this.leaveService.createLeave(this.addForm.value).subscribe(data => {
      if (data.success == true) {
        this.message = data.message
      }else{
        this.message = null;
        console.log('Tao Don Xin Nghi Khong Thanh Cong')
      }
    });
  }

}
