import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private socket: WebSocket;
  user:any 
  ipAddress=""
  private commandDataSubject = new Subject<any>();
  commandData$ = this.commandDataSubject.asObservable();
  private apiUrl = 'http://localhost:8080/api/icc/emvtag/save'; 
 

  constructor(private http: HttpClient) {

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

    // this.socket = new WebSocket('ws://localhost:9008/WebMessage');
    this.socket = new WebSocket( `ws://${this.ipAddress}:9008/WebMessage`);

    this.socket.addEventListener('open', (event) => {
      // console.log('WebSocket connection opened : ', event);
    });

    this.socket.addEventListener('message', (event) => {
      const data = JSON.parse(event.data);
      // console.log(data)
      if (data.header === 'smartCardData') {
        this.commandDataSubject.next(data.data);
      }
    });

    this.socket.addEventListener('close', (event) => {
      // console.log('WebSocket connection closed : ', event);
    });
  }


  

  

  send(data: any) {
    this.socket.send(JSON.stringify(data));
  }


  
  subscribeToSmartCardData(callback: (data: any) => void) {
    this.socket.addEventListener('message', (event) => {
      const data = JSON.parse(event.data);
      if (data.header === 'smartCardData') {
        // // console.log(JSON.parse(data.commands));
        callback(data.data);
      }
    });
  }
  subscribeToSmartCardCommande(callback: (data: any) => void) {
    this.socket.addEventListener('message', (event) => {
      const data = JSON.parse(event.data);
      if (data.header === 'smartCardData') {
        // // console.log(JSON.parse(data.commands));
        callback(data.commands);
      }
    });
  }

  subscribeToSmartCardTags(callback: (data: any) => void) {
    this.socket.addEventListener('message', (event) => {
      const data = JSON.parse(event.data);
      if (data.header === 'smartCardData') {
        // // console.log(JSON.parse(data.commands));
        callback(data.alltags);
      }
    });
  }


  
  


  
}
