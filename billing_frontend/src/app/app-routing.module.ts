import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FactureDetailsComponent } from './facture-details/facture-details.component';
import { FacturesComponent } from './factures/factures.component';
import { PaymentComponent } from './payment/payment.component';

const routes: Routes = [
  {path:"factures",component:FacturesComponent },
  {path:"payment",component:PaymentComponent },
  {path: 'facture-details/:id', component:FactureDetailsComponent},
  {path: 'payment/:id', component:PaymentComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
