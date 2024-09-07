import { Component, Input } from '@angular/core';
import {GlobalService} from "../../../services/global.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.scss']
})
export class LogsComponent {
  itemsPerPageSelct: number = 5;
  tab_1:boolean = true
  tab_2:boolean = false
  tab_3:boolean = false
  tab_4:boolean = false


  tab_MsgOUT:boolean = true
  tab_MsgIN:boolean = false
  tab_MsgRendue:boolean = false



  // arrayHead = ["Operation","Date Send","Sended Data","Date Received","Data Received"]
  data:any = []

  dateFrom="";
  dateTo="" ;


  logsData: any[] = [];
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

bankCode:any=''

@Input() numberOfLines:number = 10;
p: number = 1;
  constructor(
    private globalService:GlobalService,

    private auth:AuthService

  ){
    this.dateFrom = '';
    this.dateTo = '';
  }
  ngOnInit(): void {
    Promise.resolve().then(() => this.globalService.titleSubject.next("Logs"))

    this.auth.user.subscribe(val => {
      this.bankCode = val.bankCode
      // console.log("bankCode,",val.bankCode);

      if (this.bankCode !="") {
        // console.log('test banck code ',this.bankCode);

        this.fetchDataLogs();
      }

    })

    // this.fetchDataLogs();

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
    let dateFromFormatted = this.formatDate(this.dateFrom);
    let dateToFormatted = this.formatDateTo(this.dateTo);

    let body = {
       "bankCode":this.bankCode,
       "dateFromStr": dateFromFormatted,
       "dateToStr":dateToFormatted
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


formatDate(date: string): string {
  if (!date) return "";

  let dateObj = new Date(date);
  let day = String(dateObj.getDate()).padStart(2, '0');
  let month = String(dateObj.getMonth() + 1).padStart(2, '0'); // Les mois sont indexés à partir de 0
  let year = dateObj.getFullYear();
  let hours = String(dateObj.getHours()).padStart(2, '0');
  let minutes = String(dateObj.getMinutes()).padStart(2, '0');
  let seconds = String(dateObj.getSeconds()).padStart(2, '0');

  return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
}


formatDateTo(date: string): string {
  if (!date) return "";

  let dateObj = new Date(date);
  let day = String(dateObj.getDate()).padStart(2, '0');
  let month = String(dateObj.getMonth() + 1).padStart(2, '0'); // Les mois sont indexés à partir de 0
  let year = dateObj.getFullYear();
  let hours = String(dateObj.getHours()).padStart(2, '0');
  let minutes = String(dateObj.getMinutes()).padStart(2, '0');
  let seconds = String(dateObj.getSeconds()).padStart(2, '0');

  return `${day}/${month}/${year} 23:59:00`;
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
        "Operation": item["Operation"],
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


// formatDate(date: Date): string {
//   const day = date.getDate();
//   const month = ("0" + (date.getMonth() + 1)).slice(-2);
//   const year = date.getFullYear();
//   const hours = ("0" + date.getHours()).slice(-2);
//   const minutes = ("0" + date.getMinutes()).slice(-2);
//   const seconds = ("0" + date.getSeconds()).slice(-2);

//   const formattedDate = `${day}-${month}-${year} ${hours}:${minutes}:${seconds}`;
//   return formattedDate;
// }




  clearInputDate(){
    this.dateFrom = "";
    this.dateTo = "";
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
}
