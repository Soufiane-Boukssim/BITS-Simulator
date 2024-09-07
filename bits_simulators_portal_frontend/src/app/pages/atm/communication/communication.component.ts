import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {GlobalService} from "../../../services/global.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-communication',
  templateUrl: './communication.component.html',
  styleUrls: ['./communication.component.scss']
})
export class CommunicationComponent {

  bankCode: string = ""
  port:number = 0
  clientChecked: boolean = false;
  user1: any;
  fr = false;
  en = false;
  esp = false;
  language: string = "";

  alertBody: any = {
    message: '',
    status: '000',
    open: false
  }
  serviceChecked: boolean = false;
  ipRemote:string = ""
  ipLocal:string = ""
  errField:string = ""
  errFieldReIp:string = ""
  errFieldLoIp:string = ""
  isDelete:boolean = false
  commuicationForm: FormGroup = new FormGroup({
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
      bankCode: new FormControl(this.bankCode, Validators.required),
    }),

  });

  handleApiResponse(response: any) {
    switch (response.respCode) {
      case '000':
        // console.log('Success:');
        // Handle not found (e.g., show a not found message, clear form, etc.)
        break;
      case '404':
        console.error('Not Found:');
        // Handle not found (e.g., show a not found message, clear form, etc.)
        break;
      case '001':
        console.error('Exception:');
        // Handle exception (e.g., show error message, log error, etc.)
        break;
      case '409':
        console.error('Already Exists:');
        // Handle already exists (e.g., show a message indicating the item already exists)
        break;
      default:
        console.error('Unexpected response:', response);
      // Handle other cases
    }
  }
  constructor(
    private globalService:GlobalService,
    private formBuilder:FormBuilder,
    private auth: AuthService
  ){
    this.commuicationForm = this.formBuilder.group({
      remoteIpAddress: [''],
      port: ['']
    });
    }


  checkPortFormat(){
    this.errField = ''; // Resetting error message
    if(this.commuicationForm.get('commPort')?.value < 100){
      this.errField = "Port number shoudn't be less than 3 numbers"
    }
  }

  checkIpFormat(type:any) {
    let ip = (type === "remote") ? this.commuicationForm.get('commIp')?.value : this.commuicationForm.get('commIp')?.value;
    let errField = (type === "remote") ? 'errFieldReIp' : 'errFieldLoIp';
    this.errField = ''; // Resetting error message

    let octets = ip.split('.');
    if (octets.length !== 4) {
      this.errField = "IP address should have 4 octets";
      return;
    }

    for (let i = 0; i < octets.length; i++) {
      let val = octets[i];
      if (!val.match(/^\d+$/)) {
        this.errField = "Each octet should be a number";
        return;
      }
      if (Number(val) > 255 || Number(val) < 0) {
        this.errField = "Each octet should be between 0 and 255";
        return;
      }
    }
    // console.log('IP is valid:', ip);
  }


  ngOnInit() {
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters / Communication"));
      this.en=true;
    } else if (this.language === "fr") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres / communication"));
      this.fr=true;
    } else if (this.language === "es") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros / comunicación"));
      this.esp=true;
    } else {
      Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }

    // Promise.resolve().then(() => this.globalService.titleSubject.next("Communication"))
    this.auth.user.subscribe(val => {
      this.bankCode = val.bankCode
    })
    this.commuicationForm = this.formBuilder.group({
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
        bankCode: new FormControl(this.bankCode, Validators.required),
      }),
    });
    this.getCommunication();

  }

  resetForm() {
    this.commuicationForm.reset({
      commType: '',
      instCode: '',
      headerComm: 0,
      headerType: '',
      commIp: '',
      commPort: 0,
      managMac: '',
      managHeader: '',
      msgHeader: '',
      chipSupported: '',
      timeOutComms: '',
      id: {
        commId: 0,
        bankCode: this.bankCode
      },
    });
  }

  handleComModeChange() {
    const commType = this.commuicationForm.get('commType')?.value;
    // console.log(commType)
    if (commType === 'S') {
      this.commuicationForm.get('commIp')?.disable();
    } else {
      this.commuicationForm.get('commIp')?.enable();
    }
  }

  add() {
    this.handleComModeChange();
    const commType = this.commuicationForm.get('commType')?.value;

    // If commType is not 'S', then perform validation
    if (commType !== 'S' && !this.validateForm()) {
      // console.log('Form data is invalid');
      return;
    }

    // Prepare formData for add or update operation
    const formData = { ...this.commuicationForm.value };

    const action = this.isDelete ? 'update' : 'add';
    // console.log(`${action} data:`, formData);

    // Choose the appropriate service method based on the action
    const observable = this.isDelete
      ? this.globalService.updateComms(formData)
      : this.globalService.addComms(formData);

    // Subscribe to the observable from the service
    observable.subscribe(response => {
      // console.log(`Response from ${action}Communication:`, response);
      this.handleApiResponse(response);
      if (response.respCode === '000') {
        this.getCommunication();
        // Set the alert message and status, and open the alert
        this.alertBody.status = "000";
        if(this.en){
          this.alertBody.message = "Communication added successfully";
        }
        if(this.fr){
          this.alertBody.message = "Communication ajoutée avec succès";
        }
        if(this.esp){
          this.alertBody.message = "Comunicación añadida exitosamente";
        }
        this.alertBody.open = true;
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
    });
  }

  deleteCommunication() {
    // console.log('Deleting communication data:', this.commuicationForm.get('id')?.value);
    this.globalService.deleteComms(this.commuicationForm.get('id')?.value).subscribe(response => {
      // console.log('Response from deleteCommunication:', response);
      this.handleApiResponse(response)
      // Handle the response, such as updating UI or showing a success message
      this.resetForm()
      this.isDelete=false
      // Set the alert message and status, and open the alert
      this.alertBody.status = "000";
      if(this.en){
        this.alertBody.message = "Communication deleted successfully";
      }
      if(this.fr){
        this.alertBody.message = "Communication supprimée avec succès";
      }
      if(this.esp){
        this.alertBody.message = "Comunicación eliminada exitosamente";
      }
      this.alertBody.open = true;
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);
    });
  }

  validateForm(): boolean {
    const formValue = this.commuicationForm.value;

    if (!formValue.commType ) {
      console.error('Required fields are missing');
      return false;
    }

    // if (!this.isValidIp(formValue.commIp) || !this.isValidIp(formValue.commIp)) {
    //   console.error('Invalid IP address format');
    //   return false;
    // }

    if (!this.isValidPort(formValue.commPort)) {
      console.error('Invalid port number');
      return false;
    }

    return true;
  }

  // isValidIp(ip: string): boolean {
  //   const octets = ip.split('.');
  //   if (octets.length !== 4) {
  //     return false;
  //   }
  //
  //   for (let val of octets) {
  //     if (!val.match(/^\d+$/) || Number(val) > 255 || Number(val) < 0) {
  //       return false;
  //     }
  //   }
  //   return true;
  // }

  isValidPort(port: string): boolean {
    return !isNaN(parseInt(port)) && parseInt(port) >= 100;
  }





  getCommunication() {
    const id = {
      commId: 0,
      bankCode: this.bankCode, // Replace with your actual user object
    };

    // console.log('id: ', id);
    this.globalService.getComms(id).subscribe((response) => {
      // console.log('response: ', response);
      if (response.result && response.result.length > 0) {
        const firstResult = response.result[0];
        // console.log('firstResult', firstResult);

        this.isDelete = true;
        this.commuicationForm.patchValue({
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
          timeOutComms: firstResult.timeOutComms
        });

        if (firstResult.commType == 'S') {
          this.serviceChecked = true;
          this.clientChecked = false;
          this.commuicationForm.get('commIp')?.disable();
        } else {
          this.clientChecked = true;
          this.serviceChecked = false;
          this.commuicationForm.get('commIp')?.enable();
        }

        // console.log('this.commuicationForm: ', this.commuicationForm.value);
      } else {
        this.isDelete = false;
      }
    }, error => {
      // console.log('error: ', error);
    });
  }


  // deleteCommunication() {
  //
  //   // console.log('Deleting communication data:', this.commsDefinitionForm.get('id')?.value);
  //   this.globalService.deleteComms(this.commsDefinitionForm.get('id')?.value).subscribe(response => {
  //     // console.log('Response from deleteCommunication:', response);
  //     this.handleApiResponse(response)
  //     // Handle the response, such as updating UI or showing a success message
  //     this.resetForm()
  //     this.isDelete=false
  //   });
  // }

}

