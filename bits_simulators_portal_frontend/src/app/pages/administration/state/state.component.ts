import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-state',
  templateUrl: './state.component.html',
  styleUrls: ['./state.component.scss']
})
export class StateComponent implements OnInit {

  language: string = '';
  en: boolean = false;
  fr: boolean = false;
  esp: boolean = false;

  constructor(private globalService: GlobalService) {}

  ngOnInit(): void {
    const user = localStorage.getItem('user');
    if (user) {
      const parsedUser = JSON.parse(user);
      this.language = parsedUser.languageCode;

      this.en = this.language === 'en';
      this.fr = this.language === 'fr';
      this.esp = this.language === 'esp';

      // Update global title based on language
      Promise.resolve().then(() => {
        const title = "Smart ATM / NDC / State";
        this.globalService.titleSubject.next(title);
      });
    }
  }
}