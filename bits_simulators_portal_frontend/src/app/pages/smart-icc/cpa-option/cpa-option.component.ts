import { Component, OnInit } from '@angular/core';
import { cbTags } from 'src/app/data/tags_speciaux';
import { EmvTag } from 'src/app/models/EmvTag';
import { GlobalResponse } from 'src/app/models/GlobalResponse';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import { EmvTagServiceService } from 'src/app/services/icc/emv-tag-service.service';


import { dataCase } from 'src/app/data/case';

@Component({
  selector: 'app-cpa-option',
  templateUrl: './cpa-option.component.html',
  styleUrls: ['./cpa-option.component.scss']
})
export class CpaOptionComponent implements OnInit {
  p_profile: number = 0;
  searchProfil="";
  isUpdating=false;
  data_cases:any[] = [];

  countryList:any[] = [];
  cityList:any[] = [];
  currencyList:any[] = [];
  dataBanck:any[] = [];
  selectedComponent: string | null = null;
  isCheckboxDisabled: boolean = true; // Initialisez-le selon vos besoins

  selectComponent(component: string) {
    this.selectedComponent = component;
  }


  user1:any
  language: string = ""
  fr = false;
  en = false;
  esp = false;


  selectedTagOffline: string ="";
  selectedTag: string ="";
   selectedTagName: string ="";
   cbTagsSpeciaux:any[] = [];
   tags: EmvTag[] = [];
   specialTags: EmvTag[] = [];
   data_TagDefinition: any[] = [];
   data_TagDefinitionOffline: any[] = [];
   tagValue: string ="";
   tagValueOffline: string ="";
   showError: boolean = false;

  tab1:boolean = true;
  tab2:boolean = false;
  tab3:boolean = false;

  loading: boolean = false;

  alertBody:any = {
    message:'',
    status:'',
    open:false
  }



  
  //********  */


  panelOpenState = false;

  
  codeProfil: string = "";
  wording: string = "";
  bankCode="";
  currency_code: string = "";
  terminal_type=""
  country_code: string = "";
  cpa_certificate_nbr: string = "";
  amount:any= "";
  cpa_expiry_certificate:any=""
  pan_seq:any=""
  bin_id="";
  terminal_id:any=""
  application_type:any=""
  num_applications:any=""
  offline_pin_supported:any="N"
  pse_supported:any="N"
  offline_supported:any="N"

  cam_supported:any=""
  nbr_pin_verification:any=""

   pin_request_flag:any="N"
   type_pin_verification:any=""
  pin_value:any=""
 
  //// key
  cryptograme:any=""
  payment_Key:any=""
  mac_Key:any=""
  encryption_Key:any=""
  cv_MAC:any=""
  cv_KE:any=""
  cv_KP:any=""


  // data 

  data_cpa:any[]=[];

  
  data_test_cases:any[]=[];
  isChipedON: boolean = true
  isChipedOF: boolean = false;
  selectedProfile: any; // Variable pour stocker le profil sélectionné
  isUpdateButtonEnabled: boolean = false; // Définit si le bouton "Update" est activé ou non
  showSecondDiv: boolean = false; 

  isChipedPIN: boolean = false;
  isSamekey:boolean=false; //

  ispse_supported=false

  isOfflineSupportedON: boolean = true
  isOfflineSupportedOF: boolean = true

  ///  test case 
 
  selectedProfileTestCase: any; // Variable pour stocker le profil sélectionné

  ////**** */

    constructor(
      private globalService:GlobalService,
      private auth:AuthService,
      private emvTagService: EmvTagServiceService,
    
    ){}

  ngOnInit(): void {

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      // console.log("en  Card Analyze ");
      
      Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze / EMV CPA / Options"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze / EMV CPA / Options "));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze / EMV CPA / Options"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }


    // throw new Error('Method not implemented.');
    this.getAllCoutries();
    this.getAllCurrencies();
    this.getAllCPAProfiles();
    this.getAllBanck();

    this.cbTagsSpeciaux=cbTags;
    this.onTagSelectionChange();

    this.emvTagService.getTags().subscribe({
      next: (data: GlobalResponse<EmvTag[]>) => {
        //// console.log(data);
        if (data.respCode === '000') {
          this.tags = data.result || [];
          const tagValuesToInclude = ['95', '9B', '82', '9F07', '9F33', '9F26', '9F40','6F5H'];
          this.specialTags = this.tags.filter(tag => tagValuesToInclude.includes(tag.tag));
          // console.log('specialTags',this.specialTags);

        } else {
          alert("error fetching emv tags");
        }
      }
    })


   



    this.auth.user.subscribe(val => {
      this.bankCode = val.bankCode
      // console.log("bankCode,",val.bankCode);

      if (this.bankCode !="") {
        // console.log('test banck code ',this.bankCode);
        
      }
      
    })

  }

  openTab(index:any){
    this.tab1 = false;
    this.tab2 = false;
    this.tab3 = false;
    switch (index) {
      case 1:
        this.tab2 = true
       
        break;
        case 2:
          this.tab3 = true
          break;
     
      default:
        this.tab1 = true
        break;
    }

    }




    //


    selectProfil(item:any){
      this.data_TagDefinition=[];
      this.isUpdating=true
   
      console.log(item);
      this.selectedProfile=item

      this.getconfigProfilCpa(item);
      
    }

    // getAllCpaProfil

    getAllCPAProfiles(){
      this.globalService.getAllCPAProfiles().subscribe(res => {
        
        this.data_cpa=res.result
        // console.log("datacpa",res.result);
        
      })
    }


    handleCheckboxChangpse_supported() {
      this.ispse_supported = !this.ispse_supported;
      this.pse_supported = this.ispse_supported ? "true" : "false";

    

    }


    handleCheckboxChang() {
      this.terminal_type="ON"
      this.isChipedON=true
      this.isChipedOF=false
     
      
      
    }

    handleCheckboxChange(t: string) {
      if (t == 'Of') {
       
          this.terminal_type="OF"
          this.isChipedON=false
          this.isChipedOF=true
       
    
      }

      if (t =="PIN") {
        this.pin_value=""
        this.isChipedPIN=!this.isChipedPIN;
        this.cam_supported= this.cam_supported ? "Y" : "N";
      }
    }





    handleCheckboxOfflineSupported() {
      this.isOfflineSupportedON = !this.isOfflineSupportedON;
      this.offline_supported = this.offline_pin_supported ? "Y" : "N";
      
    }
    handleCheckboxOfflineSupportedPin(){
      this.isOfflineSupportedOF= !this.isOfflineSupportedOF;
      this.offline_pin_supported = this.offline_pin_supported ? "Y" : "N";
    }

    updateIsSamekey() {
      // Mettre à jour isSamekey en fonction de la valeur de type_pin_verification
      this.isSamekey = false;
    }
    handleCheckboxChangeSamekey()
    {

      this.isSamekey=!this.isSamekey;
      this.cam_supported= this.cam_supported ? "DDA" : "CDA";

    }


      /////// encrypt KEY ****************

  encryptKey(event: any, data: string) {
    let paddedData = '';
    // // console.log('event name', event.target.value);
    
    if (event.target.value.length == 16 || event.target.value.length == 32 || event.target.value.length == 48) {

      if (event.target.value.length === 16) {
        paddedData = '0'.repeat(16); 
      } else if (event.target.value.length === 32) {
        paddedData = '0'.repeat(32); 
      } else if (event.target.value.length === 48) {
        paddedData = '0'.repeat(48); 
      }

      // // console.log( 'paddedData',paddedData);
      
      
      const body={
        key: event.target.value,
        data: paddedData
        // data: "00000000000000000000000000000000"
      }
      // // console.log('body: ', body);
  
      this.globalService.encryptKey(body).subscribe((response) => {
          // // console.log('response',response.result);

          if (data == "payment_Key") {
            this.cv_KP=response.result
          }
          else if (data == "mac_Key") {
            this.cv_MAC=response.result
          }
          else {
            this.cv_KE=response.result
          }

      });

    }else{
      if (data == "payment_Key") {
        this.cv_KP=""
      }
      else if (data == "mac_Key") {
        this.cv_MAC=""
      }
      else {
        this.cv_KE=""
      }
    }
  }



  ////  fin encrypt KEY ********************************


     // ----------------------------> get All Coutries <-------------------------------------------//

  getAllCoutries(){
    this.globalService.fetchCountry().subscribe(res => {
      // // console.log('res Countries: ', res);
  
      res['result'].map((obj:any) => {
        this.countryList.push(obj);
  
      })
      //// console.log('this.countryList: ', this.countryList);
    })
  }
  
  // ----------------------------> get All Currencies <-------------------------------------------//
  getAllCurrencies(){
    this.globalService.fetchCurrency().subscribe(res => {
      // //// console.log('res Currency: ', res);
  
      res['result'].map((obj:any) => {
        this.currencyList.push(obj);
  
      })
      //// console.log('this.currencyList: ', this.currencyList);
    })
  }


   // ----------------------------> get banck <-------------------------------------------//
   getAllBanck(){
    this.globalService.fetchBank().subscribe(res => {
      this.dataBanck=res.result
    })
  }


  // ----------------------------> on Tag Selection Change <-------------------------------------------//
onTagSelectionChange() {
  if (this.selectedTag) {
      const selectedEmvTag = this.tags.find(tag => tag.tag === this.selectedTag);
      if (selectedEmvTag) {
          this.selectedTagName = selectedEmvTag.name;
          // Vous pouvez utiliser selectedEmvTag.tag ou selectedEmvTag.name selon vos besoins
      }
  } else {
      this.selectedTagName = '';
  }
}




    // ------------------------------  get test case  -------------

    getTestCasesCPAProfiles(codeprofile:any){
     const body={
        
          "code_profil": codeprofile
     
      }
      this.globalService.getTestCasesByCodeProfile(body).subscribe(res => {
        
        this.data_test_cases=res.result
        // console.log("this.data_test_cases,",this.data_test_cases);
        
        
      })
    }



addTag() { 
  // console.log("this.data_TagDefinition V1",this.data_TagDefinition);
  

 if (this.selectedTag && this.tagValue) {
  this.showError = false;
     // Créez un nouvel objet tag avec les valeurs sélectionnées et ajoutez-le à data_TagDefinition
     const tangwording=this.tags.find(tag => tag.tag === this.selectedTag)?.name || ''
     const tagtag=this.tags.find(tag => tag.tag === this.selectedTag)?.tag || ''
     const newTag = {
          tagCode: this.selectedTag,
          profileCode: this.codeProfil, // Vous pouvez ajuster cette valeur selon vos besoins
          wording: tagtag +' - '+tangwording|| '' ,// Trouvez le nom du tag correspondant à l'ID sélectionné
          tagValue:this.tagValue
         
     };

     console.log("data_TagDefinition ==>",this.data_TagDefinition);
     console.log("newTag ==>",newTag);
     
     const tagExists = this.data_TagDefinition.some(tag => tag.tagCode === newTag.tagCode);
     console.log("tagExists ==>",tagExists);
     if (!tagExists) {

        this.data_TagDefinition.push(newTag);

        // Effacez la sélection actuelle pour préparer une nouvelle sélection
        this.selectedTag = '';
        this.tagValue=""

     }else{

     }

     // console.log('Nouveau tag ajouté :', newTag);

     // console.log("this.data_TagDefinition V2",this.data_TagDefinition);
 } else {
     // console.log('Aucun tag sélectionné pour l\'ajout');

     this.showError = true;
 }
}

addTagOffline() { 
  // console.log("this.data_TagDefinition V1",this.data_TagDefinition);
  

 if (this.selectedTagOffline && this.tagValueOffline) {
  this.showError = false;
     // Créez un nouvel objet tag avec les valeurs sélectionnées et ajoutez-le à data_TagDefinition
     const tangwording=this.tagsOffline.find(tag => tag.tag === this.selectedTagOffline)?.name || ''
     const tagtag=this.tagsOffline.find(tag => tag.tag === this.selectedTagOffline)?.tag || ''
     const newTag = {
          tagCode: this.selectedTagOffline,
          profileCode: this.codeProfil, // Vous pouvez ajuster cette valeur selon vos besoins
          wording: tagtag +' - '+tangwording|| '' ,// Trouvez le nom du tag correspondant à l'ID sélectionné
          tagValue:this.tagValueOffline
         
     };
     this.data_TagDefinitionOffline.push(newTag);

     // Effacez la sélection actuelle pour préparer une nouvelle sélection
     this.selectedTagOffline = '';
     this.tagValueOffline=""

     // console.log('Nouveau tag ajouté :', newTag);
     // console.log("this.data_TagDefinition V2",this.data_TagDefinitionOffline);
 } else {
     // console.log('Aucun tag sélectionné pour l\'ajout');
     this.showError = true;
 }
}



 selectAll(event: any) {
    const isChecked = event.target.checked;
    cbTags.forEach(tag => tag.selected = isChecked);
  }

  // Fonction pour sélectionner ou désélectionner un élément à la fois
  selectOne() {
   
  }

  cleanLabel(label: string): string {
    const index = label.indexOf('-');
    return index !== -1 ? label.substring(index + 1).trim() : label;
}



deleteTagDefinition() {
  this.data_TagDefinition = this.data_TagDefinition.filter(tag => !tag.selected);
  // console.log(this.data_TagDefinition);
  
}



//----------------------------------------- save profile cpa


data_application_type=[
  { label: "App. associée à un Cpt.", value: 'S' },
  { label: "App. Prépayée", value: 'P' },
  { label: "Porte monnaie", value: 'M' },
  { label: "Autres", value: 'A' }
  
]


tagsOffline=[
  {tag:"BF30|DF01",name:'BF30|DF01- Valeur initiale de l’accumulateur 01 contrôlant les montants cumulés entre deux demandes d’autorisation'},
  {tag:"BF30|DF11",name:'BF30|DF11- Limite basse associée à l’accumulateur 01'},
  {tag:"BF30|DF12",name:'BF30|DF12- Limite basse associée à l’accumulateur 02'},
  {tag:"BF35|DF11",name:'BF35|DF11- Limite basse associée au compteur 01'},
  {tag:"BF3C|DF01",name:'"BF3C|DF01- Limite associée à l’accumulateur cyclique 01'},
  {tag:"BF3C|DF02",name:'BF3C|DF02- Montant maximum d’une transaction pouvant être approuvée offline'},
  {tag:"C3",name:'C3- Nombre maximum de jours sans demande d’autorisation online'},

]

CryptogramKeyCvKp(){
  this.cv_KP="111111111111"
 // // console.log(this.cvKp);
 
}
CryptogramKeyCvMac(){
 this.cv_MAC="111111111111"

}

CryptogramKeyCvKe(){
 this.cv_KE="111111111111"
 
}

saveCpaProfil(){

  const body={

    "profile_code": this.codeProfil,
    "wording": this.wording,
    "bank_code": this.bankCode,
    "bin_id": this.bin_id,
    "cpa_certificate_nbr": this.cpa_certificate_nbr,
    "cpa_expiry_certificate": this.cpa_expiry_certificate,
    "pse_supported": this.pse_supported,
    "application_type": this.application_type,
    "num_recs_trx_log": 0,
    "offline_supported": this.offline_supported,
    "cam_supported": this.cam_supported,
    "offline_pin_supported": this.offline_pin_supported,
    "type_pin_verification": this.type_pin_verification,
    "type_pin_rsa": "",
    "nbr_pin_verification": this.nbr_pin_verification,
    "pin_request_flag": this.pin_request_flag,
    "pin_value": this.pin_value,
    "country_code": this.country_code,
    "num_applications": this.num_applications,
    "pan_seq": this.pan_seq,
    "currency_code": this.currency_code,
    "terminal_type": this.terminal_type,
    "terminal_id": this.terminal_id,
    "amount": this.amount

  }

  const bodyKey={
         "profile_code": this.codeProfil,
        "cryptograme": this.cryptograme,
        "payment_Key": this.payment_Key,
        "mac_Key": this.mac_Key,
        "encryption_Key": this.encryption_Key,
        "cv_MAC": this.cv_MAC,
        "cv_KE": this.cv_KE,
        "cv_KP": this.cv_KP
  }


  const bodykeyDelete={
    "code_profil":this.codeProfil
  }

  
 
  dataCase.forEach(dataCase => {
    // Créer un nouvel objet bodyCase pour chaque élément
    const bodyCase = {
      "profileCode": this.codeProfil,
      "test_case": dataCase.TEST_CASE,
      "sub_case": dataCase.SUB_CASE,
      "wording": dataCase.WORDING,
      "description": dataCase.DESCRIPTION,
      "exec_seq": dataCase.EXEC_SEQ
    };
  
    // Ajouter le nouvel objet bodyCase au tableau bodyCases
    this.data_cases.push(bodyCase);
  });




  this.globalService.deleteCpaTagsProfilById(bodykeyDelete).subscribe(res => {
   
    
    if (res.respCode== "000") {
      this.globalService.saveCpaTagsProfilById(this.data_TagDefinition).subscribe(res => {
        if (res.respCode== "000") {
        //  // console.log("test 2",res.respCode);
        }else{
         // console.log(" error saved successful");
        }
        
     })

    }else{
     // console.log(" error saved successful");
    }
    
 })

 
 this.globalService.deleteCasesTest(bodykeyDelete).subscribe(res => {
  // console.log("test",res.respCode);
  if (res.respCode== "000") {
    this.globalService.saveTestCase(this.data_cases).subscribe(res => {
      if (res.respCode== "000") {
        // console.log("test 2",res.respCode);
      }else{
       // console.log(" error saved successful");
      }
      
    })

  }else{
   // console.log(" error saved successful");
  }
  
})



  this.globalService.saveKeyCpaProfil(bodyKey).subscribe(res => {
     if (res.respCode== "000") {
      // console.log(" TransactionConfig saved successful");

     }else{
      // console.log(" error saved successful");
     }
     
  })

  


  

  this.globalService.saveCpaProfil(body).subscribe(res => {
    if (res.respCode== "000") {
     // console.log(" TransactionConfig saved successful");

     this.getAllCPAProfiles();

     
   if (this.isUpdating) {
        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "Configuration profile update successfully"
        }
        if(this.fr){
          this.alertBody.message = "Mise à jour réussie du profil de configuration"
        }
        if(this.esp){
          this.alertBody.message = "Actualización del perfil de configuración exitosamente"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
       }else{
        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "Configuration profile saved successfully"
        }
        if(this.fr){
          this.alertBody.message = "Profil de configuration enregistré avec succès"
        }
        if(this.esp){
          this.alertBody.message = "Perfil de configuración guardado exitosamente"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
       }
       this.init();
   }else{
      this.alertBody.status = "Something went wrong, please check that the entred data is valid"
      if(this.en){
        this.alertBody.message = "SomeThing went wrong (Invalid cridentials or connection issue)"
      }
      if(this.fr){
        this.alertBody.message = "Une erreur s'est produite (identifiants invalides ou problème de connexion)"
      }
      if(this.esp){
        this.alertBody.message = "Algo salió mal (credenciales no válidas o problema de conexión)"
      }
      this.alertBody.open = true
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);
   }
  })


  // // console.log("bodyCases",this.data_cases);



}



enableUpdateButton() {
  this.isUpdateButtonEnabled = !!this.selectedProfile; // Activer le bouton "Update" si un profil est sélectionné
 
  this.showUpdateDiv();
}

showUpdateDiv() {
  this.showSecondDiv = true; // Afficher le deuxième div lorsque le bouton "Update" est cliqué
  this.getconfigProfilCpa(this.selectedProfile);
}

showNewProfilCpa() {
       this.selectedProfile=""
      this.showSecondDiv = true; 
      this.init();
}


init(){
  this.isUpdating=false;
  this.codeProfil=""
  this.wording=""
  this.bankCode=""
  this.bin_id=""
  this.cpa_certificate_nbr=""
  this.cpa_expiry_certificate=""
  this.pse_supported=""
  this.application_type=""
  // num_recs_trx_log
  this.offline_supported=""
  this.cam_supported=""
  this.offline_pin_supported=""
  this.type_pin_verification=""
  this.terminal_type=""
  // type_pin_rsa
  this.nbr_pin_verification=""
  this.pin_request_flag=""
  this.pin_value=""
  this.country_code=""
  this.num_applications=""
  this.pan_seq=""
  this.currency_code=""
   this.terminal_type=""
  this.terminal_id=""
  this.amount=""

  this.cryptograme=""
  this.payment_Key=""
  this.mac_Key=""
  this.encryption_Key=""
  this.cv_MAC=""
  this.cv_KE=""
  this.cv_KP=""

  this.data_TagDefinition=[]
}

////



getconfigProfilCpa(code: any){

  const body={
  
      "code_profil": code

  }

  this.isUpdateButtonEnabled = !!this.selectedProfile; 

  // console.log(' profile selected ==> ',this.selectedProfile);
  this.isChipedON=false;
  this.isOfflineSupportedON=false;
  this.isOfflineSupportedON=false

  this.globalService.getCpaProfilById(body).subscribe(res => {
    if (res.respCode === "000") {
      console.log(res.result);
      
      this.codeProfil=res.result.profile_code,
      this.wording=res.result.wording;
      this.bankCode=res.result.bank_code;
      this.bin_id=res.result.bin_id;
      this.cpa_certificate_nbr=res.result.cpa_certificate_nbr;
      this.cpa_expiry_certificate=res.result.cpa_expiry_certificate;
      this.pse_supported=res.result.pse_supported;
      if (this.pse_supported==="Y") {
        this.ispse_supported=true
      }else{
        this.ispse_supported=false
      }

      this.application_type=res.result.application_type
      // num_recs_trx_log
      this.offline_supported=res.result.offline_supported
      if (res.result.offline_supported ==="Y") {
        this.isOfflineSupportedON=true
        
      }else{
        this.isOfflineSupportedON=false
      }
      this.cam_supported=res.result.cam_supported
      if (res.result.cam_supported==="DDA") {
        this.isSamekey=true
        
      }else{
        this.isSamekey=false
      }
      this.offline_pin_supported=res.result.offline_pin_supported
      if (res.result.offline_pin_supported==="ON") {
        this.isOfflineSupportedON=true
      }else{
        this.isOfflineSupportedOF=true
      }
      this.type_pin_verification=res.result.type_pin_verification
      // type_pin_rsa
      this.nbr_pin_verification=res.result.nbr_pin_verification
      this.pin_request_flag=res.result.pin_request_flag
      if (res.result.pin_request_flag==="Y") {
        this.isChipedPIN=true
      }else{
        this.isChipedPIN=false
      }
      this.pin_value=res.result.pin_value
      this.country_code=res.result.country_code
      this.num_applications=res.result.num_applications
      this.pan_seq=res.result.pan_seq
      this.currency_code=res.result.currency_code
      this.terminal_type=res.result.currency_code
      this.terminal_id=res.result.terminal_type
      if (res.result.terminal_type ==="ON") {
        this.isChipedOF=false,
        this.isChipedON=true
        
      }else{
        this.isChipedOF=true,
        this.isChipedON=false
      }
      this.amount=res.result.amount

    } else {
        // console.log('Une erreur s\'est produite lors de la suppression des définitions de tag');
    }
  })


    this.globalService.getParamKEyCpaProfilById(body).subscribe(res => {
      if (res.respCode === "000") {
        this.cryptograme=res.result.cryptograme
        this.payment_Key=res.result.payment_Key
        this.mac_Key=res.result.mac_Key
        this.encryption_Key=res.result.encryption_Key
        this.cv_MAC=res.result.cv_MAC
        this.cv_KE=res.result.cv_KE
        this.cv_KP=res.result.cv_KP
       
        
      }
      else {
        // console.log('Une erreur s\'est produite lors de la suppression des définitions de tag');
    }})


    

    this.globalService.getCpaTagsProfilById(body).subscribe(res => {
      if (res.respCode === "000") {
       
        this.data_TagDefinition=res.result
        
      }
      else {
        // console.log('Une erreur s\'est produite lors de la suppression des définitions de tag');
    }})

}


////


deleteProfilCpa(){
  const body={
    "code_profil": this.selectedProfile
  }

  console.log("body ===>",body);
  

  this.globalService.deleteCpaProfil(body).subscribe(res => {
    if (res.respCode === "000") {
      // console.log(res.result.profile_code,);

    }else{

    }
  })

  this.globalService.deleteKeyCpaProfil(body).subscribe(res => {
    if (res.respCode === "000") {
      // console.log(res.result.profile_code,);
      this.getAllCPAProfiles();
      this.alertBody.status = res.respCode
      this.alertBody.message = res.respMsg;
      this.alertBody.open = true
      this.init();
    
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);
    }else{
      this.alertBody.status = res.respCode
      this.alertBody.message = res.respMsg;
      this.alertBody.open = true
      setTimeout(() => {
        this.alertBody.open = false;
      }, 3000);
    }
  })
  

}


selectProfilTest(item: any) {
  // console.log(item);
  this.getTestCasesCPAProfiles(item);
}
  

}
