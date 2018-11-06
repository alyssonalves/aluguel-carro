import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AluguelCarroSharedModule } from 'app/shared';
import {
    AluguelComponent,
    AluguelDetailComponent,
    AluguelUpdateComponent,
    AluguelDeletePopupComponent,
    AluguelDeleteDialogComponent,
    aluguelRoute,
    aluguelPopupRoute
} from './';

const ENTITY_STATES = [...aluguelRoute, ...aluguelPopupRoute];

@NgModule({
    imports: [AluguelCarroSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AluguelComponent,
        AluguelDetailComponent,
        AluguelUpdateComponent,
        AluguelDeleteDialogComponent,
        AluguelDeletePopupComponent
    ],
    entryComponents: [AluguelComponent, AluguelUpdateComponent, AluguelDeleteDialogComponent, AluguelDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AluguelCarroAluguelModule {}
