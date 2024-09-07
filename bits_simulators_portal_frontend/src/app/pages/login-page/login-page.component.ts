import { Component, HostListener, Input, OnInit, ViewChild } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
body:any = {
    "userCode": "",
    "password": ""
}
alertBody:any = {
  message:'',
  status:'',
  open:false
}
loading:boolean = false
constructor(private authService:AuthService){}
ngOnInit(): void {
  
} 
enterLogin(event:KeyboardEvent){
    if (event.key === 'Enter') {
      this.login()
    }
}
login(){
  this.loading = true
  this.authService.login(this.body).subscribe(response=>{
    this.alertBody.status = response.respCode
    this.alertBody.message = response.respMsg
    this.loading = false
    this.alertBody.open = true
    setTimeout(() => {
      this.alertBody.open = false;
    }, 3000);
  })
}

closeAlert(){
  this.alertBody.open = false;
}

}
