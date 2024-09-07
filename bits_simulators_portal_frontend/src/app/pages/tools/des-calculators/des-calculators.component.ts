import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-des-calculators',
  templateUrl: './des-calculators.component.html',
  styleUrls: ['./des-calculators.component.scss']
}) 
export class DesCalculatorsComponent implements OnInit{


  user1:any

  fr = false;
  en = false;
  esp = false;
  language:  string = ""
  protocol: string = "";
  bankCode: any = "";
  isDelete: boolean = false

  data=""
  key="";
  cipheredData=""
  decipheredData=""
   
  constructor(
    private globalService: GlobalService,
    private auth: AuthService,
  ){}

  ngOnInit(): void {
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / DES Encryption"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculatrices / Chiffrement DES"));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / DES Encryption"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }
  }


  process() {
    this.cipheredData=""
    this.decipheredData=""
    const body = {
      "data": this.data,
      "key": this.key
    };
  
    if (this.data !== "" && this.key !== "") {
      this.globalService.processDesMP(body).subscribe(
        (res: any) => {
          if (res.respCode === "000") {
            this.cipheredData = res.result.cipheredData;
            this.decipheredData = res.result.decipheredData;
          
          
          } else {
            console.error("Error:", res.respMsg);
            // Gérer les erreurs si nécessaire
          }
        },
        error => {
          console.error("API Error:", error);
          // Gérer les erreurs de l'appel API
        }
      );
    } else {
      console.error("Data or Key is empty.");
    }
  }
  
  


}
