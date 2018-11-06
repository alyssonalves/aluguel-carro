/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AluguelCarroTestModule } from '../../../test.module';
import { AluguelComponent } from 'app/entities/aluguel/aluguel.component';
import { AluguelService } from 'app/entities/aluguel/aluguel.service';
import { Aluguel } from 'app/shared/model/aluguel.model';

describe('Component Tests', () => {
    describe('Aluguel Management Component', () => {
        let comp: AluguelComponent;
        let fixture: ComponentFixture<AluguelComponent>;
        let service: AluguelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AluguelCarroTestModule],
                declarations: [AluguelComponent],
                providers: []
            })
                .overrideTemplate(AluguelComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AluguelComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AluguelService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Aluguel(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.aluguels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
