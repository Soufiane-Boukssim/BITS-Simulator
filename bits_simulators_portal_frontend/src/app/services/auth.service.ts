import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/env/environement';

const baseUrl = environment.url;

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userSubject: BehaviorSubject<any | null>;
  public user: Observable<any | null>;
  redirectUrl: string | null = null;
  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject(
      JSON.parse(localStorage.getItem("user")!)
    );
    this.user = this.userSubject.asObservable();
  }

  public get userValue() {
    return this.userSubject.value;
  }

  public set userValue(value: any) {
    this.userSubject.next(value);
    // console.log(value)
    localStorage.setItem("user", JSON.stringify(value));
  }

  login(data: any) {

    // console.log('baseUrl: ', baseUrl);
    return this.http
      .post<any>(`${baseUrl}/o/auth/authenticate`, data)
      .pipe(
        tap((data) => {
          if (data.respCode === "000") {
            // console.log(data)
            localStorage.setItem("user", JSON.stringify(data.result));
            this.userSubject.next(data.result);
            // console.log(this.userValue)
            // console.log('this.userValue: ', this.userValue);
            // console.log('this.redirectUrl: ', this.redirectUrl);
            // console.log('data.userRole: ', data.userRole);
            // console.log("User logged ::> " , data['result']);
            if(data.result.firstConnection == 'Y'){
              this.router.navigate(["/change-password"])
            }
            else{
            switch (data.result.userRole) {
              case 'ADMIN':
                this.router.navigate(["/administration/management"]);
                break;
              case 'USER':
                this.router.navigate(["/user/"]);
                break;
                default:
                this.router.navigate(["/forbidden"]);
                break;
            }
          }
            localStorage.setItem('lang',data.result.languageCode);
            sessionStorage.setItem("user", JSON.stringify(data.result))
            // if(this.redirectUrl){
            //   this.router.navigateByUrl(this.redirectUrl)
            // }else{
            //   this.router.navigate(["/"]);
            // }
          } else {
            return data;
          }
          //user.isLead = user.groups.includes('lead');
        }),
      );
  }
  logout() {
    // remove user from local storage to log user out
    //localStorage.removeItem("user");
    localStorage.clear()
    this.userSubject.next(null);
    this.router.navigate(["/sign-in"]);
  }
}
