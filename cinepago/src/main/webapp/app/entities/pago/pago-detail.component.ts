import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPago } from 'app/shared/model/pago.model';

@Component({
    selector: 'jhi-pago-detail',
    templateUrl: './pago-detail.component.html'
})
export class PagoDetailComponent implements OnInit {
    pago: IPago;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pago }) => {
            this.pago = pago;
        });
    }

    previousState() {
        window.history.back();
    }
}
