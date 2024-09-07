import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit{
  userCode:string = "";
  oldPassword:string = "";
  newPassword:string = "";
  islogged = true;
  errorMsg = false;
  message = "";
  retrievedJS :any =null;
 retrievedJSParsed: any = null;
 @ViewChild('modal',{read:ElementRef}) modal?:ElementRef;

 language = "";
  user1:any = "";
  fr = false;
  en = false;
  esp = false;

 alertBody:any = {
  message:'',
  status:'',
  open:false
}
 // retrievedCode:string = "";
  constructor(private service:GlobalService,private router:Router){}

  ngOnInit(): void {
    this.retrievedJS = localStorage.getItem('user');
    if(this.retrievedJS == null){
      this.islogged = false;
    }
    else{
      this.islogged = true;
    }
    this.retrievedJSParsed = JSON.parse(this.retrievedJS);
    // console.log('retrievedCode: ', this.retrievedJSParsed.userId);
    this.userCode = this.retrievedJSParsed.userId;


    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if(this.language == "en"){
      this.en = true;
    }
    else if(this.language == "fr"){
      this.fr = true;
    }
    else{
      this.esp = true;
    }
      //this.userCode
  }

  changePassword(){
    // console.log('this.userCode,this.oldPassword,this.newPassword: ', this.userCode ," ",this.oldPassword," ",this.newPassword);
    this.service.changePassword(this.userCode,this.oldPassword,this.newPassword).subscribe(res => {
      if(res['respCode'] == '000'){
        this.message = "Password was successfuly edited";
        this.openModal();
      }
      else{
        this.errorMsg = true;
        this.message = "incorrect cridentials";
        this.alertBody.status = res.respCode
          if(this.en){
            this.alertBody.message = "incorrect cridentials"
          }
          if(this.fr){
            this.alertBody.message = "informations d'identification incorrectes"
          }
          if(this.esp){
            this.alertBody.message = "credenciales incorrectas"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
      }
      // console.log("RESULT ==> ", res);
    })
  }

  openModal(){
    this.modal?.nativeElement.showModal();
  }

  closeModal(){
    this.modal?.nativeElement.close();

      this.router.navigate(['/sign-in']);

  }


  showPassword: boolean = false;

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  showOldPassword: boolean = false;
  confirmPassword = '';

  toggleOldPasswordVisibility() {
    this.showOldPassword = !this.showOldPassword;
  }

  passwordsDoNotMatch(): boolean {
    return this.newPassword !== this.confirmPassword;
  }
}
