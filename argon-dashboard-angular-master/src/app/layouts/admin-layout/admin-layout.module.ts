import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ClipboardModule} from 'ngx-clipboard';

import {AdminLayoutRoutes} from './admin-layout.routing';
import {DashboardComponent} from '../../pages/dashboard/dashboard.component';
import {IconsComponent} from '../../pages/icons/icons.component';
import {UserProfileComponent} from '../../pages/user-profile/user-profile.component'
import {TablesComponent} from '../../pages/tables/tables.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AssignLeaveComponent } from 'src/app/pages/assign-leave/assign-leave.component';
import { SubmitLeaveComponent } from 'src/app/pages/submit-leave/submit-leave.component';
import {CandidatesListComponent} from '../../pages/candidates-list/candidates-list.component';
import {CandidatesDetailComponent} from '../../pages/candidates-detail/candidates-detail.component';
import {UserListComponent} from "../../pages/user-list/user-list.component";

// import { ToastrModule } from 'ngx-toastr';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
    ClipboardModule,
    ReactiveFormsModule
  ],
  declarations: [
    DashboardComponent,
    TablesComponent,
    IconsComponent,
    AssignLeaveComponent,
    UserProfileComponent,
    SubmitLeaveComponent,
    CandidatesListComponent,
    CandidatesDetailComponent,
    UserListComponent,
  ]
})

export class AdminLayoutModule {
}
