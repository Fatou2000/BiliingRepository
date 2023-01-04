import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Facture } from './facture';
import { Observable } from 'rxjs';
import { FacturePaydunya,CheckoutResponse } from './facturepaydunya';



@Injectable({
  providedIn: 'root'
})
export class FactureService {

  showPaymentButton: boolean = true;
  private baseUrl = "http://localhost:8082/api/factures"
  private API = "https://app.paydunya.com/sandbox-api/v1/checkout-invoice/create"
  private confirm = "https://app.paydunya.com/sandbox-api/v1/checkout-invoice/confirm/"
 

  constructor(private httpclient: HttpClient) { }

  getBillingOfUser():Observable<Facture[]>{

    return this.httpclient.get<Facture[]>(this.baseUrl);
  }
  
  updateBilling(id : string){
    const body = {id: id ,status:"PAYE"};
    return this.httpclient.patch<any>(this.baseUrl+"/"+id,body);
  }

  getBillingById(id: string): Observable<Facture> {
    return this.httpclient.get<Facture>(this.baseUrl+'/'+id);
  }
 


  payment(facture : FacturePaydunya):Observable<CheckoutResponse>{
    const headers= new HttpHeaders()
    .set('content-type', 'application/json')
    .set('PAYDUNYA-MASTER-KEY','TLHUD2TH-S0sq-549y-MZM6-5hIukEvoRTAS')
    .set('PAYDUNYA-PRIVATE-KEY','test_private_OjRGCfU6owH0F3eW29V3H3UkabR')
    .set('PAYDUNYA-TOKEN','xZ7wgHTsslEJGtn5KRtx')
    return this.httpclient.post(this.API,facture,{'headers' : headers});

  }

  confirmPayment(token : string | undefined | null):Observable<any>{
    console.log("confirm service");
    const headers= new HttpHeaders()
    .set('content-type', 'application/json')
    .set('PAYDUNYA-MASTER-KEY','TLHUD2TH-S0sq-549y-MZM6-5hIukEvoRTAS')
    .set('PAYDUNYA-PRIVATE-KEY','test_private_OjRGCfU6owH0F3eW29V3H3UkabR')
    .set('PAYDUNYA-TOKEN','xZ7wgHTsslEJGtn5KRtx')
    return this.httpclient.get(this.confirm+token,{'headers' : headers});

  }


    
}
