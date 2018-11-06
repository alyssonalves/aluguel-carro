import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Aluguel } from 'app/shared/model/aluguel.model';
import { AluguelService } from './aluguel.service';
import { AluguelComponent } from './aluguel.component';
import { AluguelDetailComponent } from './aluguel-detail.component';
import { AluguelUpdateComponent } from './aluguel-update.component';
import { AluguelDeletePopupComponent } from './aluguel-delete-dialog.component';
import { IAluguel } from 'app/shared/model/aluguel.model';

@Injectable({ providedIn: 'root' })
export class AluguelResolve implements Resolve<IAluguel> {
    constructor(private service: AluguelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((aluguel: HttpResponse<Aluguel>) => aluguel.body));
        }
        return of(new Aluguel());
    }
}

export const aluguelRoute: Routes = [
    {
        path: 'aluguel',
        component: AluguelComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aluguels'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'aluguel/:id/view',
        component: AluguelDetailComponent,
        resolve: {
            aluguel: AluguelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aluguels'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'aluguel/new',
        component: AluguelUpdateComponent,
        resolve: {
            aluguel: AluguelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aluguels'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'aluguel/:id/edit',
        component: AluguelUpdateComponent,
        resolve: {
            aluguel: AluguelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aluguels'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const aluguelPopupRoute: Routes = [
    {
        path: 'aluguel/:id/delete',
        component: AluguelDeletePopupComponent,
        resolve: {
            aluguel: AluguelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Aluguels'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
