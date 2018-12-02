import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { CinepagoClienteModule } from './cliente/cliente.module';
import { CinepagoTarjetaModule } from './tarjeta/tarjeta.module';
import { CinepagoPagoModule } from './pago/pago.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        CinepagoClienteModule,
        CinepagoTarjetaModule,
        CinepagoPagoModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CinepagoEntityModule {}
