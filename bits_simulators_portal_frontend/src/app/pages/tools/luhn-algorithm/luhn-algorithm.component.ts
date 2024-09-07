import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-luhn-algorithm',
  templateUrl: './luhn-algorithm.component.html',
  styleUrls: ['./luhn-algorithm.component.scss']
})
export class LuhnAlgorithmComponent implements OnInit {

  p: number = 0;

  isDelete: boolean = false

  isValid=false;

  constructor(
    private globalService:GlobalService,
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

  user1:any

  fr = false;
  en = false;
  esp = false;
  language:  string = ""
  ngOnInit() {

    this.clearAll()
    this.selectedOption='Generate'
    // Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / Luhn algorithm"))

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / Luhn algorithm"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculatrices / Algorithme de Luhn"));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / Luhn algorithm"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }

  }

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


  }

  //
  pan:any='';
  selectedOption:string="";
  msg=""

  data:any={
    status:'',
    message:''
  }
  messages:any=[]
  contentVisible: boolean = true;


  toggleContent() {
    this.contentVisible = !this.contentVisible;
  }

  changeOperation(){

    if (!this.pan  ){
      this.data.status='NOK'
      this.data.message='required';

    }else{
      this.data.status='OK';
      this.data.message='success';

    }
    this.messages.push({
      message:this.data.message,
      status:this.data.status
    })

  }

  generate() {
    this.isValid=false;
    this.msg="";
    // console.log('pan ==>', this.pan);
    // console.log("selectedOption", this.selectedOption);
  
    if (this.selectedOption === "Generate") {
      if (this.pan.length <13 || this.pan.length >19) {
        this.msg = "Le PAN invalid";
      } else {
        const body={
          "pan":this.pan
        }
         this.globalService.luhnAlgorithm_generated(body).subscribe(res=>{
          if (res.respCode==="000") {
            this.isValid=true
             this.msg=res.respMsg
            
          }else{
            this.isValid=false
            this.msg=res.respMsg
          }
         })
      }
    }
    
    else if (this.selectedOption==="Validate"){
      if (this.pan.length <13 || this.pan.length >19) {
        this.msg = "Le PAN invalid";
      } else {
        const body={
          "pan":this.pan
        }
         this.globalService.luhnAlgorithm_validated(body).subscribe(res=>{
          if (res.respCode==="000") {
            this.isValid=true
             this.msg=res.respMsg
            
          }else{
            this.isValid=false
            this.msg=res.respMsg
          }
         })
      }
    }

    
  }



}
