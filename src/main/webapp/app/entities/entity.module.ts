import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AluguelCarroClienteModule } from './cliente/cliente.module';
import { AluguelCarroVeiculoModule } from './veiculo/veiculo.module';
import { AluguelCarroReservaModule } from './reserva/reserva.module';
import { AluguelCarroAluguelModule } from './aluguel/aluguel.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        AluguelCarroClienteModule,
        AluguelCarroVeiculoModule,
        AluguelCarroReservaModule,
        AluguelCarroAluguelModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AluguelCarroEntityModule {}
