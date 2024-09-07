import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { environment } from 'src/env/environement';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'client-side';
  constructor(
    private translate: TranslateService
    ){
    translate.setDefaultLang(localStorage.getItem('lang') as string);
  }
  changesInRoute(){
    // console.log('route changed');
    this.translate.setDefaultLang(localStorage.getItem('lang') as string);
  }
}
