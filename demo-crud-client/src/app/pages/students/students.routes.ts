import {Routes} from "@angular/router";
import {StudentListComponent} from "./student-list/student-list.component";
import {StudentNewComponent} from "./student-new/student-new.component";
import {StudentUpdateComponent} from "./student-update/student-update.component";

export const STUDENT_LIST_ROUTES: Routes = [
  {
    path: '',
    component: StudentListComponent
  },
  {
    path: 'new',
    component: StudentNewComponent
  },
  {
    path: ':id/update',
    component: StudentUpdateComponent
  }
]
