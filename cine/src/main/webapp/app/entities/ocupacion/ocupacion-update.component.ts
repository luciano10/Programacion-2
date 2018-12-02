import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IOcupacion } from 'app/shared/model/ocupacion.model';
import { OcupacionService } from './ocupacion.service';
import { ITicket } from 'app/shared/model/ticket.model';
import { TicketService } from 'app/entities/ticket';
import { IEntrada } from 'app/shared/model/entrada.model';
import { EntradaService } from 'app/entities/entrada';
import { IButaca } from 'app/shared/model/butaca.model';
import { ButacaService } from 'app/entities/butaca';
import { IFuncion } from 'app/shared/model/funcion.model';
import { FuncionService } from 'app/entities/funcion';

@Component({
    selector: 'jhi-ocupacion-update',
    templateUrl: './ocupacion-update.component.html'
})
export class OcupacionUpdateComponent implements OnInit {
    ocupacion: IOcupacion;
    isSaving: boolean;

    tickets: ITicket[];

    entradas: IEntrada[];

    butacas: IButaca[];

    funcions: IFuncion[];
    created: string;
    updated: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private ocupacionService: OcupacionService,
        private ticketService: TicketService,
        private entradaService: EntradaService,
        private butacaService: ButacaService,
        private funcionService: FuncionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ocupacion }) => {
            this.ocupacion = ocupacion;
            this.created = this.ocupacion.created != null ? this.ocupacion.created.format(DATE_TIME_FORMAT) : null;
            this.updated = this.ocupacion.updated != null ? this.ocupacion.updated.format(DATE_TIME_FORMAT) : null;
        });
        this.ticketService.query().subscribe(
            (res: HttpResponse<ITicket[]>) => {
                this.tickets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.entradaService.query().subscribe(
            (res: HttpResponse<IEntrada[]>) => {
                this.entradas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.butacaService.query().subscribe(
            (res: HttpResponse<IButaca[]>) => {
                this.butacas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.funcionService.query().subscribe(
            (res: HttpResponse<IFuncion[]>) => {
                this.funcions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.ocupacion.created = this.created != null ? moment(this.created, DATE_TIME_FORMAT) : null;
        this.ocupacion.updated = this.updated != null ? moment(this.updated, DATE_TIME_FORMAT) : null;
        if (this.ocupacion.id !== undefined) {
            this.subscribeToSaveResponse(this.ocupacionService.update(this.ocupacion));
        } else {
            this.subscribeToSaveResponse(this.ocupacionService.create(this.ocupacion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOcupacion>>) {
        result.subscribe((res: HttpResponse<IOcupacion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTicketById(index: number, item: ITicket) {
        return item.id;
    }

    trackEntradaById(index: number, item: IEntrada) {
        return item.id;
    }

    trackButacaById(index: number, item: IButaca) {
        return item.id;
    }

    trackFuncionById(index: number, item: IFuncion) {
        return item.id;
    }
}
