import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LeaveType } from '../models/leaveType';

@Injectable({
  providedIn: 'root'
})
export class LeaveTypeService {
  private API = 'http://192.168.1.147:8080';
  


  constructor(
    private http: HttpClient
  ) { }

  getListLeaveType(): Observable<LeaveType[]> {
    return this.http.get<any>(`${this.API}/api/leave-type`);
  }
}
