import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/env/environement';

const baseUrl = environment.url
@Injectable({
  providedIn: 'root'
})
export class PosCasesandscenariosService {

  constructor(private http: HttpClient) { }

  getCases(body: any): Observable<any>{
    return this.http.post(baseUrl + '/su/pos/casesDefinition/getAllCasesDefinitions',body).pipe();
  }

  getCase(body: any): Observable<any>{
    return this.http.post(baseUrl + '/su/pos/casesDefinition/getCaseDefinitions',body).pipe();
  }

  addCase(body: any): Observable<any>{
    return this.http.post(baseUrl + '/su/pos/casesDefinition/addCasesDefinition',body).pipe();
  }

  updateCase(body: any): Observable<any>{
    return this.http.post(baseUrl + '/su/pos/casesDefinition/updateCasesDefinition',body).pipe();
  }

  deleteCase(body: any): Observable<any>{
    return this.http.post(baseUrl + '/su/pos/casesDefinition/deleteCasesDefinition',body).pipe();
  }

  getFields(): Observable<any>{
    return this.http.post(baseUrl + '/su/pos/fieldsDefinition/getAllFieldsDefinitions', {}).pipe();
  }

  getScenarios(body: any): Observable<any>{
    return this.http.post(baseUrl + '/su/pos/caseSetInfo/getAllCaseSetInfos',body).pipe();
  }

  getScenario(body: any): Observable<any>{
    return this.http.post(baseUrl + '/su/pos/caseSetInfo/getOneCaseSetInfo',body).pipe();
  }

  saveScenario(action: string, body: any): Observable<any>{
    return action === "add" ? this.http.post(baseUrl + '/su/pos/caseSetInfo/addCaseSetInfo',body).pipe() : this.http.post(baseUrl + '/su/pos/caseSetInfo/updateCaseSetInfo',body).pipe();
  }

  // updateScenario(body: any): Observable<any>{
  //   return this.http.post(baseUrl + '/su/pos/caseSetInfo/updateCaseSetInfo',body).pipe();
  // }

  deleteScenario(body: any): Observable<any>{
    return this.http.post(baseUrl + '/su/pos/caseSetInfo/deleteCaseSetInfo',body).pipe();
  }

}
