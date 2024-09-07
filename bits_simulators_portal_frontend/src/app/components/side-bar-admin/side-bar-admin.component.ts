import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-side-bar-admin',
  templateUrl: './side-bar-admin.component.html',
  styleUrls: ['./side-bar-admin.component.scss']
})
export class SideBarAdminComponent {
  page__title: string = ""

  isVisibleMenu: boolean = window.innerWidth > 768 ? false : true;

  colorizeSubMenu: boolean[] = [false, false, false, false, false, false, false, false, false, false, false];
  constructor(
    private auth: AuthService,
    private router: Router,
    private globalService: GlobalService
  ) { }
  ngOnInit(): void {
    this.isVisibleMenu = true
    
    this.globalService.titleSubject.subscribe(val => {
      this.page__title = val
    })

    this.globalService.visibleMenuObs.subscribe(val => {
      this.isVisibleMenu = val;
    })


    const currentUrl = this.router.url;

    console.log("url = " + currentUrl);
    
    // Vérifiez si l'URL actuelle correspond à une URL spécifique dans votre menu
    if (currentUrl.includes('/administration/management')) {
      this.showManagSubMenus = true;
      this.toggle(0);
    } else if (currentUrl.includes('/administration/general-options-admin') ) {
      this.showParamSubMenus = true;
      this.toggle(0);
    } else if ( currentUrl.includes('/administration/bank')){
      this.showParamSubMenus = true;
      this.toggle(1);
      
    } else if (currentUrl.includes('/administration/smart-atm')) {
      this.showSmartAtmSubMenus = true;
      this.toggleSubMenuSmartAtm();
    }
    }

  showManagSubMenus = false;
  showParamSubMenus = false;

  toggleSubMenusManagement() {
    this.showManagSubMenus = !this.showManagSubMenus;
    this.showParamSubMenus = false;
    this.showSmartAtmSubMenus = false;
  }

  toggleSubMenuParam() {
    this.showParamSubMenus = !this.showParamSubMenus;
    this.showManagSubMenus = false;
    this.showSmartAtmSubMenus = false;
  }

  // toggle(index: number) {
  //   this.colorizeSubMenu[index] = !this.colorizeSubMenu[index];
  //   if (index == 0) {
  //     this.colorizeSubMenu[1] = false;
  //   }
  //   if (index == 1) {
  //     this.colorizeSubMenu[0] = false;
  //   }
  // }

  ReturnHome() {
    this.router.navigate(['administration/management'])
  }


  hideMenu() {
    this.isVisibleMenu = false
    this.globalService.visibleMenuObs.next(this.isVisibleMenu);
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    if (window.innerWidth > 768) {
      this.isVisibleMenu = true;
    } else {
      this.isVisibleMenu = false;
    }
  }


// test
showMenuConfiguration=false
showMenuNDC=false
showSmartAtmSubMenus = false;
showNdcSubMenus = false;
showConfigParamsSubMenus = false;
colorizeSubMenuNdc: boolean[] = [false, false, false,false,false,false,false];

toggleSubMenuSmartAtm() {
  this.showSmartAtmSubMenus = !this.showSmartAtmSubMenus;
  this.showManagSubMenus=false;
  this.showMenuNDC=false
  this.showMenuConfiguration=false
  this.showParamSubMenus=false;
}

toggleSubMenuConfigParams() {
  this.showConfigParamsSubMenus = !this.showConfigParamsSubMenus;
  this.showNdcSubMenus = false;
  this.showMenuNDC=false
  this.showMenuConfiguration=false
}

toggle(index: number) {
  this.colorizeSubMenu[index] = !this.colorizeSubMenu[index];
  if (index == 0) {
    this.colorizeSubMenu[1] = false;
    this.colorizeSubMenu[2] = false;
    this.colorizeSubMenu[3] = false;
    this.colorizeSubMenu[4] = false;
    this.colorizeSubMenu[5] = false;
    this.colorizeSubMenu[6] = false;
    this.showMenuNDC=false;
    this.showMenuConfiguration=false
  }
  if (index == 1) {
    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[2] = false;
    this.colorizeSubMenu[3] = false;
    this.colorizeSubMenu[4] = false;
    this.colorizeSubMenu[5] = false;
    this.colorizeSubMenu[6] = false;

  }
  if (index == 2) {
    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
    this.colorizeSubMenu[3] = false;
    this.colorizeSubMenu[4] = false;
    this.colorizeSubMenu[5] = false;
    this.colorizeSubMenu[6] = false;
  }
  if (index == 3) {
    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
    this.colorizeSubMenu[2] = false;
    this.colorizeSubMenu[4] = false;
    this.colorizeSubMenu[5] = false;
    this.colorizeSubMenu[6] = false;
    this.colorizeSubMenu[7] = false;
    this.colorizeSubMenu[8] = false;
    this.showMenuConfiguration=false;
  }
  if (index == 4) {
    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
    this.colorizeSubMenu[3] = false;
    this.colorizeSubMenu[2] = false;
    this.colorizeSubMenu[5] = false;
    this.colorizeSubMenu[6] = false;
    this.colorizeSubMenu[7] = false;
    this.colorizeSubMenu[8] = false;
    this.showMenuConfiguration=false;
  }
  if (index == 5) {
    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
    this.colorizeSubMenu[3] = false;
    this.colorizeSubMenu[2] = false;
    this.colorizeSubMenu[4] = false;
    this.colorizeSubMenu[6] = false;
    this.colorizeSubMenu[7] = false;
    this.colorizeSubMenu[8] = false;
    this.showMenuConfiguration=false;
  }
  if (index == 6) {
    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
    this.colorizeSubMenu[3] = false;
    this.colorizeSubMenu[2] = false;
    this.colorizeSubMenu[4] = false;
    this.colorizeSubMenu[5] = false;
    this.colorizeSubMenu[7] = false;
    this.colorizeSubMenu[8] = false;
  }
  if (index == 7) {
    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
    this.colorizeSubMenu[3] = false;
    this.colorizeSubMenu[2] = false;
    this.colorizeSubMenu[4] = false;
    this.colorizeSubMenu[5] = false;
    this.colorizeSubMenu[6] = false;
    this.colorizeSubMenu[8] = false;
  }
  if (index == 8) {
    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
    this.colorizeSubMenu[3] = false;
    this.colorizeSubMenu[2] = false;
    this.colorizeSubMenu[4] = false;
    this.colorizeSubMenu[5] = false;
    this.colorizeSubMenu[6] = false;
    this.colorizeSubMenu[7] = false;    
  }
}


toggleNDCMenu(){
  this.showMenuNDC = !this.showMenuNDC;
  this.colorizeSubMenu[0] = false;
  this.colorizeSubMenu[1] = false;
  this.showMenuConfiguration=false;
}


toggleConfigurationMenu(){
  this.showMenuConfiguration = !this.showMenuConfiguration;
  this.colorizeSubMenu[0] = false;
  this.colorizeSubMenu[1] = false;
  this.colorizeSubMenu[3] = false;
  this.colorizeSubMenu[4] = false;
  this.colorizeSubMenu[5] = false;

}



//test
}