import { Component, ElementRef,OnInit,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/services/global.service';

export interface UserManagement {
  userCode :string,
  userName:string,
  userType:string,
  address:string,
  mobileNumber:string,
  email:string,
  userBankCode:string,
  numberOfTriesAllowed:string,
  password:string,
  numberOfTries:string,
  status:string,
  languageCode:string,
  ipAddress:string,
  blockAccess:string;
}
@Component({
  selector: 'app-self-sign-up',
  templateUrl: './self-sign-up.component.html',
  styleUrls: ['./self-sign-up.component.scss']
})
export class SelfSignUpComponent implements OnInit{

  userBlocked = false;
  message = "";
  btnMsg = "";
  userEmpty = false;
  emailValid = true;
  phoneValid = true;

  ListOfBanks:any = [];

  navigateToMenu = false;

  data: UserManagement = {
    userCode :'',
    userName:'',
    userType:'',
    address:'',
    mobileNumber:'',
    email:'',
    userBankCode:'',
    numberOfTriesAllowed:'',
    password:'',
    numberOfTries:'',
    status:'',
    languageCode:'',
    ipAddress:'',
    blockAccess:''
}


  user: UserManagement = {
    userCode :'',
    userName:'',
    userType:'',
    address:'',
    mobileNumber:'',
    email:'',
    userBankCode:'',
    numberOfTriesAllowed:'',
    password:'',
    numberOfTries:'',
    status:'P',
    languageCode:'',
    ipAddress:'',
    blockAccess:'N'
}

@ViewChild('modal',{read:ElementRef}) modal?:ElementRef
  userLangEmpty: boolean = false;
  userNameEmpty = false;
  userTypeEmpty = false;
  userEmailEmpty = false;
  userBankEmpty = false;
  userNbTryEmpty = false;
  userStatusEmpty = false;

  alertBody:any = {
    message:'',
    status:'',
    open:false
  }

  constructor(private globalService : GlobalService,private router:Router){}
  ngOnInit(): void {
    Promise.resolve().then(() => this.globalService.titleSubject.next("Administration/AddUser"));
    this.getAllBanks();
  }

  onCheckboxChange() {
    if (this.userBlocked) {
      // console.log('unchecked');
      this.user.blockAccess = 'N';
      // console.log('this.user.blockAccess: ', this.user.blockAccess);
    } else {
      // console.log('checked');
      this.user.blockAccess = 'Y';
      // console.log('this.user.blockAccess: ', this.user.blockAccess);
    }
    // Toggle the userBlocked value
    this.userBlocked = !this.userBlocked;
  }

  initData(){
    this.data = {
      userCode :'',
      userName:'',
      userType:'',
      address:'',
      mobileNumber:'',
      email:'',
      userBankCode:'',
      numberOfTriesAllowed:'',
      password:'',
      numberOfTries:'',
      status:'',
      languageCode:'',
      ipAddress:'',
      blockAccess:''
  }
  }

  getAllBanks(){
    this.globalService.fetchBank().subscribe(res => {
      // console.log('BANKS //:: ', res['result'])
      res['result'].map((obj:any) => {
        this.ListOfBanks.push(obj)
      })
      //this.ListOfBanks.push(res['result']);
    })
  }
  save(){
    this.initData();
    const emailRegex: RegExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    const isValid:boolean = emailRegex.test(this.user.email);
    // console.log('isValid: ', isValid);
    const phoneRegex: RegExp = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
    const isPhoneValid:boolean = phoneRegex.test(this.user.mobileNumber);
    // console.log('isPhoneValid: ', isPhoneValid);
    this.data = {
      userCode :this.user.userCode,
    userName:this.user.userName,
    userType:"USER",
    address:this.user.address,
    mobileNumber:this.user.mobileNumber,
    email:this.user.email,
    userBankCode:this.user.userBankCode,
    numberOfTriesAllowed:"3",
    password:this.user.password,
    numberOfTries:this.user.numberOfTries,
    status:this.user.status,
    languageCode:this.user.languageCode,
    ipAddress:this.user.ipAddress,
    blockAccess:this.user.blockAccess
    };
    // console.log('data.userCode: ', this.data.userCode);
    // console.log('this.data.userBankCode: ', this.data.userBankCode);

    if(this.data.userCode == ""){
      this.userEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userEmpty = false;
    }
    if(this.data.userName == ""){
      this.userNameEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userNameEmpty = false;
    }
    // if(this.data.userType == ""){
    //   this.userTypeEmpty = true;
    //   // console.log("required Data!!");
    // }else{
    //   this.userTypeEmpty = false;
    // }
    if(this.data.email == ""){
      this.userEmailEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userEmailEmpty = false;
      if(isValid){
        this.emailValid = true;
      }else{
        this.emailValid = false;
      }
    }
    if(this.data.mobileNumber == ""){
      // console.log("empty Mobile number");
    }else{
      if(isPhoneValid){
        this.phoneValid = true;
      }else{
        this.phoneValid = false;
      }
    }
    if(this.data.userBankCode == ""){
      this.userBankEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userBankEmpty = false;
    }
    // if(this.data.numberOfTriesAllowed == ""){
    //   this.userNbTryEmpty = true;
    //   // console.log("required Data!!");
    // }else{
    //   this.userNbTryEmpty = false;
    // }
    /*if(this.data.status == ""){
      this.userStatusEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userStatusEmpty = false;
    }*/
    if(this.data.languageCode == ""){
      this.userLangEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userLangEmpty = false;
    }
    if(this.data.userCode != "" && this.data.userName != ""  /*&& this.data.userType != ""*/  && this.data.email != ""  && this.data.userBankCode != ""  /*&& this.data.numberOfTriesAllowed != "" */ && this.data.status != ""  && this.data.languageCode != ""){
      this.globalService.AddUser(this.data).subscribe(res =>{
        // console.log("result ::> " , res);
        if(res['respCode'] == '000'){
          this.message = "you are signed up successfuly, you will receive your password on your mail box soon :)";
          this.btnMsg = "back to Menu";
          this.navigateToMenu = true;
          this.openModal();
        }
        if(res['respCode'] == '409'){
          this.message = "User already exist";
          this.btnMsg = "try again";
          //this.openModal()
          this.alertBody.status = res.respCode
          this.alertBody.message = res.respMsg
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
        if(res['respCode'] == '001'){
          this.message = "Something went wrong, please check that the entred data is valid";
          this.btnMsg = "try again";
          this.alertBody.status = res.respCode
          this.alertBody.message = res.respMsg
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
      })
    }
  }


  openModal(){
    this.modal?.nativeElement.showModal();
  }

  closeModal(){
    this.modal?.nativeElement.close();
    if(this.navigateToMenu == true){
      this.router.navigate(['/sign-in']);
    }
  }


}
