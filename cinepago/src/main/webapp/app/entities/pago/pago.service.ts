import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPago } from 'app/shared/model/pago.model';

type EntityResponseType = HttpResponse<IPago>;
type EntityArrayResponseType = HttpResponse<IPago[]>;

@Injectable({ providedIn: 'root' })
export class PagoService {
    public resourceUrl = SERVER_API_URL + 'api/pagos';

    constructor(private http: HttpClient) {}

    create(pago: IPago): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pago);
        return this.http
            .post<IPago>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(pago: IPago): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pago);
        return this.http
            .put<IPago>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPago>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPago[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(pago: IPago): IPago {
        const copy: IPago = Object.assign({}, pago, {
            created: pago.created != null && pago.created.isValid() ? pago.created.toJSON() : null,
            updated: pago.updated != null && pago.updated.isValid() ? pago.updated.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.created = res.body.created != null ? moment(res.body.created) : null;
            res.body.updated = res.body.updated != null ? moment(res.body.updated) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((pago: IPago) => {
                pago.created = pago.created != null ? moment(pago.created) : null;
                pago.updated = pago.updated != null ? moment(pago.updated) : null;
            });
        }
        return res;
    }
}
