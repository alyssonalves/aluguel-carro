export interface IVeiculo {
    id?: number;
    marca?: string;
    modelo?: string;
    ano?: string;
    categoria?: string;
    valor_diaria?: number;
}

export class Veiculo implements IVeiculo {
    constructor(
        public id?: number,
        public marca?: string,
        public modelo?: string,
        public ano?: string,
        public categoria?: string,
        public valor_diaria?: number
    ) {}
}
