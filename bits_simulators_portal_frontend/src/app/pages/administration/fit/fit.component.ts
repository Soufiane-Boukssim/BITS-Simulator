import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-fit',
  templateUrl: './fit.component.html',
  styleUrls: ['./fit.component.scss']
})
export class FitComponent implements OnInit {

  financialForm!: FormGroup;
  showSecurity: boolean = false;
  showIndexPin: boolean = false;

  // Dropdown options
  pstdxOptions: string[] = ['0', '1', '2', '3', '4', '5', '6', '7'];
  pinCheckLengthOptions: string[] = ['DES', 'VISE', 'DIEBOLD', 'Identikey'];
  authMethodOptions: string[] = ['Local','Host'];
  maxPinEntryDigitsOptions: string[] = ['Diebold', 'ANSI', 'Specified by PBFMT (PIN Block Format)', 'BANKSYS'];
  utilisNoCoordOptions: string[] = ['Option1', 'Option2', 'Option3']; // Update with actual options

  dataTrackFields = [
    { id: 'pagdx', label: 'PAGDX', controlName: 'pagdx' },
    { id: 'piddx', label: 'PIDDX', controlName: 'piddx' },
    { id: 'prcntpin', label: 'PRCNTPIN', controlName: 'prcntpin' },
    { id: 'pandx', label: 'PANDX', controlName: 'pandx' },
    { id: 'plndx', label: 'PLNDX', controlName: 'plndx' },
    { id: 'pofdxxPin', label: 'POFDX PIN', controlName: 'pofdxxPin' }
  ];

  constructor(private fb: FormBuilder, private globalService: GlobalService) {}

  ngOnInit(): void {
    this.initForm();

    const user1 = localStorage.getItem('user');
    if (user1) {
      const parsedUser = JSON.parse(user1);
      const language = parsedUser.languageCode;
      const title = "Smart ATM / NDC / FIT";
      this.globalService.titleSubject.next(title);
    }
  }

  private initForm(): void {
    this.financialForm = this.fb.group({
      profile: [{ value: '', disabled: true }],
      code: ['', Validators.required],
      libelle: ['', Validators.required],
      institutionIndex: ['', Validators.required],
      identifiantInstitution: ['', Validators.required],
      algoNumberIndex: ['', Validators.required],
      panIndex: ['', Validators.required],
      panLength: ['', Validators.required],
      panPadCharacter: ['', Validators.required],
      pstdx: ['', Validators.required],
      pinCheckLength: ['', Validators.required],
      authMethod: ['', Validators.required],
      authLength: ['', Validators.required],
      maxPinEntryDigits: ['', Validators.required],
      pmxpnLength: ['', Validators.required],
      paddingCharacter: [''],
      hex: [''],
      encryptionType: [''],
      key1: [''],
      key2: [''],
      utilisNoCoord: [''],
      padCharacter: [''],
      encryptedPinKey: [''],
      codeLanguage: [''],
      pagdx: [''],
      piddx: [''],
      prcntpin: [''],
      pandx: [''],
      plndx: [''],
      pofdxxPin: [''],
      pinRetryCount: [''],
      offsetNumberIndex: ['']
    });
  }

  onSubmit(): void {
    console.log(this.financialForm.value);
  }

  showSecurityForm(): void {
    this.showSecurity = true;
    this.showIndexPin = false;
  }

  showIndexPinForm(): void {
    this.showIndexPin = true;
    this.showSecurity = false;
  }

  showDataTrackTable(): void {
    // Handle showing the data track table
  }
}
