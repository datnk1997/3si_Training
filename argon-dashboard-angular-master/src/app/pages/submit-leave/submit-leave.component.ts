import { Component, OnInit } from '@angular/core';
import { LeaveService } from 'src/app/services/leave.service';
import { Leave } from 'src/app/models/leave';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-submit-leave',
  templateUrl: './submit-leave.component.html',
  styleUrls: ['./submit-leave.component.scss']
})
export class SubmitLeaveComponent implements OnInit {

  private status: number = 0;
  private currentUser: User;
  private leaves: Leave[] = [];

  constructor(
    private leaveService: LeaveService,   
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.leaveService.getLeaveByStatus(this.status).subscribe(data => {
      this.leaves = data
    })
    this.currentUser = this.authService.currentUser();
  }
  refuseLeave(leave: Leave) {
    leave.status = 1;
    this.leaveService.submitLeave(leave).subscribe(data=>{
      if(data.success==true){
        this.ngOnInit();
      }
    })
  }
  submitLeave(leave) {
    leave.status = 2
    this.leaveService.submitLeave(leave).subscribe(data=>{
      if(data.success==true){
        this.ngOnInit();
      }
    })
  }
}


