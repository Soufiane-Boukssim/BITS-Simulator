import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl} from "@angular/forms";
import {TooltipPosition} from "@angular/material/tooltip";
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-card-security-values',
  templateUrl: './card-security-values.component.html',
  styleUrls: ['./card-security-values.component.scss']
})
export class CardSecurityValuesComponent implements OnInit {

  p: number = 0;

  isDelete: boolean = false
  constructor(
   private globalService: GlobalService,
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

    this.authcode='0'
    this.authmetd='0'
    this.keyset='0'
    this.eci='0'
    this.protocolv='0'
    Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / Card Security Values"))


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
  result:any='';
  key:any='';
  protocolv:any='';
  currcode:any='';
  amount:any='';
  positionOptions: TooltipPosition[] = ['after', 'before', 'above', 'below', 'left', 'right'];
  after=new FormControl(this.positionOptions[4]);
  authcode:any='';
  authmetd:any='';
  keyset:any='';
  eci:any='';
  pan:any='';
  seed:number= 0;
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

    if (!this.pan){
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


  expdate:any=""
  servcode:any=""
  cvka:any=""
  cvkb:any=""
  Result:any=""




  cavv:any=''





  avv:any='';
  merchname:any='';
  mode:any='';
  acsid:any='';
  keyid:any='';
  sequencenum:any='';





  servicecode:any='';
  atc:any='';
  mdk:any='';
  dcvv:any='';
}
