import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { NavbarComponent } from '../components/navbar/navbar.component';
import { Local } from 'protractor/built/driverProviders';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private API = 'http://192.168.1.147:8080';
  private User: User;
  constructor(
    private http: HttpClient,
    private router: Router
  ) {
  }
  login(usernameOrEmail: string, password: string) {
    return this.http.post<any>(`${this.API}/api/auth/signin`, { usernameOrEmail, password })
      .pipe(map(res => {
        if (res && res.accessToken) {
          localStorage.setItem('access_token', res.accessToken);
          this.getCurrentUser().subscribe(data => {
            localStorage.setItem('currentUser', JSON.stringify(data));
            location.reload();
          });
        }
      }));
  }

  getCurrentUser() {
    return this.http.get<User>(`${this.API}/api/user/me`);
  }

  currentUser(): User{
    return JSON.parse(localStorage.getItem('currentUser'))
  }


  logout() {
    localStorage.removeItem('access_token');
    localStorage.removeItem('currentUser');
  }
}
