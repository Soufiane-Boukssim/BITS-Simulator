package com.simulator.pos.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserManagement;
import com.simulator.entities.pos.*;
import com.simulator.externalTools.MiscTools;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.cryptoKey.CryproKeyService.DESSun;
import com.simulator.iso.cryptoKey.misc.HexTools;
import com.simulator.iso.model.ExecutionTable;
import com.simulator.iso.model.IsoFields;
import com.simulator.iso.services.SpecialValues.HandleSpecialValuesMasterCard;
import com.simulator.iso.services.SpecialValues.HandleSpecialValuesSid;
import com.simulator.iso.services.SpecialValues.HandleSpecialValuesVisa;
import com.simulator.pos.model.PosDataToShow;
import com.simulator.pos.model.PosField11Storage;
import com.simulator.pos.model.PosIsoFields;
import com.simulator.pos.model.PosResponseModel;
import com.simulator.repository.pos.*;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.simulator.config.GlobalVars.isDouble;


@Service
public  class PosWebSeeviceIsoService {
    private final Logger logger = LogManager.getLogger(PosWebSeeviceIsoService.class);
    @Autowired
    GlobalVars globalVars;
    @Autowired
    PosAuthoMsgLogRepository authoMsgLogRepository;
    @Autowired
    PosFieldsDefinitionRepository fieldDefinitionRepo;
    @Autowired
    PosCasesRepository casesRepository;


    @Autowired
    private PosField11Storage field11Storage;



    @Autowired
    PosCardRepository cardRepository;
    @Autowired
    PosMerchantRepository merchantRepository;
    @Autowired
    PosTerminalRepository terminalRepository;
    @Autowired
    PosInstRepository instRepository;
    @Autowired
    PosIccRepository iccRepository;

    @Autowired
    HandleSpecialValuesSid handleSpecialValuesSid;

    @Autowired
    HandleSpecialValuesVisa handleSpecialValuesVisa;

    @Autowired
    HandleSpecialValuesMasterCard handleSpecialValuesMasterCard;
    public static PosCasesDefinition caseDefRequest=new PosCasesDefinition();
    public static PosCasesDefinition caseDefResponse=new PosCasesDefinition();


  

    private Object toBufferIsoApi(String jsonString ,String protocolCode ,String msg ) {

        logger.info("protocolCode  toBufferIsoApi webService ==>"+protocolCode);
        logger.info("msg  toBufferIsoApi webService ===>"+msg);

        HttpClient secureHttpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(
                        SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
                ));

        WebClient.Builder builder = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(secureHttpClient));


        GlobalVars globalVars=new GlobalVars();
        logger.info("get buffer for: " + jsonString);
        Object response = null;
        if ("PO".equals(protocolCode)  ) {

//            String requestBody = jsonString + msg;
//            response = builder.baseUrl(GlobalVars.baseUrlTest)
            response = builder.baseUrl(GlobalVars.baseUrlTest)
                    .build()
                    .post()
                    .uri(GlobalVars.sidBuildMsg)
                    .headers(httpHeaders -> httpHeaders.addAll(globalVars.getHeaders1())) // Set headers go to global Vars to see which headers here
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(jsonString)) // Send JSON in the request body
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }else {
            logger.info(" add service de cette protocol  ["+protocolCode+"] ");
            return "";
        }
        return response;
    }

    private Object toFieldIsoApi(String buffer,String protocolCode) {



        HttpClient secureHttpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(
                        SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
                ));

        WebClient.Builder builder = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(secureHttpClient));


        GlobalVars globalVars=new GlobalVars();
        String requestBody = "{\"messageIn\":"+buffer+"}";
        logger.info("get buffer for: " + requestBody);
        if ("PO".equals(protocolCode) ) {
            String response = builder.baseUrl(GlobalVars.baseUrlTest)
                    .build()
                    .post()
                    .uri(GlobalVars.SidDump)
                    .headers(httpHeaders -> httpHeaders.addAll(globalVars.getHeaders1())) // Set headers go to global Vars to see which headers here
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(requestBody)) // Send JSON in the request body
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return response;
        }  else {
            return "";
        }



    }
    public ResponseApiJson<?> getBuffer(PosCasesDefinition casesDefinition,String protocolCode,String msg) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        logger.info("message"+msg);
        logger.info("casesDefinition " + casesDefinition);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        String userCode = userM.get().getUserCode();
        field11Storage.removeField11(userCode);

        try {
            setField(casesDefinition, fieldDefinitionRepo, globalVars,protocolCode,userCode);
            PosIsoFields isoFields=new PosIsoFields();
            casesDefinition.fillIsoFields(isoFields);

            caseDefRequest =casesDefinition;
            logger.info("isoFields.toJson==>",isoFields.toJson() );

            Object object = toBufferIsoApi(isoFields.toJson() ,protocolCode,msg);
            logger.info("Obj------- "+object);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all accountsuccesfully  !", object);

        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllAccountDefs please check with your provider !");

        }


    }

    public void setField(PosCasesDefinition casesDefinition, PosFieldsDefinitionRepository fieldDefinitionRepo, GlobalVars globalVars,String protocol,String userCode) {
        String field11="";
        casesDefinition.setFields(casesDefinition.getFields().stream().map(field -> {

            PosFieldsDefinition f = fieldDefinitionRepo.findById(field.getFieldDef().getId()).get();
            PosFieldValue fieldValue = new PosFieldValue();
            fieldValue.setCaseDef(casesDefinition);
            fieldValue.setFieldDef(f);
            fieldValue.setValue(field.getValue());
            //logger.info("i'm here");
            String newValue = fieldValue.getValue();


            if ("Generate Value".equals(newValue) || "From Card Profile".equals(newValue)
                    || "From Response".equals(newValue)
                    || "From Request".equals(newValue)
                    || "From Merchant".equals(newValue)
                    || "From Terminal".equals(newValue)) {
                logger.info("test field11"+field11 + ": " + field.getFieldDef().getId().getFieldId());
                try {
//                    if ("SS".equals(protocol) || "CS".equals(protocol) || "GS".equals(protocol) || "US".equals(protocol) ){
//                        newValue = handleSpecialValuesSid.handleSpecialValues(newValue, String.valueOf(field.getFieldDef().getId().getFieldId()),field11,casesDefinition,userCode);
//
//                    }else if("VB".equals(protocol) || "VS".equals(protocol)){
//                        newValue = handleSpecialValuesVisa.handleSpecialValues(newValue, String.valueOf(field.getFieldDef().getId().getFieldId()),field11,casesDefinition,userCode);
//
//                    } else if ("MC".equals(protocol) || "MD".equals(protocol)) {
//                        newValue = handleSpecialValuesMasterCard.handleSpecialValues(newValue, String.valueOf(field.getFieldDef().getId().getFieldId()),field11,casesDefinition,userCode);
//
//                    }

                    newValue = handleSpecialValues(newValue, String.valueOf(field.getFieldDef().getId().getFieldId()),field11,casesDefinition,protocol);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                fieldValue.setValue(newValue);
            }else
                fieldValue.setValue(field.getValue());
            IntStream.rangeClosed(4, 6).filter(fieldId -> fieldValue.getFieldDef().getId().getFieldId() == fieldId).filter(fieldId -> isDouble(fieldValue.getValue())).forEach(fieldId -> fieldValue.setValue(globalVars.realToIsoAmountPOS(casesDefinition, fieldId, Double.parseDouble(fieldValue.getValue()))));

            return fieldValue;
        }).collect(Collectors.toList()));
    }

    public ResponseApiJson<?> getFields(String s){
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {

            Object objectReq;


            objectReq = toFieldIsoApi(s,"SS");
            IsoFields isoFieldsReq = new IsoFields();
            isoFieldsReq.fromJson(getJsonFields(objectReq));

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get data  !", isoFieldsReq);

        } catch (Exception e) {
            logger.info("Exception of this::getFields " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getFields please check with your provider !");

        }
    }

    public ResponseApiJson<?> getDataShow(PosDataToShow dataToShow , String protocolCode , String bankCode) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        logger.info("dataToShow==>"+dataToShow);
        logger.info("dataToShow getCaseSetId==>{}", dataToShow.getId().getCaseNo());
        logger.info("dataToShow getCaseSetId==>{}", dataToShow.getScenario().getCaseSetId());
        try {
            Object objectRes;
            Object objectReq;
            PosAuthoMsgLog authoMsgLog = new PosAuthoMsgLog();

            objectReq = toFieldIsoApi(dataToShow.getReqFormater(),protocolCode);

            logger.info("objectReq===>"+objectReq);
//            logger.info("dataToShow===>"+dataToShow);

            IsoFields isoFieldsReq = new IsoFields();
            dataToShow.getReqNoFormater().fromJson(getJsonFields(objectReq));
            authoMsgLog.setMtiReq(dataToShow.getReqNoFormater().getField("MTI"));
            authoMsgLog.setAutBufferReq(dataToShow.getReqFormater());
            authoMsgLog.setSystemMode("A");
            authoMsgLog.setAutCaseNo(dataToShow.getId().getCaseNo());
            authoMsgLog.setAutCaseSetId(dataToShow.getScenario().getCaseSetId());
            PosCardProfileId cardId = new PosCardProfileId(dataToShow.getReqNoFormater().getField("2"), protocolCode, bankCode);
            PosCardProfile cardProfile = cardRepository.findById(cardId).orElse(null);
            logger.info("cardProfile ==>"+cardProfile);
            if (cardProfile == null) {
                authoMsgLog.setAutCaseCardPrf("");
            }else {
                authoMsgLog.setAutCaseCardPrf(cardProfile.getCardDesc());
            }
            authoMsgLog.setAutCaseMrcPrf("");
            authoMsgLog.setAutCaseTermPrf("");
            authoMsgLog.setCheckVerif("");
            if (!Objects.equals(dataToShow.getResFormater(), "-1")) {
                objectRes = toFieldIsoApi(dataToShow.getResFormater(),protocolCode);

                IsoFields isoFieldsRes = new IsoFields();
                dataToShow.getResNoFormater().fromJson(getJsonFields(objectRes));

                logger.info("setAutActionCode==>"+dataToShow.getResNoFormater().getField("39"));

                // Create and populate AuthoMsgLog entity

                // Example of setting values. Replace with actual field mappings
                authoMsgLog.setMtiRes(dataToShow.getResNoFormater().getField("MTI"));

                authoMsgLog.setAutBufferRep(dataToShow.getResFormater());
                authoMsgLog.setAutActionCode(dataToShow.getResNoFormater().getField("39"));
                authoMsgLog.setAutReference(dataToShow.getResNoFormater().getField("37"));
                authoMsgLog.setAutStan(dataToShow.getResNoFormater().getField("11"));
                authoMsgLog.setAutUser(dataToShow.getResNoFormater().getField("2"));
//                    authoMsgLog.setAutCaseCardPrf(dataToShow.getResNoFormater().getField("4"));
                // Set other fields...
            }
            else{
                authoMsgLog.setMtiRes("-1");

                authoMsgLog.setAutBufferRep("-1");
                authoMsgLog.setAutActionCode("-1");
                authoMsgLog.setAutReference("-1");
                authoMsgLog.setAutStan("-1");
            }
            // Create and set AuthoMsgLogId (assuming it's needed)
            Date date=new Date();
            PosAuthoMsgLogId id = new PosAuthoMsgLogId(date, protocolCode, bankCode); // Modify as needed
            authoMsgLog.setAuthoMsgLogId(id);

            // Save the entity to the database
            authoMsgLogRepository.save(authoMsgLog);
            ExecutionTable executionTable = new ExecutionTable(date,authoMsgLog.getAutReference(),authoMsgLog.getMtiReq(), authoMsgLog.getAutCaseNo(), authoMsgLog.getAutCaseCardPrf(), authoMsgLog.getAutBufferReq(), authoMsgLog.getAutBufferRep(),authoMsgLog.getMtiRes());

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get data  !", executionTable);

        } catch (Exception e) {
            logger.info("Exception of this::getDataShow " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getDataShow please check with your provider !");

        }


    }

    public ResponseApiJson<?> responseMessage(PosResponseModel responseModel, String protocolCode, String msg) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosCasesDefinition> casesDefinitionIn = casesRepository.findById(responseModel.getId());
            responseModel.getId().setCaseType('O');
            Optional<PosCasesDefinition> casesDefinitionOut = casesRepository.findById(responseModel.getId());
            ResponseApiJson<?> apiJson = getBuffer(casesDefinitionOut.get() ,protocolCode,msg);
            if (apiJson.getRespCode().equals("000")) {
                Object object = apiJson.getResult();
                logger.info("--obj--"+object);
                JSONObject jsonObject = new JSONObject(object.toString());

                // Extract the messageOut value
                String messageOutValue = jsonObject.getString("messageOut");

                // Set the extracted value to the responseModel's messageOut
                responseModel.setMessageOut(messageOutValue);
                System.out.println("responseModel "+responseModel);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get data  !"+responseModel);
            }
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get data  !");
        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllAccountDefs please check with your provider !");

        }


    }
    public String getJsonFields(Object o){
        // Convert Object to JSON String
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(o);

        // Check if the jsonResponse is a JSON string of a JSON object
        if (jsonResponse.startsWith("\"") && jsonResponse.endsWith("\"")) {
            // Remove the leading and trailing quotes
            jsonResponse = jsonResponse.substring(1, jsonResponse.length() - 1);
            // Unescape the JSON string
            jsonResponse = jsonResponse.replace("\\\"", "\"");
        }

        // Parse the JSON string to a JsonObject
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        // Extract the 'fields' part as a JSON string
        return jsonObject.get("fields").toString();
    }



    public  PosCardProfile getCardProfile(String cardNumber, String cardProtocol, String bankCode){
        try {
            PosCardProfileId cardProfileId = new PosCardProfileId(cardNumber,cardProtocol,bankCode);

            Optional<PosCardProfile> cardProfile = cardRepository.findById(cardProfileId);
            return cardProfile.orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public  PosTerminalParam getTermProfile(String terminalNumber, String cardProtocol, String bankCode){
        try {
            PosTerminalParamId terminalParamId = new PosTerminalParamId(terminalNumber,cardProtocol,bankCode);
            Optional<PosTerminalParam> terminalParam = terminalRepository.findById(terminalParamId);
            return terminalParam.orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public  PosMerchantParam getMerchantProfile(String merchantNumber, String cardProtocol, String bankCode){
        try {
            PosMerchantParamId merchantParamId = new PosMerchantParamId(merchantNumber,cardProtocol,bankCode);
            Optional<PosMerchantParam> merchantParam = merchantRepository.findById(merchantParamId);
            return merchantParam.orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public  PosInstParam getInstParam( String instProtocol, String bankCode){
        try {
            PosInstParamId instParamId = new PosInstParamId(instProtocol,bankCode);
            Optional<PosInstParam> instParam = instRepository.findById(instParamId);
            return instParam.orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<PosIccProfile> getIccProfile(String protocol, String iccProfile, String bankCode){
        try {

            return iccRepository.findById_IcPrfAndId_IccProtocolAndId_BankCode(iccProfile,protocol,bankCode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    private String handleSpecialValues(String value, String fieldNumber, String field11, PosCasesDefinition casesDefinition,String protocol) throws Exception {
        this.logger.info("field number: "+fieldNumber +"value: "+value);
        switch (value){
            case "Generate Value" :
                value = handleGenerateValue(fieldNumber, casesDefinition, field11,protocol);
                break;
            case "From Card Profile" :
                value = handleFromCardProfile(fieldNumber,casesDefinition);
                break;
            case "From Response" :
                value = handleFromResponse(fieldNumber,casesDefinition);
                break;
            case "From Request" :
                value = handleFromRequest(fieldNumber,casesDefinition);
                break;
            case "From Merchant" :
                value = handleFromMerchant(fieldNumber,casesDefinition);
                break;
            case "From Terminal" :
                value = handleFromTerminal(fieldNumber,casesDefinition);
                break;
            default :
                break;
        }

        return value;
    }

    private  String handleFromResponse(String fieldNumber, PosCasesDefinition casesDefinition) {

        if (caseDefResponse.getId()!=null)
            if (casesDefinition.getFields().stream()
                    .anyMatch(field -> field.getFieldDef().getId().getFieldId() == Integer.parseInt(fieldNumber))){
                return casesDefinition.getFields().stream()
                        .filter(field -> field.getFieldDef().getId().getFieldId() == Integer.parseInt(fieldNumber))
                        .findFirst().get().getValue();
            }
            else
                return "there is no "+fieldNumber+" in Response case";
        else
            return "I don't have Response transaction";
    }

    private String handleFromCardProfile(String fieldNumber, PosCasesDefinition casesDefinition) {
        Date date = new Date();

        logger.info("casesDefinition ==>"+casesDefinition);
        switch (fieldNumber) {
            case "2":
                if (casesDefinition.getCaseCardRef()!=null || !casesDefinition.getCaseCardRef().isEmpty())
                    return this.getCardProfile(casesDefinition.getCaseCardRef(),casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode()).getId().getCardNo();
                else return "1234567891234567";

            case "14":
                if (casesDefinition.getCaseCardRef()!=null || !casesDefinition.getCaseCardRef().isEmpty()) {
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyMM");
                    try {
                        Date dat = inputFormat.parse(this.getCardProfile(casesDefinition.getCaseCardRef(), casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode()).getCardExpiry());

                        return outputFormat.format(dat);


                    } catch (ParseException e) {
                        e.printStackTrace();
                        return new SimpleDateFormat("yyMM").format(date);
                    }


                }
                else
                    return new SimpleDateFormat("yyMM").format(date);

            case "35":
                if (casesDefinition.getCaseCardRef()!=null || !casesDefinition.getCaseCardRef().isEmpty()) {
                    //logger.info("35 : "+ this.getCardProfile(casesDefinition.getCaseCardRef(), casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode()).getTrack2().toString());
                    return this.getCardProfile(casesDefinition.getCaseCardRef(), casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode()).getTrack2().toString();
                }
                else
                    return "1234567891234567=00000000000000000000";

            case "52" :
                if (casesDefinition.getCaseCardRef()!=null || !casesDefinition.getCaseCardRef().isEmpty()) {
                    PosCardProfile card = this.getCardProfile(casesDefinition.getCaseCardRef(), casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode());
                    PosInstParam instParam = getInstParam(casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode());
                    try {

                        return HexTools.generateEncyptedPINBlock(card.getPIN(),"01",card.getId().getCardNo().substring(3,15),instParam.getPinKey());


                    } catch (ParseException e) {
                        e.printStackTrace();
                        return new SimpleDateFormat("yyMM").format(date);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                }
                else
                    return new SimpleDateFormat("yyMM").format(date);

            default:
                return "";
        }
    }

    private  String handleFromRequest(String fieldNumber, PosCasesDefinition casesDefinition) {
        if (caseDefRequest.getId()!=null)
            if (casesDefinition.getFields().stream()
                    .anyMatch(field -> field.getFieldDef().getId().getFieldId() == Integer.parseInt(fieldNumber))){
                return casesDefinition.getFields().stream()
                        .filter(field -> field.getFieldDef().getId().getFieldId() == Integer.parseInt(fieldNumber))
                        .findFirst().get().getValue();
            }
            else
                return "there is no "+fieldNumber+" in Request case";
        else
            return "I don't have Request transaction";
    }

    private  String handleFromMerchant(String fieldNumber, PosCasesDefinition casesDefinition) {
        PosMerchantParam merchantParam = getMerchantProfile(casesDefinition.getCaseMerchantPrf(),casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode());
        switch (fieldNumber) {
            case "18":
                return merchantParam.getMcc();
            case "19" :
                return  merchantParam.getCountryCode();
            case "42" :
                return merchantParam.getId().getMerCode();

            default:
                return "";
        }
    }

    private  String handleFromTerminal(String fieldNumber, PosCasesDefinition casesDefinition) throws Exception {
        PosInstParam instParam = getInstParam(casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode());
        DESSun desSun = new DESSun();
        switch (fieldNumber) {
            case "41":
                return getTermProfile(casesDefinition.getCaseTerminalPrf(),casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode()).getId().getTermCode();

            case "43" :
                return getFirst40Chars(getTermProfile(casesDefinition.getCaseTerminalPrf(),casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode()).getTermLocation());

            case "48" :
                if (casesDefinition.getFields().stream()
                        .filter(field -> field.getFieldDef().getId().getFieldId() == 0)
                        .findFirst().get().getValue().equals("1804")&& casesDefinition.getFields().stream()
                        .anyMatch(field -> field.getFieldDef().getId().getFieldId() == 24)){
                    if(casesDefinition.getFields().stream()
                            .filter(field -> field.getFieldDef().getId().getFieldId() == 24)
                            .findFirst().get().getValue().equals("811")){
                        String s=desSun.encrypt(instParam.getMasterKey(),instParam.getPinKey());
                        return "P16"+String.format("%03d", s.length())+s;

                    }
                    if (casesDefinition.getFields().stream()
                            .filter(field -> field.getFieldDef().getId().getFieldId() == 24)
                            .findFirst().get().getValue().equals("899")){
                        String s=desSun.encrypt(instParam.getMasterKey(),instParam.getMacKey());
                        return "P10"+String.format("%03d", s.length())+s;
                    }
                }else {
                    return "P9500221";
                }

            default:
                return "";
        }

    }
    public static String getFirst40Chars(String input) {
        if (input.length() > 40) {
            return input.substring(0, 40);
        } else {
            StringBuilder sb = new StringBuilder(input);
            while (sb.length() < 40) {
                sb.append('_');
            }
            return sb.toString();
        }
    }

    private String handleGenerateValue(String fieldNumber, PosCasesDefinition casesDefinition, String field11,String protocol) throws InterruptedException {
        Date date = new Date();
        Random rand = new Random();
        Optional<PosFieldValue> fieldValueOptional;
        StringBuilder field55= new StringBuilder();
        String field12,field32="";
        MiscTools miscTools = new MiscTools();
        switch (fieldNumber){
            case "5", "6":
                fieldValueOptional = casesDefinition.getFields().stream()
                        .filter(field -> field.getFieldDef().getId().getFieldId() == 4)
                        .findFirst();
                if (fieldValueOptional.isPresent())
                    return fieldValueOptional.get().getValue();
                else
                    return  "000000000000";

            case "7" :
                return new SimpleDateFormat("yyMMddHHmm").format(date);

            case "8" :
                String data = new SimpleDateFormat("yyMMddHHmm").format(date);

            case "11" :
                field11 = String.format("%06d", rand.nextInt(999999));
                return field11;

            case "12" :
                logger.info("filed 12 prtocole ====>"+protocol);
                if ("VB".equals(protocol)){
                    return new SimpleDateFormat("HHmmss").format(date);
                }else {
                    return new SimpleDateFormat("yyMMddHHmmss").format(date);
                }


            case "13" :
                return new SimpleDateFormat("yyMM").format(date);

            case "14" :
                return handleFromCardProfile("14",casesDefinition);

            case "15","17" :

                return new SimpleDateFormat("yyMMdd").format(date);

            case "16" :
                LocalDateTime now = LocalDateTime.now();
                return String.format("%02d%02d", now.getMonthValue(), now.getMonthValue());

            case "32","99" :
                if(getInstParam(casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode())!=null)
                    return Objects.requireNonNull(getInstParam(casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode())).getAcqCode();
                else
                    return "010101";
            case "33" :
                if(getInstParam(casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode())!=null)
                    return Objects.requireNonNull(getInstParam(casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode())).getFwdCode();
                else
                    return "010101";

            case "37" :
                return new SimpleDateFormat("yyMMdd").format(date)+field11;

            case "38" :
                return miscTools.genAuthNumber();

            case "48" :


            case "50" :
                if(getInstParam(casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode())!=null)
                    return Objects.requireNonNull(getInstParam(casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode())).getInstSettCurr();
                else
                    return "840";

            case "51" :
                fieldValueOptional = casesDefinition.getFields().stream()
                        .filter(field -> field.getFieldDef().getId().getFieldId() == 49)
                        .findFirst();
                if (fieldValueOptional.isPresent())
                    return fieldValueOptional.get().getValue();
                else
                    return  "840";

            case "52" :
                return handleFromCardProfile("52",casesDefinition);

            case "55" :
                if (!Objects.requireNonNull(getIccProfile(casesDefinition.getId().getCaseProtocole(), Objects.requireNonNull(this.getCardProfile(casesDefinition.getCaseCardRef(), casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode())).getIcProfile(), casesDefinition.getId().getBankCode())).isEmpty()) {
                    for (PosIccProfile iccProfile : Objects.requireNonNull(getIccProfile(casesDefinition.getId().getCaseProtocole(), Objects.requireNonNull(this.getCardProfile(casesDefinition.getCaseCardRef(), casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode())).getIcProfile(), casesDefinition.getId().getBankCode())) ){
                        String key = iccProfile.getId().getIcTag();
                        String value = iccProfile.getIcTagValue();
                        int length = value.length() / 2; // Each byte is represented by two hex characters
                        String lengthHex = String.format("%02X", length);

                        field55.append(key).append(lengthHex).append(value);

                    }
                    return field55.toString();
                }else
                    return "";

            case "56" :
                field12 = casesDefinition.getFields().stream()
                        .filter(field -> field.getFieldDef().getId().getFieldId() == 12)
                        .findFirst().get().getValue();
                field32 = casesDefinition.getFields().stream()
                        .filter(field -> field.getFieldDef().getId().getFieldId() == 32)
                        .findFirst().get().getValue();
                return String.format("%04d", casesDefinition.getCaseMti()) +
                        field11+field12+field32;

            case "73" :
                return new SimpleDateFormat("yyMMdd").format(date);

            case "128" :
                return "00000000";

            default:
                return "";

        }

    }

    private String handleInstitution(String fieldNumber, PosCasesDefinition casesDefinition) {
        switch (fieldNumber) {
            case "41":
                return getTermProfile(casesDefinition.getCaseTerminalPrf(),casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode()).getId().getTermCode();

            default:
                return "";
        }

    }



    //// getSidDump



    public  ResponseApiJson<?> getSidDump(String messageIn ,String protocolCode){
        String idRequest = "Dump" + new Date().getTime() + (Math.random() * 9999);
        logger.info("getSidDump===>"+messageIn);

        try {

            Object objectReq;


            objectReq = WebServiceDump(messageIn,protocolCode);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Data Dump successfully", objectReq);


        }catch (Exception e){
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Data Dump Error", "");

        }
    }


    private Object WebServiceDump(String messageIn ,String protocolCode) {
        logger.info("WebServiceDump", messageIn);

        HttpClient secureHttpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(
                        SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
                ));

        WebClient.Builder builder = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(secureHttpClient));

        String requestBody = "{\"messageIn\":" + messageIn + "}";

        if ("PO".equals(protocolCode) ) {
            String response = builder.baseUrl(GlobalVars.baseUrlTest)
                    .build()
                    .post()
                    .uri(GlobalVars.SidDump)
                    .headers(httpHeaders -> httpHeaders.addAll(globalVars.getHeaders1())) // Set headers go to global Vars to see which headers here
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(requestBody)) // Send JSON in the request body
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return response;

        } else {
            logger.info(" add service de cette protocol  [" + protocolCode + "] ");
            return "";
        }
    }


    ///// **********SidMsgHexTrace


    public  ResponseApiJson<?> SidMsgHexTrace(String messageIn ,String protocolCode){
        String idRequest = "Dump" + new Date().getTime() + (Math.random() * 9999);
        logger.info("getSidDump===>"+messageIn);

        try {

            Object objectReq;


            objectReq = WebServiceHexTrace(messageIn,protocolCode);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Data HexTrace successfully", objectReq);


        }catch (Exception e){
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Data HexTrace Error", "");

        }
    }




    private Object WebServiceHexTrace(String messageIn ,String protocolCode) {
        logger.info("WebServiceHexTrace", messageIn);

        HttpClient secureHttpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(
                        SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
                ));

        WebClient.Builder builder = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(secureHttpClient));

        String requestBody = "{\"messageIn\":" + messageIn + "}";

        if ("PO".equals(protocolCode) ) {
            String response = builder.baseUrl(GlobalVars.baseUrlTest)
                    .build()
                    .post()
                    .uri(GlobalVars.SidMsgHexTrace)
                    .headers(httpHeaders -> httpHeaders.addAll(globalVars.getHeaders1())) // Set headers go to global Vars to see which headers here
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(requestBody)) // Send JSON in the request body
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return response;
        } else {
            logger.info(" add service de cette protocol  [" + protocolCode + "] ");
            return "";
        }
    }



    public  ResponseApiJson<?> clearSession(){
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            String userCode = userM.get().getUserCode();
            globalVars.getSessionMap().remove(userCode);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "session clear success   !", "");

        }catch (Exception e){
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getDataShow please check with your provider !");

        }
    }


    public  ResponseApiJson<?> getMsgKeyExchange(String data, String protocolCode){
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("User ==>: " + userM);

        try {
            Object objectReq;


            objectReq = WebServicekeyExchange(data,protocolCode);
            logger.info("objectReq ===>"+objectReq );
            if (objectReq instanceof String) {
                String jsonString = (String) objectReq;
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.has("messageOut")) {
                    String messageOutValue = jsonObject.getString("messageOut");
                    logger.info("Value of messageOut: " + messageOutValue);

                    globalVars.getSessionMap().put(userM.get().getUserCode(), messageOutValue);

                } else {
                    logger.warn("No 'messageOut' found in the response.");
                }
            } else {
                logger.warn("Unexpected response type. Expected String, got " + objectReq.getClass().getName());
            }



            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get data  !", objectReq);

        }catch (Exception e){
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getDataShow please check with your provider !");

        }
    }



    private Object WebServicekeyExchange(String messageIn ,String protocolCode ) {
        logger.info("WebServiceDump", messageIn.toString());

        HttpClient secureHttpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(
                        SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
                ));

        WebClient.Builder builder = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(secureHttpClient));

        String requestBody = "{\"messageIn\": \"" + messageIn + "\" }";
        String response = builder.baseUrl(GlobalVars.baseUrlTest)
                .build()
                .post()
                .uri(GlobalVars.VisaBuildMsgkeyExchange)
                .headers(httpHeaders -> httpHeaders.addAll(globalVars.getHeaders1())) // Set headers go to global Vars to see which headers here
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody)) // Send JSON in the request body
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}











