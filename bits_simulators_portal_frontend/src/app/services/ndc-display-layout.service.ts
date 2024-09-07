import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/env/environement'; // Adjust the import path as needed
import { NdcDisplayLayout } from '../models/ndc-display-layout.model'; // Adjust the import path as needed

@Injectable({
  providedIn: 'root'
})
export class NdcDisplayLayoutService {

  private apiUrl = `${environment.url}/layouts`; // Use the apiUrl property from environment

  constructor(private http: HttpClient) { }

  getAllLayouts(): Observable<NdcDisplayLayout[]> {
    return this.http.get<NdcDisplayLayout[]>(this.apiUrl);
  }

  getLayoutById(profileCode: string, screenNumber: string): Observable<NdcDisplayLayout> {
    const params = new HttpParams()
      .set('profileCode', profileCode)
      .set('screenNumber', screenNumber);
    return this.http.get<NdcDisplayLayout>(`${this.apiUrl}/byId`, { params });
  }

  createLayout(layout: NdcDisplayLayout): Observable<NdcDisplayLayout> {
    return this.http.post<NdcDisplayLayout>(this.apiUrl, layout);
  }

  updateLayout(profileCode: string, screenNumber: string, layout: NdcDisplayLayout): Observable<NdcDisplayLayout> {
    // Construire les paramètres de la requête
    const params = new HttpParams()
      .set('profileCode', profileCode)
      .set('screenNumber', screenNumber);

    // Effectuer la requête PUT avec les paramètres et le corps
    return this.http.put<NdcDisplayLayout>(this.apiUrl, layout, { params }).pipe(
      tap(() => {
        console.log(`Layout updated with profileCode=${profileCode} and screenNumber=${screenNumber}`);
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Error occurred while updating layout:', error);
        return throwError(error);
      })
    );
  }

  deleteLayout(profileCode: string, screenNumber: string): Observable<void> {
    // Construire l'URL avec les paramètres
    const params = new HttpParams()
      .set('profileCode', profileCode)
      .set('screenNumber', screenNumber);
    
    // Effectuer la requête DELETE avec les paramètres
    return this.http.delete<void>(`${this.apiUrl}`, { params }).pipe(
      tap(() => {
        console.log(`Layout deletion request sent with profileCode=${profileCode} and screenNumber=${screenNumber}`);
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Error occurred while deleting layout:', error);
        return throwError(error);
      })
    );
  }

  getLayout(profileCode: string, screenNumber: string): Observable<NdcDisplayLayout> {
    const params = new HttpParams()
      .set('profileCode', profileCode)
      .set('screenNumber', screenNumber);

    return this.http.get<NdcDisplayLayout>(`${this.apiUrl}/byId`, { params });
  }

}
