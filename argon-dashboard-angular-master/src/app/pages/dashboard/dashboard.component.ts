import { Component, OnInit } from '@angular/core';
import { LeaveService } from 'src/app/services/leave.service';
import { Leave } from 'src/app/models/leave';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  private usersLeave: Leave[] = [];
  constructor(
    private leaveService: LeaveService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.getLeaveSubmited();
  }
  getLeaveSubmited() {
    this.leaveService.getLeaveSubmited().subscribe(data => {
      this.usersLeave = data;
    });
  }
}
