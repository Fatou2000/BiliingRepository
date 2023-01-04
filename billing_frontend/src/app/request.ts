import { Forfait } from "./forfait";
import { Resultat } from "./resultat";

export class Request{
  id?: string;
  duration?: number | null;
  status?: string | null;
  requestDate?: Date | null;
  forfait?: Forfait | null;
  resultat?: Resultat | null;
  
}