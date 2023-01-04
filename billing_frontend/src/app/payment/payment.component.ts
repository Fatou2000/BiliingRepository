import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { render } from 'creditcardpayments/creditCardPayments'
import { FactureService } from '../facture.service';
import { paypal } from 'creditcardpayments/creditCardPayments';
import { MatDialogRef } from '@angular/material/dialog';
import { Facture } from '../facture';
import { FacturesComponent } from '../factures/factures.component';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { CheckoutResponse, FacturePaydunya } from '../facturepaydunya';
import { ActivatedRoute, Data, Router } from '@angular/router';
import { Token } from '@angular/compiler';


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  @ViewChild( FacturesComponent)
  paypalElement!: ElementRef;
  factures : any;
  id : string = '';
  billing : any;
  token : any;
  @Input() receivedValue: boolean | undefined;
  /*@Input() id;
  billing : FacturePaydunya = new FacturePaydunya();*/
  
  

  constructor(public factureservice : FactureService, public dialogRef: MatDialogRef<PaymentComponent>,private router : Router , private route : ActivatedRoute ) { 
    
    
  }

  ngOnInit(): void {



    /*this.id = this.route.snapshot.params['id'];*/
    this.billing = new Facture();
    this.factureservice.getBillingById(this.id).subscribe( data => {
      this.billing = data;
      console.log(data);
    });

    

    this.getBilling();
    this.confirmpayment(localStorage.getItem("token"));


   
      /*if(this.route.snapshot.params["token"]){
        this.factureservice.confirmPayment(this.route.snapshot.params["token"]?.toString()).subscribe(data =>{
          console.log(data);
        }
        )
      }*/


  }
  private confirmpayment(token:string | null){
    this.factureservice.confirmPayment(token).subscribe(data =>{
      console.log(data);
      if(data.status === "completed"){
        this.setbillingstatus();
        /*this.factureservice.togglebutton();*/
        /*this.factureservice.showPaymentButton = false;*/
      }
    })
  }
  closeModal() {
    this.dialogRef.close();
  }
  private getBilling(){
    this.factureservice.getBillingOfUser().subscribe(data=>{
      this.factures = data;
    });
  }
  private setbillingstatus(){
    this.factureservice.updateBilling(this.id).subscribe(data =>{
      this.billing.id = data.id;
    })
  }
  

  payment(){
    
    this.factureservice.payment({ "invoice": { "total_amount": this.billing.total, "description": this.billing.typeFacturation }, "store": { "name": "Baamtu"}, "actions":{"return_url":"http://localhost:63282/factures","cancel_url":"http://localhost:63282/factures"} ,"items":{"name": "Chaussures Croco","quantity": 3,"unit_price": "10000","total_price": "30000","description":"chaussures"} })
    .subscribe(
      data =>{
        console.log(data);
        this.token = data.token;
        localStorage.setItem("token",this.token);
        console.log(data.token);
        window.location.href = "https://paydunya.com/sandbox-checkout/invoice/"+ data.token;

      }
    )
    
  }
 getPaydunyadataresponsetext(data : CheckoutResponse){
    return data.response_text;
 }
  
}
