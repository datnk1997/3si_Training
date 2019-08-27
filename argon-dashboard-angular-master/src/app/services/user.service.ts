import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../models/user';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApiResponse } from '../models/apiResponse';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private API = 'http://192.168.1.147:8080';

  constructor(
    private http: HttpClient
  ) {
  }
  updateUser(user): Observable<User> {
    return this.http.put<User>(`${this.API}/api/user`, user);
  }

  getUserList(): Observable<User[]> {
    return this.http.get<User[]>(`${this.API}/api/users`);
  }

  getInfoUser(): Observable<User> {
    return this.http.get<User>(`${this.API}/api/info-user`);
  }

}
