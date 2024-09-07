import { Component } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-converters',
  templateUrl: './converters.component.html',
  styleUrls: ['./converters.component.scss']
})
export class ConvertersComponent {

  p: number = 0;
  
  user1:any

  fr = false;
  en = false;
  esp = false;
  language:  string = ""

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


    //Promise.resolve().then(() => this.globalService.titleSubject.next("General Options"))
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / Converters"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / Converters"));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / Converters"));
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
        this.hex=""
        this.ascii=""
        this.tab_1 = true
        this.p = 0
        break;
      case 1:
        this.hex=""
        this.ascii=""
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

  ascii:any='';
  hex:any='';


  data:any={
    status:'',
    message:''
  }
  messages:any=[]
  contentVisible: boolean = true;

  toggleContent() {
    this.contentVisible = !this.contentVisible;
  }

  asciiTohex() {
    this.hex=""
    if (this.ascii != null && this.ascii !== '') {
      this.hex = this.ascii.split('')
                           .map((char: { charCodeAt: (arg0: number) => { (): any; new(): any; toString: { (arg0: number): string; new(): any; }; }; }) => char.charCodeAt(0).toString(16).padStart(2, '0'))
                           .join(' ');
    } else {
      this.hex = '';
    }
  }


  hexToascii() {
    this.ascii = '';
    if (this.hex != null && this.hex !== '') {
      try {
        // Split the hex string by spaces and convert each hex pair to an ASCII character
        this.ascii = this.hex.split(' ')
                             .map((hexChar: string) => String.fromCharCode(parseInt(hexChar, 16)))
                             .join('');
      } catch (e) {
        console.error('Invalid HEX input', e);
        this.ascii = 'Invalid HEX input';
      }
    } else {
      this.ascii = '';
    }
  }

}
