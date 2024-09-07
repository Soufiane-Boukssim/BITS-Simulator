import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { emvTagResults } from 'src/app/data/TagResults';
import { EmvTag } from 'src/app/models/EmvTag';
import { GlobalResponse } from 'src/app/models/GlobalResponse';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import { EmvTagServiceService } from 'src/app/services/icc/emv-tag-service.service';

import { cbTags } from 'src/app/data/tags_speciaux';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {


  user1: any
  language: string = ""
  fr = false;
  en = false;
  esp = false;

  isUpdating = false;

  countryList: any[] = [];
  cityList: any[] = [];
  currencyList: any[] = [];


  data_profil: any[] = [];
  data_profilAll: any[] = [];

  isDefaultModal: boolean = false;
  isDenialModal: boolean = false;
  isOnlineModal: boolean = false;

  codeProfil: string = "";
  wording: string = "";
  description: string = "";
  dateCreate: string = "";
  activeProfile: string = "";
  respCode: string = "000";


  data_TerminalConfig: any[] = [];
  data_IssuerConfig: any[] = [];

  data_TransactionConfig: any[] = [];
  terminalConfig: string = "";
  terminalControlledBy: string = "";
  isTerminalConfigOn: boolean = false;
  isTerminalConfigOff: boolean = false;
  terminalCountry: string = "";
  terminalCurrency: string = "";
  terminalBaseEmv: string = "";
  terminalTacd: string = "";
  terminalTacr: string = "";
  terminalTaco: string = "";
  terminalMin: string = "";
  terminalMax: string = "";
  terminalValue: string = "";
  terminalFloor: string = "";

  selectedCountry: any;
  selectedCurrency: any;


  cryptogram: string = "";
  keyType: string = "";
  keySet: number = 0;
  paymentKey: string = "";
  macKey: string = "";
  encryptionKey: string = "";
  cvKp: string = "";
  cvMac: string = "";
  cvKe: string = "";

  searchProfil = "";

  trxType: string = "";
  trxNumber: number = 1;
  trxPin: number = 0;
  trxAmt: number = 0;
  trxSuccConnect: number = 10;
  trxIssApproved: number = 10;
  trxIssAuthent: number = 10;

  p_profile: number = 0;

  cbTagsSpeciaux: any[] = [];
  tags: EmvTag[] = [];
  specialTags: EmvTag[] = [];

  cryptograms: { label: string; value: string; }[] = [];

  cBTrxType: { label: string; value: string; }[] = [];


  /////


  data_TagDefinition: any[] = [];
  selectedTags: any[] = [];
  selectedTagsUpdate: any[] = [];
  //////
  selectedTab: number = 0;
  tabButtons: any;


  TagResults: any[] = emvTagResults;

  hexValue: string = '';
  bytes: any[] = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
  results: any[] = [];

  hexValueDenial: string = '';
  bytesDenial: any[] = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];






  hexValueOnline: string = '';
  bytesOnline: any[] = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];

  // TVR
  resultsTVR: any[] = [];
  bytesTVR: any[] = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
  hexValueTVR: string = '';


  // TSI 
  resultsTSI: any[] = [];
  bytesTSI: any[] = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
  hexValueTSI: string = '';

  // CVR 
  resultsCVR: any[] = [];
  bytesCVR: any[] = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
  hexValueCVR: string = '';


  selectedTag: string = "";
  selectedTagName: string = "";




  @Output() selectComponent = new EventEmitter<string>();
  ;


  onSelectComponent(componentName: string) {
    this.selectComponent.emit(componentName);
  }


  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  @ViewChild('analyzeModal', { read: ElementRef }) analyzeModal?: ElementRef

  tabConfig_1: boolean = true
  tabConfig_2: boolean = false
  tabConfig_3: boolean = false
  tabConfig_4: boolean = false
  tabConfig_5: boolean = false



  tab_1: boolean = true
  tab_2: boolean = false
  tab_3: boolean = false
  tab_4: boolean = false
  tab_5: boolean = false
  tab_6: boolean = false

  loading: boolean = false;

  alertBody: any = {
    message: '',
    status: '',
    open: false
  }


  constructor(
    private globalService: GlobalService,
    private emvTagService: EmvTagServiceService,

    private auth: AuthService
    

  ) { }



  // ----------------------------> ngOnInit <-------------------------------------------//

  ngOnInit(): void {


    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      // console.log("en  Card Analyze ");

      Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze / EMV Card "));
      this.en = true
    } else if (this.language === "fr") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze  / EMV Card  "));
      this.fr = true
    } else if (this.language === "es") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Card Analyze  / EMV Card "));
      this.esp = true
    } else {
      Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }

    this.getAllProfil();

    this.getAllCoutries();
    this.getAllCurrencies();
    this.getAllCities();

    this.cbTagsSpeciaux = cbTags

    this.cryptograms = this.constCryptogram;
    this.cBTrxType = this.constCBTrxType

    this.emvTagService.getTags().subscribe({
      next: (data: GlobalResponse<EmvTag[]>) => {
        //// console.log(data);
        if (data.respCode === '000') {
          this.tags = data.result || [];
          const tagValuesToInclude = ['95', '9B', '82', '9F07', '9F33', '9F26', '9F40', '6F5H'];
          this.specialTags = this.tags.filter(tag => tagValuesToInclude.includes(tag.tag));
          // console.log('specialTags',this.specialTags);


          setTimeout(() => {
            this.loading = false;
          }, 3000)

        } else {
          alert("error fetching emv tags");
        }
      }
    })

  }




  constCryptogram = [
    { label: "Visa Smart Debit/Credit", value: 'V' },
    { label: "MasterCard M/chip Lite", value: 'M' },
    { label: "MasterCard M/chip Select", value: 'S' },
    { label: "MasterCard M/chip 4", value: 'N' },
    { label: "EMV CCD", value: 'E' }
  ];


  constCBTrxType = [
    { label: "Purchase", value: 'P' },
    { label: "Cash", value: 'C' },
    { label: "Services", value: 'S' },
    { label: "Cashback", value: 'B' }

  ]

  open_tabDailog(index: number) {
    //// console.log("index",index);
    this.selectedTab = index;



  }

  openModal() {
    //// console.log("test")
    this.modal?.nativeElement.showModal();
  }
  closeModal() {
    this.modal?.nativeElement.close();
  }

  // ----------------------------> open Analyze Modal DEfault <-------------------------------------------//

  openAnalyzeModalDefault() {
    this.analyzeModal?.nativeElement.showModal();

    this.isDefaultModal = true;
    this.isDenialModal = false;
    this.isOnlineModal = false;


    const data = this.TagResults.find(item => item.tag === "AC");
    this.results = data.results;
    // //// console.log(this.results)
    // this.hexValue = "";
    // this.bytes = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
    // const selectedTag = this.specialTags.find(tag => tag.shortName === event.target.value);
    const selectedTag = this.specialTags.find(tag => tag.shortName === "AC");
    if (selectedTag) {
      var length = +selectedTag.length;
      const lengthAsNumber = Math.min(length, 5); // Convert length to number

      this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
      this.selectedTab = 1;

      // this.hexValue = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
      // this.bytes = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
    }


    const decimalValue = parseInt(this.hexValue, 16);

    const binaryValue = decimalValue.toString(2);

    const paddedBinaryValue = binaryValue.padStart(this.results.length, '0');

    // Map through results and update checked based on binary value
    this.results.forEach((result, index) => {
      result.checked = paddedBinaryValue[index] === '1';
    });



  }

  // ----------------------------> open Analyze Modal Denial <-------------------------------------------//
  openAnalyzeModalDenial() {
    this.analyzeModal?.nativeElement.showModal();

    this.isDefaultModal = false
    this.isDenialModal = true
    this.isOnlineModal = false

    const data = this.TagResults.find(item => item.tag === "AC");
    this.results = data.results;
    //// console.log(this.results)
    // this.hexValueDenial = "";
    // this.bytesDenial = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
    // const selectedTag = this.specialTags.find(tag => tag.shortName === event.target.value);
    const selectedTag = this.specialTags.find(tag => tag.shortName === "AC");
    if (selectedTag) {
      var length = +selectedTag.length;
      const lengthAsNumber = Math.min(length, 5); // Convert length to number

      this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
      this.selectedTab = 1;

      // this.hexValueDenial = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
      // this.bytesDenial = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
    }



    const decimalValue = parseInt(this.hexValueDenial, 16);

    const binaryValue = decimalValue.toString(2);

    const paddedBinaryValue = binaryValue.padStart(this.results.length, '0');

    // Map through results and update checked based on binary value
    this.results.forEach((result, index) => {
      result.checked = paddedBinaryValue[index] === '1';
    });


  }

  // ----------------------------> open Analyze Modal Online <-------------------------------------------//

  openAnalyzeModalOnline() {

    this.analyzeModal?.nativeElement.showModal();

    this.isDefaultModal = false
    this.isDenialModal = false
    this.isOnlineModal = true

    const data = this.TagResults.find(item => item.tag === "AC");
    this.results = data.results;
    //// console.log(this.results)
    // this.hexValueDenial = "";
    // this.bytesDenial = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
    // const selectedTag = this.specialTags.find(tag => tag.shortName === event.target.value);
    const selectedTag = this.specialTags.find(tag => tag.shortName === "AC");
    if (selectedTag) {
      var length = +selectedTag.length;
      const lengthAsNumber = Math.min(length, 5); // Convert length to number

      this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
      this.selectedTab = 1;

      // this.hexValueOnline = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
      // this.bytesOnline = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
    }



    const decimalValue = parseInt(this.hexValueOnline, 16);

    const binaryValue = decimalValue.toString(2);

    const paddedBinaryValue = binaryValue.padStart(this.results.length, '0');

    // Map through results and update checked based on binary value
    this.results.forEach((result, index) => {
      result.checked = paddedBinaryValue[index] === '1';
    });


  }

  closeAnalyzeModal() {
    this.analyzeModal?.nativeElement.close();
  }

  // ----------------------------> open tab Config <-------------------------------------------//
  open_tabConfig(tab: number) {
    this.tabConfig_1 = false;
    this.tabConfig_2 = false;
    this.tabConfig_3 = false;
    this.tabConfig_4 = false;
    this.tabConfig_5 = false;
    switch (tab) {
      case 1:
        this.tabConfig_2 = true

        break;

      case 2:
        this.tabConfig_3 = true

        break;

      case 3:
        this.tabConfig_4 = true

        break;

      case 3:
        this.tabConfig_5 = true

        break;


      default:
        this.tabConfig_1 = true
        break;
    }
  }


  open_tabTVR(index: number) {
    this.selectedTab = index;
  }

  open_tabCVR(index: number) {
    this.selectedTab = index;
  }


  open_tabTSI(index: number) {
    this.selectedTab = index;
  }



  // ---------------------------->open tab <-------------------------------------------//
  open_tab(tab: number) {
    this.tab_1 = false;
    this.tab_2 = false;
    this.tab_3 = false;
    this.tab_4 = false;
    this.tab_5 = false;
    // this.tab_6 = false;
    switch (tab) {
      case 1:
        this.tab_2 = true
        break;

      case 2:
        this.tab_3 = true
        // console.log(" resultsTVR",this.bytesTVR);
        this.bytesTVR = [];
        this.hexValueTVR = "";
        this.resultsTVR = [];

        const data = this.TagResults.find(item => item.tag === "TVR");
        this.resultsTVR = data.results;

        const selectedTag = this.specialTags.find(tag => tag.shortName === "TVR");


        if (selectedTag) {
          var length = +selectedTag.length;
          const lengthAsNumber = Math.min(length, 5); // Convert length to number

          this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
          this.selectedTab = 1;

          this.hexValueTVR = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
          this.bytesTVR = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
        }


        // // console.log(" resultsTVR",this.resultsTVR);
        //// console.log(" bytesTVR ===>",this.bytesTVR);
        // // console.log(" hexValueTVR",this.hexValueTVR);



        break;

      case 3:

        this.tab_4 = true
        // console.log(" resultsTVR",this.bytesTVR);
        this.bytesTSI = []
        this.bytesTVR = [];
        this.hexValueTVR = "";
        this.resultsTVR = [];
        // console.log(this.TagResults);
        // // console.log(event.target.value);
        const dataTSI = this.TagResults.find(item => item.tag === 'TSI');
        this.resultsTSI = dataTSI.results;
        // console.log(this.resultsTSI)
        this.hexValueTSI = "";
        this.bytesTSI = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
        const selectedTagTSI = this.specialTags.find(tag => tag.shortName === 'TSI');
        if (selectedTagTSI) {
          var length = +selectedTagTSI.length;
          const lengthAsNumber = Math.min(length, 5); // Convert length to number

          this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
          this.selectedTab = 1;

          // this.hexValueTSI = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
          // this.bytesTSI = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
        }






        break;

      case 4:
        this.tab_5 = true

        //   this.resultsCVR = [];
        //  // // console.log(this.TagResults);
        //   // // console.log(event.target.value);
        //   const dataCVR = this.TagResults.find(item => item.tag === 'CVR');
        //   this.resultsCVR = dataCVR.results;
        //   // console.log(this.resultsCVR)
        //   this.hexValueCVR = "";
        //   this.bytesCVR = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
        //   const selectedTagCVR = this.specialTags.find(tag => tag.shortName === 'CVR');


        //   // console.log('selectedTagCVR',selectedTagCVR);

        //   if (selectedTagCVR) {
        //     var length = +selectedTagCVR.length;
        //     const lengthAsNumber = Math.min(length, 5); // Convert length to number

        //     this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
        //     this.selectedTab = 1;

        //     this.hexValueCVR = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
        //     this.bytesCVR = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
        //   }

        //    // console.log('resultsCVR',this.resultsCVR);
        //    // console.log('bytesCVR',this.bytesCVR);
        this.bytesTSI = []
        this.bytesTVR = [];
        this.hexValueTVR = "";
        this.resultsTVR = [];
        // console.log(this.TagResults);
        // // console.log(event.target.value);
        const dataCVR = this.TagResults.find(item => item.tag === 'CVR');
        this.resultsCVR = dataCVR.results;
        // console.log('CVR data ==>' ,this.resultsCVR)
        this.hexValueCVR = "";
        this.bytesCVR = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
        const selectedTagCVR = this.specialTags.find(tag => tag.shortName === 'CVR');
        // console.log("selectedTagCVR is ==>",selectedTagCVR);

        if (selectedTagCVR) {
          var length = +selectedTagCVR.length;
          const lengthAsNumber = Math.min(length, 5); // Convert length to number

          // console.log("lengthAsNumber==>",lengthAsNumber);


          this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
          this.selectedTab = 1;

          // this.hexValueTSI = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
          // this.bytesTSI = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
        }

        break;


      default:
        this.tab_1 = true
        break;
    }
  }




  // ----------------------------> get All Coutries <-------------------------------------------//

  getAllCoutries() {
    this.globalService.fetchCountry().subscribe(res => {
      // //// console.log('res Countries: ', res);

      res['result'].map((obj: any) => {
        this.countryList.push(obj);

      })
      //// console.log('this.countryList: ', this.countryList);
    })
  }

  // ----------------------------> get All Currencies <-------------------------------------------//
  getAllCurrencies() {
    this.globalService.fetchCurrency().subscribe(res => {
      // //// console.log('res Currency: ', res);

      res['result'].map((obj: any) => {
        this.currencyList.push(obj);

      })
      //// console.log('this.currencyList: ', this.currencyList);
    })
  }

  // ----------------------------> get All Cities <-------------------------------------------//
  getAllCities() {
    this.globalService.fetchCities().subscribe(res => {
      //// console.log("CITIES ", res);
      if (res.result.length > 0) {
        this.cityList = res.result
        //// console.log('this.cityList: ', this.cityList);
      }
    })
  }

  onCountrySelect() {
    //// console.log('Pays sélectionné : ', this.terminalCountry);
  }


  // ----------------------------> on Tag Selected  <-------------------------------------------//

  onTagSelected(event: any) {
    this.results = [];
    //// console.log(this.TagResults);
    //// console.log(event.target.value);
    const data = this.TagResults.find(item => item.tag === "AC");
    this.results = data.results;
    //// console.log(this.results)
    this.hexValue = "";
    this.bytes = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
    // const selectedTag = this.specialTags.find(tag => tag.shortName === event.target.value);
    const selectedTag = this.specialTags.find(tag => tag.shortName === "AC");
    if (selectedTag) {
      var length = +selectedTag.length;
      const lengthAsNumber = Math.min(length, 5); // Convert length to number

      this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
      this.selectedTab = 1;

      this.hexValue = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
      this.bytes = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
    }
  }

  // ----------------------------> update HexValue    <-------------------------------------------//
  updateHexValue() {
    this.hexValue = this.bytes.map(item => item.byte).join('');
  }

  // ----------------------------> update HexValue Denial   <-------------------------------------------//
  updateHexValueDenial() {
    this.hexValueDenial = this.bytesDenial.map(item => item.byte).join('');
  }
  // ----------------------------> update HexValue Online   <-------------------------------------------//
  updateHexValueOnline() {
    this.hexValueOnline = this.bytesOnline.map(item => item.byte).join('');
  }


  // ----------------------------> analyze   <-------------------------------------------//
  analyze() {
    const decimalValue = parseInt(this.hexValue, 16);

    const binaryValue = decimalValue.toString(2);

    const paddedBinaryValue = binaryValue.padStart(this.results.length, '0');

    // Map through results and update checked based on binary value
    this.results.forEach((result, index) => {
      result.checked = paddedBinaryValue[index] === '1';
    });
  }

  // ----------------------------> analyze Denial  <-------------------------------------------//

  analyzeDenial() {
    const decimalValue = parseInt(this.hexValueDenial, 16);

    const binaryValue = decimalValue.toString(2);

    const paddedBinaryValue = binaryValue.padStart(this.results.length, '0');

    // Map through results and update checked based on binary value
    this.results.forEach((result, index) => {
      result.checked = paddedBinaryValue[index] === '1';
    });
  }


  // ----------------------------> analyze Online  <-------------------------------------------//
  analyzeOnline() {
    const decimalValue = parseInt(this.hexValueOnline, 16);

    const binaryValue = decimalValue.toString(2);

    const paddedBinaryValue = binaryValue.padStart(this.results.length, '0');

    // Map through results and update checked based on binary value
    this.results.forEach((result, index) => {
      result.checked = paddedBinaryValue[index] === '1';
    });
  }
  ////// getAllProfil 

  getAllProfil() {
    this.globalService.getAllProfil().subscribe(res => {
      if (res.respCode == "000") {
        this.data_profilAll = res.result
      } else {
        console.error("error", res);
      }


    })
  }


  selectProfil(item: any) {
    this.isUpdating = true
    // console.log(item);
    this.search(item);
  }


  init() {
    this.isUpdating = false;
    this.data_profil = [];
    this.isDefaultModal = false;
    this.isDenialModal = false;
    this.isOnlineModal = false;
    this.codeProfil = "";
    this.wording = "";
    this.description = "";
    this.dateCreate = "";
    this.activeProfile = "";
    this.respCode = "000";
    this.terminalConfig = "";
    this.terminalControlledBy = "";
    this.isTerminalConfigOn = false;
    this.isTerminalConfigOff = false;
    this.terminalCountry = "";
    this.terminalCurrency = "";
    this.terminalBaseEmv = "";
    this.terminalTacd = "";
    this.terminalTacr = "";
    this.terminalTaco = "";
    this.terminalMin = "";
    this.terminalMax = "";
    this.terminalValue = "";
    this.terminalFloor = "";
    this.selectedCountry = null;
    this.selectedCurrency = null;
    this.cryptogram = "";
    this.keyType = "";
    this.keySet = 0;
    this.paymentKey = "";
    this.macKey = "";
    this.encryptionKey = "";
    this.cvKp = "";
    this.cvMac = "";
    this.cvKe = "";
    this.searchProfil = "";
    this.trxType = "";
    this.trxNumber = 1;
    this.trxPin = 0;
    this.trxAmt = 0;
    this.trxSuccConnect = 10;
    this.trxIssApproved = 10;
    this.trxIssAuthent = 10;
    this.p_profile = 0;
    this.bytes = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
    this.hexValue = '';
    this.bytesDenial = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
    this.hexValueDenial = '';
    this.hexValueOnline = '';
    this.bytesOnline = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];


    this.data_TagDefinition = []
  }


  // ----------------------------> Search  <-------------------------------------------//

  onCodeInserted(): void {
    // Appeler la fonction search avec le code de profil saisi.
    this.search(this.codeProfil);

  }


  search(code: string) {
    const body = {
      "code_profil": code
    }

    this.globalService.searchProfile(body).subscribe(res => {
      if (res.respCode == "000") {
        this.data_profil = res.result
        //// console.log('data_profil: ', this.data_profil);
        //// console.log('test wording' ,res.result.wording);
        this.codeProfil = res.result.codeProfile
        this.wording = res.result.wording
        this.description = res.result.description
        this.dateCreate = res.result.dateCreate
        this.activeProfile = res.result.activeProfile;
        this.respCode = "404"

      } else {
        this.data_profil = [],
          this.wording = ""
        this.description = ""
        this.dateCreate = ""
        this.activeProfile = ""
        this.respCode = "000"
      }

    })


    this.globalService.getTermminalCongById(body).subscribe(res => {
      if (res.respCode == "000") {
        this.data_TerminalConfig = res.result

        //// console.log('data_TerminalConfig: ', this.data_TerminalConfig);

        this.terminalConfig = res.result.terminalConfig
        this.terminalControlledBy = res.result.terminalControlledBy
        if (res.result.terminalControlledBy === "ON") {

          this.isTerminalConfigOn = false;
          this.isTerminalConfigOff = true;
        } else {
          this.isTerminalConfigOn = true;
          this.isTerminalConfigOff = false;
        }
        this.terminalCountry = res.result.terminalCountry
        this.terminalCurrency = res.result.terminalCurrency
        this.terminalBaseEmv = res.result.terminalBaseEmv

        this.terminalTacd = res.result.terminalTacd
        this.hexValue = res.result.terminalTacd
        let hexIndex = 0;
        for (let i = 0; i < this.bytes.length; i++) {
          // Si la chaîne actuelle est vide, remplacer par l'octet de la chaîne hexadécimale
          if (this.bytes[i].byte === "") {
            // Extraire l'octet de la chaîne hexadécimale
            let byte = this.hexValue.substring(hexIndex, hexIndex + 2);
            // Mettre à jour l'élément actuel du tableau bytes
            this.bytes[i].byte = byte;
            // Déplacer l'index hexadécimal à l'octet suivant
            hexIndex += 2;
          }
        }

        this.terminalTacr = res.result.terminalTacr
        this.hexValueDenial = res.result.terminalTacr

        let hexIndexDenial = 0;
        for (let i = 0; i < this.bytesDenial.length; i++) {
          // Si la chaîne actuelle est vide, remplacer par l'octet de la chaîne hexadécimale
          if (this.bytesDenial[i].byte === "") {
            // Extraire l'octet de la chaîne hexadécimale
            let byte = this.hexValueDenial.substring(hexIndexDenial, hexIndexDenial + 2);
            // Mettre à jour l'élément actuel du tableau bytes
            this.bytesDenial[i].byte = byte;
            // Déplacer l'index hexadécimal à l'octet suivant
            hexIndexDenial += 2;
          }
        }


        this.terminalTaco = res.result.terminalTaco
        this.hexValueOnline = res.result.terminalTaco

        let hexIndexOnline = 0;
        for (let i = 0; i < this.bytesDenial.length; i++) {
          // Si la chaîne actuelle est vide, remplacer par l'octet de la chaîne hexadécimale
          if (this.bytesOnline[i].byte === "") {
            // Extraire l'octet de la chaîne hexadécimale
            let byte = this.hexValueOnline.substring(hexIndexOnline, hexIndexOnline + 2);
            // Mettre à jour l'élément actuel du tableau bytes
            this.bytesOnline[i].byte = byte;
            // Déplacer l'index hexadécimal à l'octet suivant
            hexIndexOnline += 2;
          }
        }

        this.terminalMin = res.result.terminalMin
        this.terminalMax = res.result.terminalMax
        this.terminalValue = res.result.terminalValue
        this.terminalFloor = res.result.terminalFloor
      } else {
        this.data_TerminalConfig = []

        this.terminalConfig = ""
        this.terminalControlledBy = ""
        this.terminalCountry = ""
        this.terminalCurrency = ""
        this.terminalBaseEmv = ""
        this.terminalTacd = ""
        this.terminalTacr = ""
        this.terminalTaco = ""
        this.terminalMin = ""
        this.terminalMax = ""
        this.terminalValue = ""
        this.terminalFloor = ""

      }

    })

    this.globalService.getIssuerConfigById(body).subscribe(res => {
      if (res.respCode == "000") {
        this.data_TransactionConfig = res.result

        //// console.log('data_TransactionConfig  : ', this.data_TransactionConfig);

        this.cryptogram = res.result.cryptogram;
        this.keyType = res.result.keyType;
        this.keySet = 0;
        this.paymentKey = res.result.paymentKey
        this.macKey = res.result.macKey
        this.encryptionKey = res.result.encryptionKey
        this.cvKp = res.result.cvKp
        this.cvMac = res.result.cvMac
        this.cvKe = res.result.cvKe

      } else {
        this.data_TransactionConfig = []
        this.cryptogram = "";
        this.keyType = "";
        this.keySet = 0;
        this.paymentKey = "";
        this.macKey = ""
        this.encryptionKey = ""
        this.cvKp = ""
        this.cvMac = ""
        this.cvKe = ""
      }
    })


    this.globalService.getTransactionConfigById(body).subscribe(res => {
      if (res.respCode == "000") {
        this.data_TransactionConfig = res.result

        //// console.log('data_TransactionConfig test : ', this.data_TransactionConfig);

        this.trxType = res.result.trxType;
        this.trxNumber = res.result.trxNumber;
        this.trxPin = res.result.trxPin;
        this.trxAmt = res.result.trxAmt;
        this.trxSuccConnect = res.result.trxSuccConnect;
        this.trxIssApproved = res.result.trxIssApproved;
        this.trxIssAuthent = res.result.trxIssAuthent;

      } else {
        this.data_TransactionConfig = []
        this.trxType = "";
        this.trxNumber = 0;
        this.trxPin = 0
        this.trxAmt = 0
        this.trxSuccConnect = 0
        this.trxIssApproved = 0
        this.trxIssAuthent = 0
      }
    })



    this.globalService.geTagDefinitionByCodeProfile(body).subscribe(res => {
      if (res.respCode == "000") {
        if (Array.isArray(res.result)) {
          this.data_TagDefinition = res.result;
        } else {
          // S'il n'est pas un tableau, mettez-le dans un tableau
          this.data_TagDefinition = [res.result];
        }

        // console.log('data_TagDefinition:',this.data_TagDefinition);

      } else {
        this.data_TagDefinition = []

      }
    })









  }


  // ----------------------------> delete tag defin  <-------------------------------------------//

  deleteTagDefinition() {
    // Obtenez les tags sélectionnés
    this.selectedTags = this.data_TagDefinition.filter(tag => tag.selected).map(tag => ({
      tagId: tag.tagId,
      codeProfile: tag.codeProfile,
      tagName: tag.tagName
    }));

    console.log("selectedTags ===>", this.selectedTags);

    this.selectedTagsUpdate = [...this.selectedTagsUpdate, ...this.selectedTags];

    console.log("selectedTagsUpdate ===>", this.selectedTagsUpdate);
    // // Vérifiez s'il y a des tags sélectionnés
    if (this.selectedTags.length === 0) {
      // console.log('Aucun tag sélectionné pour la suppression');
      return;
    }


    this.data_TagDefinition = this.data_TagDefinition.filter(tag => !tag.selected);

    // // Appelez le service pour supprimer les tags sélectionnés
    // this.globalService.deleteTagDefinition(selectedTags).subscribe(res => {
    //     if (res.respCode === "000") {
    //       this.data_TagDefinition = this.data_TagDefinition.filter(tag => !tag.selected);
    //     } else {
    //         // console.log('Une erreur s\'est produite lors de la suppression des définitions de tag');
    //     }
    // });



  }

  addTag() {
    // console.log("this.data_TagDefinition V1",this.data_TagDefinition);

    if (this.selectedTag) {
      // Créez un nouvel objet tag avec les valeurs sélectionnées et ajoutez-le à data_TagDefinition
      const newTag = {
        tagId: this.selectedTag,
        codeProfile: this.codeProfil, // Vous pouvez ajuster cette valeur selon vos besoins
        tagName: this.tags.find(tag => tag.tag === this.selectedTag)?.name || '' // Trouvez le nom du tag correspondant à l'ID sélectionné
      };

      const tagExists = this.data_TagDefinition.some(tag => tag.tagId === newTag.tagId && tag.codeProfile === newTag.codeProfile);


      if (!tagExists) {
        this.data_TagDefinition.push(newTag);
        this.selectedTag = '';

      } else {
        // console.log('Le tag existe déjà dans data_TagDefinition');
      }

      // console.log('Nouveau tag ajouté :', newTag);

      // console.log("this.data_TagDefinition V2",this.data_TagDefinition);
    } else {
      // console.log('Aucun tag sélectionné pour l\'ajout');
    }
  }






  // ----------------------------> save update  <-------------------------------------------//



  SaveUpdate() {
    const body = {
      "codeProfile": this.codeProfil,
      "wording": this.wording,
      "description": this.description,
      "dateCreate": this.dateCreate,
      "activeProfile": "N"
    }

    const bodyIssuerConfig = {
      "codeProfile": this.codeProfil,
      "cryptogram": this.cryptogram,
      "keyType": this.keyType,
      "keySet": this.keySet,
      "paymentKey": this.paymentKey,
      "macKey": this.macKey,
      "encryptionKey": this.encryptionKey,
      "cvKp": this.cvKp,
      "cvMac": this.cvMac,
      "cvKe": this.cvKe

    }

    const bodyTermminalCong = {

      "codeProfile": this.codeProfil,
      "terminalConfig": this.terminalConfig,
      "terminalControlledBy": this.terminalControlledBy,
      "terminalCountry": this.terminalCountry,
      "terminalCurrency": this.terminalCurrency,
      "terminalBaseEmv": this.terminalBaseEmv,
      "terminalTacd": this.hexValue,
      "terminalTacr": this.hexValueDenial,
      "terminalTaco": this.hexValueOnline,
      "terminalMin": this.terminalMin,
      "terminalMax": this.terminalMax,
      "terminalValue": this.terminalValue,
      "terminalFloor": this.terminalFloor,

    }

    const bodyTransactionConfig = {
      "codeProfile": this.codeProfil,
      "trxType": this.trxType,
      "trxNumber": this.trxNumber,
      "trxPin": this.trxPin,
      "trxAmt": this.trxAmt,
      "trxSuccConnect": this.trxSuccConnect,
      "trxIssApproved": this.trxIssApproved,
      "trxIssAuthent": this.trxIssAuthent
    }



    this.globalService.saveProfile(body).subscribe(res => {
      if (res.respCode == "000") {
        // console.log(" profile  saved successful");

      }

    })

    // console.log("this.selectedTags   ==> ",this.selectedTags);

    this.globalService.deleteTagDefinition(this.selectedTagsUpdate).subscribe(res => {
      if (res.respCode === "000") {
        this.data_TagDefinition = this.data_TagDefinition.filter(tag => !tag.selected);
      } else {
        // console.log('Une erreur s\'est produite lors de la suppression des définitions de tag');
      }
    });


    this.globalService.saveTagDefinition(this.data_TagDefinition).subscribe(res => {
      if (res.respCode == "000") {
        // console.log("data_TagDefinition saved successful");

      }

    })


    this.globalService.saveIssuerConfig(bodyIssuerConfig).subscribe(res => {
      if (res.respCode == "000") {
        // console.log(" IssuerConfig saved successful");

      }

    })

    this.globalService.saveTermminalCong(bodyTermminalCong).subscribe(res => {
      if (res.respCode == "000") {
        // console.log(" TermminalCong saved successful");

      }

    })



    this.globalService.saveTransactionConfig(bodyTransactionConfig).subscribe(res => {
      if (res.respCode == "000") {
        // console.log(" TransactionConfig saved successful");
        this.getAllProfil();
        if (this.isUpdating) {
          this.alertBody.status = "000"
          if (this.en) {
            this.alertBody.message = "Configuration profile update successfully"
          }
          if (this.fr) {
            this.alertBody.message = "Mise à jour réussie du profil de configuration"
          }
          if (this.esp) {
            this.alertBody.message = "Actualización del perfil de configuración exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        } else {
          this.alertBody.status = "000"
          if (this.en) {
            this.alertBody.message = "Configuration profile saved successfully"
          }
          if (this.fr) {
            this.alertBody.message = "Profil de configuration enregistré avec succès"
          }
          if (this.esp) {
            this.alertBody.message = "Perfil de configuración guardado exitosamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
        this.init();



      } else {
        this.alertBody.status = "Something went wrong, please check that the entred data is valid"
        if (this.en) {
          this.alertBody.message = "SomeThing went wrong (Invalid cridentials or connection issue)"
        }
        if (this.fr) {
          this.alertBody.message = "Une erreur s'est produite (identifiants invalides ou problème de connexion)"
        }
        if (this.esp) {
          this.alertBody.message = "Algo salió mal (credenciales no válidas o problema de conexión)"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
    })


  }




  // ----------------------------> end save <-------------------------------------------//

  // ----------------------------> end delete profile <-------------------------------------------//
  deleteProfileFunction() {
    const body = {
      "code_profil": this.codeProfil,
    }
    // console.log(body);
    this.globalService.deleteTransactionConfig(body).subscribe(res => {
      if (res.respCode == "000") {
        // console.log(" TransactionConfig delete successful");

      }
    })

    this.globalService.deleteTermminalCong(body).subscribe(res => {
      if (res.respCode == "000") {
        // console.log(" TermminalCong delete successful");

      }

    })
    this.globalService.deleteIssuerConfig(body).subscribe(res => {
      if (res.respCode == "000") {
        // console.log(" IssuerConfig delete successful");

      }

    })


    this.globalService.deleteProfile(body).subscribe(res => {
      if (res.respCode == "000") {
        // console.log(" Profile delete successful");


        this.alertBody.status = res.respCode
        this.alertBody.message = `profile delete successfuly `;
        this.alertBody.open = true
        this.getAllProfil()
        this.init();
        setTimeout(() => {
          this.alertBody.open = false;
          // window.location.reload();
        }, 3000);
      } else {
        this.alertBody.status = res.respCode
        this.alertBody.message = res.respMsg;
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }


    })



  }
  // ----------------------------> end delete profile <-------------------------------------------//
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

  //////  Cryptogram 



  CryptogramKeyCvKp() {
    this.cvKp = "111111111111"
    // // console.log(this.cvKp);

  }
  CryptogramKeyCvMac() {
    this.cvMac = "111111111111"

  }

  CryptogramKeyCvKe() {
    this.cvKe = "111111111111"

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

  // ----------------------------> tag TVR TSI CVR <-------------------------------------------//


  ///TVR
  onTagSelectedTVR(event: any) {
    this.resultsTVR = [];
    // console.log(this.TagResults);
    // console.log(event.target.value);
    const data = this.TagResults.find(item => item.tag === 'TVR');
    this.resultsTVR = data.results;
    // console.log(this.results)
    this.hexValueTVR = "";
    this.bytesTVR = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
    const selectedTag = this.specialTags.find(tag => tag.shortName === 'TVR');
    if (selectedTag) {
      var length = +selectedTag.length;
      const lengthAsNumber = Math.min(length, 5); // Convert length to number

      this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
      this.selectedTab = 1;

      this.hexValueTVR = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
      this.bytesTVR = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
    }
  }

  updateHexValueTVR() {
    this.hexValueTVR = this.bytesTVR.map(item => item.byte).join('');
  }

  analyzeTVR() {
    const decimalValue = parseInt(this.hexValueTVR, 16);

    const binaryValue = decimalValue.toString(2);

    const paddedBinaryValue = binaryValue.padStart(this.resultsTVR.length, '0');

    // Map through results and update checked based on binary value
    this.resultsTVR.forEach((result, index) => {
      result.checked = paddedBinaryValue[index] === '1';
    });
  }



  /// TSI 
  onTagSelectedTSI(event: any) {
    this.resultsTSI = [];
    // console.log(this.TagResults);
    // console.log(event.target.value);
    const data = this.TagResults.find(item => item.tag === 'TSI');
    this.resultsTSI = data.results;
    // console.log(this.results)
    this.hexValueTSI = "";
    this.bytesTSI = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
    const selectedTag = this.specialTags.find(tag => tag.shortName === 'TSI');
    if (selectedTag) {
      var length = +selectedTag.length;
      const lengthAsNumber = Math.min(length, 5); // Convert length to number

      this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
      this.selectedTab = 1;

      this.hexValueTSI = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
      this.bytesTSI = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
    }
  }

  updateHexValueTSI() {
    this.hexValueTSI = this.bytesTSI.map(item => item.byte).join('');
  }

  analyzeTSI() {
    const decimalValue = parseInt(this.hexValueTSI, 16);

    const binaryValue = decimalValue.toString(2);

    const paddedBinaryValue = binaryValue.padStart(this.resultsTSI.length, '0');

    // Map through results and update checked based on binary value
    this.resultsTSI.forEach((result, index) => {
      result.checked = paddedBinaryValue[index] === '1';
    });
  }





  updateHexValueCVR() {
    this.hexValueCVR = this.bytesCVR.map(item => item.byte).join('');
  }

  analyzeCVR() {
    const decimalValue = parseInt(this.hexValueCVR, 16);

    const binaryValue = decimalValue.toString(2);

    const paddedBinaryValue = binaryValue.padStart(this.resultsCVR.length, '0');

    // Map through results and update checked based on binary value
    this.resultsCVR.forEach((result, index) => {
      result.checked = paddedBinaryValue[index] === '1';
    });
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


      const body = {
        key: event.target.value,
        data: paddedData
        // data: "00000000000000000000000000000000"
      }
      // // console.log('body: ', body);

      this.globalService.encryptKey(body).subscribe((response) => {
        // // console.log('response',response.result);

        if (data == "payment_Key") {
          this.cvKp = response.result
        }
        else if (data == "mac_Key") {
          this.cvMac = response.result
        }
        else {
          this.cvKe = response.result
        }

      });

    } else {
      if (data == "payment_Key") {
        this.cvKp = ""
      }
      else if (data == "mac_Key") {
        this.cvMac = ""
      }
      else {
        this.cvKe = ""
      }
    }
  }



  ////  fin encrypt KEY ********************************





}

