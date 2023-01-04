import { Client } from "./client";
import { FactureStatus } from "./facture-status";
import { Forfait } from "./forfait";
import { Product } from "./product";
import { TypeFacturation } from "./type-facturation";

export class Facture {
    id? : string;
    rabais? : number | null ;
    tva?: number | null;
    sousTotal?: number | null;
    total?: number | null;
    typeFacturation?: TypeFacturation | null ;
    status?: FactureStatus | null ;
    reference?: string | null;
    date?: Date | null;
    forfait?: Forfait | null;
    client?: Client | null;
    product?: Product | null;
    showPaymentButton?: boolean | null;
    bgcolorbutton ?: string | null;
    color ?: string | null;
}
