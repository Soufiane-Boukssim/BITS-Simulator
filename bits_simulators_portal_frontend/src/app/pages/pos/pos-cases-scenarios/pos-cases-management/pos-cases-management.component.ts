import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pos-cases-management',
  templateUrl: './pos-cases-management.component.html',
  styleUrls: ['./pos-cases-management.component.scss']
})
export class PosCasesManagementComponent {
  @Input() not_owned_title:string = ""
  @Input() owned_title:string = ""
  @Input() not_owned:any = []
  @Input() owned:any = []
  @Output() confirm: any = new EventEmitter<any>();
  not_owned_checked:boolean = false
  owned_checked:boolean = false

  ngOnInit(): void {
    this.isAnItemChecked()
  }

  logLists(id:string){
    this.not_owned_checked = false
    this.owned_checked = false
    this.not_owned.map((item:any)=>{
      if(item.caseNo == id){
        item.checked = !item.checked
      }
      if(item.checked == true){
        this.not_owned_checked = true
      }
    })
    this.owned.map((item:any)=>{
      if(item.caseNo == id){
        item.checked = !item.checked
      }
      if(item.checked == true){
        this.owned_checked = true
      }
    })
  }

  isAnItemChecked(){
    this.not_owned_checked = false
    this.owned_checked = false
    this.not_owned.map((item:any)=>{
      if(item.checked == true){
        this.not_owned_checked = true
      }
    })
    this.owned.map((item:any)=>{
      if(item.checked == true){
        this.owned_checked = false
      }
    })
  }

  addItem() {
    const itemsToMove = this.not_owned.filter((item:any) => item.checked);
    
    itemsToMove.forEach((item:any) => {
      item.checked = false;
      const index = this.not_owned.findIndex((e:any) => e.caseNo === item.caseNo);
      this.not_owned.splice(index, 1);
      this.owned.push(item);
    });
  
    this.not_owned_checked = false;
    this.emitData();
  }

  removeItem(){
    const itemsToMove = this.owned.filter((item:any) => item.checked);
  
    itemsToMove.forEach((item:any) => {
      item.checked = false;
      const index = this.owned.findIndex((e:any) => e.caseNo === item.caseNo);
      this.owned.splice(index, 1);
      this.not_owned.push(item);
    });
    this.owned_checked = false;
    this.emitData();
  }

  emitData(): void {
    this.confirm.emit(this.owned);
  }
}
