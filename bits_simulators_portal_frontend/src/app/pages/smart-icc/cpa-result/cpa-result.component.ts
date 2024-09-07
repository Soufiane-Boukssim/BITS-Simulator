import {FlatTreeControl} from '@angular/cdk/tree';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import { EmvTagServiceService } from 'src/app/services/icc/emv-tag-service.service';





@Component({
  selector: 'app-cpa-result',
  templateUrl: './cpa-result.component.html',
  styleUrls: ['./cpa-result.component.scss']
})
export class CpaResultComponent implements OnInit {

  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  @ViewChild('analyzeModal', { read: ElementRef }) analyzeModal?: ElementRef

  selectedComponent: string | null = null;
  data_cpa:any[]=[];
  searchProfil=""
  data_test_cases:any[]=[];
  p_profile: number = 0;
  selectComponent(component: string) {
    this.selectedComponent = component;
  }
  tab1:boolean = true;
  tab2:boolean = false;
  tab3:boolean = false;
  selectedProfileTestCase: any; // Variable pour stocker le profil sélectionné


  user1:any
  language: string = ""
  fr = false;
  en = false;
  esp = false;


  openTab(index:any){
    this.tab1 = false;
    this.tab2 = false;
    this.tab3 = false;
    switch (index) {
      case 1:
        this.tab2 = true
       
        break;
        case 2:
          this.tab3 = true
          break;
     
      default:
        this.tab1 = true
        break;
    }

    }

  

    


    constructor(
      private globalService:GlobalService,
      private auth:AuthService,
      private emvTagService: EmvTagServiceService,
     
    ){}
  
    ngOnInit(): void {
      this.getAllCPAProfiles();

      this.user1 = localStorage.getItem('user');
      this.user1 = JSON.parse(this.user1);
      this.language = this.user1.languageCode;
      if (this.language === "en") {
        // console.log("en  Card Analyze ");
        
        Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze / EMV CPA / Results"));
        this.en=true
      } else if (this.language === "fr") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze  / EMV CPA / Results "));
          this.fr=true
      } else if (this.language === "es") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze  / EMV CPA / Results"));
          this.esp=true
      } else {
          Promise.resolve().then(() => this.globalService.titleSubject.next(""));
      }

      

    }
  
   


    // getAllCpaProfil

   getAllCPAProfiles(){
    this.globalService.getAllCPAProfiles().subscribe(res => {
      
      this.data_cpa=res.result
      // console.log("datacpa",res.result);
      
    })
  }

 // ------------------------------  get test case  -------------

 getTestCasesCPAProfiles(codeProfile:any){
  const body={
     
       "code_profil":codeProfile
  
   }
   this.globalService.getGroupedTestCasesByCodeProfile(body).subscribe(res => {
     
     this.data_test_cases=res.result
     // console.log("this.data_test_cases,",this.data_test_cases);
     
     
   })
 }

  


    // dailogue 

    openModal() {
      // console.log("test")
      this.analyzeModal?.nativeElement.showModal();
    }
    closeAnalyzeModal() {
      this.analyzeModal?.nativeElement.close();
    }  
    



    selectProfil(item:any){
      // console.log(item);
      this.getTestCasesCPAProfiles(item);
      
    }
}

