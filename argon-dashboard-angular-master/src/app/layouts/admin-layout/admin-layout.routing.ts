import {Routes} from '@angular/router';

import {DashboardComponent} from '../../pages/dashboard/dashboard.component';
import {IconsComponent} from '../../pages/icons/icons.component';
import {UserProfileComponent} from '../../pages/user-profile/user-profile.component';
import {TablesComponent} from '../../pages/tables/tables.component';
import {AssignLeaveComponent} from 'src/app/pages/assign-leave/assign-leave.component';
import {CandidatesListComponent} from '../../pages/candidates-list/candidates-list.component';
import {SubmitLeaveComponent} from 'src/app/pages/submit-leave/submit-leave.component';
import {UserListComponent} from "../../pages/user-list/user-list.component";


export const AdminLayoutRoutes: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  {path: 'user-profile', component: UserProfileComponent},
  {path: 'tables', component: TablesComponent},
  {path: 'icons', component: IconsComponent},
  {path: 'assignleave', component: AssignLeaveComponent},
  {path: 'submitleave', component: SubmitLeaveComponent},
  {path: 'candidates-list', component: CandidatesListComponent},
  {path: 'user-list', component: UserListComponent},
];
