import {Component, ElementRef, HostListener, OnDestroy, OnInit, ViewChild} from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import {FormControl, FormGroup, Validators, FormBuilder, FormArray} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import { saveAs } from 'file-saver';
import {WsService} from "../../../services/ws.service";
import { NavigationStart, Router } from '@angular/router';

import { jsPDF } from 'jspdf';
import autoTable from 'jspdf-autotable';
import { WebsocketService } from 'src/app/services/icc/websocket.service';
import { MatTreeNestedDataSource } from '@angular/material/tree';

let intervalId: string | number | NodeJS.Timeout | undefined;

let counteurBlockError :number = 0;
@Component({
  selector: 'app-testing-execution',
  templateUrl: './testing-execution.component.html',
  styleUrls: ['./testing-execution.component.scss']
})
export class TestingExecutionComponent implements OnInit,OnDestroy {
  downloadFile() {
    this.http.get('../../../assets/agent/agent.txt', { responseType: 'blob' }).subscribe(data => {
      saveAs(data, 'fileName.txt');
    });
  }
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


  pin_Institution=""


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
  tab_3: boolean = false
  bankCode: string = ""
  protocol: string = ""
  language: string = ""
  expandedScenarios: { [key: string]: boolean } = {};
  scenarios: any = []
  scenarioIdd:any;
  isLoading: boolean = false;


  searchscenarios:string=""
  selectedOption: string = '';

  cards: any = []
  merchants: any = []
  terminals: any = []
  currencies: any = []
  currency: string =""
  cardNo=""
  merCode=""
  termCode=""

  amount: number =0
  status: string = "Not Connected";
  color: string = "#c9c9c9";
  selectedScenario:any={}
  messages = {
    header: "",
    messageOut: "",
    messageIn: "",
    index: 0,
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
    bankCode: ""
  }
  p = 0
  pS=0
  showTable:any=[]
  finalTable:any=[]
  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  caseForm: FormGroup = new FormGroup('');
  communicationForm: FormGroup = new FormGroup({
    commType: new FormControl(''),
    instCode: new FormControl(''),
    headerComm: new FormControl(0),
    headerType: new FormControl(''),
    commIp: new FormControl(''),
    commPort: new FormControl(0),
    managMac: new FormControl(''),
    managHeader: new FormControl(''),
    msgHeader: new FormControl(''),
    timeOutComms:new FormControl(''),

    id: new FormGroup({
      commId: new FormControl(0, Validators.required),
      commProtocol: new FormControl(this.protocol, Validators.required),
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

//// 


  id_Datashow={}
  id_caseForm_send={} 
  timeSend=6000
 
  
  constructor(
    private globalService: GlobalService,
    private auth: AuthService,
    private fb: FormBuilder,
    private http: HttpClient,
    private wsService: WsService,
    private router: Router,
  
   
  ) { 

    this.router.events.subscribe(event => {

      if (event instanceof NavigationStart) {
         // console.log('test event',event);
         this.routPath = event.url;
         // console.log("test url" ,event.url);

         if (event.url=="/user/iso/testing-execution") {
          // console.log("url OK");
         }else if (event.url !="/user/iso/testing-execution" && this.ButtonConf==true) {

          localStorage.setItem("Url",event.url);
          this.closeModal()
          window.location.reload();
         }else{
             if (this.isConnected) {
              // console.log('url KO');
              this.router.navigateByUrl('/user/iso/testing-execution');
              this.openModalConf();
             }
         }


      }
    });
  }

  isPageChangeConfirmed() {
    this.ButtonConf=true
    this.router.navigateByUrl(this.routPath);
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


  handleCheckboxChange(t: string) {
    if (t == 'blockError') {
       this.blockError=!this.blockError
    }
    if(t=="echoTestAutoReply"){
      this.echoTestAutoReply=!this.echoTestAutoReply

      if(this.echoTestAutoReply){

        // console.log("echoTestAutoReply changed true" );

        const data = this.scenarios;
        // console.log("data: " ,data);


        const filteredData = data.map((item: { id: any; caseSetDesc: any; casesDefinitions: any[]; }) => ({
                id: item.id,
                caseSetDesc: item.caseSetDesc,
                casesDefinitions: item.casesDefinitions.filter(def => def.caseDirection === "req" && def.caseAction === "ET")
            }))
            .filter((item: { casesDefinitions: string | any[]; }) => item.casesDefinitions.length > 0);

        // console.log("data  echoTestAutoRepl ", filteredData);
        if (filteredData.length>0 && filteredData!=null){
          // console.log("echo test deja definer");
          this.messageechoTestAutoReply=""

        }else{
          this.echoTestAutoReply=false
          this.messageechoTestAutoReply="Provide the definitions of case, 'echo'"
        }

      }
    }
    if(t=="loop"){
      this.loop=!this.loop
    }
    if(t=="autoReplyAll"){
      this.autoReplyAll=!this.autoReplyAll
    }
    if(t=="echoTestPeriodic"){
      this.echoTestPeriodic=!this.echoTestPeriodic
    }
  }


  saveAction(){
    // console.log("blockedErrorAction==>",this.blockError);
    // console.log("blockErrorTimeValue===>",this.blocktimeoutValue);

    this.closeModal()
  }



  // ngOnDestroy(): void {
  // // this.disconnect()
  // // // console.log("I'm destroyed");
  // // this.showTable=[];
  // // this.wsService.disconnect()

  //   }
  ngOnInit(): void {

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

    // Promise.resolve().then(() => this.globalService.titleSubject.next("Testing Execution"))
    this.globalService.protocol.subscribe(val => {
      if (val) {
        // console.log(val)
        this.protocol = val
      }
    })
    if (!this.protocol) {
      this.protocol = localStorage.getItem('protocol') as string
    }
    this.language = localStorage.getItem('lang') || 'en'
    // console.log('this.protocol: ', this.protocol);
    this.auth.user.subscribe(val => {
      this.bankCode = val.bankCode
    })



    this.webSocketCodeInInit()
    this.clearFormGroup()
    this.retrieveCards()
    this.getSenarios()
    this.retrieveMerchants()
    this.retrieveTerminals()
    this.retrieveCurrency()

    this.getFields();
    this.getAllComms()
    this.getResponseList();
    this.getOneInstitutionParam()

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

              if (this.i == 0) {

                // console.log('this.messageJson: ', this.messageJson);
                clearTimeout(this.timeOutId)
                this.dataToShow.id=this.messageJson.id
                this.dataToShow.bankCode=this.bankCode
                this.dataToShow.scenario=this.messageJson.scenario
                this.dataToShow.reqFormater=this.messageJson.messageIn
                this.dataToShow.resFormater=this.messageJson.messageOut
                this.id_Datashow=this.messageJson.id;

                // console.log("this.id_Datashow ==>",this.id_Datashow);
                // console.log("this.id_caseForm_send ==>",this.id_caseForm_send);


                this.getDataToShow(this.dataToShow)


              }
            }
            if (this.messageJson.header == "messageOut") {

              if (this.i == 0) {

                // console.log('this.messageJson messageOut ===>:  ', this.messageJson);


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

            if (this.messageJson.header == "keyExchange") {
              const body={

                data:this.messageJson.messageIn,
                protocolCode:this.protocol
              }
              this.globalService.getMsgKeyExchange(body).subscribe(res=>{
                if (res.respCode==="000") {
                  const resultObj = JSON.parse(res.result);
                  const messageOut_api = resultObj.messageOut; 


                  this.messages.header = "messageIn"
                  this.messages.scenario = this.messageJson.scenario
                  this.messages.id = this.messageJson.id
                  this.messages.messageOut = this.messageJson.messageIn;
                  this.messages.timeout=this.blocktimeoutValue*1000
                  // console.log(this.messages)
      
                  this.messages.index=1
                  this.wsService.sendRequest(this.messages);
                 
                }else{
                  // console.log("error getMsgKeyExchange !");
                  
                }
              })
           
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
            // if (confirm("Please start agent Or Install It if you don't have it.\n Do you want to install it?")) {
            //   // console.log("Implement delete functionality here");
            //   this.downloadFile();
            // }

            // this.openModalAleart();
          }

          this.status = 'Agent Disconnected'
          this.color = "#FF0000"
          this.SwitchStatus = '0'
        }
      }
    });
  }



  areIdsEqual(id1: any, id2: any): boolean {
    return id1.caseNo === id2.caseNo &&
           id1.caseProtocole === id2.caseProtocole &&
           id1.caseType === id2.caseType &&
           id1.bankCode === id2.bankCode;
}



  openModal() {
    this.modal?.nativeElement.showModal()
  }
  closeModal() {
    this.modal?.nativeElement.close()
  }
  open_tab(index: number) {
    if (index == 0) {
      this.tab_1 = true
      this.tab_2 = false
      this.tab_3 = false
    } else if (index == 1) {
      this.tab_1 = false
      this.tab_2 = true
      this.tab_3 = false
    } else {
      this.tab_1 = false
      this.tab_2 = false
      this.tab_3 = true
    }

  }
  clearFormGroup() {
    this.caseForm = this.fb.group({
      id: this.fb.group({
        caseNo: [''],
        caseProtocole: [''],
        caseType: [''],
        bankCode: ['']
      }),
      caseDesc: [''],
      caseDirection: [''],
      caseCardRef: [''],
      caseMerchantPrf: [''],
      caseTerminalPrf: [''],
      caseAction: [''],
      caseAmount: [0],
      caseHeader: [''],
      caseMti: [''],
      fields: this.fb.array(this.createFields())
    });
  }

  createFields() {
    return [
      this.fb.group({
        fieldDef: this.fb.group({
          id: this.fb.group({
            fieldId: [''],
            fieldProtocole: [''],
            bankCode: ['']
          }),
          fieldDesc: [''],
          fieldName: [''],
          fieldType: [''],
          fieldLengthMax: [''],
          fieldFormatCheck: [''],
          fieldFormatStr: ['']
        }),
        value: ['']
      }),
      // ... Repeat for other field objects
    ];

  }
  get fields(): FormArray {
    return this.caseForm.get('fields') as FormArray;
  }



  toggleScenario(scenarioTitle: any): void {
    // console.log('scenarioTitle: ', scenarioTitle);
    this.expandedScenarios[scenarioTitle] = !this.expandedScenarios[scenarioTitle];
    // console.log('expandedScenarios[scenario.title]: ', this.expandedScenarios[scenarioTitle]);
  }


  getSenarios() {
    this.isLoading = true;
    let id = {
      bankCode: this.bankCode,
      caseSetProtocole: this.protocol

    }
    this.globalService.getScenarios(id).subscribe(
      data => {
          // console.log('data: ', data);
          this.scenarios = data.result
          this.isLoading = false;
       },
       error => {
        console.error('Une erreur s\'est produite : ', error);
        this.isLoading = false; // Désactiver le loader en cas d'erreur
      }

       )
  }




    //response
    getResponseList() {
      let id = {
        "bankCode": this.bankCode,
        "respProtocol": this.protocol
      }
      // console.log('id: ', id);
      this.globalService.fetchResponse(id).subscribe(response => {

        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.responseList = response.result
          // console.log('this.responseList: ', this.responseList);
        }
      })
      this.clearAll()
    }

  selectCase(id:any,scenarioId:any){
    this.scenariosSelect=false
    this.scenarios.map((cases:any)=>{
    cases.casesDefinitions.map((item:any)=>{
      if (cases.id==scenarioId && item.id==id){
        this.isSelectScenario=false;
        this.isSelectCase=true;
        // console.log("item: ",item)
        this.selectedScenario=cases;
        this.fillForm(item);
        // console.log(this.caseForm.value);
        // // console.log("test des case",this.caseForm.value?.caseDesc);
        this.titleSelect=this.caseForm.value?.caseDesc
      }
    })
    })
  }

  selectScenario(scenarioId:any){ 

    this.scenarioIdd=scenarioId
    this.scenarios.map((cases:any)=>{
      if (cases.id==scenarioId){
          // // console.log("selected scenario",cases.caseSetDesc);
          this.scenariosSelect=true
          this.titleSelect=cases.caseSetDesc
      }
      cases.casesDefinitions.map((item:any)=>{
        if (cases.id==scenarioId && item.caseDirection=="req"){
          this.isSelectScenario=true;
          this.isSelectCase=false;
          // console.log("item: ",item)
          this.fillForm(item);
          this.selectedScenario=cases
          // // console.log(this.caseForm.value);
          // // console.log("test selectScenario",this.caseForm.value?.caseDesc);
          // this.titleSelect=this.caseForm.value?.caseDesc
        }
      })
    })
  }


//   prepareMessageWithDelay(caseForm: any, scenario: any, index: any, delay: number): Promise<any> {
//     return new Promise((resolve) => {
//         setTimeout(() => {
//             const message = this.prepareMessageForCase(caseForm, scenario, index);
//             resolve(message);
//         }, delay);
//     });
// }


  async sendMessage() {
    if (this.isConnected) {
      //  if (this.blockError==true && this.blocktimeoutValue >0 ) {
      //     counteurBlockError++;
      //  }

      // console.log("timeOutComms ==>",this.communicationForm.get("timeOutComms")?.value);

      // // console.log("data caseForm===>",this.caseForm?.value);
      // // console.log("amount===>",this.amount);
      
       

        let i = 0
        this.clearMessage();
        if (this.isSelectCase) {
          this.countourSend+=1
          // Prepare message for single case
          const message = this.prepareMessageForCase(this.caseForm, this.selectedScenario, i);

        } else if (this.isSelectScenario) {
          // Prepare messages for all cases in the selected 
          
          let isFirstExecution = true;

          this.selectedScenario.casesDefinitions.forEach((caseItem: any) => {
            if (caseItem.caseDirection == "req") {
              this.fillForm(caseItem)
              this.id_caseForm_send=this.caseForm.value?.id
              const message = this.prepareMessageForCase(this.caseForm, this.selectedScenario, i);
              this.countourSend+=1
              i++
              // console.log("this.caseForm  ===>",this.caseForm?.value);
             
            }

        
            
          });

          /**** */

        //   for (const caseItem of this.selectedScenario.casesDefinitions) {
        //     if (caseItem.caseDirection == "req") {
        //         this.fillForm(caseItem);
        //         await this.prepareMessageWithDelay(this.caseForm, this.selectedScenario, i, 6000);
        //         this.countourSend += 1;
        //         i++;
        //     }
        // }
        }
    }else{
      this.alertBody.status = "Please Connected";
      if (this.en) {
          this.alertBody.message = "Please Connected";
      }
      if (this.fr) {
          this.alertBody.message = "Veuillez vous connecter";
      }
      if (this.esp) {
          this.alertBody.message = "Por favor, conectado";
      }
      this.alertBody.open = true;
      setTimeout(() => {
          this.alertBody.open = false;
      }, 3000);
    }



  }





  //********* Select ECho test  */

 // Dans le composant
    logCaseDescription(caseDescription: any) {
      // console.log("logCaseDescription ",caseDescription);
    }



  
  
  createFieldsAction(fields: any[]) {
      return fields.map(field => this.fb.group({
          fieldDef: this.fb.group({
              id: this.fb.group({
                  fieldId: [field.fieldDef.id.fieldId],
                  fieldProtocole: [field.fieldDef.id.fieldProtocole],
                  bankCode: [field.fieldDef.id.bankCode]
              }),
              fieldDesc: [field.fieldDef.fieldDesc],
              fieldName: [field.fieldDef.fieldName],
              fieldType: [field.fieldDef.fieldType],
              fieldLengthMax: [field.fieldDef.fieldLengthMax],
              fieldFormatCheck: [field.fieldDef.fieldFormatCheck],
              fieldFormatStr: [field.fieldDef.fieldFormatStr]
          }),
          value: [field.value]
      }));
  }

    ChangeActionSend() {



      const data = this.scenarios;
      let i = 0
      const filteredData = data
          .map((item: { id: any; caseSetDesc: any; casesDefinitions: any[]; }) => ({
              id: item.id,
              caseSetDesc: item.caseSetDesc,
              casesDefinitions: item.casesDefinitions.filter(def =>  def?.caseAction === this.selectedOption)
          }))
          .filter((item: { casesDefinitions: string | any[]; }) => item.casesDefinitions.length > 0);
  
      // console.log("data prepareMessageForCaseAction V1", filteredData[0]);
   

   
 if (this.isConnected) {


        this.countourSend+=1

        let i = 0
        this.clearMessage();
       
        
        const message = this.prepareMessageForCaseAction(filteredData[0], this.selectedScenario, i);


    }else{
      this.alertBody.status = "Please Connected";
      if (this.en) {
          this.alertBody.message = "Please Connected";
      }
      if (this.fr) {
          this.alertBody.message = "Veuillez vous connecter";
      }
      if (this.esp) {
          this.alertBody.message = "Por favor, conectado";
      }
      this.alertBody.open = true;
      setTimeout(() => {
          this.alertBody.open = false;
      }, 3000);
    }


  }


    prepareMessageForCaseAction(filteredData:any, scenarioToSend:any, index:any) :{     header: string;     messageOut: string;     messageIn: string;     index: number;     id: string;     scenario: string; } {
      const data =filteredData;
      // // console.log("data prepareMessageForCaseAction  V2 ", data)
      // console.log("data.id.caseType==>",data);
      
    
    
      // console.log("excution api  getBuffer -> ");
        this.globalService.getBuffer(data,this.protocol).subscribe({
          next: (data1: any) => {
            // console.log("data getBuffer -> ");
            // console.log("data getBuffer -> ",data1);
            if (data1.respCode === "000") {
              const resultData = JSON.parse(data1.result);
              this.messages.header = "echo"
              this.messages.scenario = scenarioToSend.id
              this.messages.id = data.casesDefinitions.id
              this.messages.messageOut = resultData.messageOut;
              // console.log(this.messages)
              this.messages.timeout=this.blocktimeoutValue*1000
              this.messages.index=index
              this.wsService.sendRequest(this.messages);
              // Logic to prepare message for a single case
              // Return the message object
            }
          }
        })
      
    


      return this.messages
    }



  ////  *************** end  send echo test **********************///////////


 ////  **************** **********************///////////

  echoTestExution(){
    // console.log("echo test======>");
    
    const data = this.scenarios;
    let i = 0
    const filteredData = data
        .map((item: { id: any; caseSetDesc: any; casesDefinitions: any[]; }) => ({
            id: item.id,
            caseSetDesc: item.caseSetDesc,
            casesDefinitions: item.casesDefinitions.filter(def =>  def?.caseAction === this.selectedOption)
        }))
        .filter((item: { casesDefinitions: string | any[]; }) => item.casesDefinitions.length > 0);

    // console.log("data prepareMessageForCaseAction V1 echoTestExution", filteredData[0]);


    // console.log("echoTestPeriodic",this.echoTestPeriodic);
    // console.log("echoTestPeriodictimeoutValue,",this.echoTestPeriodictimeoutValue);
    
    
     if (this.isConnected && this.echoTestPeriodic==true &&  this.echoTestPeriodictimeoutValue>0) {


      intervalId =setInterval(() => {
        this.countourSend += 1;
        let i = 0;
        this.clearMessage();
        const message = this.prepareMessageForCaseAction(filteredData[0], this.selectedScenario, i);
      }, this.echoTestPeriodictimeoutValue * 1000);

     }


  }




  ////  **************************************///////////



  prepareMessageForCase(caseItem:any, scenarioToSend:any, index:any) :{
    header: string;     messageOut: string;     messageIn: string;     index: number;     id: string;     scenario: string;

  }
  {
    const data = this.caseForm.value;
    let body;
    // console.log("scenarios ====>",this.scenarios)
    // console.log("data ==> ", data)
    // console.log("scenarioId   ==>",this.scenarioIdd);
    // console.log("caseItem==>",caseItem.value);
    //// console.log("caseItem==>",caseItem?.value?.id?.caseNo);
    

    const caseNum=caseItem?.value?.id?.caseNo

    // caseNum=caseItem?.value?.id?.caseNo
    
  
    // console.log("caseNum v1",caseNum);
    
    
  //  if (this.isSelectScenario) {
  //    body = this.scenarios.find((scenario: { casesDefinitions: any[]; }) => {
  //     return scenario.casesDefinitions.some((definition: { id: { caseNo: any; }; }) => definition.id.caseNo === data.id.caseNo);
  //   });
  
  //  // console.log("body ==> ", body);
  //   // console.log("caseItem test ===>",caseItem);
  //  }

  if (this.isSelectScenario) {
    // Find the scenario based on the scenarioId
    body = this.scenarios.find((scenario: { id: { caseSetId: any; caseSetProtocole: any; bankCode: any; }; casesDefinitions: any[]; }) => {
        return (
            scenario.id.caseSetId === this.scenarioIdd?.caseSetId &&
            scenario.id.caseSetProtocole === this.scenarioIdd?.caseSetProtocole &&
            scenario.id.bankCode === this.scenarioIdd?.bankCode 

        );
    });

    // console.log("caseNum v2",caseNum);
    // console.log("body V1 ==> ", body);


    const filteredCases = body.casesDefinitions.filter((caseDef: { id: { caseNo: string; }; }) => caseDef.id.caseNo === caseNum);
    // console.log("Filtered Cases: ", filteredCases);

    body==filteredCases

    body = { ...body, casesDefinitions: filteredCases };
    // console.log("Updated body ==> ", body);
     // console.log("body v2",caseNum);
    
}


// console.log("this.isSelectCase==>",this.isSelectCase);
// console.log("caseNum v3",caseNum);

    if (this.isSelectCase) {
      const filteredBody = this.scenarios.find((scenario: { casesDefinitions: any[]; }) => {
        return scenario.casesDefinitions.some((definition: { id: { caseNo: any; }; }) => definition.id.caseNo === data.id.caseNo);
      });
        body = {
        id: filteredBody.id,
        caseSetDesc: filteredBody.caseSetDesc,
        casesDefinitions: filteredBody.casesDefinitions.filter((definition: { caseDesc: any; }) => {
            return definition.caseDesc === this.titleSelect;
        })
    };
    
      // console.log("filteredBody body ==> ", body);
   


    // console.log("this cardNo==>",this.caseForm.get("caseCardRef")?.value);
    
   

       // Modifier la valeur du champ avec fieldId == 4
       body.casesDefinitions.forEach((definition: { caseCardRef: string;  caseMerchantPrf: string; caseTerminalPrf: string;  fields: any[]; }) => {
        definition.caseCardRef = this.caseForm.get("caseCardRef")?.value;
        definition.caseMerchantPrf = this.caseForm.get("caseMerchantPrf")?.value;
        definition.caseTerminalPrf = this.caseForm.get("caseTerminalPrf")?.value;

        definition.fields.forEach((field: { fieldDef: { id: { fieldId: number; }; }; value: string; }) => {
            if (field.fieldDef.id.fieldId === 4) {
                field.value = this.amount.toString(); // Convertir le nombre en chaîne de caractères
            }
            if (field.fieldDef.id.fieldId === 49) {
                field.value = this.currency; // Convertir le nombre en chaîne de caractères
            }
        });
      });

      // console.log("Modified body ==> ", body);
    }



    data.fields.find((f: any) => {
      if (f.fieldDef.id.fieldId == 49)
        f.value = this.currency
    })
    data.fields.find((f: any) => {
      if (f.fieldDef.id.fieldId == 4)
        f.value = this.amount
    })
    // console.log("data.id.caseType===>",data.id.caseType);
    // console.log("body send to getBuffer ==>",body);
    if(data.id.caseType==="I"  )
      // this.globalService.getBuffer(data,"SS").subscribe({
     this.globalService.getBuffer(body,this.protocol).subscribe({
        next: (data1: any) => {
          // console.log("data getBuffer -> ",data1);
          if (data1.respCode === "000") {
            const resultData = JSON.parse(data1.result);
            this.messages.header = "messageIn"
            this.messages.scenario = scenarioToSend.id
            this.messages.id = data.id
            this.messages.messageOut = resultData.messageOut;
            this.messages.timeout=this.blocktimeoutValue*1000
            // console.log(this.messages)

            this.messages.index=index
            this.wsService.sendRequest(this.messages);
            // Logic to prepare message for a single case
            // Return the message object
          }
        }
      })
    else {

      this.messages.header = "messageOut"
      this.messages.scenario = scenarioToSend.id
      this.messages.id = data.id
      this.messages.index=index
      this.messages.timeout=this.blocktimeoutValue*1000
      this.wsService.sendRequest(this.messages);
    }
    return this.messages
  }



  getDataToShow(dataToShow:any){
    // console.log("test dataToShow id-->",dataToShow?.id);
    
  
     
    

    let table=[]
    this.globalService.getDataToShow(dataToShow,this.protocol,this.bankCode).subscribe({
      next: (data: any) => {
        // // console.log("show data  ",data);
        if (data.respCode === "000") {
          // // console.log("result  ",data.result);
          this.showTable.unshift(data.result)
          table=this.showTable
          // // console.log("table  ",table);
          this.finalTable=table
          // // console.log(" data finalTable  ",this.finalTable);
          // console.log(this.showTable);
        } else {
          // console.log("error getDataToShow",data.respMsg);

          //alert(data.respMsg);
        }


      },
      error: (error: Error) => {
        // console.log(error);

        this.alertBody.status = "An error has occurred";
        if (this.en) {
            this.alertBody.message = "An error has occurred !";
        }
        if (this.fr) {
            this.alertBody.message = "Une erreur est survenue !";
        }
        if (this.esp) {
            this.alertBody.message = "An error has occurred !";
        }
        this.alertBody.open = true;
        setTimeout(() => {
            this.alertBody.open = false;
        }, 3000);

        }
    });
  }

  fillForm(item : any) {
    // console.log("item ==>",item);
    
    this.caseForm.patchValue(item);
    // For the 'fields' array, you need to set each group individually
    const fieldsControl = this.caseForm.get('fields') as FormArray;
    fieldsControl.clear(); // Clear existing form groups
    item.fields.forEach((field:any) => {
      fieldsControl.push(this.fb.group({
        fieldDef: this.fb.group({
          id: this.fb.group({
            fieldId: field.fieldDef.id.fieldId,
            fieldProtocole: field.fieldDef.id.fieldProtocole,
            bankCode: field.fieldDef.id.bankCode
          }),
          fieldDesc: field.fieldDef.fieldDesc,
          fieldName: field.fieldDef.fieldName,
          fieldType: field.fieldDef.fieldType,
          fieldLengthMax: field.fieldDef.fieldLengthMax,
          fieldFormatCheck: field.fieldDef.fieldFormatCheck,
          fieldFormatStr: field.fieldDef.fieldFormatStr
        }),
        value: field.value
      }));
    });
    this.currency="";
    this.amount=0;
    // console.log("test item fields ==>",item.fields);
    
    item.fields.forEach((field: any) => {
      if (field.fieldDef.id.fieldId === 49) {
        this.currency = field.value;
      }
      if (field.fieldDef.id.fieldId === 4) {
        this.amount = parseFloat(field.value);
      }
    });
  }




  retrieveCards(): void {
    const body = {
      bankCode: this.bankCode,
      cardProtocol: this.protocol
    };
    this.globalService.getCards(body).subscribe({
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

  retrieveCurrency(): void {

    this.globalService.fetchCurrency().subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.respCode === "000") {
          this.currencies = data.result;
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
      merProtocol: this.protocol
    };
    this.globalService.getMerchants(body).subscribe({
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
      terProtocol: this.protocol
    };
    this.globalService.getTerminals(body).subscribe({
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






  selectMessageRecord(id: any) {
    this.finalTable.map((item: any) => {
      if (item.date == id) {
        this.selectTransaction(item)

      }
    })
  }



  selectTransaction(row: any) {
    // console.log('row: ', row);
    this.reqXml = "";
    this.resXml = "";
    this.reqFields = [];
    this.respFields = [];
    let item = row
    let req = item.bufferReq
    let rep = item.bufferRes
    let body = {
      messageIn: "",
      protocolCode:this.protocol
    };
    if (req == item.bufferReq) {
      body.messageIn = req;
      // console.log('body.messageIn: ', body.messageIn);

      // // console.log("body getSidDump 1 ===>",body);

      this.globalService.getSidDump(body).subscribe((data) => {
        // // console.log("test data getSidDump :",data);
        const resultObj = JSON.parse(data.result);
        const fields = resultObj.fields;
        // // console.log('Parsed fields:', fields);

        // Assuming fields is valid JSON, proceed with assigning it
        this.dataFromTransaction.tLog.req = JSON.stringify(fields);

        // console.log('this.dataFromTransaction.tLog.req: ', JSON.parse(this.dataFromTransaction.tLog.req));
        this.allFields(this.dataFromTransaction.tLog.req, "req");
        this.reqXml = this.convertJSONtoXML(this.reqFields);
      });

      
      this.globalService.SidMsgHexTrace(body).subscribe((data) => {
        // console.log('data: ', data);

        const resultObj = JSON.parse(data.result);
        const hexTraceData = resultObj.hexTrace;

        this.dataFromTransaction.tHex.req = hexTraceData;
        const lines: string[] = hexTraceData.split("\r\n");
        this.hexReq = "";

        for (let i = 0; i < lines.length; i++) {
          this.hexReq += "\n" + lines[i];
        }
        // console.log("hexReq:::> ", this.hexReq);
      });


    }
    if (rep == item.bufferRes) {
      body.messageIn = rep;
      // console.log("body getSidDump ===>",body);

      this.globalService.getSidDump(body).subscribe((data) => {
        const resultObj = JSON.parse(data.result);
        const fields = resultObj.fields;
        // // console.log('Parsed fields:', fields);

        // Assuming fields is valid JSON, proceed with assigning it
        this.dataFromTransaction.tLog.resp = JSON.stringify(fields);

        // this.dataFromTransaction.tLog.resp = JSON.stringify(data.fields);
        this.allFields(this.dataFromTransaction.tLog.resp, "resp");
        // console.log("this.respFields: ", this.respFields);
        this.resXml = this.convertJSONtoXML(this.respFields);
      });
      this.globalService.SidMsgHexTrace(body).subscribe((data) => {
        // console.log('data: ', data);

        const resultObj = JSON.parse(data.result);
        const hexTraceData = resultObj.hexTrace;
        // // console.log('Parsed fields:', fields);

        // Assuming fields is valid JSON, proceed with assigning it
        this.dataFromTransaction.tLog.resp = JSON.stringify(hexTraceData);


        this.dataFromTransaction.tHex.req = hexTraceData;
        const lines: string[] = hexTraceData.split("\r\n");
        this.hexResp = "";

        for (let i = 0; i < lines.length; i++) {
          this.hexResp += "\n" + lines[i];
        }

      });
    }

  }



  allFields(array: any, action: string) {
    let arr = JSON.parse(array);
    // console.log("arr: ", arr);
    this.fieldList.forEach((item: any) => {
      // console.log('item: ', item);

      if (
        (-1) ==
        parseInt(item.id.fieldId)
      ) {
        if (action == "req") {
          this.reqFields.push({
            id: 0,
            fieldName: item.fieldName,
            value: arr["MTI"],
          });
        } else {
          this.respFields.push({
            id: 0,
            fieldName: item.fieldName,
            value: arr["MTI"],
          });
        }
      }

      for (var key in arr) {
        if (
          parseInt(key) ==
          parseInt(item.id.fieldId)
        ) {
          if (action == "req") {
            this.reqFields.push({
              id: key,
              fieldName: item.fieldName,
              value: arr[key],
            });
          } else {
            this.respFields.push({
              id: key,
              fieldName: item.fieldName,
              value: arr[key],
            });
          }
        }
      }

    });
    // console.log("fields req -> ", this.reqFields);
    // console.log("fields resp -> ", this.respFields);
  }

  getFields() {
    const body={
      "fieldProtocole": this.protocol,
      "bankCode": this.bankCode
    }
    this.globalService.getFields(body).subscribe((data) => {
      this.fieldList = data.result;
      // console.log("-- -- fields list -- -- ", this.fieldList);
    });
  }

  formatDate(value: string) {
    const date = new Date(value);
    const formattedDate = `${date.getFullYear()}-${('0' + (date.getMonth() + 1)).slice(-2)}-${('0' + date.getDate()).slice(-2)} ${('0' + date.getHours()).slice(-2)}h:${('0' + date.getMinutes()).slice(-2)}min`;
    return formattedDate;
  }



  convertJSONtoXML(json: any) {
    let xml = "";
    json.forEach((element: any) => {
      xml += `\n<field `
      for (var key in element) {
        if (key != 'fieldName')
          xml += `${key}="${element[key]}" `
      }
      xml += `/>`
    });
    // console.log('xml: ', xml);
    return xml
    // function convert(json: any) {
    //   Object.keys(json).forEach(function (key) {
    //     xml += "<field>";
    //     if (json[key] instanceof Array) {
    //       json[key].forEach(function (val) {
    //         xml += "<" + key + ">" + convert(val) + "</" + key + "> \n";
    //       });
    //     } else if (json[key] instanceof Object) {
    //       xml += "<" + key + ">" + convert(json[key]) + "</" + key + "> \n";
    //     } else {
    //       xml += "<" + key + ">" + json[key] + "</" + key + "> \n";
    //     }
    //     xml += "</field> \n";
    //   });
    //   return xml;
    // }
    // console.log(xml);
    //return convert(json);
  }



clearMessage(){
  this.messages={
    header: "",
      messageOut: "",
    messageIn: "",
    index: 0,
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
    this.messages.messageOut = `{"ip":"${this.communicationForm.get("commIp")?.value}","port": ${this.communicationForm.get("commPort")?.value},"type":"${this.communicationForm.get("commType")?.value}"}`;
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
      
    
    this.globalService.clear_Session({}).subscribe(res=>{

    })

    // // console.log('this.wsService.connect()',this.wsService.connect());


    // if (this.wsService.connect()) {
    //     // console.log("Connected successfully");
    //     this.isConnected=true
    //     this.alertBody.status = "000";
    //     if (this.en) {
    //         this.alertBody.message = "Connected successfully";
    //     }
    //     if (this.fr) {
    //         this.alertBody.message = "Connecté avec succès";
    //     }
    //     if (this.esp) {
    //         this.alertBody.message = "Conectado exitosamente";
    //     }
    //     this.alertBody.open = true;
    //     setTimeout(() => {
    //         this.alertBody.open = false;
    //     }, 3000);

    // } else {
    //     this.isConnected=false
    //     this.alertBody.status = "not connected";
    //     if (this.en) {
    //         this.alertBody.message = "Failed to connect";
    //     }
    //     if (this.fr) {
    //         this.alertBody.message = "Échec de la connexion";
    //     }
    //     if (this.esp) {
    //         this.alertBody.message = "Falló la conexión";
    //     }
    //     this.alertBody.open = true;
    //     setTimeout(() => {
    //         this.alertBody.open = false;
    //     }, 3000);
    // }




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
              this.echoTestExution()
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
    this.messages.messageOut = `{"ip":"${this.communicationForm.get("commIp")?.value}","port": ${this.communicationForm.get("commPort")?.value},"type":"${this.communicationForm.get("commType")?.value}"}`;
    this.msgsToSend=[]
    this.msgsToSend.push(this.messages)
    // console.log('this.msgsToSend: ', this.msgsToSend);
    // this.wsService.sendMessage(JSON.stringify(this.messages))
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

    this.globalService.clear_Session({}).subscribe(res=>{
    })

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
}



////////
  // close() {
  //   this.isConnected=false

  //   if (this.wsService.disconnect()) {
  //     this.alertBody.status = "000";
  //     if (this.en) {
  //         this.alertBody.message = "Disconnected successfully";
  //     }
  //     if (this.fr) {
  //         this.alertBody.message = "Déconnecté avec succès";
  //     }
  //     if (this.esp) {
  //         this.alertBody.message = "Desconectado exitosamente";
  //     }
  //     this.alertBody.open = true;
  //     setTimeout(() => {
  //         this.alertBody.open = false;
  //     }, 3000);
  //   }else{
  //     this.alertBody.status = "faild Disconnected";
  //     if (this.en) {
  //         this.alertBody.message = "Disconnected faild";
  //     }
  //     if (this.fr) {
  //         this.alertBody.message = "Déconnecté  faild";
  //     }
  //     if (this.esp) {
  //         this.alertBody.message = "Desconectado faild";
  //     }
  //     this.alertBody.open = true;
  //     setTimeout(() => {
  //         this.alertBody.open = false;
  //     }, 3000);
  //   }


  // }


  getOneInstitutionParam(){
      const body={
        
          bankCode: this.bankCode,
          instProtocol: this.protocol
    
      }
      this.globalService.getOneInstitutionParam(body).subscribe(res=>{
        if(res.respCode==="000"){
           
          this.pin_Institution=res.result[0].pinKey
        }else{
          // console.log("Error de getOneInstitutionParam");
          
        }
      })
  }

  getAllComms() {
    const id = {
      commId: 0,
      bankCode: this.bankCode, // Replace with your actual user object
      commProtocol: this.protocol, // Replace with your actual communication protocol
    };
    this.globalService.getOneCommunicationParam(id).subscribe({
      next: (data: any) => {
        // console.log(data);
        if (data.result && data.result.length > 0) {
          const firstResult = data.result[0];
          if (data.respCode === "000") {
            this.communicationForm.patchValue({
              commType: firstResult.commType,
              timeOutComms:firstResult.timeOutComms,
              instCode: firstResult.instCode,
              headerType: firstResult.headerType,
              headerComm: firstResult.headerComm,
              commIp: firstResult.commIp,
              commPort: firstResult.commPort,
              managMac: firstResult.managMac,
              managHeader: firstResult.managHeader,
              msgHeader: firstResult.msgHeader,
              id: firstResult.id
              // Add more form controls as needed
            });
          } else {
            alert(data.respMsg);
          }
        }
      },
      error: (error: Error) => {
        // console.log(error);
      }
    });
  }




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


  closemodalAleartInstall() {
    this.isConnected = false;
    this.downloadFile()
    this.modalAleart?.nativeElement.close()
  }



  onSelectionChange(event: any) {
    this.selectedOption = event.target.value;
  }

  maxDigitsAfterDecimal = 0;
  getStepValue(): string {
    if (this.maxDigitsAfterDecimal < 1) {
      // If maxDigitsAfterDecimal is less than 1, default to 2
      this.maxDigitsAfterDecimal = 2;
    }
    return '0.' + '0'.repeat(this.maxDigitsAfterDecimal - 1) + '1';
  }

   selected = false;
   onCurrencyChangeCountour:boolean = false;
  changeDisplaySelect(event:any){
    this.onCurrencyChangeCountour = !this.onCurrencyChangeCountour;
    if (this.onCurrencyChangeCountour) {
      this.selected = true;
    } else {
      this.selected = false;
    }


    // console.log('this.selected  changeDisplaySelect', this.selected)


    }


  onCurrencyChange() {

    // this.onCurrencyChangeCountour = !this.onCurrencyChangeCountour;
    this.selected = false;
    // console.log('this.selected', this.selected);


    // Récupérer la devise sélectionnée
    const selectedCurrency = this.currencies.find((currency: any) => currency.id === this.currency);
    // // console.log("test  selectedCurrency",selectedCurrency);


    if (selectedCurrency) {
        const  ccyExponent=2
        this.amount = 0.00;
        this.maxDigitsAfterDecimal = selectedCurrency.ccyExponent;
        // this.amount = parseFloat(this.amount.toFixed(selectedCurrency.ccyExponent));

    }
  }


  onInput(event: any) {
    const value = event.target.value;
    const parts = value.split('.');
    if (parts.length === 2 && parts[1].length > this.maxDigitsAfterDecimal) {
      // Truncate the decimal part to the maximum allowed digits
      event.target.value = parts[0] + '.' + parts[1].slice(0, this.maxDigitsAfterDecimal);
    }
  }

  formatHexData(hexData: string): string {
    const formattedLines = [];
    const chunkSize = 72;
    for (let i = 0; i < hexData.length; i += chunkSize) {
      const line = hexData.substr(i, chunkSize);
      formattedLines.push(line);
    }
    return formattedLines.join('\n');
  }

  generatePDF() {
    const doc = new jsPDF();

    doc.text('Sender', doc.internal.pageSize.getWidth() / 2, 10, { align: 'center' });

    const headers = ['ID', 'Field Name', 'Value'];
    const data = this.respFields.map((item: { id: any; fieldName: any; value: any; }) => [item.id, item.fieldName, item.value]);

    // Générer la première table
    const startYForFirstTable = 20;
    const rowHeight = 10; // Hauteur estimée d'une ligne de données
    const firstTableHeight = startYForFirstTable + (data.length * rowHeight);

    // Afficher la première table
    autoTable(doc, {
      head: [headers],
      body: data,
      startY: startYForFirstTable
    });

    // Déterminer la position Y pour la deuxième table
    const startYForSecondTable = firstTableHeight + 10; // Ajoutez un espace de 10 unités

    doc.text('Receiver', doc.internal.pageSize.getWidth() / 2, startYForSecondTable, { align: 'center' });

    const headersRec = ['ID', 'Field Name', 'Value'];
    const dataRec = this.reqFields.map((item: { id: any; fieldName: any; value: any; }) => [item.id, item.fieldName, item.value]);

    // Afficher la deuxième table
    autoTable(doc, {
      head: [headersRec],
      body: dataRec,
      startY: startYForSecondTable + 10 // Ajoutez un espace supplémentaire après la deuxième table
    });

    doc.save('logs.pdf');
}




//////////////////


getNameCase(data: string): string {
  // Parcourir la liste des scénarios
  for (const scenario of this.scenarios) {
    // Parcourir les définitions de cas pour chaque scénario
    for (const caseDefinition of scenario.casesDefinitions) {
      // Vérifier si l'ID du cas correspond à la valeur donnée
      if (caseDefinition.id.caseNo === data) {
        // Si la correspondance est trouvée, retourner la description du cas
        return caseDefinition.caseDesc;
      }
    }
  }
  // Si aucun cas correspondant n'est trouvé, retourner une chaîne vide ou une valeur par défaut
  return '';
}










}


