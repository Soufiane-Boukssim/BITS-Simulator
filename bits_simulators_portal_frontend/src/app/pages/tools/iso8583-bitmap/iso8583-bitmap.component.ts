import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-iso8583-bitmap',
  templateUrl: './iso8583-bitmap.component.html',
  styleUrls: ['./iso8583-bitmap.component.scss']
})
export class Iso8583BitmapComponent implements OnInit {

  p: number = 0;

  isDelete: boolean = false
  results: any[] = [];

  user1:any

  fr = false;
  en = false;
  esp = false;
  language:  string = ""
  protocol: string = "";
  bankCode: any = "";

  dataHex=""
  alertBody: any = {
    message: '',
    status: '000',
    open: false
  }

  data_parser:any = [];
  constructor(private formBuilder: FormBuilder,
     private globalService: GlobalService) { }

  

  fillResults() {
    const itemCount = 32; // You can change this to the desired number of items
    for (let i = 0; i < itemCount; i++) {
      this.results.push({name: `Item${i}`, selected: false});
    }
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
  ngOnInit() {


    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / ISO 8583"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculatrices / ISO 8583"));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators /  ISO 8583"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }


    this.clearAll()
    this.fillResults();
    this.noteGrouped = this.chunkArray(this.results.slice(0, 16), 4);
    this.printedGrouped = this.chunkArray(this.results.slice(16, 32), 4);

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
  bitmap:any="";
  note: boolean = true;
  printer: boolean = false;





  messages: any = [];


  dateTime:string = ""

  selectTab(index: number) {
    this.note = false
    this.printer = false
    switch (index) {
      case 0:
        this.note = true

        break;
      case 1:
        this.printer = true

        break;

      default:
        break;
    }
  }

  noteGrouped: any[][] = [];
  printedGrouped: any[][] = [];
  chunkArray(array: any[], chunkSize: number): any[][] {
    const results: any[][] = [];
    for (let i = 0; i < array.length; i += chunkSize) {
      results.push(array.slice(i, i + chunkSize));
    }
    return results;
  }







  messageParcerSid(){
    this.data_parser=[]
    const body={
      "data":this.dataHex
    }

    this.globalService.messageParserSid(body).subscribe(res=>{
        if (res.respCode==="000") {
          this.data_parser=res.result

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
              this.alertBody.message = "error de data parcer";
          }
          if (this.fr) {
              this.alertBody.message = "error de data parcer";
          }
          if (this.esp) {
              this.alertBody.message ="error de data parcer";
          }
          this.alertBody.open = true;
          setTimeout(() => {
              this.alertBody.open = false;
          }, 3000);

        }
    })
  }
}
