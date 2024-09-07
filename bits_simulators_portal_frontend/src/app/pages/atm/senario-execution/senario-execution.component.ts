import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {OperationAtmDto} from "../model/OperationAtmDto";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SenarioAtmDto} from "../model/SenarioAtmDto";
import {GlobalService} from "../../../services/global.service";
import {SenarioExecutionService} from "../../../services/senario-execution.service";
import {AuthService} from "../../../services/auth.service";
import {SenarioService} from "../../../services/senario.service";
import {AtmCardService} from "../../../services/atmCardProfil/atm-card.service";

@Component({
  selector: 'app-senario-execution',
  templateUrl: './senario-execution.component.html',
  styleUrls: ['./senario-execution.component.scss']
})
export class SenarioExecutionComponent implements OnInit{
  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  @ViewChild('confirmModal', { read: ElementRef }) confirmModal?: ElementRef

  searchScenario: string = "";
  cards: any[] = [];
  isLoading: boolean = false;
  isLoadingSena: boolean = false;


  tab_1: boolean = true;
  scripts : SenarioAtmDto[] = [];
  tab_2: boolean = false;
  scenarioExecutionForm: FormGroup = new FormGroup('');

  owned_cases: any[] = [];
  not_owned_cases: any[] = [];

  scenarios: any = [];

  user1: any;
  fr = false;
  en = false;
  esp = false;
  language: string = "";


  bankCode: string = "";
  allCases: any;
  constructor(
    private globalService: GlobalService,
    private casesscenariosService: SenarioExecutionService,
    private authService: AuthService,
    private caseSen: SenarioService,
    private atmService: AtmCardService,
    private fb: FormBuilder,
  ) {
    this.authService.user.subscribe(
      (x: any) => {
        this.bankCode = x.bankCode;
      }
    );
  }

  ngOnInit(): void {
    this.scenarioExecutionForm = this.fb.group({
      libelle: [null , Validators.required],
      senarioScripts: this.fb.array([]),
      // cardProfileIssuerCode  : [null,Validators.required],
      id: this.fb.group({
        cardNo: [null, Validators.required],
        bankCode: [this.bankCode]
      })
    });

    this.scenarioExecutionForm.get('id.cardNo')?.valueChanges.subscribe(selectedIssuerCode => {
      // console.log("ng selectedIssuerCode==>",selectedIssuerCode);
      // console.log("==>", this.scenarioExecutionForm.get('id.cardNo')?.value);

      this.filterScenarioByCardProfile(selectedIssuerCode);
    });
    // Promise.resolve().then(() => this.globalService.titleSubject.next("Scenario OperationAtmDto"));

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters / Script / Scenario"));
      this.en=true;
    } else if (this.language === "fr") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres / Script / Scénario"));
      this.fr=true;
    } else if (this.language === "es") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros / Script / Scenario"));
      this.esp=true;
    } else {
      Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }

    this.retrieveScenario();
    this.retrieveCases();
    this.retrieveCards();

  }

  cardProfileScenariosMap: { [cardProfile: string]: any[] } = {};

 

  test(){
    // console.log("test scenarioExecutionForm test ==>", this.scenarioExecutionForm.value);
    // console.log("test scenarios",this.scenarios);
    
  }

  filterScenarioByCardProfile(selectedIssuerCode: string): void {
    // console.log("selectedIssuerCode ==>",selectedIssuerCode);
    // console.log("cardProfileCasesMap ==>",this.cardProfileScenariosMap);

    
    // console.log("owned_cases ==>",this.owned_cases);
    // console.log("ownedCases ==>",this.ownedCases);
    // console.log("not_owned_cases",this.not_owned_cases);


    this.owned_cases = [];
    this.not_owned_cases = [];
    this.ownedCases = [];

    if (this.cardProfileScenariosMap[selectedIssuerCode]) {
      this.not_owned_cases = this.cardProfileScenariosMap[selectedIssuerCode];
    } else {
      this.not_owned_cases = this.scriptss.filter((scriptObj: any) => {
        return scriptObj.atmCardProfilDto.id.cardNo === selectedIssuerCode;
      });
      this.cardProfileScenariosMap[selectedIssuerCode] = this.not_owned_cases;
    }
    this.not_owned_cases = this.not_owned_cases.filter(scriptObj => scriptObj.atmCardProfilDto.id.cardNo === selectedIssuerCode);
  
  
  
  
  }


  p: number = 0;
  pSen: number = 0;
  scriptss: any[] = [];
  senarioScript :{[senarioEx : string] : any[]} = {};
  retrieveScenarios(id: any): void {
    this.scenarioMode = "edit";
    this.casesscenariosService.getSenarioById(id).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.selectedScenario = data.result;
          
          // console.log("data ==>",data.result);
          const d = this.scenarioExecutionForm.value;
          // console.log("scenarioExecutionForm V1111 ==>", d);

          // this.scenarioExecutionForm.patchValue(data.result);
         this.scenarioExecutionForm.patchValue({
          libelle: data.result.libelle,
          id: {
            cardNo: data.result.senarioScripts[0]?.atmCardProfilDto?.id?.cardNo,
            bankCode: data.result.senarioScripts[0]?.atmCardProfilDto?.id?.bankCode || this.bankCode
          }
        });

          // console.log("scenarioExecutionForm V2222==>",this.scenarioExecutionForm.value);

  
          const targetLibelles = data.result.senarioScripts.map((script: any) => script.libelle);
          const selectedIssuerCode = this.scenarioExecutionForm.get('id.cardNo')?.value;

          // console.log('targetLibelles:', targetLibelles);
          // console.log('selectedIssuerCode:===>', selectedIssuerCode);

          if (this.senarioScript[id]) {
            this.owned_cases = this.senarioScript[id];
          } else {
            this.owned_cases = this.scriptss.filter((scriptObj: any) => targetLibelles.includes(scriptObj.libelle) && scriptObj.atmCardProfilDto?.id?.cardNo === selectedIssuerCode);
            this.senarioScript[id] = this.owned_cases;
          }

          // console.log('owned_cases:', this.owned_cases);

          this.not_owned_cases = this.scriptss.filter((caseObj: any) =>
            !targetLibelles.includes(caseObj.libelle) && caseObj.atmCardProfilDto?.id?.cardNo === selectedIssuerCode
          );

          // console.log('not_owned_cases:', this.not_owned_cases);

          this.allCases = this.scriptss.filter((caseObj: any) => !targetLibelles.includes(caseObj.libelle) && caseObj.atmCardProfilDto?.id?.cardNo === selectedIssuerCode);

          // console.log('allCases:', this.allCases);

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
  retrieveScenario(): void {
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

  retrieveCases() {
    this.isLoading=true;
    this.caseSen.getAllSenarios(this.bankCode).subscribe({
      next: (data: any) => {
        if (data.respCode === "000") {
          this.scriptss = data.result.length > 0 ? data.result : this.scriptss;
          this.not_owned_cases = this.scriptss.map((a: any) => ({ ...a }));
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
  saveScenarios(): void {
    const libelle = this.scenarioExecutionForm.get('libelle')?.value;
    const senarioScripts = this.ownedCases.map((caseObj: any) => ({ libelle: caseObj.libelle }));

    const cardProfileIssuerCode = this.scenarioExecutionForm.get('id.cardNo')?.value;


    const body = { libelle, senarioScripts , 
      idCardProfil: {
        cardNo:cardProfileIssuerCode,
        bankCode:this.bankCode
       }
      , bankCode: this.bankCode};

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
          this.scenarioExecutionForm.reset();
          this.retrieveScenario();
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

  deleteScenarios(): void {
    this.confirmModal?.nativeElement.showModal();
  }
  ownedCases: any[]= [];
  updateScenarios(): void {
    const libelle = this.scenarioExecutionForm.get('libelle')?.value;
    // console.log('Updating libelle:', libelle);
    let senarioScripts : any[];
    const cardProfileIssuerCode = this.scenarioExecutionForm.get('id.cardNo')?.value;

    if (this.ownedCases.length > 0) {
      senarioScripts = this.ownedCases.map((caseObj: any) => ({ libelle: caseObj.libelle }));
    } else {
      senarioScripts = this.selectedScenario.senarioScripts;
    }
    const body = { libelle, senarioScripts ,
       idCardProfil: {
        cardNo:cardProfileIssuerCode,
        bankCode:this.bankCode
       }
       , bankCode: this.bankCode};

    // console.log(body);
    this.casesscenariosService.updateSenario(this.selectedScenario.id, body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = data.respMsg;
          this.ownedCases = [];
          this.owned_cases = [];
          this.scenarioExecutionForm.reset();
          this.retrieveScenario();
          this.retrieveCases();
          this.selectedScenario = {};
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
          this.scenarioExecutionForm.reset();
          this.newScenarios();
          this.retrieveScenario();
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
  newScenarios(): void {
    this.scenarioMode = "add";
    this.ownedCases = [];
    this.owned_cases = [];
    this.scenarioExecutionForm.reset();
    this.retrieveCases();
    this.retrieveScenario();
    this.selectedScenario = {};
  }
}
