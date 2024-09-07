import { Component } from '@angular/core';
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-key-block-decoder',
  templateUrl: './key-block-decoder.component.html',
  styleUrls: ['./key-block-decoder.component.scss']
})
export class KeyBlockDecoderComponent {

  p: number = 0;

  isDelete: boolean = false
  constructor(

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


    //Promise.resolve().then(() => this.globalService.titleSubject.next("General Options"))


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

  key:any='';


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

    if (!this.key){
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
}
