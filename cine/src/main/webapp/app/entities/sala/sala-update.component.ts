import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISala } from 'app/shared/model/sala.model';
import { SalaService } from './sala.service';

@Component({
    selector: 'jhi-sala-update',
    templateUrl: './sala-update.component.html'
})
export class SalaUpdateComponent implements OnInit {
    sala: ISala;
    isSaving: boolean;
    created: string;
    updated: string;

    constructor(private salaService: SalaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sala }) => {
            this.sala = sala;
            this.created = this.sala.created != null ? this.sala.created.format(DATE_TIME_FORMAT) : null;
            this.updated = this.sala.updated != null ? this.sala.updated.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sala.created = this.created != null ? moment(this.created, DATE_TIME_FORMAT) : null;
        this.sala.updated = this.updated != null ? moment(this.updated, DATE_TIME_FORMAT) : null;
        if (this.sala.id !== undefined) {
            this.subscribeToSaveResponse(this.salaService.update(this.sala));
        } else {
            this.subscribeToSaveResponse(this.salaService.create(this.sala));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISala>>) {
        result.subscribe((res: HttpResponse<ISala>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
