import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-atm-management',
  templateUrl: './atm-management.component.html',
  styleUrls: ['./atm-management.component.scss']
})
export class AtmManagementComponent implements OnInit{
  @Input() not_owned_title:string = ""
  @Input() owned_title:string = ""
  @Input() not_owned:any = []
  @Input() owned:any = []
  @Output() confirm: any = new EventEmitter<any>();
  not_owned_checked:boolean = false
  owned_checked:boolean = false

  ngOnInit(): void {
    // console.log(this.not_owned, this.owned)
  }

  logLists(id:string){
    this.not_owned_checked = false
    this.owned_checked = false
    this.not_owned.map((item:any)=>{
      if(item.id == id){
        item.checked = !item.checked
      }
      if(item.checked == true){
        this.not_owned_checked = true
      }
    })
    this.owned.map((item:any)=>{
      if(item.id == id){
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
    const owned = this.owned.map((item:any) => item);
    const not_owned = this.not_owned.map((item:any) => item);
    // console.log(not_owned, owned)

    const itemsToMove = this.not_owned.filter((item:any) => item.checked);
    // console.log(itemsToMove);
    const owned1 = this.owned.map((item:any) => item);
    const not_owned1 = this.not_owned.map((item:any) => item);
    // console.log(not_owned1, owned1)
    itemsToMove.forEach((item:any) => {
      item.checked = false;
      const index = this.not_owned.findIndex((e:any) => e.id === item.id);
      this.not_owned.splice(index, 1);
      this.owned.push(item);
      const owned1 = this.owned.map((item:any) => item);
      const not_owned1 = this.not_owned.map((item:any) => item);
      // console.log(not_owned1, owned1)
    });

    this.not_owned_checked = false;
    this.emitData();
  }

  removeItem(){
    const itemsToMove = this.owned.filter((item:any) => item.checked);

    itemsToMove.forEach((item:any) => {
      item.checked = false;
      const index = this.owned.findIndex((e:any) => e.id === item.id);
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
