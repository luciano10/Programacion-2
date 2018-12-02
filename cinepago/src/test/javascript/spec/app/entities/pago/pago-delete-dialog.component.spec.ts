/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CinepagoTestModule } from '../../../test.module';
import { PagoDeleteDialogComponent } from 'app/entities/pago/pago-delete-dialog.component';
import { PagoService } from 'app/entities/pago/pago.service';

describe('Component Tests', () => {
    describe('Pago Management Delete Component', () => {
        let comp: PagoDeleteDialogComponent;
        let fixture: ComponentFixture<PagoDeleteDialogComponent>;
        let service: PagoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CinepagoTestModule],
                declarations: [PagoDeleteDialogComponent]
            })
                .overrideTemplate(PagoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PagoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PagoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
