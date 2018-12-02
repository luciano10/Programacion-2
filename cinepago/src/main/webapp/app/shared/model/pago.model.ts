import { Moment } from 'moment';
import { ITarjeta } from 'app/shared/model//tarjeta.model';

export interface IPago {
    id?: number;
    importe?: number;
    pagoUuid?: string;
    created?: Moment;
    updated?: Moment;
    tarjeta?: ITarjeta;
}

export class Pago implements IPago {
    constructor(
        public id?: number,
        public importe?: number,
        public pagoUuid?: string,
        public created?: Moment,
        public updated?: Moment,
        public tarjeta?: ITarjeta
    ) {}
}
