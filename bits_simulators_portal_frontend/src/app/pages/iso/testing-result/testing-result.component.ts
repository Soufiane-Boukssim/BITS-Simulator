import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-testing-result',
  templateUrl: './testing-result.component.html',
  styleUrls: ['./testing-result.component.scss']
})
export class TestingResultComponent {
  tab_1: boolean = true
  tab_2: boolean = false
  tab_3: boolean = false
  itemsPerPageSelct:number=5
  messages: any = [];
  messagesDataSearch: any = [];
  p: number = 0;
  dateFrom: Date = new Date('1999-01-01');
  dateTo: Date = new Date();

  isLoading: boolean = false;

  protocol: string = "";
  bankCode: string = "";
  protocoleId: string = "";

  user1:any

  fr = false;
  en = false;
  esp = false;
  language: string = ""

  dateFromSearch:string= ""
  dateToSearch: string="";
  reqXml: any = 'azertyu';
  resXml: any = 'azertyuirvfd';
  fieldList: any = [];
  isoFields: any = [];
  reqFields: any = [];
  respFields: any = [];
  hexResp: string = "";
  hexReq: string = "";
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
  constructor(
    private globalService: GlobalService,
    private authService: AuthService,
  ) {
    this.reqXml = '';
    this.resXml = '';

    this.authService.user.subscribe(
      (x: any) => {
        this.bankCode = x.bankCode;
      }
    );
  }
  ngOnInit(): void {
    
    this.getFields()
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Execution / Result"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Exécution / Résultat"));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Ejecución / Resultado"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }

    this.globalService.protocol.subscribe(val => {
          if (val) {
            // console.log( "protocol",val)
            this.protocol = val
          }
        })
        if (!this.protocol) {
          this.protocol = localStorage.getItem('protocol') as string

          // console.log( "protocol V2",this.protocol)
        }

     this.getFields();
    this.getMessages()
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
  dateToString(date: Date): string {
    // Ensure that date is a valid Date object
    if (!(date instanceof Date) || isNaN(date.getTime())) {
      return ''; // or handle error accordingly
    }
  
    // Convert date to string in the desired format
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months are zero-based
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  getMessages() {
    this.isLoading=true;
    let id = {
      "dateFromStr": this.dateToString(this.dateFrom),
      "dateToStr": this.dateToString(this.dateTo),
      "protocol": this.protocol,
      "instCode": "00100"
    }
    this.globalService.getMessages(id).subscribe(response => {
      this.isLoading=false;
      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.messages = response.result
        this.messagesDataSearch=response.result

      }
    },
    
    error => {
      this.isLoading=false;
      // console.log('error: ', error);
    })
  }



  

  getMessagesSearch() {
    let id = {
      "dateFromStr": this.dateFromSearch,
      "dateToStr": this.dateToSearch,
      "protocol": this.protocol,
      "instCode": "00100"
    }

    // console.log("id::<",id);
    
    this.messages=[]
 
    
    this.globalService.getMessages(id).subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.messages = response.result

      }
    })
  }





  selectMessageRecord(id: any) {
    this.messages.map((item: any) => {
      if (item.authoMsgLogId == id) {
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
    let bodyReq = {
      messageIn: row.autBufferReq,
      protocolCode:this.protocol
    };
    let bodyRes = {
      messageIn: row.autBufferRep,
      protocolCode:this.protocol
    };

      this.globalService.getSidDump(bodyReq).subscribe((data) => {
        // console.log("test data getSidDump :",data);
        const resultObj = JSON.parse(data.result);
        const fields = resultObj.fields;
        // console.log('Parsed fields:', fields);

        // Assuming fields is valid JSON, proceed with assigning it
        this.dataFromTransaction.tLog.req = JSON.stringify(fields);

        // console.log('this.dataFromTransaction.tLog.req: ', JSON.parse(this.dataFromTransaction.tLog.req));
        this.allFields(this.dataFromTransaction.tLog.req, "req");
        this.reqXml = this.convertJSONtoXML(this.reqFields);
      });

      
      this.globalService.SidMsgHexTrace(bodyReq).subscribe((data) => {
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


      this.globalService.getSidDump(bodyRes).subscribe((data) => {
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
      this.globalService.SidMsgHexTrace(bodyRes).subscribe((data) => {
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




  allFields(array: any, action: string) {
    let arr = JSON.parse(array);
    // console.log("arr: ", arr);
    // console.log("this.fieldList ==>",this.fieldList);
    
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





}
