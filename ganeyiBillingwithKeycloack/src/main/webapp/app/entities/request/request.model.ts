import dayjs from 'dayjs/esm';
import { IResultat } from 'app/entities/resultat/resultat.model';
import { IForfait } from 'app/entities/forfait/forfait.model';
import { IClient } from 'app/entities/client/client.model';
import { IApi } from 'app/entities/api/api.model';

export interface IRequest {
  id: string;
  duration?: number | null;
  status?: string | null;
  requestDate?: dayjs.Dayjs | null;
  resultat?: Pick<IResultat, 'id'> | null;
  forfait?: Pick<IForfait, 'id'> | null;
  client?: Pick<IClient, 'id'> | null;
  api?: Pick<IApi, 'id'> | null;
}

export type NewRequest = Omit<IRequest, 'id'> & { id: null };
