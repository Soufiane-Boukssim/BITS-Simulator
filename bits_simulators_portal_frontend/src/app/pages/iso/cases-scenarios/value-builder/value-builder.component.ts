import { Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { FieldProcessingService } from 'src/app/services/field-processing.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-value-builder',
  templateUrl: './value-builder.component.html',
  styleUrls: ['./value-builder.component.scss']
})
export class ValueBuilderComponent implements OnChanges, OnInit {
  @ViewChild('editInput') editInput?: ElementRef;

  @Input() fieldId: string = "";
  @Input() details: any[] = [];
  @Output() detailsConfirmed = new EventEmitter<any>();

  // retrieve tags by fieldId
  // fill the select option with the tags
  // take the details, if empty (add case) do nothing
  // let the user play with tags (restrict using a tage thats already used - pref)
  // on confirm return the details array with the value combined too

  userTable: FormGroup;
  control?: FormArray;
  touchedRows: any;

  bankCode: string = ""
  protocol: string = ""
  user1: any

  fr = false;
  en = false;
  esp = false;

  language = ""
  // details: any;
  tags: any;
  constructor(
    private fb: FormBuilder, private fieldProcessingSevice: FieldProcessingService,
    private globalService: GlobalService,
    private auth: AuthService
  ) {
    this.touchedRows = [];
    this.userTable = this.fb.group({
      tableRows: this.fb.array([])
    });
    this.addRow();
  }

  ngOnChanges(changes: SimpleChanges): void {

    this.retriveTags(this.fieldId);
    // console.log(this.details);
    // // !this.details ? this.details = this.getDetailsForFieldId(this.fieldId).details : '';
    // const control = this.userTable.get('tableRows') as FormArray;

    // // Clear existing rows in the form array
    // while (control.length !== 0) {
    //   control.removeAt(0);
    // }

    // this.details ? this.details.forEach(detail => {
    //   // console.log(detail);

    //   this.addRow(detail);
    // }) : '';
  }


  ngOnInit(): void {
    // this.userTable.get('tableRows')?.reset();
    // const control = this.userTable.get('tableRows') as FormArray;
    // control.clear();

    // Promise.resolve().then(() => this.globalService.titleSubject.next("Testing Execution"))
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    this.globalService.protocol.subscribe(val => {
      if (val) {
        // console.log(val)
        this.protocol = val
      }
    })
    if (!this.protocol) {
      this.protocol = localStorage.getItem('protocol') as string
    }
    this.language = localStorage.getItem('lang') || 'en'
    // console.log('this.protocol: ', this.protocol);
    this.auth.user.subscribe(val => {
      this.bankCode = val.bankCode
    })

    this.retriveTags(this.fieldId);
  }

  retriveTags(fieldId: string): void {
    const body = {
      "bankCode": this.bankCode,
      "subfldProtocole": this.protocol
    }

    // console.log("body===>",body);
    //  console.log("retriveTags  fieldId ==>",fieldId ); 

    this.fieldProcessingSevice.retrieveSubFields(fieldId, body)
      .subscribe({
        next: (data: any) => {
          console.log("Filtered Subfields: ", data.result);
          this.tags = data.result;
        },
        error: (error) => {
          // console.log(error);
        }
      });
  }

  ngAfterOnInit() {
    this.control = this.userTable.get('tableRows') as FormArray;
  }

  initiateForm(initialValues: any = {}): FormGroup {
    return this.fb.group({
      name: [initialValues?.name || ''],
      value: [initialValues?.value || ''],
      isEditable: [true]
    });
  }
  addRow(existingDetails: any = {}) {
    // console.log(existingDetails)
    const control = this.userTable.get('tableRows') as FormArray;
    control.push(this.initiateForm(existingDetails));
  }

  deleteRow(index: number) {
    const control = this.userTable.get('tableRows') as FormArray;
    control.removeAt(index);
  }

  editRow(group: FormGroup) {
    group.get('isEditable')?.setValue(true);
  }

  doneRow(group: FormGroup) {
    group.get('isEditable')?.setValue(false);
  }

  saveUserDetails() {
    // console.log(this.userTable.value);
  }

  get getFormControls() {
    const control = this.userTable.get('tableRows') as FormArray;
    return control;
  }

  submitForm() {
    const control = this.userTable.get('tableRows') as FormArray;
    this.touchedRows = control.controls.filter(row => row.touched).map(row => row.value);
    // console.log(this.touchedRows);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  confirmDetails(): void {
    const control = this.userTable.get('tableRows') as FormArray;

    const detailsArray = control.controls.map((row: any) => {
      const name = row.get('name')?.value || '';
      const value = row.get('value')?.value || '';

      return { name, value };
    });

    const combinedValue = detailsArray.map((detail) => {
      let formattedValue: string;

      // Check the fieldId and apply the appropriate conversion logic
      if (this.fieldId === "48") {
        // FieldId is "48", use the existing logic
        formattedValue = `${detail.name}${('00' + detail.value.length).slice(-3)}${detail.value}`;
      } else
        if (this.fieldId === "55") {
          // FieldId is "55", convert byte length to hexadecimal
          const byteLengthHex = Math.ceil(detail.value.length / 2).toString(16).toUpperCase().padStart(2, "0");
          formattedValue = `${detail.name}${byteLengthHex}${detail.value}`;
        } else {
          // Handle other cases as needed
          formattedValue = `${detail.name}${('00' + detail.value.length).slice(-3)}${detail.value}`;
        }

      return formattedValue;
    }).join('');

    // Emit the combined value and details array
    this.detailsConfirmed.emit({ details: detailsArray, value: combinedValue });
  }


  //confirmDetails(): void {
  // const combinedValue = this.selectedField.details.map((detail: any) => detail.value).join('');
  // this.detailsConfirmed.emit(combinedValue);
  //}

  // private getDetailsForFieldId(fieldId: string): any {
  //   switch (fieldId) {
  //     case "3":
  //       return { details: [{ name: 'Processing Code', value: '' }, { name: 'Source Account', value: '' }, { name: 'Destination Account', value: '' }] };
  //     case "22":
  //       return {
  //         details: [
  //           { name: 'Capacité d’entrée de données de carte', value: '' },
  //           { name: 'Capacité d’authentification du titulaire', value: '' },
  //           { name: 'Capacité de rétention de carte', value: '' },
  //           { name: 'Environnement fonctionnel', value: '' },
  //           { name: 'Présence du titulaire de la carte', value: '' },
  //           { name: 'Présence de la carte', value: '' },
  //           { name: 'Mode d’entrée des données de carte', value: '' },
  //           { name: 'Méthode d’authentification du titulaire', value: '' },
  //           { name: 'Entité d’authentification du titulaire', value: '' },
  //           { name: 'Capacité de mise à jour', value: '' },
  //           { name: 'Capacité de sortie du terminal', value: '' },
  //           { name: 'Longueur du PIN', value: '' }
  //         ]
  //       };
  //     case "35":
  //       return {
  //         details: [
  //           { name: 'Card Number', value: '' },
  //           { name: 'Expiry date', value: '' },
  //           { name: 'SSS', value: '' },
  //           { name: 'ADITIONAL DATA', value: '' }
  //         ]
  //       };
  //     case "54":
  //       return {
  //         details: [
  //           { name: 'Type compte1', value: '' },
  //           { name: 'Type montant1', value: '' },
  //           { name: 'Code Monnaie1', value: '' },
  //           { name: 'Crédit/Débit1', value: '' },
  //           { name: 'Montant Balance1', value: '' },
  //           { name: 'Type compte2', value: '' },
  //           { name: 'Type montant2', value: '' },
  //           { name: 'Code Monnaie2', value: '' },
  //           { name: 'Crédit/Débit2', value: '' },
  //           { name: 'Montant Balance2', value: '' }
  //         ]
  //       };
  //     case "53":
  //       return {
  //         details: [
  //           { name: 'Format de sécurité (Pin block encryption method)', value: '' },
  //           { name: 'Format du pin block', value: '' },
  //           { name: 'Index de la clé d\'encryption du PIN', value: '' }
  //         ]
  //       };
  //     default:
  //       return { details: null };
  //   }
  // }
}