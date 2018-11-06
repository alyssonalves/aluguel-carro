import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IVeiculo } from 'app/shared/model/veiculo.model';
import { VeiculoService } from './veiculo.service';

@Component({
    selector: 'jhi-veiculo-update',
    templateUrl: './veiculo-update.component.html'
})
export class VeiculoUpdateComponent implements OnInit {
    private _veiculo: IVeiculo;
    isSaving: boolean;

    constructor(private veiculoService: VeiculoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ veiculo }) => {
            this.veiculo = veiculo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.veiculo.id !== undefined) {
            this.subscribeToSaveResponse(this.veiculoService.update(this.veiculo));
        } else {
            this.subscribeToSaveResponse(this.veiculoService.create(this.veiculo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVeiculo>>) {
        result.subscribe((res: HttpResponse<IVeiculo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get veiculo() {
        return this._veiculo;
    }

    set veiculo(veiculo: IVeiculo) {
        this._veiculo = veiculo;
    }
}
