import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'students',
    pathMatch: 'full'
  },
  {
    path: 'students',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/students/students.routes').then(r => r.STUDENT_LIST_ROUTES)
  }
];
