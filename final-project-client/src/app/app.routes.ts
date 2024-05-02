import { Routes } from '@angular/router';
import { PlpComponent } from "./pages/plp/plp.component";
import { PdpComponent } from "./pages/pdp/pdp.component";

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'plp',
    pathMatch: 'full'
  },
  {
    path: 'plp',
    component: PlpComponent
  },
  {
    path: 'pdp/:id',
    component: PdpComponent
  }
];
