import { Component, OnInit ,ElementRef , ViewChild } from '@angular/core';
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
  dateStartPass:string,
  dateEndPass:string
}
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {



  bankList:any = [];

  userBlocked = false;
  message = "";
  btnMsg = "";
  userEmpty = false;
  emailValid = true;

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
    blockAccess:'N',
    dateStartPass:'',
    dateEndPass:''
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
    status:'',
    languageCode:'',
    ipAddress:'',
    blockAccess:'N',
    dateStartPass:'',
    dateEndPass:''
}

@ViewChild('modal',{read:ElementRef}) modal?:ElementRef
  userLangEmpty: boolean = false;
  userNameEmpty = false;
  userTypeEmpty = false;
  userEmailEmpty = false;
  userBankEmpty = false;
  userNbTryEmpty = false;
  userStatusEmpty = false;
  dateStartEmpty = false;
  dateEndEmpty = false;

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

  constructor(private globalService : GlobalService,private router:Router){}
  ngOnInit(): void {
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if(this.language == "en"){
      Promise.resolve().then(() => this.globalService.titleSubject.next("User/Add New User"));
      this.en = true;
    }
    else if(this.language == "fr"){
      Promise.resolve().then(() => this.globalService.titleSubject.next("Utilisateur/Ajouter Nouveau Utilisateur"));
      this.fr = true;
    }
    else{
      Promise.resolve().then(() => this.globalService.titleSubject.next("Usuario/Agregar Nuevo Usuario"));
      this.esp = true;
    }

    this.getBankList()
  }

  onCheckboxChange() {
    if (this.userBlocked) {
      // console.log('unchecked');
      this.user.blockAccess = 'Y';
      // console.log('this.user.blockAccess: ', this.user.blockAccess);
    } else {
      // console.log('checked');
      this.user.blockAccess = 'N';
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
      blockAccess:'',
      dateStartPass:'',
      dateEndPass:''
  }
  }

  save(){

    // console.log("bankList :: ", this.bankList);
    
    this.initData();
    const emailRegex: RegExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    const isValid:boolean = emailRegex.test(this.user.email);
    // console.log('isValid: ', isValid);
    // console.log("this.user.userBankCode :: " ,this.user.userBankCode);
    
    this.data = {
      userCode :this.user.userCode,
    userName:this.user.userName,
    userType:this.user.userType,
    address:this.user.address,
    mobileNumber:this.user.mobileNumber,
    email:this.user.email,
    userBankCode:this.user.userBankCode,
    numberOfTriesAllowed:this.user.numberOfTriesAllowed,
    password:this.user.password,
    numberOfTries:this.user.numberOfTries,
    status:this.user.status,
    languageCode:this.user.languageCode,
    ipAddress:this.user.ipAddress,
    blockAccess:this.user.blockAccess,
    dateStartPass:this.user.dateStartPass,
    dateEndPass:this.user.dateEndPass
    };
    // console.log('data.userCode: ', this.data.userCode);

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
    if(this.data.userType == ""){
      this.userTypeEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userTypeEmpty = false;
    }
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
    // console.log("test this.data.userBankCode 0",this.data.userBankCode);
    
    if(this.data.userBankCode == ""){
      this.userBankEmpty = true;
      // console.log("required bank!!");
    }else{
      this.userBankEmpty = false;
    }
    if(this.data.numberOfTriesAllowed == ""){
      this.userNbTryEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userNbTryEmpty = false;
    }
    if(this.data.status == ""){
      this.userStatusEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userStatusEmpty = false;
    }
    if(this.data.languageCode == ""){
      this.userLangEmpty = true;
      // console.log("required Data!!");
    }else{
      this.userLangEmpty = false;
    }
    if(this.data.dateStartPass == ""){
      this.dateStartEmpty = true;
    }
    else{
        this.dateStartEmpty = false;
    }
    if(this.data.dateEndPass == ""){
      this.dateEndEmpty = true;
    }
    else{
        this.dateEndEmpty = false;
    }

    
    // console.log('this.userLangEmpty: ', this.userLangEmpty);
    if(this.data.userCode != "" && this.data.userName != ""  && this.data.userType != ""
    && this.data.email != ""  && this.data.userBankCode != ""
    && this.data.numberOfTriesAllowed != ""  && this.data.status != ""  && this.data.languageCode != "" &&
    this.data.dateStartPass != "" && this.data.dateEndPass){

    // // console.log("data saved ==>",this.data);
      
      this.globalService.AddUser(this.data).subscribe(res =>{
        // console.log("result ::> " , res);
        if(res['respCode'] == '000'){
          this.message = "User was successfuly Added";
          this.btnMsg = "back to Menu";
          this.navigateToMenu = true;
          //this.openModal()
          this.router.navigate(['/administration/management']);
          /*this.message = "User already exist";
          this.btnMsg = "try again";*/
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "User added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "L’utilisateur a été ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Usuario agregado correctamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
        if(res['respCode'] == '409'){
          this.message = "User already exist";
          this.btnMsg = "try again";
          this.alertBody.status = res.respCode
          if(this.en){
            this.alertBody.message = "User already Exist"
          }
          if(this.fr){
            this.alertBody.message = "L'utilisateur existe déjà"
          }
          if(this.esp){
            this.alertBody.message = "La usuario ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
        if(res['respCode'] == '001'){
          this.message = "Something went wrong, please check that the entred data is valid";
          this.btnMsg = "try again";
          this.alertBody.status = res.respCode
          //this.alertBody.message = res.respMsg
          if(this.en){
            this.alertBody.message = "SomeThing went wrong (Invalid cridentials or connection issue)"
          }
          if(this.fr){
            this.alertBody.message = "Une erreur s'est produite (identifiants invalides ou problème de connexion)"
          }
          if(this.esp){
            this.alertBody.message = "Algo salió mal (credenciales no válidas o problema de conexión)"
          }
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
      this.router.navigate(['/administration']);
    }
  }

  getBankList() {
    this.globalService.fetchBank().subscribe(response => {
      // console.log('response  BANK: ', response);
      if (response.result.length > 0) {
        this.bankList = response.result
        // console.log('this.bankList: ', this.bankList);
      }
    })
  }

}
