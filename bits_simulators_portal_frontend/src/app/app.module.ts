import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule, provideAnimations } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardHeaderComponent } from './components/dashboard-header/dashboard-header.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { PageLayoutComponent } from './components/page-layout/page-layout.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { UiMockupComponent } from './pages/ui-mockup/ui-mockup.component';
import { AlertComponent } from './components/alert/alert.component';
import { ClickOutsideDirective } from './click-outside.directive';
import { AccessManagementComponent } from './components/access-management/access-management.component';
import { CheckboxComponent } from './components/checkbox/checkbox.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { SwitchComponent } from './components/switch/switch.component';
import { TestingExecutionComponent } from './pages/iso/testing-execution/testing-execution.component';
import { TestingResultComponent } from './pages/iso/testing-result/testing-result.component';
import { CheckboxSquareComponent } from './components/checkbox-square/checkbox-square.component';
import { CasesScenariosComponent } from './pages/iso/cases-scenarios/cases-scenarios.component';
import { HomeComponent } from './pages/home/home.component';
import { AdministrationComponent } from './pages/administration/administration.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { FieldComponent } from './components/field/field.component';
import { JwtInterceptor } from './helper/jwt.interceptor';
import { UserDetailsComponent } from './pages/user-details/user-details.component';
import { PageLayouAdminComponent } from './components/page-layou-admin/page-layou-admin.component';
import { SideBarAdminComponent } from './components/side-bar-admin/side-bar-admin.component';
import { GlobalParametersComponent } from './pages/iso/global-parameters/global-parameters.component';
import { GeneralOptionsComponent } from './pages/iso/general-options/general-options.component';
import { AcquirerProfileComponent } from './pages/iso/acquirer-profile/acquirer-profile.component';
import { CardProfileComponent } from './pages/atm/card-profile/card-profile.component';
import { CommunicationComponent } from './pages/atm/communication/communication.component';
import { DefinitionComponent } from './pages/atm/definition/definition.component';
import { EvenementComponent } from './pages/atm/evenement/evenement.component';
import { IpAddressMaskDirective } from './ip-address-mask.directive';
import { RejetsComponent } from './pages/atm/rejets/rejets.component';
import { LogsComponent } from './pages/atm/logs/logs.component';
import { LogsParamsComponent } from './pages/atm/logs-params/logs-params.component';
import { ScenarioComponent } from './pages/atm/scenario/scenario.component';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { EmvDictionnaryComponent } from './pages/smart-icc/emv-dictionnary/emv-dictionnary.component';
import { EmvCardExplorerComponent } from './pages/smart-icc/emv-card-explorer/emv-card-explorer.component';
import { EmptyDataPipe } from './pipes/empty-data.pipe';
import { SearchPipe } from './pipes/search.pipe';
import { ValueBuilderComponent } from './pages/iso/cases-scenarios/value-builder/value-builder.component';
import { ValueDefinerComponent } from './pages/iso/cases-scenarios/value-definer/value-definer.component';
import { CasesManagementComponent } from './pages/iso/cases-scenarios/cases-management/cases-management.component';
import { SubFieldNamePipe } from './pipes/sub-field-name.pipe';
import { GeneralOptionsAdminComponent } from './pages/administration/general-options-admin/general-options-admin.component';
import { AddUserComponent } from './pages/administration/add-user/add-user.component';
import { SelfSignUpComponent } from './pages/self-sign-up/self-sign-up.component';
import { ChangePasswordComponent } from './pages/change-password/change-password.component';
import { DatePipe } from '@angular/common';
import { BankComponent } from './pages/administration/bank/bank.component';


import { PosAcquirerProfileComponent } from './pages/pos/pos-acquirer-profile/pos-acquirer-profile.component';
import { PosCasesScenariosComponent } from './pages/pos/pos-cases-scenarios/pos-cases-scenarios.component';
import { PosGeneralOptionsComponent } from './pages/pos/pos-general-options/pos-general-options.component';
import { PosGlobalParametersComponent } from './pages/pos/pos-global-parameters/pos-global-parameters.component';
import { PosTestingExecutionComponent } from './pages/pos/pos-testing-execution/pos-testing-execution.component';
import { PosTestingResultComponent } from './pages/pos/pos-testing-result/pos-testing-result.component';
import { PosCasesManagementComponent } from './pages/pos/pos-cases-scenarios/pos-cases-management/pos-cases-management.component';
import { PosValueBuilderComponent } from './pages/pos/pos-cases-scenarios/pos-value-builder/pos-value-builder.component';
import { PosValueDefinerComponent } from './pages/pos/pos-cases-scenarios/pos-value-definer/pos-value-definer.component';
import { CryptoCalculatorsComponent } from './pages/tools/crypto-calculators/crypto-calculators.component';
import { CardSecurityValuesComponent } from './pages/tools/card-security-values/card-security-values.component';
import { PinBlockCalculatorsComponent } from './pages/tools/pin-block-calculators/pin-block-calculators.component';
import { PinVerificationComponent } from './pages/tools/pin-verification/pin-verification.component';
import { PinExtractComponent } from './pages/tools/pin-extract/pin-extract.component';
import { LuhnAlgorithmComponent } from './pages/tools/luhn-algorithm/luhn-algorithm.component';
import { Iso8583BitmapComponent } from './pages/tools/iso8583-bitmap/iso8583-bitmap.component';
import { KeyBlockDecoderComponent } from './pages/tools/key-block-decoder/key-block-decoder.component';
import { EmvTlvParserComponent } from './pages/tools/emv-tlv-parser/emv-tlv-parser.component';
import { EmvTagDecodersComponent } from './pages/tools/emv-tag-decoders/emv-tag-decoders.component';
import { EnvMiscComponent } from './pages/tools/env-misc/env-misc.component';
import { ArqcCalculatorsComponent } from './pages/tools/arqc-calculators/arqc-calculators.component';
import { ConvertersComponent } from './pages/tools/converters/converters.component';
import { CliUtilsComponent } from './pages/tools/cli-utils/cli-utils.component';
import {MatIconModule} from "@angular/material/icon";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTreeModule} from '@angular/material/tree';
import {MatButtonModule} from '@angular/material/button';
import { NavbarComponent } from './pages/smart-icc/navbar/navbar.component';
import { MenuComponent } from './pages/smart-icc/analyse-and-test/menu/menu.component';
import { AnalyseAndTesterComponent } from './pages/smart-icc/analyse-and-tester/analyse-and-tester.component';
import { NewConfigurationComponent } from './pages/smart-icc/analyse-and-test/new-configuration/new-configuration.component';
import { EMVParametersComponent } from './pages/smart-icc/analyse-and-test/emvparameters/emvparameters.component';
import { TerminalConfigurationComponent } from './pages/smart-icc/analyse-and-test/terminal-configuration/terminal-configuration.component';
import { IssuesConfigurationComponent } from './pages/smart-icc/analyse-and-test/issues-configuration/issues-configuration.component';
import { TransactionConfigurationComponent } from './pages/smart-icc/analyse-and-test/transaction-configuration/transaction-configuration.component';
import { TagsComponent } from './pages/smart-icc/analyse-and-test/tags/tags.component';
import { ShowTvrComponent } from './pages/smart-icc/analyse-and-test/show-tvr/show-tvr.component';
import { ShowTsiComponent } from './pages/smart-icc/analyse-and-test/show-tsi/show-tsi.component';
import { ShowCvrComponent } from './pages/smart-icc/analyse-and-test/show-cvr/show-cvr.component';
import { CommandeTracerComponent } from './pages/smart-icc/analyse-and-test/commande-tracer/commande-tracer.component';
import { TableComponent } from './components/table/table.component';
import { EmvCpaAnalyserComponent } from './pages/smart-icc/emv-cpa-analyser/emv-cpa-analyser.component';
import { CpaResultComponent } from './pages/smart-icc/cpa-result/cpa-result.component';
import { CpaOptionComponent } from './pages/smart-icc/cpa-option/cpa-option.component';
import { CpaLaunchComponent } from './pages/smart-icc/cpa-launch/cpa-launch.component';


import {MatExpansionModule} from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';

import {MatTableModule} from '@angular/material/table';

import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { ParameteroptionsComponent } from './pages/iso/parameteroptions/parameteroptions.component';
import { PosParametreOptionComponent } from './pages/pos/pos-parametre-option/pos-parametre-option.component';
import {CasesSenarioComponent} from "./pages/atm/cases-senario/cases-senario.component";
import {SenarioExecutionComponent} from "./pages/atm/senario-execution/senario-execution.component";
import {SenariosComponent} from "./pages/atm/senarios/senarios.component";
import {AtmManagementComponent} from "./pages/atm/senarios/atm-management/atm-management.component";
import { ExecutionScriptComponent } from './pages/atm/execution-script/execution-script.component';
import { DesCalculatorsComponent } from './pages/tools/des-calculators/des-calculators.component';
import { AuthService } from './services/auth.service';
import { ProfileComponent } from './pages/administration/profile/profile.component';
import { StateComponent } from './pages/administration/state/state.component';
import { ScreenComponent } from './pages/administration/screen/screen.component';
import { FitComponent } from './pages/administration/fit/fit.component';
import { TimersComponent } from './pages/administration/timers/timers.component';
import { EmvComponent } from './pages/administration/emv/emv.component';
import { ConfigComponent } from './pages/administration/config/config.component';
@NgModule({
  declarations: [
    AppComponent,
    DashboardHeaderComponent,
    SidebarComponent,
    PageLayoutComponent,
    LoginPageComponent,
    UiMockupComponent,
    AlertComponent,
    ClickOutsideDirective,
    AccessManagementComponent,
    CheckboxComponent,
    SwitchComponent,
    TestingExecutionComponent,
    TestingResultComponent,
    CheckboxSquareComponent,
    CasesScenariosComponent,
    HomeComponent,
    AdministrationComponent,
    FieldComponent,
    UserDetailsComponent,
    CasesSenarioComponent,
    SenarioExecutionComponent,
    SenariosComponent,
    AtmManagementComponent,
    PageLayouAdminComponent,
    SideBarAdminComponent,
    GlobalParametersComponent,
    GeneralOptionsComponent,
    AcquirerProfileComponent,
    CardProfileComponent,
    CommunicationComponent,
    DefinitionComponent,
    EvenementComponent,
    IpAddressMaskDirective,
    RejetsComponent,
    LogsComponent,
    LogsParamsComponent,
    ScenarioComponent,
    EmvDictionnaryComponent,
    EmvCardExplorerComponent,
    EmptyDataPipe,
    SearchPipe,
    ValueBuilderComponent,
    ValueDefinerComponent,
    CasesManagementComponent,
    SubFieldNamePipe,
    GeneralOptionsAdminComponent,
    AddUserComponent,
    SelfSignUpComponent,
    ChangePasswordComponent,
    BankComponent,
    PosAcquirerProfileComponent,
    PosCasesScenariosComponent,
    PosGeneralOptionsComponent,
    PosGlobalParametersComponent,
    PosTestingExecutionComponent,
    PosTestingResultComponent,
    PosCasesManagementComponent,
    PosValueBuilderComponent,
    PosValueDefinerComponent,
    CryptoCalculatorsComponent,
    CardSecurityValuesComponent,
    PinBlockCalculatorsComponent,
    PinVerificationComponent,
    PinExtractComponent,
    LuhnAlgorithmComponent,
    Iso8583BitmapComponent,
    KeyBlockDecoderComponent,
    EmvTlvParserComponent,
    EmvTagDecodersComponent,
    EnvMiscComponent,
    ArqcCalculatorsComponent,
    ConvertersComponent,
    CliUtilsComponent,
    SubFieldNamePipe,
    NavbarComponent,
    NavbarComponent,
    MenuComponent,
    AnalyseAndTesterComponent,
    NewConfigurationComponent,
    EMVParametersComponent,
    TerminalConfigurationComponent,
    IssuesConfigurationComponent,
    TransactionConfigurationComponent,
    TagsComponent,
    ShowTvrComponent,
    ShowTsiComponent,
    ShowCvrComponent,
    CommandeTracerComponent,
    TableComponent,
    EmvCpaAnalyserComponent,
    CpaResultComponent,
    CpaOptionComponent,
    CpaLaunchComponent,
    ParameteroptionsComponent,
    PosParametreOptionComponent,
    DesCalculatorsComponent,
    AlertComponent,
    ExecutionScriptComponent,
    ProfileComponent,
    StateComponent,
    ScreenComponent,
    FitComponent,
    TimersComponent,
    EmvComponent,
    ConfigComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        deps: [HttpClient],
        useFactory: HttpLoaderFactory
      }
    }),
    MatIconModule,
    MatTooltipModule,
    MatCheckboxModule,
    MatTreeModule,
    MatButtonModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatTableModule,
    MatProgressSpinnerModule
  ],
  // providers: [provideAnimations(), {
  //   provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true
  // },DatePipe],
  providers: [
    provideAnimations(),
    AuthService,
    DatePipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}