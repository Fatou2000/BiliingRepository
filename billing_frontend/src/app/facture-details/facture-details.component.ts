import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Facture } from '../facture';
import { FactureService } from '../facture.service';

@Component({
  selector: 'app-facture-details',
  templateUrl: './facture-details.component.html',
  styleUrls: ['./facture-details.component.css']
})
export class FactureDetailsComponent implements OnInit {

  factures: any;
  id: string = '';
  facture: any;

  constructor(private facturedetailsservice : FactureService) { }

  ngOnInit(): void {
    /*this.factures = new Facture();*/
    this.facturedetailsservice.getBillingById(this.id).subscribe( data => {
      this.factures = data;
    });
    
  }
  private getBilling() {
    this.facturedetailsservice.getBillingOfUser().subscribe(data=> {
      this.factures = data;
    });
  }

}
