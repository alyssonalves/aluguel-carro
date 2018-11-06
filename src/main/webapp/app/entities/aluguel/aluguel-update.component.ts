import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAluguel } from 'app/shared/model/aluguel.model';
import { AluguelService } from './aluguel.service';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente';
import { IVeiculo } from 'app/shared/model/veiculo.model';
import { VeiculoService } from 'app/entities/veiculo';
import { IReserva } from 'app/shared/model/reserva.model';
import { ReservaService } from 'app/entities/reserva';

@Component({
    selector: 'jhi-aluguel-update',
    templateUrl: './aluguel-update.component.html'
})
export class AluguelUpdateComponent implements OnInit {
    private _aluguel: IAluguel;
    isSaving: boolean;

    cliente_ids: ICliente[];

    veiculo_ids: IVeiculo[];

    reservas: IReserva[];
    data_retiradaDp: any;
    data_prev_devolucaoDp: any;
    data_real_devolucaoDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private aluguelService: AluguelService,
        private clienteService: ClienteService,
        private veiculoService: VeiculoService,
        private reservaService: ReservaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ aluguel }) => {
            this.aluguel = aluguel;
        });
        this.clienteService.query({ filter: 'cliente-is-null' }).subscribe(
            (res: HttpResponse<ICliente[]>) => {
                if (!this.aluguel.cliente_idId) {
                    this.cliente_ids = res.body;
                } else {
                    this.clienteService.find(this.aluguel.cliente_idId).subscribe(
                        (subRes: HttpResponse<ICliente>) => {
                            this.cliente_ids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.veiculoService.query({ filter: 'veiculo-is-null' }).subscribe(
            (res: HttpResponse<IVeiculo[]>) => {
                if (!this.aluguel.veiculo_idId) {
                    this.veiculo_ids = res.body;
                } else {
                    this.veiculoService.find(this.aluguel.veiculo_idId).subscribe(
                        (subRes: HttpResponse<IVeiculo>) => {
                            this.veiculo_ids = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.reservaService.query().subscribe(
            (res: HttpResponse<IReserva[]>) => {
                this.reservas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.aluguel.id !== undefined) {
            this.subscribeToSaveResponse(this.aluguelService.update(this.aluguel));
        } else {
            this.subscribeToSaveResponse(this.aluguelService.create(this.aluguel));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAluguel>>) {
        result.subscribe((res: HttpResponse<IAluguel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackClienteById(index: number, item: ICliente) {
        return item.id;
    }

    trackVeiculoById(index: number, item: IVeiculo) {
        return item.id;
    }

    trackReservaById(index: number, item: IReserva) {
        return item.id;
    }
    get aluguel() {
        return this._aluguel;
    }

    set aluguel(aluguel: IAluguel) {
        this._aluguel = aluguel;
    }
}
