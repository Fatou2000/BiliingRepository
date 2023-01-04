import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RequestFormService } from './request-form.service';
import { RequestService } from '../service/request.service';
import { IRequest } from '../request.model';
import { IResultat } from 'app/entities/resultat/resultat.model';
import { ResultatService } from 'app/entities/resultat/service/resultat.service';
import { IForfait } from 'app/entities/forfait/forfait.model';
import { ForfaitService } from 'app/entities/forfait/service/forfait.service';
import { IClient } from 'app/entities/client/client.model';
import { ClientService } from 'app/entities/client/service/client.service';
import { IApi } from 'app/entities/api/api.model';
import { ApiService } from 'app/entities/api/service/api.service';

import { RequestUpdateComponent } from './request-update.component';

describe('Request Management Update Component', () => {
  let comp: RequestUpdateComponent;
  let fixture: ComponentFixture<RequestUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let requestFormService: RequestFormService;
  let requestService: RequestService;
  let resultatService: ResultatService;
  let forfaitService: ForfaitService;
  let clientService: ClientService;
  let apiService: ApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RequestUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(RequestUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RequestUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    requestFormService = TestBed.inject(RequestFormService);
    requestService = TestBed.inject(RequestService);
    resultatService = TestBed.inject(ResultatService);
    forfaitService = TestBed.inject(ForfaitService);
    clientService = TestBed.inject(ClientService);
    apiService = TestBed.inject(ApiService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call resultat query and add missing value', () => {
      const request: IRequest = { id: 'CBA' };
      const resultat: IResultat = { id: '695f9788-34d9-4a74-ad63-5cd4416cde8a' };
      request.resultat = resultat;

      const resultatCollection: IResultat[] = [{ id: '79c73346-104f-4647-84f6-51cb44a9407a' }];
      jest.spyOn(resultatService, 'query').mockReturnValue(of(new HttpResponse({ body: resultatCollection })));
      const expectedCollection: IResultat[] = [resultat, ...resultatCollection];
      jest.spyOn(resultatService, 'addResultatToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ request });
      comp.ngOnInit();

      expect(resultatService.query).toHaveBeenCalled();
      expect(resultatService.addResultatToCollectionIfMissing).toHaveBeenCalledWith(resultatCollection, resultat);
      expect(comp.resultatsCollection).toEqual(expectedCollection);
    });

    it('Should call Forfait query and add missing value', () => {
      const request: IRequest = { id: 'CBA' };
      const forfait: IForfait = { id: '85a6da7b-b881-4c27-8365-4c3475010ea0' };
      request.forfait = forfait;

      const forfaitCollection: IForfait[] = [{ id: '9126fa15-76f3-46f6-981d-8b3986685fd2' }];
      jest.spyOn(forfaitService, 'query').mockReturnValue(of(new HttpResponse({ body: forfaitCollection })));
      const additionalForfaits = [forfait];
      const expectedCollection: IForfait[] = [...additionalForfaits, ...forfaitCollection];
      jest.spyOn(forfaitService, 'addForfaitToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ request });
      comp.ngOnInit();

      expect(forfaitService.query).toHaveBeenCalled();
      expect(forfaitService.addForfaitToCollectionIfMissing).toHaveBeenCalledWith(
        forfaitCollection,
        ...additionalForfaits.map(expect.objectContaining)
      );
      expect(comp.forfaitsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Client query and add missing value', () => {
      const request: IRequest = { id: 'CBA' };
      const client: IClient = { id: '3df652d5-5f99-4cec-a8e2-1ee2a039f9f4' };
      request.client = client;

      const clientCollection: IClient[] = [{ id: '0aaff0be-f9da-4b4b-acc9-09d37c69ec4f' }];
      jest.spyOn(clientService, 'query').mockReturnValue(of(new HttpResponse({ body: clientCollection })));
      const additionalClients = [client];
      const expectedCollection: IClient[] = [...additionalClients, ...clientCollection];
      jest.spyOn(clientService, 'addClientToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ request });
      comp.ngOnInit();

      expect(clientService.query).toHaveBeenCalled();
      expect(clientService.addClientToCollectionIfMissing).toHaveBeenCalledWith(
        clientCollection,
        ...additionalClients.map(expect.objectContaining)
      );
      expect(comp.clientsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Api query and add missing value', () => {
      const request: IRequest = { id: 'CBA' };
      const api: IApi = { id: '1af4bf52-9cdc-4b61-9104-f8886595320f' };
      request.api = api;

      const apiCollection: IApi[] = [{ id: 'a0f47519-07df-45c6-af4b-9209f07e34d6' }];
      jest.spyOn(apiService, 'query').mockReturnValue(of(new HttpResponse({ body: apiCollection })));
      const additionalApis = [api];
      const expectedCollection: IApi[] = [...additionalApis, ...apiCollection];
      jest.spyOn(apiService, 'addApiToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ request });
      comp.ngOnInit();

      expect(apiService.query).toHaveBeenCalled();
      expect(apiService.addApiToCollectionIfMissing).toHaveBeenCalledWith(apiCollection, ...additionalApis.map(expect.objectContaining));
      expect(comp.apisSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const request: IRequest = { id: 'CBA' };
      const resultat: IResultat = { id: '72ee8fe2-5d5e-49f2-8110-15a901379d3d' };
      request.resultat = resultat;
      const forfait: IForfait = { id: 'fd2e50c0-34ae-4e63-80c8-2037d1c9052b' };
      request.forfait = forfait;
      const client: IClient = { id: 'fd6ca639-5239-41a2-95bb-a7cd522d3510' };
      request.client = client;
      const api: IApi = { id: '634fba58-d106-451c-81da-c7a0f12c8a6c' };
      request.api = api;

      activatedRoute.data = of({ request });
      comp.ngOnInit();

      expect(comp.resultatsCollection).toContain(resultat);
      expect(comp.forfaitsSharedCollection).toContain(forfait);
      expect(comp.clientsSharedCollection).toContain(client);
      expect(comp.apisSharedCollection).toContain(api);
      expect(comp.request).toEqual(request);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRequest>>();
      const request = { id: 'ABC' };
      jest.spyOn(requestFormService, 'getRequest').mockReturnValue(request);
      jest.spyOn(requestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ request });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: request }));
      saveSubject.complete();

      // THEN
      expect(requestFormService.getRequest).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(requestService.update).toHaveBeenCalledWith(expect.objectContaining(request));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRequest>>();
      const request = { id: 'ABC' };
      jest.spyOn(requestFormService, 'getRequest').mockReturnValue({ id: null });
      jest.spyOn(requestService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ request: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: request }));
      saveSubject.complete();

      // THEN
      expect(requestFormService.getRequest).toHaveBeenCalled();
      expect(requestService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRequest>>();
      const request = { id: 'ABC' };
      jest.spyOn(requestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ request });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(requestService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareResultat', () => {
      it('Should forward to resultatService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(resultatService, 'compareResultat');
        comp.compareResultat(entity, entity2);
        expect(resultatService.compareResultat).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareForfait', () => {
      it('Should forward to forfaitService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(forfaitService, 'compareForfait');
        comp.compareForfait(entity, entity2);
        expect(forfaitService.compareForfait).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareClient', () => {
      it('Should forward to clientService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(clientService, 'compareClient');
        comp.compareClient(entity, entity2);
        expect(clientService.compareClient).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareApi', () => {
      it('Should forward to apiService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(apiService, 'compareApi');
        comp.compareApi(entity, entity2);
        expect(apiService.compareApi).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
