import { Moment } from 'moment';

export interface ICliente {
    id?: number;
    cpf?: string;
    nome?: string;
    data_nascimento?: Moment;
}

export class Cliente implements ICliente {
    constructor(public id?: number, public cpf?: string, public nome?: string, public data_nascimento?: Moment) {}
}
