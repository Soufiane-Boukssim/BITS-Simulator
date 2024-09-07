import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ATMProfile } from '../models/atm-profile.model';

@Injectable({
  providedIn: 'root'
})
export class ATMProfileService {
  private apiUrl = 'http://localhost:9193/api/atm-profiles'; // URL de l'API

  // Source de message pour les notifications
  private messageSource = new BehaviorSubject<string | null>(null);
  currentMessage = this.messageSource.asObservable();

  constructor(private http: HttpClient) {}

  setMessage(message: string | null) {
    this.messageSource.next(message);
  }

  // Créer ou mettre à jour un profil ATM
  createOrUpdateATMProfile(atmProfile: ATMProfile): Observable<ATMProfile> {
    return this.http.post<ATMProfile>(this.apiUrl, atmProfile).pipe(
      catchError(this.handleError)
    );
  }

  // Obtenir un profil ATM par code
  getATMProfileByCode(profileCode: string): Observable<ATMProfile> {
    return this.http.get<ATMProfile>(`${this.apiUrl}/${profileCode}`).pipe(
      catchError(this.handleError)
    );
  }

  // Obtenir tous les profils ATM
  getAllATMProfiles(): Observable<ATMProfile[]> {
    return this.http.get<ATMProfile[]>(this.apiUrl).pipe(
      catchError(this.handleError)
    );
  }

  // Supprimer un profil ATM par code
  deleteATMProfileByCode(profileCode: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${profileCode}`).pipe(
      catchError(this.handleError)
    );
  }

  // Gérer les erreurs
  private handleError(error: HttpErrorResponse) {
    console.error('Une erreur est survenue:', error);
    return throwError('Une erreur est survenue, veuillez réessayer plus tard.');
  }
}
