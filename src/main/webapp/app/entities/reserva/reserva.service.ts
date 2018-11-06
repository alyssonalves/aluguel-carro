import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReserva } from 'app/shared/model/reserva.model';

type EntityResponseType = HttpResponse<IReserva>;
type EntityArrayResponseType = HttpResponse<IReserva[]>;

@Injectable({ providedIn: 'root' })
export class ReservaService {
    private resourceUrl = SERVER_API_URL + 'api/reservas';

    constructor(private http: HttpClient) {}

    create(reserva: IReserva): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reserva);
        return this.http
            .post<IReserva>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(reserva: IReserva): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reserva);
        return this.http
            .put<IReserva>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IReserva>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IReserva[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(reserva: IReserva): IReserva {
        const copy: IReserva = Object.assign({}, reserva, {
            data_inicial: reserva.data_inicial != null && reserva.data_inicial.isValid() ? reserva.data_inicial.format(DATE_FORMAT) : null,
            data_final: reserva.data_final != null && reserva.data_final.isValid() ? reserva.data_final.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.data_inicial = res.body.data_inicial != null ? moment(res.body.data_inicial) : null;
        res.body.data_final = res.body.data_final != null ? moment(res.body.data_final) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((reserva: IReserva) => {
            reserva.data_inicial = reserva.data_inicial != null ? moment(reserva.data_inicial) : null;
            reserva.data_final = reserva.data_final != null ? moment(reserva.data_final) : null;
        });
        return res;
    }
}
