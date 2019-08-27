import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Nationality } from '../models/nationnality';

@Injectable({
  providedIn: 'root'
})
export class NationalityService {
  private API = 'http://192.168.1.147:8080';
  constructor(
    private http: HttpClient
  ) { }
  getNationalities(): Observable<Nationality[]> {
    return this.http.get<Nationality[]>(`${this.API}/api/nationality`);
  }
}
