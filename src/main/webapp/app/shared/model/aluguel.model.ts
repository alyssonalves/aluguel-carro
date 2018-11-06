import { Moment } from 'moment';

export interface IAluguel {
    id?: number;
    data_retirada?: Moment;
    data_prev_devolucao?: Moment;
    data_real_devolucao?: Moment;
    valor_locacao?: number;
    cliente_idCliente_id?: string;
    cliente_idId?: number;
    veiculo_idVeiculo_id?: string;
    veiculo_idId?: number;
    reserva_idId?: number;
}

export class Aluguel implements IAluguel {
    constructor(
        public id?: number,
        public data_retirada?: Moment,
        public data_prev_devolucao?: Moment,
        public data_real_devolucao?: Moment,
        public valor_locacao?: number,
        public cliente_idCliente_id?: string,
        public cliente_idId?: number,
        public veiculo_idVeiculo_id?: string,
        public veiculo_idId?: number,
        public reserva_idId?: number
    ) {}
}
