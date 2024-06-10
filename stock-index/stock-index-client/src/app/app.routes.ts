import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'indexes'
  },
  {
    path: 'indexes',
    pathMatch: 'prefix',
    loadChildren: () => import('./stock-index/stock-index.routes').then(r => r.stockIndexRoutes)
  }
];
