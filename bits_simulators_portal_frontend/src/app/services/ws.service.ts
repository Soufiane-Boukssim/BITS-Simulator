import { Injectable } from '@angular/core';
import { BehaviorSubject, delay, Observable, of, Subject, timeout } from 'rxjs';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import {GlobalService} from "./global.service";
import {TestingExecutionComponent} from "../pages/iso/testing-execution/testing-execution.component";




@Injectable({
  providedIn: 'root'
})
export class WsService {
  public socket: WebSocket | undefined;
  public messages: Subject<any> = new Subject();
  public onMessage: Subject<MessageEvent> = new Subject();
  public connected: boolean = false;
  timeout: any;
  isTimeout: Boolean = false;
  index: number = 0
  timeoutIds: Map<number,any> = new Map<number, any>();

 user:any
 ipAddress=""
  conectedWebService:boolean=false;

  constructor(private ms: GlobalService) {

  }


  connect() {

     this.user= localStorage.getItem('user');
    if (this.user) {
        const data = JSON.parse(this.user);
        if (data.ipAddress!="0:0:0:0:0:0:0:1") {
          this.ipAddress= data.ipAddress;

        }else{
          this.ipAddress="127.0.0.1"
        }


    } else {
        // Handle the case where 'user' is null
        console.error("User data not found in localStorage.");
    }
    //this.socket = new WebSocket('ws://localhost:9008/WebMessage');

    this.socket = new WebSocket(`ws://${this.ipAddress}:9008/WebMessage`);
    // console.log('test  socket',this.socket);
    // console.log('test connected V1',this.connected);
    this.socket.addEventListener('open', (event) => {
      // console.log('addEventListener EVENT : ', event);
      this.messages.next(event);
      this.connected = true;
      this.conectedWebService=true

      // console.log('test conectedWebService V2 ',this.conectedWebService);
      // console.log('test connected V2',this.connected);
    });





    this.onMessage = new Subject();
    this.socket.addEventListener('message', (event) => {
      let dataToShow = {
        id: {},
        scenario: {},
        reqFormater: "",
        reqNoFormater: {},
        resFormater: "",
        resNoFormater: {},
        bankCode: ""
      }
      //let isEmpty: boolean = false
      //// console.log('this.isTimeout: ', this.isTimeout);
      //// console.log('timeoutIds: send ', this.timeoutIds);
      this.onMessage.next(event)
      // console.log('test ',event);
      /*if (!this.timeoutIds.get(JSON.parse(event.data)["index"])?.isTimeout) {
        // console.log('this.timeoutIds: ', this.timeoutIds);*/




        /*if (JSON.parse(event.data)["index"] !== undefined)
          clearTimeout(this.timeoutIds.get(JSON.parse(event.data)["index"])?.iT);*/
        if (JSON.parse(event.data)["id"] !== undefined) {
          let body = {
            messageIn: JSON.parse(event.data)["messageOut"]
          }
          dataToShow.reqFormater = JSON.parse(event.data)["messageOut"];
          dataToShow.resFormater = JSON.parse(event.data)["messageIn"];
          dataToShow.id = JSON.parse(event.data)["id"];
          dataToShow.scenario = JSON.parse(event.data)["scenario"];
          //this.testingExecution.getDataToShow(dataToShow)

        }
        /*else
          clearTimeout(this.timeoutIds.get(this.index).iT);
      }

      this.isTimeout = false*/
    });

    this.socket.addEventListener('close', (event) => {
      // console.log('event ', event);
      this.messages.next(event);
      this.connected = false;
    });

    if ( this.conectedWebService = true) {
      return true;
    }else{
      return false
    }


  }


  sendRequest(data: any) {
    this.index = data.index
    try {
      this.socket?.send(JSON.stringify(data));
      /*let isTimeout: boolean=false
      var iT = setTimeout(() => {
        isTimeout = true
        console.error(`Timeout error for index ${this.index}`);

      }, 5000 * (this.index + 1));
      this.timeoutIds.set(this.index,{iT,isTimeout})
      // console.log('timeoutIds: send ', this.timeoutIds);

*/
    } catch (error) {
      console.error(error);
    }
  }


  subscribeToSmartCardData(callback: (data: any) => void) {
    this.socket?.addEventListener('message', (event) => {
      const data = JSON.parse(event.data);
      if (data.header === 'messageIn') {
        callback(data.data);
      }
    })
  }

  disconnect() {
    if (this.socket) {
      this.socket.close();
    }
  }


  // disconnect() {
  //   if (this.socket) {
  //       this.socket.close();

  //       return true;
  //   } else {
  //       return false; // Le socket n'était pas ouvert
  //   }
  // }
}

