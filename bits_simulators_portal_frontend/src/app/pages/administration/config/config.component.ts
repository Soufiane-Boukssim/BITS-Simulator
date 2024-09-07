import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-config',
  templateUrl: './config.component.html',
  styleUrls: ['./config.component.scss']
})
export class ConfigComponent implements OnInit {
  language: string = '';
  en: boolean = false;
  fr: boolean = false;
  esp: boolean = false;

  constructor(private globalService: GlobalService) {}

  ngOnInit(): void {
    const user1 = localStorage.getItem('user');
    if (user1) {
      const parsedUser = JSON.parse(user1);
      this.language = parsedUser.languageCode;

      if (this.language === "en") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Smart ATM / NDC / Configuration Parameters / Configuration"));
        this.en = true;
      } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Smart ATM / NDC / Configuration Parameters / Configuration"));
        this.fr = true;
      } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Smart ATM / NDC / Configuration Parameters / Configuration"));
        this.esp = true;
      }
    }
  }
}



