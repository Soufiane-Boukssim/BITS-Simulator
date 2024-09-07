import { Component, HostListener } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-page-layou-admin',
  templateUrl: './page-layou-admin.component.html',
  styleUrls: ['./page-layou-admin.component.scss']
})
export class PageLayouAdminComponent {
  page__title: string = ""
  isVisibleMenu: boolean = window.innerWidth > 768 ? true : false;
  isVisiblePhone: boolean = false

  constructor(
    private globalService: GlobalService
  ) { }
  ngOnInit(): void {

    this.isVisibleMenu = true;
    this.onResize();
    // // console.log("test  isVisibleMenu layout ");


    this.globalService.titleSubject.subscribe(val => {
      this.page__title = val
    })


    this.globalService.visibleMenuObs.subscribe(val => {
      this.isVisibleMenu = val;

      // // console.log("isVisibleMenu ==>",this.isVisibleMenu);
      // // console.log("isVisiblePhone ===>",this.isVisiblePhone);


    })

  }


  @HostListener('window:resize', ['$event'])
  onResize(event?: any) {
    //// // console.log("window:innerWidth ==>",window.innerWidth);

    if (window.innerWidth > 768) {
      this.isVisibleMenu = true;
      this.isVisiblePhone = true;
    } else {
      this.isVisibleMenu = false;
      this.isVisiblePhone = false
    }
  }



}
