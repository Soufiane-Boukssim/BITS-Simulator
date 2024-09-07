import {PopularList} from "./PopularList";
import {ATMfield} from "./ATMfield";

export interface FieldCasesDto {
  popularList: PopularList;
  value: string | null;
  atmField: ATMfield[];
  bankCode: string;
}
