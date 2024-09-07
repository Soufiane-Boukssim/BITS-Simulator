import { Component } from '@angular/core';

@Component({
  selector: 'app-new-configuration',
  templateUrl: './new-configuration.component.html',
  styleUrls: ['./new-configuration.component.scss']
})
export class NewConfigurationComponent {

  showGeneralSettings = true;

  toggleView() {
    this.showGeneralSettings = !this.showGeneralSettings;
  }

}
