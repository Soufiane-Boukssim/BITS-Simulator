// // Import the necessary modules and classes
// import { Component, OnInit } from '@angular/core';
// import { FormGroup, FormControl, Validators } from '@angular/forms';
// import { GlobalService } from "../../../services/global.service";
// import { ATMfield } from "../model/ATMfield";
// import { MessageTypeField } from "../model/MessageTypeField";
//
// @Component({
//   selector: 'app-atmfield',
//   templateUrl: './atmfield.component.html',
//   styleUrls: ['./atmfield.component.scss']
// })
// export class ATMfieldComponent implements OnInit {
//   atmfields: ATMfield[] = [];
//   originalAtmfields: ATMfield[] = [];
//   searchText: string = '';
//   user1: any;
//   language: string = "";
//   en: boolean = false;
//   fr: boolean = false;
//   esp: boolean = false;
//   selectedATMfield: ATMfield | null = null;
//   atmfieldForm: FormGroup;
//   messageTypeFields = Object.keys(MessageTypeField).filter(key => isNaN(Number(key)));
//   itemsPerPageSelct: number = 5;
//   p: number = 1;
//
//   constructor(private globalService: GlobalService) {
//     this.atmfieldForm = new FormGroup({
//       idATMfield: new FormControl('', Validators.required),
//       messageTypeField: new FormControl('', Validators.required),
//       description: new FormControl('', Validators.required)
//     });
//   }
//
//   ngOnInit(): void {
//
//       this.user1 = localStorage.getItem('user');
//       this.user1 = JSON.parse(this.user1);
//       this.language = this.user1.languageCode;
//       if (this.language === "en") {
//       Promise.resolve().then(() => this.globalService.titleSubject.next("ATM field"));
//       this.en=true;
//     } else if (this.language === "fr") {
//       Promise.resolve().then(() => this.globalService.titleSubject.next("ATM field")); // Replace with the French translation
//       this.fr=true;
//     } else if (this.language === "es") {
//       Promise.resolve().then(() => this.globalService.titleSubject.next("ATM field")); // Replace with the Spanish translation
//       this.esp=true;
//     } else {
//       Promise.resolve().then(() => this.globalService.titleSubject.next(""));
//     }
//     this.getAllATMfields();
//   }
//
//   filterList(): void {
//     this.atmfields = this.originalAtmfields.filter(atmfield =>
//       (atmfield.idATMfield ? atmfield.idATMfield.includes(this.searchText) : false) ||
//       (typeof atmfield.messageTypeField === 'string' && atmfield.messageTypeField ? (atmfield.messageTypeField as string).includes(this.searchText) : false) ||
//       (atmfield.description ? atmfield.description.includes(this.searchText) : false)
//     );
//   }
//
//   getAllATMfields(): void {
//     this.globalService.getAllATMfields().subscribe(atmfields => {
//       this.originalAtmfields = atmfields;
//       this.atmfields = [...this.originalAtmfields];
//     });
//   }
//
//   selectATMfield(atmfield: ATMfield): void {
//     this.selectedATMfield = atmfield;
//     this.atmfieldForm.setValue({
//       idATMfield: atmfield.idATMfield,
//       messageTypeField: atmfield.messageTypeField,
//       description: atmfield.description
//     });
//   }
//
//   clearForm(): void {
//     this.atmfieldForm.reset();
//     this.selectedATMfield = null;
//   }
//
//   searchByAll(): void {
//     this.filterList(); // Call filterList method to apply search filter
//   }
// }
