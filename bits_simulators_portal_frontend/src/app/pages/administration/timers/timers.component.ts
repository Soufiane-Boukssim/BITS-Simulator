import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-timers',
  templateUrl: './timers.component.html',
  styleUrls: ['./timers.component.scss']
})
export class TimersComponent implements OnInit {
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
        Promise.resolve().then(() => this.globalService.titleSubject.next("Smart ATM / NDC / Configuration Parameters / Timers"));
        this.en = true;
      } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Smart ATM / NDC / Configuration Parameters / Timers"));
        this.fr = true;
      } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Smart ATM / NDC / Configuration Parameters / Timers"));
        this.esp = true;
      }
    }
  }
}



