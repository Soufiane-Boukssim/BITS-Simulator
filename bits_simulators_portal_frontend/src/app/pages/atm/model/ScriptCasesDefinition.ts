import {FieldCasesDto} from "./FieldCasesDto";
import {TypeDefinitionScript} from "./TypeDefinitionScript";

export interface ScriptCasesDefinitionDto {
  id: number ;
  libelle: string;
  typeScript: TypeDefinitionScript;
  cardProfileIssuerCode: string;
  idCardProfil:{
    cardNo:string,
    bankCode:string
  },
  fieldCases: FieldCasesDto[];
  bankCode: string;
}
