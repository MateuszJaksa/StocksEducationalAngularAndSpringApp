import {Company} from "./company";

export interface Session {
  id: number;
  name: string;
  creator: string;
  descriptionEN: string;
  descriptionPL: string;
  startingDate: string;
  endingDate: string;
  companies: Company[];
  funds: number;
  createdDate: string;
}
