import { Component } from '@angular/core';
import { NewConfigurationComponent } from '../analyse-and-test/new-configuration/new-configuration.component';

@Component({
  selector: 'app-analyse-and-tester',
  templateUrl: './analyse-and-tester.component.html',
  styleUrls: ['./analyse-and-tester.component.scss']
})
export class AnalyseAndTesterComponent {


  selectedComponent: string | null = null;

  selectComponent(component: string) {
    this.selectedComponent = component;
  }
  tab1:boolean = true;
  tab2:boolean = false;
  


  openTab(index:any){
    this.tab1 = false;
    this.tab2 = false;
    switch (index) {
      case 1:
        this.tab2 = true
       
        break;
     
      default:
        this.tab1 = true
        break;
    }

    }
  
}
