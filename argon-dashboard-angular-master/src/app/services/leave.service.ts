import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Leave } from '../models/leave';
import { ApiResponse } from '../models/apiResponse';
import { map } from 'rxjs/operators';
import { load } from '@angular/core/src/render3';
import { Local } from 'protractor/built/driverProviders';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {
  private API = 'http://192.168.1.147:8080';

  constructor(
    private http: HttpClient
  ) { }
  getLeaveSubmited(): Observable<Leave[]> {
    return this.http.get<Leave[]>(`${this.API}/api/leave`);
  }

  createLeave(leave: Leave): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.API}/api/leave`, leave);
  }

  getLeaveByStatus(status: number): Observable<Leave[]> {
    return this.http.get<Leave[]>(`${this.API}/api/leave/${status}`);
  }
  submitLeave(leave: Leave): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.API}/api/leave`,leave);
    
  }
}
