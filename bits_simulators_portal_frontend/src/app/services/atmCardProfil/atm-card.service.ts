import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../../env/environement";
const baseUrl = environment.url
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AtmCardService {

  constructor(private http: HttpClient,) { }

  getCards(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/AtmCardProfile/getAllCardProfiles', body)
  }
  addCard(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/AtmCardProfile/addCardProfile', body)
  }
  updateCard(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/AtmCardProfile/updateCardProfile', body)
  }
  deleteCard(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/AtmCardProfile/deleteCardProfile', body)
  }

  fetchIcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/AtmIccProfile/getAllIccProfiles', body)
  }
  addIcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/AtmIccProfile/addIccProfile', body)
  }
  updateIcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/AtmIccProfile/updateIccProfile', body)
  }
  deleteIcc(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/AtmIccProfile/deleteIccProfile', body)
  }
}
