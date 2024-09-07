import { DatePipe } from '@angular/common';
import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-dashboard-header',
  templateUrl: './dashboard-header.component.html',
  styleUrls: ['./dashboard-header.component.scss']
})
export class DashboardHeaderComponent implements OnInit {
  isVisible: boolean = false
  currentUrl: string = ""
  protocol: string = ""
  fullUserName: string | null = ""
  user: any
  currentDate = new Date;
  language = "";
  englishFDisplay = false;
  spanishFDisplay = false;
  frenshFDisplay = false;
  formattedDate: string = "";


  isVisibleMenu: boolean = false


  // isVisibleMenu_btn_close: boolean = true; 
  isVisibleMenu_btn_close: boolean = window.innerWidth > 768 ? true : false;

  constructor(
    private auth: AuthService,
    private router: Router,
    private globalService: GlobalService,
    private datePipe: DatePipe
  ) {
    globalService.protocol.subscribe(val => {
      this.protocol = val
    })


  }
  ngOnInit(): void {
    this.user = localStorage.getItem('user');
    this.user = JSON.parse(this.user);
    this.fullUserName = this.user.userName;
    this.language = this.user.languageCode;
    if (this.language == "en") {
      this.englishFDisplay = true;
    }
    else if (this.language == "fr") {
      this.frenshFDisplay = true;
    }
    else {
      this.spanishFDisplay = true;
    }

    this.globalService.visibleMenuObs.subscribe(val => {
      this.isVisibleMenu_btn_close = val;
    })

    // console.log('this.fullUserName: ', this.fullUserName);
    this.currentDate = new Date();
    const formattedDateOrNull = this.datePipe.transform(this.currentDate, 'yyyy-MM-dd HH:mm:ss');
    this.formattedDate = formattedDateOrNull !== null ? formattedDateOrNull : '';
  }
  toggleToolTip() {
    this.isVisible = !this.isVisible
  }
  hideToolTip() {
    this.isVisible = false
  }
  logout() {
    this.globalService.currentRoute = ""
    this.auth.logout()
  }

  goToChangePass() {
    this.router.navigate(['/change-password']);
  }

  @HostListener('document:click', ['$event.target'])
  onClickOutside(targetElement: EventTarget): void {
    if (!this.isVisible) {
      return;
    }

    const div = document.querySelector('div[appClickOutside]');
    if (div && !div.contains(targetElement as Node)) {
      this.hideToolTip();
    }
  }
  getProtocolLogo(protocol: string): string {
    let prot = protocol
    if (!protocol) {
      prot = localStorage.getItem('protocol') as string
    }
    switch (prot) {
      case 'SS':
        return 'assets/icons/Switch_logo.png';
      case 'MC':
        return 'assets/icons/mastercard.png';
      case 'MD':
        return 'assets/icons/mastercard.png';
      case 'VS':
        return 'assets/icons/visalogo.png';
      case 'VB':
        return 'assets/icons/visalogo.png';

      case 'PO':
        return 'assets/icons/pos.png';




      default:
        return '';
    }
  }



  toggleMnnu() {
    this.isVisibleMenu = !this.isVisibleMenu

    this.globalService.visibleMenuObs.next(this.isVisibleMenu);
  }
  hideMenu() {
    this.isVisibleMenu = false
  }



  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    // Vérifiez la largeur de la fenêtre ici et mettez à jour isVisibleMenu en conséquence
    if (window.innerWidth > 768) {
      this.isVisibleMenu_btn_close = true;
    } else {
      this.isVisibleMenu_btn_close = false;
    }
  }


}
