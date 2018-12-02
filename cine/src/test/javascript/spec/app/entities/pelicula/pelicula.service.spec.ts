/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PeliculaService } from 'app/entities/pelicula/pelicula.service';
import { IPelicula, Pelicula } from 'app/shared/model/pelicula.model';

describe('Service Tests', () => {
    describe('Pelicula Service', () => {
        let injector: TestBed;
        let service: PeliculaService;
        let httpMock: HttpTestingController;
        let elemDefault: IPelicula;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(PeliculaService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Pelicula(0, 'AAAAAAA', currentDate, currentDate, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        estreno: currentDate.format(DATE_TIME_FORMAT),
                        created: currentDate.format(DATE_TIME_FORMAT),
                        updated: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Pelicula', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        estreno: currentDate.format(DATE_TIME_FORMAT),
                        created: currentDate.format(DATE_TIME_FORMAT),
                        updated: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        estreno: currentDate,
                        created: currentDate,
                        updated: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Pelicula(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Pelicula', async () => {
                const returnedFromService = Object.assign(
                    {
                        titulo: 'BBBBBB',
                        estreno: currentDate.format(DATE_TIME_FORMAT),
                        created: currentDate.format(DATE_TIME_FORMAT),
                        updated: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        estreno: currentDate,
                        created: currentDate,
                        updated: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Pelicula', async () => {
                const returnedFromService = Object.assign(
                    {
                        titulo: 'BBBBBB',
                        estreno: currentDate.format(DATE_TIME_FORMAT),
                        created: currentDate.format(DATE_TIME_FORMAT),
                        updated: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        estreno: currentDate,
                        created: currentDate,
                        updated: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Pelicula', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
