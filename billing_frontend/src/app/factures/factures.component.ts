import { Component, OnInit , Inject } from '@angular/core';
import { FactureService } from '../facture.service';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { FactureDetailsComponent } from '../facture-details/facture-details.component';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { Router, withEnabledBlockingInitialNavigation } from '@angular/router';
import { PaymentComponent } from '../payment/payment.component';
import { ListKeyManager } from '@angular/cdk/a11y';
import { Facture } from '../facture';
import { withLatestFrom } from 'rxjs';
import { FactureStatus } from '../facture-status';
import { SigninService } from '../signin.service';


@Component({
  selector: 'app-factures',
  templateUrl: './factures.component.html',
  styleUrls: ['./factures.component.css']
})
export class FacturesComponent implements OnInit {

  factures: any;
  status: any = [];
  liste_factures: any = [];
  facture: Facture = new Facture();
  value = FactureStatus.PAYE;
  nonpaye = FactureStatus.NON_PAYE;
  access_token: any;
  list_products: any;
  modalRef: MdbModalRef<FactureDetailsComponent> | null = null;

  constructor(private dialog : MatDialog , public factureservice : FactureService, private signinservice: SigninService, private modalService:MdbModalService, private router : Router) { }

  ngOnInit(): void {
    this.getBilling();
    this.signinservice.signinUser();
    this.signinservice.getToken();
    
  }

  private getBilling(){
    this.factureservice.getBillingOfUser().subscribe(data=> {
      this.factures = data;
      for(let facture of this.factures) {
        this.liste_factures.push(facture);

        if(facture.status == this.value) {
          facture.showPaymentButton = true;
          facture.bgcolorbutton = '#e1fcef';
          facture.color ='#14804a'
        }

        else if (facture.status == this.nonpaye) {
          facture.showPaymentButton = false;
          facture.bgcolorbutton = 'rgba(255, 218, 68, 0.28)';
          facture.color ='#e5981d';
        }
      }
    });
   
    
}

 
 saveToken(){
  this.signinservice.signinUser().subscribe(data => {
    this.access_token = data.access_token;
    localStorage.setItem("access_token",this.access_token);
  })
 }
 

onOpenDialog(id:string){
    
    /*this.modalRef = this.modalService.open(FactureDetailsComponent)*/
    const dialogConfig = new MatDialogConfig();
    // The user can't close the dialog by clicking outside its body
    dialogConfig.id = "modal-component";
    /*dialogConfig.height = "750px";*/
    dialogConfig.width = "840px";
    dialogConfig.maxHeight = "700px";
  
    // https://material.angular.io/components/dialog/overview
    const modalDialog = this.dialog.open(FactureDetailsComponent, dialogConfig);
    modalDialog.componentInstance.id = id;

}
OpenPaymentPage(id:string){
  const dialogConfig = new MatDialogConfig();
    // The user can't close the dialog by clicking outside its body
    dialogConfig.id = "modal-component";
    dialogConfig.height = "450px";
    dialogConfig.width = "850px";
    // https://material.angular.io/components/dialog/overview
    const modalDialog = this.dialog.open(PaymentComponent, dialogConfig);
    modalDialog.componentInstance.id = id;
    /*this.router.navigate(['payment', id]);*/
}

}
