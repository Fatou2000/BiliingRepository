import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'client',
        data: { pageTitle: 'billingwithkeycloackApp.client.home.title' },
        loadChildren: () => import('./client/client.module').then(m => m.ClientModule),
      },
      {
        path: 'payment',
        data: { pageTitle: 'billingwithkeycloackApp.payment.home.title' },
        loadChildren: () => import('./payment/payment.module').then(m => m.PaymentModule),
      },
      {
        path: 'product',
        data: { pageTitle: 'billingwithkeycloackApp.product.home.title' },
        loadChildren: () => import('./product/product.module').then(m => m.ProductModule),
      },
      {
        path: 'product-license',
        data: { pageTitle: 'billingwithkeycloackApp.productLicense.home.title' },
        loadChildren: () => import('./product-license/product-license.module').then(m => m.ProductLicenseModule),
      },
      {
        path: 'pricing',
        data: { pageTitle: 'billingwithkeycloackApp.pricing.home.title' },
        loadChildren: () => import('./pricing/pricing.module').then(m => m.PricingModule),
      },
      {
        path: 'facture',
        data: { pageTitle: 'billingwithkeycloackApp.facture.home.title' },
        loadChildren: () => import('./facture/facture.module').then(m => m.FactureModule),
      },
      {
        path: 'forfait',
        data: { pageTitle: 'billingwithkeycloackApp.forfait.home.title' },
        loadChildren: () => import('./forfait/forfait.module').then(m => m.ForfaitModule),
      },
      {
        path: 'request',
        data: { pageTitle: 'billingwithkeycloackApp.request.home.title' },
        loadChildren: () => import('./request/request.module').then(m => m.RequestModule),
      },
      {
        path: 'resultat',
        data: { pageTitle: 'billingwithkeycloackApp.resultat.home.title' },
        loadChildren: () => import('./resultat/resultat.module').then(m => m.ResultatModule),
      },
      {
        path: 'api',
        data: { pageTitle: 'billingwithkeycloackApp.api.home.title' },
        loadChildren: () => import('./api/api.module').then(m => m.ApiModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
