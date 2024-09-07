import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-checkbox-square',
  templateUrl: './checkbox-square.component.html',
  styleUrls: ['./checkbox-square.component.scss']
})
export class CheckboxSquareComponent {
  @Input() text: string = '';
  @Input() isChecked: boolean = false; // Input to determine the checked state
  @Output() checkboxChanged = new EventEmitter<void>(); // Output to notify parent of checkbox change
  @Output() checkboxChange = new EventEmitter<boolean>();
  @Input() isDisabled: boolean = false;
  toggleCheckbox(event : any) {
    this.checkboxChanged.emit(); // Notify the parent component that the checkbox state has changed
    this.checkboxChange.emit(event.target.checked)
  }

  

}
