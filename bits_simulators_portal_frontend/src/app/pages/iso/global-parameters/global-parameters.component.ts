import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-global-parameters',
  templateUrl: './global-parameters.component.html',
  styleUrls: ['./global-parameters.component.scss']
})
export class GlobalParametersComponent {
  protocol: string = "";
  language: string = "";
  bankCode: any = "";

  isLoading: boolean = false;

  alertBody: any = {
    message: '',
    status: '000',
    open: false
  }

  bankList:any=[]
  user1:any

  fr = false;
  en = false;
  esp = false;
  

  showError: boolean = false;
  message = "";
  userDeleteActive:boolean=false
  userSaveActive:boolean=false
  
  
 
  instForm: FormGroup = new FormGroup('');

  constructor(
    private globalService: GlobalService,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) { }
  tab_1: boolean = false;
  tab_2: boolean = false
  tab_3: boolean = true;
  isDelete: boolean = false
  isChiped: boolean = false
  pinFormats = [
    { code: '01', description: 'ISO 9564-1 & ANSI X9.8 format 0' },
    { code: '02', description: 'Docutel ATM format' },
    { code: '03', description: 'Diebold & IBM ATM format' },
    { code: '04', description: 'PLUS Network format' },
    { code: '05', description: 'ISO 9564-1 format 1' },
    { code: '34', description: 'Standard EMV 1996 format' },
    { code: '35', description: 'MasterCard Pay Now & Pay Later format' },
    { code: '41', description: 'Visa/Amex new PIN only format' },
    { code: '42', description: 'Visa/Amex new & old PIN format' },
    { code: '47', description: 'ISO 9564-1 & ANSI X9.8 format 3' },
  ];
  user: any = {}
  serviceChecked: boolean = false;
  clientChecked: boolean = false;
  manageHeaderChecked: boolean = false;
  manageHeaderMac: boolean = false;
  checkValMasterKey: String = ''
  checkValPinKey: String = ''
  checkValMacKey: String = ''
  currencyList: any = []

  


  communicationForm: FormGroup = new FormGroup({
    commType: new FormControl(''),
    instCode: new FormControl(''),
    headerComm: new FormControl(0),
    headerType: new FormControl(''),
    commIp: new FormControl(''),
    commPort: new FormControl(0),
    managMac: new FormControl(''),
    managHeader: new FormControl(''),
    msgHeader: new FormControl(''),


    chipSupported: new FormControl(''),
    timeOutComms: new FormControl(''),
    

    id: new FormGroup({
      commId: new FormControl(0, Validators.required),
      commProtocol: new FormControl(this.protocol, Validators.required),
      bankCode: new FormControl(this.user.bankCode, Validators.required),
    }),

  });

  // For idCommunication

  ngOnInit() {
    this.authService.user.subscribe(
      (x: any) => {
        this.user = x;
        // console.log(this.user)
      });

      this.user1 = localStorage.getItem('user');
      this.user1 = JSON.parse(this.user1);
      this.language = this.user1.languageCode;
      if (this.language === "en") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters / Institution"));
        this.en=true
      } else if (this.language === "fr") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres / Institution"));
          this.fr=true
      } else if (this.language === "es") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros / Institución"));
          this.esp=true
      } else {
          Promise.resolve().then(() => this.globalService.titleSubject.next(""));
      }

      
  
      const storedProtocol = localStorage.getItem('protocol');
      if (storedProtocol !== null) {
        this.protocol = storedProtocol;
      } else {
        // console.log("error de protocol");
        
      }
    
    this.language = localStorage.getItem('lang') || 'en'
    // // console.log('this.protocol: ', this.protocol);
    this.authService.user.subscribe(val => {
      this.bankCode = val.bankCode
    })

  
    // console.log("protocol ==>",this.protocol);

    this.getCommunicationParam()
      this.getInstitutionParam()
      this.getCurrencyList()

      this.getBankList();
  }
  open_tab(index: number) {
    
    this.isDelete = false
    if (index == 0) {
      this.tab_1 = true
      this.tab_2 = false
      this.tab_3 = false
     
    } else if (index == 2) {
      
      this.tab_1 = false
      this.tab_2 = false
      this.tab_3 = true
    } else {
    
      this.tab_1 = false
      this.tab_2 = false
      this.tab_3 = true
    }
  }



  getBankList() {
    this.globalService.fetchBank().subscribe(response => {
      if (response.respCode ==="000") {
        this.bankList = response.result
        // console.log('this.bankList: ', this.bankList);
      }else{
        // console.log("error: ", response.respMsg);
      }
    })
  }

 

  handleCheckboxChange(t: string) {
    if (t == 's') {
      this.serviceChecked = true
      this.clientChecked = false
      this.communicationForm.get('commIp')?.disable()
      // this.communicationForm.get('commIp')?.setValue('');

    } if (t == 'c') {
      this.clientChecked = true
      this.serviceChecked = false
      this.communicationForm.get('commIp')?.enable()

    }

    if (t == 'm') {
      if (this.manageHeaderMac) {
        this.manageHeaderMac = false
      } else this.manageHeaderMac = true

    }

    if (t == 'h') {
      if (this.manageHeaderChecked) {
        this.manageHeaderChecked = false
      
      } else {
        this.manageHeaderChecked = true  
    
      }

    }
  }

  handleCheckboxChang() {
    if (this.isChiped) {
      this.isChiped = false
    }
    else this.isChiped = true
  }
 

  getCurrencyList() {

    this.globalService.fetchCurrency().subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.currencyList = response.result
      }
    })
    this.clearAll()
  }


  getCommunicationParam() {
    this.isLoading=true;
    const id = {
      commId: 0,
      bankCode: this.user.bankCode, // Replace with your actual user object
      commProtocol: this.protocol, 
    };

    // console.log('id: ', id);
    this.globalService.getOneCommunicationParam(id).subscribe((response) => {
      // console.log('response: ', response);
     this.isLoading=false;
      if (response.result && response.result.length > 0) {
        const firstResult = response.result[0]; 
        // console.log('firstResult',firstResult);

        if (firstResult.managHeader==null || firstResult.managHeader=="N"|| firstResult.managHeader=="") {
          this.manageHeaderChecked=false
        }else{
          this.manageHeaderChecked=true
        }
        
        this.isDelete = true
        this.communicationForm.patchValue({
          commType: firstResult.commType,
          instCode: firstResult.instCode,
          headerType: firstResult.headerType,
          headerComm: firstResult.headerComm,
          commIp: firstResult.commIp,
          commPort: firstResult.commPort,
          managMac: firstResult.managMac,
          managHeader: firstResult.managHeader,
          msgHeader: firstResult.msgHeader,
          id: firstResult.id,
          chipSupported: firstResult.chipSupported,
          timeOutComms:firstResult.timeOutComms
          // Add more form controls as needed
        });

        if (firstResult.chipSupported == 'Y') {
          this.isChiped = true
        }
        else {
          this.isChiped = false
        }


        if (firstResult.commType == 'S') {
          this.serviceChecked = true
          this.clientChecked = false
          this.communicationForm.get('commIp')?.disable()
        }
        else {
          this.clientChecked = true
          this.serviceChecked = false
          this.communicationForm.get('commIp')?.enable()
        }
        if (firstResult.managHeader == 'Y')
          this.manageHeaderChecked = true
        if (firstResult.managMac == 'Y')
          this.manageHeaderMac = true

        // console.log('this.communicationForm: ', this.communicationForm.value);
        this.isLoading=false
      }

      else this.isDelete = false
    },
    (error) => {
      // console.log('error: ', error);
      this.isLoading=false
    });

    // Assuming you have a clearAll function

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
          // // console.log('response',response.result);

          if (data == "masterKey") {
            this.checkValMasterKey=response.result
          }
          else if (data == "pinKey") {
            this.checkValPinKey=response.result
          }
          else {
            this.checkValMacKey=response.result
          }

      });

    }else{
      if (data == "masterKey") {
        this.checkValMasterKey=""
      }
      else if (data == "pinKey") {
        this.checkValPinKey=""
      }
      else {
        this.checkValMacKey=""
      }
    }
  }



  ////  fin encrypt KEY ********************************





  submitCommunicationParam() {
    const formData = { ...this.communicationForm.value };

   
    // console.log("formData ",formData);

    // Modify values as needed
    if (this.manageHeaderMac) {
      formData.manageHeaderMac = "Y";
    } else
      formData.manageHeaderMac = "N";


    if (this.manageHeaderChecked) {
      formData.managHeader = "Y";
      // console.log("testttttt::::> managHeader set to Y");
    } else{
      formData.managHeader = "N";
      // console.log("testttttt::::> managHeader set to N");
    }
    // console.log("test fin de formData ",formData);
    

    if (this.serviceChecked) {
      formData.commType = "S"
      formData.commIp = ""
    }

    if (this.clientChecked) {
      formData.commType = "C"
    }
   

    
    
  if (this.isDelete) {
    this.globalService.updateCommunicationParam(formData).subscribe((response) => {
     this.closemodal()
      // console.log('response://// ', response);
      if (response.respCode == '000') {
        this.communicationForm.reset();
        this.getCommunicationParam();

        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "Communication Param  update successfuly"
        }
        if(this.fr){
          this.alertBody.message = "Mise à jour du Communication Param réussie"
        }
        if(this.esp){
          this.alertBody.message = "Communication Param actualizado exitosamente"
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
    this.globalService.addCommunicationParam(formData).subscribe((response) => {
      if (response.respCode == '000') {
        this.communicationForm.reset();
        this.getCommunicationParam();

        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "Communication Param added successfuly"
        }
        if(this.fr){
          this.alertBody.message = "Communication Param  ajouté avec succès"
        }
        if(this.esp){
          this.alertBody.message = "Communication Param añadido exitosamente"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      
      }
      else if (response.respCode=="409") {
        this.closemodal();
        this.alertBody.status = "Communication Param already exists"
        if(this.en){
          this.alertBody.message = "Communication Param already exists"
        }
        if(this.fr){
          this.alertBody.message = "Communication Param deja exists"
        }
        if(this.esp){
          this.alertBody.message = "Communication Param ya existe"
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


  clearAll() {
    this.instForm.reset();

    this.instForm = this.formBuilder.group({
      id: this.formBuilder.group({
        //systemMode: [''],
        //instCode: [''],
        instProtocol: [this.protocol],
        bankCode: [this.bankCode]
      }),
      //instDesc: [''],
      instTransCur1: ['', Validators.maxLength(3)], // Max length of 3
      // instTransCur2: [''],                       // Commented out in the shared data
      // instTransCur3: [''],                       // Commented out in the shared data
      instSettCurr: ['', Validators.maxLength(3)], // Max length of 3
      // singleMsg: [''],                           // Commented out in the shared data
      pinKey: ['', Validators.maxLength(256)],     // Max length of 256
      macKey: ['', Validators.maxLength(256)],     // Max length of 256
      pinFormat: [''],
      // pinKeyLen: [0],                             // Commented out in the shared data
      // macKeyLen: [0],                             // Commented out in the shared data
      masterKey: ['', Validators.maxLength(256)],  // Max length of 256
      chipSupported: [''],
      timeOutComms: [0],
      acqCode: [''],
      fwdCode: [''] ,                               // Added field from the shared data

      ica: [''] , 
      acquirerId: [''] , 
      stationId: [''] , 
      processorId: [''] , 

    });
    this.communicationForm.reset({
      commType: '',
      instCode: '',
      headerComm: 0,
      headerType: '',
      commIp: '',
      commPort: 0,
      managMac: '',
      managHeader: '',
      msgHeader: '',
      id: {
        commId: 0,
        commProtocol: this.protocol,
        bankCode: this.user.bankCode,
      },
    });
  }
  deleteCommunicationParamRecord() {
  //   // console.log('this.idCommunication: ', this.communicationForm.value.id);
  //   this.globalService.deleteCommunicationParam(this.communicationForm.value.id).subscribe((response) => {
  //     // console.log('response: ', response);

  //     this.clearAll()
  //   });
  // }
  this.globalService.deleteCommunicationParam(this.communicationForm.value.id).subscribe((response) => {
    this.closemodal();
    if (response.respCode == "000") {
      this.clearAll()

      this.alertBody.status = "000"
      if (this.en) {
        this.alertBody.message = "Communication Param  delete successfuly";
      }

      if (this.fr) {
        this.alertBody.message = "Suppression du Communication Param  réussie";
      }

      if (this.esp) {
        this.alertBody.message = "Eliminación del Communication Param  exitosa";
      }
      this.alertBody.open = true
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);
    }
    else {

      this.alertBody.status = "Something went wrong deleted Communication Param  "
      if (this.en) {
        this.alertBody.message = "Something went wrong during Communication Param  deletion";
      }

      if (this.fr) {
        this.alertBody.message = "Une erreur est survenue lors de la suppression du Communication Param ";
      }

      if (this.esp) {
        this.alertBody.message = "Algo salió mal durante la eliminación del Communication Param ";
      }

      this.alertBody.open = true
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);

    }
  })
}


  getInstitutionParam() {
    const id = {
      bankCode: this.user.bankCode, // Replace with your actual user object
      instProtocol: this.protocol, // Replace with your actual communication protocol
    };

    // console.log('id: ', id);
    this.globalService.getOneInstitutionParam(id).subscribe((response) => {
      // console.log('response: ', response);

      if (response.result && response.result.length > 0) {
        const firstResult = response.result[0]; // Assuming you want the first result if there are multiple
        this.isDelete = true;

        this.instForm.patchValue({
          id: {
            // systemMode: firstResult.id.systemMode,
            // instCode: firstResult.id.instCode,
            instProtocol: firstResult.id.instProtocol,
            bankCode: firstResult.id.bankCode
          },
          instTransCur1: firstResult.instTransCur1,
          // instTransCur2: firstResult.instTransCur2,
          // instTransCur3: firstResult.instTransCur3,
          instSettCurr: firstResult.instSettCurr,
          // singleMsg: firstResult.singleMsg,
          pinKey: firstResult.pinKey,
          macKey: firstResult.macKey,
          pinFormat: firstResult.pinFormat.padStart(2, '0'),
          // pinKeyLen: firstResult.pinKeyLen,
          // macKeyLen: firstResult.macKeyLen,
          masterKey: firstResult.masterKey,
          chipSupported: firstResult.chipSupported,
          timeOutComms: firstResult.timeOutComms,
          acqCode: firstResult.acqCode,
          fwdCode: firstResult.fwdCode , // Include fwdCode
          ica:firstResult.ica ,
          processorId:firstResult.processorId,
          stationId:firstResult.stationId,
          acquirerId:firstResult.acquirerId
          // Add more form controls as needed
        });
        // if (firstResult.chipSupported == 'Y') {
        //   this.isChiped = true
        // }
        // else {
        //   this.isChiped = false
        // }


             let paddedDatamasterKey = '';
              if ( firstResult.masterKey.length=== 16) {
                paddedDatamasterKey = '0'.repeat(16); 
              } else if (firstResult.masterKey.length === 32) {
                paddedDatamasterKey = '0'.repeat(32); 
              } else if (firstResult.masterKey.length === 48) {
                paddedDatamasterKey = '0'.repeat(48); 
              }
        const bodyMasterKey={
          key: firstResult.masterKey,
          data: paddedDatamasterKey
          // data: "00000000000000000000000000000000"
        }
        // // console.log('body: ', body);
    
        this.globalService.encryptKey(bodyMasterKey).subscribe((response) => {
              this.checkValMasterKey=response.result
        });


        let paddedDatapinKey = '';
        if ( firstResult.pinKey.length=== 16) {
          paddedDatapinKey = '0'.repeat(16); 
        } else if (firstResult.pinKey.length === 32) {
          paddedDatapinKey = '0'.repeat(32); 
        } else if (firstResult.pinKey.length === 48) {
          paddedDatapinKey = '0'.repeat(48); 
        }

        const bodypinKey={
          key: firstResult.pinKey,
          data: paddedDatapinKey
          // data: "00000000000000000000000000000000"
        }
        this.globalService.encryptKey(bodypinKey).subscribe((response) => {
          this.checkValPinKey=response.result
        });

        let paddedDatamacKey = '';
        if ( firstResult.macKey.length=== 16) {
          paddedDatamacKey = '0'.repeat(16); 
        } else if (firstResult.macKey.length === 32) {
          paddedDatamacKey = '0'.repeat(32); 
        } else if (firstResult.macKey.length === 48) {
          paddedDatamacKey = '0'.repeat(48); 
        }

        const bodyMacKey={
          key: firstResult.macKey,
          data: paddedDatamacKey
          // data: "00000000000000000000000000000000"
        }
        this.globalService.encryptKey(bodyMacKey).subscribe((response) => {
          this.checkValMacKey=response.result
        });




        // console.log('this.instForm: ', this.instForm.value);
      } else {
        this.isDelete = false;
      }
    })

    // Assuming you have a clearAll function

  }


  deleteInstitutionParamRecord() {
  this.globalService.deleteInstitutionParam(this.instForm.value.id).subscribe((response) => {
    this.closemodal();
    if (response.respCode == "000") {
      this.clearAll();

      this.alertBody.status = "000"
      if (this.en) {
        this.alertBody.message = "Institution delete successfuly";
      }

      if (this.fr) {
        this.alertBody.message = "Suppression du Institutionréussie";
      }

      if (this.esp) {
        this.alertBody.message = "Eliminación del Institutionexitosa";
      }
      this.alertBody.open = true
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);
    }
    else {

      this.alertBody.status = "Something went wrong deleted Institution"
      if (this.en) {
        this.alertBody.message = "Something went wrong during Institutiondeletion";
      }

      if (this.fr) {
        this.alertBody.message = "Une erreur est survenue lors de la suppression du Institution";
      }

      if (this.esp) {
        this.alertBody.message = "Algo salió mal durante la eliminación del Institution";
      }

      this.alertBody.open = true
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);

    }
  })
}

  submitInstitutionParam() {
    if (this.isChiped) {
      this.instForm.get('chipSupported')?.setValue('Y');
    }
    else {
      this.instForm.get('chipSupported')?.setValue('N');
    }
  //   if (!this.isDelete) {
  //     // console.log('this.instForm.value: ', this.instForm.value);
  //     this.globalService.addInstitutionParam(this.instForm.value).subscribe((response) => {
  //       // console.log('response: ', response);

  //       if (response.respCode === '000') {
  //         this.instForm.reset();

  //         this.getInstitutionParam();

  //       }
  //     });
  //   } else {
  //     // console.log('this.instForm.value: ', this.instForm.value);
  //     this.globalService.updateInstitutionParam(this.instForm.value).subscribe((response) => {
  //       // console.log('response: ', response);

  //       if (response.respCode === '000') {
  //         this.instForm.reset();
  //         this.getInstitutionParam()
  //       }
  //     });
  //   }
  //   this.isDelete = true;
  // }

  if (this.isDelete) {
    this.globalService.updateInstitutionParam(this.instForm.value).subscribe((response) => {
     this.closemodal()
      // console.log('response://// ', response);
      if (response.respCode == '000') {
        this.instForm.reset();
        this.getInstitutionParam()

        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "Institution Param update successfuly"
        }
        if(this.fr){
          this.alertBody.message = "Mise à jour du Institution Param réussie"
        }
        if(this.esp){
          this.alertBody.message = "Institution Param actualizado exitosamente"
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
    this.globalService.addInstitutionParam(this.instForm.value).subscribe((response) => {
      if (response.respCode == '000') {
        this.instForm.reset();
        this.getInstitutionParam()

        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "Institution Param added successfuly"
        }
        if(this.fr){
          this.alertBody.message = "Institution Param  ajouté avec succès"
        }
        if(this.esp){
          this.alertBody.message = "Institution Param añadido exitosamente"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      
      }
      else if (response.respCode=="409") {
        this.closemodal();
        this.alertBody.status = "Institution Param already exists"
        if(this.en){
          this.alertBody.message = "Institution Param already exists"
        }
        if(this.fr){
          this.alertBody.message = "Institution Param deja exists"
        }
        if(this.esp){
          this.alertBody.message = "Institution Param ya existe"
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


  deleteGlobal(){
    if (this.tab_1==true) {
      this.deleteCommunicationParamRecord();
    }
  

    if (this.tab_3==true) {
      this.deleteInstitutionParamRecord()
    }
    
    
  }


  SaveGlobal(){
    if (this.tab_1==true) {
      this.submitCommunicationParam()
      
    }
    if (this.tab_3==true) {
      this.submitInstitutionParam();
    }
    
  }



}
