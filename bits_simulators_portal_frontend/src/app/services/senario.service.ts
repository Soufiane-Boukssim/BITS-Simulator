import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../env/environement";
const baseUrl = environment.url

@Injectable({
  providedIn: 'root'
})
export class SenarioService {

  constructor(private http: HttpClient) { }

  createSenario(senarioScript : any): Observable<any> {
    return this.http.post(baseUrl+"/senarioScripts/add", senarioScript);
  }
  getSenarioById(id: number): Observable<any> {
    return this.http.get(`${baseUrl}/senarioScripts/get/${id}`);
  }
  getAllSenarios(bankCode : string): Observable<any> {
    return this.http.get(`${baseUrl}/senarioScripts/all/${bankCode}`);
  }
  updateSenario(id: number, senarioScript: any): Observable<any> {
    return this.http.put(`${baseUrl}/senarioScripts/update/${id}`, senarioScript);
  }
  deleteSenario(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/senarioScripts/delete/${id}`);
  }

}
