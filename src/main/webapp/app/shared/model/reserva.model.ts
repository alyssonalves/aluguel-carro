import { Moment } from 'moment';

export interface IReserva {
    id?: number;
    data_inicial?: Moment;
    data_final?: Moment;
    cliente_idId?: number;
    veiculo_idVeiculo?: string;
    veiculo_idId?: number;
}

export class Reserva implements IReserva {
    constructor(
        public id?: number,
        public data_inicial?: Moment,
        public data_final?: Moment,
        public cliente_idId?: number,
        public veiculo_idVeiculo?: string,
        public veiculo_idId?: number
    ) {}
}
