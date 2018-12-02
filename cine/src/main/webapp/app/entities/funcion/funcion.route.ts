import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Funcion } from 'app/shared/model/funcion.model';
import { FuncionService } from './funcion.service';
import { FuncionComponent } from './funcion.component';
import { FuncionDetailComponent } from './funcion-detail.component';
import { FuncionUpdateComponent } from './funcion-update.component';
import { FuncionDeletePopupComponent } from './funcion-delete-dialog.component';
import { IFuncion } from 'app/shared/model/funcion.model';

@Injectable({ providedIn: 'root' })
export class FuncionResolve implements Resolve<IFuncion> {
    constructor(private service: FuncionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Funcion> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Funcion>) => response.ok),
                map((funcion: HttpResponse<Funcion>) => funcion.body)
            );
        }
        return of(new Funcion());
    }
}

export const funcionRoute: Routes = [
    {
        path: 'funcion',
        component: FuncionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.funcion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'funcion/:id/view',
        component: FuncionDetailComponent,
        resolve: {
            funcion: FuncionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.funcion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'funcion/new',
        component: FuncionUpdateComponent,
        resolve: {
            funcion: FuncionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.funcion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'funcion/:id/edit',
        component: FuncionUpdateComponent,
        resolve: {
            funcion: FuncionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.funcion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const funcionPopupRoute: Routes = [
    {
        path: 'funcion/:id/delete',
        component: FuncionDeletePopupComponent,
        resolve: {
            funcion: FuncionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'cineApp.funcion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
