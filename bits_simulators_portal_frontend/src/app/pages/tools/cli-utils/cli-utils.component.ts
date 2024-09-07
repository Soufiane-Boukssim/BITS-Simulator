import { HttpClient } from '@angular/common/http';
import {Component, ElementRef, ViewChild} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { GlobalService } from 'src/app/services/global.service';
import { WsService } from 'src/app/services/ws.service';

@Component({
  selector: 'app-cli-utils',
  templateUrl: './cli-utils.component.html',
  styleUrls: ['./cli-utils.component.scss']
})
export class CliUtilsComponent {
  @ViewChild('fileInput') fileInput!: ElementRef<HTMLInputElement>;

  
  fileName: string = '';
  p: number = 0;
  pV2: number = 0;


  data_parser:any = [];

  ipmRecordDetails:any=[];
 

  details_parcer:any = [];

  user1:any

  fr = false;
  en = false;
  esp = false;
  language:  string = ""
  protocol: string = "";
  bankCode: any = "";


  itemsPerPageSelct:number=5

  showError: boolean = false;
  message = "";

  alertBody: any = {
    message: '',
    status: '000',
    open: false
  }


  isDelete: boolean = false
  constructor(
    private globalService: GlobalService,
    private auth: AuthService,
    private fb: FormBuilder,
    private http: HttpClient,
    private wsService: WsService,
    private router: Router,
    private formBuilder: FormBuilder,

  ) { }




  tab_1: boolean = false
  tab_2: boolean = true
  tab_3: boolean = false
  tab_4: boolean = false
  tab_5: boolean = false
  tab_6: boolean = false
  tab_7: boolean = false
  tab_8: boolean = false
  tab_9: boolean = false
  tab_10: boolean = false
  user: any
  
  ngOnInit() {

    this.clearAll()

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("File Parser / IPM Parser"));
      this.en=true
    } else if (this.language === "fr") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("Analyseur de fichiers / Analyseur IPM"));
        this.fr=true
    } else if (this.language === "es") {
        Promise.resolve().then(() => this.globalService.titleSubject.next("File Parser / IPM Parser"));
        this.esp=true
    } else {
        Promise.resolve().then(() => this.globalService.titleSubject.next(""));
    }



  }

  open_tab(index: number) {
    this.clearAll()
    this.cancelUpload()
    this.data_parser=[]
    this.details_parcer=[]
    this.p=0
    this.pV2=0
    this.isDelete = false
    this.tab_1 = false
    this.tab_2 = false
    this.tab_3 = false
    this.tab_4 = false
    this.tab_5 = false
    this.tab_6 = false
    this.tab_7 = false
    this.tab_8 = false
    this.tab_9 = false
    this.tab_10 = false
    switch (index) {
      case 0:
        Promise.resolve().then(() => this.globalService.titleSubject.next("File Parser / LIS Parser"));
        this.tab_1 = true
        this.p = 0
        break;
      case 1:
        Promise.resolve().then(() => this.globalService.titleSubject.next("File Parser / IPM Parser"));
        this.tab_2 = true
        this.p = 0
        break;
      case 2:
        Promise.resolve().then(() => this.globalService.titleSubject.next("File Parser / VISA Parser"));
        this.tab_3 = true
        this.p = 0
        break;
      case 3:
        this.tab_4 = true
        this.p = 0
        break;
      case 4:
        this.tab_5 = true
        this.p = 0
        break;
      case 5:
        this.tab_6 = true
        this.p = 0
        break;
      case 6:
        this.tab_7 = true
        this.p = 0
        break;
      case 7:
        this.tab_8 = true
        this.p = 0
        break;
      case 8:
        this.tab_9 = true
        this.p = 0
        break;
      case 9:
        this.tab_10 = true
        this.p = 0
        break;

      default:
        break;
    }
  }

  clearAll(): void {
    this.isDelete = false;


  }

  //file

  onFilesSelected() {
    const file = this.fileInput.nativeElement.files?.item(0);
    if (file) {
      this.fileName = file.name;
      // console.log(file);
    }
  }



uploadFiles() {
  const file: File | null = this.fileInput.nativeElement.files?.item(0) ?? null;
  if (!file) {
    console.error('No file selected');
    return;
  }

  const formData = new FormData();
  formData.append("file", file);

  this.globalService.formDataIPM(formData).subscribe(response => {
    if (response.respCode === "000") {
      
      this.data_parser = response.result;
    //  // console.log("data_parser",this.data_parser);

    this.alertBody.status = "000";
    if (this.en) {
        this.alertBody.message = "Data parsed successfully";
    }
    if (this.fr) {
        this.alertBody.message = "Data parsed successfully";
    }
    if (this.esp) {
        this.alertBody.message = "Data parsed successfully";
    }
    this.alertBody.open = true;
    setTimeout(() => {
        this.alertBody.open = false;
    }, 3000);
      
    } else {

      this.alertBody.status = "No records found";
      if (this.en) {
          this.alertBody.message = "checking data file";
      }
      if (this.fr) {
          this.alertBody.message = "verifying data file";
      }
      if (this.esp) {
          this.alertBody.message = "verifying data file";
      }
      this.alertBody.open = true;
      setTimeout(() => {
          this.alertBody.open = false;
      }, 3000);
      console.error('No response data received');
    }
  });

    
}






uploadFilesLis() {
  const file: File | null = this.fileInput.nativeElement.files?.item(0) ?? null;
  if (!file) {
    console.error('No file selected');
    return;
  }

  const formData = new FormData();
  formData.append("file", file);

  this.globalService.formDataLis(formData).subscribe(response => {
    if (response.respCode === "000") {
      
      this.data_parser = response.result;
    //  // console.log("data_parser",this.data_parser);

    this.alertBody.status = "000";
    if (this.en) {
        this.alertBody.message = "Data parsed successfully";
    }
    if (this.fr) {
        this.alertBody.message = "Data parsed successfully";
    }
    if (this.esp) {
        this.alertBody.message = "Data parsed successfully";
    }
    this.alertBody.open = true;
    setTimeout(() => {
        this.alertBody.open = false;
    }, 3000);
      
    } else {

      this.alertBody.status = "No records found";
      if (this.en) {
          this.alertBody.message = "checking data file";
      }
      if (this.fr) {
          this.alertBody.message = "verifying data file";
      }
      if (this.esp) {
          this.alertBody.message = "verifying data file";
      }
      this.alertBody.open = true;
      setTimeout(() => {
          this.alertBody.open = false;
      }, 3000);
      console.error('No response data received');
    }
  });

    
}






/////////////////////////////////






showDetails(data:any){
  // console.log("data: " ,data);
  this.details_parcer=[]
  const body={
    "data":data
  }

  this.globalService.showDetailsIPM(body).subscribe(res=>{
    if (res.respCode==="000"){
      // console.log("result ===>",res.result);

      this.details_parcer=res.result;
      
    }
  })
}


hextoAscii(data: string) {
  let asciiString = '';
  for (let i = 0; i < data.length; i += 2) {
    const hexByte = data.substr(i, 2);
    const asciiChar = String.fromCharCode(parseInt(hexByte, 16));
    asciiString += asciiChar;
  }
  asciiString = asciiString.replace(/[^a-zA-Z0-9]/g, '');
  
  // console.log("asciiString"+asciiString);
  
  this.addRecords(asciiString);


  // console.log("this.ipmRecordDetails",this.ipmRecordDetails);
  
}


show_detailsLis(data:any){
  // console.log("data Lis==>",data);
  this.details_parcer=[]
  const body={
    "data":data
  }

  this.globalService.showDetailsLis(body).subscribe(res=>{
    if (res.respCode==="000"){
      // console.log("result ===>",res.result);
      this.details_parcer=res.result
      
    }
  })
  
  
}







addRecords(asciiString: string): void {
  // Initialisation de ipmRecordDetails
  this.ipmRecordDetails = [];

  // Définition des positions et longueurs de chaque champ
  const fieldDefinitions = [
    { id: "000", Description: "MTI", Length: 4 },
    { id: "024", Description: "Function code", Length: 3 },
    { id: "048", Description: "Function code", Length: 40 },
    { id: "048.0105", Description: "PDS 0105", Length: 25 },
    { id: "048.0122", Description: "PDS 0122", Length: 1 },
    { id: "071", Description: "Message number", Length: 8 }
  ];

  // Parcours des définitions de champs pour extraire les valeurs
  let startIndex = 0;
  for (const fieldDef of fieldDefinitions) {
    const valueLength = fieldDef.Length;
    const value = asciiString.substr(startIndex, valueLength);
    this.ipmRecordDetails.push({
      id: fieldDef.id,
      Description: fieldDef.Description,
      Length: valueLength.toString(),
      Value: value
    });
    startIndex += valueLength;
  }
}

  

  
  cancelUpload() {
    // Reset the file input and clear the file name
    this.fileInput.nativeElement.value = '';
    this.fileName = '';
    // console.log('Cancel');
  }

  onDragOver(event: DragEvent) {
    event.preventDefault();
    // Optionally, apply some styles to the drop zone
  }

  onDrop(event: DragEvent) {
    event.preventDefault();
    if (event.dataTransfer && event.dataTransfer.files && event.dataTransfer.files.length > 0) {
      this.fileInput.nativeElement.files = event.dataTransfer.files;
      this.onFilesSelected();
      // console.log('File dropped', this.fileInput.nativeElement.files?.item(0));
    }
  }






  ///////







}
