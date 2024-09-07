import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import { WebsocketService } from 'src/app/services/icc/websocket.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent  implements OnInit {


  user1:any
  language: string = ""
  fr = false;
  en = false;
  esp = false;


  data_profil:any[]=[]
  data_initiateApplication:any[]=[]

  codeProfile:string = "";


  dataCARD:{} = {};
  cardApkSelect:string = "";


  alertBody:any = {
    message:'',
    status:'',
    open:false
  }


  public checkedTags: any[] = [];
showImage: boolean = false;


  tab0:boolean = true;
  tab1:boolean = false;
  tab2:boolean = false;
  tab3:boolean = false;
  tab4:boolean = false;
  tab5:boolean = false;
  tab6:boolean = false;
  tab7:boolean = false;
  tab8:boolean = false;
  tab9:boolean = false;
  tab10:boolean = false;
  tab11:boolean = false;

  tab_Decision:boolean = true
  tab_Script:boolean = false

  constructor(
    private globalService:GlobalService,
    private auth:AuthService,
    private websocketService: WebsocketService
   
  ){}

  ngOnInit(): void {
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      // console.log("en  Card Analyze ");
      
      Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze / EMV CPA /Scenario "));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze  / EMV CPA / Scenario  "));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze  / EMV CPA / Scenario "));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }

    this.getAllProfile();

  }


  open_tabScript(tab:number){
    this.tab_Script = false;
    this.tab_Decision=false
  
  
    switch (tab) {
      case 1:
        this.tab_Script = true
        break;
        
        default:
          this.tab_Decision = true
          break;
      }
    }


  openTab(index:any){
    this.tab0 = false;
    this.tab1 = false;
    this.tab2 = false;
    this.tab3 = false;
    this.tab4 = false;
    this.tab5 = false;
    this.tab6 = false;
    this.tab7 = false;
    this.tab8 = false;
    this.tab9 = false;
    this.tab10 = false;
    this.tab11 = false;
   
     
    switch (index) {
      case 1:
        this.tab1 = true
       
        break;
      case 2:
        this.tab2 = true
      
        break;
      case 3:
        this.tab3 = true
       
        break;
      case 4:
        this.tab4 = true
      
        break;
      case 5:
        this.tab5 = true
      
        break;
      case 6:
        this.tab6 = true
     
        break;
      case 7:
        this.tab7 = true
        
        break;
      case 8:
        this.tab8 = true
     
        break;
      case 9:
        this.tab9 = true
     
        break;
      case 10:
        this.tab10 = true
      
        break;
      case 11:
        this.tab11 = true
        break;
      default:
        break;
    }

    }


 
    getAllProfile(){
      this.globalService.getAllProfil().subscribe(res => {
        this.data_profil=res.result
        // console.log('--------------> :',res.result);

        const activeProfile = this.data_profil.find(profile => profile.activeProfile === 'Y');
        if (activeProfile) {
            this.codeProfile = activeProfile.codeProfile;
        }
        
      })
    }

  



  startProcess() {
    let delay = 0; // Initial delay

    // Define switch cases for each tab
    for (let i = 1; i <= 11; i++) {
        switch (i) {
            case 1:
                setTimeout(() => {
                    this.resetTabs();
                    this.tab1 = true;
                    this.tab0 = false;
                }, delay);
                break;
            case 2:
                setTimeout(() => {
                    this.resetTabs();
                    this.tab2 = true;
                }, delay ); // Adding 4 seconds to previous delay
                break;
            case 3:
                setTimeout(() => {
                    this.resetTabs();
                    this.tab3 = true;
                }, delay ); // Adding 5 seconds to previous delay
                break;
            case 4:
                setTimeout(() => {
                      this.resetTabs();
                      this.tab4 = true;
                }, delay ); // Adding 5 seconds to previous delay
                break;
             case 5:
                  setTimeout(() => {
                        this.resetTabs();
                        this.tab5 = true;
                 }, delay ); // Adding 5 seconds to previous delay
                break;
                case 6:
                     setTimeout(() => {
                           this.resetTabs();
                           this.tab6 = true;
                    }, delay ); // Adding 5 seconds to previous delay
                   break;
                   
             case 7:
              setTimeout(() => {
                    this.resetTabs();
                    this.tab7 = true;
             }, delay ); // Adding 5 seconds to previous delay
            break;
            
            case 8:
              setTimeout(() => {
                    this.resetTabs();
                    this.tab8 = true;
             }, delay ); // Adding 5 seconds to previous delay
            break;
            case 9:
                 setTimeout(() => {
                       this.resetTabs();
                       this.tab9 = true;
                }, delay ); // Adding 5 seconds to previous delay
               break;
               case 10:
                    setTimeout(() => {
                          this.resetTabs();
                          this.tab10 = true;
                   }, delay ); // Adding 5 seconds to previous delay
                  break;
                  
             case 11:
              setTimeout(() => {
                    this.resetTabs();
                    this.tab11 = true;
             }, delay ); // Adding 5 seconds to previous delay
            break;
             
            default:
                break;
        }

        // Increment delay for the next tab
        delay += 5000; // Increment by 5 seconds for each tab
    }
}

resetTabs() {
    // Reset all tabs to false
    this.tab0=false
    this.tab1 = false;
    this.tab2 = false;
    this.tab3 = false;
    this.tab4 = false;
    this.tab5 = false;
    this.tab6 = false;
    this.tab7 = false;
    this.tab8 = false;
    this.tab9 = false;
    this.tab10 = false;
    this.tab11 = false;
}



currentTab: number = 1;

startStepByStepProcess() {
  this.resetTabs();
  this.readSmartCardData()
  this.tab1 = true;
  this.showImage = true;
  if (Object.keys(this.dataCARD).length != 0) {
    
    switch (this.currentTab) {
      case 1:
        this.showImage = false;
        // this.readSmartCardData()
        //  this.initiateApplicationFunction();
       // this.InitiateApplication()
      //  this.readApplicationDataFunction();
        this.resetTabs();
        this.tab1 = true;
       
        break;
      case 2:
        
        // this.offlineDataAuthenticationFunction();
        this.resetTabs();
        this.tab2 = true;
        this.readApplicationDataFunction();
        break;
      case 3:
        this.offlineDataAuthenticationFunction();
        // this.processingRestrictionFunction()
        this.resetTabs();
        this.tab3 = true;
        break;
      case 4:
        
      this.processingRestrictionFunction()
      // this.cardHolderVerificationFunction();
        this.resetTabs();
        this.tab4 = true;
        break;
      case 5:
         this.cardHolderVerificationFunction();
        // this.terminalRiskManagementFunction();
        this.resetTabs();
        this.tab5 = true;
        break;
      case 6:
        this.terminalRiskManagementFunction();
        // this.terminalActionAnalysisFunction();
        this.resetTabs();
        this.tab6 = true;
        break;
      case 7:
        this.terminalActionAnalysisFunction();
        // this.cardActionAnalysisFunction();
        this.resetTabs();
        this.tab7 = true;
        break;
      case 8:
        this.resetTabs();
        this.tab8 = true;
        this.cardActionAnalysisFunction();
        break;
      case 9:
        this.resetTabs();
        this.tab9 = true;
        this.authorisationRequestFunction();
        break;
      case 10:
        this.resetTabs();
        this.tab10 = true;
        this.issuerResponseFunction();
        break;
      case 11:
        this.resetTabs();
        this.tab11 = true;
        this.completionFunction();
        break;
      default:
        break;
        
    }
    this.currentTab++;
  }
  else{
     // console.log("wait to read card");
     
  }
  
}





//--------------> api  <-------------------------

initiateApplicationFunction(){
  const body={
    "code_profil": this.codeProfile
  }
  this.globalService.initiateApplicationApi(body).subscribe(res => {
    this.data_initiateApplication=res.result
  })
}



readApplicationDataFunction(){
  this.globalService.readApplicationData(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    

    
    
  })
}


offlineDataAuthenticationFunction(){
  this.globalService.offlineDataAuthentication(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    

    
    
  })
}

processingRestrictionFunction(){
  this.globalService.processingRestriction(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    

    
    
  })
}

cardHolderVerificationFunction(){
  this.globalService.cardHolderVerification(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    

    
    
  })
}

terminalRiskManagementFunction(){
  this.globalService.terminalRiskManagement(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    
  })
}


terminalActionAnalysisFunction(){
  this.globalService.terminalActionAnalysis(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    
  })
}


cardActionAnalysisFunction(){
  this.globalService.cardActionAnalysis(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    
  })
}


authorisationRequestFunction(){
  this.globalService.authorisationRequest(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    
  })
}

issuerResponseFunction(){
  this.globalService.issuerResponse(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    
  })
}

completionFunction(){
  this.globalService.completion(this.dataCARD).subscribe(res => {
    // this.data_initiateApplication.push(res.result);
    this.data_initiateApplication = this.data_initiateApplication.concat(res.result);
    // console.log("this.data_initiateApplication",this.data_initiateApplication);
    
  })
}


//--------------> save To File <-------------------------

saveToFile() {
  let content = '';
  this.data_initiateApplication.forEach(item => {
    content += `${item.date}\t${item.description}\n`;
  });

  const blob = new Blob([content], { type: 'text/plain' });
  const anchor = document.createElement('a');
  anchor.download = 'file.txt';
  anchor.href = window.URL.createObjectURL(blob);
  anchor.click();
}

//-------------->clear log <-------------------------
clearLog() {
  this.data_initiateApplication=[]
}
  


//--------------> red card <-------------------------



readSmartCardData(): void {
  this.websocketService.send({ header: 'readSmartCard', messageOut: JSON.stringify(this.checkedTags) });
  this.websocketService.subscribeToSmartCardData((smartCardData) => {
    const parsedData = JSON.parse(smartCardData)  as SmartCardData;
    // console.log("parsedData ",parsedData)
    if (parsedData && parsedData.ChildNodes && parsedData.ChildNodes.length > 0) {
      this.cardApkSelect = parsedData.ChildNodes[0].ChildNodes[1].Name;
      this.showImage = false; // Masquer l'image si la condition est remplie
      this.dataCARD=parsedData
      // console.log("this.dataCARD",this.dataCARD);

      const body={
        "code_profil": this.codeProfile
      }
      this.globalService.initiateApplicationApi(body).subscribe(res => {
        this.data_initiateApplication=res.result
      })
      
     
    } else {
      this.showImage = true; // Afficher l'image par défaut
    }
    
  })
}


InitiateApplication(): void {
  this.websocketService.send({ header: 'Initiate Application', messageOut: "Initiate Application" });
}

ReadApplicationdata(): void {
  this.websocketService.send({ header: 'Read Application data', messageOut: "Read Application data" });
}

OfflineDataAuthentication(): void {
  this.websocketService.send({ header: 'Offline Data Authentication', messageOut: "Offline Data Authentication" });
}

ProcessingRestriction(): void {
  this.websocketService.send({ header: 'Processing Restriction', messageOut: "Processing Restriction" });
}




// ----------------------------> change active profile  <-------------------------------------------//
onProfilSelect() {
    this.changeActiveProfile()  ;
}
changeActiveProfile(){
  const body={
    "code_profil": this.codeProfile
  }
  this.globalService.changeActiveProfile(body).subscribe(res => {
    if (res.respCode== "000") {
     // console.log("profil active");
     this.alertBody.status = res.respCode
       this.alertBody.message = `profile active ` ;
       this.alertBody.open = true
       setTimeout(() => {
         this.alertBody.open = false;
       }, 3000);
     }else{
      //  this.alertBody.status = res.respCode
      //  this.alertBody.message = res.respMsg;
      //  this.alertBody.open = true
      //  setTimeout(() => {
      //    this.alertBody.open = false;
      //  }, 3000);
     }
     
    
 })}






}


interface SmartCardData {
  Name: string;
  Value: string;
  Length: string | null;
  Label: string | null;
  ChildNodes: SmartCardData[];
}
