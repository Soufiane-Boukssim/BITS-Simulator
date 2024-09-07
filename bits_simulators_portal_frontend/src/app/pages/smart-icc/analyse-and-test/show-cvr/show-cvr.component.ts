import { Component } from '@angular/core';

@Component({
  selector: 'app-show-cvr',
  templateUrl: './show-cvr.component.html',
  styleUrls: ['./show-cvr.component.scss']
})
export class ShowCvrComponent {
  selectedOption: number | null = null;

  selectOption(option: number) {
    this.selectedOption = option;
  }
}
