import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/env/environement';
const baseUrl = environment.url

@Injectable({
  providedIn: 'root'
})
export class PosGlobalService {
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

  //card profile
  getCards(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/CardProfile/getAllCardProfiles', body)
  }
  addCard(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/CardProfile/addCardProfile', body)
  }
  updateCard(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/CardProfile/updateCardProfile', body)
  }
  deleteCard(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/CardProfile/deleteCardProfile', body)
  }
  //Account

  //Tags
  fetchTags(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/subFldDef/getAllSubfldDefinitions', body)
  }


  fetchMTI(body: any): Observable<any> {

    return this.http.post(baseUrl + '/su/pos/mtiDef/getAllMtidefs', body)
  }
  addMti(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/mtiDef/addMtiDef', body)
  }
  updateMti(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/mtiDef/updateMtiDef', body)
  }
  deleteMti(body: any): Observable<any> {
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
  fetchExchange(): Observable<any> {
    let body = {}
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


  //Response
  fetchResponse(body: any): Observable<any> {

    return this.http.post(baseUrl + '/su/pos/responseDef/getAllResponseDefs', body)
  }
  addResponse(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/responseDef/addResponseDef', body)
  }
  updateResponse(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/responseDef/updateResponseDef', body)
  }
  deleteResponse(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/responseDef/deleteResponseDef', body)
  }




  //Mcc
  fetchMcc(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/pos/mccDef/getAllMccDefs', body)
  }
  addMcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/mccDef/addMccDef', body)
  }
  updateMcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/mccDef/updateMccDef', body)
  }
  deleteMcc(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/mccDef/deleteMccDef', body)
  }




  //Icc
  fetchIcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/iccProfile/getAllIccProfiles', body)
  }
  addIcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/iccProfile/addIccProfile', body)
  }
  updateIcc(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/iccProfile/updateIccProfile', body)
  }
  deleteIcc(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/iccProfile/deleteIccProfile', body)
  }





  //Account
  fetchAccount(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/pos/accountDef/getAllAccountdefs', body)
  }
  addAccount(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/accountDef/addAccountDef', body)
  }
  updateAccount(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/accountDef/updateAccountDef', body)
  }
  deleteAccount(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/accountDef/deleteAccountDef', body)
  }





  //Function
  fetchFunction(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/pos/functDef/getAllFunctDefs', body)
  }
  addFunction(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/functDef/addFunctDef', body)
  }
  updateFunction(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/functDef/updateFunctDef', body)
  }
  deleteFunction(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/functDef/deleteFunctDef', body)
  }






  //Processing
  fetchProcessing(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/pos/processingDef/getAllProcessingDefs', body)
  }
  addProcessing(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/processingDef/addProcessingDef', body)
  }
  updateProcessing(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/processingDef/updateProcessingDef', body)
  }
  deleteProcessing(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/processingDef/deleteProcessingDef', body)
  }


  //Tag

  fetchTag(): Observable<any> {
    let body = {}
    return this.http.post(baseUrl + '/su/pos/subFldDef/getAllSubfldDefinitions', body)
  }
  addTag(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/subFldDef/addSubfldDefinition', body)
  }
  updateTag(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/subFldDef/updateSubfldDefinition', body)
  }
  deleteTag(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + '/su/pos/subFldDef/deleteSubfldDefinition', body)
  }



  getMerchants(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/merchantParam/getAllMerchantParams', body).pipe();
  }

  getTerminals(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/terminalParam/getAllTerminalParams', body).pipe();
  }



  updateCommunicationParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/commsParam/updateCommsParam`, body);
  }

  getOneCommunicationParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/commsParam/getOneCommsParam`, body);
  }

  /*getAllCommunicationParams(): Observable<any> {
    return this.http.post(`/su/pos/commsParam/getAllCommsParams`, {});
  }*/

  deleteCommunicationParam(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + `/su/pos/commsParam/deleteCommsParam`, body);
  }

  addCommunicationParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/commsParam/addCommsParam`, body);
  }





  fetchMerchant(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/merchantParam/getAllMerchantParams', body);
  }

  addMerchant(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/merchantParam/addMerchantParam', body);
  }

  updateMerchant(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/merchantParam/updateMerchantParam', body);
  }

  deleteMerchant(body: any): Observable<any> {
    // console.log('Merchant body: ', body);
    return this.http.post(baseUrl + '/su/pos/merchantParam/deleteMerchantParam', body);
  }



  fetchTerminal(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/terminalParam/getAllTerminalParams', body);
  }

  addTerminal(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/terminalParam/addTerminalParam', body);
  }

  updateTerminal(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/terminalParam/updateTerminalParam', body);
  }

  deleteTerminal(body: any): Observable<any> {
    // console.log('Terminal body: ', body);
    return this.http.post(baseUrl + '/su/pos/terminalParam/deleteTerminalParam', body);
  }





  updateInstitutionParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/instParam/updateInstParam`, body);
  }

  getOneInstitutionParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/instParam/getOneInstParam`, body);
  }

  deleteInstitutionParam(body: any): Observable<any> {
    // console.log('body: ', body);
    return this.http.post(baseUrl + `/su/pos/instParam/deleteInstParam`, body);
  }

  addInstitutionParam(body: any): Observable<any> {
    return this.http.post(baseUrl + `/su/pos/instParam/addInstParam`, body);
  }



  //authMessageLog
  getMessages(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/message/getAllMessages', body).pipe();
  }





  //intern Services
  getSidDump(body: any): Observable<any> {
    return this.http.post<any>('https://acs.bits.ma:13443/BITSWS/SidDump', body)
  }
  apiUrl: string = "https://acs.bits.ma:13443/BITSWS/";

  SidBuildMsg(body: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + `SidBuildMsg`, body)
  }

  SidDump(body: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + `SidDump`, body)
  }

  SidMsgHexTrace(body: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + `SidMsgHexTrace`, body)
  }

  getFields(): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/fieldsDefinition/getAllFieldsDefinitions', {}).pipe();
  }


  getScenarios(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/caseSetInfo/getAllCaseSetInfos', body).pipe();
  }


  getBuffer(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/webService/getBuffer', body).pipe();
  }

  getResponseMessage(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/webService/responseMessage', body).pipe();
  }

  getDataToShow(body: any): Observable<any> {
    return this.http.post(baseUrl + '/su/pos/webService/getDataToShow', body).pipe();
  }

}
