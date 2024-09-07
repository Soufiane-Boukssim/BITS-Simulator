import { group } from '@angular/animations';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-general-options-admin',
  templateUrl: './general-options-admin.component.html',
  styleUrls: ['./general-options-admin.component.scss']
})
export class GeneralOptionsAdminComponent {
  @ViewChild('modal',{read:ElementRef}) modal?:ElementRef
  p: number = 0;

  isReadOnly: boolean = false;

  mtiForm: FormGroup = new FormGroup('');
  idMti: FormGroup = new FormGroup('');
  mtiList: any = []

  currencyForm: FormGroup = new FormGroup('');
  currencyList: any = []

  countryForm: FormGroup = new FormGroup('');
  countryList: any = []

  cityForm: FormGroup = new FormGroup('');
  cityList: any = []

  exchangeForm: FormGroup = new FormGroup('');
  idExchange: FormGroup = new FormGroup('');
  exchangeList: any = []

  responseForm: FormGroup = new FormGroup('');
  idResponse: FormGroup = new FormGroup('');
  responseList: any = []

  mccForm: FormGroup = new FormGroup('');
  idMcc: FormGroup = new FormGroup('');
  mccList: any = []

  tagForm: FormGroup = new FormGroup('');
  idTag: FormGroup = new FormGroup('');
  tagList: any = []

  accountForm: FormGroup = new FormGroup('');
  idAccount: FormGroup = new FormGroup('');
  accountList: any = []

  functionForm: FormGroup = new FormGroup('');
  idFunction: FormGroup = new FormGroup('');
  functionList: any = []

  processingForm: FormGroup = new FormGroup('');
  idProcessing: FormGroup = new FormGroup('');
  processingList: any = []
  isDelete: boolean = false;

  bankForm: FormGroup = new FormGroup('');
  bankList: any = []

  GroupForm: FormGroup = new FormGroup('');
  idGroup:FormGroup = new FormGroup('');
  GroupList: any = []

  menuForm: FormGroup = new FormGroup('');
  menuList: any = []

  
  alertBody: any = {
    message: '',
    status: '000',
    open: false
  }

  user1:any

  fr = false;
  en = false;
  esp = false;
  language:  string = ""
  protocol: string = "";
  bankCode: any = "";


  message=""
  constructor(
    private globalService: GlobalService,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {}
  tab_1: boolean = true
  tab_2: boolean = false
  tab_3: boolean = false
  tab_4: boolean = false
  tab_5: boolean = false
  tab_6: boolean = false
  tab_7: boolean = false
  tab_8: boolean = false
  tab_9: boolean = false
  tab_10: boolean = false
  tab_11: boolean = false
  tab_12: boolean = false
  user: any


  idSelected = "";
  MenusList:any = [];

  DeleteScop = "";




  
  ngOnInit() {
    this.authService.user.subscribe(
      (x: any) => {
        this.user = x;
        // console.log(this.user)
      }
    );
    this.clearAll()


    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if(this.language == "en"){
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters / General"));
      this.en=true;
    }
    else if(this.language == "fr"){
      Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres / Général"));
      this.fr=true;
    }
    else{
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros / General"));
      this.esp=true;
    }

    //Promise.resolve().then(() => this.globalService.titleSubject.next("Parameter/General"))


    this.getCurrencyList()
  }

  open_tab(index: number) {
    this.clearAll()
    this.resetErrorMsg();
    this.userDeleteActive = false
    this.isDelete = false
    this.tab_1 = false
    this.tab_2 = false
    this.tab_3 = false
    this.tab_4 = false
    this.tab_5 = false
    this.tab_6 = false
    this.tab_7 = false
    this.tab_8 = false
    this.tab_9 = false
    this.tab_10 = false
    this.tab_11= false
    this.tab_12= false
    switch (index) {
      case 0:
        this.tab_1 = true
        this.getCurrencyList()
        this.p = 0
        this.DeleteScop = "currency"
        break;
      case 1:
        this.tab_2 = true
        this.getCountryList()
        this.DeleteScop = "country"
        this.p = 0
        break;
      case 2:
        this.tab_3 = true
        this.getAllCities();
        this.getCountryList();
        this.getBankList();
        this.DeleteScop = "city"
        this.p = 0
        break;
      case 3:
        this.tab_4 = true
        this.getResponseList()
        this.p = 0
        break;
      case 4:
        this.tab_5 = true
        this.getMccList()
        this.DeleteScop = "mcc"
        this.p = 0
        break;
      case 5:
        this.tab_6 = true
        this.getMTIList()
        this.DeleteScop = "mti"
        this.p = 0
        break;
      case 6:
        this.tab_7 = true
        this.getTagList()
        this.p = 0
        break;
      case 7:
        this.tab_8 = true
        this.getAccountList()
        this.DeleteScop = "account"
        this.p = 0
        break;
      case 8:
        this.tab_9 = true
        this.getFunctionList()
        this.DeleteScop = "function"
        this.p = 0
        break;
      case 9:
        this.tab_10 = true
        this.getProcessingList()
        this.DeleteScop = "processing"
        this.p = 0
        break;
      case 10:
        this.tab_11 = true;

        this.p = 0
        break;
        case 11:
          this.tab_12 = true;
          this.getGroupList();
          this.getAllMenus();
          this.DeleteScop = "group"
          this.p = 0
          break;

      default:
        break;
    }
  }

  resetErrorMsg(){
    this.codeMtiValid = true;
    this.mtiDescValid = true;
    this.mtiDirValid = true;
    this.idCountValid = true;
    this.countDescValid = true;
    this.countAlpha2Valid = true;
    this.countAlpha3Valid = true;
    this.idCcyValid = true;
    this.CcyDescValid = true;
    this.CcyAlphaValid = true;
    this.CcyExponnentValid = true;
    this.codeMccValid = true;
    this.mccDescValid = true;
    this.codeAccValid = true;
    this.AccDescValid = true;
    this.fcCodeValid = true;
    this.fcDescValid = true;
    this.procCodeValid = true;
    this.procDescValid = true;
    this.groupCodeValid = true;
    this.groupNameValid = true;
    this.menuCodeValid = true;
    this.searchText= '';
  }

  clearAll(): void {
    this.isReadOnly = false;
    this.isDelete = false;
    this.mtiForm.reset();
    this.currencyForm.reset();
    this.countryForm.reset();
    this.cityForm.reset();
    this.exchangeForm.reset();
    this.responseForm.reset();
    this.mccForm.reset();
    this.tagForm.reset();
    this.accountForm.reset();
    this.functionForm.reset();
    this.processingForm.reset();
    this.bankForm.reset();
    this.GroupForm.reset();
    this.menuForm.reset();
    this.idMti = this.formBuilder.group({
      mtiCode: [''],
      mtiProtocol: [''],
      bankCode: [this.user.bankCode]
    }),
      this.mtiForm = this.formBuilder.group({
        id: this.idMti,
        mtiDesc: [''],
        mtiDirection: ['']
      })

    this.currencyForm = this.formBuilder.group({
      id: [''],
      ccyDesc: [''],
      ccyAlpha: [''],
      ccyExponent: [0]
    })

    this.countryForm = this.formBuilder.group({
      id: [''],
      countryDesc: [''],
      countryAlpha2: [''],
      countryAlpha3: ['']
    })



    this.cityForm = this.formBuilder.group({
      bankCode:[''],
      cityCode:[''],
      countryCode:[''],
      wording:['']
    })

    this.idExchange = this.formBuilder.group({
      baseCcy: [''],
      rateStartDate: [''],
      currCode: [''],
      rateProtocol: ['I'],
      bankCode: [this.user.bankCode]
    }),
      this.exchangeForm = this.formBuilder.group({
        id: this.idExchange,
        buyRate: [0],
        midRate: [0],
        sellRate: [0]
      })

    this.idResponse = this.formBuilder.group({
      respCode: [''],
      respProtocol: ['I'],
      bankCode: [this.user.bankCode]
    })
    this.responseForm = this.formBuilder.group({
      id: this.idResponse,
      respDesc: [''],
      respAction: ['']
    })


    this.idMcc = this.formBuilder.group({
      mccCode: [''],
      mccProtocol: ['I'],
      bankCode: [this.user.bankCode]
    })
    this.mccForm = this.formBuilder.group({
      id: this.idMcc,
      mccDesc: ['']
    })


    this.idTag = this.formBuilder.group({
      subfldId: [''],
      subfldProtocole: ['I'],
      bankCode: [this.user.bankCode]
    })
    this.tagForm = this.formBuilder.group({
      subfldDefinitionId: this.idTag,
      subfldDescr: [''],
      subfldType: [''],
      subfldLengthMax: [0]
    })


    this.idAccount = this.formBuilder.group({
      accCode: [''],
      accProtocol: ['I'],
      bankCode: [this.user.bankCode]
    })
    this.accountForm = this.formBuilder.group({
      id: this.idAccount,
      accDesc: ['']
    })


    this.idFunction = this.formBuilder.group({
      fctCode: [''],
      fctProtocol: ['I'],
      bankCode: [this.user.bankCode],
      ftcProtocol: ['I']
    })
    this.functionForm = this.formBuilder.group({
      id: this.idFunction,
      fctDesc: ['']
    })


    this.idProcessing = this.formBuilder.group({
      procCode: [''],
      procProtocol: ['I'],
      bankCode: [this.user.bankCode]
    })
    this.processingForm = this.formBuilder.group({
      id: this.idProcessing,
      procDesc: ['']
    })

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

    this.idGroup = this.formBuilder.group({
      groupCode : [''],
      menuCode : ['']
    })
    this.GroupForm = this.formBuilder.group({
      groupId: this.idGroup,
      groupName : ''
    })

    this.menuForm = this.formBuilder.group({
      menuName:"",
    })

  }


  //City
  getAllCities(){
    this.globalService.fetchCities().subscribe(res => {
      // console.log("CITIES ", res);
      if (res.result.length > 0) {
        this.cityList = res.result
        // console.log('this.cityList: ', this.cityList);
      }
    })
  }

  selectCityRecord(id: string) {
    this.cityList.map((item: any) => {
      this.isReadOnly = true;
      if (item.cityCode == id) {
        this.cityForm.setValue({
          cityCode: item.cityCode,
          countryCode: item.countryCode,
          bankCode: item.bankCode,
          wording: item.wording
        })

        this.isDelete = true;
      }
    })
  }

  cityCodeValid = true;
  countryCodeValid = true;
  bankCodeValid = true;
  cityWordingValid = true;
  submitCity(){
    if (!this.isDelete) {
      if(this.cityForm.value.cityCode == ""){
        this.cityCodeValid = false;
      }else{
        this.cityCodeValid = true;
      }
      if(this.cityForm.value.countryCode == ""){
        this.countryCodeValid = false;
      }else{
        this.countryCodeValid = true;
      }
      if(this.cityForm.value.bankCode == ""){
        this.bankCodeValid = false;
      }else{
        this.bankCodeValid = true;
      }
      if(this.cityForm.value.wording == ""){
        this.cityWordingValid = false;
      }else{
        this.cityWordingValid = true;
      }
      if(this.cityCodeValid && this.countryCodeValid && this.bankCodeValid && this.cityWordingValid){
      // console.log('this.processingForm.value: ', this.cityForm.value);
      this.globalService.addCity(this.cityForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.cityForm.reset();
          this.getAllCities()

          this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "City added successfuly"
        }
        if(this.fr){
          this.alertBody.message = "Ville  ajouté avec succès"
        }
        if(this.esp){
          this.alertBody.message = "City añadido exitosamente"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      
      }
      else if (response.respCode=="409") {
        this.alertBody.status = "Country already exists"
        if(this.en){
          this.alertBody.message = "City already exists"
        }
        if(this.fr){
          this.alertBody.message = "Ville deja exists"
        }
        if(this.esp){
          this.alertBody.message = "City ya existe"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }else{
     
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
      })}
    }
    else {
      // console.log('this.processingForm.value: ', this.cityForm.value);
      this.globalService.updateCity(this.cityForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.cityForm.reset();
          this.getAllCities()
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "City update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du ville réussie"
          }
          if(this.esp){
            this.alertBody.message = "City actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
         
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

  deleteCityRecord() {
    // console.log('this.id: ', this.cityForm.value.cityCode);
    this.globalService.deleteCity(this.cityForm.value.cityCode).subscribe(response => {
      // console.log('response city delete: ', response);
      this.getAllCities()
      this.clearAll();

      if (response.respCode == "000") {
        this.getCountryList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "City  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du ville réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del City exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted City "
        if (this.en) {
          this.alertBody.message = "Something went wrong during city deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du ville";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del city";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }

  //Mti
  getMTIList() {
    let id = {
      "bankCode": this.user.bankCode,
      "mtiProtocol": "I"
    }
    // console.log('id: ', id);
    this.globalService.fetchMTI(id).subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.mtiList = response.result
        // console.log('this.mtiList: ', this.mtiList);
      }
    })
    this.clearAll()
  }

  codeMtiValid = true;
  mtiDescValid = true;
  mtiDirValid = true;
  // CcyAlphaValid = true;
  // CcyExponnentValid = true;
  submitMti() {
    if (!this.isDelete) {
      if(this.mtiForm.value.id.mtiCode == ""){
        this.codeMtiValid = false;
      }else{
        this.codeMtiValid = true;
      }
      if(this.mtiForm.value.mtiDesc == ""){
        this.mtiDescValid = false;
      }else{
        this.mtiDescValid = true;
      }
      if(this.mtiForm.value.mtiDirection == ""){
        this.mtiDirValid = false;
      }else{
        this.mtiDirValid = true;
      }
      if(this.codeMtiValid && this.mtiDescValid && this.mtiDirValid){
      // console.log('this.mtiForm.value: ', this.mtiForm.value);
      this.globalService.addMti(this.mtiForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          if(this.userDeleteActive == false){
          this.mtiForm.reset();
          this.getMTIList()
          }
        }
      })}
    }
    else {
      // console.log('this.mtiForm.value: ', this.mtiForm.value);
      this.globalService.updateMti(this.mtiForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          if(this.userDeleteActive == false){
          this.mtiForm.reset();
          this.getMTIList()
          }
        }
      })
    }
  }
  selectMtiRecord(id: string) {
    this.isReadOnly = true;
    this.mtiList.map((item: any) => {
      if (item.id.mtiCode == id) {
        this.idMti.setValue({
          mtiCode: item.id.mtiCode,
          mtiProtocol: item.id.mtiProtocol,
          bankCode: item.id.bankCode
        })
        this.mtiForm.setValue({
          id: this.idMti.value,
          mtiDesc: item.mtiDesc,
          mtiDirection: item.mtiDirection
        })
        this.isDelete = true
      }
    })
    // console.log("this.mtiForm " ,this.mtiForm.value);
  }
  deleteMtiRecord() {
    // console.log('this.id: ', this.idMti.value);
    this.globalService.deleteMti(this.idMti.value).subscribe(response => {
      // console.log('response: ', response);
      this.getMTIList()
    })
  }


  //country
  getCountryList() {

    this.globalService.fetchCountry().subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.countryList = response.result
        // console.log('this.countryList: ', this.countryList);
      }
    })
    this.clearAll()
  }

  idCountValid = true;
  countDescValid = true;
  countAlpha2Valid = true;
  countAlpha3Valid = true;
  submitCountry() {
    if (!this.isDelete) {

      if(this.countryForm.value.id == ""){
        this.idCountValid = false;
      }else{
        this.idCountValid = true;
      }
      if(this.countryForm.value.countryDesc == ""){
        this.countDescValid = false;
      }else{
        this.countDescValid = true;
      }
      if(this.countryForm.value.countryAlpha2 == ""){
        this.countAlpha2Valid = false;
      }else{
        this.countAlpha2Valid = true;
      }
      if(this.countryForm.value.countryAlpha3 == ""){
        this.countAlpha3Valid = false;
      }else{
        this.countAlpha3Valid = true;
      }
      if(this.idCountValid && this.countDescValid && this.countAlpha2Valid && this.countAlpha3Valid){
      // console.log('this.countryForm.value: ', this.countryForm.value);
      this.globalService.addCountry(this.countryForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          
          this.countryForm.reset();
          this.getCountryList()

          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Country added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Country  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Country añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        
        }
        else if (response.respCode=="409") {
        
          this.alertBody.status = "Country already exists"
          if(this.en){
            this.alertBody.message = "Country already exists"
          }
          if(this.fr){
            this.alertBody.message = "Country deja exists"
          }
          if(this.esp){
            this.alertBody.message = "Country ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
         
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
      })}
    }
    else {
      // console.log('this.countryForm.value: ', this.countryForm.value);
      this.globalService.updateCountry(this.countryForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.countryForm.reset();
          this.getCountryList()

          this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "Country update successfuly"
        }
        if(this.fr){
          this.alertBody.message = "Mise à jour du Country réussie"
        }
        if(this.esp){
          this.alertBody.message = "Country actualizado exitosamente"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);


      }else{
      
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
  selectCountryRecord(id: string) {
    this.isReadOnly = true;
    this.countryList.map((item: any) => {
      if (item.id == id) {
        this.countryForm.setValue({
          id: item.id,
          countryDesc: item.countryDesc,
          countryAlpha2: item.countryAlpha2,
          countryAlpha3: item.countryAlpha3
        })
        this.isDelete = true
      }
    })
    // console.log('this.countryForm: ', this.countryForm.value);
  }
  deleteCountryRecord() {
    // console.log('this.id: ', this.countryForm.value.id);
    this.globalService.deleteCountry(this.countryForm.value.id).subscribe(response => {
      // console.log('response: ', response);
      this.getCountryList()
      this.clearAll()

      if (response.respCode == "000") {
        this.getCountryList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Country  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Country réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Country exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Country "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Country deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Country";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Country";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }


  //currency
  getCurrencyList() {

    this.globalService.fetchCurrency().subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.currencyList = response.result
        // console.log('this.currencyList: ', this.currencyList);
      }
    })
    this.clearAll()
  }

  idCcyValid = true;
  CcyDescValid = true;
  CcyAlphaValid = true;
  CcyExponnentValid = true;
  submitCurrency() {


    if (!this.isDelete) {
      if(this.currencyForm.value.id == ""){
        this.idCcyValid = false;
      }else{
        this.idCcyValid = true;
      }
      if(this.currencyForm.value.ccyDesc == ""){
        this.CcyDescValid = false;
      }else{
        this.CcyDescValid = true;
      }
      if(this.currencyForm.value.ccyAlpha == ""){
        this.CcyAlphaValid = false;
      }else{
        this.CcyAlphaValid = true;
      }
      if(this.currencyForm.value.ccyExponent == ""){
        this.CcyExponnentValid = false;
      }else{
        this.CcyExponnentValid = true;
      }
      if(this.idCcyValid && this.CcyDescValid && this.CcyAlphaValid && this.CcyExponnentValid){
      // console.log('this.currencyForm.value: ', this.currencyForm.value);
      this.globalService.addCurrency(this.currencyForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.currencyForm.reset();
          this.getCurrencyList()

          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Currency added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Currency  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Currency añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);

        }else if (response.respCode=="409") {
            this.closeModal();
            this.alertBody.status = "Currency already exists"
            if(this.en){
              this.alertBody.message = "Currency already exists"
            }
            if(this.fr){
              this.alertBody.message = "Currency deja exists"
            }
            if(this.esp){
              this.alertBody.message = "Currency ya existe"
            }
            this.alertBody.open = true
            setTimeout(() => {
              this.alertBody.open = false;
            }, 3000);
          }else{
            this.closeModal();
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
      })}
    }
    else {
      // console.log('this.currencyForm.value: ', this.currencyForm.value);
      this.globalService.updateCurrency(this.currencyForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.currencyForm.reset();
          this.getCurrencyList()

          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Currency update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du Currency réussie"
          }
          if(this.esp){
            this.alertBody.message = "Currency actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
          this.closeModal()
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
  selectCurrencyRecord(id: string) {
    this.isReadOnly = true;
    this.currencyList.map((item: any) => {
      if (item.id == id) {
        this.currencyForm.setValue({
          id: item.id,
          ccyDesc: item.ccyDesc,
          ccyAlpha: item.ccyAlpha,
          ccyExponent: item.ccyExponent
        })
        this.isDelete = true
      }
    })
    // console.log("currency form " , this.currencyForm.value);
  }
  deleteCurrencyRecord() {
    // console.log('this.id: ', this.currencyForm.value.id);
    this.globalService.deleteCurrency(this.currencyForm.value.id).subscribe(response => {
      // console.log('response: ', response);
      this.getCurrencyList()
      this.clearAll()
      
      if (response.respCode == "000") {
        this.getCurrencyList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Currency  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Currency réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Currency exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Currency "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Currency deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Currency";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Currency";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }

    })
  }



  //mcc
  getMccList() {
    const body={
      bankCode:"00100",
      mccProtocol: "PO"
    }

    this.globalService.fetchMcc(body).subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.mccList = response.result
        // console.log('this.mccList: ', this.mccList);
      }
    })
    this.clearAll()
  }

  codeMccValid = true;
  mccDescValid = true;
  // CcyAlphaValid = true;
  // CcyExponnentValid = true;
  submitMcc() {
    if (!this.isDelete) {
      if(this.mccForm.value.id.mccCode == ""){
        this.codeMccValid = false;
      }else{
        this.codeMccValid = true;
      }
      if(this.mccForm.value.mccDesc == ""){
        this.mccDescValid = false;
      }else{
        this.mccDescValid = true;
      }
      if(this.codeMccValid && this.mccDescValid){
      // console.log('this.mccForm.value: ', this.mccForm.value);
      this.globalService.addMcc(this.mccForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          if(this.userDeleteActive == false){
          this.mccForm.reset();
          this.getMccList()
          }
        }
      })}
    }
    else {
      // console.log('this.mccForm.value: ', this.mccForm.value);
      this.globalService.updateMcc(this.mccForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          if(this.userDeleteActive == false){
          this.mccForm.reset();
          this.getMccList()
          }
        }
      })
    }
  }
  selectMccRecord(id: string) {
    // console.log('MCC SELECTED ID ', id)
    // console.log('this.mccList: ', this.mccList);
    this.isReadOnly = true;
    this.mccList.map((item: any) => {
      if (item.id == id) {
        this.idMcc.setValue({
          mccCode: item.id.mccCode,
          mccProtocol: item.id.mccProtocol,
          bankCode: item.id.bankCode
        })
        this.mccForm.setValue({
          id: this.idMcc.value,
          mccDesc: item.mccDesc,
        })
        this.isDelete = true
      }
    })
    // console.log('this.mccForm: ', this.mccForm.value);
  }
  deleteMccRecord() {
    // console.log('this.id: ', this.idMcc.value);
    this.globalService.deleteMcc(this.idMcc.value).subscribe(response => {
      // console.log('response: ', response);
      this.getMccList();
      this.clearAll()
    })
  }

  //exchange
  getExchangeList() {
    const body={
      protocol:this.protocol,
      bankCode:this.bankCode
    }

    this.globalService.fetchExchange(body).subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.exchangeList = response.result
        // console.log('this.exchangeList: ', this.exchangeList);
      }
    })
    this.clearAll()
  }
  submitExchange() {
    if (!this.isDelete) {
      // console.log('this.exchangeForm.value: ', this.exchangeForm.value);
      this.globalService.addExchange(this.exchangeForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.exchangeForm.reset();
          this.getExchangeList()
        }
      })
    }
    else {
      // console.log('this.exchangeForm.value: ', this.exchangeForm.value);
      this.globalService.updateExchange(this.exchangeForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.exchangeForm.reset();
          this.getExchangeList()
        }
      })
    }
  }
  selectExchangeRecord(id: string) {
    // console.log('id: ', id);

    this.exchangeList.map((item: any) => {
      // console.log('item: ', item);
      if (item.id == id) {
        this.idExchange.setValue({
          baseCcy: item.id.baseCcy,
          rateStartDate: item.id.rateStartDate,
          currCode: item.id.currCode,
          rateProtocol: item.id.rateProtocol,
          bankCode: item.id.bankCode
        })
        this.exchangeForm.setValue({
          id: this.idExchange.value,
          buyRate: item.buyRate,
          midRate: item.midRate,
          sellRate: item.sellRate
        })
        this.isDelete = true
      }
    })
  }
  deleteExchangeRecord() {
    // console.log('this.id: ', this.idExchange.value);
    this.globalService.deleteExchange(this.idExchange.value).subscribe(response => {
      // console.log('response: ', response);
      this.getExchangeList()
    })
  }



  //response
  getResponseList() {
    let id = {
      "bankCode": this.user.bankCode,
      "respProtocol": "I"
    }
    // console.log('id: ', id);
    this.globalService.fetchResponse(id).subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.responseList = response.result
        // console.log('this.responseList: ', this.responseList);
      }
    })
    this.clearAll()
  }
  submitResponse() {
    if (!this.isDelete) {
      // console.log('this.responseForm.value: ', this.responseForm.value);
      this.globalService.addResponse(this.responseForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.responseForm.reset();
          this.getResponseList()
        }
      })
    }
    else {
      // console.log('this.responseForm.value: ', this.responseForm.value);
      this.globalService.updateResponse(this.responseForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.responseForm.reset();
          this.getResponseList()
        }
      })
    }
  }
  selectResponseRecord(id: string) {
    this.responseList.map((item: any) => {
      if (item.id == id) {
        this.idResponse.setValue({
          respCode: item.id.respCode,
          respProtocol: item.id.respProtocol,
          bankCode: item.id.bankCode
        })
        this.responseForm.setValue({
          id: this.idResponse.value,
          respDesc: item.respDesc,
          respAction: item.respAction
        })
        this.isDelete = true
      }
    })
  }
  deleteResponseRecord() {
    // console.log('this.id: ', this.idResponse.value);
    this.globalService.deleteResponse(this.idResponse.value).subscribe(response => {
      // console.log('response: ', response);
      this.getResponseList()
    })
  }



  //tag
  getTagList() {

    this.globalService.fetchTag().subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.tagList = response.result
        // console.log('this.tagList: ', this.tagList);
      }
    })
    this.clearAll()
  }
  submitTag() {
    if (!this.isDelete) {
      // console.log('this.tagForm.value: ', this.tagForm.value);
      this.globalService.addTag(this.tagForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.tagForm.reset();
          this.getTagList()
        }
      })
    }
    else {
      // console.log('this.tagForm.value: ', this.tagForm.value);
      this.globalService.updateTag(this.tagForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.tagForm.reset();
          this.getTagList()
        }
      })
    }
  }
  selectTagRecord(id: any) {
    this.tagList.map((item: any) => {

      if (item.subfldDefinitionId == id) {

        this.idTag.setValue({
          subfldId: item.subfldDefinitionId.subfldId,
          subfldProtocole: item.subfldDefinitionId.subfldProtocole,
          bankCode: item.subfldDefinitionId.bankCode
        })
        this.tagForm.setValue({
          subfldDefinitionId: this.idTag.value,
          subfldDescr: item.subfldDescr,
          subfldType: item.subfldType,
          subfldLengthMax: Number.parseInt(item.subfldLengthMax)
        })
        this.isDelete = true
      }
    })
  }
  deleteTagRecord() {
    // console.log('this.id: ', this.idTag.value);
    this.globalService.deleteTag(this.idTag.value).subscribe(response => {
      // console.log('response: ', response);
      this.getTagList()
    })
  }



  //Account
  getAccountList() {
    const body={
      bankCode:this.user.bankCode,
      accProtocol:"PO"
    }

    this.globalService.fetchAccount(body).subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.accountList = response.result
        // console.log('this.accountList: ', this.accountList);
      }
    })
    this.clearAll()
  }

  codeAccValid = true;
  AccDescValid = true;
  submitAccount() {
    if (!this.isDelete) {
      if(this.accountForm.value.id.accCode == ""){
        this.codeAccValid = false;
      }else{
        this.codeAccValid = true;
      }
      if(this.accountForm.value.accDesc == ""){
        this.AccDescValid = false;
      }else{
        this.AccDescValid = true;
      }
      if(this.codeAccValid && this.AccDescValid){
      // console.log('this.accountForm.value: ', this.accountForm.value);
      this.globalService.addAccount(this.accountForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.accountForm.reset();
          this.getAccountList()
        }
      })}
    }
    else {
      // console.log('this.accountForm.value: ', this.accountForm.value);
      this.globalService.updateAccount(this.accountForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          this.accountForm.reset();
          this.getAccountList()
        }
      })
    }
  }
  selectAccountRecord(id: string) {
    this.isReadOnly = true;
    this.accountList.map((item: any) => {
      if (item.id == id) {
        this.idAccount.setValue({
          accCode: item.id.accCode,
          accProtocol: item.id.accProtocol,
          bankCode: item.id.bankCode
        })
        this.accountForm.setValue({
          id: this.idAccount.value,
          accDesc: item.accDesc
        })
        this.isDelete = true
      }
    })
  }
  deleteAccountRecord() {
    // console.log('this.id: ', this.idAccount.value);
    this.globalService.deleteAccount(this.idAccount.value).subscribe(response => {
      // console.log('response: ', response);
      this.getAccountList()
      this.clearAll()
    })
  }



  //function
  getFunctionList() {

    this.globalService.fetchFunction().subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.functionList = response.result
        // console.log('this.functionList: ', this.functionList);
      }
    })
    this.clearAll()
  }
  fcCodeValid = true;
  fcDescValid = true;
  submitFunction() {
    if (!this.isDelete) {

      if(this.functionForm.value.id.fctCode == ""){
        this.fcCodeValid = false;
      }else{
        this.fcCodeValid = true;
      }
      if(this.functionForm.value.fctDesc == ""){
        this.fcDescValid = false;
      }else{
        this.fcDescValid = true;
      }
      if(this.fcCodeValid && this.fcDescValid){
      // console.log('this.functionForm.value: ', this.functionForm.value);
      this.globalService.addFunction(this.functionForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          if(this.userDeleteActive == false){
          this.functionForm.reset();
          this.getFunctionList()
          }
        }
      })}
    }
    else {
      // console.log('this.functionForm.value: ', this.functionForm.value);
      this.globalService.updateFunction(this.functionForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          if(this.userDeleteActive == false){
          this.functionForm.reset();
          this.getFunctionList()
          }
        }
      })
    }
  }
  selectfunctionRecord(id: string) {
    this.isReadOnly = true;
    this.functionList.map((item: any) => {
      if (item.id == id) {
        this.idFunction.setValue({
          fctCode: item.id.fctCode,
          fctProtocol: item.id.fctProtocol,
          ftcProtocol: item.id.ftcProtocol,
          bankCode: item.id.bankCode
        })
        this.functionForm.setValue({
          id: this.idFunction.value,
          fctDesc: item.fctDesc
        })
        this.isDelete = true
      }
    })
    // console.log("Fubction Form ",this.functionForm.value);
  }
  deleteFunctionRecord() {
    // console.log('this.id: ', this.idFunction.value);
    this.globalService.deleteFunction(this.idFunction.value).subscribe(response => {
      // console.log('response: ', response);
      this.getFunctionList()
      this.clearAll()
    })
  }




  //processing
  getProcessingList() {

    this.globalService.fetchProcessing().subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.processingList = response.result
        // console.log('this.processingList: ', this.processingList);
      }
    })
    this.clearAll()
  }
  procCodeValid = true;
  procDescValid = true;
  submitProcessing() {
    if (!this.isDelete) {
      if(this.processingForm.value.id.procCode == ""){
        this.procCodeValid = false;
      }else{
        this.procCodeValid = true;
      }
      if(this.processingForm.value.procDesc == ""){
        this.procDescValid = false;
      }else{
        this.procDescValid = true;
      }
      if(this.procCodeValid && this.procDescValid){
      // console.log('this.processingForm.value: ', this.processingForm.value);
      this.globalService.addProcessing(this.processingForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          if(this.userDeleteActive == false){
          this.processingForm.reset();
          this.getProcessingList()
          }
        }
      })}
    }
    else {
      // console.log('this.processingForm.value: ', this.processingForm.value);
      this.globalService.updateProcessing(this.processingForm.value).subscribe(response => {
        // console.log('response: ', response);
        if (response.respCode == '000') {
          if(this.userDeleteActive == false){
          this.processingForm.reset();
          this.getProcessingList()
          }
        }
      })
    }
  }
  selectprocessingRecord(id: string) {
    this.isReadOnly = true;
    this.processingList.map((item: any) => {
      if (item.id == id) {
        this.idProcessing.setValue({
          procCode: item.id.procCode,
          procProtocol: item.id.procProtocol,
          bankCode: item.id.bankCode
        })
        this.processingForm.setValue({
          id: this.idProcessing.value,
          procDesc: item.procDesc
        })
        this.isDelete = true
      }
    })
  }
  deleteprocessingRecord() {
    // console.log('this.id: ', this.idProcessing.value);
    this.globalService.deleteProcessing(this.idProcessing.value).subscribe(response => {
      // console.log('response: ', response);
      this.getProcessingList()
      this.clearAll()
    })
  }

// HABILITAION Group //
getGroupList() {
  this.globalService.getAllGroups().subscribe(response => {
    // console.log('response  Groups: ', response);
    if (response.result.length > 0) {
      this.GroupList = response.result
      // console.log('this.GroupList: ', this.GroupList);
    }
  })
  this.clearAll()
}

groupCodeValid = true;
groupNameValid = true;
menuCodeValid = true;
userDeleteActive = false;

userDeleteActiveSelect = false;
userSaveActive=true;
submitGroup() {
  // console.log('this.processingForm.value: ', this.GroupForm.value.groupId.groupCode);
    var gCode = this.GroupForm.value.groupId.groupCode;
    var gName = this.GroupForm.value.groupName;
    var mName = this.GroupForm.value.groupId.menuCode;
  if (!this.isDelete) {

    if(this.GroupForm.value.groupId.groupCode == ""){
      this.groupCodeValid = false;
    }else{
      this.groupCodeValid = true;
    }
    if(this.GroupForm.value.groupName == ""){
      this.groupNameValid = false;
    }else{
      this.groupNameValid = true;
    }
    if(this.GroupForm.value.groupId.menuCode == ""){
      this.menuCodeValid = false;
    }else{
      this.menuCodeValid = true;
    }
    if(this.groupCodeValid && this.groupNameValid && this.menuCodeValid){
    this.globalService.addNewGroup(gCode,gName,mName).subscribe(response => {
      // console.log('response: ', response);
      if (response.respCode == '000') {
        if(this.userDeleteActive == false){
        this.GroupForm.reset();
        this.getGroupList();
        }
      }
    })}
  }
  else {
    // console.log('this.GroupForm.value: ', this.GroupForm.value);
    this.globalService.updateGroup(gCode,gName,mName).subscribe(response => {
      // console.log('response update: ', response);
      if (response.respCode == '000') {
        if(this.userDeleteActive == false){
        this.GroupForm.reset();
        this.getGroupList()
        }
      }
    })
  }
}
selectedGroupRecord(id: string) {
  this.isReadOnly = true;
  this.GroupList.map((item: any) => {
    if (item.groupId == id) {
      this.idGroup.setValue({
        groupCode: item.groupId.groupCode,
        menuCode: item.groupId.menuCode
      })
      this.GroupForm.setValue({
        groupId: this.idGroup.value,
        groupName: item.groupName
      })
      this.isDelete = true
    }
  })
}

deleteGroupRecord() {
  // console.log('this.id: ', this.idGroup.value.groupCode);
  this.globalService.deleteGroup(this.idGroup.value.groupCode).subscribe(response => {
    // console.log('response delete: ', response);
    this.getGroupList()
    this.clearAll()
  })
}


getAllMenus(){
  this.globalService.getAllMenus().subscribe(res => {
    // console.log("result Menues ", res);
    this.MenusList.push(res['result']);
    // console.log('this.MenusList: ', this.MenusList);
  })
}

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

// live Search (HAMZA 06.02.2024)
searchText: string = '';

  get filteredCurrencyList() {
    return this.currencyList.filter((item:any) =>
      item.id.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.ccyDesc.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  get filteredCountryList() {
    return this.countryList.filter((item:any) =>
      item.id.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.countryDesc.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  get filteredCityList() {
    return this.cityList.filter((item:any) =>
      item.cityCode.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.wording.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  get filteredMCCList() {
    return this.mccList.filter((item:any) =>
      item.id.mccCode.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.mccDesc.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  get filteredMtiList() {
    return this.mtiList.filter((item:any) =>
      item.id.mtiCode.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.mtiDesc.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  get filteredAccountList() {
    return this.accountList.filter((item:any) =>
      item.id.accCode.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.accDesc.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }


  get filteredFunctionList() {
    return this.functionList.filter((item:any) =>
      item.id.fctCode.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.fctDesc.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  get filteredProcessingList() {
    return this.processingList.filter((item:any) =>
      item.id.procCode.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.procDesc.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  get filteredBankList() {
    return this.bankList.filter((item:any) =>
      item.id.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.wording.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }
  get filteredGroupList() {
    return this.GroupList.filter((item:any) =>
      item.groupId.groupCode.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.groupName.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  // -------- Global Delete ---------------- //

  Delete(){
    // console.log('this.DeleteScop: ', this.DeleteScop);
    if(this.DeleteScop == "currency"){
      this.deleteCurrencyRecord()
    }

    if(this.DeleteScop == "country"){
      this.deleteCountryRecord()
    }

    if(this.DeleteScop == "city"){
      this.deleteCityRecord()
    }

    if(this.DeleteScop == "mcc"){
      this.deleteMccRecord()
    }

    if(this.DeleteScop == "mti"){
      this.deleteMtiRecord()
    }

    if(this.DeleteScop == "account"){
      this.deleteAccountRecord()
    }

    if(this.DeleteScop == "function"){
      this.deleteFunctionRecord()
    }

    if(this.DeleteScop == "processing"){
      this.deleteprocessingRecord()
    }

    if(this.DeleteScop == "group"){
      // console.log("GROUP");
      this.deleteGroupRecord()
    }
  }

  openModal(){
    this.userDeleteActive = true;
    this.modal?.nativeElement.showModal()
  }
  closeModal(){
    this.modal?.nativeElement.close();
  }
/*
  get filteredCountryList() {
    return this.countryList.filter((item:any) =>
      item.id.toLowerCase().includes(this.searchText.toLowerCase()) ||
      item.countryDesc.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }*/

//  (((((((((((((((((((END HAMZA)))))))))))))))))))




openDeletePopUp(){

  if(this.language=="en"){
    this.message = "Are you sure you want to delete  :"
  }else if (this.language==="fr") {
    this.message ="Êtes-vous sûr de vouloir supprimer :"
  }else{
    this.message ="¿Estás seguro de que quieres eliminar ?"
  }
  this.userDeleteActiveSelect = true;
  this.userSaveActive=false;
  this.openModal();

}
openSavePopUp(){

  if(this.language=="en"){
    this.message = "Are you sure you want to save "
  }else if (this.language==="fr") {
    this.message ="Êtes-vous sûr de vouloir enregistrer:"
  }else{
    this.message ="¿Estás seguro de que quieres guardar?"
  }
  
  this.userDeleteActiveSelect = false;
  this.userSaveActive=true;
  this.openModal();
}


deleteGlobal(){
  if (this.tab_1==true) {
   this.deleteCurrencyRecord()
  }
  if (this.tab_2==true) {
    this.deleteCountryRecord();
  }

  if (this.tab_3==true) {
    this.deleteCityRecord();
  }
  if (this.tab_12==true) {
    this.deleteprocessingRecord()
  }
  this.closeModal();
}



SaveGlobal(){
  if (this.tab_1==true) {
    this.submitCurrency()
    
  }
  if (this.tab_2==true) {
    this.submitCountry();
  }
  if (this.tab_3==true) {
    this.submitCity();
  }

  if (this.tab_12==true) {
    this.deleteGroupRecord()
  }
  this.closeModal();
}



}
