/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AluguelCarroTestModule } from '../../../test.module';
import { AluguelDeleteDialogComponent } from 'app/entities/aluguel/aluguel-delete-dialog.component';
import { AluguelService } from 'app/entities/aluguel/aluguel.service';

describe('Component Tests', () => {
    describe('Aluguel Management Delete Component', () => {
        let comp: AluguelDeleteDialogComponent;
        let fixture: ComponentFixture<AluguelDeleteDialogComponent>;
        let service: AluguelService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AluguelCarroTestModule],
                declarations: [AluguelDeleteDialogComponent]
            })
                .overrideTemplate(AluguelDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AluguelDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AluguelService);
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
