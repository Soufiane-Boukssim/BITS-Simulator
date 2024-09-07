import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import { EmvTagServiceService } from 'src/app/services/icc/emv-tag-service.service';

@Component({
  selector: 'app-cpa-launch',
  templateUrl: './cpa-launch.component.html',
  styleUrls: ['./cpa-launch.component.scss']
})
export class CpaLaunchComponent  implements OnInit {

  data_test_cases:any[]=[];
  selectedProfileTestCase: any; // Variable pour stocker le profil sélectionné
  p_profile=0
  data_cpa:any[]=[];
  casesCoches: number[] = [];
  searchProfil=""
  constructor(
    private globalService:GlobalService,
    private auth:AuthService,
    private emvTagService: EmvTagServiceService,
   
  ){}


  user1:any
  language: string = ""
  fr = false;
  en = false;
  esp = false;

  ngOnInit(): void {

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      // console.log("en  Card Analyze ");
      
      Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze / EMV CPA / Launch"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze / EMV CPA / Lancement "));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze / EMV CPA / Options"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }
    this.getAllCPAProfiles();
  }



   // getAllCpaProfil

   getAllCPAProfiles(){
    this.globalService.getAllCPAProfiles().subscribe(res => {
      
      this.data_cpa=res.result
      // console.log("datacpa",res.result);
      
    })
  }

   // ------------------------------  get test case  -------------

   selectProfil(item:any) {
    // console.log(item);
    this.getTestCasesCPAProfiles(item);
   }

   getTestCasesCPAProfiles(codeProfile:any){
    const body={
       
         "code_profil": codeProfile
    
     }
     this.globalService.getGroupedTestCasesByCodeProfile(body).subscribe(res => {
       
       this.data_test_cases=res.result
       // console.log("this.data_test_cases,",this.data_test_cases);
       
       
     })
   }


  // Fonction appelée lorsqu'une case à cocher est cochée ou décochée
  gererCoche(testCaseId: number) {
    // Vérifier si l'ID est déjà présent dans le tableau
    const index = this.casesCoches.indexOf(testCaseId);

    // Si l'ID est déjà présent, le retirer du tableau
    if (index !== -1) {
      this.casesCoches.splice(index, 1);
    } else { // Sinon, ajouter l'ID au tableau
      this.casesCoches.push(testCaseId);
    }

    // Afficher le tableau pour vérification
    // console.log(this.casesCoches);
  }

}
