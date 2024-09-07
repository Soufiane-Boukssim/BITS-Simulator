import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {OperationAtmDto} from "../model/OperationAtmDto";
import {GlobalService} from "../../../services/global.service";
import {SenarioService} from "../../../services/senario.service";
import {AuthService} from "../../../services/auth.service";
import {CaseSenaService} from "../../../services/case-sena.service";
import {AtmCardService} from "../../../services/atmCardProfil/atm-card.service";
import {AtmCardProfilDto} from "../model/AtmCardProfilDto";

@Component({
  selector: 'app-senarios',
  templateUrl: './senarios.component.html',
  styleUrls: ['./senarios.component.scss']
})
export class SenariosComponent implements OnInit{

  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  @ViewChild('confirmModal', { read: ElementRef }) confirmModal?: ElementRef

  searchScenario: string = "";
  user1: any;
  fr = false;
  en = false;
  esp = false;
  language: string = "";

  isLoading: boolean = false;
  isLoadingSena: boolean = false;

  cards: AtmCardProfilDto[] = [];

  tab_1: boolean = true;
  scripts : OperationAtmDto[] = [];
  tab_2: boolean = false;
  scenarioForm: FormGroup = new FormGroup('');

  owned_cases: any[] = [];
  not_owned_cases: any[] = [];

  scenarios: any = [];



  bankCode: string = "";
  all_cases: any;

  constructor(
    private globalService: GlobalService,
    private casesscenariosService: SenarioService,
    private authService: AuthService,
    private atmService: AtmCardService,
    private caseSen: CaseSenaService,
    private fb: FormBuilder,
  ) {
    this.authService.user.subscribe(
      (x: any) => {
        this.bankCode = x.bankCode;
      }
    );
  }

  ngOnInit(): void {
    this.scenarioForm = this.fb.group({
      libelle: [null , Validators.required],
      atmCardProfilDto: this.fb.group({
        id: this.fb.group({
          cardNo: [null, Validators.required],
          bankCode: [this.bankCode]
        })

        // issuerCode: [null , Validators.required],
      }),
      scriptCasesDefinitions: this.fb.array([]),
      bankCode: [this.bankCode]
    });
    this.authService.user.subscribe(
      (x: any) => {
        this.bankCode = x.bankCode;
      }
    );
    // console.log("test scenarioForm ==>",this.scenarioForm.value);
    
    this.scenarioForm.get('atmCardProfilDto.id.cardNo')?.valueChanges.subscribe(selectedIssuerCode => {
      // console.log("ng selectedIssuerCode==>",selectedIssuerCode);
      // console.log("==>", this.scenarioForm.get('atmCardProfilDto.id.cardNo')?.value);
      
      
      this.filterCasesByCardProfile(selectedIssuerCode);
    });

    // Promise.resolve().then(() => this.globalService.titleSubject.next("Script"));

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters / Script / Operation"));
      this.en=true;
    } else if (this.language === "fr") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres / Script / Opération"));
      this.fr=true;
    } else if (this.language === "es") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros / Script / Operation"));
      this.esp=true;
    } else {
      Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }

    this.retrieveScenarios();
    this.retrieveCases();
    this.retrieveCards();
  }
  cardProfileCasesMap: { [cardProfile: string]: any[] } = {};



  test(){
    // console.log("test scenarioForm test ==>", this.scenarioForm.value);
    
  }

  cases: any[] = []; // Assuming cases are loaded somewhere in your component


  filterCasesByCardProfile(selectedIssuerCode: string): void {
    // console.log("test scenarioForm ==>",this.scenarioForm.value);

    
    // console.log("selectedIssuerCode===>",selectedIssuerCode);
  
    // console.log("cardProfileCasesMap ==>",this.cardProfileCasesMap);
    
    // Reset the arrays

    // console.log("owned_cases ==>",this.owned_cases);
    // console.log("all_cases=>",this.all_cases);
    // console.log("ownedCases ==>",this.ownedCases);
    // console.log("not_owned_cases",this.not_owned_cases);
    
    
    

    this.owned_cases = [];
    this.ownedCases = [];
    this.not_owned_cases = [];
    this.all_cases = [];


    if (this.cardProfileCasesMap[selectedIssuerCode]) {
      this.not_owned_cases = this.cardProfileCasesMap[selectedIssuerCode];
    } else {
      this.not_owned_cases = this.cases.filter((caseObj: any) => {
        return caseObj.idCardProfil?.cardNo === selectedIssuerCode;
      });
      this.cardProfileCasesMap[selectedIssuerCode] = this.not_owned_cases;
    }

    this.not_owned_cases = this.not_owned_cases.filter(caseObj => caseObj.idCardProfil?.cardNo === selectedIssuerCode);
    this.all_cases = this.cases.filter((caseObj: any) => caseObj.idCardProfil?.cardNo === selectedIssuerCode);
  
  }
  
  p: number = 0;
  pSen: number = 0;
  scenarioOwnedCasesMap: { [scenarioId: string]: any[] } = {};

  retrieveScenario(id: any): void {
    this.scenarioMode = "edit";
    this.casesscenariosService.getSenarioById(id).subscribe({
      next: (data: any) => {
        // // console.log(data);
        if (data.respCode === "000") {
          this.selectedScenario = data.result;
          // console.log("data senarios===>",data.result);
          
          this.scenarioForm.patchValue(data.result);

          // console.log("dtaa this.scenarioForm ==>", this.scenarioForm.value);
          
          this.scenarioForm.get('atmCardProfilDto.id.cardNo')?.setValue(this.selectedScenario.atmCardProfilDto?.id?.cardNo);
          // console.log("data scenarioForm v22==>",this.scenarioForm.value);
          
          const targetLibelles = data.result.scriptCasesDefinitions.map((scriptCaseDefinition: any) => scriptCaseDefinition.libelle);
          const issuerCode = this.scenarioForm.get('atmCardProfilDto.id.cardNo')?.value;

          if (this.scenarioOwnedCasesMap[id]) {
            this.owned_cases = this.scenarioOwnedCasesMap[id];
          } else {
            this.owned_cases = this.cases.filter((scriptObj: any) => targetLibelles.includes(scriptObj.libelle));
            this.scenarioOwnedCasesMap[id] = this.owned_cases;
          }

          this.not_owned_cases = this.cases.filter((caseObj: any) =>
            caseObj?.idCardProfil?.cardNo === issuerCode && !targetLibelles.includes(caseObj.libelle)
          );

          this.all_cases = this.cases.filter((caseObj: any) =>
            caseObj.idCardProfil?.cardNo === issuerCode && !targetLibelles.includes(caseObj.libelle)
          );
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }


  retrieveCards(): void {
    const body = {
      bankCode: this.bankCode,
    };
    this.atmService.getCards(body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          // console.log("data res  this.cards ===>",data.result);
          
          this.cards = data.result;
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }
  retrieveScenarios(): void {
    this.isLoadingSena=true;
    this.casesscenariosService.getAllSenarios(this.bankCode).subscribe({
      next: (data: any) => {
        // console.log(this.scripts);
        // console.log(data);
        this.isLoadingSena=false;
        if (data.respCode === "000") {
          this.scenarios = data.result;
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
        this.isLoadingSena=false;
      }
    });
  }
  selectedScenario: any;
  // cases: any = [];
  retrieveCases() {
    this.isLoading=true;
    this.caseSen.getAllScriptCasesDefinition(this.bankCode).subscribe({
      next: (data: any) => {
        if (data.respCode === "000") {
          this.cases = data.result.length > 0 ? data.result : this.cases;

          this.not_owned_cases = this.cases.map((a: any) => ({ ...a }));
          this.isLoading=false
          // console.log(this.owned_cases, this.not_owned_cases)
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
        this.isLoading=false
      }
    });
  }

  handleCasesConfirmed(res: any): void {
    // console.log(res);
    this.ownedCases = res;
  }
  isAlertVisible: boolean = false;
  status: string = "";
  msg: string = "";
  scenarioMode: string = "add";
  saveScenario(): void {
    const libelle = this.scenarioForm.get('libelle')?.value;
    const scriptCasesDefinitions = this.ownedCases.map((caseObj: any) => ({ libelle: caseObj.libelle }));
    const cardNo = this.scenarioForm.get('atmCardProfilDto.id.cardNo')?.value;
    // console.log("test cardNo::>",cardNo);
    
    const body = {
      libelle,
      scriptCasesDefinitions,
      atmCardProfilDto: {
        id:{
          cardNo:cardNo,
          bankCode:this.bankCode
        }
      },
      bankCode: this.bankCode
    };
    // console.log(body);
    this.casesscenariosService.createSenario(body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = data.respMsg;
          this.ownedCases = [];
          this.owned_cases = [];
          this.scenarioForm.reset();
          this.retrieveScenarios();
          this.retrieveCases();
          this.selectedScenario = {};
        } else {
          this.isAlertVisible = true;
          this.status = "001";
          this.msg = data.respMsg;
        }
        setTimeout(() => {
          this.isAlertVisible = false;
        }, 3000);
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }


  ownedCases: any[] = [];
  updateScenario(): void {
    const libelle = this.scenarioForm.get('libelle')?.value;
    const scriptCasesDefinitions = this.ownedCases.map((caseObj: any) => ({ libelle: caseObj.libelle }));
    const cardNo = this.scenarioForm.get('atmCardProfilDto.id.cardNo')?.value;

    const body = { libelle, scriptCasesDefinitions ,  atmCardProfilDto: {
      id:{
        cardNo:cardNo,
        bankCode:this.bankCode
      }
      },
      bankCode: this.bankCode
    };



    // console.log(body);
    this.casesscenariosService.updateSenario(this.selectedScenario.id, body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = data.respMsg;
        } else {
          this.isAlertVisible = true;
          this.status = "001";
          this.msg = data.respMsg;
        }
        setTimeout(() => {
          this.isAlertVisible = false;
        }, 2000);
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }
  closeConfirmModal() {
    this.confirmModal?.nativeElement.close();
  }
  openModal() {
    // console.log('openModal called with direction:');
    this.confirmModal?.nativeElement.showModal();
  }
deleteScenariosConfirmed(): void {
    this.casesscenariosService.deleteSenario(this.selectedScenario.id).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = data.respMsg;
          this.ownedCases = [];
          this.owned_cases = [];
          this.scenarioForm.reset();
          this.retrieveScenarios();
          this.retrieveCases();
          this.selectedScenario = {};
          this.newScenario();
        } else {
          this.isAlertVisible = true;
          this.status = "001";
          this.msg = data.respMsg;
        }
        setTimeout(() => {
          this.isAlertVisible = false;
        }, 3000);
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });

}
  newScenario(): void {
    this.scenarioMode = "add";
    this.ownedCases = [];
    this.owned_cases = [];
    this.scenarioForm.reset();
    this.retrieveCases();
    this.retrieveScenarios();
    this.selectedScenario = {};
  }

}
