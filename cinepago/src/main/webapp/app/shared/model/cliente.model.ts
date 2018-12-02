import { Moment } from 'moment';
import { ITarjeta } from 'app/shared/model//tarjeta.model';

export interface ICliente {
    id?: number;
    documento?: number;
    apellido?: string;
    nombre?: string;
    created?: Moment;
    updated?: Moment;
    tarjetas?: ITarjeta[];
}

export class Cliente implements ICliente {
    constructor(
        public id?: number,
        public documento?: number,
        public apellido?: string,
        public nombre?: string,
        public created?: Moment,
        public updated?: Moment,
        public tarjetas?: ITarjeta[]
    ) {}
}
