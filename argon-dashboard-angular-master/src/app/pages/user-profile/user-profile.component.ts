import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { NationalityService } from 'src/app/services/nationality.service';
import { Nationality } from 'src/app/models/nationnality';
import { AdminLayoutComponent } from 'src/app/layouts/admin-layout/admin-layout.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  private addForm: FormGroup;
  private currentUser: User = new User();
  private user: User;
  private nationals: Nationality[] = [];
  private message: string;
  private infoUser: User;
  private ageUser: number;

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private naltionalService: NationalityService
  ) { }

  ngOnInit() {
    this.getInfoUser();
    this.currentUser = this.authService.currentUser();
    this.addForm = this.formBuilder.group({
      id: [this.currentUser.id],
      name: [this.currentUser.name, Validators.required],
      birthday: [this.currentUser.birthday, Validators.required],
      gender: [this.currentUser.gender, Validators.required],
      maritalStatus: [this.currentUser.maritalStatus, Validators.required],
      nationality: [this.currentUser.nationality, Validators.required],
    })
    this.getNationalities();
  }
  getInfoUser() {
    this.userService.getInfoUser().subscribe(user => {
      this.infoUser = user;
      this.ageUser = new Date().getFullYear() - new Date(this.infoUser.birthday).getFullYear();
      console.log(this.ageUser);
    })
  }
  onSubmit() {
    this.userService.updateUser(this.addForm.value).subscribe(data => {
      if (data != null) {
        this.user = data;
        this.authService.getCurrentUser().subscribe(data => {
          localStorage.setItem('currentUser', JSON.stringify(data))
        })
        this.ngOnInit;
        this.message = 'Cập Nhật Thông Tin Thành Công';
      } else {
        this.message = null;
        console.log('Cap Nhat Tai Khoan Khong Thanh Cong')
      }
    })
  }
  getNationalities() {
    this.naltionalService.getNationalities().subscribe(national => {
      this.nationals = national
    })
  }


}
