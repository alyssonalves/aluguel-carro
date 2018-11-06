import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAluguel } from 'app/shared/model/aluguel.model';
import { Principal } from 'app/core';
import { AluguelService } from './aluguel.service';

@Component({
    selector: 'jhi-aluguel',
    templateUrl: './aluguel.component.html'
})
export class AluguelComponent implements OnInit, OnDestroy {
    aluguels: IAluguel[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private aluguelService: AluguelService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.aluguelService.query().subscribe(
            (res: HttpResponse<IAluguel[]>) => {
                this.aluguels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAluguels();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAluguel) {
        return item.id;
    }

    registerChangeInAluguels() {
        this.eventSubscriber = this.eventManager.subscribe('aluguelListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
