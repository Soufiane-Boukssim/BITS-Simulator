import {Component, ElementRef, HostListener, Input, OnDestroy, OnInit, ViewChild} from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import {FormControl, FormGroup, Validators, FormBuilder, FormArray} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import { saveAs } from 'file-saver';

import { NavigationStart, Router } from '@angular/router';

import { jsPDF } from 'jspdf';
import autoTable from 'jspdf-autotable';
import {SenarioAtmDto} from "../model/SenarioAtmDto";
import {AtmCardService} from "../../../services/atmCardProfil/atm-card.service";
import {SenarioService} from "../../../services/senario.service";
import {SenarioExecutionService} from "../../../services/senario-execution.service";
import {OperationAtmDto} from "../model/OperationAtmDto";
import {WsAtmService} from "../../../services/ws-atm.service";
import {FieldCasesDto} from "../model/FieldCasesDto";
import {ScriptCasesDefinitionDto} from "../model/ScriptCasesDefinition";
import {TypeDefinitionScript} from "../model/TypeDefinitionScript";
import {ATMfield} from "../model/ATMfield";

let intervalId: string | number | NodeJS.Timeout | undefined;

let counteurBlockError :number = 0;
@Component({
  selector: 'app-execution-script',
  templateUrl: './execution-script.component.html',
  styleUrls: ['./execution-script.component.scss']
})
export class ExecutionScriptComponent implements OnInit,OnDestroy {
  @ViewChild('confirmModal', { read: ElementRef }) confirmModal?: ElementRef

  tab_3:boolean = false
  tab_4:boolean = false

  tab_MsgOUT:boolean = true
  tab_MsgIN:boolean = false
  tab_MsgRendue:boolean = false
  scenario : any[] = [];
  logsData: any[] = [];  // Initialize with your log data
  p: number = 0;  // Current page number
  pS: number = 0;
  itemsPerPageSelct: number = 5;

  // arrayHead = ["Operation","Date Send","Sended Data","Date Received","Data Received"]
  data:any = []


  requestBody={};

  logsDataFiltered: any[] = [];

  logsDataFilteredSearch: any[] = [];

  send: string = '';
  receive: string = '';
  advice: string = '';
  detail: string = '';

  showDetails:boolean=false;

  mergedArray: any[] = [];
  mergedArrayReceive: any[] = [];
  mergedArrayAdvice: any[] = [];
/// OUT
  messageClasse: string='';
  messageSubClass: string='';
  luno: string='';
  timeVariantNumber: string='';
  topOfReceiptTransactionFlag: string='';
  messageCoOrdinationNumber: string='';
  track2Data: string='';
  track3Data: string='';
  operationCodeData: string='';
  amountEntry: string='';
  pinBuffer: string='';
  generalPurposeBufferB: string='';
  generalPurposeBufferC: string='';
  track1Data: string='';
  lastTransactionStatus: string=''


  sendCount = 0;
  /// IN
  messageClasseIN: string = '';
  logicalUnitNumber: string = '';
  messageSequenceNumber: string = '';
  nextStateIDData: string = '';
  noteDistribuer: string = '';
  transactionSerialNumber: string = '';
  functionID: string = '';
  screenNumber: string = '';
  screenUpdateData: string = '';
  messageCoOrdinationNumberIN: string = '';
  cardReturnRetainFlag: string = '';
  printerFlag: string = '';
  printerData: string = '';


// compte rendue

  messageClasseRendue: string = '';
  logicalUnitNumberRendue: string = '';
  messageSubClassRendue: string='';
  statusDescription:string='';
  statusInfo:string="";


  @Input() numberOfLines:number = 10;
  selectedScenarioId: string | null = null;
  searchScenario: string = "";
  isLoadingSena: boolean = false;


  scripts : SenarioAtmDto[] = [];
  scenarioExecutionForm: FormGroup = new FormGroup('');

  scenarios: any = [];

  isAlertVisible: boolean = false;
  msg: string = "";


  allCases: any;
  selectedScenarioScripts: SenarioAtmDto[] = [];
  loading:boolean=false;

  isSelectScenario: boolean=false;
  isSelectCase: boolean = false;
  connected: boolean = false;
  nameButton: string = 'Connect'
  response:any
  i: any = 0;
  timeOutId: any;
  reqXml: any = '';
  resXml: any = '';
  fieldList: any = [];

  responseList: any = []
  user1:any

  fr = false;
  en = false;
  esp = false;


  showError: boolean = false;
  message = "";

  alertBody: any = {
    message: '',
    status: '000',
    open: false
  }

  isConnected: boolean = false;

  countourSend=0;
  counteurReceived=0;

  titleSelect=""
  scenariosSelect:boolean = false;



  //isoFields: any = [];
  msgsToSend:any=[];
  reqFields: any = [];
  respFields: any = [];
  hexResp: string = "";
  hexReq: string = "";
  messageJson: any = "";
  SwitchStatus: string = '0';
  dataFromTransaction: any = {
    tLog: {
      req: "",
      resp: "",
    },
    tHex: {
      req: "",
      resp: "",
    },
    tField: {
      req: "",
      resp: "",
    },
  };
  tab_1: boolean = true
  tab_2: boolean = false
  bankCode: string = "00100"
  protocol: string = ""
  language: string = ""
  expandedScenarios: { [key: string]: boolean } = {};

  isLoading: boolean = false;


  searchscenarios:string=""
  selectedOption: string = '';

  cards: any = []
  currency: string =""

  amount: number =0
  status: string = "Not Connected";
  color: string = "#c9c9c9";
  selectedScenario:any={}
  messages = {
    header: "",
    messageOut: "",
    messageIn: "",
    index: "",
    id: "",
    scenario: "",
    timeout: 0
  };

  dataToShow = {
    id: {},
    scenario: {},
    reqFormater: "",
    reqNoFormater: {},
    resFormater: "",
    resNoFormater: {},
    bankCode: "00100"
  }
  showTable:any=[]
  finalTable:any=[]
  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  caseForm: FormGroup = new FormGroup('');
  commuicationForm: FormGroup = new FormGroup({
    commType: new FormControl(''),
    instCode: new FormControl(''),
    headerComm: new FormControl(0),
    headerType: new FormControl(''),
    commIp: new FormControl(''),
    commPort: new FormControl(0),
    managMac: new FormControl(''),
    managHeader: new FormControl(''),
    msgHeader: new FormControl(''),


    chipSupported: new FormControl(''),
    timeOutComms: new FormControl(''),


    id: new FormGroup({
      commId: new FormControl(0, Validators.required),
      bankCode: new FormControl(this.bankCode, Validators.required),
    }),

  });
  webSocketSubscription:any
  ButtonConf:boolean=false

  routPath = "";


  /////


  blocktimeoutValue=0;
  blockError=false;

  echoTestAutoReply=false;
  messageechoTestAutoReply=""
  loop=false
  looptimeoutValue=0;
  autoReplyAll=false
  autoReplyAlltimeoutValue=0;
  echoTestPeriodic=false;
  echoTestPeriodictimeoutValue =0;
  private modalAleartInstall: any;


  constructor(
    private globalService: GlobalService,
    private casesscenariosService: SenarioExecutionService,
    private authService: AuthService,
    private caseSen: SenarioService,
    private atmService: AtmCardService,
    private wsService: WsAtmService,
    private http: HttpClient,
    private formBuilder:FormBuilder,
    private fb: FormBuilder,
  ) {
    this.authService.user.subscribe(
      (x: any) => {
        this.bankCode = x.bankCode;
      }
    );
  }



  //   }
  ngOnInit(): void {

    this.isScriptSelected = true;
    this.scenarioExecutionForm = this.fb.group({
      libelle: [null , Validators.required],
      senarioScriptLibelles: this.fb.array([]),
      cardProfileIssuerCode  : [null,Validators.required],
      currency: [null,Validators.required],
      amount: [null, Validators.required]


    });
    Promise.resolve().then(() => this.globalService.titleSubject.next("Scenario OperationAtmDto"));

    this.retrieveScenario();
    this.sendCount = 0;
    this.retrieveCases();
    this.logsDataFiltered = this.logsData;
    this.retrieveCards();
    // this.fetchDataLogs();
    this.getCurrencyList();
    localStorage.setItem('visited',"true");
    localStorage.removeItem('Url');
    this.p=0
    this.showTable=[]

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Execution / Testing"));
      this.en=true
    } else if (this.language === "fr") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Exécution / Essai"));
      this.fr=true
    } else if (this.language === "es") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Ejecución / Pruebas"));
      this.esp=true
    } else {
      Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }



    this.webSocketCodeInInit()

    this.getCommunication()


  }
  onPageChange(page: number) {
    this.p = page;
  }
  senarioScriptDto: OperationAtmDto = {
    id: 0,
    libelle: '',
    scriptCasesDefinitions: [],
    atmCardProfilDto: {
      id:{
        cardNo:'',
        bankCode:this.bankCode
      },
      cardDesc: '',
      accountCurr: '',
      track1: '',
      track2: '',
      track3: ''
    },
    bankCode: this.bankCode
  };
  fillForm(item: ScriptCasesDefinitionDto) {
    // Clear existing form groups
    this.caseForm.reset();

    // Set values for the form fields
    this.caseForm.patchValue({
      id: item.id,
      libelle: item.libelle,
      typeScript: item.typeScript,
      cardProfileIssuerCode: item.cardProfileIssuerCode,
      bankCode: item.bankCode
    });

    // For the 'fieldCases' array, you need to set each group individually
    const fieldsControl = this.caseForm.get('fieldCases') as FormArray;
    fieldsControl.clear();

    item.fieldCases.forEach((field: FieldCasesDto) => {
      fieldsControl.push(this.fb.group({
        popularList: field.popularList,
        value: field.value,
        atmField: this.fb.array(field.atmField.map((atmField: ATMfield) => this.fb.group({
          id: atmField.id,
          idATMfield: atmField.idATMfield,
          messageTypeField: atmField.messageTypeField,
          description: atmField.description
        }))),
        bankCode: field.bankCode
      }));
    });
  }



  onClickButton() {
    // console.log("onClickButton ",this.selectedScenario);
    // console.log("isScriptSelected ",this.isScriptSelected);
    
    if (this.isConnected && this.isScriptSelected) {
      this.casesscenariosService.processSenarioScript(this.senarioScriptDto).subscribe({
        next: (data: any) => {
          // console.log(data);
          if (data.respCode === "000") {
            this.isAlertVisible = true;
            this.msg = data.respMsg;
            //this.fetchDataLogs();

            this.messages.header = "messageIn_ATM";
            // @ts-ignore
            this.messages.scenario = this.senarioScriptDto.id;
            this.messages.id = data.id;
            this.messages.messageOut = data.result;
            this.messages.timeout = this.blocktimeoutValue * 1000;
            // console.log(this.messages);
            this.sendCount++;
            // @ts-ignore
            this.messages.index = 1;
            this.wsService.sendRequest(this.messages);

          } else {
            alert(data.respMsg);
          }
        },
        error: (error: Error) => {
          // console.log(error);
        }
      });
    }
    else if (this.isConnected && this.isScriptSelected==false) {
      // console.log("selectedScenario==>",this.selectedScenario);
      let i = 0
       
      this.selectedScenario.senarioScripts.forEach((senarioScripts:any)=>{
        // console.log("senarioScripts ==>",senarioScripts);

        this.casesscenariosService.processSenarioScript(senarioScripts).subscribe({
          next: (data: any) => {
            // console.log(data);
            if (data.respCode === "000") {
              this.isAlertVisible = true;
              this.msg = data.respMsg;
              //this.fetchDataLogs();
  
              this.messages.header = "messageIn_ATM";
              // @ts-ignore
              this.messages.scenario = this.senarioScriptDto.id;
              this.messages.id = data.id;
              this.messages.messageOut = data.result;
              this.messages.timeout = this.blocktimeoutValue * 1000;
              // console.log(this.messages);
              this.sendCount++;
              // @ts-ignore
              this.messages.index = 1;
              this.wsService.sendRequest(this.messages);

            } else {
              alert(data.respMsg);
            }

          
            // if (i == this.selectedScenario.senarioScripts.length) {
            //   this.isScriptSelected = true;
            // }
            i++;
          },
          error: (error: Error) => {
            // console.log(error);
          }
        });
      })

      
      
     
     
    }
    else {
      let message = "Please Connect";
      if (!this.isConnected && !this.isSelectCase) {
        message = this.getLocalizedMessage("Please Connect and Select a Case");
      } else if (!this.isConnected) {
        message = this.getLocalizedMessage("Please Connect");
      } else if (!this.isSelectCase) {
        message = this.getLocalizedMessage("Please Select a Case");
      }

      this.alertBody.status = message;
      this.alertBody.message = message;
      this.alertBody.open = true;
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);
    }
  }


  getLocalizedMessage(message: string) {
    if (this.fr) {
      if (message === "Please Connect") return "Veuillez vous connecter";
      if (message === "Please Select a Case") return "Veuillez sélectionner un cas";
      if (message === "Please Connect and Select a Case") return "Veuillez vous connecter et sélectionner un cas";
    }
    if (this.esp) {
      if (message === "Please Connect") return "Por favor, conectado";
      if (message === "Please Select a Case") return "Por favor, seleccione un caso";
      if (message === "Please Connect and Select a Case") return "Por favor, conectado y seleccione un caso";
    }
    return message;
  }






  


  webSocketCodeInInit(){
    // console.log("1");



    this.buttonConnect(false)
    // console.log("2");
    this.webSocketSubscription = this.wsService.messages.subscribe((message) => {
      // console.log("tes webSocketSubscription");

      if (message.type === 'open' && this.SwitchStatus === '0') {

        this.status = 'Agent connected'
        this.color = "#0AAEFB"
        this.sendMessageConnect()
        this.wsService.onMessage.subscribe(data => {
          this.messageJson = JSON.parse(data.data)
          if (this.messageJson.header == "connect") {
            if (this.messageJson.messageOut === "True") {
              this.SwitchStatus = '1'
              this.status = "Switch connected"
              // console.log('this.SwitchStatus: ', this.SwitchStatus);
              this.color = "#FBB40A";
              this.buttonConnect(true)
            }
            else {
              this.SwitchStatus = '0'
              this.status = 'Switch disconnected'
              this.color = "#FB820A"
              this.buttonConnect(false)

            }
          }
          else {
            if (this.messageJson.header == "messageIn") {
                // console.log('this.messageJson ==>: ', this.messageJson);
                this.getDataShowTable(this.messageJson)

            }
            if (this.messageJson.header == "messageOut") {

              if (this.i == 0) {

                // console.log('this.messageJson: ', this.messageJson);


                this.globalService.getResponseMessage(this.messageJson).subscribe({
                  next: (data: any) => {
                    // console.log("show data  ",data);



                  },
                  error: (error: Error) => {
                    // console.log(error);
                  }
                });

              }
            }
          }
        })
      }
      else {
        if (message.type === 'close') {
          this.buttonConnect(false)
          if (message.code === 1006) {
            this.clearAll();
            this.isConnected=false;
          }

          this.status = 'Agent Disconnected'
          this.color = "#FF0000"
          this.SwitchStatus = '0'
        }
      }
    });
  }





  getDataShowTable(dataToShow:any){
               // console.log("data show==>",dataToShow);
               
         
                  this.globalService.saveMessages({messageIn: dataToShow.messageIn, messageOut:dataToShow.messageOut}).subscribe({
                    next: (response: any) => {
                      // console.log('MessageOut and MessageIn:', this.messages.messageOut + ' ' + dataToShow);
                      // console.log('Messages saved:', response);
                      if (response.respCode === "000") {
                        // console.log("response.result ==>",response.result);
                        
                        if (!Array.isArray(this.logsDataFiltered)) {
                          this.logsDataFiltered = [];
                        }
                  
                        // Verifique se response.result é um array ou um objeto
                          if (Array.isArray(response.result)) {
                            // Adicione os novos logs ao array existente
                            this.logsDataFiltered = [...this.logsDataFiltered, ...response.result];
                          } else {
                            // Adicione o objeto diretamente ao array
                            this.logsDataFiltered = [...this.logsDataFiltered, response.result];
                          }
    
                       // this.fetchDataLogs()
                        // console.log('Messages saved successfully');
                      } else {
                        // console.log('Error saving messages:', response);
                      }
                    },
                    error: (error: Error) => {
                      // console.log('Error saving messages:', error);
                    }
                  });
                
               
            
  
              //Update the logs table
              // this.logsDataFiltered = data.logs;
              // // console.log("--->this.logsDataFiltered:", this.logsDataFiltered);
              // this.logsDataFilteredSearch = data.logs;
  }


  clearMessage(){
    this.messages={
      header: "",
      messageOut: "",
      messageIn: "",
      index: "",
      id: "",
      scenario: "",
      timeout:0
    }
  }


  //Web Socket Codes

  sendMessageConnect(){
    this.clearMessage();
    this.messages.header = "connect";
    this.messages.timeout=this.blocktimeoutValue*1000
    this.messages.messageOut = `{"ip":"${this.commuicationForm.get("commIp")?.value}","port": ${this.commuicationForm.get("commPort")?.value},"type":"${this.commuicationForm.get("commType")?.value}"}`;
    this.msgsToSend=[]
    this.msgsToSend.push(this.messages)
    // console.log('this.msgsToSend: ', this.msgsToSend);
    // this.wsService.sendMessage(JSON.stringify(this.messages))
    for (let i = 0; i < this.msgsToSend.length; i++) {
      this.msgsToSend[i].index=i
      this.wsService.sendRequest(this.msgsToSend[i]);
      // console.log(`messages[${i}], i: `, this.msgsToSend[i], "  i= ", i);
    }
    // console.log("sent");
  }

  buttonConnect(b: boolean) {
    if (this.SwitchStatus === '0') {
      this.connected = false
    }
    else
      this.connected = b
    if (b === false) {
      this.nameButton = 'Connect'
    }
    else {

      this.nameButton = 'Disconnect'
    }
  }

  open() {
    // this.close();
    this.loading=true
    this.wsService.connect()

    this.wsService.messages.subscribe((message) => {
        // console.log("tes webSocketSubscription V111");

        // console.log("message.type",message.type);

        if (message.type === 'close') {
          if (message.code === 1006) {
            this.loading=false
            this.clearAll();
            this.isConnected=false;

            this.openModalAleart();
          }

        }else if (message.type === 'open' ) {


          this.wsService.onMessage.subscribe(data => {
              this.messageJson = JSON.parse(data.data)
              // console.log('this.messageJson.header ',this.messageJson.header );

              if (this.messageJson.header == "connect") {

                // console.log('this.messageJson.messageOut',this.messageJson.messageOut );
                if (this.messageJson.messageOut === "True") {
                  this.loading=false
                  this.SwitchStatus = '1'
                  this.status = "Switch connected"
                  // console.log('this.SwitchStatus: ', this.SwitchStatus);
                  this.color = "#FBB40A";
                  this.buttonConnect(true)

                  /////////

                  // console.log("Connected successfully");
                  this.isConnected=true
                  this.alertBody.status = "000";
                  if (this.en) {
                    this.alertBody.message = "Connected successfully";
                  }
                  if (this.fr) {
                    this.alertBody.message = "Connecté avec succès";
                  }
                  if (this.esp) {
                    this.alertBody.message = "Conectado exitosamente";
                  }
                  this.alertBody.open = true;
                  setTimeout(() => {
                    this.alertBody.open = false;
                  }, 3000);

                }
                else {
                  this.SwitchStatus = '0'
                  this.status = 'Switch disconnected'
                  this.color = "#FB820A"
                  this.buttonConnect(false)
                  this.loading=false

                  ////// clear
                  this.reqFields=[]
                  this.respFields=[]
                  this.hexResp=""
                  this.hexReq=""
                  this.countourSend=0
                  this.clearAll();
                  this.clearMessage();
                  this.showTable=[];
                  this.finalTable=[]

                  /////

                  this.isConnected=false
                  this.alertBody.status = "not connected";
                  if (this.en) {
                    this.alertBody.message = "Failed to connect";
                  }
                  if (this.fr) {
                    this.alertBody.message = "Échec de la connexion";
                  }
                  if (this.esp) {
                    this.alertBody.message = "Falló la conexión";
                  }
                  this.alertBody.open = true;
                  setTimeout(() => {
                    this.alertBody.open = false;
                  }, 3000);

                }
              }
            }
          )





        }
        else{
          // console.log( "new exption");
          this.loading=false

        }

      }

    );
  }





/////test disconect ////////

  ngOnDestroy(): void {

    // console.log("Component destroyed");
    this.disconnect();
    this.wsService.disconnect()
    if (this.isConnected) {
      // console.log('test disconnect');

      window.location.reload();
    }
    window.location.reload();
  }

  clearAction() {
    this.blocktimeoutValue=0;
    this.blockError=false;

    this.echoTestAutoReply=false;
    this.messageechoTestAutoReply=""
    this.loop=false
    this.looptimeoutValue=0;
    this.autoReplyAll=false
    this.autoReplyAlltimeoutValue=0;
    this.echoTestPeriodic=false;
    this.echoTestPeriodictimeoutValue =0;


  }


  disconnect(){
    this.reqFields=[]
    this.respFields=[]
    this.hexResp=""
    this.hexReq=""
    this.countourSend=0
    this.clearAll();
    this.clearMessage();
    this.showTable=[];
    this.finalTable=[]
    this.closemodalAleart();
    this.wsService.disconnect()
    // console.log("test disconnect");

    this.isConnected=false
    this.wsService.disconnect()
    this.clearMessage();
    this.messages.header = "disconnect";
    this.messages.messageOut = `{"ip":"${this.commuicationForm.get("commIp")?.value}","port": ${this.commuicationForm.get("commPort")?.value},"type":"${this.commuicationForm.get("commType")?.value}"}`;
    this.msgsToSend=[]
    this.msgsToSend.push(this.messages)
    // console.log('this.msgsToSend: ', this.msgsToSend);
    for (let i = 0; i < this.msgsToSend.length; i++) {
      this.msgsToSend[i].index=i
      this.wsService.sendRequest(this.msgsToSend[i]);
      // console.log(`messages[${i}], i disconect : `, this.msgsToSend[i], "  i= ", i);
    }
    // console.log("sent");
    clearInterval(intervalId);
    this.p=0
    this.clearAll();
    this.clearAction();
    this.logsDataFiltered=[];

    this.sendCount=0
    this.alertBody.status = "000";
    if (this.en) {
      this.alertBody.message = "Disconnected successfully";
    }
    if (this.fr) {
      this.alertBody.message = "Déconnecté avec succès";
    }
    if (this.esp) {
      this.alertBody.message = "Desconectado exitosamente";
    }
    this.alertBody.open = true;
    setTimeout(() => {
      this.alertBody.open = false;
    }, 3000);
    this.isConnected = false;
  }

  getCommunication() {
    const id = {
      commId: 0,
      bankCode: this.bankCode, // Replace with your actual user object
    };

    // console.log('id: ', id);
    this.globalService.getComms(id).subscribe((response) => {
      // console.log('response: ', response);
      if (response.result && response.result.length > 0) {
        const firstResult = response.result[0];
        // console.log('firstResult', firstResult);

        this.isDelete = true;
        this.commuicationForm.patchValue({
          commType: firstResult.commType,
          instCode: firstResult.instCode,
          headerType: firstResult.headerType,
          headerComm: firstResult.headerComm,
          commIp: firstResult.commIp,
          commPort: firstResult.commPort,
          managMac: firstResult.managMac,
          managHeader: firstResult.managHeader,
          msgHeader: firstResult.msgHeader,
          id: firstResult.id,
          chipSupported: firstResult.chipSupported,
          timeOutComms: firstResult.timeOutComms
        });

        // console.log('this.commuicationForm: ', this.commuicationForm.value);
      } else {
        this.isDelete = false;
      }
    }, error => {
      // console.log('error: ', error);
    });
  }
  isDelete:boolean = false



  clearAll(){
    this.finalTable=[]
    this.reqXml= '';
    this.resXml = '';
    this.hexResp = "";
    this.hexReq = "";
    this.reqFields = [];
    this.caseForm.reset();
  }


  @ViewChild('modalConf', { read: ElementRef }) modalConf?: ElementRef
  openModalConf() {
    this.ButtonConf = false;
    this.modalConf?.nativeElement.showModal()
  }
  closemodalConf() {
    this.ButtonConf = false;
    this.modalConf?.nativeElement.close()
  }



  @ViewChild('modalAleart', { read: ElementRef }) modalAleart?: ElementRef
  openModalAleart() {
    this.isConnected = false;
    this.modalAleart?.nativeElement.showModal()
  }
  closemodalAleart() {
    this.clearAll();
    this.isConnected = false;
    this.modalAleart?.nativeElement.close()
  }

  pSen: number = 0;
  scriptss: any[] = [];
  retrieveScenarios(id: any): void {
    this.selectedScenarioId = id;
    this.casesscenariosService.getSenarioById(id).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.selectedScenario = data.result;
          this.selectedScenarioScripts = this.selectedScenario.senarioScriptLibelles;
        } else {
          alert(data.respMsg);
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }
  getOperations(id: number): [] {
    let s = this.scenarios.find((x: any) => x.id === id);
    return s.senarioScriptLibelles;
  }
  toggleScenario(id: any) {
    // console.log('Scenarios:', this.scenarios);
    // console.log('Provided id:', id);
    let s = this.scenarios.find((x: any) => x.id === id);
    // console.log('Found scenario:', s);
    if (s) {
      // console.log('Scenario object:', s);
      // console.log('Scenario scripts:', s.senarioScripts);
      this.expandedScenarios[id] = !this.expandedScenarios[id];
    } else {
      // console.log('No scenario found with provided id');
    }
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
          // console.log("data  this.scenarios ==>", this.scenarios );

          // console.log("data result ==>", data.result);
          
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
  retrieveCases() {
    this.isLoading=true;
    this.caseSen.getAllSenarios(this.bankCode).subscribe({
      next: (data: any) => {
        if (data.respCode === "000") {
          this.scriptss = data.result.length > 0 ? data.result : this.scriptss;
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

  closeConfirmModal() {
    this.confirmModal?.nativeElement.close();
  }
  openModal() {
    // console.log('openModal called with direction:');
    this.confirmModal?.nativeElement.showModal();
  }
  selectedLibelle: string = '';
  isScriptSelected: boolean = false;

  selectSenario(scenarioId: any) {

    // console.log("id scenarioId ==> ",scenarioId);
    // console.log("this.scenarios => ",this.scenarios);
    
    
    this.scenarios.map((scenario: SenarioAtmDto) => {
      if (scenario.id == scenarioId) {
        this.selectedScenario = scenario; 
        this.selectedLibelle = scenario.libelle;
        this.isScriptSelected = false;
      }
    });

    // console.log("selectedScenario==>",this.selectedScenario);
    // console.log("selectedLibelle==>",this.selectedLibelle);
  }


  selectScript(script: OperationAtmDto) {
    this.isScriptSelected = true;
    // console.log('Selected script:', script);

    // Store the selected script data in senarioScriptDto
    this.senarioScriptDto = script;

    const amountField = script.scriptCasesDefinitions
      .flatMap(def => def.fieldCases)
      .find(fieldCase => fieldCase.atmField[0].description === 'Amount Entry Field');

    this.scenarioExecutionForm.patchValue({
      libelle: script.libelle,
      cardProfileIssuerCode: script.atmCardProfilDto.id.cardNo,
      currency: script.atmCardProfilDto.accountCurr,
      amount: amountField ? amountField.value : 0
    });
  }
  getCurrencyList() {

    this.globalService.fetchCurrency().subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.currencyList = response.result
      }
    })
    this.clearAll()
  }

  open_tab(tab:number){
    this.tab_1 = false;
    this.tab_2 = false;
    this.tab_3 = false;
    this.tab_4 = false;
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


  open_tabInfo(tab:number){
    this.tab_MsgOUT = false;
    this.tab_MsgIN = false;
    this.tab_MsgRendue = false;
    switch (tab) {

      case 1:
        this.tab_MsgIN = true

        break;

      case 2:
        this.tab_MsgRendue = true

        break;

      default:
        this.tab_MsgOUT = true
        break;
    }
  }


  fetchDataLogs() {
    let body = {
      "bankCode":this.bankCode
    }
    // this.requestBody
    this.globalService.getDataLogs(body).subscribe(
      (response) => {
        this.logsData = response.result;
        // //  // console.log('Logs Data:', response.result);
        this.logsDataFiltered=response.result
        // console.log("--->this.logsDataFiltered:",this.logsDataFiltered);
        this.logsDataFilteredSearch=response.result
      },
      (error) => {
        console.error('Error fetching logs:', error);
      }
    );
  }







  filtreDataLogs() {
    if (this.dateFrom && this.dateTo) {
      const fromDate = new Date(this.dateFrom);
      const toDate = new Date(this.dateTo);

      // Filtrer les données en fonction des dates
      this.logsDataFiltered = this.logsDataFilteredSearch.filter((log) => {
        const logEDate = new Date(log["Date Send"]);
        const logRDate = new Date(log["Date Received"]);

        return logEDate.getTime() >= fromDate.getTime() && logEDate.getTime() <= toDate.getTime() &&
          logRDate.getTime() >= fromDate.getTime() && logRDate.getTime() <= toDate.getTime();
      })
        .map((item: any) => {
          return {
            "Date Send": item["Date Send"],
            "Sended Data": item["Sended Data"],
            "Date Received": item["Date Received"],
            "Data Received": item["Data Received"],
            "Advice": item["Advice"]
          };
        });
    } else {
      this.logsDataFiltered = this.logsDataFilteredSearch;
    }
  }
  selectedCurrency: string = '';


  formatDate(date: Date): string {
    const day = date.getDate();
    const month = ("0" + (date.getMonth() + 1)).slice(-2);
    const year = date.getFullYear();
    const hours = ("0" + date.getHours()).slice(-2);
    const minutes = ("0" + date.getMinutes()).slice(-2);
    const seconds = ("0" + date.getSeconds()).slice(-2);

    const formattedDate = `${day}-${month}-${year} ${hours}:${minutes}:${seconds}`;
    return formattedDate;
  }

  selectedCardProfileCurrency: string = '';
  currencyList: any[] = [];


  dateFrom: string | null | undefined ;
  dateTo: string | null | undefined ;

  clearInputDate(){
    this.dateFrom = null;
    this.dateTo = null;
    this.filtreDataLogs(); // Call the filter function to update the table based on the new date values
  }

  cleanUnicode(value: string): string {
    // Check if value is defined
    if (value) {
      // Filter special Unicode characters and replace them with an empty string
      return value.replace(/[^\x20-\x7E]+/g, '');
    } else {
      // If value is undefined, return an empty string
      return '';
    }
  }

  showData(data:any){
    // // console.log("data:" ,data);

    this.mergedArray=[]
    this.mergedArrayReceive=[]
    this.mergedArrayAdvice=[]
    // //  // console.log('data',data);
    // //  // console.log('data.logRData',data.logRData);


    this.advice = data['Advice'];
    // //  // console.log("advice",data['Advice']);


    this.showDetails=true

    // Formater les données d'envoi
    // let formattedSendDataTest = this.formatData(data['Sended Data']);
    let formattedSendDataTest = this.formatData(data.logEData);
    //  // console.log('formattedSendDataTest', formattedSendDataTest);

    let resultArray: any[] = [];

    for (let i = 0; i < formattedSendDataTest.length; i += 60) {
      let chunk = formattedSendDataTest.substr(i, 60);
      resultArray.push({ "HexUtil": chunk });
    }
    //  // console.log('dataArray', resultArray);



    // let formattedSendDataTestV2 = this.formatDataV2(data['Sended Data']);
    let formattedSendDataTestV2 = this.formatDataV2(data.logEData);
    //  // console.log('formattedSendDataTestV2', formattedSendDataTestV2);

    let resultArrayV2: any[] = [];

    for (let i = 0; i < formattedSendDataTestV2.length; i += 40) {
      let chunk = formattedSendDataTestV2.substr(i, 39);
      resultArrayV2.push({ "HexUtilV2": chunk });
    }
    //  // console.log('dataArrayV2', resultArrayV2);



    // Assurez-vous que les deux tableaux ont la même longueur
    if (resultArray.length === resultArrayV2.length) {
      // Créer un nouveau tableau pour stocker les éléments combinés

      let label = 0;

      // Fusionner les éléments des deux tableaux
      for (let i = 0; i < resultArray.length; i++) {
        let mergedItem = {
          "label": label.toString().padStart(4, '0'),
          "HexUtil": resultArray[i].HexUtil,
          "HexUtilV2": resultArrayV2[i].HexUtilV2
        };
        this.mergedArray.push(mergedItem);
        label += 20;
      }
      // Afficher le tableau fusionné
      //  // console.log(this.mergedArray);

    } else {
      //  // console.log("Les tableaux n'ont pas la même longueur, impossible de fusionner.");
    }



    /////////////////////

    // Formater les données d'envoi
    // let formattedReceiveDataTest = this.formatDataReceive(data['Data Received']);
    let formattedReceiveDataTest = this.formatDataReceive(data.logRData);
    // // console.log("formattedReceiveDataTest",formattedReceiveDataTest);

    let resultArrayReceive: any[] = [];

    for (let i = 0; i < formattedReceiveDataTest.length; i += 60) {
      let chunk = formattedReceiveDataTest.substr(i, 60);
      resultArrayReceive.push({ "HexUtil": chunk });
    }
    //  // console.log('resultArrayReceive', resultArrayReceive);





    // let formattedReceiveDataTestV2 = this.formatDataV2(data['Data Received']);
    let formattedReceiveDataTestV2 = this.formatDataV2(data.logRData);
    //  // console.log('formattedReceiveDataTestV2', formattedReceiveDataTestV2);

    let resultArrayReceiveV2: any[] = [];

    for (let i = 0; i < formattedReceiveDataTestV2.length; i += 40) {
      let chunk = formattedReceiveDataTestV2.substr(i, 39);
      resultArrayReceiveV2.push({ "HexUtilV2": chunk });
    }
    //  // console.log('resultArrayReceiveV2', resultArrayReceiveV2);

    // Formater les données d'envoi
    // let formattedAdviceData = this.formatDataAdvice(data['Advice']);
    let formattedAdviceData = this.formatDataAdvice(data.logECptRnd);
    let resultArrayAdvice: any[] = [];
    // console.log("test data.logECptRnd",formattedAdviceData);


    for (let i = 0; i < formattedAdviceData.length; i += 60) {
      let chunk = formattedAdviceData.substr(i, 60);
      resultArrayAdvice.push({ "HexUtil": chunk });
    }
    // console.log('resultArrayAdvice',resultArrayAdvice);



    //  let formattedAdviceDataTestV2 = this.formatDataAdviceV2(data['Advice']);
    let formattedAdviceDataTestV2 = this.formatDataAdviceV2(data.logECptRnd);
    let resultArrayAdviceV2: any[] = [];
    for (let i = 0; i < formattedAdviceDataTestV2.length; i += 40) {
      let chunk = formattedAdviceDataTestV2.substr(i, 39);
      resultArrayAdviceV2.push({ "HexUtilV2": chunk });
    }
    // console.log('resultArrayAdviceV2',resultArrayAdviceV2);

    if (resultArrayAdvice.length === resultArrayAdviceV2.length) {
      // Créer un nouveau tableau pour stocker les éléments combinés


      let label = 0;

      // Fusionner les éléments des deux tableaux
      for (let i = 0; i < resultArrayAdvice.length; i++) {
        let mergedItem = {
          "label": label.toString().padStart(4, '0'),
          "HexUtil": resultArrayAdvice[i].HexUtil,
          "HexUtilV2": resultArrayAdviceV2[i].HexUtilV2
        };
        this.mergedArrayAdvice.push(mergedItem);
        label += 20;
      }

    }



    // this.send=data['Sended Data']
    this.send=data.logEData;
    let formattedSendData = this.send.split(/[^\x20-\x7E]+/);
    //  // console.log('formattedSendData', formattedSendData);


    this.messageClasse = formattedSendData[0].charAt(0) + ' -> Transaction Request';
    this.messageSubClass = formattedSendData[0].charAt(1);
    this.luno = formattedSendData[1];
    //this.timeVariantNumber = formattedSendData[];
    this.topOfReceiptTransactionFlag = formattedSendData[2].charAt(0);
    this.messageCoOrdinationNumber = formattedSendData[2].charAt(1);
    this.track2Data = formattedSendData[3];
    //this.track3Data = formattedSendData[6];
    this.operationCodeData = formattedSendData[4];
    this.amountEntry = formattedSendData[5];
    this.pinBuffer = formattedSendData[6];
    //this.generalPurposeBufferB = formattedSendData[10];
    //this.generalPurposeBufferC = formattedSendData[11];
    this.track1Data = formattedSendData[7];
    this.lastTransactionStatus = formattedSendData[8];



    // let formattedReceiveData = data['Data Received'].split(/[^\x20-\x7E]+/);
    let formattedReceiveData = data.logRData.split(/[^\x20-\x7E]+/);
    // console.log('formattedReceiveData', formattedReceiveData);

    this.messageClasseIN=formattedReceiveData[0] + ' -> Transaction Reply';
    this.logicalUnitNumber=formattedReceiveData[1]
    this.messageSequenceNumber=formattedReceiveData[2]
    this.nextStateIDData=formattedReceiveData[3]
    this.noteDistribuer=formattedReceiveData[4]
    this.transactionSerialNumber=formattedReceiveData[5].substring(0, 4)
    this.functionID=formattedReceiveData[5].substring(4, 1)
    this.screenNumber=formattedReceiveData[5].substring(5, 3)
    this.screenUpdateData=formattedReceiveData[5].substring(8)
    this.messageCoOrdinationNumberIN=formattedReceiveData[6].substring(0, 1)
    this.cardReturnRetainFlag=formattedReceiveData[6].substring(1, 1)


    // let AdvideDataFormated = data['Advice'].split(/[^\x20-\x7E]+/);
    let AdvideDataFormated = data.logECptRnd.split(/[^\x20-\x7E]+/);
    // // console.log('AdvideDataFormated',AdvideDataFormated);


    this.messageClasseRendue= AdvideDataFormated[0].charAt(0)+'-> Transaction Reply';
    this.messageSubClassRendue=AdvideDataFormated[0].charAt(1);
    this.logicalUnitNumberRendue=AdvideDataFormated[1];
    this.statusDescription=AdvideDataFormated[2];
    this.statusInfo=AdvideDataFormated[3]


    if (formattedReceiveData[6].length > 5)
    {
      this.printerFlag= formattedReceiveData[6].substring(2, 1)
      this.printerData= formattedReceiveData[6].substring(3)
    }



    // Assurez-vous que les deux tableaux ont la même longueur
    if (resultArrayReceive.length === resultArrayReceiveV2.length) {

      // Créer un nouveau tableau pour stocker les éléments combinés

      let label = 0;

      // Fusionner les éléments des deux tableaux
      for (let i = 0; i < resultArray.length; i++) {

        let mergedItem = {
          "label": label.toString().padStart(4, '0'),
          "HexUtil": resultArrayReceive[i].HexUtil,
          "HexUtilV2": resultArrayReceiveV2[i].HexUtilV2
        };
        this.mergedArrayReceive.push(mergedItem);
        label += 20;
      }

      //  // console.log('mergedArrayReceive',this.mergedArrayReceive);

    }












  }




  formatData(data: string): string {
    let formattedData = '';
    for (let i = 0; i < data.length; i++) {
      let char = data.charCodeAt(i).toString(16).toUpperCase();
      if (char.length < 2) {
        char = '0' + char; // ajouter un zéro pour les caractères hexadécimaux à un chiffre
      }
      formattedData += char + ' ';
    }
    return formattedData.trim(); // supprimer l'espace final
  }

  formatDataV2(data: string): string {
    let formattedData = '';
    for (let i = 0; i < data.length; i++) {
      let char = data[i];
      if (/[\x20-\x7E]/.test(char)) {
        formattedData += char + ' ';
      } else {
        formattedData += '# ';
      }
    }
    return formattedData.trim();
  }

  formatDataAdvice(data: string): string {
    let formattedData = '';
    for (let i = 0; i < data.length; i++) {
      let char = data.charCodeAt(i).toString(16).toUpperCase();
      if (char.length < 2) {
        char = '0' + char; // ajouter un zéro pour les caractères hexadécimaux à un chiffre
      }
      formattedData += char + ' ';
    }
    return formattedData.trim(); // supprimer l'espace final
  }

  formatDataAdviceV2(data: string): string {
    let formattedData = '';
    for (let i = 0; i < data.length; i++) {
      let char = data[i];
      if (/[\x20-\x7E]/.test(char)) {
        formattedData += char + ' ';
      } else {
        formattedData += '# ';
      }
    }
    return formattedData.trim();
  }


  formatDataReceive(data: string): string {
    let formattedData = '';
    for (let i = 0; i < data.length; i++) {
      let char = data.charCodeAt(i).toString(16).toUpperCase();
      if (char.length < 2) {
        char = '0' + char; // ajouter un zéro pour les caractères hexadécimaux à un chiffre
      }
      formattedData += char + ' ';
    }
    return formattedData.trim(); // supprimer l'espace final
  }

  formatDataReceiveV2(data: string): string {
    let formattedData = '';
    for (let i = 0; i < data.length; i++) {
      let char = data[i];
      if (/[\x20-\x7E]/.test(char)) {
        formattedData += char + ' ';
      } else {
        formattedData += '# ';
      }
    }
    return formattedData.trim();
  }


  closemodalAleartInstall() {
    this.modalAleartInstall?.nativeElement.close()

  }

}
