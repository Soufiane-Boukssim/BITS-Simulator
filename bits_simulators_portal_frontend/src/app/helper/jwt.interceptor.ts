import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(
    private authenticationService: AuthService,
    private router: Router
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const user = this.authenticationService.userValue;
    const isLoggedIn = user?.access_token;

    if (isLoggedIn) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${user.access_token}`
        }
      });
    }

    return next.handle(request).pipe(
      tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          // Vérifier si event.body existe et contient respCode
          if (event.body && event.body.respCode === "005") {
            this.authenticationService.logout(); 
          }
        }
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Error response:', error);

        // Vérifier si error.error existe et contient respCode
        const respCode = error.error?.respCode;

        if (respCode === '005') {
          this.router.navigate(['/sign-in']); 
        } else {
          // Gérer d'autres codes de réponse ou erreurs générales ici
          console.error('Unhandled error response code:', respCode);
        }

        // Retourner l'erreur complète pour d'autres parties de l'application
        return throwError(error);
      })
    );
  }
}
