import { Component } from '@angular/core';
import {GlobalService} from "../../../services/global.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-rejets',
  templateUrl: './rejets.component.html',
  styleUrls: ['./rejets.component.scss']
})
export class RejetsComponent {
  tab_1:boolean = true
  tab_2:boolean = false
  tab_3:boolean = false
  tab_4:boolean = false
  tab_5:boolean = false
  tab_6:boolean = false



  alertBody:any = {
    message:'',
    status:'',
    open:false
  }

  part1:string ='';
  part2:string ='';
  bankCode:string ='';
  message:string =this.part1+'#'+this.part2;
  constructor(
    private globalService:GlobalService,
    private auth: AuthService
  ){}
  ngOnInit(): void {
    Promise.resolve().then(() => this.globalService.titleSubject.next("Rejets"))
    //this.initData("C#01");
    this.message =this.part1+'#'+this.part2;
    this.auth.user.subscribe(val => {
      this.bankCode = val.bankCode
    })
    this.getReasonCommandRej();
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
        case 4:
        this.tab_5 = true

          break;
        case 5:
        this.tab_6 = true

          break;

      default:
        this.tab_1 = true
        break;
    }
  }

  changeVar(){
    this.message = this.part1+'#'+this.part2;
  }

  initData(data:string){
    var splitData = data.split('#');
    if(splitData.length===2){
      this.part1=splitData[0];
      this.part2=splitData[1]
    }
    this.message = this.part1+'#'+this.part2;
    // console.log("splitData: ",splitData)
  }

  getReasonCommandRej(): void {
    // console.log("this.bankCode"+this.bankCode)
    var body={
      "terminalNumber":"00000001",
      "bankCode":this.bankCode
    }
    this.globalService.getReasonCommandRej(body)
      .subscribe(response => {
        // console.log('Response from getReasonCommandRej:', response);
        if(response.respCode==="000"){
          this.initData(response.result.messageSolicited)
        }
        // Handle response as needed
      }, error => {
        console.error('Error getting Reason Command Rejection:', error);
        // Handle error as needed
      });
  }

  addReasonCommandRej(): void {
    var body={
      "id": {
        "terminalNumber": "00000001",
        "bankCode": this.bankCode
      },
      "errorType": this.part1,
      "errorCode": this.part2,
      "messageSolicited": this.message
    }

    this.globalService.addReasonCommandRej(body)
      .subscribe(response => {
        // // console.log('Response from addReasonCommandRej:', response);
        if(response.respCode == '000'){
          this.alertBody.status = response.respCode
          this.alertBody.message = response.respMsg;
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }else{
          this.alertBody.status = response.respCode
          this.alertBody.message =  response.respMsg;
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
      }, error => {
        console.error('Error adding Reason Command Rejection:', error);
        // Handle error as needed
      });
  }
}
