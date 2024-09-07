import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import { ERROR_MESSAGES } from 'src/assets/error/error';

@Component({
  selector: 'app-pos-acquirer-profile',
  templateUrl: './pos-acquirer-profile.component.html',
  styleUrls: ['./pos-acquirer-profile.component.scss']
})
export class PosAcquirerProfileComponent {

    cardPGeneralForm: FormGroup = new FormGroup({
      cardNumber: new FormControl('', [Validators.required, Validators.minLength(13), Validators.maxLength(19)]),
      cardDesc: new FormControl('', [Validators.required]),
      bin: new FormControl('', [Validators.required, Validators.maxLength(6)]),
      issuerCode: new FormControl('', Validators.required),
      issuerName: new FormControl('', Validators.required),
      cardExpiry: new FormControl('', Validators.required),
      accountCurr: new FormControl('', Validators.required),
      accAmount: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]*$/)]),
      available: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]*$/)]),
      accountType: new FormControl('', Validators.required),
      cardType: new FormControl('MG'), // default value as 'MG'
      pin: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]*$/)]),
      cvnVal: new FormControl('', Validators.required),
      cvn2Val: new FormControl('', [Validators.required])
    });
    magneticForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      mobile: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]+$/)]),
      cfType: new FormControl('', Validators.required),
      cfNo: new FormControl('', Validators.required),
      track1: new FormControl('', Validators.required),
      track2: new FormControl('', Validators.required),
      track3: new FormControl('', Validators.required)
    });
    iccForm = new FormGroup({
      icAid: new FormControl('', Validators.required),
      icProfile: new FormControl('', Validators.required),
      icMkAc: new FormControl('', Validators.required),
      icMkSmc: new FormControl('', Validators.required),
      icMkSmi: new FormControl('', Validators.required),
      cfType: new FormControl('', Validators.required),
      cfNo: new FormControl('', Validators.required)
  
    });
    tab_1: boolean = true
    tab_2: boolean = false
    tab_3: boolean = false
    tab_4: boolean = true
    tab_5: boolean = false
    tab_ICC: boolean = false
    searchCard: string = ""
    searchMerchant: string = ""
    searchTerminal: string = ""
    cards: any = []
    currencyList: any = []
    accounts: any = []
    tags: any = []
    protocol="PO";
    bankCode: string = ""
    language: 'en' | 'fr' | string = "en"
    icProfiles: any = []
    showBtnDelete:boolean=false
    showBtnDeleteProfile:boolean=false
    alertBody: any = {
      message: '',
      status: '000',
      open: false
    }
    icProfileObject: any = {
      id: {
        icPrf: "",
        icTag: "",
        iccProtocol: "",
        bankCode: ""
      },
      icPrfDesc: "",
      icTagDesc: "",
      icTagValue: ""
    }
    icTag: string = ""
    tagVal: string = ""
    isDelete: boolean = false
    isUpdate: boolean = false
  
  
    merchantForm: FormGroup = new FormGroup('');
    merchantList: any = []
    countryList: any = []
    mccList: any = []
    cityList: any = []
    terminalForm: FormGroup = new FormGroup('');
    terminalList: any = []
  
  
    merCode: string = ""
    merProtocol: string = ""
    mcc: string = ""
    merName: string = ""
    countryCode: string = ""
    city: string = ""
    
  
  
    /////
    termCode:string = ""
    termLocation: string = ""
    termCapabilities: string = ""
    termType: string = ""
    tranCount: string = ""
    termVerResult: string = ""
    applicationVerNum: string = ""
  
    
    /////
  
    checkicMkSmc='';
    checkicMkAc='';
    checkicMkSmi='';
  
  
    showError: boolean = false;
    message = "";
    userDeleteActive:boolean=false
    userSaveActive:boolean=false
  
    userDeleteActiveProfile:boolean=false
    userSaveActiveProfile:boolean=false
  
  
  
    userDeleteActiveCard:boolean=false
    userSaveActiveCard:boolean=false
  
  
    user1:any
  
    fr = false;
    en = false;
    esp = false;
  
    itemsPerPageSelct:number=5
    itemsPerPageSelctProfile:number=5
    p: number = 0;
    pCard: number = 0;
    pProfile=0
    searchText: string = '';
  
    clearAll(): void {
      this.isDelete = false;
      this.merchantForm.reset();
      // Resetting the form
      this.terminalForm.reset();
  
      this.clear();
      this.terminalForm = this.formBuilder.group({
        id: this.formBuilder.group({
          termCode: [''],
          terProtocol: [this.protocol],
          bankCode: [this.bankCode]
        }),
        termLocation: [''],
        termCapabilities: [''],
        termType: [''],
        tranCount: [''],
        termVerResult: [''],
        applicationVerNum: ['']
      });
  
  
      this.merchantForm = this.formBuilder.group({
        id: this.formBuilder.group({
          merCode: [''],
          merProtocol: [''],
          bankCode: ['']
        }),
        merName: [''],
        mcc: [''],
        city: [''],
        countryCode: ['']
      })
    }
    constructor(
      private globalService: GlobalService,
      private formBuilder: FormBuilder,
      private auth: AuthService
    ) {
  
    }
    ngOnInit() {
      // this.globalService.protocol.subscribe(val => {
      //   if (val) {
      //     // console.log(val)
      //     this.protocol = val
      //   }
      // })
      // if (!this.protocol) {
      //   this.protocol = localStorage.getItem('protocol') as string
      // }
      this.user1 = localStorage.getItem('user');
      this.user1 = JSON.parse(this.user1);
      this.language = this.user1.languageCode;
      if (this.language === "en") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters / Acquirer Profile"));
        this.en=true
      } else if (this.language === "fr") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres / Acquirer Profile"));
          this.fr=true
      } else if (this.language === "es") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros / Acquirer Profile"));
          this.esp=true
      } else {
          Promise.resolve().then(() => this.globalService.titleSubject.next(""));
      }
  
  
      // this.language = localStorage.getItem('lang') || 'en'
      // console.log('this.protocol: ', this.protocol);
      this.auth.user.subscribe(val => {
        this.bankCode = val.bankCode
      })
      // Promise.resolve().then(() => this.globalService.titleSubject.next("Acquirer icProfile"))
      this.getCards()
      this.getAccounts()
      this.getTags()
      this.getCity()
      this.getCurrencyList()
  
    }
  
  
    open_tab(index: number) {
      this.tab_1 = false
      this.tab_2 = false
      this.tab_3 = false
      this.searchCard = ""
      this.searchMerchant = ""
      this.searchTerminal = ""
      this.clearAll()
      if (index == 0) {
        this.tab_1 = true
        this.getCards()
      } else if (index == 1) {
        this.tab_2 = true
        this.getCountryList()
        this.getMerchantList()
        this.getMccList()
      } else {
        this.tab_3 = true
        this.getTerminalList()
      }
  
    }
    open_tab2(index: number) {
      this.tab_4 = false
      this.tab_5 = false
      this.tab_ICC = false
      if (index == 0) {
        this.tab_4 = true
      } else if (index == 1) {
        this.tab_5 = true
      } else {
        this.tab_ICC = true
      }
    }
    getCards() {
      this.clear()
      this.protocol = localStorage.getItem('protocol') as string
      let body = {
        "cardProtocol": "PO",//this.protocol,
        "bankCode": this.bankCode
      }
      // console.log('body: ', body);
      this.globalService.getCardsPOS(body).subscribe(data => {
        // console.log('data: ', data);
        this.cards = data.result
      })
    }
    getErrorMessage(code: string): string {
      const message = ERROR_MESSAGES[code];
      return message ? message[this.language] : "Unknown error";
    }
  
  
  
   /////// encrypt KEY ****************
  
   encryptKey(event: any, data: string) {
    let paddedData = '';
    // // console.log('event name', event.target.value);
    
    if (event.target.value.length == 16 || event.target.value.length == 32 || event.target.value.length == 48) {
  
      if (event.target.value.length === 16) {
        paddedData = '0'.repeat(16); 
      } else if (event.target.value.length === 32) {
        paddedData = '0'.repeat(32); 
      } else if (event.target.value.length === 48) {
        paddedData = '0'.repeat(48); 
      }
  
      // // console.log( 'paddedData',paddedData);
      
      
      const body={
        key: event.target.value,
        data: paddedData
        // data: "00000000000000000000000000000000"
      }
      // // console.log('body: ', body);
  
      this.globalService.encryptKey(body).subscribe((response) => {
  
          if (data == "icMkAc") {
            this.checkicMkAc=response.result
          }
          else if (data == "icMkSmc") {
            this.checkicMkSmc=response.result
          }
          else {
            this.checkicMkSmi=response.result
          }
  
      });
  
    }
    else{
      if (data == "icMkAc") {
        this.checkicMkAc=""
      }
      else if (data == "icMkSmc") {
        this.checkicMkSmc=""
      }
      else {
        this.checkicMkSmi=""
      }
    }
  }
  
  
  
  ////  fin encrypt KEY ********************************
  
  
  
  
  
  
  
  
  
  
    getCurrencyList() {
  
      this.globalService.fetchCurrency().subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.currencyList = response.result
        }
      })
      this.clearAll()
    }
  
  
  
    selectCardRecord(id: any) {
      this.cards.map((item: any) => {
        if (item.id == id) {
          // console.log('item: ', item);
  
          this.isDelete = true;
  
  
          this.cards.map((item: any) => {
            if (item.id == id) {
              this.isDelete = true;
  
              this.cardPGeneralForm.setValue({
                cardNumber: item.id.cardNo,
                cardDesc: item.cardDesc,
                bin: item.bin,
                issuerCode: item.issuerCode,
                issuerName: item.issuerName,
                cardExpiry: item.cardExpiry,
                accountCurr: item.accountCurr,
                accAmount: item.accAmount,
                available: item.available,
                accountType: item.accountType,
                cardType: item.cardType,
                pin: item.pin,
                cvnVal: item.cvnVal,
                cvn2Val: item.cvn2Val
              });
  
              this.magneticForm.setValue({
                firstName: item.firstName,
                lastName: item.lastName,
                mobile: item.mobile,
                cfType: item.cfType,
                cfNo: item.cfNo,
                track1: item.track1,
                track2: item.track2,
                track3: item.track3
              });
              if (this.cardPGeneralForm.get('cardType')?.value != 'MG') {
  
                this.iccForm.setValue({
                  icAid: item.icAid,
                  icProfile: item.icProfile,
                  icMkAc: item.icMkAc,
                  icMkSmc: item.icMkSmc,
                  icMkSmi: item.icMkSmi,
                  cfType: item.cfType,
                  cfNo: item.cfNo
                });
                let paddedDataicMkAc = '';
                if ( item.icMkAc.length=== 16) {
                  paddedDataicMkAc = '0'.repeat(16); 
                } else if (item.icMkAc.length === 32) {
                  paddedDataicMkAc = '0'.repeat(32); 
                } else if (item.icMkAc.length === 48) {
                  paddedDataicMkAc = '0'.repeat(48); 
                }
                const bodyicMkAc={
                  key: item.icMkAc,
                  data: paddedDataicMkAc
                  // data: "00000000000000000000000000000000"
                }
                // // console.log('body: ', body);
            
                this.globalService.encryptKey(bodyicMkAc).subscribe((response) => {
                      this.checkicMkAc=response.result
                });
  
  
                let paddedDataicMkSmc = '';
                if ( item.icMkSmc.length=== 16) {
                  paddedDataicMkSmc = '0'.repeat(16); 
                } else if (item.icMkSmc.length === 32) {
                  paddedDataicMkSmc = '0'.repeat(32); 
                } else if (item.icMkSmc.length === 48) {
                  paddedDataicMkSmc = '0'.repeat(48); 
                }
  
                const bodyicMkSmc={
                  key: item.icMkSmc,
                  data: paddedDataicMkSmc
                  // data: "00000000000000000000000000000000"
                }
                // // console.log('body: ', body);
            
                this.globalService.encryptKey(bodyicMkSmc).subscribe((response) => {
                      this.checkicMkSmc=response.result
                });
  
                let paddedDataicMkSmi = '';
                if ( item.icMkSmi.length=== 16) {
                  paddedDataicMkSmi = '0'.repeat(16); 
                } else if (item.icMkSmi.length === 32) {
                  paddedDataicMkSmi = '0'.repeat(32); 
                } else if (item.icMkSmi.length === 48) {
                  paddedDataicMkSmi = '0'.repeat(48); 
                }
  
                const bodyicMkSmi={
                  key: item.icMkSmi,
                  data: paddedDataicMkSmi
                  // data: "00000000000000000000000000000000"
                }
                // // console.log('body: ', body);
            
                this.globalService.encryptKey(bodyicMkSmi).subscribe((response) => {
                      this.checkicMkSmi=response.result
                });
  
  
  
                let iccBody = {
                  "icPrf": this.cardPGeneralForm.get('cardNumber')?.value,
                  "iccProtocol": "PO",
                  "bankCode": this.bankCode
                }
                this.globalService.fetchIccPOS(iccBody).subscribe(response => {
                  this.icProfiles = response.result
                })
              }
            }
          });
  
        }
      });
    }
  
    deleteCard() {
      const id = {
        cardNo: this.cardPGeneralForm.get('cardNumber')?.value,
        bankCode: this.bankCode,
        cardProtocol: "PO" //this.protocol
      }
   
  
    this.globalService.deleteCardPOS(id).subscribe(response => {
      this.closeModalCard();
      if (response.respCode == "000") {
          this.getCards()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Card  delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Card réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Card exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Card "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Card deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Card";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Card";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }
  
  
    saveCard() {
      const general = this.cardPGeneralForm.value
      const magnetic = this.magneticForm.value
      const icc = this.iccForm.value
      const tags = this.icProfiles
      let body = {}
      let id = {
        cardNo: this.cardPGeneralForm.get('cardNumber')?.value,
        bankCode: this.bankCode,
        cardProtocol:"PO" //this.protocol
      }
  
  
    body = { id, ...general, ...magnetic, ...icc, tags }
   
    const cardExpiryString: string | null = this.cardPGeneralForm.get('cardExpiry')?.value;
    let exp: string | undefined; 
  
    if (cardExpiryString) {
      const [year, month] = cardExpiryString.split('-'); // Sépare l'année et le mois
      
   
       exp = year.slice(-2) + month;
       
      //  // console.log("exp::>",exp);
       
  } else {
      console.error("La valeur de cardExpiry n'est pas une date valide");
  }
  
    const bodyValidateCardExp={
      pan: this.cardPGeneralForm.get('cardNumber')?.value,
      exp:exp
    }
    this.globalService.validateCardExp(bodyValidateCardExp).subscribe(response => {
      if (response.respCode=="000") {
  
        if (this.isDelete) {
          this.globalService.updateCardPOS(body).subscribe(response => {
            this.closeModalCard()
            // console.log('response://// ', response);
            if (response.respCode == '000') {
              this.getCards()
      
              this.alertBody.status = "000"
              if(this.en){
                this.alertBody.message = "Card update successfuly"
              }
              if(this.fr){
                this.alertBody.message = "Mise à jour du Card réussie"
              }
              if(this.esp){
                this.alertBody.message = "Card actualizado exitosamente"
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
        else {
          this.globalService.addCardPOS(body).subscribe(response => {
            // // console.log('response: ', response);
            if (response.respCode == '000') {
              this.getCards()
            
      
              this.showBtnDelete=false
      
              this.alertBody.status = "000"
              if(this.en){
                this.alertBody.message = "Card added successfuly"
              }
              if(this.fr){
                this.alertBody.message = "Card  ajouté avec succès"
              }
              if(this.esp){
                this.alertBody.message = "Card añadido exitosamente"
              }
              this.alertBody.open = true
              setTimeout(() => {
                this.alertBody.open = false;
              }, 3000);
            
            }
            else if (response.respCode=="409") {
              this.closeModalCard();
              this.alertBody.status = "Card already exists"
              if(this.en){
                this.alertBody.message = "Card already exists"
              }
              if(this.fr){
                this.alertBody.message = "Card deja exists"
              }
              if(this.esp){
                this.alertBody.message = "Card ya existe"
              }
              this.alertBody.open = true
              setTimeout(() => {
                this.alertBody.open = false;
              }, 3000);
            }else{
              this.closeModalCard()
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
  
  
      }else{
        this.closeModalCard()
        this.alertBody.status = "Card Expired"
        if (this.en) {
          this.alertBody.message = "Card Expired";
        }
        if (this.fr) {
          this.alertBody.message = "Carte Expirée";
        }
        if (this.esp) {
          this.alertBody.message = "Tarjeta Expirada";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
    })
  
  
    
  }
  
  
  
  getCity() {
    this.globalService.fetchCities().subscribe(data => {
      // console.log('data: ', data);
      this.cityList = data.result
    })
  }
  
    getAccounts() {
      const body={
        bankCode:this.bankCode,
        accProtocol:this.protocol
      }
      this.globalService.fetchAccount(body).subscribe(data => {
        // console.log('data: ', data);
        this.accounts = data.result
      })
    }
    private checkErrorsForForm(form: FormGroup, formName: string) {
      Object.keys(form.controls).forEach(key => {
        const controlErrors = form.get(key)?.errors;
        if (controlErrors) {
          // console.log(`Validation Errors in ${formName} - Field: ${key}:`, controlErrors);
        }
      });
    }
    getTags() {
      let body = {}
      this.globalService.fetchTagsPOS(body).subscribe(data => {
        // console.log('data: ', data);
        this.tags = data.result
      })
    }
    getMccList() {
      const body={
        bankCode: this.bankCode,
        iccProtocol: this.protocol
      }
  
      this.globalService.fetchMcc(body).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.mccList = response.result
          // console.log('this.mccList: ', this.mccList);
        }
      })
  
    }
    clear() {
      this.magneticForm.reset();
      this.cardPGeneralForm.reset({
        accountType: '',
        cardType: 'MG'
      });
      this.iccForm.reset();
      this.icProfiles = []
      this.isDelete = false
      this.open_tab2(0)
    }
    addicProfiles() {
      if (this.isUpdate) {
        const index = this.icProfiles.findIndex((profile: any) => profile.id.icTag === this.icTag);
  
        if (index !== -1) {
          // Update the record in the array
          this.icProfiles[index].icTagValue = this.tagVal;
  
          this.icTag = '';
          this.tagVal = '';
        }
      } else {
        this.icProfileObject.id.icPrf = this.cardPGeneralForm.get('cardNumber')?.value
        this.icProfileObject.id.iccProtocol = this.protocol
        this.icProfileObject.id.bankCode = this.bankCode
        this.icProfileObject.id.icTag = this.icTag
        this.icProfileObject.icTagValue = this.tagVal
        this.icProfileObject.icPrfDesc = this.cardPGeneralForm.get('cardDesc')?.value
        this.tags.map((item: any) => {
          if (item.subfldDefinitionId.subfldId == this.icProfileObject.id.icTag) {
            this.icProfileObject.icTagDesc = item.subfldDescr
          }
        })
        // console.log('this.icProfileObject: ', this.icProfileObject);
        this.icProfiles.push(this.icProfileObject)
        this.icProfileObject = {
          id: {
            icPrf: "",
            icTag: "",
            iccProtocol: "",
            bankCode: ""
          },
          icPrfDesc: "",
          icTagDesc: "",
          icTagValue: ""
        }
      }
  
      this.closeModal()
    }
    @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
    openModal() {
      this.modal?.nativeElement.showModal()
    }
    closeModal() {
      this.modal?.nativeElement.close()
      this.isUpdate = false
      this.icTag = '';
      this.tagVal = '';
    }
  
    openModalUpdate(id: string) {
      this.isUpdate = true
      this.icProfiles.map((item: any) => {
        if (item.id.icTag == id) {
          this.icTag = item.id.icTag;
          this.tagVal = item.icTagValue;
        }
      });
      this.modal?.nativeElement.showModal()
    }
  
  
    getFilteredTags(): any[] {
      if (!this.isUpdate)
        return this.tags.filter((tag: any) => !this.icProfiles.some((profile: any) => profile.id.icTag === tag.subfldDefinitionId.subfldId));
      else
        return this.tags
    }
    getCountryList() {
  
      this.globalService.fetchCountry().subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.countryList = response.result
          // console.log('this.countryList: ', this.countryList);
        }
      })
  
    }
  
  
  
    getMerchantList() {
      let id = {
        "bankCode": this.bankCode,
        "merProtocol": "PO" //this.protocol
      }
      // console.log('id: ', id);
      this.globalService.fetchMerchantPOS(id).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.merchantList = response.result
          // console.log('this.merchantList: ', this.merchantList);
        }
      })
      this.clearAll()
    }
  
    submitMerchant() {
  
      const body={
        id: {
          merCode:this.merCode ,
          merProtocol: "PO",//this.protocol ,
          bankCode:this.bankCode 
      },
      merName: this.merName,
      mcc: this.mcc,
      city: this.city,
      countryCode: this.countryCode
  
      }
  
  
      if (this.showBtnDelete) {
        this.globalService.updateMerchantPOS(body).subscribe(response => {
          this.closeModalGlobal()
          // // console.log('response://// ', response);
          if (response.respCode == '000') {
            this.initMerchant()
            this.getMerchantList()
  
            this.alertBody.status = "000"
            if(this.en){
              this.alertBody.message = "Merchant update successfuly"
            }
            if(this.fr){
              this.alertBody.message = "Mise à jour du merchand réussie"
            }
            if(this.esp){
              this.alertBody.message = "Merchant actualizado exitosamente"
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
      else {
        this.globalService.addMerchantPOS(body).subscribe(response => {
          // // console.log('response: ', response);
          this.closeModalGlobal()
          if (response.respCode == '000') {
            this.initMerchant()
            this.getMerchantList()
  
            this.showBtnDelete=false
  
            this.alertBody.status = "000"
            if(this.en){
              this.alertBody.message = "Merchant added successfuly"
            }
            if(this.fr){
              this.alertBody.message = "Merchand  ajouté avec succès"
            }
            if(this.esp){
              this.alertBody.message = "Merchant añadido exitosamente"
            }
            this.alertBody.open = true
            setTimeout(() => {
              this.alertBody.open = false;
            }, 3000);
          
          }
          else if (response.respCode=="409") {
            this.alertBody.status = "Merchant already exists"
            if(this.en){
              this.alertBody.message = "Merchant already exists"
            }
            if(this.fr){
              this.alertBody.message = "Merchand deja exists"
            }
            if(this.esp){
              this.alertBody.message = "Merchant ya existe"
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
  
    selectMerchantRecord(data: any) {
      // console.log("data >:::",data);
      this.showBtnDelete=true
      
      this.merCode=data.id.merCode;
      this.merName=data.merName;
      this.mcc=data.mcc;
      this.city=data.city
      this.countryCode=data.countryCode
    }
  
    initMerchant(){
      this.showBtnDelete=false
        this.merCode="";
        this.merName="";
        this.mcc="";
        this.city="";
        this.countryCode="";
      }
   
  
    deleteMerchantRecord() {
      const body={
        
          merCode:this.merCode ,
          merProtocol:"PO",//this.protocol ,
          bankCode:this.bankCode 
      
      }
      // console.log(body);
      this.globalService.deleteMerchantPOS(body).subscribe(response => {
        this.closeModalGlobal();
        if (response.respCode == "000") {
          this.initMerchant();
          this.getMerchantList();
  
  
          this.alertBody.status = "000"
          if (this.en) {
            this.alertBody.message = "Merchant delete successfuly";
          }
  
          if (this.fr) {
            this.alertBody.message = "Suppression du Merchand réussie";
          }
  
          if (this.esp) {
            this.alertBody.message = "Eliminación del Merchant exitosa";
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
        else {
  
          this.alertBody.status = "Something went wrong deleted Merchant "
          if (this.en) {
            this.alertBody.message = "Something went wrong during Merchant deletion";
          }
  
          if (this.fr) {
            this.alertBody.message = "Une erreur est survenue lors de la suppression du Merchant";
          }
  
          if (this.esp) {
            this.alertBody.message = "Algo salió mal durante la eliminación del Merchant";
          }
  
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
  
        }
      })
    }
  
  
    deleteTagRecord(id: any) {
  
      this.globalService.deleteIccPOS(id).subscribe(response => {
        // console.log('response: ', response);
        this.icProfiles = this.icProfiles.filter((profile: any) => profile.id !== id);
      })
    }
  
    getTerminalList() {
      let id = {
        "bankCode": this.bankCode,
        "terProtocol": "PO"
      }
      // console.log('id: ', id);
      this.globalService.fetchTerminalPOS(id).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.terminalList = response.result
          // console.log('this.terminalList: ', this.terminalList);
        }
      })
      this.clearAll()
    }
  
  ////////////////////
  initTerProfil(){
    this.showBtnDeleteProfile=false
  
    this.termCode = ""
    this.termLocation = ""
    this.termCapabilities = ""
    this.termType = ""
    this.tranCount = ""
    this.termVerResult = ""
    this.applicationVerNum = ""
  }
  
  
    submitTerminal() {
  
  
      const body={
        id: {
          termCode:this.termCode ,
          terProtocol: "PO",
          bankCode:this.bankCode 
      },
      termLocation: this.termLocation,
      termCapabilities: this.termCapabilities,
      termType: this.termType,
      tranCount: this.tranCount,
      termVerResult: this.termVerResult,
      applicationVerNum: this.applicationVerNum
  
   
      }
  
      // console.log("test::<" ,body);
      
      if (this.showBtnDeleteProfile) {
        this.globalService.updateTerminalPOS(body).subscribe(response => {
          this.closeModalProfile()
          // console.log('response://// ', response);
          if (response.respCode == '000') {
            this.initTerProfil()
            this.getTerminalList()
  
            this.alertBody.status = "000"
            if(this.en){
              this.alertBody.message = "Terminal Profil update successfuly"
            }
            if(this.fr){
              this.alertBody.message = "Mise à jour du Terminal Profil réussie"
            }
            if(this.esp){
              this.alertBody.message = "Terminal Profil actualizado exitosamente"
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
      else {
        this.globalService.addTerminalPOS(body).subscribe(response => {
          this.closeModalProfile()
          if (response.respCode == '000') {
            this.initTerProfil()
            this.getTerminalList()
            this.closeModalGlobal()
  
            this.showBtnDelete=false
  
            this.alertBody.status = "000"
            if(this.en){
              this.alertBody.message = "Terminal Profil added successfuly"
            }
            if(this.fr){
              this.alertBody.message = "Terminal Profil  ajouté avec succès"
            }
            if(this.esp){
              this.alertBody.message = "Terminal Profil añadido exitosamente"
            }
            this.alertBody.open = true
            setTimeout(() => {
              this.alertBody.open = false;
            }, 3000);
          
          }
          else if (response.respCode=="409") {
            this.closeModalProfile();
            this.alertBody.status = "Terminal Profil already exists"
            if(this.en){
              this.alertBody.message = "Terminal Profil already exists"
            }
            if(this.fr){
              this.alertBody.message = "Terminal Profil deja exists"
            }
            if(this.esp){
              this.alertBody.message = "Terminal Profil ya existe"
            }
            this.alertBody.open = true
            setTimeout(() => {
              this.alertBody.open = false;
            }, 3000);
          }else{
            this.closeModalGlobal()
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
  
    
  
    selectTerminalRecord(data: any) {
      this.showBtnDeleteProfile=true
      this.termCode = data.id.termCode
      this.termLocation = data.termLocation
      this.termCapabilities = data.termCapabilities
      this.termType = data.termType
      this.tranCount = data.tranCount
      this.termVerResult = data.termVerResult
      this.applicationVerNum = data.applicationVerNum
    }
  
  
    deleteTerminalRecord() {
    //   // console.log('this.id: ', this.terminalForm.value.id);
    //   this.globalService.deleteTerminal(this.terminalForm.value.id).subscribe(response => {
    //     // console.log('response: ', response);
    //     this.getTerminalList()
    //   })
    // }
  
    const body={
    
        termCode:this.termCode ,
        terProtocol:"PO" ,
        bankCode:this.bankCode 
      
    }
    // console.log(body);
    this.globalService.deleteTerminalPOS(body).subscribe(response => {
      this.closeModalProfile();
      if (response.respCode == "000") {
            this.initTerProfil()
            this.getTerminalList()
  
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Terminal profile delete successfuly";
        }
  
        if (this.fr) {
          this.alertBody.message = "Suppression du Terminal profile réussie";
        }
  
        if (this.esp) {
          this.alertBody.message = "Eliminación del Terminal profile exitosa";
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
  
        this.alertBody.status = "Something went wrong deleted Terminal profile "
        if (this.en) {
          this.alertBody.message = "Something went wrong during Terminal profile deletion";
        }
  
        if (this.fr) {
          this.alertBody.message = "Une erreur est survenue lors de la suppression du Terminal profile";
        }
  
        if (this.esp) {
          this.alertBody.message = "Algo salió mal durante la eliminación del Terminal profile";
        }
  
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
  
      }
    })
  }
  
  
  
  
  
  
    //////
  
    @ViewChild('modalGlobal', { read: ElementRef }) modalGlobal?: ElementRef
    openModalGlobal() {
      this.modalGlobal?.nativeElement.showModal()
    }
    closeModalGlobal() {
      this.modalGlobal?.nativeElement.close()
    }
  
    openSavePopUp(){
  
      // console.log(" this.bankCode==>", this.bankCode);
      // console.log("this.merCode ==>",this.merCode);
      // console.log("this.mcc ==>",this.mcc);
      // console.log("this.city ==>",this.city);
      // console.log("this.countryCode ==>",this.countryCode);
      
      
     
      this.showError=true
      if (
  
        this.bankCode !== "" &&
        this.merCode !== "" &&
        this.merName !== "" &&
        this.mcc !== "" &&
        this.city !== "" &&
        this.countryCode !== ""
      ) {
        this.message = "Are you sure you want to save"
        this.userDeleteActive = false;
        this.userSaveActive=true
        this.openModalGlobal();
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
      this.openModalGlobal();
    
    
    }
    
   
  
  //////
  
  
  
  @ViewChild('modalProfile', { read: ElementRef }) modalProfile?: ElementRef
  openModalProfile() {
    this.modalProfile?.nativeElement.showModal()
  }
  closeModalProfile() {
    this.modalProfile?.nativeElement.close()
  }
  
  
  
  openSavePopUpProfile(){
    this.showError=true
    if (
  
      this.bankCode !== "" &&
      this.termCode !== "" &&
      this.termLocation !== "" &&
      this.termCapabilities !== "" &&
      this.termType !== "" &&
      this.tranCount !== "" &&
      this.termVerResult !== "" &&
      this.applicationVerNum !== ""
  
    ) {
      if(this.language=="en"){
        this.message = "Are you sure you want to save "
      }else if (this.language==="fr") {
        this.message ="Êtes-vous sûr de vouloir enregistrer:"
      }else{
        this.message ="¿Estás seguro de que quieres guardar?"
      }
      
      this.userDeleteActiveProfile = false;
      this.userSaveActiveProfile=true
      this.openModalProfile();
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
  openDeletePopUpProfile(){
   if(this.language=="en"){
        this.message = "Are you sure you want to delete  :"
      }else if (this.language==="fr") {
        this.message ="Êtes-vous sûr de vouloir supprimer :"
      }else{
        this.message ="¿Estás seguro de que quieres eliminar ?"
      }
  
    this.userDeleteActiveProfile = true;
    this.userSaveActiveProfile=false;
    this.openModalProfile();
  
  
  }
  
  
  
  @ViewChild('modalCard', { read: ElementRef }) modalCard?: ElementRef
  openModalCard() {
    this.modalCard?.nativeElement.showModal()
  }
  closeModalCard() {
    this.modalCard?.nativeElement.close()
  }
  
  
  openDeletePopUpCard(){
    if(this.language=="en"){
        this.message = "Are you sure you want to delete  :"
      }else if (this.language==="fr") {
        this.message ="Êtes-vous sûr de vouloir supprimer :"
      }else{
        this.message ="¿Estás seguro de que quieres eliminar ?"
      }
    this.userDeleteActiveCard = true;
    this.userSaveActiveCard=false;
    this.openModalCard();
  
  
  }
  
  
  openSavePopUpCard(){
  // console.log(" this.cardPGeneralForm ==>", this.cardPGeneralForm);
  // console.log("this.magneticForm==>",this.magneticForm);
  
  // console.log(" this.cardPGeneralForm ==>", this.cardPGeneralForm.valid);
  // console.log(" this.magneticForm ==>", this.magneticForm.valid);
  
    if (
        // this.cardPGeneralForm.valid && this.magneticForm.valid
        this.cardPGeneralForm.get('cardNumber')?.value!="" &&this.cardPGeneralForm.get('cardNumber')?.value!=null 
        &&  this.cardPGeneralForm.get('cardDesc')?.value!=null && this.cardPGeneralForm.get('cardDesc')?.value!=" "
    ) {
      this.message = "Are you sure you want to save"
      this.userDeleteActiveCard= false;
      this.userSaveActiveCard=true
      this.openModalCard();
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
  
  
  getCountryDes(countryCode: string): string {
    const country = this.countryList.find((country: { id: string; }) => country.id === countryCode);
    return country ? country.countryDesc : countryCode;
  }
  
  getCityDes(c: string): string{
    const city = this.cityList.find((cc: { cityCode: string; }) => cc.cityCode === c);
    return city ? city.wording : c;
  }
  
  }
  