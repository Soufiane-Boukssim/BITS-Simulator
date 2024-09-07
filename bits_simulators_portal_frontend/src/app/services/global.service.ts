import { HttpClient,HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, ObservedValueOf, Subject } from 'rxjs';
import { environment } from 'src/env/environement';
import { UserManagement } from '../pages/user-details/user-details.component';
import {ATMfield} from "../pages/atm/model/ATMfield";
const baseUrl = environment.url
const urlHamza = 'http://localhost:9193'
@Injectable({
  providedIn: 'root'
})
export class GlobalService {
  visibleMenuObs = new Subject<boolean>();
  visibleMenu = this.visibleMenuObs.asObservable()

  titleSubject = new Subject<string>();
  protocolSubject = new Subject<string>();
  page__title = this.titleSubject.asObservable()
  protocol = this.protocolSubject.asObservable()
  currentRoute: string = ""
  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.currentRoute = this.router.url.slice(1, 4)
    // console.log('this.currentRoute: ', this.currentRoute);
  }



  getUsers(body: any): Observable<any> {
    return this.http.post(baseUrl + '/sa/user/getListOfUsers', body);
  }

  getUserInfos(userCode: string): Observable<any> {
    return this.http.post(baseUrl + '/sa/user/getUserInfos', userCode);
  }

  getUserActivities(userCode: string): Observable<any> {
    return this.http.post(baseUrl + '/sa/user/getUserActivities', userCode);
  }

  updateUser(user:UserManagement):Observable<any>{
    return this.http.post(baseUrl + '/sa/user/updateUser', user)
  }

  AddUser(data:any):Observable<any>{
    return this.http.post(baseUrl + '/o/auth/addUser',data);

  }

  deleteUser(body: any): Observable<any> {
    return this.http.post<any>(baseUrl + '/sa/user/deleteUser', body)
  }

  changePassword(userCode:string,oldPass:string,newPass:string):Observable<any>{
    const body = {
      "userCode": userCode,
      "oldPassword":oldPass,
      "newPassword":newPass
    }
    return this.http.post<any>(baseUrl + '/sa/user/changePassword',body);
  }

  //card profile
  getCards(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/CardProfile/getAllCardProfiles', body)
  }
  addCard(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/CardProfile/addCardProfile', body)
  }
  updateCard(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/CardProfile/updateCardProfile', body)
  }
  deleteCard(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/CardProfile/deleteCardProfile', body)
  }



  //card profile
  getCardsPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/CardProfile/getAllCardProfiles', body)
  }
  addCardPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/CardProfile/addCardProfile', body)
  }
  updateCardPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/CardProfile/updateCardProfile', body)
  }
  deleteCardPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/CardProfile/deleteCardProfile', body)
  }




  ////  valid card holder

  validateCardExp(body: any): Observable<any> {
    return this.http.post(baseUrl + '/cardHolder-validate/validateCard', body)
  }


  ////
  //Account

  //Tags
  fetchTags(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/subFldDef/getAllSubfldDefinitions', body)
  }


  fetchMTI(body: any): Observable<any> {

    return this.http.post(baseUrl + '/su/mtiDef/getAllMtidefs', body)
  }
  addMti(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/mtiDef/addMtiDef', body)
  }
  updateMti(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/mtiDef/updateMtiDef', body)
  }
  deleteMti(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/mtiDef/deleteMtiDef', body)
  }

  //Tags POS
  fetchTagsPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/subFldDef/getAllSubfldDefinitions', body)
  }


  //// MTI POS

  fetchMTIPOS(body: any): Observable<any> {

    return this.http.post(baseUrl + '/su/pos/mtiDef/getAllMtidefs', body)
  }
  addMtiPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/mtiDef/addMtiDef', body)
  }
  updateMtiPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/mtiDef/updateMtiDef', body)
  }
  deleteMtiPOS(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/mtiDef/deleteMtiDef', body)
  }



  //Country
  fetchCountry(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/countryParam/getAllCountryParams', body)
  }
  addCountry(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/countryParam/addCountryParam', body)
  }
  updateCountry(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/countryParam/updateCountryParam', body)
  }
  deleteCountry(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/countryParam/deleteCountryParam', body)
  }

  //City

  fetchCities(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/City/getAllCities', body)
  }

  addCity(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/City/addCity', body)
  }

  updateCity(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/City/updateCity', body)
  }

  deleteCity(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/City/deleteCity', body)
  }


  //Currency
  fetchCurrency(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/currencyParam/getAllCurrencyParams', body)
  }
  addCurrency(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/currencyParam/addCurrencyParam', body)
  }
  updateCurrency(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/currencyParam/updateCurrencyParam', body)
  }
  deleteCurrency(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/currencyParam/deleteCurrencyParam', body)
  }

  getAmount(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/currencyParam/getAmount', body)
  }

  //Exchange
  fetchExchange(body: any): Observable<any> {
  
    return this.http.post(baseUrl + '/su/exchangeRateParam/getAllExchangeRateParams', body)
  }
  addExchange(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/exchangeRateParam/addExchangeRateParam', body)
  }
  updateExchange(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/exchangeRateParam/updateExchangeRateParam', body)
  }
  deleteExchange(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/exchangeRateParam/deleteExchangeRateParam', body)
  }


    //ExchangePOS
    fetchExchangePos(body: any): Observable<any> {
  
      return this.http.post(baseUrl + '/su/pos/exchangeRateParam/getAllExchangeRateParams', body)
    }
    addExchangePos(body: any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/exchangeRateParam/addExchangeRateParam', body)
    }
    updateExchangePos(body: any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/exchangeRateParam/updateExchangeRateParam', body)
    }
    deleteExchangePos(body: any): Observable<any> {
      // console.log('body: ', body);
      return this.http.post(baseUrl + '/su/pos/exchangeRateParam/deleteExchangeRateParam', body)
    }
  


  //Response
  fetchResponse(body: any): Observable<any> {

    return this.http.post(baseUrl + '/su/responseDef/getAllResponseDefs', body)
  }
  addResponse(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/responseDef/addResponseDef', body)
  }
  updateResponse(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/responseDef/updateResponseDef', body)
  }
  deleteResponse(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/responseDef/deleteResponseDef', body)
  }

   //Response POS
   fetchResponsePOS(body: any): Observable<any> {

    return this.http.post(baseUrl + '/su/pos/responseDef/getAllResponseDefs', body)
  }
  addResponsePOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/responseDef/addResponseDef', body)
  }
  updateResponsePOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/responseDef/updateResponseDef', body)
  }
  deleteResponsePOS(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/responseDef/deleteResponseDef', body)
  }



  //Mcc
  fetchMcc(body:any): Observable<any> {

    return this.http.post(baseUrl + '/su/mccDef/getAllMccDefs', body)
  }
  addMcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/mccDef/addMccDef', body)
  }
  updateMcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/mccDef/updateMccDef', body)
  }
  deleteMcc(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/mccDef/deleteMccDef', body)
  }


   //Mcc pos
   fetchMccPOS(body:any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/mccDef/getAllMccDefs', body)
  }
  addMccPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/mccDef/addMccDef', body)
  }
  updateMccPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/mccDef/updateMccDef', body)
  }
  deleteMccPOS(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/mccDef/deleteMccDef', body)
  }



  //Icc
  fetchIcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/iccProfile/getAllIccProfiles', body)
  }
  addIcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/iccProfile/addIccProfile', body)
  }
  updateIcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/iccProfile/updateIccProfile', body)
  }
  deleteIcc(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/iccProfile/deleteIccProfile', body)
  }



   //Icc POS
   fetchIccPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/iccProfile/getAllIccProfiles', body)
  }
  addIccPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/iccProfile/addIccProfile', body)
  }
  updateIccPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/iccProfile/updateIccProfile', body)
  }
  deleteIccPOS(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/iccProfile/deleteIccProfile', body)
  }





  //Account
  fetchAccount(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/accountDef/getAllAccountdefs', body)
  }
  addAccount(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/accountDef/addAccountDef', body)
  }
  updateAccount(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/accountDef/updateAccountDef', body)
  }
  deleteAccount(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/accountDef/deleteAccountDef', body)
  }


    //Account POS
    fetchAccountPOS(body: any): Observable<any> {

      return this.http.post(baseUrl + '/su/pos/accountDef/getAllAccountdefs', body)
    }
    addAccountPOS(body: any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/accountDef/addAccountDef', body)
    }
    updateAccountPOS(body: any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/accountDef/updateAccountDef', body)
    }
    deleteAccountPOS(body: any): Observable<any> {
      // console.log('body: ', body);
      return this.http.post(baseUrl + '/su/pos/accountDef/deleteAccountDef', body)
    }





  //Function
  fetchFunction(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/functDef/getAllFunctDefs', body)
  }

  fetchFunctionByProtocol(body:any): Observable<any> {
    return this.http.post(baseUrl + '/su/functDef/getFunctDefsByProtocol', body)
  }


  addFunction(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/functDef/addFunctDef', body)
  }
  updateFunction(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/functDef/updateFunctDef', body)
  }
  deleteFunction(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/functDef/deleteFunctDef', body)
  }


    //Function POS
    fetchFunctionPOS(): Observable<any> {
      let body = {}
      return this.http.post(baseUrl + '/su/pos/functDef/getAllFunctDefs', body)
    }

    fetchFunctionByProtocolPOS(body:any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/functDef/getFunctDefsByProtocol', body)
    }


    addFunctionPOS(body: any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/functDef/addFunctDef', body)
    }
    updateFunctionPOS(body: any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/functDef/updateFunctDef', body)
    }
    deleteFunctionPOS(body: any): Observable<any> {
      // console.log('body: ', body);
      return this.http.post(baseUrl + '/su/pos/functDef/deleteFunctDef', body)
    }




  //Processing
  fetchProcessing(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/processingDef/getAllProcessingDefs', body)
  }

  fetchProcessingByProtocol(body:any): Observable<any> {

    return this.http.post(baseUrl + '/su/processingDef/getProcessingDefsByProtocol', body)
  }
  addProcessing(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/processingDef/addProcessingDef', body)
  }
  updateProcessing(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/processingDef/updateProcessingDef', body)
  }
  deleteProcessing(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/processingDef/deleteProcessingDef', body)
  }



   //Processing POS
   fetchProcessingPOS(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/pos/processingDef/getAllProcessingDefs', body)
  }
  fetchProcessingByProtocolPOS(body:any): Observable<any> {

    return this.http.post(baseUrl + '/su/pos/processingDef/getProcessingDefsByProtocol', body)
  }
 
  addProcessingPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/processingDef/addProcessingDef', body)
  }
  updateProcessingPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/processingDef/updateProcessingDef', body)
  }
  deleteProcessingPOS(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/processingDef/deleteProcessingDef', body)
  }


  //BANK
  fetchBank(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/sa/bank/getAllBanks', body)
  }
  addBank(body: any): Observable<any> {
    return this.http.post(baseUrl + '/sa/bank/addBank', body)
  }
  updateBank(body: any): Observable<any> {
    return this.http.post(baseUrl + '/sa/bank/updateBank', body)
  }
  deleteBank(body: string): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/sa/bank/deleteBank', body)
  }

  //Tag

  fetchTag(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/subFldDef/getAllSubfldDefinitions', body)
  }

  fetchTagByProtocol(body:any): Observable<any> {
    return this.http.post(baseUrl + '/su/subFldDef/getSubfldDefinitionsByProtocols', body)
  }

  addTag(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/subFldDef/addSubfldDefinition', body)
  }
  updateTag(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/subFldDef/updateSubfldDefinition', body)
  }
  deleteTag(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/subFldDef/deleteSubfldDefinition', body)
  }



   //Tag POS

   fetchTagPOS(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/pos/subFldDef/getAllSubfldDefinitions', body)
  }

  fetchTagByProtocolPOS(body:any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/subFldDef/getSubfldDefinitionsByProtocols', body)
  }

  addTagPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/subFldDef/addSubfldDefinition', body)
  }
  updateTagPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/subFldDef/updateSubfldDefinition', body)
  }
  deleteTagPOS(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/subFldDef/deleteSubfldDefinition', body)
  }




  getMerchants(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/merchantParam/getAllMerchantParams', body).pipe();
  }

  getTerminals(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/terminalParam/getAllTerminalParams', body).pipe();
  }


 /*getAllCommunicationParams(): Observable<any> {
    return this.http.post(`/su/commsParam/getAllCommsParams`, {});
  }*/

  deleteCommunicationParam(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + `/su/commsParam/deleteCommsParam`, body);
  }

  addCommunicationParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/commsParam/addCommsParam`, body);
  }

  updateCommunicationParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/commsParam/updateCommsParam`, body);
  }

  getOneCommunicationParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/commsParam/getOneCommsParam`, body);
  }


  ///POS
  getOneCommunicationParamPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/commsParam/getOneCommsParam`, body);
  }


  ///POS

  deleteCommunicationParamPOS(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + `/su/pos/commsParam/deleteCommsParam`, body);
  }

  addCommunicationParamPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/commsParam/addCommsParam`, body);
  }

  updateCommunicationParamPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/commsParam/updateCommsParam`, body);
  }

  getOneCommunicationParaPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/commsParam/getOneCommsParam`, body);
  }




  //////// encrypto KEy ISO  ////////


  encryptKey(body: any): Observable<any> {
    return this.http.post(baseUrl + '/Crypto/key/encrypt', body).pipe();
  }


  //////////








  fetchMerchant(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/merchantParam/getAllMerchantParams', body);
  }

  addMerchant(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/merchantParam/addMerchantParam', body);
  }

  updateMerchant(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/merchantParam/updateMerchantParam', body);
  }

  deleteMerchant(body: any): Observable<any> {
    // console.log('Merchant body: ', body);
    return this.http.post(baseUrl + '/su/merchantParam/deleteMerchantParam', body);
  }



  ///POS


  fetchMerchantPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/merchantParam/getAllMerchantParams', body);
  }

  addMerchantPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/merchantParam/addMerchantParam', body);
  }

  updateMerchantPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/merchantParam/updateMerchantParam', body);
  }

  deleteMerchantPOS(body: any): Observable<any> {
    // console.log('Merchant body: ', body);
    return this.http.post(baseUrl + '/su/pos/merchantParam/deleteMerchantParam', body);
  }




   //////// Terminal
  fetchTerminal(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/terminalParam/getAllTerminalParams', body);
  }

  addTerminal(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/terminalParam/addTerminalParam', body);
  }

  updateTerminal(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/terminalParam/updateTerminalParam', body);
  }

  deleteTerminal(body: any): Observable<any> {
    // console.log('Terminal body: ', body);
    return this.http.post(baseUrl + '/su/terminalParam/deleteTerminalParam', body);
  }


    //////// Terminal POS
    fetchTerminalPOS(body: any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/terminalParam/getAllTerminalParams', body);
    }

    addTerminalPOS(body: any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/terminalParam/addTerminalParam', body);
    }

    updateTerminalPOS(body: any): Observable<any> {
      return this.http.post(baseUrl + '/su/pos/terminalParam/updateTerminalParam', body);
    }

    deleteTerminalPOS(body: any): Observable<any> {
      // console.log('Terminal body: ', body);
      return this.http.post(baseUrl + '/su/pos/terminalParam/deleteTerminalParam', body);
    }





  updateInstitutionParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/instParam/updateInstParam`, body);
  }

  getOneInstitutionParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/instParam/getOneInstParam`, body);
  }

  deleteInstitutionParam(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + `/su/instParam/deleteInstParam`, body);
  }

  addInstitutionParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/instParam/addInstParam`, body);
  }



  ////POS ===>

  updateInstitutionParamPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/instParam/updateInstParam`, body);
  }

  getOneInstitutionParamPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/instParam/getOneInstParam`, body);
  }

  deleteInstitutionParamPOS(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + `/su/pos/instParam/deleteInstParam`, body);
  }

  addInstitutionParamPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/instParam/addInstParam`, body);
  }




  //authMessageLog
  getMessages(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/message/getAllMessages', body).pipe();
  }



  //authMessageLog POS
  getMessagesPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/message/getAllMessages', body).pipe();
  }




  //intern Services
  getSidDump(body: any): Observable<any> {
    // return this.http.post<any>('https://acs.bits.ma:13443/BITSWS/SidDump', body)
    // console.log("getSidDump body ==>",body);
    
    return this.http.post<any>(baseUrl +"/su/webService/SidDump", body)
  }


  getSidDumpPos(body: any): Observable<any> {
    // return this.http.post<any>('https://acs.bits.ma:13443/BITSWS/SidDump', body)
    // console.log("getSidDump body ==>",body);
    
    return this.http.post<any>(baseUrl +"/su/pos/webService/SidDump", body)
  }

  apiUrl: string = "https://acs.bits.ma:13443/BITSWS/";

  SidBuildMsg(body: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + `SidBuildMsg`, body)
  }

  SidDump(body: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + `SidDump`, body)
  }

  // SidMsgHexTrace(body: any): Observable<any> {
  //   return this.http.post<any>(this.apiUrl + `SidMsgHexTrace`, body)
  // }

  SidMsgHexTrace(body: any): Observable<any> {
    return this.http.post<any>(baseUrl +"/su/webService/SidMsgHexTrace", body)
  }

  SidMsgHexTracePos(body: any): Observable<any> {
    return this.http.post<any>(baseUrl +"/su/pos/webService/SidMsgHexTrace", body)
  }

  getFields(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/fieldsDefinition/getAllFieldsDefinitions', body).pipe();
  }

  getFieldsPOS(): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/fieldsDefinition/getAllFieldsDefinitions', {}).pipe();
  }

  

  //HABILITAIONS
  getUserHabilitations(userCode: string):Observable<any>{
    const params = {
      userCode : userCode,
      menuName: [
        "string"
      ]
  };
    return this.http.post<any>(baseUrl + '/sa/userHab/GroupsInUser', params);
  }

  getNotUserHabilitations(userCode: string):Observable<any>{
    const params = {
      userCode : userCode,
      menuName: [
        "string"
      ]
  };
    return this.http.post<any>(baseUrl + '/sa/userHab/GroupsNotInUser', params);
  }


  validatingHabilitaion(userCode:string,menuName:any[],protocols:any[]):Observable<any>{
    let body = {
      userCode:userCode,
      menuName:menuName,
      protocols:protocols
    }
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Accept': 'application/json'});
    return this.http.post(baseUrl + '/sa/userHab/validate',body,{headers: headers}) ;
  }

  getAllGroups():Observable<any>{
    return this.http.get(baseUrl + '/sa/userHab/allGroups');
  }

  addNewGroup(groupCode:string,groupName:string,menuCode:string):Observable<any>{
    const params = {
      groupCode,
      groupName,
      menuCode
    };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
      // Add any other headers if needed
    });
    return this.http.post<any>(`${baseUrl}/sa/userHab/addGroup`,null,{ headers,params });
  }
  updateGroup(groupCode:string,groupName:string,menuCode:string):Observable<any>{
    const params = {
      groupCode,
      groupName,
      menuCode
    };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
      // Add any other headers if needed
    });
    return this.http.post<any>(`${baseUrl}/sa/userHab/updateGroup`,null,{ headers,params });
  }

  deleteGroup(groupCode: any): Observable<any> {
    const params = {
      groupCode
    };
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
      // Add any other headers if needed
    });
    return this.http.post(`${baseUrl}/sa/userHab/removeGroup`,null, {headers,params} );
  }

  getAllMenus():Observable<any>{
    return this.http.get(baseUrl + '/sa/userHab/allMenus');
  }

  getScenarios(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/caseSetInfo/getAllCaseSetInfos', body).pipe();
  }

  //POS
  getScenariosPOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/caseSetInfo/getAllCaseSetInfos', body).pipe();
  }



  // API  de WEb services ISO
  getBuffer(body: any, protocolCode: string): Observable<any> {
    // console.log('test caseType getBuffer ----> ',protocolCode);

    // return this.http.post(baseUrl + '/su/webService/getBuffer', body).pipe();
    return this.http.post(baseUrl + '/su/webService/getBuffer?protocolCode=' + protocolCode, body).pipe();

  }

  getResponseMessage(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/webService/responseMessage', body).pipe();
  }


  getMsgKeyExchange(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/webService/getMsgKeyExchange', body).pipe();
  }
  
  clear_Session(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/webService/clear_Session', body).pipe();
  }


  getDataToShow(body: any, protocolCode: string ,bankCode:string): Observable<any> {
    const params = new HttpParams()
        .set('protocolCode', protocolCode)
        .set('bankCode', bankCode);

    return this.http.post(baseUrl + '/su/webService/getDataToShow', body, { params }).pipe();
  }


   // API  de WEb services POS
   getBufferPOS(body: any, protocolCode: string): Observable<any> {
    // console.log('test caseType getBuffer ----> ',protocolCode);

    // return this.http.post(baseUrl + '/su/webService/getBuffer', body).pipe();
    return this.http.post(baseUrl + '/su/pos/webService/getBuffer?protocolCode=' + protocolCode, body).pipe();

  }

  getResponseMessagePOS(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/webService/responseMessage', body).pipe();
  }

  getDataToShowPOS(body: any, protocolCode: string ,bankCode:string): Observable<any> {
    const params = new HttpParams()
        .set('protocolCode', protocolCode)
        .set('bankCode', bankCode);

    return this.http.post(baseUrl + '/su/pos/webService/getDataToShow', body, { params }).pipe();
  }








  //ATM
  getComms(commsDefinitionId: any): Observable<any> {
    return this.http.post(`${baseUrl}/su/communication/communications`, commsDefinitionId);
  }

  addComms(commsDefinition: any): Observable<any> {
    return this.http.post(`${baseUrl}/su/communication/add`, commsDefinition);
  }

  updateComms(commsDefinition: any): Observable<any> {
    return this.http.post(`${baseUrl}/su/communication/update`, commsDefinition);
  }

  deleteComms(commsDefinitionId: any): Observable<any> {
    return this.http.post(`${baseUrl}/su/communication/delete`, commsDefinitionId);
  }

  addTerminalDefinition(definitionModel: any): Observable<any> {
    return this.http.post(`${baseUrl}/su/terminalDefinition/add`, definitionModel);
  }

  getTerminalDefinition(terminalDefinitionId: any): Observable<any> {
    return this.http.post(`${baseUrl}/su/terminalDefinition/get`, terminalDefinitionId);
  }

  addReasonCommandRej(reasonCommandRej: any): Observable<any> {
    return this.http.post(`${baseUrl}/su/reasonCommandRej/add`, reasonCommandRej);
  }

  getReasonCommandRej(id: any): Observable<any> {
    return this.http.post(`${baseUrl}/su/reasonCommandRej/get`, id);
  }

 // logs

 getDataLogs(body:any): Observable<any> {
  // console.log("body log:",body);

  return this.http.post(baseUrl +'/v1/atm/terminal_log/logs', body).pipe();
 }
  saveMessages(data: any): Observable<any> {
    return this.http.post<any>(baseUrl+'/v1/atm/terminal_log/saveMessages', data);
  }

 getDataLogParaConfig(body:any): Observable<any> {
  // return this.http.get(baseUrl +'/v1/atm/log_ndc/configuration').pipe();
  return this.http.post(baseUrl +'/v1/atm/log_ndc/configuration', body).pipe();
 }

 getDataLogParaScreen(body:any): Observable<any> {
  return this.http.post(baseUrl +'/v1/atm/log_ndc/screen',body).pipe();

 }

 getDataLogParaState(body:any): Observable<any> {
  return this.http.post(baseUrl +'/v1/atm/log_ndc/state',body).pipe();
 }

 getDataLogParaFit(body:any): Observable<any> {
  return this.http.post(baseUrl +'/v1/atm/log_ndc/fit',body).pipe();
 }

 getDataLogParaAid(body:any): Observable<any> {
  return this.http.post(baseUrl +'/v1/atm/log_ndc/aid',body).pipe();
 }

 getDataLogTypeState(body:any): Observable<any>{
  return this.http.get(baseUrl +'/v1/atm/log_ndc/state_type').pipe()
 }


//  getImage(): Observable<Blob> {
//   return this.http.get(baseUrl +'/v1/atm/log_ndc/image', { responseType: 'blob' });
// }

getImage(imageId: string) {
  return this.http.get<any>(baseUrl +`/v1/atm/log_ndc/image/${imageId}`, { responseType: 'blob' as 'json' });
}

/// get page  user

getUserRolePage(body:any): Observable<any> {

  return this.http.post(baseUrl +'/v1/atm/terminal_log/user_role_page', body).pipe();
 }

 getProtocole(): Observable<any> {

  return this.http.get(baseUrl +'/v1/atm/terminal_log/protocoles').pipe();
 }

//  commsDefinitionId

 getCommunication(commsDefinitionId: any): Observable<any> {
  return this.http.post(`${baseUrl}/su/communication/communications`, commsDefinitionId);
}

addCommunication(commsDefinition: any): Observable<any> {
  return this.http.post(`${baseUrl}/su/communication/add`, commsDefinition);
}

updateCommunication(commsDefinition: any): Observable<any> {
  return this.http.post(`${baseUrl}/su/communication/update`, commsDefinition);
}

deleteCommunication(commsDefinitionId: any): Observable<any> {
  return this.http.post(`${baseUrl}/su/communication/delete`, commsDefinitionId);
}




// getSolicite(body:any): Observable<any> {
//   return this.http.post(baseUrl +'/su/terminalMessNonsolicite/get', body).pipe();
//  }

getSoliciteService(body:any): Observable<any> {
  // // console.log("------>body:",body);

  return this.http.post(baseUrl + '/su/terminalMessNonsolicite/get', body);
}

updateSolicite(body:any):Observable<any>{
  // console.log("body updateSolicite --->:",body);
  return this.http.post<any>(baseUrl + '/su/terminalMessNonsolicite/update',body)
}





///// icc



 searchProfile(body:any): Observable<any> {
  // // console.log("body log:",body);

  return this.http.post(baseUrl +'/profiles/search', body).pipe();
 }


 getAllProfil(): Observable<any> {
  // // console.log("body log:",body);

  return this.http.get(baseUrl +'/profiles/getAllProfiles').pipe();
 }


 getTermminalCongById(body:any): Observable<any> {
  // // console.log("body log:",body);

  return this.http.post(baseUrl +'/terminal-config/getTermminalCongById', body).pipe();
 }


 getIssuerConfigById(body:any): Observable<any> {
  // // console.log("body log:",body);

  return this.http.post(baseUrl +'/issuer-config/getIssuerConfigById', body).pipe();
 }


 getTransactionConfigById(body:any): Observable<any> {
  // // console.log("body log:",body);

  return this.http.post(baseUrl +'/transaction-config/getTransactionConfigById', body).pipe();
 }


 geTagDefinitionByCodeProfile(body:any): Observable<any> {
  // // console.log("body log:",body);

  return this.http.post(baseUrl +'/get-tags-definition/getTagDefinitionByCodeProfile', body).pipe();
 }


 deleteTagDefinition(body:any): Observable<any> {
  // console.log("body deleteTagDefinition:",body);
  return this.http.post(baseUrl +'/get-tags-definition/delete', body).pipe();
 }

 saveTagDefinition(body:any): Observable<any> {
  // // console.log("body log:",body);
  return this.http.post(baseUrl +'/get-tags-definition/insert', body).pipe();
 }

 //// save

 saveProfile(body:any): Observable<any> {
  return this.http.post(baseUrl +'/profiles/insert', body).pipe();
 }

 changeActiveProfile(body:any): Observable<any> {
  return this.http.post(baseUrl +'/profiles/changeActiveProfile', body).pipe();
 }

 saveIssuerConfig(body:any): Observable<any> {
  return this.http.post(baseUrl +'/issuer-config/insert', body).pipe();
 }


 saveTermminalCong(body:any): Observable<any> {
  return this.http.post(baseUrl +'/terminal-config/insert', body).pipe();
 }
 saveTransactionConfig(body:any): Observable<any> {
  return this.http.post(baseUrl +'/transaction-config/insert', body).pipe();
 }

 /// delete


 deleteTransactionConfig(body:any): Observable<any> {
  return this.http.post(baseUrl +'/transaction-config/delete', body).pipe();
 }
 deleteTermminalCong(body:any): Observable<any> {
  return this.http.post(baseUrl +'/terminal-config/delete', body).pipe();
 }
 deleteIssuerConfig(body:any): Observable<any> {
  return this.http.post(baseUrl +'/issuer-config/delete', body).pipe();
 }

 deleteProfile(body:any): Observable<any> {
  return this.http.post(baseUrl +'/profiles/delete', body).pipe();
 }


 /// EMV

 initiateApplicationApi(body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getInitiateApplication', body).pipe();
 }


 readApplicationData (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getReadApplicationData', body).pipe();
 }


 offlineDataAuthentication (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getOfflineDataAuthentication', body).pipe();
 }


 processingRestriction (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getProcessingRestriction', body).pipe();
 }


 cardHolderVerification (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getCardHolderVerification', body).pipe();
 }


 terminalRiskManagement (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getTerminalRiskManagement', body).pipe();
 }



 terminalActionAnalysis (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getTerminalActionAnalysis', body).pipe();
 }



 cardActionAnalysis (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getCardActionAnalysis', body).pipe();
 }


 authorisationRequest (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getAuthorisationRequest', body).pipe();
 }

 issuerResponse (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getIssuerResponse', body).pipe();
 }

 completion (body:any): Observable<any> {
  return this.http.post(baseUrl +'/InitiateApplication/getCompletion', body).pipe();
 }



//  getAllCpaProfilById (): Observable<any> {
//   return this.http.get(baseUrl +'/InitiateApplication/getCpaProfilById').pipe();
//  }



 saveCpaProfil(body:any): Observable<any> {
  return this.http.post(baseUrl +'/cpa-profil/insert',body).pipe();
 }


 deleteCpaProfil(body:any): Observable<any> {
  return this.http.post(baseUrl +'/cpa-profil/delete',body).pipe();
 }


 getAllCPAProfiles (): Observable<any> {
  return this.http.get(baseUrl +'/cpa-profil/getAllCPAProfiles').pipe();
 }


 getCpaProfilById (body:any): Observable<any> {
  return this.http.post(baseUrl +'/cpa-profil/getCpaProfilById',body).pipe();
 }



 getParamKEyCpaProfilById (body:any): Observable<any> {
  return this.http.post(baseUrl +'/key-paramCpa/getKeyPAramCpaProfilById',body).pipe();
 }

 saveKeyCpaProfil(body:any): Observable<any> {
  return this.http.post(baseUrl +'/key-paramCpa/insert',body).pipe();
 }

 deleteKeyCpaProfil(body:any): Observable<any> {
  return this.http.post(baseUrl +'/key-paramCpa/delete',body).pipe();
 }



 getCpaTagsProfilById (body:any): Observable<any> {
  return this.http.post(baseUrl +'/cpa-tagsprofil/getTagCpaByCodeProfile',body).pipe();
 }


 saveCpaTagsProfilById (body:any): Observable<any> {
  return this.http.post(baseUrl +'/cpa-tagsprofil/insert',body).pipe();
 }


 deleteCpaTagsProfilById (body:any): Observable<any> {
  return this.http.post(baseUrl +'/cpa-tagsprofil/delete',body).pipe();
 }




 getTestCasesByCodeProfile (body:any): Observable<any> {
  return this.http.post(baseUrl +'/test-cases/getTestCasesByCodeProfile',body).pipe();
 }



 getGroupedTestCasesByCodeProfile (body:any): Observable<any> {
  return this.http.post(baseUrl +'/test-cases/getGroupedTestCasesByCodeProfile',body).pipe();
 }


 saveTestCase (body:any): Observable<any> {
  return this.http.post(baseUrl +'/test-cases/insert',body).pipe();
 }



 deleteCasesTest (body:any): Observable<any> {
  return this.http.post(baseUrl +'/test-cases/delete',body).pipe();
 }

  getAllATMfields(): Observable<ATMfield[]> {
    return this.http.get<ATMfield[]>(baseUrl + '/su/ATMfield/all');
  }

 ///  TOOLS ==============================================>
 parceData(body:any): Observable<any> {  
  return this.http.post(baseUrl +'/su/tools/Calculators/parceData',body).pipe();
 }



 formDataIPM(body:any): Observable<any> {  
  // console.log("body: " + body);
  
  return this.http.post(baseUrl +'/su/tools/ipmparser/upload',body).pipe();
 }

 showDetailsIPM(body:any): Observable<any> {  
  // console.log("body: " + body);
  
  return this.http.post(baseUrl +'/su/tools/ipmparser/show_details',body).pipe();
 }



 formDataLis(body:any): Observable<any> {  
  // console.log("body: " + body);
  
  return this.http.post(baseUrl +'/su/tools/lisparser/upload',body).pipe();
 }


 showDetailsLis(body:any): Observable<any> {  
  // console.log("body: " + body);
  
  return this.http.post(baseUrl +'/su/tools/lisparser/show_details',body).pipe();
 }



 generatePinBlockHex(body:any): Observable<any> {  
  return this.http.post(baseUrl +'/su/tools/Calculators/generateEncyptedPINBlock',body).pipe();
 }

 luhnAlgorithm_generated(body:any): Observable<any> {  
  return this.http.post(baseUrl +'/su/tools/luhn_algorithm/luhnAlgorithm_generated',body).pipe();
 }

 luhnAlgorithm_validated(body:any): Observable<any> {  
  return this.http.post(baseUrl +'/su/tools/luhn_algorithm/luhnAlgorithm_validate',body).pipe();
 }



 messageParserSid(body:any): Observable<any> {  
  return this.http.post(baseUrl +'/su/tools/messageParser/parceData',body).pipe();
 }


 processDesMP(body:any): Observable<any> {  
  return this.http.post(baseUrl +'/su/tools/Calculators/des_mp',body).pipe();
 }

}
