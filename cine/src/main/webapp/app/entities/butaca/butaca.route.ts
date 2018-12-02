import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Butaca } from 'app/shared/model/butaca.model';
import { ButacaService } from './butaca.service';
import { ButacaComponent } from './butaca.component';
import { ButacaDetailComponent } from './butaca-detail.component';
import { ButacaUpdateComponent } from './butaca-update.component';
import { ButacaDeletePopupComponent } from './butaca-delete-dialog.component';
import { IButaca } from 'app/shared/model/butaca.model';

@Injectable({ providedIn: 'root' })
export class ButacaResolve implements Resolve<IButaca> {
    constructor(private service: ButacaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Butaca> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Butaca>) => response.ok),
                map((butaca: HttpResponse<Butaca>) => butaca.body)
            );
        }
        return of(new Butaca());
    }
}

export const butacaRoute: Routes = [
    {
        path: 'butaca',
        component: ButacaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.butaca.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'butaca/:id/view',
        component: ButacaDetailComponent,
        resolve: {
            butaca: ButacaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.butaca.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'butaca/new',
        component: ButacaUpdateComponent,
        resolve: {
            butaca: ButacaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.butaca.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'butaca/:id/edit',
        component: ButacaUpdateComponent,
        resolve: {
            butaca: ButacaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.butaca.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const butacaPopupRoute: Routes = [
    {
        path: 'butaca/:id/delete',
        component: ButacaDeletePopupComponent,
        resolve: {
            butaca: ButacaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.butaca.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
