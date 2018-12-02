import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPelicula } from 'app/shared/model/pelicula.model';
import { PeliculaService } from './pelicula.service';
import { ICalificacion } from 'app/shared/model/calificacion.model';
import { CalificacionService } from 'app/entities/calificacion';

@Component({
    selector: 'jhi-pelicula-update',
    templateUrl: './pelicula-update.component.html'
})
export class PeliculaUpdateComponent implements OnInit {
    pelicula: IPelicula;
    isSaving: boolean;

    calificacions: ICalificacion[];
    estreno: string;
    created: string;
    updated: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private peliculaService: PeliculaService,
        private calificacionService: CalificacionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pelicula }) => {
            this.pelicula = pelicula;
            this.estreno = this.pelicula.estreno != null ? this.pelicula.estreno.format(DATE_TIME_FORMAT) : null;
            this.created = this.pelicula.created != null ? this.pelicula.created.format(DATE_TIME_FORMAT) : null;
            this.updated = this.pelicula.updated != null ? this.pelicula.updated.format(DATE_TIME_FORMAT) : null;
        });
        this.calificacionService.query().subscribe(
            (res: HttpResponse<ICalificacion[]>) => {
                this.calificacions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.pelicula.estreno = this.estreno != null ? moment(this.estreno, DATE_TIME_FORMAT) : null;
        this.pelicula.created = this.created != null ? moment(this.created, DATE_TIME_FORMAT) : null;
        this.pelicula.updated = this.updated != null ? moment(this.updated, DATE_TIME_FORMAT) : null;
        if (this.pelicula.id !== undefined) {
            this.subscribeToSaveResponse(this.peliculaService.update(this.pelicula));
        } else {
            this.subscribeToSaveResponse(this.peliculaService.create(this.pelicula));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPelicula>>) {
        result.subscribe((res: HttpResponse<IPelicula>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCalificacionById(index: number, item: ICalificacion) {
        return item.id;
    }
}
