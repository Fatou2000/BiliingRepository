import { IProduct, NewProduct } from './product.model';

export const sampleWithRequiredData: IProduct = {
  id: 'c442dfc5-f1e3-4a23-b568-16f6b07dcadc',
};

export const sampleWithPartialData: IProduct = {
  id: 'eb05732e-9cd2-4531-93fe-3a45b5084bec',
  name: 'copying Tuna Marketing',
  description: 'Multi-lateral',
  isActive: true,
};

export const sampleWithFullData: IProduct = {
  id: 'a3b776a4-568a-463e-85ba-bd435b43af8b',
  name: 'Account parse service-desk',
  description: 'National',
  logo: 'backing Iraqi',
  isActive: false,
  price: 92014,
};

export const sampleWithNewData: NewProduct = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
