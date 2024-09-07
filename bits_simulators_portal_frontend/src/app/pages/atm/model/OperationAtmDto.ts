import {ScriptCasesDefinitionDto} from "./ScriptCasesDefinition";
import {AtmCardProfilDto} from "./AtmCardProfilDto";

export interface OperationAtmDto {
  id: number;
  libelle: string;
  scriptCasesDefinitions: ScriptCasesDefinitionDto[];
  atmCardProfilDto: AtmCardProfilDto;
  bankCode: string;
}
