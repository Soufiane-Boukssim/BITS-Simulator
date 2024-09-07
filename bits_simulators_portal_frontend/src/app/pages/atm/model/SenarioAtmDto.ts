import {OperationAtmDto} from "./OperationAtmDto";

export interface SenarioAtmDto {
  id: number;
  libelle: string;
  senarioScripts: OperationAtmDto[];
  bankCode: string;
  cardProfileIssuerCode: string;
}
