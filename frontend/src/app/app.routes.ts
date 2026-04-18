import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard';
import { CatalogoComponent } from './catalogo/catalogo';
import { RisorseUmaneComponent } from './risorse-umane/risorse-umane';
import { MagazzinoComponent } from './magazzino/magazzino';
import { OrdiniComponent } from './ordini/ordini';
import { authGuard } from './auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/catalogo', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'catalogo', component: CatalogoComponent },
  { path: 'ordini', component: OrdiniComponent, canActivate: [authGuard], data: { roles: ['direttore', 'addetto_vendite', 'magazziniere', 'addetto_produzione', 'cliente'] } },
  { path: 'risorse-umane', component: RisorseUmaneComponent, canActivate: [authGuard], data: { roles: ['direttore', 'addetto_vendite', 'magazziniere', 'addetto_produzione'] } },
  { path: 'magazzino', component: MagazzinoComponent, canActivate: [authGuard], data: { roles: ['direttore', 'magazziniere', 'addetto_produzione'] } },
];
