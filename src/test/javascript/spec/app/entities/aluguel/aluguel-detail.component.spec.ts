/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AluguelCarroTestModule } from '../../../test.module';
import { AluguelDetailComponent } from 'app/entities/aluguel/aluguel-detail.component';
import { Aluguel } from 'app/shared/model/aluguel.model';

describe('Component Tests', () => {
    describe('Aluguel Management Detail Component', () => {
        let comp: AluguelDetailComponent;
        let fixture: ComponentFixture<AluguelDetailComponent>;
        const route = ({ data: of({ aluguel: new Aluguel(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AluguelCarroTestModule],
                declarations: [AluguelDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AluguelDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AluguelDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.aluguel).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
