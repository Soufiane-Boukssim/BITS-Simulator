import { Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-pos-value-definer',
  templateUrl: './pos-value-definer.component.html',
  styleUrls: ['./pos-value-definer.component.scss']
})
export class PosValueDefinerComponent implements OnChanges  ,OnInit {
  @ViewChild('editInput') editInput?: ElementRef;

  @Input() fieldId: string = "";
  @Input() status: string = "";
  @Input() details: any[] = [];
  @Output() detailsConfirmed = new EventEmitter<any>();


  detailsMontantSup: any[] = [];

  selectedTypeCompte=""
  selectedSinge=""
  selectedTypeMontant=""
  selectedCodeMonnaie=""
  montant=0

  // selectedField: any;
  editingDetail: any = {name: "", value: ""};
  allowEdit: boolean = false;
  v: string = "";
  selectedDetail: any;
  constructor(
    private globalService: GlobalService,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
    // console.log(this.details)
  }
  accountList: any = []
  functionList: any = []
  processingList: any = []
  responseList: any = []
  mccList: any = []
  countryList: any = []
  currencyList: any = []

  protocol: string = "PO";
  bankCode: any = "";
  countryCode=""

  showForm: boolean = false


  processingCode=""
  accountCode=""
  accountCodedesination=""

  functionCode=""
  responseCode=""
  mccCode=""

  currencyCode=""

  ngOnInit(): void {
    // console.log("this.fieldId ==>",this.fieldId);

    // console.log("status===>",this.status);
    

  

    // console.log("this.details V1 ==>" , this.details);
    // // console.log("this.details[0]?.value )==>",this.details[0]?.value);
    
   
    if ((this.fieldId === '22' || this.fieldId === '35' || this.fieldId === '53' ) && this.status=="add") {
      this.updateSelectedField();
     }


    if (this.fieldId != '22' && this.fieldId != '35' && this.fieldId != '53' && this.status==="add") {
      this.details = [];
    }

    // console.log("this.details[0]?.value )==>",this.details[0]?.value);
   
    // console.log("this.details V2 ==>" , this.details);
    

    this.authService.user.subscribe(val => {
      this.bankCode = val.bankCode

      // console.log("test bank",this.bankCode);
      
    })
   

    this.getProcessingList();
    this.getAccountList();
    this.getFunctionList();
    this.getResponseList();
    this.getMccList();
    this.getCountryList();
    this.getCurrencyList();

    if (this.fieldId==='3') {
      this.processingCode=this.details[0]?.value
      this.accountCode=this.details[1]?.value
      this.accountCodedesination=this.details[2]?.value
    }else if (this.fieldId === '24' && this.details && this.details.length > 0) {
      // console.log("24444" ,this.details);
      // console.log("this.details[0]?.value,",this.details[0]?.value);
    
      this.functionCode= this.details[0]?.value;
    }
    else if (this.fieldId === '39' && this.details && this.details.length > 0) {
      this.responseCode= this.details[0]?.value;
    }
    else if (this.fieldId === '18' && this.details && this.details.length > 0) {
      this.mccCode= this.details[0]?.value;
    }
    else if ((this.fieldId === '19' || this.fieldId === '21') && this.details && this.details.length > 0) {
      this.countryCode= this.details[0]?.value;
    }
    else if ((this.fieldId === '49' || this.fieldId === '50' || this.fieldId === '51') && this.details && this.details.length > 0) {
      this.currencyCode= this.details[0]?.value;
    }
    else if (this.fieldId === '54' && this.details && this.details.length > 0) {
     this.detailsMontantSup=this.details
    }
    else{
      // console.log("not implemented");
      
    }

    

  }


  getProcessingList() {

    // console.log(" satart  getProcessingList  ==============================>*");
    
    const body={
      procCode: "00",
      procProtocol: this.protocol,
      bankCode: this.bankCode
    }
    this.globalService.fetchProcessingByProtocolPOS(body).subscribe(response => {
      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.processingList = response.result
        // console.log('this.processingList: ', this.processingList);
      }
    })
    
  }

   //Account
   getAccountList() {
    const body={
      bankCode:this.bankCode,
      accProtocol:this.protocol
    }

    this.globalService.fetchAccountPOS(body).subscribe(response => {

      if (response.result.length > 0) {
        this.accountList = response.result
    
      }
    })

  }

    //function
    getFunctionList() {
    
      const body={
       
          fctCode: "100",
          fctProtocol: this.protocol,
          bankCode: this.bankCode
      
    
      }
      this.globalService.fetchFunctionByProtocolPOS(body).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.functionList = response.result
          // console.log('this.functionList: ', this.functionList);
        }
      })

    }

     //response
  getResponseList() {
    let id = {
      "bankCode": this.bankCode,
      "respProtocol": this.protocol
    }
    // console.log('id: ', id);
    this.globalService.fetchResponsePOS(id).subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.responseList = response.result
        // console.log('this.responseList: ', this.responseList);
      }
    })
  }
  

    //mcc
    getMccList() {
      const body={
        bankCode: this.bankCode,
        mccProtocol: this.protocol
      }
    
      this.globalService.fetchMccPOS(body).subscribe(response => {
  
        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.mccList = response.result
          // console.log('this.mccList: ', this.mccList);
        }
      })
    }



    //country
    getCountryList() {

      this.globalService.fetchCountry().subscribe(response => {

        // console.log('response: ', response);
        if (response.result.length > 0) {
          this.countryList = response.result
          // console.log('this.countryList: ', this.countryList);
        }
      })
    
    }

   //currency
  getCurrencyList() {

    this.globalService.fetchCurrency().subscribe(response => {

      // console.log('response: ', response);
      if (response.result.length > 0) {
        this.currencyList = response.result
        // console.log('this.currencyList: ', this.currencyList);
      }
    })
  }


    /////////******************* Field 3  */

  updateDetails(event: any) {
    const selectedValue = event.target.value;
  
    // Recherchez l'objet correspondant à la valeur sélectionnée dans processingList
    const selectedProc = this.processingList.find((p: { id: { procCode: any; }; }) => p.id.procCode === selectedValue);
  
    if (selectedProc) {
      // Créer un nouvel objet contenant le nom et la valeur sélectionnée
      const newDetail = {
        name: selectedProc.procDesc,
        value: selectedValue
      };
  
    
     
      if (this.details.length > 0) {
        // Si un élément existe, mettez à jour ses valeurs
        this.details[0] = newDetail;
      } else {
        // Sinon, ajoutez-le à la position 0 du tableau 'details'
        this.details.unshift(newDetail);
      }

    }
  
    // // console.log('this.details:::>', this.details);
  }
  


  updateDetailsSelectAccount(event: any) {
    const selectedValue = event.target.value;
  
    // Recherchez l'objet correspondant à la valeur sélectionnée dans accountList
    const selectedProc = this.accountList.find((p: { id: { accCode: any; }; }) => p.id.accCode === selectedValue);
  
    if (selectedProc) {
      // Créer un nouvel objet contenant le nom et la valeur sélectionnée
      const newDetail = {
        name: selectedProc.accDesc,
        value: selectedValue
      };
  
      // Vérifiez si un élément existe déjà à la position 1 du tableau 'details'
      if (this.details.length > 1) {
        // Si un élément existe, mettez à jour ses valeurs
        this.details[1] = newDetail;
      } else {
        // Sinon, ajoutez-le à la position 1 du tableau 'details'
        this.details.splice(1, 0, newDetail);
      }
    }
  
    // // console.log('this.details account:::>', this.details);
  }


  updateDetailsSelectAccountDestination(event: any) {
    const selectedValue = event.target.value;
  
    // Recherchez l'objet correspondant à la valeur sélectionnée dans accountList
    const selectedProc = this.accountList.find((p: { id: { accCode: any; }; }) => p.id.accCode === selectedValue);
  
    if (selectedProc) {
      // Créer un nouvel objet contenant le nom et la valeur sélectionnée
      const newDetail = {
        name: selectedProc.accDesc,
        value: selectedValue
      };
  
      // Vérifiez si un élément existe déjà à la position 1 du tableau 'details'
      if (this.details.length > 2) {
        // Si un élément existe, mettez à jour ses valeurs
        this.details[2] = newDetail;
      } else {
        // Sinon, ajoutez-le à la position 1 du tableau 'details'
        this.details.splice(2, 0, newDetail);
      }
    }
  }
  
  ////////////******** function  field 24 */

  updateDetailsFunction(event: any) {

     this.details=[];
    const selectedValue = event.target.value;
  
    // Recherchez l'objet correspondant à la valeur sélectionnée dans processingList
    const selectedProc = this.functionList.find((p: { id: { fctCode: any; }; }) => p.id.fctCode === selectedValue);
  
    if (selectedProc) {
      // Créer un nouvel objet contenant le nom et la valeur sélectionnée
      const newDetail = {
        name: selectedProc.fctDesc,
        value: selectedValue
      };
  
    
     
      if (this.details.length > 0) {
        // Si un élément existe, mettez à jour ses valeurs
        this.details[0] = newDetail;
      } else {
        // Sinon, ajoutez-le à la position 0 du tableau 'details'
        this.details.unshift(newDetail);
      }

    }
  
    // console.log('this.details:::>', this.details);
  }


  ////////////******** Response  field 39 */

  updateDetailsResponse(event: any) {

    this.details=[];
    const selectedValue = event.target.value;
  
    // Recherchez l'objet correspondant à la valeur sélectionnée dans processingList
    const selectedProc = this.responseList.find((p: { id: { respCode: any; }; }) => p.id.respCode === selectedValue);
  
    if (selectedProc) {
      // Créer un nouvel objet contenant le nom et la valeur sélectionnée
      const newDetail = {
        name: selectedProc.respDesc,
        value: selectedValue
      };

      if (this.details.length > 0) {
        // Si un élément existe, mettez à jour ses valeurs
        this.details[0] = newDetail;
      } else {
        // Sinon, ajoutez-le à la position 0 du tableau 'details'
        this.details.unshift(newDetail);
      }

    }
  
    // // console.log('this.details:::>', this.details);
  }



    ////////////******** Mcc  field 18 */

    updateDetailsMcc(event: any) {

      this.details=[];

      const selectedValue = event.target.value;
    
      // Recherchez l'objet correspondant à la valeur sélectionnée dans processingList
      const selectedProc = this.mccList.find((p: { id: { mccCode: any; }; }) => p.id.mccCode === selectedValue);
    
      if (selectedProc) {
        // Créer un nouvel objet contenant le nom et la valeur sélectionnée
        const newDetail = {
          name: selectedProc.mccDesc,
          value: selectedValue
        };
  
        if (this.details.length > 0) {
          // Si un élément existe, mettez à jour ses valeurs
          this.details[0] = newDetail;
        } else {
          // Sinon, ajoutez-le à la position 0 du tableau 'details'
          this.details.unshift(newDetail);
        }
  
      }
    
      // // console.log('this.details:::>', this.details);
    }
  
 ////////////******** Country  field 19  21 */

 updateDetailsCountry(event: any) {

  this.details=[];

  const selectedValue = event.target.value;

  // Recherchez l'objet correspondant à la valeur sélectionnée dans processingList
  const selectedProc = this.countryList.find((p: { id: any; }) => p.id === selectedValue);

  // console.log("selectedProc000", selectedProc);
  
  if (selectedProc) {
    // Créer un nouvel objet contenant le nom et la valeur sélectionnée
    const newDetail = {
      name: selectedProc.countryDesc,
      value: selectedValue
    };

    if (this.details.length > 0) {
      // Si un élément existe, mettez à jour ses valeurs
      this.details[0] = newDetail;
    } else {
      // Sinon, ajoutez-le à la position 0 du tableau 'details'
      this.details.unshift(newDetail);
    }

  }

  // console.log('this.details:: 18 :> ', this.details);
}


////////////******** Currency  field 49 50 51 */

    updateDetailsCurrency(event: any) {

      this.details=[];

      const selectedValue = event.target.value;

      // Recherchez l'objet correspondant à la valeur sélectionnée dans processingList
      const selectedProc = this.currencyList.find((p: { id: any; }) => p.id === selectedValue);

      if (selectedProc) {
        // Créer un nouvel objet contenant le nom et la valeur sélectionnée
        const newDetail = {
          name: selectedProc.ccyDesc,
          value: selectedValue
        };

        if (this.details.length > 0) {
          // Si un élément existe, mettez à jour ses valeurs
          this.details[0] = newDetail;
        } else {
          // Sinon, ajoutez-le à la position 0 du tableau 'details'
          this.details.unshift(newDetail);
        }

      }

      // // console.log('this.details:::>', this.details);
    }




    //// ******* field 54 


    add(){
      this.showForm=true

      this.selectedTypeCompte = "";
      this.selectedSinge = "";
      this.selectedTypeMontant = "";
      this.selectedCodeMonnaie = "";
      this.montant = 0;

    }

    cancelAdd(){
      this.showForm=false
    }


    SaveUpdatedetailsMontantSup() {
      const existingIndex = this.detailsMontantSup.findIndex(item => item["Type compte"] === this.selectedTypeCompte);
      
      if (existingIndex !== -1) {
        // Si l'élément existe déjà, effectuer une mise à jour
        this.detailsMontantSup[existingIndex] = {
          "Type compte": this.selectedTypeCompte,
          "Singe": this.selectedSinge,
          "Type montant": this.selectedTypeMontant,
          "Code monnaie": this.selectedCodeMonnaie,
          "Montant": this.montant
        };
      } else {
        // Si l'élément n'existe pas, effectuer une ajout
        this.detailsMontantSup.push({
          "Type compte": this.selectedTypeCompte,
          "Singe": this.selectedSinge,
          "Type montant": this.selectedTypeMontant,
          "Code monnaie": this.selectedCodeMonnaie,
          "Montant": this.montant
        });
      }
    
      // Réinitialiser les valeurs après l'opération
      this.selectedTypeCompte = "";
      this.selectedSinge = "";
      this.selectedTypeMontant = "";
      this.selectedCodeMonnaie = "";
      this.montant = 0;

      this.showForm=false
    }
    


    editdetailsMontantSupById(detail: any) {
      this.showForm=true;
      // console.log("detail: " , detail);
      this.selectedTypeCompte =detail['Type compte'];
      this.selectedSinge = detail['Singe'];
      this.selectedTypeMontant = detail['Type montant'];
      this.selectedCodeMonnaie = detail['Code monnaie'];
      this.montant = detail['Montant'];

    }
    
    deleteeditdetailsMontantSupByID(detail: any) {
      const index = this.detailsMontantSup.indexOf(detail);
      if (index !== -1) {
        this.detailsMontantSup.splice(index, 1);
      }
    }





    confirmdetailsMontantSup(): void {
      // // console.log("detailsMontantSup", this.detailsMontantSup);
      let montantExponent: number;
      const combinedValue = this.detailsMontantSup.map(detail => {
        const currency = this.currencyList.find((c: { id: any; }) => c.id === detail["Code monnaie"]);

        if (currency) {
          const exponent = currency.ccyExponent;
           montantExponent = detail["Montant"] * Math.pow(10, exponent);

          // // console.log("test montant: " + montantExponent);
          
        }
        // const formattedMontant = detail["Montant"].toString().padStart(12, "0");
        const formattedMontant = montantExponent.toString().padStart(12, "0");
        // Concaténer les propriétés
        return detail["Type compte"] + detail["Type montant"] + detail["Code monnaie"] + detail["Singe"] + formattedMontant;
      }).join(''); // Fusionner tous les éléments sans virgule
    
      // // console.log("combinedValue", combinedValue);


      

      const res = {
        details: this.detailsMontantSup,
        value: combinedValue
      }
      this.detailsConfirmed.emit(res);
    }






////////////////////////////
  ngOnChanges(): void {
    // console.log(this.fieldId);
    // console.log("this.details  ngOnChanges ==>", this.details);
    // !this.details ? this.updateSelectedField() : '';
  }

  private updateSelectedField(): void {
    this.details = this.getDetailsForFieldId(this.fieldId);
    // console.log("this.details ==> ",this.details);
  }

  editDetail(detail: any): void {
    this.editingDetail = { ...detail };
    // console.log(this.editingDetail)
    this.allowEdit = true;
    this.editInput?.nativeElement.focus();
  }

  saveDetail(): void {
    // Update the original detail with the edited value
    const index = this.details.findIndex((detail: any) => detail.name === this.editingDetail.name);
    if (index !== -1) {
      this.details[index].value = this.editingDetail.value;
    }
    // this.editingDetail = null; // Reset editing state
    this.allowEdit = false;
    // console.log(this.details);
  }

  confirmDetails(): void {
    const combinedValue = this.details.map((detail: any) => detail.value).join('');
    const res = {
      details: this.details,
      value: combinedValue
    }
    this.detailsConfirmed.emit(res);

    // this.details=[]
  }


  // TODO shld  be passed from parent component
  private getDetailsForFieldId(fieldId: string): any {
    switch (fieldId) {
      // case "3":
      //   return [{ name: '', value: '' }, { name: '', value: '' }, { name: '', value: '' }]
      // // return [{ name: 'Processing Code', value: '' }, { name: 'Source Account', value: '' }, { name: 'Destination Account', value: '' }];
      case "22":
        return [
          { name: 'Capacité d’entrée de données de carte', value: '' },
          { name: 'Capacité d’authentification du titulaire', value: '' },
          { name: 'Capacité de rétention de carte', value: '' },
          { name: 'Environnement fonctionnel', value: '' },
          { name: 'Présence du titulaire de la carte', value: '' },
          { name: 'Présence de la carte', value: '' },
          { name: 'Mode d’entrée des données de carte', value: '' },
          { name: 'Méthode d’authentification du titulaire', value: '' },
          { name: 'Entité d’authentification du titulaire', value: '' },
          { name: 'Capacité de mise à jour', value: '' },
          { name: 'Capacité de sortie du terminal', value: '' },
          { name: 'Longueur du PIN', value: '' }
        ];
      case "35":
        return [
          { name: 'Card Number', value: '' }, 
          { name: 'Expiry date', value: '' }, 
          { name: 'SSS', value: '' },
          { name: 'ADITIONAL DATA', value: '' }
        ];
      // case "54":
      //   return [
      //     { name: 'Type compte1', value: '' }, 
      //     { name: 'Type montant1', value: '' }, 
      //     { name: 'Code Monnaie1', value: '' },
      //     { name: 'Crédit/Débit1', value: '' },
      //     { name: 'Montant Balance1', value: '' },
      //     { name: 'Type compte2', value: '' }, 
      //     { name: 'Type montant2', value: '' }, 
      //     { name: 'Code Monnaie2', value: '' },
      //     { name: 'Crédit/Débit2', value: '' },
      //     { name: 'Montant Balance2', value: '' }
      //   ];
      case "53":
        return [
          { name: 'Format de sécurité (Pin block encryption method)', value: '' }, 
          { name: 'Format du pin block', value: '' }, 
          { name: 'Index de la clé d\'encryption du PIN', value: '' }
        ];
      default:
        return null;
    }
  }
}
