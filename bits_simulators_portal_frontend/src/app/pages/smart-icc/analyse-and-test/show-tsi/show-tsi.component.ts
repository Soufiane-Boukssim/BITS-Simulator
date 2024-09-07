import { Component } from '@angular/core';

@Component({
  selector: 'app-show-tsi',
  templateUrl: './show-tsi.component.html',
  styleUrls: ['./show-tsi.component.scss']
})
export class ShowTsiComponent {
  selectedOption: number | null = null;

  selectOption(option: number) {
    this.selectedOption = option;
  }
}
