import { IClient, NewClient } from './client.model';

export const sampleWithRequiredData: IClient = {
  id: 'b5d26ec1-78ed-413c-a418-9dee47137427',
};

export const sampleWithPartialData: IClient = {
  id: '3cc4180b-6c63-4fdc-b591-621bc0e72631',
  accountId: 'monitor discrete CSS',
  companyName: 'EXE',
  firstName: 'Ali',
  address: 'Global Director connect',
  phoneNumber: 'EXE Illinois Vatu',
};

export const sampleWithFullData: IClient = {
  id: '2ffde4b2-2132-4798-b369-2f042aad5039',
  accountId: 'Kids redundant Distributed',
  companyName: 'ivory Account schemas',
  firstName: 'Vilma',
  address: 'cross-platform payment Cape',
  phoneNumber: 'Borders bandwidth',
  lastName: 'Wilderman',
  email: 'Sofia.Thiel89@yahoo.com',
};

export const sampleWithNewData: NewClient = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
