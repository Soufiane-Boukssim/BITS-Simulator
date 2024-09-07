import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageLayoutComponent } from './components/page-layout/page-layout.component';
import { UiMockupComponent } from './pages/ui-mockup/ui-mockup.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { AuthGuardService } from './services/auth-guard.service';
import { TestingExecutionComponent } from './pages/iso/testing-execution/testing-execution.component';
import { CasesScenariosComponent } from './pages/iso/cases-scenarios/cases-scenarios.component';
import { HomeComponent } from './pages/home/home.component';
import { AdministrationComponent } from './pages/administration/administration.component';
import { UserDetailsComponent } from './pages/user-details/user-details.component';
import { PageLayouAdminComponent } from './components/page-layou-admin/page-layou-admin.component';
import { GlobalParametersComponent } from './pages/iso/global-parameters/global-parameters.component';
import { GeneralOptionsComponent } from './pages/iso/general-options/general-options.component';
import { AcquirerProfileComponent } from './pages/iso/acquirer-profile/acquirer-profile.component';
import { TestingResultComponent } from './pages/iso/testing-result/testing-result.component';
import { CardProfileComponent } from './pages/atm/card-profile/card-profile.component';
import { CommunicationComponent } from './pages/atm/communication/communication.component';
import { DefinitionComponent } from './pages/atm/definition/definition.component';
import { EvenementComponent } from './pages/atm/evenement/evenement.component';
import { RejetsComponent } from './pages/atm/rejets/rejets.component';
import { LogsComponent } from './pages/atm/logs/logs.component';
import { LogsParamsComponent } from './pages/atm/logs-params/logs-params.component';
import { ScenarioComponent } from './pages/atm/scenario/scenario.component';
import { EmvDictionnaryComponent } from './pages/smart-icc/emv-dictionnary/emv-dictionnary.component';
import { EmvCardExplorerComponent } from './pages/smart-icc/emv-card-explorer/emv-card-explorer.component';

import { GeneralOptionsAdminComponent } from './pages/administration/general-options-admin/general-options-admin.component';
import { AddUserComponent } from './pages/administration/add-user/add-user.component';
import { SelfSignUpComponent } from './pages/self-sign-up/self-sign-up.component';
import { ChangePasswordComponent } from './pages/change-password/change-password.component';
import { BankComponent } from './pages/administration/bank/bank.component';
//test
import { ProfileComponent } from './pages/administration/profile/profile.component'; // Import the Profile component
import { StateComponent } from './pages/administration/state/state.component';
import { ScreenComponent } from './pages/administration/screen/screen.component';
import { FitComponent } from './pages/administration/fit/fit.component';
import { TimersComponent } from './pages/administration/timers/timers.component';
import { EmvComponent } from './pages/administration/emv/emv.component';
import { ConfigComponent } from './pages/administration/config/config.component';

//test


import {PosTestingExecutionComponent} from "./pages/pos/pos-testing-execution/pos-testing-execution.component";
import {PosCasesScenariosComponent} from "./pages/pos/pos-cases-scenarios/pos-cases-scenarios.component";
import {PosGlobalParametersComponent} from "./pages/pos/pos-global-parameters/pos-global-parameters.component";
import {PosGeneralOptionsComponent} from "./pages/pos/pos-general-options/pos-general-options.component";
import {PosAcquirerProfileComponent} from "./pages/pos/pos-acquirer-profile/pos-acquirer-profile.component";
import {PosTestingResultComponent} from "./pages/pos/pos-testing-result/pos-testing-result.component";
import {CryptoCalculatorsComponent} from "./pages/tools/crypto-calculators/crypto-calculators.component";
import {CardSecurityValuesComponent} from "./pages/tools/card-security-values/card-security-values.component";
import {PinBlockCalculatorsComponent} from "./pages/tools/pin-block-calculators/pin-block-calculators.component";
import {PinVerificationComponent} from "./pages/tools/pin-verification/pin-verification.component";
import {PinExtractComponent} from "./pages/tools/pin-extract/pin-extract.component";
import {LuhnAlgorithmComponent} from "./pages/tools/luhn-algorithm/luhn-algorithm.component";
import {Iso8583BitmapComponent} from "./pages/tools/iso8583-bitmap/iso8583-bitmap.component";
import {KeyBlockDecoderComponent} from "./pages/tools/key-block-decoder/key-block-decoder.component";
import {EmvTlvParserComponent} from "./pages/tools/emv-tlv-parser/emv-tlv-parser.component";
import {EmvTagDecodersComponent} from "./pages/tools/emv-tag-decoders/emv-tag-decoders.component";
import {EnvMiscComponent} from "./pages/tools/env-misc/env-misc.component";
import {ArqcCalculatorsComponent} from "./pages/tools/arqc-calculators/arqc-calculators.component";
import {ConvertersComponent} from "./pages/tools/converters/converters.component";
import {CliUtilsComponent} from "./pages/tools/cli-utils/cli-utils.component";
import { AnalyseAndTesterComponent } from './pages/smart-icc/analyse-and-tester/analyse-and-tester.component';
import { NewConfigurationComponent } from './pages/smart-icc/analyse-and-test/new-configuration/new-configuration.component';
import { EMVParametersComponent } from './pages/smart-icc/analyse-and-test/emvparameters/emvparameters.component';
import { TerminalConfigurationComponent } from './pages/smart-icc/analyse-and-test/terminal-configuration/terminal-configuration.component';
import { IssuesConfigurationComponent } from './pages/smart-icc/analyse-and-test/issues-configuration/issues-configuration.component';
import { TransactionConfigurationComponent } from './pages/smart-icc/analyse-and-test/transaction-configuration/transaction-configuration.component';
import { TagsComponent } from './pages/smart-icc/analyse-and-test/tags/tags.component';
import { EmvCpaAnalyserComponent } from './pages/smart-icc/emv-cpa-analyser/emv-cpa-analyser.component';
import { ParameteroptionsComponent } from './pages/iso/parameteroptions/parameteroptions.component';
import { PosParametreOptionComponent } from './pages/pos/pos-parametre-option/pos-parametre-option.component';
import {SenariosComponent} from "./pages/atm/senarios/senarios.component";
import {SenarioExecutionComponent} from "./pages/atm/senario-execution/senario-execution.component";
import {CasesSenarioComponent} from "./pages/atm/cases-senario/cases-senario.component";
import {ExecutionScriptComponent} from "./pages/atm/execution-script/execution-script.component";
import { DesCalculatorsComponent } from './pages/tools/des-calculators/des-calculators.component';

const routes: Routes = [
  {path:'user',component:PageLayoutComponent,children:[
    {path:'',component:HomeComponent},
    {path:'iso',canActivate: [AuthGuardService],children:[
      {path:'ui-component',component:UiMockupComponent},
      {path:'testing-execution',component:TestingExecutionComponent},
      {path:'cases-scenarios',component:CasesScenariosComponent},
      {path:'global-params',component:GlobalParametersComponent},
      {path:'parameter-general',component:GeneralOptionsComponent},
      {path:'parameter-options',component:ParameteroptionsComponent},
      {path:'',component:AcquirerProfileComponent},
      {path:'testing-result',component:TestingResultComponent},
    ]},
      {path:'atm',canActivate: [AuthGuardService],children:[
          {path:'',component:CardProfileComponent},
          {path:'communication',component:CommunicationComponent},
          {path: 'execution-script', component: ExecutionScriptComponent},
          {path:'definition',component:DefinitionComponent},
          {path:'evenement',component:EvenementComponent},
          {path:'rejets',component:RejetsComponent},
          {path:'operation' ,component:SenariosComponent},
          {path:'senario-execution',component:SenarioExecutionComponent},
          {path:'logs',component:LogsComponent},
          {path:'cases-senario',component:CasesSenarioComponent},
          {path:'logs-params',component:LogsParamsComponent},
          {path:'scenario',component:ScenarioComponent},
        ]},
    {path: 'icc', canActivate: [AuthGuardService],children: [
      { path: '',   redirectTo: 'emv-dictionnary', pathMatch: 'full' },
      { path: 'emv-dictionnary', component: EmvDictionnaryComponent },
      { path: 'card-explorer', component: EmvCardExplorerComponent },
      { path: 'card-analyzer', component: AnalyseAndTesterComponent },
      { path: 'cpa-analyzer', component: EmvCpaAnalyserComponent },
      { path: 'new-configuration', component: NewConfigurationComponent },
      { path: 'emv-parameters',  component: EMVParametersComponent},
      { path: 'terminal-configuration', component: TerminalConfigurationComponent },
      { path: 'issues-configuration', component: IssuesConfigurationComponent },
      { path: 'transaction-configuration', component: TransactionConfigurationComponent },
      { path: 'tags', component: TagsComponent },

    ]},
    {path:'pos',canActivate: [AuthGuardService],children:[
          {path:'ui-component',component:UiMockupComponent},
          {path:'testing-execution',component:PosTestingExecutionComponent},
          {path:'cases-scenarios',component:PosCasesScenariosComponent},
          {path:'global-params',component:PosGlobalParametersComponent},
          {path:'parameter-general',component:PosGeneralOptionsComponent},
          {path:'parameter-options',component:PosParametreOptionComponent},
          {path:'',component:PosAcquirerProfileComponent},
          {path:'testing-result',component:PosTestingResultComponent},
    ]},
      {path: 'tools',canActivate: [AuthGuardService], children: [
          { path: '', component: CryptoCalculatorsComponent },
          { path: 'card-security-values', component: CardSecurityValuesComponent },
          { path: 'pin-block-calculators', component: PinBlockCalculatorsComponent },
          { path: 'pin-verification', component: PinVerificationComponent },
          { path: 'pin-extract', component: PinExtractComponent },
          { path: 'luhn-algorithm', component: LuhnAlgorithmComponent },
          { path: 'iso-8583-bitmap', component: Iso8583BitmapComponent },
          { path: 'key-block-decoder', component: KeyBlockDecoderComponent },
          { path: 'emv-tlv-parser', component: EmvTlvParserComponent },
          { path: 'emv-tag-decoders', component: EmvTagDecodersComponent },
          { path: 'env-misc', component: EnvMiscComponent },
          { path: 'arqc-calculators', component: ArqcCalculatorsComponent },
          { path: 'converters', component: ConvertersComponent },
          { path: 'cli-utils', component: CliUtilsComponent },

          { path: 'des-calculators', component: DesCalculatorsComponent },
          // Add other routes here
        ]}
  ],canActivate:[AuthGuardService]},

  {path:'administration',component:PageLayouAdminComponent,children:[
    {path:'user-details/:id',component:UserDetailsComponent},
    {path:'add-user',component:AddUserComponent},
    {path:'management',component:AdministrationComponent},
    {path:'general-options-admin',component:GeneralOptionsAdminComponent},
    {path:'bank',component:BankComponent},
    ////////////////////////////////test
    {path:'profile',component:ProfileComponent},
    { path: 'state', component: StateComponent},    
    { path: 'screen', component: ScreenComponent},    
    { path: 'fit', component: FitComponent},    
    { path: 'timers', component: TimersComponent},    
    { path: 'emv', component: EmvComponent},    
    { path: 'config', component: ConfigComponent}    
    ////////////////////////////////test
  ],canActivate:[AuthGuardService]},
  {path:'change-password',component:ChangePasswordComponent},
  {path:'self-sign-up',component:SelfSignUpComponent},
  {path:'sign-in',component:LoginPageComponent},
  {path:'',component:LoginPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
