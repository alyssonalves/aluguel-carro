import { NgModule } from '@angular/core';

import { AluguelCarroSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [AluguelCarroSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [AluguelCarroSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class AluguelCarroSharedCommonModule {}
