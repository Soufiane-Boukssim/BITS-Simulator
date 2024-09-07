package com.simulator.iso.services.SpecialValues;

import com.simulator.config.GlobalVars;
import com.simulator.entities.*;
import com.simulator.externalTools.MiscTools;
import com.simulator.iso.cryptoKey.CryproKeyService.DESSun;
import com.simulator.iso.cryptoKey.misc.HexTools;
import com.simulator.iso.model.Field11Storage;
import com.simulator.repository.*;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
@Service
@AllArgsConstructor
public class HandleSpecialValuesVisa {
    private final Logger logger = LogManager.getLogger(HandleSpecialValuesVisa.class);


    @Autowired
    GlobalVars globalVars;
    @Autowired
    AuthoMsgLogRepository authoMsgLogRepository;
    @Autowired
    FieldsDefinitionRepository fieldDefinitionRepo;
    @Autowired
    CasesRepository casesRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    TerminalRepository terminalRepository;
    @Autowired
    InstRepository instRepository;
    @Autowired
    IccRepository iccRepository;

    @Autowired
    private Field11Storage field11Storage;
    public static CasesDefinition caseDefRequest=new CasesDefinition();
    public static CasesDefinition caseDefResponse=new CasesDefinition();

    public String handleSpecialValues(String value, String fieldNumber, String field11, CasesDefinition casesDefinition ,String userCode) throws Exception {
        this.logger.info("field number: "+fieldNumber +"value: "+value);
        switch (value){
            case "Generate Value" :
                value = handleGenerateValue(fieldNumber, casesDefinition, field11,userCode);
                break;
            case "From Card Profile" :
                value = handleFromCardProfile(fieldNumber,casesDefinition,userCode);
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



    private  String handleFromMerchant(String fieldNumber, CasesDefinition casesDefinition) {
        MerchantParam merchantParam = getMerchantProfile(casesDefinition.getCaseMerchantPrf(),casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode());
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


    public  MerchantParam getMerchantProfile(String merchantNumber, String cardProtocol, String bankCode){
        try {
            MerchantParamId merchantParamId = new MerchantParamId(merchantNumber,cardProtocol,bankCode);
            Optional<MerchantParam> merchantParam = merchantRepository.findById(merchantParamId);
            return merchantParam.orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public TerminalParam getTermProfile(String terminalNumber, String cardProtocol, String bankCode){
        try {
            TerminalParamId terminalParamId = new TerminalParamId(terminalNumber,cardProtocol,bankCode);
            Optional<TerminalParam> terminalParam = terminalRepository.findById(terminalParamId);
            return terminalParam.orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
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
    private  String handleFromTerminal(String fieldNumber, CasesDefinition casesDefinition) throws Exception {
        InstParam instParam = getInstParam(casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode());
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


    public  InstParam getInstParam( String instProtocol, String bankCode){
        try {
            InstParamId instParamId = new InstParamId(instProtocol,bankCode);
            Optional<InstParam> instParam = instRepository.findById(instParamId);
            return instParam.orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private  String handleFromRequest(String fieldNumber, CasesDefinition casesDefinition) {
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


    private  String handleFromResponse(String fieldNumber, CasesDefinition casesDefinition) {

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


    private String handleGenerateValue(String fieldNumber, CasesDefinition casesDefinition, String field11,String userCode) throws InterruptedException {
        Date date = new Date();
        Random rand = new Random();
        Optional<FieldValue> fieldValueOptional;
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
                logger.info("Field11 =====> ["+field11+"]");
                field11Storage.saveField11(userCode, field11);

                return field11;

            case "12" :

                return new SimpleDateFormat("HHmmss").format(date);

            case "13" :
                return new SimpleDateFormat("yyMM").format(date);

            case "14" :
                return handleFromCardProfile("14",casesDefinition,userCode);

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
                String formattedDate = new SimpleDateFormat("yyMMdd").format(date);
                String storedField11 = field11Storage.getField11(userCode);
                if (storedField11 != null) {
                    return formattedDate + storedField11;
                } else {
                    return formattedDate;
                }
//                return new SimpleDateFormat("yyMMdd").format(date)+field11;

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
                return handleFromCardProfile("52",casesDefinition,userCode);

            case "55" :
                if (!Objects.requireNonNull(getIccProfile(casesDefinition.getId().getCaseProtocole(), Objects.requireNonNull(this.getCardProfile(casesDefinition.getCaseCardRef(), casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode())).getIcProfile(), casesDefinition.getId().getBankCode())).isEmpty()) {
                    for (IccProfile iccProfile : Objects.requireNonNull(getIccProfile(casesDefinition.getId().getCaseProtocole(), Objects.requireNonNull(this.getCardProfile(casesDefinition.getCaseCardRef(), casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode())).getIcProfile(), casesDefinition.getId().getBankCode())) ){
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

    public  CardProfile getCardProfile(String cardNumber, String cardProtocol, String bankCode){
        try {
            CardProfileId cardProfileId = new CardProfileId(cardNumber,cardProtocol,bankCode);

            Optional<CardProfile> cardProfile = cardRepository.findById(cardProfileId);
            return cardProfile.orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<IccProfile> getIccProfile(String protocol, String iccProfile, String bankCode){
        try {

            return iccRepository.findById_IcPrfAndId_IccProtocolAndId_BankCode(iccProfile,protocol,bankCode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String handleFromCardProfile(String fieldNumber, CasesDefinition casesDefinition,String userCode) {
        Date date = new Date();
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
                    CardProfile card = this.getCardProfile(casesDefinition.getCaseCardRef(), casesDefinition.getId().getCaseProtocole(), casesDefinition.getId().getBankCode());
                    InstParam instParam = getInstParam(casesDefinition.getId().getCaseProtocole(),casesDefinition.getId().getBankCode());
                    try {
                        String messageOutValue = globalVars.getSessionMap().get(userCode);
                        if (messageOutValue == null) {
                            return HexTools.generateEncyptedPINBlock(card.getPIN(),"01",card.getId().getCardNo().substring(3,15),instParam.getPinKey());

                        }else {
                            return HexTools.generateEncyptedPINBlock(card.getPIN(),"01",card.getId().getCardNo().substring(3,15),messageOutValue);

                        }


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
}
