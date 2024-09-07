import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {GlobalService} from "../../../services/global.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-definition',
  templateUrl: './definition.component.html',
  styleUrls: ['./definition.component.scss']
})
export class DefinitionComponent {
  tab_1:boolean = true
  tab_2:boolean = false
  tab_3:boolean = false
  tab_4:boolean = false
  tab_5:boolean = false
  tab_6:boolean = false
  bankCode:any=''
  datetimeValue: any = ''
  definitionForm: FormGroup= this.formBuilder.group({
    terminalDefinition: this.formBuilder.group({
      id: this.formBuilder.group({
        terminalNumber: ['00000001'],
        bankCode: [this.bankCode]
      }),
      terminalLabel: [''],
      nomProtocol: [''],
      communicationIndex: [''],
      machineNumber: [''],
      luno: [''],
      tdate: [''],
      ttime: [''],
      terminalRelease: [''],
      softwareId: [''],
      configId: ['']
    }),
    note: this.formBuilder.group({
      id: this.formBuilder.group({
        terminalNumber: ['00000001'],
        bankCode: [this.bankCode]
      }),
      hcashType: [''],
      hcashConfig: [''],
      hcashFitness: [''],
      hcashSupplies: [''],
      hcashReject: [''],
      cassette1Config: [''],
      cassette1Fitness: [''],
      cassette1Supplies: [''],
      cassette2Config: [''],
      cassette2Fitness: [''],
      cassette2Supplies: [''],
      cassette3Config: [''],
      cassette3Fitness: [''],
      cassette3Supplies: [''],
      cassette4Config: [''],
      cassette4Fitness: [''],
      cassette4Supplies: ['']
    }),
    printer: this.formBuilder.group({
      id: this.formBuilder.group({
        terminalNumber: ['00000001'],
        bankCode: [this.bankCode]
      }),
      receiptConfig: [''],
      receiptType: [''],
      receiptFitness: [''],
      receiptSupplie: [''],
      receiptRollwidth: [''],
      receiptLeftcol: [''],
      receiptMaxline: [''],
      journalConfig: [''],
      journalType: [''],
      journalFitness: [''],
      journalSupplie: [''],
      journalTraceonoff: [''],
      statementConfig: [''],
      statementType: [''],
      statementFitness: [''],
      statementSupplie: [''],
      statementRollwidth: ['']
    }),
    configuration: this.formBuilder.group({
      id: this.formBuilder.group({
        terminalNumber: ['00000001'],
        bankCode: [this.bankCode]
      }),
      terminalModel: [''],
      terminalSystemDisk: [''],
      terminalMCRW: [''],
      terminalDepository: [''],
      terminalNightsafe: [''],
      terminalEncryptor: [''],
      terminalSecurityCamera: [''],
      terminalDoorAccess: [''],
      terminalFlexDisk: [''],
      terminalTiBin: [''],
      terminalKeyboard: [''],
      terminalDisplayVoice: [''],
      terminalSignageDisplay: [''],
      terminalMedia: [''],
      terminalEnvDisp: [''],
      terminalDPM: [''],
      terminalDigitalAudio: [''],
      terminalBNA: [''],
      terminalCheqProcessing: ['']
    }),
    keys: this.formBuilder.group({
      id: this.formBuilder.group({
        terminalNumber: ['00000001'],
        bankCode: [this.bankCode]
      }),
      keyAOld: [''],
      keyA: [''],
      keyACv: [''],
      keyBOld: [''],
      keyB: [''],
      keyBCv: [''],
      keyMacOld: [''],
      keyMac: [''],
      keyMacCv: [''],
      keyVisaOld: [''],
      keyVisa: [''],
      keyVisaCv: ['']
    })
  });

  alertBody:any = {
    message:'',
    status:'',
    open:false
  }
  constructor(
    private formBuilder:FormBuilder,
    private globalService:GlobalService,
    private auth:AuthService
  ){

  }
  ngOnInit(): void {
    Promise.resolve().then(() => this.globalService.titleSubject.next("Definition"))
    this.auth.user.subscribe(val => {
      this.bankCode = val.bankCode
    })
    this.resetForm()
    this.getTerminal()
    const formData = JSON.parse(<string>localStorage.getItem('form'));
    if (formData) {
      // If there is form data in local storage, use it to set the form values
      this.definitionForm.patchValue(formData);
    }
  }
  open_tab(tab:number){
    this.tab_1 = false;
    this.tab_2 = false;
    this.tab_3 = false;
    this.tab_4 = false;
    this.tab_5 = false;
    this.tab_6 = false;
    switch (tab) {
      case 1:
        this.tab_2 = true

        break;

        case 2:
        this.tab_3 = true

          break;

        case 3:
        this.tab_4 = true

          break;

      default:
        this.tab_1 = true
        break;
    }
  }

  resetForm() {
    this.definitionForm = this.formBuilder.group({
      terminalDefinition: this.formBuilder.group({
        id: this.formBuilder.group({
          terminalNumber: ['00000001'],
          bankCode: [this.bankCode]
        }),
        terminalLabel: [''],
        nomProtocol: [''],
        communicationIndex: [''],
        machineNumber: [''],
        luno: [''],
        tdate: [''],
        ttime: [''],
        terminalRelease: [''],
        softwareId: [''],
        configId: ['']
      }),
      note: this.formBuilder.group({
        id: this.formBuilder.group({
          terminalNumber: ['00000001'],
          bankCode: [this.bankCode]
        }),
        hcashType: [''],
        hcashConfig: [''],
        hcashFitness: [''],
        hcashSupplies: [''],
        hcashReject: [''],
        cassette1Config: [''],
        cassette1Fitness: [''],
        cassette1Supplies: [''],
        cassette2Config: [''],
        cassette2Fitness: [''],
        cassette2Supplies: [''],
        cassette3Config: [''],
        cassette3Fitness: [''],
        cassette3Supplies: [''],
        cassette4Config: [''],
        cassette4Fitness: [''],
        cassette4Supplies: ['']
      }),
      printer: this.formBuilder.group({
        id: this.formBuilder.group({
          terminalNumber: ['00000001'],
          bankCode: [this.bankCode]
        }),
        receiptConfig: [''],
        receiptType: [''],
        receiptFitness: [''],
        receiptSupplie: [''],
        receiptRollwidth: [''],
        receiptLeftcol: [''],
        receiptMaxline: [''],
        journalConfig: [''],
        journalType: [''],
        journalFitness: [''],
        journalSupplie: [''],
        journalTraceonoff: [''],
        statementConfig: [''],
        statementType: [''],
        statementFitness: [''],
        statementSupplie: [''],
        statementRollwidth: ['']
      }),
      configuration: this.formBuilder.group({
        id: this.formBuilder.group({
          terminalNumber: ['00000001'],
          bankCode: [this.bankCode]
        }),
        terminalModel: [''],
        terminalSystemDisk: [''],
        terminalMCRW: [''],
        terminalDepository: [''],
        terminalNightsafe: [''],
        terminalEncryptor: [''],
        terminalSecurityCamera: [''],
        terminalDoorAccess: [''],
        terminalFlexDisk: [''],
        terminalTiBin: [''],
        terminalKeyboard: [''],
        terminalDisplayVoice: [''],
        terminalSignageDisplay: [''],
        terminalMedia: [''],
        terminalEnvDisp: [''],
        terminalDPM: [''],
        terminalDigitalAudio: [''],
        terminalBNA: [''],
        terminalCheqProcessing: ['']
      }),
      keys: this.formBuilder.group({
        id: this.formBuilder.group({
          terminalNumber: ['00000001'],
          bankCode: [this.bankCode]
        }),
        keyAOld: [''],
        keyA: [''],
        keyACv: [''],
        keyBOld: [''],
        keyB: [''],
        keyBCv: [''],
        keyMacOld: [''],
        keyMac: [''],
        keyMacCv: [''],
        keyVisaOld: [''],
        keyVisa: [''],
        keyVisaCv: ['']
      })
    });
    return this.definitionForm
  }

  handleApiResponse(response: any) {
    switch (response.respCode) {
      case '000':
        // console.log('Success:');
        // Handle not found (e.g., show a not found message, clear form, etc.)
        break;
      case '404':
        console.error('Not Found:');
        // Handle not found (e.g., show a not found message, clear form, etc.)
        break;
      case '001':
        console.error('Exception:');
        // Handle exception (e.g., show error message, log error, etc.)
        break;
      case '409':
        console.error('Already Exists:');
        // Handle already exists (e.g., show a message indicating the item already exists)
        break;
      default:
        console.error('Unexpected response:', response);
      // Handle other cases
    }
  }

  save() {
    const [date, time] = this.datetimeValue.split('T');
    this.definitionForm.get('terminalDefinition')?.patchValue({
      tdate: date,
      ttime: time
    });
    let terminalNumber = this.definitionForm.get('terminalDefinition')?.get('id')?.get('terminalNumber')?.value
    this.definitionForm.patchValue({
      terminalDefinition: {
        id: {
          terminalNumber: '00000001'
        }
      }
    });

    // Note
    this.definitionForm.patchValue({
      note: {
        id: {
          terminalNumber: '00000001'
        }
      }
    });

    // Printer
    this.definitionForm.patchValue({
      printer: {
        id: {
          terminalNumber: '00000001'
        }
      }
    });

    // Configuration
    this.definitionForm.patchValue({
      configuration: {
        id: {
          terminalNumber: '00000001'
        }
      }
    });

    // Keys
    this.definitionForm.patchValue({
      keys: {
        id: {
          terminalNumber: '00000001'
        }
      }
    });

    // Save the form data to local storage
    localStorage.setItem('form', JSON.stringify(this.definitionForm.value));

    const observable = this.globalService.addTerminalDefinition(this.definitionForm.value);
    // Subscribe to the observable from the service
    observable.subscribe(response => {
      // console.log(`Response from add Terminal:`, response);
      this.handleApiResponse(response);
      if (response.respCode === '000') {
        this.getTerminal();
        this.alertBody.status = response.respCode
        this.alertBody.message = response.respMsg;
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      } else {
        this.alertBody.status = response.respCode
        this.alertBody.message = response.respMsg;
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
    });
  }


  updateCheckboxValue(detailsElement: any, groupName: string, controlName: string, isChecked: boolean): void {
    const value = isChecked ? 'Y' : 'N';
    // Assuming 'keys' is a direct group under 'definitionForm'. Adjust the path if nested differently.
    if (isChecked) {
      if (detailsElement != undefined) {
        detailsElement.setAttribute('open', '');
      }
    }
    if (!isChecked && detailsElement != undefined){
      detailsElement.removeAttribute('open');
    }
    this.definitionForm.get(groupName)?.get(controlName)?.setValue(value);
  }

  updateRadioValue(controlName: string, value: string): void {
    this.definitionForm.get(`configuration.${controlName}`)?.setValue(value);
  }


  getTerminal() {
    const terminalId ="00000001"//this.definitionForm.get('terminalDefinition.id.terminalNumber')?.value;
    const bankCode = this.bankCode; // Assuming bankCode is globally available or part of your form

    this.globalService.getTerminalDefinition({ "bankCode": this.bankCode, "terminalNumber":"00000001" }).subscribe(response => {
      if (response.respCode === '000' && response.result) {
        const data = response.result;
        // console.log("data: ", data)
        // Terminal Definition
      if (data.terminalDefinition!=null)
        this.definitionForm.patchValue({
          terminalDefinition: {
            id: {
              terminalNumber: data.terminalDefinition.id.terminalNumber,
              bankCode: data.terminalDefinition.id.bankCode
            },
            terminalLabel: data.terminalDefinition.terminalLabel,
            nomProtocol: data.terminalDefinition.nomProtocol,
            communicationIndex: data.terminalDefinition.communicationIndex,
            machineNumber: data.terminalDefinition.machineNumber,
            luno: data.terminalDefinition.luno,
            tdate: data.terminalDefinition.tdate,
            ttime: data.terminalDefinition.ttime,
            terminalRelease: data.terminalDefinition.terminalRelease,
            softwareId: data.terminalDefinition.softwareId,
            configId: data.terminalDefinition.configId
          }
        });
        if (data.note!=null)
        // Note
        this.definitionForm.patchValue({
          note: {
            id: {
              terminalNumber: data.terminalDefinition.id.terminalNumber,
              bankCode: data.terminalDefinition.id.bankCode
            },
            hcashType: data.note.hcashType,
            hcashConfig: data.note.hcashConfig,
            hcashFitness: data.note.hcashFitness,
            hcashSupplies: data.note.hcashSupplies,
            hcashReject: data.note.hcashReject,
            cassette1Config: data.note.cassette1Config,
            cassette1Fitness: data.note.cassette1Fitness,
            cassette1Supplies: data.note.cassette1Supplies,
            cassette2Config: data.note.cassette2Config,
            cassette2Fitness: data.note.cassette2Fitness,
            cassette2Supplies: data.note.cassette2Supplies,
            cassette3Config: data.note.cassette3Config,
            cassette3Fitness: data.note.cassette3Fitness,
            cassette3Supplies: data.note.cassette3Supplies,
            cassette4Config: data.note.cassette4Config,
            cassette4Fitness: data.note.cassette4Fitness,
            cassette4Supplies: data.note.cassette4Supplies
          }
        });
        if (data.printer!=null)
        // Printer
        this.definitionForm.patchValue({
          printer: {
            id: {
              terminalNumber: data.terminalDefinition.id.terminalNumber,
              bankCode: data.terminalDefinition.id.bankCode
            },
            receiptConfig: data.printer.receiptConfig,
            receiptType: data.printer.receiptType,
            receiptFitness: data.printer.receiptFitness,
            receiptSupplie: data.printer.receiptSupplie,
            receiptRollwidth: data.printer.receiptRollwidth,
            receiptLeftcol: data.printer.receiptLeftcol,
            receiptMaxline: data.printer.receiptMaxline,
            journalConfig: data.printer.journalConfig,
            journalType: data.printer.journalType,
            journalFitness: data.printer.journalFitness,
            journalSupplie: data.printer.journalSupplie,
            journalTraceonoff: data.printer.journalTraceonoff,
            statementConfig: data.printer.statementConfig,
            statementType: data.printer.statementType,
            statementFitness: data.printer.statementFitness,
            statementSupplie: data.printer.statementSupplie,
            statementRollwidth: data.printer.statementRollwidth
          }
        });
        if (data.configuration!=null)
        // Configuration
        this.definitionForm.patchValue({
          configuration: {
            id: {
              terminalNumber: data.terminalDefinition.id.terminalNumber,
              bankCode: data.terminalDefinition.id.bankCode
            },
            terminalModel: data.configuration.terminalModel,
            terminalSystemDisk: data.configuration.terminalSystemDisk,
            terminalMCRW: data.configuration.terminalMCRW,
            terminalDepository: data.configuration.terminalDepository,
            terminalNightsafe: data.configuration.terminalNightsafe,
            terminalEncryptor: data.configuration.terminalEncryptor,
            terminalSecurityCamera: data.configuration.terminalSecurityCamera,
            terminalDoorAccess: data.configuration.terminalDoorAccess,
            terminalFlexDisk: data.configuration.terminalFlexDisk,
            terminalTiBin: data.configuration.terminalTiBin,
            terminalKeyboard: data.configuration.terminalKeyboard,
            terminalDisplayVoice: data.configuration.terminalDisplayVoice,
            terminalSignageDisplay: data.configuration.terminalSignageDisplay,
            terminalMedia: data.configuration.terminalMedia,
            terminalEnvDisp: data.configuration.terminalEnvDisp,
            terminalDPM: data.configuration.terminalDPM,
            terminalDigitalAudio: data.configuration.terminalDigitalAudio,
            terminalBNA: data.configuration.terminalBNA,
            terminalCheqProcessing: data.configuration.terminalCheqProcessing
          }
        });

        if (data.keys!=null)
        // Keys
        this.definitionForm.patchValue({
          keys: {
            id: {
              terminalNumber: data.terminalDefinition.id.terminalNumber,
              bankCode: data.terminalDefinition.id.bankCode
            },
            keyAOld: data.keys.keyAOld,
            keyA: data.keys.keyA,
            keyACv: data.keys.keyACv,
            keyBOld: data.keys.keyBOld,
            keyB: data.keys.keyB,
            keyBCv: data.keys.keyBCv,
            keyMacOld: data.keys.keyMacOld,
            keyMac: data.keys.keyMac,
            keyMacCv: data.keys.keyMacCv,
            keyVisaOld: data.keys.keyVisaOld,
            keyVisa: data.keys.keyVisa,
            keyVisaCv: data.keys.keyVisaCv
          }
        });

        // console.log('Form populated with fetched data');
      } else {
        console.error('Failed to fetch terminal data or response code not 000',response.respCode);
      }
    }, error => {
      console.error('Error fetching terminal data:', error);
    });
  }


}
