import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-ui-mockup',
  templateUrl: './ui-mockup.component.html',
  styleUrls: ['./ui-mockup.component.scss']
})
export class UiMockupComponent implements OnInit {
  constructor(
    private globalService:GlobalService
  ){}
  ngOnInit(): void {
    Promise.resolve().then(() => this.globalService.titleSubject.next("UI Mockup"))
  }
  tab_1:boolean = true
  tab_2:boolean = false
  tab_3:boolean = false
    not_owned:any = [
    {
    'id':'f45r8r6',
    'title':'ISO',
    'checked':false
    },
    {
    'id':'e4ef44e',
    'title':'POS',
    'checked':false
    },
  ]

  owned:any = [
    {
    'id':'e47g8fe',
    'title':'ATM',
    'checked':false
    }
  ]
  open_tab(index:number){
    if(index == 0){
      this.tab_1 = true
      this.tab_2 = false
      this.tab_3 = false
    }else if(index == 1){
      this.tab_1 = false
      this.tab_2 = true
      this.tab_3 = false
    }else{
      this.tab_1 = false
      this.tab_2 = false
      this.tab_3 = true
    }

  }



}
