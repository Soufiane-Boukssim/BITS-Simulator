import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { GlobalService } from './global.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {


  userHabilitations:any = null;

  constructor(private router: Router, private authService: AuthService,
    private globalService: GlobalService,
    ) {}

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree>> {
    const user = this.authService.userValue;
    const allowedRoles:{[route:string]:string[]} = {
      '/administration':['ADMIN'],
      '/user':['USER'],
    }
    const url = state.url
    // console.log('state.url: ', state.url);
    // console.log('user',user);

  //  if (user) {
  //   let body = {
  //     "user_code":user.userId
  //   } 
   
  //     this.globalService.getUserRolePage(body).subscribe(
  //         (response) => { 

  //           if (response.respCode==="000") {
  //             console.log("--->User_Page response  :",response.result);
  //             this.userHabilitations = response.result
  //            //  localStorage.setItem('userHabilitations', JSON.stringify(this.userHabilitations));
 
             
  //           }
  
  //         },
  //         (error) => {
  //             console.error('Error fetching User_Page:', error);
  //         }
  //     );
  //  }else{
  //   this.authService.logout()
  //   this.router.navigate(['/sign-in']);
  //  }
   



  if (user) {
    let body = {
      "user_code": user.userId
    };

    try {
      const response = await this.globalService.getUserRolePage(body).toPromise();
      if (response.respCode === "000") {
        this.userHabilitations = response.result;

        if (state.url.startsWith('/user') && user.userRole == "USER") {
          const path = state.url.split('/')[2];
          if (path) {
            if (this.hasAccess(path)) {
              return true;
            } else {
              this.authService.logout();
              // this.router.navigate(['/sign-in']);
              return false;
            }
          } else {
            return true;
          }
        }
      }
    } catch (error) {
      console.error('Error fetching User_Page:', error);
      this.authService.logout();
      // this.router.navigate(['/sign-in']);
      return false;
    }
  } else {
    this.authService.logout();
    // this.router.navigate(['/sign-in']);
    return false;
  }

    
      
  


    if ((state.url.startsWith('/sign-in') || state.url.startsWith('/sign-up') ) && user) {
      if(user.userRole == "ADMIN"){
        this.router.navigate(['/administration/management']);
        
      }
      if(user.userRole == "USER"){
        
        this.router.navigate(['/user/']);
        
    
      }
      return false;
    }

    if ((state.url.startsWith('/sign-in') || state.url.startsWith('/sign-up')) && !user) {
      return true;
    }

    if (state.url.startsWith('/user') && user.userRole == "ADMIN") {
      this.router.navigate(['/sign-in'], { queryParams: { returnUrl: url } });
      return false;
    }

    
    // if (state.url.startsWith('/user') && user.userRole == "USER"){
    //   // console.log("test url ===>", state.url);
      
    // const path = state.url.split('/')[2]; // Adjust based on your URL structure
    //     // console.log("PATH:: ",path);
    //     if(path){
    //       // console.log("this.hasAccess(path) ==>",this.hasAccess(path));
          
    //       if (this.hasAccess(path)) {
    //         return true;
    //       } else {
    //         // Optionally redirect to an unauthorized page
    //         this.authService.logout()
    //         this.router.navigate(['/sign-in']);
    //         return false;
    //       }
    //     }
    //     {
    //       return true;
    //     }
        
        
    //   }

    if (state.url.startsWith('/administration') && user.userRole == "USER") {
      this.router.navigate(['/sign-in'], { queryParams: { returnUrl: url } });
      return false;
    }

    if (!user) {
      this.router.navigate(['/sign-in'], { queryParams: { returnUrl: url } });
      return false;
    }else{
      return true;
    }

    // const allowedRolesForRoute = allowedRoles[url];
    // if(allowedRolesForRoute && allowedRolesForRoute.includes(user.userRole)){
    //   return true;
    // }else{
    //   this.authService.logout()
    // }
    // return false;
  }


  hasAccess(habilitationCode: string): boolean {
    // console.log("habilitationCode ==>",habilitationCode);
    //console.log("userHabilitations ==>",this.userHabilitations);

    //  this.userHabilitations=JSON.parse(localStorage.getItem('userHabilitations') || '[]');


    var checkhabilitationCode = false;
    for (let i = 0; i < this.userHabilitations.length; i++) {
      const h = this.userHabilitations[i];
        // console.log("hhhhh=>",h.habilitationCode);
      
       // console.log(" result habilitationCode===> ", h.habilitationCode === habilitationCode.toUpperCase());
       
       checkhabilitationCode =  (h.habilitationCode).toUpperCase() === habilitationCode.toUpperCase()
       if(checkhabilitationCode === true){
        break;
       }
     }

    return checkhabilitationCode;
  }
}
