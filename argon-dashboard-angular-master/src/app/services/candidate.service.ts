import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Candidate} from '../models/candidate';

@Injectable({
  providedIn: 'root'
})
export class CandidateService {
  private API = 'http://192.168.1.147:8080';
  constructor(
    private http: HttpClient) { }
  getCandidate(id: number): Observable<any> {
    return this.http.get(`${this.API}/${id}`);
  }
  createCandidate(candidate: Object): Observable<Object> {
    return this.http.post(`${this.API}`, candidate);
  }
  updateCandidate(id: number, value: any): Observable<any> {
    return this.http.put(`${this.API}/${id}`, value);
  }
  deleteCandidate(id: number): Observable<any> {
    return this.http.delete(`${this.API}/api/candidate/${id}`);
  }
  getCandidateList(): Observable<Candidate[]> {
    return this.http.get<Candidate[]>(`${this.API}/api/candidate`);
  }
}
