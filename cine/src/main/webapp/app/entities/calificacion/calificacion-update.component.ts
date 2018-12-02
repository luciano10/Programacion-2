import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICalificacion } from 'app/shared/model/calificacion.model';
import { CalificacionService } from './calificacion.service';

@Component({
    selector: 'jhi-calificacion-update',
    templateUrl: './calificacion-update.component.html'
})
export class CalificacionUpdateComponent implements OnInit {
    calificacion: ICalificacion;
    isSaving: boolean;
    created: string;
    updated: string;

    constructor(private calificacionService: CalificacionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ calificacion }) => {
            this.calificacion = calificacion;
            this.created = this.calificacion.created != null ? this.calificacion.created.format(DATE_TIME_FORMAT) : null;
            this.updated = this.calificacion.updated != null ? this.calificacion.updated.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.calificacion.created = this.created != null ? moment(this.created, DATE_TIME_FORMAT) : null;
        this.calificacion.updated = this.updated != null ? moment(this.updated, DATE_TIME_FORMAT) : null;
        if (this.calificacion.id !== undefined) {
            this.subscribeToSaveResponse(this.calificacionService.update(this.calificacion));
        } else {
            this.subscribeToSaveResponse(this.calificacionService.create(this.calificacion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICalificacion>>) {
        result.subscribe((res: HttpResponse<ICalificacion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
