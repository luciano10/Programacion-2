import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CinepagoSharedModule } from 'app/shared';
import {
    TarjetaComponent,
    TarjetaDetailComponent,
    TarjetaUpdateComponent,
    TarjetaDeletePopupComponent,
    TarjetaDeleteDialogComponent,
    tarjetaRoute,
    tarjetaPopupRoute
} from './';

const ENTITY_STATES = [...tarjetaRoute, ...tarjetaPopupRoute];

@NgModule({
    imports: [CinepagoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TarjetaComponent,
        TarjetaDetailComponent,
        TarjetaUpdateComponent,
        TarjetaDeleteDialogComponent,
        TarjetaDeletePopupComponent
    ],
    entryComponents: [TarjetaComponent, TarjetaUpdateComponent, TarjetaDeleteDialogComponent, TarjetaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CinepagoTarjetaModule {}
