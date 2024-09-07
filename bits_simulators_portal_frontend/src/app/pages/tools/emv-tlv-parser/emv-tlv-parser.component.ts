import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { emvTagResults } from 'src/app/data/TagResults';
import { EmvTag } from 'src/app/models/EmvTag';
import { GlobalResponse } from 'src/app/models/GlobalResponse';
import { GlobalService } from 'src/app/services/global.service';
import { EmvTagServiceService } from 'src/app/services/icc/emv-tag-service.service';


@Component({
  selector: 'app-emv-tlv-parser',
  templateUrl: './emv-tlv-parser.component.html',
  styleUrls: ['./emv-tlv-parser.component.scss']
})

  export class EmvTlvParserComponent implements OnInit {
    @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
    @ViewChild('analyzeModal', { read: ElementRef }) analyzeModal?: ElementRef
  
    tags: EmvTag[] = [];
    specialTags: EmvTag[] = [];
    p: number = 1;
    searchText: string = "";
    selectedTag?: EmvTag;
  
    itemsPerPageSelct:number=5

    user1:any

    fr = false;
    en = false;
    esp = false;
    language:  string = ""
    protocol: string = "";
    bankCode: any = "";
    isDelete: boolean = false
  
    constructor(private globalService: GlobalService, private emvTagService: EmvTagServiceService) {
    
    }
  
    openModal(tag: EmvTag) {
      this.selectedTag = tag;
      this.modal?.nativeElement.showModal();
    }
    closeModal() {
      this.modal?.nativeElement.close();
    }
  
    openAnalyzeModal() {
      this.analyzeModal?.nativeElement.showModal();
    }
    closeAnalyzeModal() {
      this.analyzeModal?.nativeElement.close();
    }
  
    TagResults: any[] = emvTagResults;
    loading: boolean = false;
    ngOnInit(): void {
      this.user1 = localStorage.getItem('user');
      this.user1 = JSON.parse(this.user1);
      this.language = this.user1.languageCode;
      if (this.language === "en") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators / EMV TAG Decoders"));
        this.en=true
      } else if (this.language === "fr") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Calculatrices / Décodeur EMV TAG"));
          this.fr=true
      } else if (this.language === "es") {
          Promise.resolve().then(() => this.globalService.titleSubject.next("Calculators /  EMV TAG Decoders"));
          this.esp=true
      } else {
          Promise.resolve().then(() => this.globalService.titleSubject.next(""));
      }
      this.loading = true;
      this.emvTagService.getTags().subscribe({
        next: (data: GlobalResponse<EmvTag[]>) => {
          // console.log(data);
          if (data.respCode === '000') {
            this.tags = data.result || [];
            const tagValuesToInclude = ['95', '9B', '82', '9F07', '9F33', '9F26', '9F40'];
            this.specialTags = this.tags.filter(tag => tagValuesToInclude.includes(tag.tag));
            // console.log(this.specialTags);
            setTimeout(() => {
              this.loading = false;
            }, 3000)
  
          } else {
            alert("error fetching emv tags");
          }
        }
      })
    }
    selectedTab: number = 0;
    tabButtons: any;
  
    open_tab(index: number) {
      this.selectedTab = index;
    }
  
    hexValue: string = '';
    bytes: any[] = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
  
    results: any[] = [];
    onTagSelected(event: any) {
      this.results = [];
      // console.log(this.TagResults);
      // console.log(event.target.value);
      const data = this.TagResults.find(item => item.tag === event.target.value);
      this.results = data.results;
      // console.log(this.results)
      this.hexValue = "";
      this.bytes = [{ byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }, { byte: '' }];
      const selectedTag = this.specialTags.find(tag => tag.shortName === event.target.value);
      if (selectedTag) {
        var length = +selectedTag.length;
        const lengthAsNumber = Math.min(length, 5); // Convert length to number
  
        this.tabButtons = Array.from({ length: lengthAsNumber }, (_, i) => i + 1);
        this.selectedTab = 1;
  
        this.hexValue = '0'.repeat(lengthAsNumber * 2); // Each byte represented by 2 characters
        this.bytes = Array.from({ length: lengthAsNumber }, () => ({ byte: '00' }));
      }
    }
    updateHexValue() {
      this.hexValue = this.bytes.map(item => item.byte).join('');
    }
  
    analyze() {
     const decimalValue = parseInt(this.hexValue, 16);
    
      const binaryValue = decimalValue.toString(2);
    
      const paddedBinaryValue = binaryValue.padStart(this.results.length, '0');
    
      // Map through results and update checked based on binary value
      this.results.forEach((result, index) => {
        result.checked = paddedBinaryValue[index] === '1';
      });
    }
  }