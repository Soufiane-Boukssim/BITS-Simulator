import { DatePipe } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import * as e from 'express';
import { findIndex } from 'rxjs';
import { GlobalService } from 'src/app/services/global.service';

export interface UserManagement {
  userCode: string,
  userName2: string,
  userType: string,
  address: string,
  mobileNumber: string,
  email: string,
  userBankCode: string,
  numberOfTriesAllowed: string,
  password: string,
  numberOfTries: string,
  status: string,
  languageCode: string,
  ipAddress: string,
  blockAccess: string,
  dateStartPass: string | null,
  dateEndPass: string | null
}
@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent {

  alertBody: any = {
    message: '',
    status: '',
    open: false
  }
  fr = false;
  en = false;
  esp = false;

  p: number = 0;
  bankList: any = [];

  tab_1: boolean = true
  tab_2: boolean = false
  tab_3: boolean = false
  tab_4: boolean = false

  itemsPerPageSelct: number = 5

  userActivityList: any = [];

  userCode: any = "";
  list: any[] = [];
  userHabilitation: any[] = [];
  userHabilitationN: any[] = [];
  Protocols: any[] = [];
  protoNotHaveSelected: any[] = [];
  selectedAndAddedProto: any[] = [];

  protoHaveSelected: any[] = [];
  selectedAndRemovedProto: any[] = [];

  selectedMenuNameHave: any[] = [];

  selectedMenuNameDHave: any[] = [];

  notHaveProtoForBlueIso: any[] = [];

  userExiste: boolean = true;
  isoProtoSelect = false;

  originalValue = '';
  userBlocked = false;

  IsoSelected = false;
  addSellected = false;
  isISOEnabled = false;

  selectMenuBlue = false;

  blueMSelected = false;
  RedMSelected = false;

  message = "null";

  allowEdit = false;
  hideFirstEdit = true;
  saveEdit = false;

  goodIpSyntax = true;
  goodPhoneSyntax = true;
  goodEmailSyntax = true;

  user: UserManagement = {
    userCode: '',
    userName2: '',
    userType: '',
    address: '',
    mobileNumber: '',
    email: '',
    userBankCode: '',
    numberOfTriesAllowed: '',
    password: '',
    numberOfTries: '',
    status: '',
    languageCode: '',
    ipAddress: '',
    blockAccess: '',
    dateStartPass: '',
    dateEndPass: ''

  }


  user1: any;
  language = "";

  @ViewChild('modal', { read: ElementRef }) modal?: ElementRef
  constructor(
    private globalService: GlobalService,
    private route: ActivatedRoute,
    private router: Router,
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if (this.language == "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Administration/user details"));
      this.en = true;
    }
    else if (this.language == "fr") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Administration/détails de l'utilisateur"));
      this.fr = true;
    }
    else {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Administración/Detalles de usuario"));
      this.esp = true;
    }

    //Promise.resolve().then(() => this.globalService.titleSubject.next("Administration/user details"));
    this.route.paramMap.subscribe((params) => {
      // // // console.log('params: ', params.get('id'));
      this.userCode = params.get('id');
    })

    this.list = [];
    this.userHabilitation = [];
    this.userHabilitationN = [];
    // // console.log("this.userCode ", this.userCode)
    this.getBankList();
    this.getUserHabilitation(this.userCode.toString());
    this.getNotUserHabilitation(this.userCode.toString());
    this.getUserInformation(this.userCode.toString());
    this.getUserActivities(this.userCode.toString());
  }


  getBankList() {
    this.globalService.fetchBank().subscribe(response => {
      // // console.log('response  BANK: ', response);
      if (response.result.length > 0) {
        this.bankList = response.result
        // // console.log('this.bankList: ', this.bankList);
      }
    })
  }

  open_tab(tab: number) {
    this.tab_1 = false;
    this.tab_2 = false;
    this.tab_3 = false;
    switch (tab) {
      case 1:
        this.tab_2 = true
        break;

      case 2:
        this.tab_3 = true

        break;

      default:
        this.tab_1 = true
        break;
    }
  }

  getUserHabilitation(userId: string) {
    this.globalService.getUserHabilitations(userId).subscribe(res => {
      // // console.log('RES ================> ', res);
      this.protoNotHaveSelected = [];
      this.protoHaveSelected = [];
      //avoid dupplicating records (groups) //
      const listOfMenus = res['result']['listOfMenus'];
      // if (listOfMenus.length > 0) {
      if (Array.isArray(listOfMenus)) {
        this.userHabilitation = this.processHabilitation(listOfMenus);
      }
      else {
        this.userHabilitation = this.processHabilitation([]);
      }
      //------------------------------------//
      var c = 0;
      for (let i = 0; i < this.userHabilitation.length; i++) {
        if (this.userHabilitation[i]["groupCode"] == "ISO") {
          c++;
          // // console.log('c: ', c);
          if (c > 1) {
            // // console.log('c>1: ', c > 1);

            this.userHabilitation.splice(i, c - 1);
          }
        }
      }
      // // console.log('this.userHabilitation: ', this.userHabilitation);
    })
  }


  getNotUserHabilitation(userId: string) {
    this.globalService.getNotUserHabilitations(userId).subscribe(res => {
      // // console.log('RES Not ================> ', res);
      //avoid dupplicating records (groups) //
      const listOfMenus = res['result']['listOfMenus'];
      this.userHabilitationN = this.processHabilitation(listOfMenus);
      //------------------------------------//
      var c = 0;
      for (let i = 0; i < this.userHabilitationN.length; i++) {
        if (this.userHabilitationN[i]["groupCode"] == "ISO") {
          c++;
          // // console.log('c: ', c);
          if (c > 1) {
            // // console.log('c>1: ', c > 1);

            this.userHabilitationN.splice(i, c - 1);
          }
        }

      }
      // // console.log('this.userHabilitationN: ', this.userHabilitationN);
    })
  }

  private processHabilitation(listOfMenus: any[]): any[] {
    const uniqueGroupCodes = new Set();
    const result: any[] = [];

    if (listOfMenus.length > 0) {
      listOfMenus.forEach(menu => {
        const groupCode = menu.groupCode;

        if (!uniqueGroupCodes.has(groupCode)) {
          // If groupCode is not in the set, add the record to the result
          uniqueGroupCodes.add(groupCode);
          result.push(menu);
        }
      });
    }
    return result;
  }


  selectMenuNameHave(menuName: string) {
    this.RedMSelected = false;
    this.blueMSelected = true;
    const index = this.selectedMenuNameHave.indexOf(menuName);

    if (index !== -1) {
      // If menuName is already in the selected list, remove it (deselect)
      this.selectedMenuNameHave.splice(index, 1);
    } else {
      // If menuName is not in the selected list, add it (select)
      this.selectedMenuNameHave.push(menuName);
    }

    // // console.log('SELECTED =====>', this.selectedMenuNameHave);

    // Check for ISO groupCode
    this.protoHaveSelected = [];
    this.protoNotHaveSelected = [];

    for (let i = 0; i < this.selectedMenuNameHave.length; i++) {

      // // console.log('this.resFound: ', this.resFound);
      // // console.log('this.selectedMenuNameDHave[i]: ', this.selectedMenuNameHave[i]);
      if (this.selectedMenuNameHave[i]["groupCode"] == "ISO") {
        this.IsoSelected = true;
        // // console.log("$$$$$", this.selectedMenuNameHave[i]["listProtocols"])
        const arr = this.selectedMenuNameHave[i]["listProtocols"].map((e: any) => {
          return e;
        });
        const arr2 = this.selectedMenuNameHave[i]["listProtocolsNHave"].map((e: any) => {
          return e;
        });
        this.protoHaveSelected.push(arr);
        this.protoNotHaveSelected.push(arr2);
        // // console.log('this.protoHaveSelected: ', this.protoHaveSelected);
        // // console.log('this.protoNotHaveSelected: ', this.protoNotHaveSelected);
        this.isoProtoSelect = true;
        // // console.log("ISO FOUND!!!");
      }
      else {
        this.isoProtoSelect = false;
        // // console.log("ISO NOT FOUND");
      }
    }
  }


  resFound: any[] = [];
  foundMenuName: any = null;
  foundSelected: any = null;


  selectMenuNameNotHave(menuName: any) {
    this.addSellected = false;
    this.RedMSelected = true;
    this.blueMSelected = false;

    // // console.log('this.userHabilitation: ', this.userHabilitation);
    // // console.log('menuName Red: ', menuName);

    const index = this.selectedMenuNameDHave.indexOf(menuName);

    if (index !== -1) {
      // If menuName is already in the selected list, remove it (deselect)
      this.selectedMenuNameDHave.splice(index, 1);
    } else {
      // If menuName is not in the selected list, add it (select)
      this.selectedMenuNameDHave.push(menuName);
    }

    // // console.log('SELECTED =====>', this.selectedMenuNameDHave);
    // // console.log('this.dontHave ==>: ', this.userHabilitationN);
    // // console.log('this.selectedMenuNameHave.length: ', this.selectedMenuNameDHave.length);

    // Check for ISO groupCode
    this.isISOEnabled = this.selectedMenuNameDHave.some(item => item.groupCode === 'ISO');

    if (this.isISOEnabled) {
      this.IsoSelected = false;
      const isoItem = this.selectedMenuNameDHave.find(item => item.groupCode === 'ISO');
      const arr = isoItem.listProtocolsNHave.map((e: any) => e);
      this.protoNotHaveSelected.push(arr);
      this.isoProtoSelect = true;
      // // console.log("ISO FOUND!!!");
    } else {
      this.isoProtoSelect = false;
      // // console.log("ISO NOT FOUND");
    }

    this.selectedMenuNameHave = [];
  }

 
  selectedProtoNotHave(protocol: any) {
    this.blueMSelected = false;
    this.RedMSelected = true;

    const isInHave = this.selectedMenuNameHave.some(menu => menu.listProtocols.includes(protocol));
    const isInNotHave = this.selectedMenuNameDHave.some(menu => menu.listProtocols.includes(protocol));

    if (!isInHave && !isInNotHave) {
      this.selectedMenuNameHave.forEach(menu => {
        const index = menu.listProtocolsNHave.findIndex((p: any) => p === protocol);
        if (index !== -1) {
          menu.listProtocolsNHave.splice(index, 1);
          menu.listProtocols.push(protocol);
        }
      });

      this.selectedMenuNameDHave.forEach(menu => {
        const index = menu.listProtocols.findIndex((p: any) => p === protocol);
        if (index === -1) {
          menu.listProtocols.push(protocol);
        }
      });

      this.selectedAndAddedProto.push(protocol);
    } else {
      this.selectedMenuNameHave.forEach(menu => {
        const index = menu.listProtocols.findIndex((p: any) => p === protocol);
        if (index !== -1) {
          menu.listProtocols.splice(index, 1);
          menu.listProtocolsNHave.push(protocol);
        }
      });

      this.selectedMenuNameDHave.forEach(menu => {
        const index = menu.listProtocols.findIndex((p: any) => p === protocol);
        if (index !== -1) {
          menu.listProtocols.splice(index, 1);
        }
      });

      this.selectedAndAddedProto = this.selectedAndAddedProto.filter(p => p !== protocol);
    }

    // // console.log("USER HABILITATION :: ", this.userHabilitation);
    // // console.log(' this.selectedAndAddedProto: ', this.selectedAndAddedProto);
    // // console.log('SELECTED PROTO this.selectedMenuNameDHave.length: ', this.selectedMenuNameDHave.length);
    // // console.log('this.protoNotHaveSelected[0]: ', this.protoNotHaveSelected[0]);

    // // console.log('this.addSellected: ', this.addSellected);
    // // console.log('this.IsoSelected: ', this.IsoSelected);
    const foundpro = this.notHaveProtoForBlueIso.find((obj: any) => obj == protocol);
    if (this.IsoSelected && !this.addSellected && !foundpro) {
      this.notHaveProtoForBlueIso.push(protocol);
      // // console.log("PUSH");
    }

    if (foundpro) {
      this.notHaveProtoForBlueIso = [];
      this.notHaveProtoForBlueIso.push(protocol);
    }
    // // console.log("Clean");

    // // console.log('this.notHaveProtoForBlueIso: ', this.notHaveProtoForBlueIso);
    // // console.log("selectedMenuNameDHave :: after> ", this.selectedMenuNameDHave);
  }




  selectedProtoHave(protocol: any) {
    // // console.log('this.selectedMenuNameHave: ', this.selectedMenuNameHave);
    this.blueMSelected = true;
    this.RedMSelected = false;

    this.selectedMenuNameHave.forEach(menu => {
      const indexInProtocols = menu.listProtocols.findIndex((p: any) => p === protocol);
      const indexInProtocolsNotHave = menu.listProtocolsNHave.findIndex((p: any) => p === protocol);

      if (indexInProtocols !== -1) {
        menu.listProtocols.splice(indexInProtocols, 1);
        menu.listProtocolsNHave.push(protocol);
      } else if (indexInProtocolsNotHave !== -1) {
        menu.listProtocolsNHave.splice(indexInProtocolsNotHave, 1);
        menu.listProtocols.push(protocol);
      } else {
        menu.listProtocols.push(protocol);
      }
    });

    const indexInSelectedAndRemovedProto = this.selectedAndRemovedProto.findIndex((p: any) => p === protocol);
    if (indexInSelectedAndRemovedProto !== -1) {
      this.selectedAndRemovedProto.splice(indexInSelectedAndRemovedProto, 1);
    } else {
      this.selectedAndRemovedProto.push(protocol);
    }

    // // console.log('this.selectedAndRemovedProto: ', this.selectedAndRemovedProto);
    // // console.log('SELECTED PROTO this.selectedMenuNameHave: ', this.selectedMenuNameHave);
  }





  submit() {
    this.list = [];
    var flenght = this.userHabilitation.length;
    var count = 0;
    var protocols = [];
    for (let c = 0; c < this.userHabilitation.length; c++) {
      if (this.userHabilitation[c]['groupCode'] == "ISO") {
        count++;
        if (count > 2) {
          for (let i = 0; this.userHabilitation[c]['listProtocols'].length; i++) {
            flenght = this.userHabilitation.length + count - 1;
          }
        }
      }
      if (this.userHabilitation[c]['groupCode'] == "ISO") {
        const arr = this.userHabilitation[c]['listProtocols'].map((e: any) => {
          return e.idProtocole;
        })
        protocols.push(arr);
      }
    }
    // // console.log('flenght ===> ', flenght);
    for (let c = 0; c < flenght; c++) {
      this.list.push(this.userHabilitation[c]['groupCode'])

    }
    // // console.log('this.list: ', this.list);
    // // console.log('protocols: ', protocols);

    this.globalService.validatingHabilitaion(this.userCode, this.list, protocols[0]).subscribe((result) => {
      // // console.log('this.have>>>>>>>>> ', this.userHabilitation);
      // // console.log('result>>>>>: ', result);
      this.message = "user Habilitation was Succesdfully modified!!";
      // // console.log('Succesdfully modified!!');
      if (this.userHabilitationN.length > 0 || this.userHabilitation.length > 0 || result['respCode'] == '000') {
        this.alertBody.status = "000"
        if (this.en) {
          this.alertBody.message = "Habilitation added successfuly"
        }
        if (this.fr) {
          this.alertBody.message = "L’Habilitation a été modifié avec succès"
        }
        if (this.esp) {
          this.alertBody.message = "Habilitación actualizada con éxito"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
      else {
        this.alertBody.status = result.respCode
        this.alertBody.message = result.respMsg
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
        /*this.message = "something went wrong";
        this.openModal();*/
      }
    })
  }


  BlueMnSelected = false;
  isSelected(menuName: string): boolean {
    // // console.log("test ==>", this.selectedMenuNameDHave.includes(menuName));
    return this.selectedMenuNameDHave.includes(menuName);
  }



  isSelected2(menuName: string): boolean {
    return this.selectedMenuNameHave.includes(menuName);
  }

  protSelectRed = false;
  isSelectedProtoNot(protocol: any): boolean {
    // // console.log('this.selectedMenuNameDHave: selected RED', this.selectedMenuNameDHave);
    // // console.log('this.selectedMenuNameHave: selected RED', this.selectedMenuNameHave);
    this.selectedMenuNameDHave.map((o: any) => {
      if (o.listProtocols.includes(protocol)) {
        this.protSelectRed = true;
      } else {
        this.protSelectRed = false;
      }
    })
    if (this.selectedMenuNameDHave.length < 1) {
      this.selectedMenuNameHave.map((o: any) => {
        if (o.listProtocols.includes(protocol)) {
          this.protSelectRed = true;
        } else {
          this.protSelectRed = false;
        }
      })
    }

    // // console.log("this.protSelectRed==>", this.protSelectRed);

    return this.protSelectRed;
    /*for(let i =0 ;i<this.selectedMenuNameDHave.length;i++){
      return this.selectedMenuNameDHave[i]["listProtocols"].includes(protocol);
    }*/
    //return this.selectedMenuNameDHave[0]["listProtocols"].includes(protocol);
  }

 
  protSelectBlue = false;
  

  isSelectedProto(protocol: any): boolean {
    return this.selectedMenuNameHave.some(menu => menu.listProtocolsNHave.includes(protocol));
  }



  resetSelections() {
    this.protoNotHaveSelected = [];
    this.protoHaveSelected = [];
    this.selectedMenuNameDHave = [];
    this.selectedMenuNameHave = [];
    this.getUserHabilitation(this.userCode.toString());
    this.getNotUserHabilitation(this.userCode.toString());
    this.addSellected = false;
    //this.getHabilitations(this.originalValue);
  }



  displayMenuNameNotHave() {
    this.isISOEnabled = false;
    this.protoNotHaveSelected = [];
    this.selectedAndRemovedProto = [];
    this.addSellected = true;
    this.notHaveProtoForBlueIso = [];

    this.blueMSelected = false;
    this.RedMSelected = false;
    // // console.log('userHabilitation: ', this.userHabilitation);

    // // console.log('this.protoNotHaveSelected:  NOT HAVE SELECTED', this.protoNotHaveSelected);
    if (this.selectedMenuNameHave && Array.isArray(this.selectedMenuNameHave) && this.selectedMenuNameHave.length > 0) {
      for (let i = 0; i < this.selectedMenuNameHave.length; i++) {
        for (let c = 0; c < this.selectedMenuNameHave[i]['listProtocolsNHave'].length; c++) {
          this.selectedMenuNameHave[i]["listProtocols"] = this.selectedMenuNameHave[i]["listProtocols"].filter((obj: any) => obj != this.selectedMenuNameHave[i]['listProtocolsNHave'][c]);

        }
      }

      //this.selectedMenuNameHave[0]["listProtocols"].splice(0,1);
      // // console.log('this.selectedMenuNameHave: DISPLAY RED ', this.selectedMenuNameHave);
      // // console.log('this.selectedMenuNameHave: ', this.selectedMenuNameHave);
      // // console.log('this.protoNotHaveSelected: BEFORE', this.protoNotHaveSelected);
      // // console.log('this.protoHaveSelected: BEFORE', this.protoHaveSelected.length);
      this.selectedMenuNameHave.forEach(menuName => {
        const index = this.userHabilitation.indexOf(menuName); // Find the index of each selected menuName in the 'have' array
        // // console.log('index: ', index);
        // // console.log('menuName[groupCode]: ', menuName['groupCode']);
        // // console.log('menuName[listProtocols].length: ', menuName['listProtocols'].length);
        if (this.userHabilitationN == null) {
          this.userHabilitationN = [];
        }
        if (index > -1 && menuName['groupCode'] != 'ISO') {

          this.userHabilitation.splice(index, 1); // Remove the selected menuName from the 'have' array
          // // console.log('found', menuName);
          this.protoNotHaveSelected = [];
          this.protoHaveSelected[0] = [];
          this.userHabilitationN.push(menuName); // Add each selected menuName to the 'dontHave' array
        }
        if (menuName['groupCode'] == 'ISO' && menuName['listProtocols'].length > 0) {
          // // console.log("IS ISO *****************************");
          const arr = menuName['listProtocolsNHave'].map((e: any) => {
            return e;
          });
          // // console.log("$$$$$  ", arr);
          this.protoNotHaveSelected.push(arr);
          //this.protoNotHaveSelected = menuName['listProtocolsNHave'];
          const arr2 = menuName['listProtocols'].map((e: any) => {
            return e;
          });
          this.protoHaveSelected[0] = arr2;
          /*for(let i=0 ; i<this.selectedAndRemovedProto.length;i++){
            this.protoNotHaveSelected.push(this.selectedAndRemovedProto[i]);
            this.protoHaveSelected.splice(this.selectedAndRemovedProto[i]);
          }*/
          // // console.log('userHabilitation: ', this.userHabilitation);
        }
        if (index > -1 && menuName['groupCode'] == 'ISO' && menuName['listProtocols'].length == 0) {
          // // console.log("IS ISO 2 *****************************");
          this.userHabilitation.splice(index, 1); // Remove the selected menuName from the 'have' array
          // // console.log('found', menuName);
          this.userHabilitationN.push(menuName);
          // // console.log('menuName: assigned', menuName);

        }


      });
      // // console.log('this.protoNotHaveSelected: after', this.protoNotHaveSelected);
      // // console.log('this.protoHaveSelected: BEFORE2', this.protoHaveSelected);
      // // console.log('this.selectedAndRemovedProto ///>>> ', this.selectedAndRemovedProto);
      this.selectedAndRemovedProto.forEach(proto => {
        // // console.log('proto: ', proto);
        for (let i = 0; i < this.protoHaveSelected.length; i++) {
          // // console.log('this.protoHaveSelected[0][i]: ', this.protoHaveSelected[0][i]);
          if (this.protoHaveSelected[0][i] == proto) {

            // // console.log("found matching");
            this.protoHaveSelected[0].splice(i, 1);
            // // console.log('this.protoNotHaveSelected: ', this.protoHaveSelected);
          }
        }
      })
      this.selectedMenuNameHave = []; // Reset the selected menuName array to an empty array
      this.protoHaveSelected = [];
      this.protoNotHaveSelected = [];
    }
  }

  displayMenuNameHave() {
    // // console.log("this.protSelectRed add==>", this.protSelectRed);
    this.selectedAndRemovedProto = [];
    this.selectedAndAddedProto = [];
    this.notHaveProtoForBlueIso = [];
    this.selectMenuBlue = false;

    this.blueMSelected = false;
    this.RedMSelected = false;
    this.isISOEnabled = false;
    //this.notHaveProtoForBlueIso = [];
    this.addSellected = false;
    // // console.log('this.userHabilitationN: ', this.userHabilitationN);
    // // console.log('this.userHabilitation: ', this.userHabilitation);
    // // console.log('this.notHaveProtoForBlueIso.length: ', this.notHaveProtoForBlueIso.length);
    // // console.log('this.notHaveProtoForBlueIso: ', this.notHaveProtoForBlueIso);
    if (this.userHabilitation == null) {
      this.userHabilitation = [];

    }


    // // console.log("selected Menu", this.selectedMenuNameDHave);
    // ----- Handling the case if the Habilitation Blue selected and the red protocols selected as well

    this.userHabilitation.map((e: any) => {

      if (e.groupCode === "ISO" && this.notHaveProtoForBlueIso.length > 0) {
        for (let f = 0; f < this.notHaveProtoForBlueIso.length; f++) {
          const protoFound = e.listProtocols.find((obj: any) => obj == this.notHaveProtoForBlueIso[f]);
          if (!protoFound) {
            // // console.log('this.notHaveProtoForBlueIso[f]: not found in userHabi ', this.notHaveProtoForBlueIso[f]);
            e.listProtocols.push(this.notHaveProtoForBlueIso[f]);
            // // console.log('this.protoHaveSelected: before push ', this.protoHaveSelected);
            this.protoHaveSelected[0].filter((item: any) => item != this.notHaveProtoForBlueIso[f]);
            //// // console.log('arr: ', arr);


            this.protoHaveSelected[0] = e.listProtocols;




            // // console.log('this.protoHaveSelected: after push ', this.protoHaveSelected);
            // // console.log('this.notHaveProtoForBlueIso[f]: ', this.notHaveProtoForBlueIso[f]);
          }


          // // console.log('this.protoNotHaveSelected[0]: ', this.protoNotHaveSelected[0]);
          this.protoNotHaveSelected[0].map((ob: any) => {

            if (ob == this.notHaveProtoForBlueIso[f]) {
              // // console.log('ob: ', ob);
              // // console.log('this.notHaveProtoForBlueIso[f]: ', this.notHaveProtoForBlueIso[f]);
              // // console.log("MATCHING");
              this.protoNotHaveSelected[0].splice(this.protoNotHaveSelected[0].indexOf(ob), 1);
            }
          })
          //this.protoNotHaveSelected[0].filter((obj:any) => obj != this.notHaveProtoForBlueIso[f]);
          // // console.log('this.protoNotHaveSelected: ', this.protoNotHaveSelected[0]);
          e.listProtocolsNHave.map((obj: any) => {
            if (obj == this.notHaveProtoForBlueIso[f]) {
              // // console.log("MATCHING22");
              // // console.log('obj.index: ', e.listProtocolsNHave.indexOf(obj));
              e.listProtocolsNHave.splice(e.listProtocolsNHave.indexOf(obj), 1);
            }
          })
          e.listProtocolsNHave.filter((obj: any) => obj != this.notHaveProtoForBlueIso[f]);
          // // console.log('e.listProtocolsNHave: ', e.listProtocolsNHave);
        }
      }
      else {
        // // console.log("Blue Menu Not selected");
      }
    })

    // // console.log('this.userHabilitation:  CHECK FILTER', this.userHabilitation);
    //---------------------------------------------------------------------------------------------------

    if (this.selectedMenuNameDHave && Array.isArray(this.selectedMenuNameDHave) && this.selectedMenuNameDHave.length > 0) {
      // // console.log('this.selectedMenuNameDHave.length: ', this.selectedMenuNameDHave.length);
      // // console.log('this.selectedMenuNameDHave: ', this.selectedMenuNameDHave);
      // // console.log('this.selectedMenuNameDHave[].length: ', this.selectedMenuNameDHave[0].length);
      // // console.log('this.selectedMenuNameDHave[0]: ', this.selectedMenuNameDHave[0]);
      for (let i = 0; i < this.selectedMenuNameDHave.length; i++) {
        for (let c = 0; c < this.selectedMenuNameDHave[i]['listProtocols'].length; c++) {
          this.selectedMenuNameDHave[i]['listProtocolsNHave'] = this.selectedMenuNameDHave[i]['listProtocolsNHave'].filter((obj: any) => obj != this.selectedMenuNameDHave[i]['listProtocols'][c]);
        }

        for (let c = 0; c < this.selectedMenuNameDHave[i]['listProtocolsNHave'].length; c++) {
          // // console.log('this.selectedMenuNameDHave[i][listProtocolsNHave].length: ', this.selectedMenuNameDHave[i]['listProtocolsNHave'].length);
          this.selectedMenuNameDHave[i]['listProtocols'] = this.selectedMenuNameDHave[i]['listProtocols'].filter((obj: any) => obj != this.selectedMenuNameDHave[i]['listProtocolsNHave'][c]);
        }
        if (this.notHaveProtoForBlueIso.length > 0) {
          // // console.log('this.notHaveProtoForBlueIso.length: ', this.notHaveProtoForBlueIso.length);
          // // console.log("blueISO selected this.notHaveProtoForBlueIso.length>0");
          for (let f = 0; f < this.notHaveProtoForBlueIso.length; f++) {
            this.selectedMenuNameDHave[i]['listProtocols'].push(this.notHaveProtoForBlueIso[f]);
          }
        }
       
      }
      // // console.log('this.selectedMenuNameDHave: after ===  ', this.selectedMenuNameDHave.length);
      this.selectedMenuNameDHave.forEach(menuName => {
        const index = this.userHabilitationN.indexOf(menuName); // Find the index of each selected menuName in the 'have' array
        if (index > -1) {
          this.userHabilitationN.splice(index, 1); // Remove the selected menuName from the 'have' array
          // // console.log('found', menuName);
        }
        if (this.userHabilitation == null) {
          this.userHabilitation = [];
        }
        this.userHabilitation.push(menuName);// Add each selected menuName to the 'dontHave' array;
        if (menuName['groupCode'] == 'ISO' && menuName['listProtocols'].length == 0) {
          // // console.log('IN MY NEW CONDITION');
          this.userHabilitation = this.userHabilitation.filter((ob: any) => ob != menuName);
          this.userHabilitationN.push(menuName);
        }
        // // console.log('this.userHabilitation: ', this.userHabilitation);
      });

      // Reset the selected menuName array to an empty array
      // // console.log('this.protoNotHaveSelected: BEFORE', this.protoNotHaveSelected);
      // // console.log('this.protoNotHaveSelected.length: ', this.protoNotHaveSelected.length);
      let c = 0;
      // // console.log('this.selectedAndAddedProto: ', this.selectedAndAddedProto);
      this.selectedAndAddedProto.forEach(proto => {


        for (let i = 0; i < this.protoNotHaveSelected[0].length; i++) {

          // // console.log('this.protoNotHaveSelected[0][i]: ', this.protoNotHaveSelected[0][i]);
          // // console.log('proto: ', proto);
          if (this.protoNotHaveSelected[0][i] == proto) {
            c++;
            // // console.log("found matching");
            this.protoNotHaveSelected[0].splice(i, 1);
            // // console.log('this.protoNotHaveSelected[0]: ', this.protoNotHaveSelected[0][i]);
            // // console.log('this.protoNotHaveSelected: ', this.protoNotHaveSelected);
          }
        }
      })
      this.selectedMenuNameDHave = [];
      this.selectedAndAddedProto = [];
      this.notHaveProtoForBlueIso = [];


    }
    this.selectedMenuNameDHave = [];
    this.selectedMenuNameHave = [];
    this.protoHaveSelected = [];
    this.protoNotHaveSelected = [];
  }


  /*------------ pop up  --------------*/
  openModal() {
    this.modal?.nativeElement.showModal()
  }
  closeModal() {
    this.modal?.nativeElement.close();
    this.saveEdit = false;
    this.hideFirstEdit = true;
    this.allowEdit = false;
    this.getUserHabilitation(this.userCode);
  }

  /*---------------- pop up end  ----------------*/
  formattedDate: String | null = null;
  getUserInformation(userCode: string) {
    this.globalService.getUserInfos(userCode).subscribe(res => {
      // // console.log('result User Infos ==> ', res['result']);
      this.user.userCode = res['result']['userCode'];
      this.user.userName2 = res['result']['userName2'];
      this.user.userType = res['result']['userType'];
      this.user.address = res['result']['address'];
      this.user.email = res['result']['email'];
      this.user.password = res['result']['password'];
      this.user.numberOfTries = res['result']['numberOfTries'];
      this.user.numberOfTriesAllowed = res['result']['numberOfTriesAllowed'];
      this.user.mobileNumber = res['result']['mobileNumber'];
      this.user.userBankCode = res['result']['userBankCode'];
      this.user.languageCode = res['result']['languageCode'];
      this.user.blockAccess = res['result']['blockAccess'];
      this.user.ipAddress = res['result']['ipAddress'];
      // this.formatDate(res['result']['dateStartPass']);
      //this.user.dateStartPass = res['result']['dateStartPass'];
      this.user.dateStartPass = this.formatDate(res['result']['dateStartPass']);
      // // console.log('this.user.dateStartPass get funct: ', this.user.dateStartPass)

      this.user.dateEndPass = this.formatDate(res['result']['dateEndPass']);
      if (this.user.blockAccess == 'Y') {
        this.userBlocked = true;
      }
      this.user.status = res['result']['status'];
    })
  }

  formatDate(date: string): string {
    // Assuming date is in the format 'yyyy-MM-ddTHH:mm:ss.SSSZ'
    const formattedDate = this.datePipe.transform(new Date(date), 'yyyy-MM-dd');
    return formattedDate || ''; // Handle null or undefined case
  }

  getUserActivities(userCode: string) {
    this.globalService.getUserActivities(userCode).subscribe(res => {
      if (res['result'].length > 0) {
        this.userActivityList = res['result'];
      } else {
        this.userActivityList = [];
      }
      // // console.log('this.userActivityList: ', this.userActivityList);
    })
  }


  editUser() {
    // // console.log('userCode EDIT: ', this.userCode);
    // // console.log('user EDIT date start after: ', this.user.dateStartPass);
    const IpAddRegex: RegExp = /^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
    const isIpAdValid: boolean = IpAddRegex.test(this.user.ipAddress);

    const emailRegex: RegExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    const isEmailValid: boolean = emailRegex.test(this.user.email);
    const phoneRegex: RegExp = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
    const isPhoneValid: boolean = phoneRegex.test(this.user.mobileNumber);

    // // console.log("edit Method");
    if (isIpAdValid && isEmailValid && isPhoneValid) {
      this.goodIpSyntax = true;
      this.goodEmailSyntax = true;
      this.goodPhoneSyntax = true;
      this.globalService.updateUser(this.user).subscribe(res => {
        // // console.log("result ::> ", res);
        if (res['respCode'] == '000') {
          this.message = "user info were Succesdfully modified!!";
          this.alertBody.status = "000"
          if (this.en) {
            this.alertBody.message = "User Info updated successfully"
          }
          if (this.fr) {
            this.alertBody.message = "L'Utilisateur a été modifié avec succès"
          }
          if (this.esp) {
            this.alertBody.message = "La información del usuario se actualizó correctamente"
          }
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }
        else {
          // // console.log("else update");
          this.alertBody.status = res.respCode
          if (this.en) {
            this.alertBody.message = "SomeThing went wrong (Invalid cridentials or connection issue)"
          }
          if (this.fr) {
            this.alertBody.message = "Une erreur s'est produite (identifiants invalides ou problème de connexion)"
          }
          if (this.esp) {
            // // console.log("espagnol");
            this.alertBody.message = "Algo salió mal (credenciales no válidas o problema de conexión)"
          }
          //this.alertBody.message = res.respMsg
          this.alertBody.open = true
          setTimeout(() => {
            this.alertBody.open = false;
          }, 3000);
        }

      })
    }
    if (!isIpAdValid) {
      this.goodIpSyntax = false;
      // // console.log("INVALID SYNTAX");
    } else {
      this.goodIpSyntax = true;
    }
    if (!isEmailValid) {

      this.goodEmailSyntax = false;
      // // console.log("INVALID SYNTAX");
    }
    else {
      this.goodEmailSyntax = true;
    }
    if (!isPhoneValid) {
      this.goodPhoneSyntax = false;
      // // console.log("INVALID SYNTAX");
    }
    else {
      this.goodPhoneSyntax = true;
    }
  }

  displaySaveButton() {
    this.allowEdit = true;
    this.hideFirstEdit = false;
    this.saveEdit = true;
  }

  onCheckboxChange() {
    if (this.userBlocked) {
      // // console.log('unchecked');
      this.user.blockAccess = 'N';
      // // console.log('this.user.blockAccess: ', this.user.blockAccess);
    } else {
      // // console.log('checked');
      this.user.blockAccess = 'Y';
      // // console.log('this.user.blockAccess: ', this.user.blockAccess);
    }
    // Toggle the userBlocked value
    this.userBlocked = !this.userBlocked;
  }

  resetUpdate() {
    this.getUserInformation(this.userCode);
    this.saveEdit = false;
    this.hideFirstEdit = true;
    this.allowEdit = false;
  }

  userDeleteActive = false;
  openDeletePopUp() {
    this.message = "Please click Delete to proceed the action"
    this.userDeleteActive = true;
    this.openModal();
  }

  deleteUser() {
    console.log("this.user ==>",this.user);
    
    const body={
        userCode: this.user.userCode,
        status: this.user.status
    }

    this.globalService.deleteUser(body).subscribe(res=>{
      if (res.respCode==="000") {
        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "User deleted successfully"
        }
        if(this.fr){
          this.alertBody.message = "Utilisateur supprimé avec succès"
        }
        if(this.esp){
          this.alertBody.message = "User deleted successfully"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
        setTimeout(() => {
          // Navigate to another page using the router
          this.router.navigate(['/administration/management']); // Replace 'your-destination' with the actual route
    
        }, 3000);
      }
      else{
        this.alertBody.status = "000"
        if(this.en){
          this.alertBody.message = "User deleted failed"
        }
        if(this.fr){
          this.alertBody.message = "Échec de la suppression de l'utilisateur"
        }
        if(this.esp){
          this.alertBody.message = "User deleted successfully"
        }
        this.alertBody.open = true
        setTimeout(() => {
          this.alertBody.open = false;
        }, 3000);
      }
    })
   
    
  }
}
