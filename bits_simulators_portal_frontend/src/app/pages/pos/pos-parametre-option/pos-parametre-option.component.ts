import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';



@Component({
  selector: 'app-pos-parametre-option',
  templateUrl: './pos-parametre-option.component.html',
  styleUrls: ['./pos-parametre-option.component.scss']
})
export class PosParametreOptionComponent {

    itemsPerPageSelct:number=5
    p: number = 0;
    mtiForm: FormGroup = new FormGroup('');
    idMti: FormGroup = new FormGroup('');
    mtiList: any = []
  
    currencyForm: FormGroup = new FormGroup('');
    currencyList: any = []
    showBtnDelete:boolean=false
    countryForm: FormGroup = new FormGroup('');
    countryList: any = []
  
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
    isDelete: boolean = false
  
  
    alertBody: any = {
      message: '',
      status: '000',
      open: false
    }
  
    showError: boolean = false;
    message = "";
    userDeleteActive:boolean=false
    userSaveActive:boolean=false
  
  
    searchCurrnecy: string = ""
    searchCountry: string = ""
    searchExchange  : string = ""
    searchResponse  : string = ""
    searchMCC  : string = ""
    searchMTI  : string = ""
    searchTag  : string = ""
    searchAccount  : string = ""
    searchFunction  : string = ""
    searchProcessing  : string = ""
  
    
    constructor(
      private globalService: GlobalService,
      private formBuilder: FormBuilder,
      private authService: AuthService
    ) { }
    tab_1: boolean = false
    tab_2: boolean = false
    tab_3: boolean = false
    tab_4: boolean = false
    tab_5: boolean = false
    tab_6: boolean = true
    tab_7: boolean = false
    tab_8: boolean = false
    tab_9: boolean = false
    tab_10: boolean = false
    user: any
  
    user1:any
  
    fr = false;
    en = false;
    esp = false;
    language:  string = ""
    protocol: string = "";
    bankCode: any = "";
    ngOnInit() {
      this.authService.user.subscribe(
        (x: any) => {
          this.user = x;
          // console.log(this.user)
        }
      );
  
      this.user1 = localStorage.getItem('user');
      this.user1 = JSON.parse(this.user1);
      this.language = this.user1.languageCode;
      if (this.language === "en") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters / Options"));
        this.en=true
      } else if (this.language === "fr") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres/Options"));
          this.fr=true
      } else if (this.language === "es") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros / Options"));
          this.esp=true
      } else {
          Promise.resolve().then(() => this.globalService.titleSubject.next(""));
      }
      this.authService.user.subscribe(val => {
        this.bankCode = val.bankCode
  
        // console.log("test bank",this.bankCode);
        
      })
     
  
      this.clearAll()
  
      this.getMTIList();
  
      // Promise.resolve().then(() => this.globalService.titleSubject.next("General Options"))
  
  
      this.getCurrencyList()
    }
  
    open_tab(index: number) {
      this.itemsPerPageSelct=5
      this.clearAll()
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
      switch (index) {
        case 0:
          this.tab_1 = true
          this.getCurrencyList()
          this.p = 0
          break;
        case 1:
          this.tab_2 = true
          this.getCountryList()
          this.p = 0
          break;
        case 2:
          this.tab_3 = true
          this.getExchangeList()
          this.getCurrencyList()
          this.getCountryList()
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
          this.p = 0
          break;
        case 5:
          this.tab_6 = true
          this.getMTIList()
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
          this.p = 0
          break;
        case 8:
          this.tab_9 = true
          this.getFunctionList()
          this.p = 0
          break;
        case 9:
          this.tab_10 = true
          this.getProcessingList()
          this.p = 0
          break;
  
        default:
          break;
      }
    }
  
    clearAll(): void {
      this.isDelete = false;
      this.mtiForm.reset();
      this.currencyForm.reset();
      this.countryForm.reset();
      this.exchangeForm.reset();
      this.responseForm.reset();
      this.mccForm.reset();
      this.tagForm.reset();
      this.accountForm.reset();
      this.functionForm.reset();
      this.processingForm.reset();
      this.idMti = this.formBuilder.group({
        mtiCode: [''],
        mtiProtocol: ["PO"],
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
  
      this.idExchange = this.formBuilder.group({
        baseCcy: [''],
        rateStartDate: [''],
        currCode: [''],
        rateProtocol: ["PO"],
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
        respProtocol: ["PO"],
        bankCode: [this.user.bankCode]
      })
      this.responseForm = this.formBuilder.group({
        id: this.idResponse,
        respDesc: [''],
        respAction: ['']
      })
  
  
      this.idMcc = this.formBuilder.group({
        mccCode: [''],
        mccProtocol: ["PO"],
        bankCode: [this.user.bankCode]
      })
      this.mccForm = this.formBuilder.group({
        id: this.idMcc,
        mccDesc: ['']
      })
  
  
      this.idTag = this.formBuilder.group({
        subfldId: [''],
        subfldProtocole: ["PO"],
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
        accProtocol: ["PO"],
        bankCode: [this.user.bankCode]
      })
      this.accountForm = this.formBuilder.group({
        id: this.idAccount,
        accDesc: ['']
      })
  
  
      this.idFunction = this.formBuilder.group({
        fctCode: [''],
        fctProtocol: ["PO"],
        bankCode: [this.user.bankCode],
        ftcProtocol: ["PO"],
      })
      this.functionForm = this.formBuilder.group({
        id: this.idFunction,
        fctDesc: ['']
      })
  
  
      this.idProcessing = this.formBuilder.group({
        procCode: [''],
        procProtocol: ["PO"],
        bankCode: [this.user.bankCode]
      })
      this.processingForm = this.formBuilder.group({
        id: this.idProcessing,
        procDesc: ['']
      })
  
    }
  
    //Mti
    getMTIList() {
      let id = {
        "bankCode": this.user.bankCode,
        "mtiProtocol": "PO"
      }
      // console.log('id: ', id);
      this.globalService.fetchMTIPOS(id).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.mtiList = response.result
          // console.log('this.mtiList: ', this.mtiList);
          
        }
      })
      this.clearAll()
    }
    submitMti() {
   
    if (this.isDelete) {
      this.globalService.updateMtiPOS(this.mtiForm.value).subscribe(response => {
       this.closemodal()
        // console.log('response://// ', response);
        if (response.respCode == '000') {
          this.mtiForm.reset();
            this.getMTIList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Mti update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du Mti réussie"
          }
          if(this.esp){
            this.alertBody.message = "Mti actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
          this.closemodal()
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
      this.closemodal()
      this.globalService.addMtiPOS(this.mtiForm.value).subscribe(response => {
        if (response.respCode == '000') {
          this.mtiForm.reset();
          this.getMTIList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Mti added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mti  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Mti añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        
        }
        else if (response.respCode=="409") {
          this.closemodal();
          this.alertBody.status = "Mti already exists"
          if(this.en){
            this.alertBody.message = "Mti already exists"
          }
          if(this.fr){
            this.alertBody.message = "Mti deja exists"
          }
          if(this.esp){
            this.alertBody.message = "Mti ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.closemodal();
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
  
  
    selectMtiRecord(id: string) {
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
    }
    deleteMtiRecord() {
  
    this.globalService.deleteMtiPOS(this.idMti.value).subscribe(response => {
      this.closemodal();
      if (response.respCode == "000") {
        this.getMTIList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Mti  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Mti réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Mti exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Mti "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Mti deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Mti";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Mti";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
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
    submitCountry() {
    if (this.isDelete) {
      this.globalService.updateCountry(this.countryForm.value).subscribe(response => {
       this.closemodal()
        // console.log('response://// ', response);
        if (response.respCode == '000') {
          this.getCountryList()
          this.countryForm.reset();
  
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
          this.closemodal()
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
      this.closemodal()
      this.globalService.addCountry(this.countryForm.value).subscribe(response => {
        if (response.respCode == '000') {
          this.getCountryList()
          this.countryForm.reset();
  
          this.showBtnDelete=false
  
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
          this.closemodal();
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
          this.closemodal();
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
    }
  
  
  
    deleteCountryRecord() {
    this.globalService.deleteCountry(this.countryForm.value.id).subscribe(response => {
      this.closemodal();
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
    submitCurrency() {
    
      if (this.isDelete) {
        this.globalService.updateCurrency(this.currencyForm.value).subscribe(response => {
         this.closemodal()
          // console.log('response://// ', response);
          if (response.respCode == '000') {
            this.getCurrencyList()
            this.currencyForm.reset();
  
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
            this.closemodal()
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
        this.closemodal()
        this.globalService.addCurrency(this.currencyForm.value).subscribe(response => {
          if (response.respCode == '000') {
            this.getCurrencyList()
            this.currencyForm.reset();
    
            this.showBtnDelete=false
    
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
          
          }
          else if (response.respCode=="409") {
            this.closemodal();
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
            this.closemodal();
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
      this.showBtnDelete=true
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
    }
    deleteCurrencyRecord() {
    
    this.globalService.deleteCurrency(this.currencyForm.value.id).subscribe(response => {
      this.closemodal();
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
        bankCode: this.bankCode,
        mccProtocol: 'PO'
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
  
  
    submitMcc() {
   
    if (this.isDelete) {
      this.globalService.updateMcc(this.mccForm.value).subscribe(response => {
       this.closemodal()
        // console.log('response://// ', response);
        if (response.respCode == '000') {
          this.mccForm.reset();
           this.getMccList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Mcc update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du Mcc réussie"
          }
          if(this.esp){
            this.alertBody.message = "Mcc actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
          this.closemodal()
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
      this.closemodal()
      this.globalService.addMcc(this.mccForm.value).subscribe(response => {
        if (response.respCode == '000') {
          this.exchangeForm.reset();
          this.mccForm.reset();
          this.getMccList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Mcc added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mcc  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Mcc añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        
        }
        else if (response.respCode=="409") {
          this.closemodal();
          this.alertBody.status = "Mcc already exists"
          if(this.en){
            this.alertBody.message = "Mcc already exists"
          }
          if(this.fr){
            this.alertBody.message = "Mcc deja exists"
          }
          if(this.esp){
            this.alertBody.message = "Mcc ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.closemodal();
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
  
  
  
    selectMccRecord(id: any) {
      // this.isDelete = true
      
      this.mccList.map((item: any) => {
        if (item.id.mccCode == id.mccCode) {
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
  
      // console.log("this.mccForm",this.mccForm.value);
      
    }
    deleteMccRecord() {
    //   // console.log('this.id: ', this.idMcc.value);
    //   this.globalService.deleteMcc(this.idMcc.value).subscribe(response => {
    //     // console.log('response: ', response);
    //     this.getMccList()
    //   })
    // }
  
    this.globalService.deleteMcc(this.idMcc.value).subscribe(response => {
      this.closemodal();
      if (response.respCode == "000") {
        this.getMccList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Mcc  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Mcc réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Mcc exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Mcc "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Mcc deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Mcc";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Mcc";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }
  
    //exchange
    getExchangeList() {
      const body={
        protocol:"PO",
        bankCode:this.bankCode
      }
  
      this.globalService.fetchExchangePos(body).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.exchangeList = response.result
          // console.log('this.exchangeList: ', this.exchangeList);
        }
      })
      this.clearAll()
    }
  
  
    submitExchange() {
  
    if (this.isDelete) {
      this.globalService.updateExchangePos(this.exchangeForm.value).subscribe(response => {
       this.closemodal()
        // console.log('response://// ', response);
        if (response.respCode == '000') {
          this.exchangeForm.reset();
          this.getExchangeList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "eExchange update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du eExchange réussie"
          }
          if(this.esp){
            this.alertBody.message = "eExchange actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
          this.closemodal()
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
      this.closemodal()
      this.globalService.addExchangePos(this.exchangeForm.value).subscribe(response => {
        if (response.respCode == '000') {
          this.exchangeForm.reset();
          this.getExchangeList()
          this.showBtnDelete=false
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "eExchange added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "eExchange  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "eExchange añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        
        }
        else if (response.respCode=="409") {
          this.closemodal();
          this.alertBody.status = "eExchange already exists"
          if(this.en){
            this.alertBody.message = "eExchange already exists"
          }
          if(this.fr){
            this.alertBody.message = "eExchange deja exists"
          }
          if(this.esp){
            this.alertBody.message = "eExchange ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.closemodal();
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
  
    selectExchangeRecord(id: string) {
      this.showBtnDelete=true
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
    this.globalService.deleteExchangePos(this.idExchange.value).subscribe(response => {
      this.closemodal();
      if (response.respCode == "000") {
        this.getExchangeList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Exchange  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Exchange réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Exchange exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Exchange "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Exchange deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Exchange";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Exchange";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }
  
  
  
    //response
    getResponseList() {
      let id = {
        "bankCode": this.user.bankCode,
        "respProtocol": "PO"
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
    //   if (!this.isDelete) {
    //     // console.log('this.responseForm.value: ', this.responseForm.value);
    //     this.globalService.addResponse(this.responseForm.value).subscribe(response => {
    //       // console.log('response: ', response);
    //       if (response.respCode == '000') {
    //         this.responseForm.reset();
    //         this.getResponseList()
    //       }
    //     })
    //   }
    //   else {
    //     // console.log('this.responseForm.value: ', this.responseForm.value);
    //     this.globalService.updateResponse(this.responseForm.value).subscribe(response => {
    //       // console.log('response: ', response);
    //       if (response.respCode == '000') {
    //         this.responseForm.reset();
    //         this.getResponseList()
    //       }
    //     })
    //   }
    // }
  
    if (this.isDelete) {
      this.globalService.updateResponse(this.responseForm.value).subscribe(response => {
       this.closemodal()
        // console.log('response://// ', response);
        if (response.respCode == '000') {
          this.responseForm.reset();
          this.getResponseList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Response update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du Response réussie"
          }
          if(this.esp){
            this.alertBody.message = "Response actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
          this.closemodal()
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
      this.closemodal()
      this.globalService.addResponse(this.responseForm.value).subscribe(response => {
        if (response.respCode == '000') {
          this.responseForm.reset();
          this.getResponseList()
          this.showBtnDelete=false
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Response added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Response  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Response añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        
        }
        else if (response.respCode=="409") {
          this.closemodal();
          this.alertBody.status = "Response already exists"
          if(this.en){
            this.alertBody.message = "Response already exists"
          }
          if(this.fr){
            this.alertBody.message = "Response deja exists"
          }
          if(this.esp){
            this.alertBody.message = "Response ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.closemodal();
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
  
    selectResponseRecord(id: string) {
      this.showBtnDelete=true
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
   
  
    this.globalService.deleteResponse(this.idResponse.value).subscribe(response => {
      this.closemodal();
      if (response.respCode == "000") {
        this.getResponseList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Response  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Response réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Response exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Response "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Response deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Response";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Response";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }
  
  
    //tag
    getTagList() {
      const body={
        subfldId: "4F",
        subfldProtocole:"PO",
        bankCode: this.bankCode
      }
  
      this.globalService.fetchTagByProtocolPOS(body).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.tagList = response.result
          // console.log('this.tagList: ', this.tagList);
        }
      })
      this.clearAll()
    }
  
  
    submitTag() {
   
  
    if (this.isDelete) {
      this.globalService.updateTagPOS(this.tagForm.value).subscribe(response => {
       this.closemodal()
        // console.log('response://// ', response);
        if (response.respCode == '000') {
          this.tagForm.reset();
          this.getTagList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Tag update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du Tag réussie"
          }
          if(this.esp){
            this.alertBody.message = "Tag actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
          this.closemodal()
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
      this.closemodal()
      this.globalService.addTagPOS(this.tagForm.value).subscribe(response => {
        if (response.respCode == '000') {
          this.tagForm.reset();
          this.getTagList()
  
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Tag added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Tag  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Tag añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        
        }
        else if (response.respCode=="409") {
          this.closemodal();
          this.alertBody.status = "Tag already exists"
          if(this.en){
            this.alertBody.message = "Tag already exists"
          }
          if(this.fr){
            this.alertBody.message = "Tag deja exists"
          }
          if(this.esp){
            this.alertBody.message = "Tag ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.closemodal();
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
  
  
  
    selectTagRecord(id: any) {
      this.showBtnDelete=true
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
    this.globalService.deleteTagPOS(this.idTag.value).subscribe(response => {
      this.closemodal();
      if (response.respCode == "000") {
     this.getTagList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Tag  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Tag réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Tag exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Tag "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Tag deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Tag";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Tag";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }
  
  
  
    //Account
    getAccountList() {
      const body={
        bankCode:this.bankCode,
        accProtocol:"PO"
      }
  
      this.globalService.fetchAccountPOS(body).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.accountList = response.result
          // console.log('this.accountList: ', this.accountList);
        }
      })
      this.clearAll()
    }
    submitAccount() {
    //   if (!this.isDelete) {
    //     // console.log('this.accountForm.value: ', this.accountForm.value);
    //     this.globalService.addAccount(this.accountForm.value).subscribe(response => {
    //       // console.log('response: ', response);
    //       if (response.respCode == '000') {
    //         this.accountForm.reset();
    //         this.getAccountList()
    //       }
    //     })
    //   }
    //   else {
    //     // console.log('this.accountForm.value: ', this.accountForm.value);
    //     this.globalService.updateAccount(this.accountForm.value).subscribe(response => {
    //       // console.log('response: ', response);
    //       if (response.respCode == '000') {
    //         this.accountForm.reset();
    //         this.getAccountList()
    //       }
    //     })
    //   }
    // }
  
    if (this.isDelete) {
      this.globalService.updateAccount(this.accountForm.value).subscribe(response => {
       this.closemodal()
        // console.log('response://// ', response);
        if (response.respCode == '000') {
          this.accountForm.reset();
          this.getAccountList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Account update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du Account réussie"
          }
          if(this.esp){
            this.alertBody.message = "Account actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
          this.closemodal()
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
      this.closemodal()
      this.globalService.addAccount(this.accountForm.value).subscribe(response => {
        if (response.respCode == '000') {
          this.accountForm.reset();
          this.getAccountList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Account added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Account  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Account añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        
        }
        else if (response.respCode=="409") {
          this.closemodal();
          this.alertBody.status = "Account already exists"
          if(this.en){
            this.alertBody.message = "Account already exists"
          }
          if(this.fr){
            this.alertBody.message = "Account deja exists"
          }
          if(this.esp){
            this.alertBody.message = "Account ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.closemodal();
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
  
  
    selectAccountRecord(id: string) {
      this.showBtnDelete=true
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
    this.globalService.deleteAccount(this.idAccount.value).subscribe(response => {
      this.closemodal();
      if (response.respCode == "000") {
        this.getAccountList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Account  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Account réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Account exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Account "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Account deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Account";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Account";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }
  
  
    //function
    getFunctionList() {
      
      const body={
       
          fctCode: "100",
          fctProtocol: "PO",
          bankCode: this.bankCode
    
      }
      this.globalService.fetchFunctionByProtocolPOS(body).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.functionList = response.result
          // console.log('this.functionList: ', this.functionList);
        }
      })
      this.clearAll()
    }
  
  
    submitFunction() {
    if (this.isDelete) {
      this.globalService.updateFunctionPOS(this.functionForm.value).subscribe(response => {
       this.closemodal()
        // console.log('response://// ', response);
        if (response.respCode == '000') {
            this.functionForm.reset();
            this.getFunctionList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Function update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du Function réussie"
          }
          if(this.esp){
            this.alertBody.message = "Function actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
          this.closemodal()
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
      this.closemodal()
      this.globalService.addFunctionPOS(this.functionForm.value).subscribe(response => {
        if (response.respCode == '000') {
            this.functionForm.reset();
            this.getFunctionList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Function added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Function  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Function añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        
        }
        else if (response.respCode=="409") {
          this.closemodal();
          this.alertBody.status = "Function already exists"
          if(this.en){
            this.alertBody.message = "Function already exists"
          }
          if(this.fr){
            this.alertBody.message = "Function deja exists"
          }
          if(this.esp){
            this.alertBody.message = "Function ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.closemodal();
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
  
  
    selectfunctionRecord(id: string) {
      this.showBtnDelete=true
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
    }
    deleteFunctionRecord() {
    this.globalService.deleteFunctionPOS(this.idFunction.value).subscribe(response => {
      this.closemodal();
      if (response.respCode == "000") {
        this.getFunctionList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Function  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Function réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Function exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Function "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Function deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Function";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Function";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }
  
  
  
    //processing
    getProcessingList() {
      const body={
        procCode: "00",
        procProtocol: "PO",
        bankCode: this.bankCode
      }
  
  
      // console.log("test body: " + body);
      
  
      this.globalService.fetchProcessingByProtocolPOS(body).subscribe(response => {
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.processingList = response.result
          // console.log('this.processingList: ', this.processingList);
        }
      })
      this.clearAll()
    }
    submitProcessing() {
    
  
    if (this.isDelete) {
      this.globalService.updateProcessingPOS(this.processingForm.value).subscribe(response => {
       this.closemodal()
        // console.log('response://// ', response);
        if (response.respCode == '000') {
            this.processingForm.reset();
            this.getProcessingList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Processing update successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Mise à jour du Processing réussie"
          }
          if(this.esp){
            this.alertBody.message = "Processing actualizado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
  
        }else{
          this.closemodal()
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
      this.closemodal()
      this.globalService.addProcessingPOS(this.processingForm.value).subscribe(response => {
        if (response.respCode == '000') {
          this.processingForm.reset();
            this.getProcessingList()
  
          this.alertBody.status = "000"
          if(this.en){
            this.alertBody.message = "Processing added successfuly"
          }
          if(this.fr){
            this.alertBody.message = "Processing  ajouté avec succès"
          }
          if(this.esp){
            this.alertBody.message = "Processing añadido exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        
        }
        else if (response.respCode=="409") {
          this.closemodal();
          this.alertBody.status = "Processing already exists"
          if(this.en){
            this.alertBody.message = "Processing already exists"
          }
          if(this.fr){
            this.alertBody.message = "Processing deja exists"
          }
          if(this.esp){
            this.alertBody.message = "Processing ya existe"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.closemodal();
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
  
  
    selectprocessingRecord(id: string) {
      this.showBtnDelete=true
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
   
    this.globalService.deleteProcessingPOS(this.idProcessing.value).subscribe(response => {
      this.closemodal();
      if (response.respCode == "000") {
        this.getProcessingList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Processing  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Processing réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Processing exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Processing "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Processing deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Processing";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Processing";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }
  
  
  
  
    ////************************************* */
  
  
  
    @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
    openModal() {
      this.modal?.nativeElement.showModal()
    }
    closemodal() {
      this.modal?.nativeElement.close()
    }
  
  
    openDeletePopUp(){
      if(this.language=="en"){
        this.message = "Are you sure you want to delete  :"
      }else if (this.language==="fr") {
        this.message ="Êtes-vous sûr de vouloir supprimer :"
      }else{
        this.message ="¿Estás seguro de que quieres eliminar ?"
      }
     
      this.userDeleteActive = true;
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
      
      this.userDeleteActive = false;
      this.userSaveActive=true;
      this.openModal();
    }
  
    init(){
  
      this.clearAll();
    }
  
  
    deleteGlobal(){
      if (this.tab_1==true) {
        this.deleteCurrencyRecord()
      }
      if (this.tab_2==true) {
        this.deleteCountryRecord();
      }
  
      if (this.tab_3==true) {
        this.deleteExchangeRecord();
      }
      
      if (this.tab_4==true) {
        this.deleteResponseRecord()
      }
      if (this.tab_5==true) {
        this.deleteMccRecord()
      }
      if (this.tab_6==true) {
        this.deleteMtiRecord()
      }
      if (this.tab_7==true) {
        this.deleteTagRecord()
      }
      if (this.tab_8==true) {
        this.deleteAccountRecord()
      }
      if (this.tab_9==true) {
        this.deleteFunctionRecord()
      }
      if (this.tab_10==true) {
        this.deleteprocessingRecord()
      }
    }
  
  
    SaveGlobal(){
      if (this.tab_1==true) {
        this.submitCurrency()
        
      }
      if (this.tab_2==true) {
        this.submitCountry();
      }
      if (this.tab_3==true) {
        this.submitExchange();
      }
  
      if (this.tab_4==true) {
        this.submitResponse()
      }
      if (this.tab_5==true) {
        this.submitMcc()
      }
  
      if (this.tab_6==true) {
        this.submitMti()
      }
      if (this.tab_7==true) {
        this.submitTag()
      }
  
      if (this.tab_8==true) {
        this.submitAccount()
      }
      if (this.tab_9==true) {
        this.submitFunction()
      }
      if (this.tab_10==true) {
        this.submitProcessing()
      }
    }
  
  
  }
  