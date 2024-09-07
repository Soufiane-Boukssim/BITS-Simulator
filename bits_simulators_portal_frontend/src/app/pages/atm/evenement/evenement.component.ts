import { Component } from '@angular/core';
import {GlobalService} from "../../../services/global.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-evenement',
  templateUrl: './evenement.component.html',
  styleUrls: ['./evenement.component.scss']
})
export class EvenementComponent {


  alertBody:any = {
    message:'',
    status:'',
    open:false
  }

  bankCode:any=''


  message:any=""
  tab1:boolean = true;
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
  tab12:boolean = false;
  tab13:boolean = false;
  tab14:boolean = false;
  constructor(
    private globalService:GlobalService,
    private auth:AuthService
  ){}
  ngOnInit(): void {

    Promise.resolve().then(() => this.globalService.titleSubject.next("Evenement"));
    this.auth.user.subscribe(val => {
      this.bankCode = val.bankCode
      // console.log("bankCode,",val.bankCode);

      if (this.bankCode !="") {
        // console.log('test banck code ',this.bankCode);

        this.getSolicite();
      }

    })


  }
  openTab(index:any){
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
  this.tab12 = false;
  this.tab13 = false;
  this.tab14 = false;
  this.timeofdayclock = false
    this.cardreaderwriter = false
    this.cashhandler = false
    this.depository = false
    this.receiptprinter = false
    this.journalprinter = false
    this.encryptor = false
    this.nightsafedepository = false
    this.cameradooraccesstouchscreen = false
    this.sensors = false
    this.statementprinter = false
    this.bunchnoteacceptor = false
    this.envelopedispenser = false
    this.chequeprocessing = false
  switch (index) {
    case 1:
      this.tab1 = true
      this.timeofdayclock = true
      //this.findItemInResult("")
      break;
    case 2:
      this.tab2 = true
      this.cardreaderwriter = true
      break;
    case 3:
      this.tab3 = true
      this.cashhandler = true
      break;
    case 4:
      this.tab4 = true
      this.depository = true
      break;
    case 5:
      this.tab5 = true
      this.receiptprinter = true
      break;
    case 6:
      this.tab6 = true
      this.journalprinter = true
      break;
    case 7:
      this.tab7 = true
      this.encryptor = true
      break;
    case 8:
      this.tab8 = true
      this.nightsafedepository = true
      break;
    case 9:
      this.tab9 = true
      this.cameradooraccesstouchscreen = true
      break;
    case 10:
      this.tab10 = true
      this.sensors = true
      break;
    case 11:
      this.tab11 = true
      this.statementprinter = true
      break;
    case 12:
      this.tab12 = true
      this.bunchnoteacceptor = true
      break;
    case 13:
      this.tab13 = true
      this.envelopedispenser = true
      break;
    case 14:
      this.tab14 = true
      this.chequeprocessing = true
      break;
    default:
      break;
  }
  this.handleChangeVal()
  }


  timeofdayclock:boolean = true
  cardreaderwriter:boolean = false
  cashhandler:boolean = false
  depository:boolean = false
  receiptprinter:boolean = false
  journalprinter:boolean = false
  encryptor:boolean = false
  nightsafedepository:boolean = false
  cameradooraccesstouchscreen:boolean = false
  sensors:boolean = false
  statementprinter:boolean = false
  bunchnoteacceptor:boolean = false
  envelopedispenser:boolean = false
  chequeprocessing:boolean = false
  terminalNumber:string = "00000001"
  messages:any = []

  timeDayObject:any = {
    deviceId:"A",
    deviceStatus:"",
    errorSeverity:""
  }
  cardReaderWriterObject:any = {
    deviceId:"D",
    deviceStatus:"",
    deviceStatus2:"",
    errorSeverity:"",
    supplyStatus:{
      paper:"",
      ribbon:"",
      printHead:"",
      knife:""
    }
  }
  cashHandlerObject:any = {
    deviceId:"E",
    deviceStatus:{
      dispenseOp:'',
      dispC1:'',
      dispC2:'',
      dispC3:'',
      dispC4:'',
    },
    errorSeverity:{
      completeSevInfo:'',
      err1:'',
      err2:'',
      err3:'',
      err4:'',
    },
    supplyStatus:{
      rejectBin:'',
      supplyStatus1:'',
      supplyStatus2:'',
      supplyStatus3:'',
      supplyStatus4:'',
    }
  }
  depositoryObject:any = {
    deviceId:"F",
    deviceStatus:"",
    errorSeverity:"",
    supplyStatus:""
  }
  receiptPrinterObject:any = {
    deviceId:"G",
    deviceStatus:"",
    errorSeverity:"",
    supplyStatus:{
      paper:"",
      ribbon:"",
      printHead:"",
      knife:""
    }
  }
  journalPrinterObject:any = {
    deviceId:"H",
    deviceStatus:"",
    errorSeverity:"",
    supplyStatus:{
      paper:"",
      ribbon:"",
      printHead:"",
      knife:""
    }
  }
  encryptorObject:any = {
    deviceId:"L",
    deviceStatus:"",
    errorSeverity:""
  }
  nightSafeDepositoryObject:any = {
    deviceId:"K",
    deviceStatus:"",
    errorSeverity:"",
    supplyStatus:""
  }
  cameraObject:any = {
    deviceId:"M",
    deviceStatus:'',
    errorSeverity:'',
    supplyStatus:""
  }
  doorAccessObject:any = {
    deviceId:"N",
    deviceStatus:'',
    errorSeverity:'',
    supplyStatus:""
  }
  touchScreenObject:any = {
    deviceId:"Q",
    deviceStatus:'',
    errorSeverity:'',
    supplyStatus:""
  }
  sensorsObject:any = {
    deviceId:"P",
    deviceStatus:{
      vibrationHeat:'0',
      doorContact:'0',
      silentSignal:'0',
      electronicsEnclosure:'0',
      deposit:'0',
      currencyRejectBin:'0',
      cardBin:'0',
      currencyCassette1:'0',
      currencyCassette2:'0',
      currencyCassette3:'0',
      currencyCassette4:'0'
    }
  }
  statementPrinterObject:any = {
    deviceId:"V",
    deviceStatus:"",
    errorSeverity:"",
    supplyStatus:{
      paper:"",
      ribbon:"",
      printHead:"",
      knife:""
    }
  }
  bunchNoteAcceptorObject:any = {
    deviceId:"W",
    deviceStatus:{
      bnaOperation:"",
      escrowCounts:"",
      escrowNumber:"",
      vaultedCounts:"",
      vaultedNumber:"",
      returnedCounts:"",
      returnedNumber:""
    },
    errorSeverity:"",
    supplyStatus:{
      paper:"",
      ribbon:"",
      printHead:"",
      knife:""
    }
  }
  envelopeDispenserObject:any = {
    deviceId:"S",
    deviceStatus:"",
    errorSeverity:"",
    supplyStatus:"",
  }
  chequeProcessingObject:any = {
    deviceId:"C",
    deviceStatus:{
      errorCPMPosition:"",
      errorCPM2Position:"",
    },
    errorSeverity:""
  }





  timeOfDayMessage: string = "";
  cardReaderWriterMessage:string = "";
  cashHandlerMessage:string = ""
  depositoryMessage:string = ""
  receiptPrinterMessage:string  = ""
  journalPrinterMessage:string = ""
  encryptorMessage:string =""
  nightSafeDepositoryMessage:string = ""
  cameraMessage:string = ""
  doorAccessMessage:string = ""
  touchScreenMessage:string = ""
  sensorsMessage:string = ""
  statementPrinterMessage:string = ""
  bunchNotAcceptorMessage:string = ""
  envolopeDispenserMessage:string = ""
  chequeProcessingMessage:string = ""


  changeCheckBox(name: string, isChecked: boolean) {
    switch (name) {
      case 'vibrationHeat':
        this.sensorsObject.deviceStatus.vibrationHeat = isChecked ? '1' : '0';
        break;
      case 'doorContact':
        this.sensorsObject.deviceStatus.doorContact = isChecked ? '1' : '0';
        break;
      case 'silentSignal':
        this.sensorsObject.deviceStatus.silentSignal = isChecked ? '1' : '0';
        break;
      case 'electronicsEnclosure':
        this.sensorsObject.deviceStatus.electronicsEnclosure = isChecked ? '1' : '0';
        break;
      case 'deposit':
        this.sensorsObject.deviceStatus.deposit = isChecked ? '1' : '0';
        break;
      case 'currencyRejectBin':
        this.sensorsObject.deviceStatus.currencyRejectBin = isChecked ? '1' : '0';
        break;
      case 'cardBin':
        this.sensorsObject.deviceStatus.cardBin = isChecked ? '1' : '0';
        break;
      case 'currencyCassette1':
        this.sensorsObject.deviceStatus.currencyCassette1 = isChecked ? '1' : '0';
        break;
      case 'currencyCassette2':
        this.sensorsObject.deviceStatus.currencyCassette2 = isChecked ? '1' : '0';
        break;
      case 'currencyCassette3':
        this.sensorsObject.deviceStatus.currencyCassette3 = isChecked ? '1' : '0';
        break;
      case 'currencyCassette4':
        this.sensorsObject.deviceStatus.currencyCassette4 = isChecked ? '1' : '0';
        break;
      default:
        // Optionally handle any cases not explicitly mentioned
        console.warn(`Unhandled device status: ${name}`);
        break;
    }
  }


  handleChangeVal(){
    if(this.timeofdayclock  == true){
      // console.log('this.timeofdayclock : ', this.timeofdayclock );
      this.timeOfDayMessage = "A" + "#" + this.timeDayObject.deviceStatus + "#" + this.timeDayObject.errorSeverity + "#"
      this.message = this.timeOfDayMessage
    }
    if(this.cardreaderwriter == true){
      this.cardReaderWriterMessage = "D" + "#" + this.cardReaderWriterObject.deviceStatus + this.cardReaderWriterObject.deviceStatus2 + "#" + this.cardReaderWriterObject.errorSeverity + "MSMDATA" + "#" +
        this.cardReaderWriterObject.supplyStatus.paper +
        this.cardReaderWriterObject.supplyStatus.ribbon +
        this.cardReaderWriterObject.supplyStatus.printHead +
        this.cardReaderWriterObject.supplyStatus.knife
      this.message = this.cardReaderWriterMessage
    }
    if(this.cashhandler == true){
      this.cashHandlerMessage = "E" + "#"
        + this.cashHandlerObject.deviceStatus.dispenseOp
        + this.cashHandlerObject.deviceStatus.dispC1
        + this.cashHandlerObject.deviceStatus.dispC2
        + this.cashHandlerObject.deviceStatus.dispC3
        + this.cashHandlerObject.deviceStatus.dispC4
        + "#"
        + this.cashHandlerObject.errorSeverity.completeSevInfo
        + this.cashHandlerObject.errorSeverity.err1
        + this.cashHandlerObject.errorSeverity.err2
        + this.cashHandlerObject.errorSeverity.err3
        + this.cashHandlerObject.errorSeverity.err4
        + "#"
        + this.cashHandlerObject.supplyStatus.rejectBin
        + this.cashHandlerObject.supplyStatus.supplyStatus1
        + this.cashHandlerObject.supplyStatus.supplyStatus2
        + this.cashHandlerObject.supplyStatus.supplyStatus3
        + this.cashHandlerObject.supplyStatus.supplyStatus4
      this.message = this.cashHandlerMessage
    }
    if(this.depository == true){
      this.depositoryMessage = this.depositoryObject.deviceId
        + "#"
        + this.depositoryObject.deviceStatus
        + "#"
        + this.depositoryObject.errorSeverity + "#" + "MSMDATA" + "#" + this.depositoryObject.supplyStatus
      this.message = this.depositoryMessage
    }
    if(this.receiptprinter == true){
      this.receiptPrinterMessage = this.receiptPrinterObject.deviceId + "#" + this.receiptPrinterObject.deviceStatus + '#' + this.receiptPrinterObject.errorSeverity + '#' + "MSMDATA" + "#" + this.receiptPrinterObject.supplyStatus.paper + this.receiptPrinterObject.supplyStatus.ribbon + this.receiptPrinterObject.supplyStatus.printHead + this.receiptPrinterObject.supplyStatus.knife
      this.message = this.receiptPrinterMessage
    }
    if(this.journalprinter == true){
      this.journalPrinterMessage = this.journalPrinterObject.deviceId + '#' + this.journalPrinterObject.deviceStatus + '#' + this.journalPrinterObject.errorSeverity + this.journalPrinterObject.supplyStatus.paper +
        this.journalPrinterObject.supplyStatus.ribbon +
        this.journalPrinterObject.supplyStatus.printHead +
        this.journalPrinterObject.supplyStatus.knife
      this.message = this.journalPrinterMessage
    }
    if(this.encryptor == true){
      this.encryptorMessage = this.encryptorObject.deviceId + "#" + this.encryptorObject.deviceStatus + '#' + this.encryptorObject.errorSeverity
      this.message = this.encryptorMessage
    }
    if(this.nightsafedepository == true){
      this.nightSafeDepositoryMessage = this.nightSafeDepositoryObject.deviceId
        + '#' + this.nightSafeDepositoryObject.deviceStatus
        + '#' + this.nightSafeDepositoryObject.errorSeverity
        + '#' +  'MSMDATA'
        + '#' + this.nightSafeDepositoryObject.supplyStatus
      this.message = this.nightSafeDepositoryMessage
    }
    if(this.cameradooraccesstouchscreen == true){
      this.cameraMessage = this.cameraObject.deviceId + '#' + this.cameraObject.deviceStatus + '#' + this.cameraObject.errorSeverity + '#' + 'MSMDATA' + '#' + this.cameraObject.supplyStatus
      this.doorAccessMessage = this.doorAccessObject.deviceId + '#' + this.doorAccessObject.deviceStatus + '#' + this.doorAccessObject.errorSeverity + '#' + 'MSMDATA' + '#' + this.doorAccessObject.supplyStatus
      this.touchScreenMessage = this.touchScreenObject.deviceId + '#' + this.touchScreenObject.deviceStatus + '#' + this.touchScreenObject.errorSeverity + '#' + 'MSMDATA' + '#' + this.touchScreenObject.supplyStatus
      this.message = this.cameraMessage
    }
    if(this.sensors == true){
      this.sensorsMessage = this.sensorsObject.deviceId + '#' + this.sensorsObject.deviceStatus.vibrationHeat + this.sensorsObject.deviceStatus.doorContact + this.sensorsObject.deviceStatus.silentSignal + this.sensorsObject.deviceStatus.electronicsEnclosure + this.sensorsObject.deviceStatus.deposit + this.sensorsObject.deviceStatus.currencyRejectBin + this.sensorsObject.deviceStatus.cardBin + this.sensorsObject.deviceStatus.currencyCassette1 + this.sensorsObject.deviceStatus.currencyCassette2 + this.sensorsObject.deviceStatus.currencyCassette3 + this.sensorsObject.deviceStatus.currencyCassette4
      this.message = this.sensorsMessage
    }
    if(this.statementprinter == true){
      this.statementPrinterMessage = this.statementPrinterObject.deviceId
        + '#' + this.statementPrinterObject.deviceStatus
        + '#' + this.statementPrinterObject.errorSeverity
        + '#' + 'MSMDATA'
        + '#' + this.statementPrinterObject.supplyStatus.paper
        + this.statementPrinterObject.supplyStatus.ribbon
        + this.statementPrinterObject.supplyStatus.printHead
        + this.statementPrinterObject.supplyStatus.knife
      this.message = this.statementPrinterMessage
    }
    if(this.bunchnoteacceptor == true){
      this.bunchNotAcceptorMessage = this.bunchNoteAcceptorObject.deviceId + '#' +
        this.bunchNoteAcceptorObject.deviceStatus.bnaOperation +
        this.bunchNoteAcceptorObject.deviceStatus.escrowCounts +
        this.bunchNoteAcceptorObject.deviceStatus.escrowNumber +
        this.bunchNoteAcceptorObject.deviceStatus.vaultedCounts +
        this.bunchNoteAcceptorObject.deviceStatus.vaultedNumber +
        this.bunchNoteAcceptorObject.deviceStatus.returnedCounts +
        this.bunchNoteAcceptorObject.deviceStatus.returnedNumber +
        '#' + 'MSMDATA' + '#' +
        this.bunchNoteAcceptorObject.supplyStatus.paper +
        this.bunchNoteAcceptorObject.supplyStatus.ribbon +
        this.bunchNoteAcceptorObject.supplyStatus.printHead +
        this.bunchNoteAcceptorObject.supplyStatus.knife
      this.message = this.bunchNotAcceptorMessage
    }
    if(this.envelopedispenser == true){
      this.envolopeDispenserMessage =  this.envelopeDispenserObject.deviceId
        + '#' + this.envelopeDispenserObject.deviceStatus
        + '#' + this.envelopeDispenserObject.errorSeverity
        + '#' + 'MSMDATA'
        + '#' + this.envelopeDispenserObject.supplyStatus
      this.message = this.envolopeDispenserMessage
    }
    if(this.chequeprocessing == true){
      this.chequeProcessingMessage = this.chequeProcessingObject.deviceId
        + '#' + this.chequeProcessingObject.deviceStatus.errorCPMPosition
        + this.chequeProcessingObject.deviceStatus.errorCPM2Position
        +  '#' + this.chequeProcessingObject.errorSeverity
        + '#' + 'MSMDATA'
      this.message = this.chequeProcessingMessage
    }
  }



  getSolicite(){
    let body = {
      "bankCode":this.bankCode
    }

    this.globalService.getSoliciteService(body).subscribe(data=>{
      // console.log('------------>data: ', data.result);
      this.terminalNumber = data.result[0].terminalNumber
      data.result.map((item:any)=>{
        if(item.devicedId == 'A'){
          // console.log('item.trxDeviceStatus: ', item.trxDeviceStatus);
          this.timeDayObject.deviceStatus = item.trxDeviceStatus
          this.timeDayObject.errorSeverity = item.errorSeverity
          // console.log('this.timeDayObject: ', this.timeDayObject);
          this.timeOfDayMessage = item.messageUnsolicited
          this.message = this.timeOfDayMessage
        }
        if(item.devicedId == 'D'){
          this.cardReaderWriterObject.deviceStatus = item.trxDeviceStatus[0]
          this.cardReaderWriterObject.deviceStatus2 = item.trxDeviceStatus[1]
          this.cardReaderWriterObject.errorSeverity = item.errorSeverity
          this.cardReaderWriterObject.supplyStatus.paper = item.supplyStatus[0]
          this.cardReaderWriterObject.supplyStatus.ribbon = item.supplyStatus[1]
          this.cardReaderWriterObject.supplyStatus.printHead = item.supplyStatus[2]
          this.cardReaderWriterObject.supplyStatus.knife = item.supplyStatus[3]
          this.cardReaderWriterMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'E'){
          this.cashHandlerObject.deviceStatus.dispenseOp = item.trxDeviceStatus[0]
          this.cashHandlerObject.deviceStatus.dispC1 = item.trxDeviceStatus.substring(1,3)
          this.cashHandlerObject.deviceStatus.dispC2 = item.trxDeviceStatus.substring(3,5)
          this.cashHandlerObject.deviceStatus.dispC3 = item.trxDeviceStatus.substring(5,7)
          this.cashHandlerObject.deviceStatus.dispC4 = item.trxDeviceStatus.substring(7,9)
          this.cashHandlerObject.errorSeverity.completeSevInfo = item.errorSeverity[0]
          this.cashHandlerObject.errorSeverity.err1 = item.errorSeverity[1]
          this.cashHandlerObject.errorSeverity.err2 = item.errorSeverity[2]
          this.cashHandlerObject.errorSeverity.err3 = item.errorSeverity[3]
          this.cashHandlerObject.errorSeverity.err4 = item.errorSeverity[4]
          this.cashHandlerObject.supplyStatus.rejectBin = item.supplyStatus[0]
          this.cashHandlerObject.supplyStatus.supplyStatus1 = item.supplyStatus[1]
          this.cashHandlerObject.supplyStatus.supplyStatus2 = item.supplyStatus[2]
          this.cashHandlerObject.supplyStatus.supplyStatus3 = item.supplyStatus[3]
          this.cashHandlerObject.supplyStatus.supplyStatus4 = item.supplyStatus[4]
          this.cashHandlerMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'F'){
          this.depositoryObject.deviceStatus = item.trxDeviceStatus
          this.depositoryObject.errorSeverity = item.errorSeverity
          this.depositoryObject.supplyStatus = item.supplyStatus
          this.depositoryMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'G'){
          this.receiptPrinterObject.deviceStatus = item.trxDeviceStatus
          this.receiptPrinterObject.errorSeverity = item.errorSeverity
          this.receiptPrinterObject.supplyStatus.paper = item.supplyStatus[0]
          this.receiptPrinterObject.supplyStatus.ribbon = item.supplyStatus[1]
          this.receiptPrinterObject.supplyStatus.printHead = item.supplyStatus[2]
          this.receiptPrinterObject.supplyStatus.knife = item.supplyStatus[3]
          this.receiptPrinterMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'H'){
          this.journalPrinterObject.deviceStatus = item.trxDeviceStatus
          this.journalPrinterObject.errorSeverity = item.errorSeverity
          this.journalPrinterObject.supplyStatus.paper = item.supplyStatus[0]
          this.journalPrinterObject.supplyStatus.ribbon = item.supplyStatus[1]
          this.journalPrinterObject.supplyStatus.printHead = item.supplyStatus[2]
          this.journalPrinterObject.supplyStatus.knife = item.supplyStatus[3]
          this.journalPrinterMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'L'){
          this.encryptorObject.deviceStatus = item.trxDeviceStatus
          this.encryptorObject.errorSeverity = item.errorSeverity
          this.encryptorMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'K'){
          this.nightSafeDepositoryObject.deviceStatus = item.trxDeviceStatus
          this.nightSafeDepositoryObject.errorSeverity = item.errorSeverity
          this.nightSafeDepositoryObject.supplyStatus = item.supplyStatus
          this.nightSafeDepositoryMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'M'){
          this.cameraObject.deviceStatus = item.trxDeviceStatus
          this.cameraObject.errorSeverity = item.errorSeverity
          this.cameraObject.supplyStatus = item.supplyStatus
          this.cameraMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'N'){
          this.doorAccessObject.deviceStatus = item.trxDeviceStatus
          this.doorAccessObject.errorSeverity = item.errorSeverity
          this.doorAccessObject.supplyStatus = item.supplyStatus
          this.doorAccessMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'Q'){
          this.touchScreenObject.deviceStatus = item.trxDeviceStatus
          this.touchScreenObject.errorSeverity = item.errorSeverity
          this.touchScreenObject.supplyStatus = item.supplyStatus
          this.touchScreenMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'P'){
          this.sensorsObject.deviceStatus.vibrationHeat = item.trxDeviceStatus[0]
          this.sensorsObject.deviceStatus.doorContact = item.trxDeviceStatus[1]
          this.sensorsObject.deviceStatus.silentSignal = item.trxDeviceStatus[2]
          this.sensorsObject.deviceStatus.electronicsEnclosure = item.trxDeviceStatus[3]
          this.sensorsObject.deviceStatus.deposit = item.trxDeviceStatus[4]
          this.sensorsObject.deviceStatus.currencyRejectBin = item.trxDeviceStatus[5]
          this.sensorsObject.deviceStatus.cardBin = item.trxDeviceStatus[6]
          this.sensorsObject.deviceStatus.currencyCassette1 = item.trxDeviceStatus[7]
          this.sensorsObject.deviceStatus.currencyCassette2 = item.trxDeviceStatus[8]
          this.sensorsObject.deviceStatus.currencyCassette3 = item.trxDeviceStatus[9]
          this.sensorsObject.deviceStatus.currencyCassette4 = item.trxDeviceStatus[10]
          this.sensorsMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'V'){
          this.statementPrinterObject.deviceStatus = item.trxDeviceStatus
          this.statementPrinterObject.errorSeverity = item.errorSeverity
          this.statementPrinterObject.supplyStatus.paper = item.supplyStatus[0]
          this.statementPrinterObject.supplyStatus.ribbon = item.supplyStatus[1]
          this.statementPrinterObject.supplyStatus.printHead = item.supplyStatus[2]
          this.statementPrinterObject.supplyStatus.knife = item.supplyStatus[3]
          this.statementPrinterMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'W'){
          this.bunchNoteAcceptorObject.deviceStatus.bnaOperation = item.trxDeviceStatus[0]
          this.bunchNoteAcceptorObject.deviceStatus.escrowCounts = item.trxDeviceStatus.substring(1,51)
          this.bunchNoteAcceptorObject.deviceStatus.escrowNumber = item.trxDeviceStatus[51]
          this.bunchNoteAcceptorObject.deviceStatus.vaultedCounts = item.trxDeviceStatus.substring(52,102)
          this.bunchNoteAcceptorObject.deviceStatus.vaultedNumber = item.trxDeviceStatus[102]
          this.bunchNoteAcceptorObject.deviceStatus.returnedCounts = item.trxDeviceStatus.substring(103,item.trxDeviceStatus.length - 1)
          this.bunchNoteAcceptorObject.deviceStatus.returnedNumber = item.trxDeviceStatus[item.trxDeviceStatus.length - 1]
          this.bunchNoteAcceptorObject.errorSeverity = item.errorSeverity
          this.bunchNoteAcceptorObject.supplyStatus.paper = item.supplyStatus[0]
          this.bunchNoteAcceptorObject.supplyStatus.ribbon = item.supplyStatus[1]
          this.bunchNoteAcceptorObject.supplyStatus.printHead = item.supplyStatus[2]
          this.bunchNoteAcceptorObject.supplyStatus.knife = item.supplyStatus[3]
          this.bunchNotAcceptorMessage = item.messageUnsolicited
        }
        if(item.devicedId == 'S'){
          this.envelopeDispenserObject.deviceStatus = item.trxDeviceStatus
          this.envelopeDispenserObject.errorSeverity = item.errorSeverity
          this.envelopeDispenserObject.supplyStatus = item.supplyStatus
          this.envolopeDispenserMessage =item.messageUnsolicited
        }
        if(item.devicedId == 'C'){
          this.chequeProcessingObject.deviceStatus.errorCPMPosition = item.trxDeviceStatus[0]
          this.chequeProcessingObject.deviceStatus.errorCPM2Position = item.trxDeviceStatus[1]
          this.chequeProcessingObject.errorSeverity = item.errorSeverity
          this.chequeProcessingMessage = item.messageUnsolicited
        }
      })
    })
  }



  saveChanges(){

      // console.log("saveChanges :");

    let bodies = [
      {
       "id": {
          "bankCode":this.bankCode,
          "devicedId":this.timeDayObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.timeDayObject.deviceId,
        "trxDeviceStatus":this.timeDayObject.deviceStatus,
        "errorSeverity":this.timeDayObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":"",
        "messageUnsolicited":this.timeOfDayMessage
      },
      {
        "id": {
          "bankCode":this.bankCode,
          "devicedId":this.cardReaderWriterObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.cardReaderWriterObject.deviceId,
        "trxDeviceStatus":this.cardReaderWriterObject.deviceStatus + this.cardReaderWriterObject.deviceStatus2,
        "errorSeverity":this.cardReaderWriterObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.cardReaderWriterObject.supplyStatus.paper + this.cardReaderWriterObject.supplyStatus.ribbon + this.cardReaderWriterObject.supplyStatus.printHead + this.cardReaderWriterObject.supplyStatus.knife ,
        "messageUnsolicited":this.cardReaderWriterMessage
      },
      {
       "id": {
          "bankCode":this.bankCode,
          "devicedId":this.cashHandlerObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.cashHandlerObject.deviceId,
        "trxDeviceStatus":this.cashHandlerObject.deviceStatus.dispenseOp + this.cashHandlerObject.deviceStatus.dispC1 + this.cashHandlerObject.deviceStatus.dispC2 + this.cashHandlerObject.deviceStatus.dispC3 + this.cashHandlerObject.deviceStatus.dispC4,
        "errorSeverity":this.cashHandlerObject.errorSeverity.completeSevInfo + this.cashHandlerObject.errorSeverity.err1 + this.cashHandlerObject.errorSeverity.err2 + this.cashHandlerObject.errorSeverity.err3 + this.cashHandlerObject.errorSeverity.err4,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.cashHandlerObject.supplyStatus.rejectBin + this.cashHandlerObject.supplyStatus.supplyStatus1 + this.cashHandlerObject.supplyStatus.supplyStatus2 + this.cashHandlerObject.supplyStatus.supplyStatus3 + this.cashHandlerObject.supplyStatus.supplyStatus4,
        "messageUnsolicited":this.cashHandlerMessage
      },
      {
       "id": {
          "bankCode":this.bankCode,
          "devicedId":this.depositoryObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.depositoryObject.deviceId,
        "trxDeviceStatus":this.depositoryObject.deviceStatus,
        "errorSeverity":this.depositoryObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.depositoryObject.deviceStatus,
        "messageUnsolicited":this.depositoryMessage
      },
      {
       "id": {
          "bankCode":this.bankCode,
          "devicedId":this.receiptPrinterObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.receiptPrinterObject.deviceId,
        "trxDeviceStatus":this.receiptPrinterObject.deviceStatus,
        "errorSeverity":this.receiptPrinterObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.receiptPrinterObject.supplyStatus.paper + this.receiptPrinterObject.supplyStatus.ribbon + this.receiptPrinterObject.supplyStatus.printHead + this.receiptPrinterObject.supplyStatus.knife,
        "messageUnsolicited":this.receiptPrinterMessage
      },
      {
       "id": {
          "bankCode":this.bankCode,
          "devicedId":this.journalPrinterObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.journalPrinterObject.deviceId,
        "trxDeviceStatus":this.journalPrinterObject.deviceStatus,
        "errorSeverity":this.journalPrinterObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.journalPrinterObject.supplyStatus.paper + this.journalPrinterObject.supplyStatus.ribbon + this.journalPrinterObject.supplyStatus.printHead + this.journalPrinterObject.supplyStatus.knife ,
        "messageUnsolicited":this.journalPrinterMessage
      },
      {
       "id": {
          "bankCode":this.bankCode,
          "devicedId":this.encryptorObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.encryptorObject.deviceId,
        "trxDeviceStatus":this.encryptorObject.deviceStatus,
        "errorSeverity":this.encryptorObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":"",
        "messageUnsolicited":this.encryptorMessage
      },
      {
       "id": {
          "bankCode":this.bankCode,
          "devicedId":this.nightSafeDepositoryObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.nightSafeDepositoryObject.deviceId,
        "trxDeviceStatus":this.nightSafeDepositoryObject.deviceStatus,
        "errorSeverity":this.nightSafeDepositoryObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.nightSafeDepositoryObject.supplyStatus,
        "messageUnsolicited":this.nightSafeDepositoryMessage
      },
      {
       "id": {
          "bankCode":this.bankCode,
          "devicedId":this.cameraObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.cameraObject.deviceId,
        "trxDeviceStatus":this.cameraObject.deviceStatus,
        "errorSeverity":this.cameraObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.cameraObject.supplyStatus,
        "messageUnsolicited":this.cameraMessage
      },
      {
       "id": {
          "bankCode":this.bankCode,
          "devicedId":this.doorAccessObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.doorAccessObject.deviceId,
        "trxDeviceStatus":this.doorAccessObject.deviceStatus,
        "errorSeverity":this.doorAccessObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.doorAccessObject.supplyStatus,
        "messageUnsolicited":this.doorAccessMessage
      },
      {
        "id": {
          "bankCode":this.bankCode,
          "devicedId":this.touchScreenObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.touchScreenObject.deviceId,
        "trxDeviceStatus":this.touchScreenObject.deviceStatus,
        "errorSeverity":this.touchScreenObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.touchScreenObject.supplyStatus,
        "messageUnsolicited":this.touchScreenMessage
      },
      {
        "id": {
          "bankCode":this.bankCode,
          "devicedId":this.sensorsObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.sensorsObject.deviceId,
        "trxDeviceStatus":this.sensorsObject.deviceStatus.vibrationHeat + this.sensorsObject.deviceStatus.doorContact + this.sensorsObject.deviceStatus.silentSignal + this.sensorsObject.deviceStatus.electronicsEnclosure + this.sensorsObject.deviceStatus.deposit + this.sensorsObject.deviceStatus.currencyRejectBin + this.sensorsObject.deviceStatus.cardBin + this.sensorsObject.deviceStatus.currencyCassette1 + this.sensorsObject.deviceStatus.currencyCassette2 + this.sensorsObject.deviceStatus.currencyCassette3 + this.sensorsObject.deviceStatus.currencyCassette4,
        "errorSeverity":"",
        "diagnostique":"MSMDATA",
        "supplyStatus":"",
        "messageUnsolicited":this.sensorsMessage
      },
      {
        "id": {
          "bankCode":this.bankCode,
          "devicedId":this.statementPrinterObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.statementPrinterObject.deviceId,
        "trxDeviceStatus":this.statementPrinterObject.deviceStatus,
        "errorSeverity":this.statementPrinterObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.statementPrinterObject.supplyStatus.paper + this.statementPrinterObject.supplyStatus.ribbon + this.statementPrinterObject.supplyStatus.printHead + this.statementPrinterObject.supplyStatus.knife,
        "messageUnsolicited":this.statementPrinterMessage
      },
      {
        "id": {
          "bankCode":this.bankCode,
          "devicedId":this.bunchNoteAcceptorObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.bunchNoteAcceptorObject.deviceId,
        "trxDeviceStatus":this.bunchNoteAcceptorObject.deviceStatus.bnaOperation + this.bunchNoteAcceptorObject.deviceStatus.escrowCounts + this.bunchNoteAcceptorObject.deviceStatus.escrowNumber + this.bunchNoteAcceptorObject.deviceStatus.vaultedCounts + this.bunchNoteAcceptorObject.deviceStatus.vaultedNumber + this.bunchNoteAcceptorObject.deviceStatus.returnedCounts + this.bunchNoteAcceptorObject.deviceStatus.returnedNumber ,
        "errorSeverity":this.bunchNoteAcceptorObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.bunchNoteAcceptorObject.supplyStatus.paper + this.bunchNoteAcceptorObject.supplyStatus.ribbon + this.bunchNoteAcceptorObject.supplyStatus.printHead + this.bunchNoteAcceptorObject.supplyStatus.knife,
        "messageUnsolicited":this.bunchNotAcceptorMessage
      },
      {
        "id": {
          "bankCode":this.bankCode,
          "devicedId":this.envelopeDispenserObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.envelopeDispenserObject.deviceId,
        "trxDeviceStatus":this.envelopeDispenserObject.deviceStatus,
        "errorSeverity":this.envelopeDispenserObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":this.envelopeDispenserObject.supplyStatus,
        "messageUnsolicited": this.envolopeDispenserMessage
      },
      {
        "id": {
          "bankCode":this.bankCode,
          "devicedId":this.chequeProcessingObject.deviceId
        },
        "terminalNumber":this.terminalNumber,
        // "devicedId":this.chequeProcessingObject.deviceId,
        "trxDeviceStatus":this.chequeProcessingObject.deviceStatus.errorCPMPosition + this.chequeProcessingObject.deviceStatus.errorCPM2Position,
        "errorSeverity":this.chequeProcessingObject.errorSeverity,
        "diagnostique":"MSMDATA",
        "supplyStatus":"",
        "messageUnsolicited":this.chequeProcessingMessage
      }
    ]
    let ok = false
    // let bankCode = {
    //   "bankCode":"00100"
    // }

      // console.log("bodies :",bodies);


      this.globalService.updateSolicite(bodies).subscribe(data=>{
        // // console.log('result: ', data.respMsg);
        // // console.log('result: ', data.respCode);
        if(data.respCode == '000'){
          this.alertBody.status = data.respCode
          this.alertBody.message = data.respMsg;
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.alertBody.status = data.respCode
          this.alertBody.message = data.respMsg;
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
      })


  }
}
