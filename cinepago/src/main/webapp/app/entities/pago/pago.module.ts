import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CinepagoSharedModule } from 'app/shared';
import {
    PagoComponent,
    PagoDetailComponent,
    PagoUpdateComponent,
    PagoDeletePopupComponent,
    PagoDeleteDialogComponent,
    pagoRoute,
    pagoPopupRoute
} from './';

const ENTITY_STATES = [...pagoRoute, ...pagoPopupRoute];

@NgModule({
    imports: [CinepagoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PagoComponent, PagoDetailComponent, PagoUpdateComponent, PagoDeleteDialogComponent, PagoDeletePopupComponent],
    entryComponents: [PagoComponent, PagoUpdateComponent, PagoDeleteDialogComponent, PagoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CinepagoPagoModule {}
