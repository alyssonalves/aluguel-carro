/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AluguelCarroTestModule } from '../../../test.module';
import { AluguelUpdateComponent } from 'app/entities/aluguel/aluguel-update.component';
import { AluguelService } from 'app/entities/aluguel/aluguel.service';
import { Aluguel } from 'app/shared/model/aluguel.model';

describe('Component Tests', () => {
    describe('Aluguel Management Update Component', () => {
        let comp: AluguelUpdateComponent;
        let fixture: ComponentFixture<AluguelUpdateComponent>;
        let service: AluguelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AluguelCarroTestModule],
                declarations: [AluguelUpdateComponent]
            })
                .overrideTemplate(AluguelUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AluguelUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AluguelService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Aluguel(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.aluguel = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Aluguel();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.aluguel = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
