import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAluguel } from 'app/shared/model/aluguel.model';

type EntityResponseType = HttpResponse<IAluguel>;
type EntityArrayResponseType = HttpResponse<IAluguel[]>;

@Injectable({ providedIn: 'root' })
export class AluguelService {
    private resourceUrl = SERVER_API_URL + 'api/aluguels';

    constructor(private http: HttpClient) {}

    create(aluguel: IAluguel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(aluguel);
        return this.http
            .post<IAluguel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(aluguel: IAluguel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(aluguel);
        return this.http
            .put<IAluguel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAluguel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAluguel[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(aluguel: IAluguel): IAluguel {
        const copy: IAluguel = Object.assign({}, aluguel, {
            data_retirada:
                aluguel.data_retirada != null && aluguel.data_retirada.isValid() ? aluguel.data_retirada.format(DATE_FORMAT) : null,
            data_prev_devolucao:
                aluguel.data_prev_devolucao != null && aluguel.data_prev_devolucao.isValid()
                    ? aluguel.data_prev_devolucao.format(DATE_FORMAT)
                    : null,
            data_real_devolucao:
                aluguel.data_real_devolucao != null && aluguel.data_real_devolucao.isValid()
                    ? aluguel.data_real_devolucao.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.data_retirada = res.body.data_retirada != null ? moment(res.body.data_retirada) : null;
        res.body.data_prev_devolucao = res.body.data_prev_devolucao != null ? moment(res.body.data_prev_devolucao) : null;
        res.body.data_real_devolucao = res.body.data_real_devolucao != null ? moment(res.body.data_real_devolucao) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((aluguel: IAluguel) => {
            aluguel.data_retirada = aluguel.data_retirada != null ? moment(aluguel.data_retirada) : null;
            aluguel.data_prev_devolucao = aluguel.data_prev_devolucao != null ? moment(aluguel.data_prev_devolucao) : null;
            aluguel.data_real_devolucao = aluguel.data_real_devolucao != null ? moment(aluguel.data_real_devolucao) : null;
        });
        return res;
    }
}
