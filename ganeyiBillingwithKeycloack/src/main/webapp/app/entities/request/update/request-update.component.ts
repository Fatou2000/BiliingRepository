import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { RequestFormService, RequestFormGroup } from './request-form.service';
import { IRequest } from '../request.model';
import { RequestService } from '../service/request.service';
import { IResultat } from 'app/entities/resultat/resultat.model';
import { ResultatService } from 'app/entities/resultat/service/resultat.service';
import { IForfait } from 'app/entities/forfait/forfait.model';
import { ForfaitService } from 'app/entities/forfait/service/forfait.service';
import { IClient } from 'app/entities/client/client.model';
import { ClientService } from 'app/entities/client/service/client.service';
import { IApi } from 'app/entities/api/api.model';
import { ApiService } from 'app/entities/api/service/api.service';

@Component({
  selector: 'jhi-request-update',
  templateUrl: './request-update.component.html',
})
export class RequestUpdateComponent implements OnInit {
  isSaving = false;
  request: IRequest | null = null;

  resultatsCollection: IResultat[] = [];
  forfaitsSharedCollection: IForfait[] = [];
  clientsSharedCollection: IClient[] = [];
  apisSharedCollection: IApi[] = [];

  editForm: RequestFormGroup = this.requestFormService.createRequestFormGroup();

  constructor(
    protected requestService: RequestService,
    protected requestFormService: RequestFormService,
    protected resultatService: ResultatService,
    protected forfaitService: ForfaitService,
    protected clientService: ClientService,
    protected apiService: ApiService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareResultat = (o1: IResultat | null, o2: IResultat | null): boolean => this.resultatService.compareResultat(o1, o2);

  compareForfait = (o1: IForfait | null, o2: IForfait | null): boolean => this.forfaitService.compareForfait(o1, o2);

  compareClient = (o1: IClient | null, o2: IClient | null): boolean => this.clientService.compareClient(o1, o2);

  compareApi = (o1: IApi | null, o2: IApi | null): boolean => this.apiService.compareApi(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ request }) => {
      this.request = request;
      if (request) {
        this.updateForm(request);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const request = this.requestFormService.getRequest(this.editForm);
    if (request.id !== null) {
      this.subscribeToSaveResponse(this.requestService.update(request));
    } else {
      this.subscribeToSaveResponse(this.requestService.create(request));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequest>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(request: IRequest): void {
    this.request = request;
    this.requestFormService.resetForm(this.editForm, request);

    this.resultatsCollection = this.resultatService.addResultatToCollectionIfMissing<IResultat>(this.resultatsCollection, request.resultat);
    this.forfaitsSharedCollection = this.forfaitService.addForfaitToCollectionIfMissing<IForfait>(
      this.forfaitsSharedCollection,
      request.forfait
    );
    this.clientsSharedCollection = this.clientService.addClientToCollectionIfMissing<IClient>(this.clientsSharedCollection, request.client);
    this.apisSharedCollection = this.apiService.addApiToCollectionIfMissing<IApi>(this.apisSharedCollection, request.api);
  }

  protected loadRelationshipsOptions(): void {
    this.resultatService
      .query({ filter: 'request-is-null' })
      .pipe(map((res: HttpResponse<IResultat[]>) => res.body ?? []))
      .pipe(
        map((resultats: IResultat[]) => this.resultatService.addResultatToCollectionIfMissing<IResultat>(resultats, this.request?.resultat))
      )
      .subscribe((resultats: IResultat[]) => (this.resultatsCollection = resultats));

    this.forfaitService
      .query()
      .pipe(map((res: HttpResponse<IForfait[]>) => res.body ?? []))
      .pipe(map((forfaits: IForfait[]) => this.forfaitService.addForfaitToCollectionIfMissing<IForfait>(forfaits, this.request?.forfait)))
      .subscribe((forfaits: IForfait[]) => (this.forfaitsSharedCollection = forfaits));

    this.clientService
      .query()
      .pipe(map((res: HttpResponse<IClient[]>) => res.body ?? []))
      .pipe(map((clients: IClient[]) => this.clientService.addClientToCollectionIfMissing<IClient>(clients, this.request?.client)))
      .subscribe((clients: IClient[]) => (this.clientsSharedCollection = clients));

    this.apiService
      .query()
      .pipe(map((res: HttpResponse<IApi[]>) => res.body ?? []))
      .pipe(map((apis: IApi[]) => this.apiService.addApiToCollectionIfMissing<IApi>(apis, this.request?.api)))
      .subscribe((apis: IApi[]) => (this.apisSharedCollection = apis));
  }
}
