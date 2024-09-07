import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/services/global.service';
import {PosGlobalService} from "../../services/pos-global.service";
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  constructor(
    private globalService: GlobalService,
    private posGlobalService: PosGlobalService,
    private router: Router,
    private auth:AuthService,
    private authenticationService: AuthService,
  ) { }
  protocol: string = "";
  selectedProtocol: string = "";
  User_Page: any[] = []; 
  iso_Protocole: any[] = []; 
  userCode:any=''


  alertBody: any = {
    message: '',
    status: '000',
    open: false
  }
  message = "";


  user1:any

  fr = false;
  en = false;
  esp = false;
  language:  string = ""



  ngOnInit(): void {
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("/Home"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("/Home"));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("/Home"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }
    
    localStorage.setItem('protocol', '')
    this.globalService.protocolSubject.next('')

    this.auth.user.subscribe(val => {
      this.userCode = val.userName

      if (val.userName !="") {
        this.fetchUserRolePage();
      
      }
    })
    this.fetchISOProtocole();

  }


  fetchUserRolePage() {
    // // // console.log("---> userCodeV2,",this.userCode);    
    let body = {
      "user_code":this.userCode
    } 
    // this.requestBody
    this.globalService.getUserRolePage(body).subscribe(
        (response) => {
            this.User_Page = response.result;
           // // console.log("--->User_Page:",response.result);

          
        },
        (error) => {
            console.error('Error fetching User_Page:', error);
        }
    );
}




fetchISOProtocole() {
 
  // this.requestBody
  this.globalService.getProtocole().subscribe(
      (response) => {
          this.iso_Protocole = response.result;
          
          // // console.log("iso_Protocole ,",this.iso_Protocole);
          
      },
      (error) => {
          console.error('Error fetching iso_Protocole:', error);
      }
  );
}



getFilteredHabilitation(habilitationCode: string): any[] {   
  return this.User_Page.filter(habilitation => habilitation.habilitationCode === habilitationCode);

}

  changeRoute(route: string) {
    // // console.log("changeRoute pos");
    // // console.log("route: " , route);
    
    
    if(route==="/pos") {
      localStorage.setItem('protocol', "PO")
      // this.posGlobalService.protocolSubject.next("P")


    }
    this.globalService.currentRoute = route
  }
  openModal() {
    this.modal?.nativeElement.showModal()
  }
  closeModal() {
    this.modal?.nativeElement.close()
  }
  chooseProtocol() {
    this.globalService.protocolSubject.next(this.protocol)
    localStorage.setItem('protocol', this.protocol)

    this.globalService.protocol.subscribe(val => {
      // // console.log('val: ', val);

    })

  }


  navigateTo() {
    if (this.protocol) {
      this.router.navigate(['/user/iso'])
    } else {
      // alert('please choose a protocol')

      this.alertBody.status = "please choose a protocol"
      if(this.en){
        this.alertBody.message = "Please choose a protocol"
      }
      if(this.fr){
        this.alertBody.message = "Veuillez choisir un protocole"
      }
      if(this.esp){
        this.alertBody.message = "Please choose a protocol"
      }
      this.alertBody.open = true
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);
    }
  }
}
