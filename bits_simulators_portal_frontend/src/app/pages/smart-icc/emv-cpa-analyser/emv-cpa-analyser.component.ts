import { Component } from '@angular/core';

@Component({
  selector: 'app-emv-cpa-analyser',
  templateUrl: './emv-cpa-analyser.component.html',
  styleUrls: ['./emv-cpa-analyser.component.scss']
})
export class EmvCpaAnalyserComponent {


  selectedComponent: string | null = null;

  selectComponent(component: string) {
    this.selectedComponent = component;
  }
  tab1:boolean = true;
  tab2:boolean = false;
  tab3:boolean = false;
  


  openTab(index:any){
    this.tab1 = false;
    this.tab2 = false;
    this.tab3 = false;
    switch (index) {
      case 1:
        this.tab2 = true
       
        break;
        case 2:
          this.tab3 = true
          break;
     
      default:
        this.tab1 = true
        break;
    }

    }



}
