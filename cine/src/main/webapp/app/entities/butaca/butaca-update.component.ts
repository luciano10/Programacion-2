import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IButaca } from 'app/shared/model/butaca.model';
import { ButacaService } from './butaca.service';
import { ISala } from 'app/shared/model/sala.model';
import { SalaService } from 'app/entities/sala';

@Component({
    selector: 'jhi-butaca-update',
    templateUrl: './butaca-update.component.html'
})
export class ButacaUpdateComponent implements OnInit {
    butaca: IButaca;
    isSaving: boolean;

    salas: ISala[];
    created: string;
    updated: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private butacaService: ButacaService,
        private salaService: SalaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ butaca }) => {
            this.butaca = butaca;
            this.created = this.butaca.created != null ? this.butaca.created.format(DATE_TIME_FORMAT) : null;
            this.updated = this.butaca.updated != null ? this.butaca.updated.format(DATE_TIME_FORMAT) : null;
        });
        this.salaService.query().subscribe(
            (res: HttpResponse<ISala[]>) => {
                this.salas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.butaca.created = this.created != null ? moment(this.created, DATE_TIME_FORMAT) : null;
        this.butaca.updated = this.updated != null ? moment(this.updated, DATE_TIME_FORMAT) : null;
        if (this.butaca.id !== undefined) {
            this.subscribeToSaveResponse(this.butacaService.update(this.butaca));
        } else {
            this.subscribeToSaveResponse(this.butacaService.create(this.butaca));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IButaca>>) {
        result.subscribe((res: HttpResponse<IButaca>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSalaById(index: number, item: ISala) {
        return item.id;
    }
}
