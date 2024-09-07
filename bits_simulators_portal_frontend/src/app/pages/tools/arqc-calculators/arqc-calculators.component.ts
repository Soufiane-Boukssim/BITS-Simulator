import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {GlobalService} from "../../../services/global.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-arqc-calculators',
  templateUrl: './arqc-calculators.component.html',
  styleUrls: ['./arqc-calculators.component.scss']
})
export class ArqcCalculatorsComponent implements OnInit {

  p: number = 0;

  isDelete: boolean = false
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
  ngOnInit() {

    this.clearAll()

    Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / ARQC Calculators"))


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
  amountauth:any='';
  amountoth:any='';
  ccode:any='';
  tvr:any='';
  cucode:any='';
  transtype:any='';
  Unprenum:any='';
  aip:any='';
  atc:any='';
  cvr:any='';
  resp:any='';
  imk:any='';
  arqc:any='';
  arpc:any='';
  seq:any='0';
  transdate:Date=new Date();
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

    if (!this.pan ||!this.amountauth ||!this.seq ||!this.ccode ||!this.tvr ||!this.cucode ||!this.transtype ||!this.Unprenum ||!this.aip ||!this.atc ||!this.cvr ||!this.resp ||!this.imk ||!this.transdate){
      this.data.status='NOK';
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


  csu:any='';
  iad:any='';



}
