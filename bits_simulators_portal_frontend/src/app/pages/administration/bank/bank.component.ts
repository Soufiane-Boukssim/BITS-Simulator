import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.scss']
})
export class BankComponent implements OnInit{

  bankForm: FormGroup = new FormGroup('');
  bankList: any = [];
  showBtnDelete:boolean=false

  itemsPerPageSelct:number=5

  countryList:any[] = [];
  cityList:any[] = [];
  currencyList:any[] = [];


  isDelete: boolean = false;
  idSelected = "";
  MenusList:any = [];
  p: number = 0;


  

  idValid = true;
  countryValid = true;
  cityValid = true;
  currencyValid = true;
  wordingValid = true;
  emailAddValid = true;
  mobileValid = true;
  phoneValid = true;
  user1:any;
  language = "";


  fr = false;
  en = false;
  esp = false;

  alertBody:any = {
    message:'',
    status:'',
    open:false
  }

  countryCode: string | undefined;

  @ViewChild('modal',{read:ElementRef}) modal?:ElementRef
  constructor(
    private globalService: GlobalService,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) { }


  ngOnInit(): void {

    

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if(this.language == "en"){
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters / Bank"));
      this.en=true
    }
    else if(this.language == "fr"){
      Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres / Banque"));
      this.fr=true
    }
    else{
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros / Banco"));
      this.esp=true
    }

    //Promise.resolve().then(() => this.globalService.titleSubject.next("Param/Bank"))
    this.getBankList()
    this.p = 0;
    this.getAllCoutries();
    this.getAllCurrencies();
    this.getAllCities()
  }

  clearAll(): void {
    this.isDelete=false
    this.bankForm.reset();
    this.bankForm = this.formBuilder.group({
      address: "",
      bankType: "",
      cityCode: "",
      countryCode: "",
      currencyCode: "",
      emailAdress: "",
      id: "",
      masterBankCode: "",
      mobileNumber: "",
      phoneNumber: "",
      tvaRate: "",
      wording: "",
      zipCode: "",

    })
  }

  // (((((((((((((((((((BANK HAMZA 05.02.2024)))))))))))))))))))
getBankList() {
  this.globalService.fetchBank().subscribe(response => {
    // console.log('response  BANK: ', response);
    if (response.result.length > 0) {
      this.bankList = response.result
      // console.log('this.bankList: ', this.bankList);
    }
  })
  this.clearAll()
}




userSaveActive = false;
message = "";
openSavePopUp(){

  if (this.bankForm.value.id != "" && this.bankForm.value.emailAdress != "" && this.bankForm.value.mobileNumber != "" &&
  this.bankForm.value.phoneNumber != ""  && this.bankForm.value.countryCode != "" && this.bankForm.value.currencyCode != ""
   && this.bankForm.value.cityCode != "" && this.bankForm.value.wording != "") {
    this.message = "Are you sure you want to save"
    this.userDeleteActive = false;
    this.userSaveActive=true
    this.openModal();
  }else{

        this.alertBody.status = " fill in all fields and then save";
    
      if (this.en) {
          this.alertBody.message = "Please fill in all fields and then save.";
      }
      if (this.fr) {
          this.alertBody.message = "Veuillez remplir tous les champs avant de sauvegarder.";
      }
      if (this.esp) {
          this.alertBody.message = "Por favor complete todos los campos y luego guarde.";
      }
  
      this.alertBody.open = true;
  
      setTimeout(() => {
          this.alertBody.open = false;
      }, 3000);
  }
 

}



submitBank() {

  const emailRegex: RegExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    const emailValid:boolean = emailRegex.test(this.bankForm.value.emailAdress);

    const phoneRegex: RegExp = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
    const isMobileValid:boolean = phoneRegex.test(this.bankForm.value.mobileNumber);
    const isPhoneValid:boolean = phoneRegex.test(this.bankForm.value.phoneNumber);
    
    this.modal?.nativeElement.close();

  // if (!this.isDelete) {
    // if (this.bankForm.value.id == "") {
    //   this.idValid = false;
    // } else {
    //   this.idValid = true;
    // }

    // if (this.bankForm.value.emailAdress == "" || !emailValid) {
    //   this.emailAddValid = false;
    // } else {
    //   this.emailAddValid = true;
    // }

    // if (this.bankForm.value.mobileNumber == "" || !isMobileValid) {
    //   this.mobileValid = false;
    // } else {
    //   this.mobileValid = true;
    // }

    // if (this.bankForm.value.phoneNumber == "" || !isPhoneValid) {
    //   this.phoneValid = false;
    // } else {
    //   this.phoneValid = true;
    // }

    // if (this.bankForm.value.countryCode == "") {
    //   this.countryValid = false;
    // } else {
    //   this.countryValid = true;
    // }

    // if (this.bankForm.value.currencyCode == "") {
    //   this.currencyValid = false;
    // } else {
    //   this.currencyValid = true;
    // }

    // if (this.bankForm.value.cityCode == "") {
    //   this.cityValid = false;
    // } else {
    //   this.cityValid = true;
    // }

    // if (this.bankForm.value.wording == "") {
    //   this.wordingValid = false;
    // } else {
    //   this.wordingValid = true;
    // }
    if(this.isDelete==false){
      // // console.log('this.bankForm.value: ', this.bankForm.value);
     // console.log("vvvvvvvvv");
     
     
    this.globalService.addBank(this.bankForm.value).subscribe(response => {
      // console.log('response: ', response);
      if (response.respCode == '000') {
        // console.log("testtttt");
        this.clearAll();
        this.bankForm.reset();
        this.getBankList();
        // console.log("testttttV2");
        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "Bank added successfuly"
        }
        if(this.fr){
          this.alertBody.message = "Banque a été ajouté avec succès"
        }
        if(this.esp){
          this.alertBody.message = "Banco agregado correctamente"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else if (response.respCode == '409') {
        this.alertBody.status = "Bank  already exists"
        if(this.en){
          this.alertBody.message = "Bank  already exists"
        }
        if(this.fr){
          this.alertBody.message = "Banque deja exists"
        }
        if(this.esp){
          this.alertBody.message = "Banco  ya existe"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else{

        this.alertBody.status = "Something went wrong, please check that the entred data is valid"
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

  
  else {
    // console.log('this.bankForm.value: ', this.bankForm.value);
    this.globalService.updateBank(this.bankForm.value).subscribe(response => {
      // console.log('response: ', response);
      // if (response.respCode == '000') {
      //   if(this.userDeleteActive == false){
      //     this.bankForm.reset();
      //     this.getBankList();
      //   }
      // }


      if (response.respCode == '000') {
        this.clearAll();
        this.bankForm.reset();
        this.getBankList();

        this.showBtnDelete=false

        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "Bank  update successfuly"
        }
        if(this.fr){
          this.alertBody.message = "Banque update avec succès"
        }
        if(this.esp){
          this.alertBody.message = "Banco  añadido con update"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else{

        this.alertBody.status = "Something went wrong, please check that the entred data is valid"
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



selectBankRecord(id: string) {
  this.bankList.map((item: any) => {
    if (item.id == id) {
      // console.log("ID FOUND");
      this.bankForm.setValue({
        address: item.address,
      bankType: item.bankType,
      cityCode: item.cityCode,
      countryCode: item.countryCode,
      currencyCode: item.currencyCode,
      emailAdress: item.emailAdress,
      id: item.id,
      masterBankCode: item.masterBankCode,
      mobileNumber: item.mobileNumber,
      phoneNumber: item.phoneNumber,
      tvaRate: item.tvaRate,
      wording: item.wording,
      zipCode: item.zipCode,
      })
      this.idSelected = item.id;
      this.isDelete = true;
    }
    // console.log("banks :: Form ==> " , this.bankForm.value);
  })
}

searchText: string = '';
get filteredBankList() {
  return this.bankList.filter((item:any) =>
    item.id.toLowerCase().includes(this.searchText.toLowerCase()) ||
    item.wording.toLowerCase().includes(this.searchText.toLowerCase())
  );
}

/// ------------- filling the lists -----------------///

getAllCoutries(){
  this.globalService.fetchCountry().subscribe(res => {
    // // console.log('res Countries: ', res);

    res['result'].map((obj:any) => {
      this.countryList.push(obj);

    })
    // console.log('this.countryList: ', this.countryList);
  })
}


getAllCurrencies(){
  this.globalService.fetchCurrency().subscribe(res => {
    // // console.log('res Currency: ', res);

    res['result'].map((obj:any) => {
      this.currencyList.push(obj);

    })
    // console.log('this.currencyList: ', this.currencyList);
  })
}

getAllCities(){
  this.globalService.fetchCities().subscribe(res => {
    // console.log("CITIES ", res);
    if (res.result.length > 0) {
      this.cityList = res.result
      // console.log('this.cityList: ', this.cityList);
    }
  })
}

/*-------- DELETE BUTTON -----------------*/

userDeleteActive = false;
openDeletePopUp(){
  this.message = "Please click Delete to proceed the action"
  this.userDeleteActive = true;
  this.userSaveActive=false;
  this.openModal();
  this.bankForm
  // console.log('this.bankForm: popup', this.bankForm.value);
  //this.selectBankRecord(this.idSelected);

}

deleteBankRecord() {
  // console.log('this.id: ', this.idSelected);
  this.modal?.nativeElement.close();
  this.globalService.deleteBank(this.idSelected).subscribe(
    response => {
        // console.log('response: ', response);
        

        this.closeModal()
        if (response.respCode=="000") {
           this.getBankList();
            this.closeModal();
            this.init()

          this.alertBody.status = "000"
          if (this.en) {
            this.alertBody.message = "Bank delete successfuly";
          }
          
          if (this.fr) {
            this.alertBody.message = "Suppression du Banque réussie";
          }
          
          if (this.esp) {
            this.alertBody.message = "Eliminación del Banco exitosa";
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
        else{

          this.alertBody.status = "Something went wrong deleted Bank "
          if (this.en) {
            this.alertBody.message = "Something went wrong during Bank deletion";
          }
          
          if (this.fr) {
            this.alertBody.message = "Une erreur est survenue lors de la suppression du Banque";
          }
          
          if (this.esp) {
            this.alertBody.message = "Algo salió mal durante la eliminación del Banco";
          }
          
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);

        }
    }
  )
}

openModal(){
  this.modal?.nativeElement.showModal()
}
closeModal(){
  this.modal?.nativeElement.close();
}



init(){

}

}
