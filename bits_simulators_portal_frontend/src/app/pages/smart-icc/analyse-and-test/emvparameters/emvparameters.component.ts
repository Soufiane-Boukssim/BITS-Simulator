import { Component } from '@angular/core';

@Component({
  selector: 'app-emvparameters',
  templateUrl: './emvparameters.component.html',
  styleUrls: ['./emvparameters.component.scss']
})
export class EMVParametersComponent {

  selectedComponent: string | null = null;

  selectComponent(component: string) {
    this.selectedComponent = component;
  }

}
