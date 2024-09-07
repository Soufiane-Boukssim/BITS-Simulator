import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import { WsService } from 'src/app/services/ws.service';

@Component({
  selector: 'app-crypto-calculators',
  templateUrl: './crypto-calculators.component.html',
  styleUrls: ['./crypto-calculators.component.scss']
})
export class CryptoCalculatorsComponent implements OnInit  {
  p: number = 0;
  
  user1:any

  fr = false;
  en = false;
  esp = false;
  language:  string = ""
  protocol: string = "";
  bankCode: any = "";
  isDelete: boolean = false

  data_resulter: any

  // data_tag:any
  data_tag: DataTag[] = [];

  itemsPerPageSelct:number=5

  showError: boolean = false;
  message = "";

  alertBody: any = {
    message: '',
    status: '000',
    open: false
  }




  constructor(
    private globalService: GlobalService,
    private auth: AuthService,
    private fb: FormBuilder,
    private http: HttpClient,
    private wsService: WsService,
    private router: Router,
    private formBuilder: FormBuilder,

  ) { }
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



  ngOnInit() {

    this.clearAll()
    this.Operation='0'

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / EMV TLV Parser"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculatrices / Analyseur EMV TLV"));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / EMV TLV Parser"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }


    this.open_tabDailog(1);

  }

  open_tab(index: number) {
    this.clearAll()
    this.isDelete = false
    this.Operation='0'
    this.contentVisible=true
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



  selectedByte: number | null = null;

  getUniqueAipBytes(): number[] {
    const uniqueAipBytes = Array.from(new Set(this.data_tag.map(item => item.aipByte)));
    return uniqueAipBytes;
  }

  isTabActive(byte: number): boolean {
    return this.selectedByte === byte;
  }

  open_tabDailog(byte: number): void {
    this.selectedByte = byte;
  }



  tab_1_Dailog: boolean = true
  tab_2_Dailog: boolean = false
  tab_3_Dailog: boolean = false
  tab_4_Dailog: boolean = false
  tab_5_Dailog: boolean = false


  
  clearAll(): void {
    this.isDelete = false;
    

  }

  //
 hexData=""
  Key:any=''
  Padding:any='0'
  Mode:any='0'
  Operation:any='0'
  contentVisible: boolean = true;
  data:any={
    status:'',
    message:''
  }
  messages:any=[]

  toggleContent() {
    this.contentVisible = !this.contentVisible;
  }








  operation:any ='0'
  BlockB:any=''
  BlockA:any=''
  Result:any=''

  changeOperation(){

    if (!this.BlockA || !this.BlockB || this.operation=='0'){
      this.data.status='NOK';
      this.data.message='required';

    }else{
      const dataBlockA = this.BlockA;
      const dataBlockB = this.BlockB;
      const OP = this.operation;
      this.Result= OP +dataBlockA + dataBlockB ;
      this.data.status='OK';
      this.data.message='success';

    }
    this.messages.push({
      message:this.data.message,
      status:this.data.status
    })

  }






  //// Calculators Parce Data


  parceData(){
    this.data_tag = [];
    this.data_resulter=[]

    const body={
      "data":this.hexData
    }
    // console.log("data:",this.hexData);
    
    this.globalService.parceData(body).subscribe(response => {
  
     if (response.respCode==="000") {
      this.data_resulter= response.result

      this.alertBody.status = "000";
      if (this.en) {
          this.alertBody.message = "Data parsed successfully";
      }
      if (this.fr) {
          this.alertBody.message = "Data parsed successfully";
      }
      if (this.esp) {
          this.alertBody.message = "Data parsed successfully";
      }
      this.alertBody.open = true;
      setTimeout(() => {
          this.alertBody.open = false;
      }, 3000);
     }else{
      this.alertBody.status = "No records found";
      if (this.en) {
          this.alertBody.message = response.respMsg;
      }
      if (this.fr) {
          this.alertBody.message = response.result.respMsg;;
      }
      if (this.esp) {
          this.alertBody.message =response.result.respMsg;;
      }
      this.alertBody.open = true;
      setTimeout(() => {
          this.alertBody.open = false;
      }, 3000);

     }
     
      
    })
  } 


  @ViewChild('modalAleart', { read: ElementRef }) modalAleart?: ElementRef
  openModalAleart(tag: string) {
    this.modalAleart?.nativeElement.showModal()
   
    const data = this.data_resulter.find((item: { tag: string; }) => item.tag === tag);
     
    this.data_tag = data.dataTag;

    // console.log(this.data_tag);
    
  }


  closemodalAleart() {
    this.modalAleart?.nativeElement.close()
  }

}



interface DataTag {
  aipByte: number;
  meaning:string;
  bitValue:string;
  present:string;
  bitIndex: number;
}
