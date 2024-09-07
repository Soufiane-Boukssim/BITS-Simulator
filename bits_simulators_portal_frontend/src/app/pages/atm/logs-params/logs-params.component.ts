import { Component, Input } from '@angular/core';
import {GlobalService} from "../../../services/global.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-logs-params',
  templateUrl: './logs-params.component.html',
  styleUrls: ['./logs-params.component.scss']
})


export class LogsParamsComponent {
  itemsPerPageSelct: number = 5;
  tab_1:boolean = true
  tab_2:boolean = false
  tab_3:boolean = false
  tab_4:boolean = false
  tab_5:boolean = false

  @Input() numberOfLines:number = 6;
  p: number = 1;

  lines:number = 15
  configArray:any = []
  screenArray:any=[]
  stateArray:any=[]
  fitArray:any=[]
  aidArray:any=[]


  imageToShow: any;

  screenPath:string = ""
  logsDataConfigFilter: any[] = [];
  logsDataScreenFilter: any[] = [];
  logsDataStateFilter: any[] = [];
  logsDataFitFilter: any[] = [];
  logsDataAidFilter: any[] = [];
  searchText: string = '';

  logsDataTypeState: any[] = [];

  isDataFiltered: boolean = false;

  showDiv: boolean = false;
  showDivFIT: boolean = false;


  bankCode:any=''


  entry2:string=""
  entry3:string=""
  entry4:string=""
  entry5:string=""
  entry6:string=""
  entry7:string=""
  entry8:string=""
  entry9:string=""
  stateDetail:any = {
    stateNumber:'',
    stateName:'',
    entry2:'',
    entry3:'',
    entry4:'',
    entry5:'',
    entry6:'',
    entry7:'',
    entry8:'',
    entry9:''
  }
  user1: any;
  fr = false;
  en = false;
  esp = false;
  language: string = "";


  fitDetail = {
    fitCode: '',
    institutionIdIndex: '',
    track3PINRetryCountIndex: '',
    institutionId: '',
    pinOffsetIndex: '',
    indirectNextStateIndex: '',
    decimalisationTable: '',
    algorithmBankId: '',
    encryptedPinKey: '',
    maximumPinDigit1: '',
    indexReferencePoint: '',
    maximumPinDigit2: '',
    languageCodeIndex: '',
    pinPad: '',
    cim86SensorFlag: '',
    panDataIndex: '',
    reserved: '',
    panDataLength: '',
    pinBlockFormat: '',
    panPad: ''
  };


  constructor(
    private globalService:GlobalService,
    private auth:AuthService
  ){}


  ngOnInit(): void {
    Promise.resolve().then(() => this.globalService.titleSubject.next("Parametrage"))
   
    this.showDivFIT = true;
    this.auth.user.subscribe(val => {
      this.bankCode = val.bankCode
      // console.log("bankCode,",val.bankCode);

      if (this.bankCode !="") {
        // console.log('test banck code ',this.bankCode);

      }

    })

    this.fetchDataLogConfig();
    this.fetchDataLogScreen();
    this.fetchDataLogState();
    this.fetchDataLogFit();
    this.fetchDataLogAid();
    this.fetchDataLogTypeState();



  }



  open_tab(tab:number){
    this.tab_1 = false;
    this.tab_2 = false;
    this.tab_3 = false;
    this.tab_4 = false;
    this.tab_5 = false;
    this.p=1;
    switch (tab) {
      case 1:
        this.tab_2 = true,
        this.searchText=''


        break;

        case 2:
        this.tab_3 = true
        this.searchText=''

          break;

        case 3:
        this.tab_4 = true
        this.searchText=''

          break;
        case 4:
        this.tab_5 = true
        this.searchText=''

          break;

      default:
        this.tab_1 = true
        this.searchText=''
        break;
    }
  }





  selectLogState(logState : any){
    this.stateDetail = logState;
    this.showDiv = true; // to show the form
  }


  fetchDataLogConfig(){
    let body = {
      "bankCode":this.bankCode
    }
    // console.log("banck code :",this.bankCode);

    this.globalService.getDataLogParaConfig(body).subscribe(data => {
      // console.log('data-conf :', data);
      let label = ""
      data.result.map((item:any)=>{
        switch (item.confType) {
          case "00": label = "Camera Control option"; break;
          case "01": label = "Ready/Supply/Amount buffer"; break;
          case "02": label = "Auto Voice"; break;
          case "03": label = "Date Format"; break;
          case "04": label = "Roll Width"; break;
          case "05": label = "Left Print Column"; break;
          case "06": label = "Reserved"; break;
          case "07": label = "Track 1  Format"; break;
          case "09": label = "Diebold status reporting for vandal guard"; break;
          case "10": label = "TI control option"; break;
          case "11": label = "Extended status control option"; break;
          case "12": label = "Specific Command Reject"; break;
          case "13": label = "Card reader error threshold"; break;
          case "14": label = "Track 3 write error threshold"; break;
          case "15": label = "Transaction Status Information"; break;
          case "16": label = "Journal Printer Backup Time"; break;
          case "17": label = "Journal Printer Backup Print Operations"; break;
          case "23": label = "Envelope Dispenser Status"; break;
          case "24": label = "Enhanced/TI Sensor Status Unsolicited Message"; break;
          case "25": label = "Media Entry/Exit Indicators Flash Rate"; break;
          case "27": label = "Remote Relay"; break;
          case "33": label = "Simulate Supervisor Mode Entry/Exit"; break;
          case "34": label = "MCN Range"; break;
          case "35": label = "Report Dual Mode EJ & Hardcopy B/U Unsolicited Messages"; break;
          case "36": label = "Enhanced EJ Backup"; break;
          case "37": label = "Print Track 2 to Journal"; break;
          case "44": label = "BNA Journal Vaulted Notes Count"; break;
          case "45": label = "BNA Message Settings"; break;
          case "46": label = "MCRW Enhanced Card Device Security Jitter"; break;
          case "48": label = "Barcode Reader"; break;
          case "69": label = "EMV Smart Card Extended Status"; break;
          case "70": label = "EMV Smart Card"; break;
          case "76": label = "Cash Handlers"; break;
          case "77": label = "CardLess Next State Number"; break;
          case "78": label = "GBRU M-Status Reporting"; break;
          case "79": label = "Coin Dispenser"; break;
          case "80": label = "Alphanumeric State Entry"; break;
          case "83": label = "Cheque Processing Module"; break;
          default:
            break;
        }
          this.logsDataConfigFilter.push({
            "Code":item.confType,
            "Label":label,
            "Value":item.confData
          })
          this.configArray = this.logsDataConfigFilter
      })
    })
  }

    fetchDataLogScreen() {

      let body = {
        "bankCode":this.bankCode
      }
      this.globalService.getDataLogParaScreen(body).subscribe(
          (response) => {

              response.result.map((item:any)=>{
                const sanitizedValue = item.screenData1.replace(/[^\x20-\x7E]+/g, '');
                this.logsDataScreenFilter.push({
                  "Number":item.screenNumber,
                  // "Value":item.screenData1,
                  "Value":sanitizedValue,
                  "Path":item.screenPath

                })
              })
              this.screenArray = this.logsDataScreenFilter
          },
          (error) => {
              console.error('Error fetching logs:', error);
          }
      );
      }

      fetchDataLogState() {
        let body = {
          "bankCode":this.bankCode
        }

        this.globalService.getDataLogParaState(body).subscribe(
            (response) => {

                response.result.map((item:any)=>{
                  this.logsDataStateFilter.push({
                    "Number":item.stateNumber,
                    "Type":item.stateType,
                    "Value":item.stateData
                  })
                })
                this.stateArray = this.logsDataStateFilter
            },
            (error) => {
                console.error('Error fetching logs:', error);
            }
        );
        }

        fetchDataLogTypeState() {
          let body = {
            "bankCode":this.bankCode
          }
          this.globalService.getDataLogTypeState(body).subscribe(
              (response) => {
                  this.logsDataTypeState = response.result;
                  // console.log('Logs Data:', response.result);
              },
              (error) => {
                  console.error('Error fetching logs:', error);
              }
          );
          }

        fetchDataLogFit() {
          let body = {
            "bankCode":this.bankCode
          }

          this.globalService.getDataLogParaFit(body).subscribe(
              (response) => {
                  response.result.map((item:any)=>{
                    this.logsDataFitFilter.push({
                      "Number":item.fitNumber,
                      "Value":item.fitData,
                    })
                  })
                  this.fitArray = this.logsDataFitFilter
              },
              (error) => {
                  console.error('Error fetching logs:', error);
              }
          );
        }
          fetchDataLogAid() {
            let body = {
              "bankCode":this.bankCode
            }

            this.globalService.getDataLogParaAid(body).subscribe(
                (response) => {
                  this.logsDataAidFilter=response.result;

                    this.aidArray  = response.result;
                },
                (error) => {
                    console.error('Error fetching logs:', error);
                }
            );
            }


            test(imageId: string) {
              this.globalService.getImage(imageId).subscribe(data => {
                this.createImageFromBlob(data);
              });
            }

            createImageFromBlob(image: Blob) {
              let reader = new FileReader();
              reader.addEventListener("load", () => {
                this.imageToShow = reader.result;
              }, false);

              if (image) {
                reader.readAsDataURL(image);
              }
            }

            showScreeImg(data:any){
              // console.log( "showScreeImg",data);

              // this.screenPath=data['Path']
              this.screenPath = 'assets/screens/' + data['Path']
              // // console.log(data['Number']);
              this.globalService.getImage(data['Number']).subscribe(data => {
                this.createImageFromBlob(data);
              });
              // Nettoyage de la valeur
            const value = data["Value"];
            const cleanedValue = value.replace(/[^\x20-\x7E]+/, ''); // Supprime les caractères non ASCII
              // console.log('cleanedValue:', cleanedValue);

            }








            searchByAll(){
              if(this.tab_1 == true){
                this.logsDataConfigFilter = this.configArray.filter((item:any)=>{
                  return item.Code.includes(this.searchText) || item.Label.includes(this.searchText) || item.Value.includes(this.searchText)
                })
              }
              if(this.tab_2 == true){
                this.logsDataScreenFilter = this.screenArray.filter((item:any)=> {
                  return item.Number.includes(this.searchText) || item.Value.includes(this.searchText)
                })
              }
              if(this.tab_3 == true){
                this.logsDataStateFilter = this.stateArray.filter((item:any)=>{
                  return item.Number.includes(this.searchText) || item.Type.includes(this.searchText) || item.Value.includes(this.searchText)
                })
              }
              if(this.tab_4 == true){
                this.logsDataFitFilter = this.fitArray.filter((item:any)=>{
                  return item.Number.includes(this.searchText) || item.Value.includes(this.searchText)
                })
              }
              if(this.tab_5 == true){
                this.logsDataAidFilter = this.aidArray.filter((item:any)=>{
                  return item.Number.includes(this.searchText) || item.Value.includes(this.searchText) ||
                  item.Value.includes(this.searchText) || item.Number.includes(this.searchText) ||
                  item.Number.includes(this.searchText) || item.Number.includes(this.searchText) ||
                   item.Value.includes(this.searchText) || item.Value.includes(this.searchText) ||
                    item.Value.includes(this.searchText)
                })
              }
            }






            selectNDCStateRecord(row:any){

              // console.log('row: ', row);
              this.logsDataTypeState.map((item:any)=>{
                // console.log('item: ', item);
                if(item.stateType == row['Type']){
                  // console.log('item: ', item);
                  this.entry2 = item.stateEntry2
                  this.entry3 = item.stateEntry3
                  this.entry4 = item.stateEntry4
                  this.entry5 = item.stateEntry5
                  this.entry6 = item.stateEntry6
                  this.entry7 = item.stateEntry7
                  this.entry8 = item.stateEntry8
                  this.entry9 = item.stateEntry9
                  this.stateDetail.stateNumber = row['Number']
                  this.stateDetail.stateName = item.stateName
                  this.stateDetail.entry2 = row['Value'].slice(0,3)
                  this.stateDetail.entry3 = row['Value'].slice(3,6)
                  this.stateDetail.entry4 = row['Value'].slice(6,9)
                  this.stateDetail.entry5 = row['Value'].slice(9,12)
                  this.stateDetail.entry6 = row['Value'].slice(12,15)
                  this.stateDetail.entry7 = row['Value'].slice(15,18)
                  this.stateDetail.entry8 = row['Value'].slice(18,21)
                  this.stateDetail.entry9 = row['Value'].slice(21,24)
                  this.showDiv = true;


                }


              })
            }


  selectFITRecord(record:any){
    this.fitDetail = {
      fitCode: record['Number'],
      institutionIdIndex: record['Value'].slice(0,3),
      institutionId: record['Value'].slice(3,18),
      indirectNextStateIndex: record['Value'].slice(18,21),
      algorithmBankId: record['Value'].slice(21,24),
      maximumPinDigit1: record['Value'].slice(24,27),
      maximumPinDigit2: record['Value'].slice(27,30),
      pinPad: record['Value'].slice(30,33),
      panDataIndex: record['Value'].slice(33,36),
      panDataLength: record['Value'].slice(36,39),
      panPad: record['Value'].slice(39,42),
      track3PINRetryCountIndex: record['Value'].slice(42,45),
      pinOffsetIndex: record['Value'].slice(45,48),
      decimalisationTable: record['Value'].slice(48,69),
      encryptedPinKey: record['Value'].slice(72,93),
      indexReferencePoint: record['Value'].slice(record['Value'].length - 3, record['Value'].length),
      languageCodeIndex: '', // Add this line
      cim86SensorFlag: '', // Add this line
      reserved: '', // Add this line
      pinBlockFormat: '' // Add this line
    };
    this.showDivFIT=true;
    record.clicked = true;
  }



}
