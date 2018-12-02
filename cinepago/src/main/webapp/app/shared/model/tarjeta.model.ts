import { Moment } from 'moment';
import { IPago } from 'app/shared/model//pago.model';
import { ICliente } from 'app/shared/model//cliente.model';

export const enum Tipo {
    DEBITO = 'DEBITO',
    CREDITO = 'CREDITO'
}

export interface ITarjeta {
    id?: number;
    numero?: string;
    tipo?: Tipo;
    saldo?: number;
    created?: Moment;
    updated?: Moment;
    pagos?: IPago[];
    cliente?: ICliente;
}

export class Tarjeta implements ITarjeta {
    constructor(
        public id?: number,
        public numero?: string,
        public tipo?: Tipo,
        public saldo?: number,
        public created?: Moment,
        public updated?: Moment,
        public pagos?: IPago[],
        public cliente?: ICliente
    ) {}
}
