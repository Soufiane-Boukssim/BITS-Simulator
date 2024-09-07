import { Component } from '@angular/core';

@Component({
  selector: 'app-show-tvr',
  templateUrl: './show-tvr.component.html',
  styleUrls: ['./show-tvr.component.scss']
})
export class ShowTvrComponent {
  selectedOption: number | null = null;
  

  selectOption(option: number) {
    this.selectedOption = option;
  }
}
