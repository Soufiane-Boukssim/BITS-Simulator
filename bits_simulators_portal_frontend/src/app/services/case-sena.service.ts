import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../env/environement";
import {FieldCasesDto} from "../pages/atm/model/FieldCasesDto";
const baseUrl = environment.url

@Injectable({
  providedIn: 'root'
})
export class CaseSenaService {
  private baseUrl = 'http://localhost:9193/api';

  constructor(private http: HttpClient) { }

  updateFieldCases(id: number, fieldCasesDto: any): Observable<any> {
    return this.http.put(`${baseUrl}/field-cases/update/${id}`, fieldCasesDto);
  }

  deleteFieldCases(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/field-cases/delete/${id}`);
  }

  getAllFieldCases(bankCode : string): Observable<any> {
    return this.http.get<FieldCasesDto[]>(`${baseUrl}/field-cases/all/${bankCode}` );
  }


  createScriptCasesDefinition(scriptCasesDefinition: any): Observable<any> {
    return this.http.post(`${baseUrl}/scriptCasesDefinition/add`, scriptCasesDefinition);
  }

  getScriptCasesDefinitionById(id: number): Observable<any> {
    return this.http.get(`${baseUrl}/scriptCasesDefinition/getById/${id}`);
  }

  getAllScriptCasesDefinition(bankCode : string): Observable<any> {
    return this.http.get(`${baseUrl}/scriptCasesDefinition/all/${bankCode}`);
  }

  updateScriptCasesDefinition(id: number, scriptCasesDefinitionDto: any): Observable<any> {
    // console.log('Sending the following JSON object to the server:', scriptCasesDefinitionDto);
    return this.http.put(`${baseUrl}/scriptCasesDefinition/update/${id}`, scriptCasesDefinitionDto);
  }


  deleteScriptCasesDefinition(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/scriptCasesDefinition/delete/${id}` , {responseType: 'text'});
  }

}
