import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAluguel } from 'app/shared/model/aluguel.model';

@Component({
    selector: 'jhi-aluguel-detail',
    templateUrl: './aluguel-detail.component.html'
})
export class AluguelDetailComponent implements OnInit {
    aluguel: IAluguel;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ aluguel }) => {
            this.aluguel = aluguel;
        });
    }

    previousState() {
        window.history.back();
    }
}
