import {ChangeDetectorRef, Component, ElementRef, EventEmitter, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PopularList} from "../model/PopularList";
import {ATMfield} from "../model/ATMfield";
import {FieldCasesDto} from "../model/FieldCasesDto";
import {MessageTypeField} from "../model/MessageTypeField";
import {ScriptCasesDefinitionDto} from "../model/ScriptCasesDefinition";
import {TypeDefinitionScript} from "../model/TypeDefinitionScript";
import {ActivatedRoute} from "@angular/router";
import {CaseSenaService} from "../../../services/case-sena.service";
import {GlobalService} from "../../../services/global.service";
import {AuthService} from "../../../services/auth.service";
import {AtmCardService} from "../../../services/atmCardProfil/atm-card.service";

@Component({
  selector: 'app-cases-senario',
  templateUrl: './cases-senario.component.html',
  styleUrls: ['./cases-senario.component.scss']
})
export class CasesSenarioComponent implements OnInit {
  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  @ViewChild('confirmModal', { read: ElementRef }) confirmModal?: ElementRef;
  @ViewChild('updateConfirmModal') updateConfirmModal!: ElementRef;




  searchCase: string = "";
  searchScenario: string = "";
  selectedCase: ScriptCasesDefinitionDto | null = null;

  public poplist: string[] =   Object.keys(PopularList).filter(key => isNaN(Number(key)));


  public fieldsDef: ATMfield[] = [];
  public selectedValueSource: PopularList | null = null;
  public selectedFieldDef: ATMfield | undefined = undefined;
  public fieldValue: string | null = '';
  public popularList: PopularList | null = null;
  public atmField: ATMfield | null = null;

  public fieldCases: FieldCasesDto[] = [];
  isLoading: boolean = false;
  isLoadingSena: boolean = false;

  public fieldId: string | null = null;
  public fieldName: string | null = null;
  tab_1: boolean = true;
  tab_2: boolean = false;


  owned_cases: any[] = [];
  not_owned_cases: any[] = [];

  scenarios: any = [];

  cases: any = []



  inFields: any[] = [];

  id:number=this.activeRoute.snapshot.params["id"];

  bankCode: string = "";
  scriptCasesDefinition: ScriptCasesDefinitionDto = {
    id: 0,
    libelle: "",
    idCardProfil:{
      cardNo:"",
      bankCode:""
    },
    typeScript: TypeDefinitionScript.TRANSACTIONMESSAGE,
    cardProfileIssuerCode: "",
    fieldCases: [],
    bankCode: this.bankCode

  };
  user1: any;
  fr = false;
  en = false;
  esp = false;
  language: string = "";
  isAlertVisible: boolean = false;
  status: string = "";
  msg: string = "";
  updateSuccess = new EventEmitter<boolean>();
  updateMessage = new EventEmitter<string>();

  constructor(
    private cdr: ChangeDetectorRef,
    private globalService: GlobalService,
    private caseSen: CaseSenaService,
    private authService: AuthService,
    private atmService: AtmCardService,
    private fb: FormBuilder,
    private activeRoute: ActivatedRoute
  ) {
    this.authService.user.subscribe(
      (x: any) => {
        this.bankCode = x.bankCode;
      }
    );
  }
  caseInForm!: FormGroup;

  ngOnInit(): void {
    this.caseInForm = this.fb.group({
      libelle: [null,Validators.required],
      cardProfileIssuerCode: [null,Validators.required]
    });
    // Promise.resolve().then(() => this.globalService.titleSubject.next("Parameter/ Script/Definition"));

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parameters / Script / Definition"));
      this.en=true;
    } else if (this.language === "fr") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Paramètres / Script / Définition"));
      this.fr=true;
    } else if (this.language === "es") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Parámetros / Script / Definition"));
      this.esp=true;
    } else {
      Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }


    this.retrieveCards();
    if (this.tab_1) {
      this.retrieveCases(TypeDefinitionScript.TRANSACTIONMESSAGE);
    } else if (this.tab_2) {
      this.retrieveCases(TypeDefinitionScript.READYMESSAGE);
    }
    if (this.tab_1) {
      this.getAllATMfields(MessageTypeField.R);
    } else if (this.tab_2) {
      this.getAllATMfields(MessageTypeField.S);
    }

  }

  getAllATMfields(messageType: MessageTypeField): void {
    this.globalService.getAllATMfields().subscribe((data: ATMfield[]) => {
      this.fieldsDef = data.filter(field => {
        return field.messageTypeField === messageType;
      });
    });
  }
  openUpdateConfirmModal() {
    // console.log('Opening update confirm modal');
    this.updateConfirmModal.nativeElement.showModal();
  }
  closeUpdateConfirmModal() {
    this.updateConfirmModal.nativeElement.close();
  }

  open_tab(index: number) {
    // Clear the fieldCases array
    this.scriptCasesDefinition.fieldCases = [];

    if (index == 0) {
      this.tab_1 = true;
      this.tab_2 = false;
      this.getAllATMfields(MessageTypeField.R);
      this.cases = this.cases.filter((caseItem: ScriptCasesDefinitionDto) => caseItem.typeScript === TypeDefinitionScript.TRANSACTIONMESSAGE);
      this.scriptCasesDefinition.typeScript = TypeDefinitionScript.TRANSACTIONMESSAGE;
      this.retrieveCases(TypeDefinitionScript.TRANSACTIONMESSAGE);
    } else if (index == 1) {
      this.tab_1 = false;
      this.tab_2 = true;
      this.getAllATMfields(MessageTypeField.S);
      this.cases = this.cases.filter((caseItem: ScriptCasesDefinitionDto) => caseItem.typeScript === TypeDefinitionScript.TRANSACTIONMESSAGE);
      this.scriptCasesDefinition.typeScript = TypeDefinitionScript.READYMESSAGE;
      this.retrieveCases(TypeDefinitionScript.READYMESSAGE);
    }
  }

  selectCase(caseItem: ScriptCasesDefinitionDto): void {
     // console.log(caseItem); // Print the caseItem object to the console
     this.id=caseItem.id;
     //console.log("id==>",this.id);
     

    // aff


    // console.log("caseInForm = " , this.caseInForm.value);
    
    // console.log("test selectedCase ==>",this.selectedCase);
        // console.log("cases = " , this.cases);

     
    this.selectedCase = caseItem;
    this.scriptCasesDefinition = caseItem;
    // Update the scriptCasesDefinition object with the selected case
    this.scriptCasesDefinition = caseItem;
    this.cdr.detectChanges();
  }


  p: number = 0;


  retrieveCases(typeScript: TypeDefinitionScript) {
    this.isLoading=true;

    this.caseSen.getAllScriptCasesDefinition(this.bankCode).subscribe({
      next: (data: any) => {
        if (data.respCode === "000") {
          // Filter the cases based on the typeScript parameter
          this.cases = data.result.filter((caseItem: ScriptCasesDefinitionDto) => caseItem.typeScript === typeScript);
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
  cards: any;
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

  fieldModalMode: string = "";


  openModal() {
    // console.log('openModal called with direction:');
      this.modal?.nativeElement.showModal();
    }

  closeModal() {
    this.clearModelFields();
    this.modal?.nativeElement.close();
    this.fieldModalMode="add"

  }
  clearModelFields() {
    this.fieldId = "";
    this.selectedValueSource = null;
    this.fieldValue = "";
    this.popularList = null;
    this.atmField = null;
  }


  onSelectedValueSourceChange(selectedValue: string): void {
    if (selectedValue === "Literal_Value") {
      this.fieldValue = "";
    }
  }


  addMode: boolean = true;
  editMode: boolean = false;

  newCase(): void {
    // console.log('newCase function was called');
    this.clearForms();
    this.caseInForm.reset();
    this.selectedCase = null;
    this.addMode = true;
    this.scriptCasesDefinition.fieldCases = [];
    this.editMode = false;
    this.inFields = [];
  }

  deleteCase(id: number) {

    this.caseSen.deleteScriptCasesDefinition(id).subscribe(response => {
      if (response === '000') {
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = 'Case deleted successfully';
          if (this.tab_1) {
            this.retrieveCases(TypeDefinitionScript.TRANSACTIONMESSAGE);
          } else if (this.tab_2) {
            this.retrieveCases(TypeDefinitionScript.READYMESSAGE);
          }
          this.clearForms();
          this.caseInForm.reset();
        } else {
          this.isAlertVisible = true;
          this.status = "001";
          this.msg = 'Failed to delete case';
        }
        setTimeout(() => {
          this.isAlertVisible = false;
        }, 3000);
      },
      error => {
        console.error(error);
      }
    );
  }
  clickCount = 0;
  updateCase(id: number, scriptCasesDefinition: any) {

    if (this.tab_1) {
      scriptCasesDefinition.typeScript = TypeDefinitionScript.TRANSACTIONMESSAGE;
    } else if (this.tab_2) {
      scriptCasesDefinition.typeScript = TypeDefinitionScript.READYMESSAGE;
    }

    scriptCasesDefinition = {
      ...scriptCasesDefinition,
      libelle: this.caseInForm.get('libelle')?.value || scriptCasesDefinition.libelle,
      cardProfileIssuerCode: this.caseInForm.get('cardProfileIssuerCode')?.value || scriptCasesDefinition.cardProfileIssuerCode
    };

    this.caseSen.updateScriptCasesDefinition(this.scriptCasesDefinition.id, scriptCasesDefinition).subscribe({
      next: (data: any) => {
        // console.log('Response from updateScriptCasesDefinition:', data);
        this.closeUpdateConfirmModal();
        if (data.respCode === "000") {
          // console.log('Update successful');
          this.scriptCasesDefinition.fieldCases = [];
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = data.respMsg;
          if (this.tab_1) {
            this.retrieveCases(TypeDefinitionScript.TRANSACTIONMESSAGE);
          } else if (this.tab_2) {
            this.retrieveCases(TypeDefinitionScript.READYMESSAGE);
          }
          
          this.clearForms();
          this.caseInForm.reset();
        } else {
          // console.log('Update failed');
          this.isAlertVisible = true;
          this.status = "001";
          this.msg = data.respMsg;
        }
        setTimeout(() => {
          this.isAlertVisible = false;
        }, 3000);
      },
      error: (error: Error) => {
        // console.log('Error from updateScriptCasesDefinition:', error);
      }
    });
  }

  clearForms(): void {
    this.scriptCasesDefinition = {
      id: 0,
      libelle: '',
      idCardProfil:{
        cardNo:'',
        bankCode:''
      },
      typeScript: TypeDefinitionScript.TRANSACTIONMESSAGE,
      cardProfileIssuerCode: '',
      fieldCases: [],
      bankCode: this.bankCode
    };
    this.fieldId = null;
    this.selectedValueSource = null;
    this.fieldValue = '';
    this.popularList = null;
    this.atmField = null;
  }

  addEditField(): void {
    const field: FieldCasesDto = {
      popularList: this.selectedValueSource as PopularList,
      value: this.selectedValueSource !== PopularList.Literal_Value ? this.selectedValueSource : this.fieldValue,
      atmField : [this.atmField as ATMfield],
      bankCode: this.bankCode
    };
      // console.log("feild ==>"+field.popularList);
      // console.log("feild ==>"+field.value);
    if (this.fieldModalMode === 'edit' ) {
     if (field.popularList!=null && field.value !=null) {
      const index = this.scriptCasesDefinition.fieldCases.findIndex(f => f.atmField[0].id === this.atmField?.id);
      this.scriptCasesDefinition.fieldCases[index] = field;
      this.closeModal();
     }else{
      this.closeModal();
     }
     
    } else {
      if (field.popularList!=null && field.value !=null) {

        this.scriptCasesDefinition.fieldCases.push(field);
        this.fieldValue = '';
        this.atmField = null;
        this.selectedValueSource = null;

      }else{
        this.closeModal();
      }
    
    }
    // this.closeModal();
    // console.log('Field added:', field);
  }

  selectFieldDef(fieldId: string | null): void {
    if (fieldId === null) {
      // console.log('No field selected. ATM Field Description is not set.');
      return;
    }
    // console.log('Selected field ID:', fieldId);
    this.selectedFieldDef = this.fieldsDef.find(f => f.id.toString() === fieldId);
    if (this.selectedFieldDef) {
      this.atmField = this.selectedFieldDef;
      // console.log('Selected ATM Field:', this.atmField);
    } else {
      // console.log('No field selected. ATM Field Description is not set.');
    }
  }


  editField(field: FieldCasesDto): void {
    // Populate the form with the field data
    this.atmField = field.atmField[0];
    this.fieldValue = field.value;
    this.popularList = field.popularList;

    this.selectedValueSource = field.popularList;

    // console.log("editField ==>",field);
    this.fieldModalMode="edit"
    if (field.atmField.length > 0) {
      this.fieldId = field.atmField[0].id.toString(); // Convert the number to a string
    } else {
        this.fieldId = null; // Or handle the case when there are no atmFields
    }

    // console.log("fieldId  ==>",this.fieldId);
    

    // Open the modal
    this.openModal();
  }

  deleteField(index: number): void {
    this.scriptCasesDefinition.fieldCases.splice(index, 1);
  }

  saveScriptCasesDefinition(): void {
    // console.log("caseInForm ==>",this.caseInForm.value);
    // console.log("scriptCasesDefinition ==>",this.scriptCasesDefinition);
    
    if (this.tab_1) {
      this.scriptCasesDefinition.typeScript = TypeDefinitionScript.TRANSACTIONMESSAGE;
    } else if (this.tab_2) {
      this.scriptCasesDefinition.typeScript = TypeDefinitionScript.READYMESSAGE;
    }
    this.scriptCasesDefinition = {
      id: this.scriptCasesDefinition.id,
      libelle: this.caseInForm.get('libelle')?.value,
      typeScript: this.scriptCasesDefinition.typeScript, // Keep the previously set value
      cardProfileIssuerCode: this.caseInForm.get('cardProfileIssuerCode')?.value,
      idCardProfil:{
        cardNo:this.caseInForm.get('cardProfileIssuerCode')?.value,
        bankCode:this.bankCode
      },
      fieldCases: this.scriptCasesDefinition.fieldCases,
      bankCode: this.bankCode
    };

    // console.log('ScriptCasesDefinition to be saved:', this.scriptCasesDefinition);

    this.caseSen.createScriptCasesDefinition(this.scriptCasesDefinition).subscribe({
      next: (data: any) => {
        // console.log('Response from createScriptCasesDefinition:', data);
        if (data.respCode === "000") {
          // console.log('ScriptCasesDefinition created successfully');
          this.scriptCasesDefinition.fieldCases = [];
          this.isAlertVisible = true;
          this.status = "000";
          this.msg = data.respMsg;
          if (this.tab_1) {
            this.retrieveCases(TypeDefinitionScript.TRANSACTIONMESSAGE);
          } else if (this.tab_2) {
            this.retrieveCases(TypeDefinitionScript.READYMESSAGE);
          }
          this.clearForms();
          this.caseInForm.reset();
        } else {
          // console.log('Failed to create ScriptCasesDefinition');
          this.isAlertVisible = true;
          this.status = "001";
          this.msg = data.respMsg;
        }
        setTimeout(() => {
          this.isAlertVisible = false;
        }, 3000);
      },
      error: (error: Error) => {
        // console.log('Error from createScriptCasesDefinition:', error);
      }
    });
  }
  protected readonly PopularList = PopularList;

  closeConfirmModal() {
    this.confirmModal?.nativeElement.close();
  }
  openConfirmModal(): void {
    // console.log('openModal called with direction:');
    this.confirmModal?.nativeElement.showModal();
  }



}
