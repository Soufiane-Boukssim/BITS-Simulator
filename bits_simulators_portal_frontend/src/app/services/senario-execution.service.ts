import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../env/environement";
import {OperationAtmDto} from "../pages/atm/model/OperationAtmDto";
const baseUrl = environment.url

@Injectable({
  providedIn: 'root'
})
export class SenarioExecutionService {


  constructor(private http: HttpClient) { }

  createSenario(senarioScript : any): Observable<any> {
    return this.http.post(`${baseUrl}/senario/add`, senarioScript);
  }
  getSenarioById(id: number): Observable<any> {
    return this.http.get(`${baseUrl}/senario/get/${id}`);
  }
  getAllSenarios(bankCode : string): Observable<any> {
    return this.http.get(`${baseUrl}/senario/all/${bankCode}`);
  }
  updateSenario(id: number, senarioScript: any): Observable<any> {
    return this.http.put(`${baseUrl}/senario/update/${id}`, senarioScript);
  }
  deleteSenario(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/senario/delete/${id}`);
  }



  processSenarioScript(senarioScript: any): Observable<any> {
    return this.http.post<any>(`${baseUrl}/v1/script-conversion/add`, senarioScript);
  }

}
