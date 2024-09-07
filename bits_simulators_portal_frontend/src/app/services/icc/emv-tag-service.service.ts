import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { EmvTag } from 'src/app/models/EmvTag';
import { GlobalResponse } from 'src/app/models/GlobalResponse';
import { environment } from 'src/env/environement';

const baseUrl = environment.url
@Injectable({
  providedIn: 'root'
})
export class EmvTagServiceService {

  constructor(private http: HttpClient) { }

  getTags(): Observable<GlobalResponse<EmvTag[]>> {
    return this.http.get<GlobalResponse<EmvTag[]>>(`${baseUrl}/icc/emvtag/getAll`)
    .pipe(
      catchError((error) => {
        console.error('Error getting emv tags : ' + error);
        throw error;
      })
    )
  }

  
  saveDataToBackend(body: any):Observable<any>{
    return this.http.post(baseUrl + '/icc/emvtag/save', body)
  }

  private apiUrl = 'http://localhost:8080/api/emvTags'; // Remplacez par votre URL d'API



  // getAllEmvTags(): Observable<EmvTag[]> {
  //   return this.http.get<EmvTag[]>(this.apiUrl);
  // }
  

  getAllEmvTags(): Observable<EmvTag[]> {
    return this.http.get<EmvTag[]>(this.apiUrl)
      .pipe(
        tap((emvTags: EmvTag[]) => {
          // console.log('Données récupérées avec succès : ', emvTags);
        }),
        catchError((error: any) => {
          console.error('Erreur lors de la récupération des données : ', error);
          // Gérer les erreurs ici
          return throwError(error);
        })
      );
  }
  
}
