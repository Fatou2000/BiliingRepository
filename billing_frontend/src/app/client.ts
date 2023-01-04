import { Forfait } from "./forfait";
import { Product } from "./product";

export class Client{
    id?: string;
    accountId?: string | null;
    companyName?: string | null;
    firstName?: string | null;
    address?: string | null;
    phoneNumber?: string | null;
    products?: Product | null;
    forfaits?: Forfait | null;
}