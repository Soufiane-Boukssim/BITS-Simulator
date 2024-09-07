import { Component, HostListener, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { filter } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  page__title: string = "";
  module: string = "";

  // isVisibleMenu:boolean = true
  isVisibleMenu: boolean = window.innerWidth > 768 ? false : true;

  visited: boolean = false
  colorizeSubMenu: boolean[] = [false, false, false, false, false, false, false, false, false];

  showSubMenusaAquirer = false;
  showbMenusOptions = true;
  showbMenusGlobalParameters = false;
  showbMenusTestingExecution = false;
  showbMenusTestingResult = false;
  showbMenusCasesScenarios = false;
  showbMenusTerminal = false;
  showbScripts = false;

  showbMenusNewMenu = false;

  /// ICC

  showbMenusSmartCardICC = true;
  showbMenusCardAnalyzeICC = false;

  /// TOOLS


  showbMenusCLIUtilsTOOLS = true;
  showbMenusEMVTag = false;
  showbMenuscryptoCalculators = false





  constructor(
    private router: Router,
    public globalService: GlobalService,
  ) {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe(() => {
        var baseRoute = this.router.url;
        // // console.log(baseRoute);
        switch (true) {
          case baseRoute?.startsWith('/user/iso'):
            // // console.log("iso")
            this.module = 'iso';
            break;
          case baseRoute?.startsWith('/user/icc'):
            // // console.log("icc")
            this.module = 'icc';
            break;
          case baseRoute?.startsWith('/user/atm'):
            // // console.log("atm")
            this.module = 'atm';
            break;
          case baseRoute?.startsWith('/user/pos'):
            // // console.log("pos")
            this.module = 'pos';
            break;
          case baseRoute?.startsWith('/user/tools'):
            // // console.log("tools")
            this.module = 'tools';
            break;
          default:
            this.module = '';
        }
      });

    this.toggle(2)


  }

  toggleSubMenuNewMenu() {
    this.showbMenusNewMenu = !this.showbMenusNewMenu;
    this.showbMenusTerminal = false;

    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }



  toggleSubMenusAquirer() {
    if (!this.visited) {

      this.showSubMenusaAquirer = !this.showSubMenusaAquirer;
      this.showbMenusOptions = false;
      this.showbMenusOptions = false;
      this.showbMenusTestingExecution = false;
      this.showbMenusCasesScenarios = false;

      this.router.navigate(["/user/iso"])


      this.showbMenusTestingResult = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[1] = false;
    } else {
      localStorage.setItem("displayPop", "true");
    }

  }

  toggleSubMenuScript() {
    this.showbScripts = !this.showbScripts;
   
  }

  toggleSubMenuTerminal() {

    this.showbMenusTerminal = !this.showbMenusTerminal;
    this.showbMenusNewMenu = false;
   

    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }

  toggleSubMenuOptions() {
    this.showbMenusOptions = !this.showbMenusOptions;
    this.showSubMenusaAquirer = false;
    this.showbMenusGlobalParameters = false;
    this.showbMenusTestingExecution = false;
    this.showbMenusTestingResult = false;
    this.showbMenusCasesScenarios = false;

    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }


  toggleSubMenuGlobalParameters() {
    this.showbMenusGlobalParameters = !this.showbMenusGlobalParameters;
    this.showbMenusOptions = false;
    this.showSubMenusaAquirer = false;
    this.showbMenusTestingExecution = false;
    this.showbMenusTestingResult = false;
    this.showbMenusCasesScenarios = false;

    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }


  toggleSubMenuTestingExecution() {
    this.showbMenusTestingExecution = !this.showbMenusTestingExecution;
    this.showbMenusOptions = false;

    this.showSubMenusaAquirer = false;
    this.showbMenusGlobalParameters = false;
    this.showbMenusTestingResult = false;
    this.showbMenusCasesScenarios = false;

    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }



  toggleSubMenuTestingResult() {
    this.showbMenusTestingResult = !this.showbMenusTestingResult;
    this.showbMenusOptions = false;
    this.showSubMenusaAquirer = false;
    this.showbMenusGlobalParameters = false;
    this.showbMenusTestingExecution = false;
    this.showbMenusCasesScenarios = false;

    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }


  toggleSubMenuCasesScenarios() {

    this.showbMenusCasesScenarios = !this.showbMenusCasesScenarios;
    this.showbMenusOptions = false;
    this.showSubMenusaAquirer = false;
    this.showbMenusGlobalParameters = false;
    this.showbMenusTestingExecution = false;
    this.showbMenusTestingResult = false;

    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }




  ///ICC ****************************************************************************

  toggleSubMenuSmartCardICC() {
    this.showbMenusSmartCardICC = !this.showbMenusSmartCardICC
    this.showbMenusCardAnalyzeICC = false

    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }


  toggleSubMenuCardAnalyzeICC() {
    this.showbMenusCardAnalyzeICC = !this.showbMenusCardAnalyzeICC
    this.showbMenusSmartCardICC = false

    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }




  ///Tools ****************************************************************************

  toggleSubMenuCLIUtilsTOOLS() {
    this.showbMenusCLIUtilsTOOLS = !this.showbMenusCLIUtilsTOOLS
    this.showbMenusEMVTag = false
    this.showbMenuscryptoCalculators = false


    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }


  toggleSubMenuEMVTag() {
    this.showbMenusEMVTag = !this.showbMenusEMVTag
    this.showbMenusCLIUtilsTOOLS = false
    this.showbMenuscryptoCalculators = false


    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }


  toggleSubMenucryptoCalculators() {
    this.showbMenuscryptoCalculators = !this.showbMenuscryptoCalculators
    this.showbMenusCLIUtilsTOOLS = false
    this.showbMenusEMVTag = false


    this.colorizeSubMenu[0] = false;
    this.colorizeSubMenu[1] = false;
  }




  toggle(index: number) {
    this.colorizeSubMenu[index] = !this.colorizeSubMenu[index];
    if (index == 0) {
      this.colorizeSubMenu[8] = false;
      this.colorizeSubMenu[9] = false;
      this.colorizeSubMenu[7] = false;
      this.colorizeSubMenu[6] = false;
      this.colorizeSubMenu[1] = false;
      this.colorizeSubMenu[2] = false;
      this.colorizeSubMenu[3] = false;
      this.colorizeSubMenu[4] = false;
      this.colorizeSubMenu[5] = false;
      this.colorizeSubMenu[6] = false;

    }
    if (index == 1) {
      this.colorizeSubMenu[8] = false;
      this.colorizeSubMenu[9] = false;
      this.colorizeSubMenu[7] = false;
      this.colorizeSubMenu[6] = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[2] = false;
      this.colorizeSubMenu[3] = false;
      this.colorizeSubMenu[4] = false;
      this.colorizeSubMenu[5] = false;
      this.colorizeSubMenu[6] = false;
    }
    if (index == 2) {
      this.colorizeSubMenu[8] = false;
      this.colorizeSubMenu[9] = false;
      this.colorizeSubMenu[7] = false;
      this.colorizeSubMenu[6] = false;
      this.colorizeSubMenu[1] = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[3] = false;
      this.colorizeSubMenu[4] = false;
      this.colorizeSubMenu[5] = false;
      this.colorizeSubMenu[6] = false;
    }
    if (index == 3) {
      this.colorizeSubMenu[8] = false;
      this.colorizeSubMenu[9] = false;
      this.colorizeSubMenu[7] = false;
      this.colorizeSubMenu[6] = false;
      this.colorizeSubMenu[1] = false;
      this.colorizeSubMenu[2] = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[4] = false;
      this.colorizeSubMenu[5] = false;
      this.colorizeSubMenu[6] = false;
    }
    if (index == 4) {
      this.colorizeSubMenu[8] = false;
      this.colorizeSubMenu[9] = false;
      this.colorizeSubMenu[7] = false;
      this.colorizeSubMenu[6] = false;
      this.colorizeSubMenu[1] = false;
      this.colorizeSubMenu[2] = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[3] = false;
      this.colorizeSubMenu[5] = false;
      this.colorizeSubMenu[6] = false;
    }
    if (index == 5) {
      this.colorizeSubMenu[8] = false;
      this.colorizeSubMenu[9] = false;
      this.colorizeSubMenu[7] = false;
      this.colorizeSubMenu[6] = false;
      this.colorizeSubMenu[1] = false;
      this.colorizeSubMenu[2] = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[4] = false;
      this.colorizeSubMenu[3] = false;
      this.colorizeSubMenu[6] = false;
    }
    if (index == 6) {
      this.colorizeSubMenu[8] = false;
      this.colorizeSubMenu[9] = false;
      this.colorizeSubMenu[7] = false;
      this.colorizeSubMenu[3] = false;
      this.colorizeSubMenu[2] = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[4] = false;
      this.colorizeSubMenu[5] = false;
      this.colorizeSubMenu[1] = false;
    }
    if (index == 7) {
      this.colorizeSubMenu[8] = false;
      this.colorizeSubMenu[9] = false;
      this.colorizeSubMenu[6] = false;
      this.colorizeSubMenu[3] = false;
      this.colorizeSubMenu[2] = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[4] = false;
      this.colorizeSubMenu[5] = false;
      this.colorizeSubMenu[1] = false;
    }
    if (index == 8) {
      this.colorizeSubMenu[9] = false;
      this.colorizeSubMenu[7] = false;
      this.colorizeSubMenu[6] = false;
      this.colorizeSubMenu[3] = false;
      this.colorizeSubMenu[2] = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[4] = false;
      this.colorizeSubMenu[5] = false;
      this.colorizeSubMenu[1] = false;
    }
    if (index == 9) {
      this.colorizeSubMenu[8] = false;
      this.colorizeSubMenu[7] = false;
      this.colorizeSubMenu[6] = false;
      this.colorizeSubMenu[3] = false;
      this.colorizeSubMenu[2] = false;
      this.colorizeSubMenu[0] = false;
      this.colorizeSubMenu[4] = false;
      this.colorizeSubMenu[5] = false;
      this.colorizeSubMenu[1] = false;
    }
  }


  ngOnInit(): void {

    this.isVisibleMenu = true
    if (localStorage.getItem('visited') == "true") {
      this.visited = true
    } else {
      this.visited = false
    }


    this.globalService.titleSubject.subscribe(val => {
      this.page__title = val
    })


    this.globalService.visibleMenuObs.subscribe(val => {
      // // console.log("val visibleMenuObs ==>",val);
      this.isVisibleMenu = val;
    })


    // const url = localStorage.getItem('Url');

    const url = this.router.url;

    console.log("url => " + url);
    this.chekURlISO(url)
    this.chekURlPOS(url)
    this.chekURlATM(url)
    this.chekURlICC(url)
    this.chekURlTOOLS(url)
  


  }



  hideMenu() {
    this.isVisibleMenu = false
    this.globalService.visibleMenuObs.next(this.isVisibleMenu);
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    // // console.log("window.innerWidth==>",window.innerWidth);

    if (window.innerWidth > 768) {
      this.isVisibleMenu = true;
    } else {
      this.isVisibleMenu = false;
    }
  }



  chekURlISO(url:any){
    if (url == "/user/iso/testing-result") {
      this.router.navigate(["/user/iso/testing-result"])
      this.showbMenusTestingExecution = true,
        this.toggle(1)
      this.showbMenusOptions = false;
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }
    if (url == "/user/iso/parameter-general") {
      this.router.navigate(["/user/iso/parameter-general"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(0)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');

    }

    if (url == "/user/iso/parameter-options") {
      this.router.navigate(["/user/iso/parameter-options"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(4)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }
    if (url == "/user/iso/global-params") {
      this.router.navigate(["/user/iso/global-params"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(1)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');

    }

    if (url == "/user/iso/") {
      this.router.navigate(["/user/iso/"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(2)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }

    if (url == "/user/iso/cases-scenarios") {
      this.router.navigate(["/user/iso/cases-scenarios"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(3)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }

    if (url == "/user/iso/testing-execution") {
      this.router.navigate(["/user/iso/testing-execution"])
      this.showbMenusTestingExecution = true,
        this.toggle(0)
      this.showbMenusOptions = false;
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }
   
  }



  chekURlPOS(url:any){
    if (url == "/user/pos/testing-result") {
      this.router.navigate(["/user/pos/testing-result"])
      this.showbMenusTestingExecution = true,
        this.toggle(1)
      this.showbMenusOptions = false;
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }
    if (url == "/user/pos/parameter-general") {
      this.router.navigate(["/user/pos/parameter-general"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(0)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');

    }

    if (url == "/user/pos/parameter-options") {
      this.router.navigate(["/user/pos/parameter-options"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(4)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }
    if (url == "/user/pos/global-params") {
      this.router.navigate(["/user/pos/global-params"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(1)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');

    }

    if (url == "/user/pos/") {
      this.router.navigate(["/user/pos/"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(2)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }

    if (url == "/user/pos/cases-scenarios") {
      this.router.navigate(["/user/pos/cases-scenarios"])
      this.showbMenusOptions = true;
      this.showbMenusTestingExecution = false
      this.toggle(3)
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }

    if (url == "/user/pos/testing-execution") {
      this.router.navigate(["/user/pos/testing-execution"])
      this.showbMenusTestingExecution = true,
        this.toggle(0)
      this.showbMenusOptions = false;
      this.showSubMenusaAquirer = false;
      localStorage.removeItem('Url');
    }
   
  }



   chekURlATM(url:any){

    if (url == "/user/atm") {
      this.router.navigate(["/user/atm"])
      this.showbMenusOptions = true,
        this.toggle(0)
      this.showbMenusTestingResult = false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }
    if (url == "/user/atm/communication") {
      this.router.navigate(["/user/atm/communication"])
      this.showbMenusOptions = true,
        this.toggle(1)
      this.showbMenusTestingResult = false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }

    if (url == "/user/atm/cases-senario") {
      this.router.navigate(["/user/atm/cases-senario"])
      this.showbMenusOptions = true;
      this.showbMenusNewMenu=true;
      this.showbMenusTerminal=false;
      this.toggle(6)
      this.showbMenusTestingResult = false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }

    if (url == "/user/atm/operation") {
      this.router.navigate(["/user/atm/operation"])
      this.showbMenusOptions = true;
      this.showbMenusNewMenu=true;
      this.showbMenusTerminal=false;
      this.toggle(3)
      this.showbMenusTestingResult = false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }
    

    if (url == "/user/atm/senario-execution") {
      this.router.navigate(["/user/atm/senario-execution"])
      this.showbMenusOptions = true;
      this.showbMenusNewMenu=true;
      this.showbMenusTerminal=false;
      this.toggle(4)
      this.showbMenusTestingResult = false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }

    if (url == "/user/atm/definition") {
      this.router.navigate(["/user/atm/definition"])
      this.showbMenusOptions = true;
      this.showbMenusTerminal=true;
      this.showbMenusNewMenu=false;
      this.toggle(5)
      this.showbMenusTestingResult = false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }

    if (url == "/user/atm/evenement") {
      this.router.navigate(["/user/atm/evenement"])
      this.showbMenusOptions = true;
      this.showbMenusTerminal=true;
      this.showbMenusNewMenu=false;
      this.toggle(6)
      this.showbMenusTestingResult = false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }
    if (url == "/user/atm/rejets") {
      this.router.navigate(["/user/atm/rejets"])
      this.showbMenusOptions = true;
      this.showbMenusTerminal=true;
      this.showbMenusNewMenu=false;
      this.toggle(0)
      this.showbMenusTestingResult = false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }


    if (url == "/user/atm/logs") {
      this.router.navigate(["/user/atm/logs"])
      this.showbMenusTestingResult = true;
     
      this.toggle(1)
      this.showbMenusOptions = false;
      this.showbMenusTerminal=false;
      this.showbMenusNewMenu=false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }
    if (url == "/user/atm/logs-params") {
      this.router.navigate(["/user/atm/logs-params"])
      this.showbMenusTestingResult = true;
     
      this.toggle(0)
      this.showbMenusOptions = false;
      this.showbMenusTerminal=false;
      this.showbMenusNewMenu=false;
      this.showbMenusTestingExecution = false;
      localStorage.removeItem('Url');
    }
   

    if (url == "/user/atm/scenario") {
      this.router.navigate(["/user/atm/scenario"])
      this.showbMenusTestingExecution = true;
     
      this.toggle(1)
      this.showbMenusOptions = false;
      this.showbMenusTerminal=false;
      this.showbMenusNewMenu=false;
      this.showbMenusTestingResult = false;
      localStorage.removeItem('Url');
    }

    if (url == "/user/atm/execution-script") {
      this.router.navigate(["/user/atm/execution-script"])
      this.showbMenusTestingExecution = true;
     
      this.toggle(0)
      this.showbMenusOptions = false;
      this.showbMenusTerminal=false;
      this.showbMenusNewMenu=false;
      this.showbMenusTestingResult = false;
      localStorage.removeItem('Url');
    }
   

  }


  chekURlICC(url:any){

    if (url == "/user/icc/emv-dictionnary") {
      this.router.navigate(["/user/icc/emv-dictionnary"])
      this.showbMenusSmartCardICC = true,
        this.toggle(0)
      this.showbMenusCardAnalyzeICC = false;
      
    }

    if (url == "/user/icc/card-explorer") {
      this.router.navigate(["/user/icc/card-explorer"])
      this.showbMenusSmartCardICC = true,
        this.toggle(1)
      this.showbMenusCardAnalyzeICC = false;
      
    }

    if (url == "/user/icc/card-analyzer") {
      this.router.navigate(["/user/icc/card-analyzer"])
      this.showbMenusCardAnalyzeICC = true,
        this.toggle(0)
      this.showbMenusSmartCardICC = false;
      
    }
    if (url == "/user/icc/cpa-analyzer") {
      this.router.navigate(["/user/icc/cpa-analyzer"])
      this.showbMenusCardAnalyzeICC = true,
        this.toggle(1)
      this.showbMenusSmartCardICC = false;
      
    }

  }




  chekURlTOOLS(url:any){

    if (url == "/user/tools/cli-utils") {
      this.router.navigate(["/user/tools/cli-utils"])
      this.showbMenusCLIUtilsTOOLS = true,
        this.toggle(0)
      this.showbMenusEMVTag = false;
      this.showbMenuscryptoCalculators=false
    }

    if (url == "/user/tools/emv-tlv-parser") {
      this.router.navigate(["/user/tools/emv-tlv-parser"])
      this.showbMenusEMVTag = true,
        this.toggle(0)
      this.showbMenusCLIUtilsTOOLS = false;
      this.showbMenuscryptoCalculators=false
    }

    if (url == "/user/tools/emv-tlv-parser") {
      this.router.navigate(["/user/tools/emv-tlv-parser"])
      this.showbMenusEMVTag = true,
        this.toggle(3)
      this.showbMenusCLIUtilsTOOLS = false;
      this.showbMenuscryptoCalculators=false
      
    }
    if (url == "/user/tools") {
      this.router.navigate(["/user/tools"])
      this.showbMenusEMVTag = true,
        this.toggle(1)
      this.showbMenusCLIUtilsTOOLS = false;
      this.showbMenuscryptoCalculators=false
    }
    
    if (url == "/user/tools/iso-8583-bitmap") {
      this.router.navigate(["/user/tools/iso-8583-bitmap"])
      this.showbMenuscryptoCalculators = true,
        this.toggle(0)
      this.showbMenusCLIUtilsTOOLS = false;
      this.showbMenusEMVTag=false
    }

    if (url == "/user/tools/des-calculators") {
      this.router.navigate(["/user/tools/des-calculators"])
      this.showbMenuscryptoCalculators = true,
        this.toggle(9)
      this.showbMenusCLIUtilsTOOLS = false;
      this.showbMenusEMVTag=false
    }
    if (url == "/user/tools/converters") {
      this.router.navigate(["/user/tools/converters"])
      this.showbMenuscryptoCalculators = true,
        this.toggle(1)
      this.showbMenusCLIUtilsTOOLS = false;
      this.showbMenusEMVTag=false
    }
    if (url == "/user/tools/pin-block-calculators") {
      this.router.navigate(["/user/tools/pin-block-calculators"])
      this.showbMenuscryptoCalculators = true,
        this.toggle(6)
      this.showbMenusCLIUtilsTOOLS = false;
      this.showbMenusEMVTag=false
    }

   if (url == "/user/tools/luhn-algorithm") {
    this.router.navigate(["/user/tools/luhn-algorithm"])
    this.showbMenuscryptoCalculators = true,
      this.toggle(4)
    this.showbMenusCLIUtilsTOOLS = false;
    this.showbMenusEMVTag=false
  }

  if (url == "/user/tools/card-security-values") {
    this.router.navigate(["/user/tools/card-security-values"])
    this.showbMenuscryptoCalculators = true,
      this.toggle(3)
    this.showbMenusCLIUtilsTOOLS = false;
    this.showbMenusEMVTag=false
  }

  if (url == "/user/tools/arqc-calculators") {
    this.router.navigate(["/user/tools/arqc-calculators"])
    this.showbMenuscryptoCalculators = true,
      this.toggle(5)
    this.showbMenusCLIUtilsTOOLS = false;
    this.showbMenusEMVTag=false
  }
  
  if (url == "/user/tools/key-block-decoder") {
    this.router.navigate(["/user/tools/key-block-decoder"])
    this.showbMenuscryptoCalculators = true,
      this.toggle(7)
    this.showbMenusCLIUtilsTOOLS = false;
    this.showbMenusEMVTag=false
  }
  
  if (url == "/user/tools/env-misc") {
    this.router.navigate(["/user/tools/env-misc"])
    this.showbMenuscryptoCalculators = true,
      this.toggle(7)
    this.showbMenusCLIUtilsTOOLS = false;
    this.showbMenusEMVTag=false
  }



  }

}
