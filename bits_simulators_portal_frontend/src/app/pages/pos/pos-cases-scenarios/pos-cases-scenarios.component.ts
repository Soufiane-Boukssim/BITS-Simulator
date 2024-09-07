import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { distinctUntilChanged } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { CasesandscenariosService } from 'src/app/services/casesandscenarios.service';
import { FieldProcessingService } from 'src/app/services/field-processing.service';
import { GlobalService } from 'src/app/services/global.service';


@Component({
  selector: 'app-pos-cases-scenarios',
  templateUrl: './pos-cases-scenarios.component.html',
  styleUrls: ['./pos-cases-scenarios.component.scss']
})
export class PosCasesScenariosComponent implements OnInit {
  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  @ViewChild('confirmModal', { read: ElementRef }) confirmModal?: ElementRef

  searchCase: string = "";
  searchScenario: string = "";

  isLoading: boolean = false;
  isLoadingSena: boolean = false;


  tab_1: boolean = true;
  tab_2: boolean = false;

  inTab: boolean = true;
  outTab: boolean = false;

  owned_cases: any[] = [];
  not_owned_cases: any[] = [];

  scenarios: any = [];

  cases: any = []

  user1:any

  fr = false;
  en = false;
  esp = false;
  language: string = ""


  fields: any = [
    { fieldDef: { fieldDesc: 'Primary Account Number', id: { fieldId: '002' } }, value: '[FROM CARD PROFILE]' },
    { fieldDef: { fieldDesc: 'Code de traitement', id: { fieldId: '003' } }, details: { processingCode: '36', sourceAccount: '00', destinationAccount: '00' }, value: '360000', collapsed: true },
    { fieldDef: { fieldDesc: 'Transaction Amount', id: { fieldId: '004' } }, value: '0000001400' }
  ]

  spcialFields: string[] = ["3","22","35","54","24","39","18","19","21","49","50","51","53"]



  inFields: any[] = [];
  outFields: any[] = [];

  bankCode: string = "";
  protocoleId: string = "PO";

  constructor(
    private globalService: GlobalService,
    private casesscenariosService: CasesandscenariosService,
    private authService: AuthService,
    private fieldProcessingService: FieldProcessingService,
    private fb: FormBuilder
  ) {

   

    this.authService.user.subscribe(
      (x: any) => {
        this.bankCode = x.bankCode;
        // this.protocoleId = "I";
        // // console.log(x);
        this.initializeForms();
        this.subscribeToValueChanges();
      }
    );

    // this.caseInForm = this.fb.group({
    //   caseDirection: ['res'] // Valeur par défaut est 'res' (IN)
    // });
    

  }

  ngOnInit(): void {

    // console.log("test on init");
    
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters /  Cases & Scenarios"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres /  Cases & Scenarios"));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros /  Cases & Scenarios"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }


  
    this.retrieveCards();
    this.retrieveMerchants();
    this.retrieveTerminals();
    this.retrieveMtis();
    this.retrieveCases();
    this.retrieveFields();
    this.retrieveScenarios();
 
    // this.changebuttonRadio();

  


  }


  changebuttonRadioIN(){

    // console.log('test changebuttonRadio  Iout to in ===> ');
    let req = this.caseOutForm.get('caseDirection')?.value;

    // console.log("let req ===>: " + req);
    

      if (req =="req") {
        this.caseInForm.get('caseDirection')?.setValue("res");
        // this.filterMtisByDirection('out', 'res')
      }
      else{
        this.caseInForm.get('caseDirection')?.setValue("req");
        // this.filterMtisByDirection('out', 'req')
      }
  }

 changebuttonRadioOut(){

  // console.log('test changebuttonRadio in to out  ===> ');

  this.caseInForm.get('caseDirection')?.value
   
     let res =this.caseInForm.get('caseDirection')?.value;

    //  // console.log( " let req ==>",res );
     
    

     if (res =="req") {
        this.caseOutForm.get('caseDirection')?.setValue("res");
        // this.filterMtisByDirection('in', 'res')
      }
      else{
        this.caseOutForm.get('caseDirection')?.setValue("req");
        // this.filterMtisByDirection('in', 'req')
      }

 }




  fieldsDef: any;
  poplist = ['Literal value', 'From Response', 'From Request', 'Generate Value', 'From Card Profile','From merchant','From Terminal'];
  selectedValueSource: string = '';
  fieldValue: string = '';
  retrieveFields(): void {
    
    this.casesscenariosService.getFieldsPOS().subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.fieldsDef = data.result;
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }

  open_tab(index: number) {
    if (index == 0) {
      this.tab_1 = true;
      this.tab_2 = false;
    } else if (index == 1) {
    
      this.tab_1 = false;
      this.tab_2 = true;
    } else if (index == 2) {
      this.changebuttonRadioIN();
      // console.log("test tab 1");
      this.inTab = true;
      this.outTab = false;
    } else if (index == 3) {
      this.changebuttonRadioOut();
      // console.log("test tab 2");
      this.inTab = false;
      this.outTab = true;
    }
  }

  // toggleCollapse(direction: string, fieldId: number) {
  //   // this.fields.collapsed = !this.fields[index].collapsed;
  //   if (direction === 'in') {
  //     this.inFields.find(e => e.)
  //   }
  // }

  toggleCollapse(direction: string, fieldId: number) {
    // this.fields.collapsed = !this.fields[index].collapsed;
    const fieldsArray = direction === 'in' ? this.inFields : this.outFields;

    const fieldToToggle = fieldsArray.find(e => e.fieldDef.id.fieldId === fieldId);

    if (fieldToToggle) {
      fieldToToggle.collapsed = !fieldToToggle.collapsed;
    }
  }
  p: number = 0;
  pSen: number = 0;

  // yassine

  caseInForm: FormGroup = new FormGroup('');
  caseInFormId: FormGroup = new FormGroup('');

  caseOutForm: FormGroup = new FormGroup('');
  caseOutFormId: FormGroup = new FormGroup('');

  scenarioForm: FormGroup = new FormGroup('');
  scenarioFormId: FormGroup = new FormGroup('');

  retrieveCases(): void {
    this.isLoading=true
    const body = {
      bankCode: this.bankCode,
      caseProtocole: this.protocoleId //this.protocoleId
    };
    this.casesscenariosService.getCasesPOS(body).subscribe({
      next: (data: any) => {
      
        // console.log(data);
        if (data.respCode === "000") {
          this.cases = data.result.length > 0 ? data.result : this.cases;
          this.not_owned_cases = this.cases.map((a: any) => ({ ...a }));
          this.isLoading=false
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

  selectedCase: Map<string, any> = new Map<string, any>([
    ["in", {}],
    ["out", {}]
  ]);

  newCase(): void {
    this.clearForms();
    this.addMode = true;
    this.editMode = false;
  }

  clearForms(): void {
    this.caseInForm.reset({
      id: {
        caseNo: '',
        caseProtocole: this.protocoleId,
        caseType: 'I',
        bankCode: this.bankCode
      }
    });
    this.caseOutForm.reset({
      id: {
        caseNo: '',
        caseProtocole: this.protocoleId,
        caseType: 'O',
        bankCode: this.bankCode
      }
    });

    this.inFields = [];
    this.outFields = [];

    this.selectedCase = new Map<string, any>;
    this.details = [];
    this.fieldModalMode = "";
    // clear all forms and empty the data variables
  }

  retriveCase(caseNo: string): void {
    this.editMode = true;
    this.addMode = false;
    const body = {
      bankCode: this.bankCode,
      caseProtocole: this.protocoleId,//this.protocoleId,
      caseNo: caseNo
    };
    // console.log(body)
    this.casesscenariosService.getCasePOS(body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.selectedCase.set("in", data.result.in);
          this.selectedCase.set("out", data.result.out);
          this.caseInForm.patchValue(this.selectedCase.get("in"));
          const infields = this.selectedCase.get("in").fields;
          const outfields = this.selectedCase.get("out").fields;


          this.inFields = this.fieldProcessingService.processFields(infields);
          this.outFields = this.fieldProcessingService.processFields(outfields);

          this.caseOutForm.patchValue(this.selectedCase.get("out"));
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }

  cards: any;
  merchants: any;
  terminals: any;
  mtisIn: any;
  mtisOut: any;
  mtiList: any; // holds original data used in filtering

  retrieveCards(): void {
    const body = {
      bankCode: this.bankCode,
      cardProtocol:this.protocoleId, //this.protocoleId
    };
    this.globalService.getCardsPOS(body).subscribe({
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

  retrieveMerchants(): void {
    const body = {
      bankCode: this.bankCode,
      merProtocol: this.protocoleId
    };
    this.globalService.fetchMerchantPOS(body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.merchants = data.result;
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }

  retrieveTerminals(): void {
    const body = {
      bankCode: this.bankCode,
      terProtocol:  this.protocoleId  //this.protocoleId
    };
    this.globalService.fetchTerminalPOS(body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.terminals = data.result;
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }

  retrieveMtis(): void {
    const body = {
      bankCode: this.bankCode,
      mtiProtocol: this.protocoleId
    };
    this.globalService.fetchMTIPOS(body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          // console.log("test successful response  fetchMTI :", data.result);
          
          this.mtisIn = data.result.filter((m: any) => m.mtiDirection === 'res');

          this.mtisOut = data.result.filter((m: any) => m.mtiDirection === 'req') ;
  
          this.mtiList = data.result;
          // this.filterMtisByDirection('in', 'res')
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }


  subscribeToValueChanges(): void {
    const relevantControls = ['caseDesc', 'caseCardRef', 'caseMerchantPrf', 'caseTerminalPrf', 'caseAction'];
    relevantControls.forEach(controlName => {
      this.caseInForm.get(controlName)?.valueChanges.subscribe(value => {
        this.caseOutForm.get(controlName)?.setValue(value);
      });
    });
  }

  filterMtisByDirection(type: string, direction: string): void {
    // console.log("test filter  filterMtisByDirection ===> " + direction);
    // console.log("test filter  type  ===> " + type);
    
    // console.log("this.mtiList. ===> " + this.mtiList);
    
    
    type === "in" ?
      this.mtisIn = this.mtiList.filter((m: any) => m.mtiDirection === direction)
      :
      this.mtisOut = this.mtiList.filter((m: any) => m.mtiDirection === direction);

      // console.log("===FINISHED =================================");
      
  }

  initializeForms(): void {
    this.caseInFormId = this.fb.group({
      caseNo: '',
      caseProtocole: [this.protocoleId], //this.protocoleId ,//
      caseType: 'I',
      bankCode: this.bankCode
    });

    this.caseOutFormId = this.fb.group({
      caseNo: '',
      caseProtocole: [this.protocoleId], // this.protocoleId ,//
      caseType: 'O',
      bankCode: this.bankCode
    });

    this.caseInForm = this.fb.group({
      id: this.caseInFormId,
      caseDesc: '',
      caseDirection: 'res',
      caseCardRef: '',
      caseMerchantPrf: '',
      caseAction: '',
      caseTerminalPrf: '',
      caseAmount: 0,
      caseHeader: '',
      caseMti: ''
    });

    this.caseOutForm = this.fb.group({
      id: this.caseOutFormId,
      caseDesc: [{ value: '', disabled: true }],
      caseDirection: 'req',
      caseCardRef: [{ value: '', disabled: true }],
      caseMerchantPrf: [{ value: '', disabled: true }],
      caseTerminalPrf: [{ value: '', disabled: true }],
      caseAction: [{ value: '', disabled: true }],
      caseAmount: [0],
      caseHeader: [''],
      caseMti: ['']
    });
    // console.log(this.protocoleId)
    // console.log(this.bankCode)
    this.scenarioFormId = this.fb.group({
      caseSetId: [''],
      caseSetProtocole: this.protocoleId ,//[this.protocoleId],
      bankCode: [this.bankCode]
    });

    this.scenarioForm = this.fb.group({
      id: this.scenarioFormId,
      caseSetDesc: ['']
    })

  
  }

  submit(): void {
    var inCase = this.caseInForm.getRawValue();
    var outCase = this.caseOutForm.getRawValue();

   

    
    inCase.fields = this.inFields;
    outCase.fields = this.outFields;

    if (Array.isArray(inCase.fields)) {
      // Iterate over inCase.fields and delete the details property
      inCase.fields.forEach((field: { details: any;  collapsed?: boolean }) => {
          if (field.details) {
              delete field.details;
              delete field.collapsed ;
          }
          
      });
    }

    if (Array.isArray(outCase.fields)) {
      // Iterate over inCase.fields and delete the details property
      outCase.fields.forEach((field: { details: any;  collapsed?: boolean }) => {
          if (field.details) {
              delete field.details;
              delete field.collapsed ;
             
          }
        
      });
    }
    
  // console.log("inCase ==>",inCase);
  // console.log("inCase ==>",inCase);
 
    
    const req = [inCase, outCase];
    if (this.addMode) {
      // console.log(req);
      this.addCase(req);
    } else {
      // console.log(req);
      this.updateCase(req);
    }
    // // console.log(this.caseInForm.value);
    // // console.log(this.caseOutForm.value);
  }

  fieldModalMode: string = "";

  // modalDirection: string = "";
  openModal(direction: string) {
    this.fieldModalMode = "add";
    // this.modalDirection = direction;
    this.modal?.nativeElement.showModal();
  }
  closeModal() {
    this.clearModelFields();
    this.modal?.nativeElement.close();
    
  }


  clearModelFields() {
    // console.log("fieldValue",this.fieldValue);
    
    this.fieldId="",
    this.selectedValueSource=""
    this.fieldValue=""
    this.selectedFieldDef=null

  }

  // poplist: any;

  addMode: boolean = true;
  editMode: boolean = false;

  selectedFieldDef: any;
  selectFieldDef($event: any): void {
    this.selectedFieldDef = this.fieldsDef.find((e: any) => e.id.fieldId.toString() === $event.target.value);
    // console.log(this.selectedFieldDef);
    // console.log(typeof this.fieldId)
  }
  addCase(body: any): void {
    this.casesscenariosService.addCasePOS(body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = data.respMsg;
          this.clearForms();
          this.open_tab(2)
        } else {
          this.isAlertVisible = true;
          this.status = "001";
          this.msg = data.respMsg;
        }
        this.retrieveCases();
        setTimeout(() => {
          this.isAlertVisible = false;
        }, 2000);
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }

  updateCase(body: any): void {
    this.casesscenariosService.updateCasePOS(body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = data.respMsg;
          this.clearForms();
          this.open_tab(2)
        } else {
          this.isAlertVisible = true;
          this.status = "001";
          this.msg = data.respMsg;
        }
        this.retrieveCases();
        setTimeout(() => {
          this.isAlertVisible = false;
        }, 2000);
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }

  isAlertVisible: boolean = false;
  status: string = "";
  msg: string = "";

  deleteCase(): void {
    this.confirmModal?.nativeElement.showModal();
  }

  closeConfirmModal(): void {
    this.confirmModal?.nativeElement.close();
  }

  deleteCaseConfirm(): void {
    if (this.tab_1) {

      // console.log("selectedCase -->",this.selectedCase.get("in").id);
      
      const { caseNo, caseProtocole, bankCode } = this.selectedCase.get("in").id;
      const id = { caseNo, caseProtocole, bankCode };
      // console.log("request : ", id);
      this.casesscenariosService.deleteCasePOS(id).subscribe({
        next: (data: any) => {
          // console.log(data);
          if (data.respCode === "000") {
            this.isAlertVisible = true;
            this.status = "000";
            this.msg = data.respMsg;
            this.clearForms();
          } else {
            this.isAlertVisible = true;
            this.status = "001";
            this.msg = data.respMsg;
          }
          this.retrieveCases();
          this.closeConfirmModal();
          setTimeout(() => {
            this.isAlertVisible = false;
          }, 2000);
        },
        error: (error: Error) => {
          // console.log(error);
        }
      });
    } else if (this.tab_2) {
      // console.log("test tab 2");
      const id = this.selectedScenario.id;
      // console.log("request : ", id);
      this.casesscenariosService.deleteScenarioPOS(id).subscribe({
        next: (data: any) => {
          // console.log(data);
          if (data.respCode === "000") {
            this.isAlertVisible = true;
            this.status = "000";
            this.msg = data.respMsg;
            this.newScenario();
          } else {
            this.isAlertVisible = true;
            this.status = "001";
            this.msg = data.respMsg;
          }
          this.retrieveCases();
          this.closeConfirmModal();
          setTimeout(() => {
            this.isAlertVisible = false;
          }, 2000);
        },
        error: (error: Error) => {
          // console.log(error);
        }
      });
    }
  }


  duplicate(){
    this.addMode=true;
    this.editMode=false;
    this.caseInForm.get("id")?.get('caseNo')?.setValue("");
    this.caseOutForm.get("id")?.get('caseNo')?.setValue("");
    this.caseInForm.get("caseDesc")?.setValue("");

    // this.addCase(req);
  }

  addEditField(): void {
    const fieldValue = {
      fieldDef: this.selectedFieldDef,
      value: this.selectedValueSource !== 'Literal value' ? this.selectedValueSource : this.fieldValue,
      details: this.details
    };
    if (this.fieldModalMode === "add") {
      if (this.inTab) {
        this.inFields.push(fieldValue);
        // console.log(" this.inFields ====>",this.inFields);
        this.details=[]
      } else if (this.outTab) {
        this.outFields.push(fieldValue);
      }
    } else if (this.fieldModalMode === "edit") {
      let fieldToUpdate: any;
      if (this.inTab) {
        fieldToUpdate = this.inFields.find((e: any) => e.fieldDef.id.fieldId === Number(this.fieldId));
      } else if (this.outTab) {
        fieldToUpdate = this.outFields.find((e: any) => e.fieldDef.id.fieldId === Number(this.fieldId));
      }
      if (fieldToUpdate) {
        fieldToUpdate.value = fieldValue.value;
        fieldToUpdate.details = fieldValue.details;
      } else (
        alert("not found")
      )
    }
    this.selectedValueSource = "";
    this.fieldValue = "";
    // this.details = null;
    this.fieldId = "";
    this.selectedFieldDef = null;
    this.closeModal();
  }

  fieldId?: string;
  details: any;
  editField(fieldValue: any): void {
    // console.log("editField  >>",this.details)
    this.details = fieldValue.details;
    // console.log(this.details)
    this.selectedFieldDef = fieldValue.fieldDef;
    this.fieldId = fieldValue.fieldDef.id.fieldId.toString();
    if (this.poplist.includes(fieldValue.value)) {
      this.selectedValueSource = fieldValue.value;
    } else {
      this.selectedValueSource = 'Literal value';
      this.fieldValue = fieldValue.value;
    }
    this.openModal("");
    this.fieldModalMode = "edit";
    // console.log(this.fieldModalMode);
  }

  deleteField(id: any): void {
    if (this.inTab) {
      this.inFields = this.inFields.filter((e: any) => e.fieldDef.id !== id);
    } else if (this.outFields) {
      this.outFields = this.outFields.filter((e: any) => e.fieldDef.id !== id);
    }
  }

  handleDetailsConfirmed(res: any): void {
    // console.log(res);
    this.fieldValue = res.value;
    this.details = res.details;
    // // console.log( "test details ",this.details)
  }
  // to do : implement adding feature with form clear and feedback alerts
  // add mode  : add field shld add a field to the list so in the end we can press save changes and add the whole case
  // edit mode : same as add but for only in / out



  retrieveScenarios(): void {
    this.isLoadingSena=true;
    const body = {
      bankCode: this.bankCode,
      caseSetProtocole: this.protocoleId
    };
    this.casesscenariosService.getScenariosPOS(body).subscribe({
      next: (data: any) => {
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
  retrieveScenario(id: any): void {
    this.scenarioMode = "edit";
    const body = id;
  
    this.casesscenariosService.getScenarioPOS(body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.selectedScenario = data.result;
          this.scenarioForm.patchValue(data.result);
  
          // Extract caseNos from data.result.cases
          const targetCaseNos = data.result.cases.map((caseObj: any) => caseObj.caseNo);
  
          // Filter cases based on whether caseNo is in targetCaseNos
          this.owned_cases = this.cases.filter((caseObj: any) => targetCaseNos.includes(caseObj.caseNo));
          
          // Filter cases that do not have matching caseNo
          this.not_owned_cases = this.cases.filter((caseObj: any) => !targetCaseNos.includes(caseObj.caseNo));
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }
  
  
  ownedCases: any;
  handleCasesConfirmed(res: any): void {
    // console.log(res);
    this.ownedCases = res;
  }
  scenarioMode: string = "add";
  saveScenario(): void {
    const body = this.scenarioForm.value;
    body.cases = this.ownedCases.map(({ checked, caseDesc, ...rest }: any) => rest);

    // console.log(body);
    this.casesscenariosService.saveScenarioPOS(this.scenarioMode, body).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = data.respMsg;
          this.ownedCases = [];
          this.owned_cases = [];
          this.scenarioForm.reset({
            id: {
              caseSetId: '',
              caseSetProtocole: this.protocoleId,  //this.protocoleId,
              bankCode: this.bankCode
            }
          });
          this.retrieveCases();
          this.retrieveScenarios();
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

  deleteScenario(): void {
    this.confirmModal?.nativeElement.showModal();
  }

  newScenario(): void {
    this.scenarioMode = "add";
    this.ownedCases = [];
    this.owned_cases = [];
    this.scenarioForm.reset({
      id: {
        caseSetId: '',
        caseSetProtocole: this.protocoleId , //this.protocoleId,
        bankCode: this.bankCode
      }
    });
    this.retrieveCases();
    this.retrieveScenarios();
    this.selectedScenario = {}; 
  }
}