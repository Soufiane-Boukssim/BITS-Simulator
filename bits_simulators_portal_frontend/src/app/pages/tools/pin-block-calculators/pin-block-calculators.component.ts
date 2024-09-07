import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import { GlobalService } from 'src/app/services/global.service';
import * as CryptoJS from 'crypto-js';
@Component({
  selector: 'app-pin-block-calculators',
  templateUrl: './pin-block-calculators.component.html',
  styleUrls: ['./pin-block-calculators.component.scss']
})
export class PinBlockCalculatorsComponent implements OnInit {
  message = "";

  alertBody: any = {
    message: '',
    status: '000',
    open: false
  }

  p: number = 0;

  isDelete: boolean = false
  constructor(
    private globalService: GlobalService,
    private formBuilder: FormBuilder,

  ) { }

  ngOnInit() {

    this.clearAll()
    Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / PIN"));
  }
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
  user: any


  

  open_tab(index: number) {
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
        this.p = 0
        break;
      case 1:
        this.tab_2 = true
        this.p = 0
        break;
      case 2:
        this.tab_3 = true
        this.p = 0
        break;
      case 3:
        this.tab_4 = true
        this.p = 0
        break;
      case 4:
        this.tab_5 = true
        this.p = 0
        break;
      case 5:
        this.tab_6 = true
        this.p = 0
        break;
      case 6:
        this.tab_7 = true
        this.p = 0
        break;
      case 7:
        this.tab_8 = true
        this.p = 0
        break;
      case 8:
        this.tab_9 = true
        this.p = 0
        break;
      case 9:
        this.tab_10 = true
        this.p = 0
        break;

      default:
        break;
    }
  }

  clearAll(): void {
    this.isDelete = false;
    this.pin=""
    this.pan=""
    this.pinblock=""

  }

  //

  pinblock:any='';
  pin:any='';
  pan:any='';

  data:any={
    status:'',
    message:''
  }
  messages:any=[]
  contentVisible: boolean = true;

  toggleContent() {
    this.contentVisible = !this.contentVisible;
  }

  calculatePinBlock() {
    this.pinblock=""
    if (this.pan && this.pin &&(this.pan.length >= 9 && this.pan.length <= 19) && (this.pin.length > 3 && this.pin.length <= 12)) {
       const body={
        "pin": this.pin,
        "pinBlockFormat": "01",
        "accountNumber": this.pan.substring(0, 12),
        "PINKey": "1234"
       }

       this.globalService.generatePinBlockHex(body).subscribe(res=>{
          if (res.respCode==="000") {
              this.pinblock=res.result;
              
          }
          else{
            this.alertBody.status = res.respCode;
            this.alertBody.message = res.respCode;
            this.alertBody.open = true;
            setTimeout(() => {
                this.alertBody.open = false;
            }, 3000);
          }
       })
    } else {
      this.alertBody.status = "Please enter both PAN and PIN";
        this.alertBody.message = "Please enter both PAN and PIN";
        this.alertBody.open = true;
        setTimeout(() => {
            this.alertBody.open = false;
        }, 3000);
    }
  }

 

  calculatePinBlockToPin() {
    this.pinblock=""
    if ( this.pin  && (this.pin.length > 3 && this.pin.length <= 12)) {
       const body={
        "pin": this.pin,
        "pinBlockFormat": "01",
        "accountNumber": "000000000000",
        "PINKey": "1234"
       }

       this.globalService.generatePinBlockHex(body).subscribe(res=>{
          if (res.respCode==="000") {
              this.pinblock=res.result;
              
          }
          else{
            this.alertBody.status = res.respCode;
            this.alertBody.message = res.respCode;
            this.alertBody.open = true;
            setTimeout(() => {
                this.alertBody.open = false;
            }, 3000);
          }
       })
    } else {
      this.alertBody.status = "Please enter both  PIN";
        this.alertBody.message = "Please enter both PIN";
        this.alertBody.open = true;
        setTimeout(() => {
            this.alertBody.open = false;
        }, 3000);
    }
  }

 





  pvki:any='';
  pvk:any='';
  pvv:any='';
  KCV:any='';



  calculateVisaPvv() {
    if (!this.pan || !this.pin || !this.pvki || !this.pvk) {
      return;
    }

    const dataToHash = `${this.pan}${this.pin}${this.pvki}`;
    const hashedData = this.applyPVK(dataToHash, this.pvk);

    this.pvv = hashedData.substring(0, 4);
  }

  applyPVK(data: string, pvk: string): string {
    // Exemple de hachage, utilisez la méthode de hachage appropriée pour votre application
    const hash = CryptoJS.HmacSHA256(data, pvk).toString(CryptoJS.enc.Hex);
    return hash;
  }
  


  intermediatepin:any='';
  pgk:any='';
  pinlength:any='';
  destable:any='';





  offset:any='';
  CSpin:any='';




  pek:any='';
  pinb:any='';




  peki:any='';
}
