package com.simulator.calculatorsCrypto.model;

import java.util.HashMap;
import java.util.Map;

public class TagDescriptionList {

    final static TagDescription[] tagDescriptionArr = new TagDescription[]{
            new TagDescription("9F01","Acquirer Identifier","Uniquely identifies the acquirer within each payment system"),
            new TagDescription("9F40","Additional Terminal Capabilities","Indicates the data input and output capabilities of the terminal"),
            new TagDescription("81","Amount, Authorised (Binary)","Authorised amount of the transaction (excluding adjustments)"),
            new TagDescription("9F02","Amount, Authorised (Numeric)","Authorised amount of the transaction (excluding adjustments)"),
            new TagDescription("9F04","Amount, Other (Binary)","Secondary amount associated with the transaction representing a cashback amount"),
            new TagDescription("9F03","Amount, Other (Numeric)","Secondary amount associated with the transaction representing a cashback amount"),
            new TagDescription("9F3A","Amount, Reference Currency","Authorised amount expressed in the reference currency"),
            new TagDescription("9F26","Application Cryptogram","Cryptogram returned by the ICC in response of the GENERATE AC command"),
            new TagDescription("9F42","Application Currency Code","Indicates the currency in which the account is managed according to ISO 4217"),
            new TagDescription("9F44","Application Currency Exponent","Indicates the implied position of the decimal point from the right of the amount represented according to ISO 4217"),
            new TagDescription("9F05","Application Discretionary Data","Issuer or payment system specified data relating to the application"),
            new TagDescription("5F25","Application Effective Date","Date from which the application may be used"),
            new TagDescription("5F24","Application Expiration Date","Date after which application expires"),
            new TagDescription("94","Application File Locator (AFL)","Indicates the location (SFI, range of records) of the AEFs related to a given application"),
            new TagDescription("4F","Application Identifier (AID) � card","Identifies the application as described in ISO/IEC 7816-5"),
            new TagDescription("9F06","Application Identifier (AID) � terminal","Identifies the application as described in ISO/IEC 7816-5"),
            new TagDescription("82","Application Interchange Profile","Indicates the capabilities of the card to support specific functions in the application"),
            new TagDescription("50","Application Label","Mnemonic associated with the AID according to ISO/IEC 7816-5"),
            new TagDescription("9F12","Application Preferred Name","Preferred mnemonic associated with the AID"),
            new TagDescription("5A","Application Primary Account Number (PAN)","Valid cardholder account number"),
            new TagDescription("5F34","Application Primary Account Number (PAN) Sequence Number","Identifies and differentiates cards with the same PAN"),
            new TagDescription("87","Application Priority Indicator","Indicates the priority of a given application or group of applications in a directory"),
            new TagDescription("9F3B","Application Reference Currency","1�4 currency codes used between the terminal and the ICC when the Transaction Currency Code is different from the Application Currency Code; each code is 3 digits according to ISO 4217"),
            new TagDescription("9F43","Application Reference Currency Exponent","Indicates the implied position of the decimal point from the right of the amount, for each of the 1�4 reference currencies represented according to ISO 4217"),
            new TagDescription("�","Application Selection Indicator","For an application in the ICC to be supported by an application in the terminal, the Application Selection Indicator indicates whether the associated AID in the terminal must match the AID in the card exactly, including the length of the AID, or only up to the length of the AID in the terminal There is only one Application Selection Indicator per AID supported by the terminal"),
            new TagDescription("61","Application Template","Contains one or more data objects relevant to an application directory entry according to ISO/IEC 7816-5"),
            new TagDescription("9F36","Application Transaction Counter (ATC)","Counter maintained by the application in the ICC (incrementing the ATC is managed by the ICC)"),
            new TagDescription("9F07","Application Usage Control","Indicates issuer�s specified restrictions on the geographic usage and services allowed for the application"),
            new TagDescription("9F08","Application Version Number","Version number assigned by the payment system for the application"),
            new TagDescription("9F09","Application Version Number","Version number assigned by the payment system for the application"),
            new TagDescription("89","Authorisation Code","Value generated by the authorisation authority for an approved transaction"),
            new TagDescription("8A","Authorisation Response Code","Code that defines the disposition of a message"),
            new TagDescription("�","Authorisation Response Cryptogram (ARPC)","Cryptogram generated by the issuer and used by the card to verify that the response came from the issuer."),
            new TagDescription("5F54","Bank Identifier Code (BIC)","Uniquely identifies a bank as defined in ISO 9362."),
            new TagDescription("8C","Card Risk Management Data Object List 1 (CDOL1)","List of data objects (tag and length) to be passed to the ICC in the first GENERATE AC command"),
            new TagDescription("8D","Card Risk Management Data Object List 2 (CDOL2)","List of data objects (tag and length) to be passed to the ICC in the second GENERATE AC command"),
            new TagDescription("�","Card Status Update (CSU)","Contains data sent to the ICC to indicate whether the issuer approves or declines the transaction, and to initiate actions specified by the issuer. Transmitted to the card in Issuer Authentication Data."),
            new TagDescription("5F20","Cardholder Name","Indicates cardholder name according to ISO 7813"),
            new TagDescription("9F0B","Cardholder Name Extended","Indicates the whole cardholder name when greater than 26 characters using the same coding convention as in ISO 7813"),
            new TagDescription("8E","Cardholder Verification Method (CVM) List","Identifies a method of verification of the cardholder supported by the application"),
            new TagDescription("9F34","Cardholder Verification Method (CVM) Results","Indicates the results of the last CVM performed"),
            new TagDescription("�","Certification Authority Public Key Check Sum","A check value calculated on the concatenation of all parts of the Certification Authority Public Key (RID, Certification Authority Public Key Index, Certification Authority Public Key Modulus, Certification Authority Public Key Exponent) using SHA-1"),
            new TagDescription("�","Certification Authority Public Key Exponent","Value of the exponent part of the Certification Authority Public Key"),
            new TagDescription("8F","Certification Authority Public Key Index","Identifies the certification authority�s public key in conjunction with the RID"),
            new TagDescription("9F22","Certification Authority Public Key Index","Identifies the certification authority�s public key in conjunction with the RID"),
            new TagDescription("�","Certification Authority Public Key Modulus","Value of the modulus part of the Certification Authority Public Key"),
            new TagDescription("83","Command Template","Identifies the data field of a command message"),
            new TagDescription("9F27","Cryptogram Information Data","Indicates the type of cryptogram and the actions to be performed by the terminal"),
            new TagDescription("9F45","Data Authentication Code","An issuer assigned value that is retained by the terminal during the verification process of the Signed Static Application Data"),
            new TagDescription("84","Dedicated File (DF) Name","Identifies the name of the DF as described in ISO/IEC 7816-4"),
            new TagDescription("�","Default Dynamic Data Authentication Data Object List (DDOL)","DDOL to be used for constructing the INTERNAL AUTHENTICATE command if the DDOL in the card is not present"),
            new TagDescription("�","Default Transaction Certificate Data Object List (TDOL)","TDOL to be used for generating the TC Hash Value if the TDOL in the card is not present"),
            new TagDescription("9D","Directory Definition File (DDF) Name","Identifies the name of a DF associated with a directory"),
            new TagDescription("73","Directory Discretionary Template","Issuer discretionary part of the directory according to ISO/IEC 7816-5"),
            new TagDescription("9F49","Dynamic Data Authentication Data Object List (DDOL)","List of data objects (tag and length) to be passed to the ICC in the INTERNAL AUTHENTICATE command"),
            new TagDescription("70","EMV Proprietary Template","Template proprietary to the EMV specification"),
            new TagDescription("�","Enciphered Personal Identification Number (PIN) Data","Transaction PIN enciphered at the PIN pad for online verification or for offline verification if the PIN pad and IFD are not a single integrated device"),
            new TagDescription("BF0C","File Control Information (FCI) Issuer Discretionary Data","Issuer discretionary part of the FCI"),
            new TagDescription("A5","File Control Information (FCI) Proprietary Template","Identifies the data object proprietary to this specification in the FCI template according to ISO/IEC 7816-4"),
            new TagDescription("6F","File Control Information (FCI) Template","Identifies the FCI template according to ISO/IEC 7816-4"),
            new TagDescription("9F4C","ICC Dynamic Number","Time-variant number generated by the ICC, to be captured by the terminal"),
            new TagDescription("9F2D","Integrated Circuit Card (ICC) PIN Encipherment Public Key Certificate","ICC PIN Encipherment Public Key certified by the issuer"),
            new TagDescription("9F2E","Integrated Circuit Card (ICC) PIN Encipherment Public Key Exponent","ICC PIN Encipherment Public Key Exponent used for PIN encipherment"),
            new TagDescription("9F2F","Integrated Circuit Card (ICC) PIN Encipherment Public Key Remainder","Remaining digits of the ICC PIN Encipherment Public Key Modulus"),
            new TagDescription("9F46","Integrated Circuit Card (ICC) Public Key Certificate","ICC Public Key certified by the issuer"),
            new TagDescription("9F47","Integrated Circuit Card (ICC) Public Key Exponent","ICC Public Key Exponent used for the verification of the Signed Dynamic Application Data"),
            new TagDescription("9F48","Integrated Circuit Card (ICC) Public Key Remainder","Remaining digits of the ICC Public Key Modulus"),
            new TagDescription("9F1E","Interface Device (IFD) Serial Number","Unique and permanent serial number assigned to the IFD by the manufacturer"),
            new TagDescription("5F53","International Bank Account Number (IBAN)","Uniquely identifies the account of a customer at a financial institution as defined in ISO 13616."),
            new TagDescription("9F0D","Issuer Action Code � Default","Specifies the issuer�s conditions that cause a transaction to be rejected if it might have been approved online, but the terminal is unable to process the transaction online"),
            new TagDescription("9F0E","Issuer Action Code � Denial","Specifies the issuer�s conditions that cause the denial of a transaction without attempt to go online"),
            new TagDescription("9F0F","Issuer Action Code � Online","Specifies the issuer�s conditions that cause a transaction to be transmitted online"),
            new TagDescription("9F10","Issuer Application Data","Contains proprietary application data for transmission to the issuer in an online transaction"),
            new TagDescription("91","Issuer Authentication Data","Data sent to the ICC for online issuer authentication"),
            new TagDescription("9F11","Issuer Code Table Index","Indicates the code table according to ISO/IEC 8859 for displaying the Application Preferred Name"),
            new TagDescription("5F28","Issuer Country Code","Indicates the country of the issuer according to ISO 3166"),
            new TagDescription("5F55","Issuer Country Code (alpha2 format)","Indicates the country of the issuer as defined in ISO 3166 (using a 2 character alphabetic code)"),
            new TagDescription("5F56","Issuer Country Code (alpha3 format)","Indicates the country of the issuer as defined in ISO 3166 (using a 3 character alphabetic code)"),
            new TagDescription("42","Issuer Identification Number (IIN)","The number that identifies the major industry and the card issuer and that forms the first part of the Primary Account Number (PAN)"),
            new TagDescription("90","Issuer Public Key Certificate","Issuer public key certified by a certification authority"),
            new TagDescription("9F32","Issuer Public Key Exponent","Issuer public key exponent used for theverification of the Signed Static Application Data and the ICC Public Key Certificate"),
            new TagDescription("92","Issuer Public Key Remainder","Remaining digits of the Issuer Public Key Modulus"),
            new TagDescription("86","Issuer Script Command","Contains a command for transmission to the ICC"),
            new TagDescription("9F18","Issuer Script Identifier","Identification of the Issuer Script"),
            new TagDescription("�","Issuer Script Results","Indicates the result of the terminal script processing"),
            new TagDescription("71","Issuer Script Template 1","Contains proprietary issuer data for transmission to the ICC before the second GENERATE AC command"),
            new TagDescription("72","Issuer Script Template 2","Contains proprietary issuer data for transmission to the ICC after the second GENERATE AC command"),
            new TagDescription("5F50","Issuer URL","The URL provides the location of the Issuer�s Library Server on the Internet."),
            new TagDescription("5F2D","Language Preference","1�4 languages stored in order of preference, each represented by 2 alphabetical characters according to ISO 639 Note: EMVCo strongly recommends that cards be personalised with data element '5F2D' coded in lowercase, but that terminals accept the data element whether it is coded in upper or lower case."),
            new TagDescription("9F13","Last Online Application Transaction Counter (ATC) Register","ATC value of the last transaction that went online"),
            new TagDescription("9F4D","Log Entry","Provides the SFI of the Transaction Log file and its number of records"),
            new TagDescription("9F4F","Log Format","List (in tag and length format) of data objects representing the logged data elements that are passed to the terminal when a transaction log record is read"),
            new TagDescription("9F14","Lower Consecutive Offline Limit","Issuer-specified preference for the maximum number of consecutive offline transactions for this ICC application allowed in a terminal with online capability"),
            new TagDescription("�","Maximum Target Percentage to be used for Biased Random Selection","Value used in terminal risk management for random transaction selection"),
            new TagDescription("9F15","Merchant Category Code","Classifies the type of business being done by the merchant, represented according to ISO 8583:1993 for Card Acceptor Business Code"),
            new TagDescription("9F16","Merchant Identifier","When concatenated with the Acquirer Identifier, uniquely identifies a given merchant"),
            new TagDescription("9F4E","Merchant Name and Location","Indicates the name and location of the merchant"),
            new TagDescription("�","Message Type","Indicates whether the batch data capture record is a financial record or advice"),
            new TagDescription("�","Personal Identification Number (PIN) Pad Secret Key","Secret key of a symmetric algorithm used by the PIN pad to encipher the PIN and by the card reader to decipher the PIN if the PIN pad and card reader are not integrated"),
            new TagDescription("9F17","Personal Identification Number (PIN) Try Counter","Number of PIN tries remaining"),
            new TagDescription("9F39","Point-of-Service (POS) Entry Mode","Indicates the method by which the PAN was entered, according to the first two digits of the ISO 8583:1987 POS Entry Mode"),
            new TagDescription("9F38","Processing Options Data Object List (PDOL)","Contains a list of terminal resident data objects (tags and lengths) needed by the ICC in processing the GET PROCESSING OPTIONS command"),
            new TagDescription("�","Proprietary Authentication Data","Contains issuer data for transmission to the card in the Issuer Authentication Data of an online transaction. For a cryptogram defined by the Common Core Definitions with a Cryptogram Version of '4', the Proprietary Authentication Data element shall be 0 bytes long. The only Cryptogram Version currently defined for the Common Core Definitions is '4'."),
            new TagDescription("80","Response Message Template Format 1","Contains the data objects (without tags and lengths) returned by the ICC in response to a command"),
            new TagDescription("77","Response Message Template Format 2","Contains the data objects (with tags and lengths) returned by the ICC in response to a command"),
            new TagDescription("5F30","Service Code","Service code as defined in ISO/IEC 7813 for track 1 and track 2"),
            new TagDescription("88","Short File Identifier (SFI)","Identifies the SFI to be used in the commands related to a given AEF or DDF. The SFI data object is a binary field with the three high order bits set to zero."),
            new TagDescription("9F4B","Signed Dynamic Application Data","Digital signature on critical application parameters for DDA or CDA"),
            new TagDescription("93","Signed Static Application Data","Digital signature on critical application parameters for SDA"),
            new TagDescription("9F4A","Static Data Authentication Tag List","List of tags of primitive data objects defined in this specification whose value fields are to be included in the Signed Static or Dynamic Application Data"),
            new TagDescription("�","Target Percentage to be Used for Random Selection","Value used in terminal risk management for random transaction selection"),
            new TagDescription("�","Terminal Action Code � Default","Specifies the acquirer�s conditions that cause a transaction to be rejected if it might have been approved online, but the terminal is unable to process the transaction online"),
            new TagDescription("�","Terminal Action Code � Denial","Specifies the acquirer�s conditions that cause the denial of a transaction without attempt to go online"),
            new TagDescription("�","Terminal Action Code � Online","Specifies the acquirer�s conditions that cause a transaction to be transmitted online"),
            new TagDescription("9F33","Terminal Capabilities","Indicates the card data input, CVM, and security capabilities of the terminal"),
            new TagDescription("9F1A","Terminal Country Code","Indicates the country of the terminal, represented according to ISO 3166"),
            new TagDescription("9F1B","Terminal Floor Limit","Indicates the floor limit in the terminal in conjunction with the AID"),
            new TagDescription("9F1C","Terminal Identification","Designates the unique location of a terminal at a merchant"),
            new TagDescription("9F1D","Terminal Risk Management Data","Application-specific value used by the card for risk management purposes"),
            new TagDescription("9F35","Terminal Type","Indicates the environment of the terminal, its communications capability, and its operational control"),
            new TagDescription("95","Terminal Verification Results","Status of the different functions as seen from the terminal"),
            new TagDescription("�","Threshold Value for Biased Random Selection","Value used in terminal risk management for random transaction selection"),
            new TagDescription("9F1F","Track 1 Discretionary Data","Discretionary part of track 1 according to ISO/IEC 7813"),
            new TagDescription("9F20","Track 2 Discretionary Data","Discretionary part of track 2 according to ISO/IEC 7813"),
            new TagDescription("57","Track 2 Equivalent Data","Contains the data elements of track 2 according to ISO/IEC 7813, excluding start sentinel, end sentinel, and Longitudinal Redundancy Check (LRC), as follows: Primary Account Number (n, var. up to 19) Field Separator (Hex 'D') (b) Expiration Date (YYMM) (n 4) Service Code (n 3) Discretionary Data (defined by individual payment systems) (n, var.) Pad with one Hex 'F' if needed to ensure whole bytes (b)"),
            new TagDescription("�","Transaction Amount","Clearing amount of the transaction, including tips and other adjustments"),
            new TagDescription("98","Transaction Certificate (TC) Hash Value","Result of a hash function specified in Book 2, Annex B3.1"),
            new TagDescription("97","Transaction Certificate Data Object List (TDOL)","List of data objects (tag and length) to be used by the terminal in generating the TC Hash Value"),
            new TagDescription("5F2A","Transaction Currency Code","Indicates the currency code of the transaction according to ISO 4217"),
            new TagDescription("5F36","Transaction Currency Exponent","Indicates the implied position of the decimal point from the right of the transaction amount represented according to ISO 4217"),
            new TagDescription("9A","Transaction Date","Local date that the transaction was authorised"),
            new TagDescription("99","Transaction Personal Identification Number (PIN) Data","Data entered by the cardholder for the purpose of the PIN verification"),
            new TagDescription("9F3C","Transaction Reference Currency Code","Code defining the common currency used by the terminal in case the Transaction Currency Code is different from the Application Currency Code"),
            new TagDescription("�","Transaction Reference Currency Conversion","Factor used in the conversion from the Transaction Currency Code to the Transaction Reference Currency Code"),
            new TagDescription("9F3D","Transaction Reference Currency Exponent","Indicates the implied position of the decimal point from the right of the transaction amount, with the Transaction Reference Currency Code represented according to ISO 4217"),
            new TagDescription("9F41","Transaction Sequence Counter","Counter maintained by the terminal that is incremented by one for each transaction"),
            new TagDescription("9B","Transaction Status Information","Indicates the functions performed in a transaction"),
            new TagDescription("9F21","Transaction Time","Local time that the transaction was authorised"),
            new TagDescription("9C","Transaction Type","Indicates the type of financial transaction, represented by the first two digits of ISO 8583:1987 Processing Code"),
            new TagDescription("9F37","Unpredictable Number","Value to provide variability and uniqueness to the generation of a cryptogram"),
            new TagDescription("9F23","Upper Consecutive Offline Limit","Issuer-specified preference for the maximum number of consecutive offline transactions for this ICC application allowed in a terminal without online capability"),

    };



    static Map<String, TagDescription> myTagDescMap = new HashMap<String, TagDescription>();

    static{
        for (TagDescription tagDescription:tagDescriptionArr) {
            myTagDescMap.put(tagDescription.getTag(), tagDescription);
        };
    }
    public static String getTagName(String tag) {

        TagDescription tagDesc = myTagDescMap.get(tag);
        return tagDesc==null?"":tagDesc.getName();
    }

    static Map<String, String> myCvmCodeMap = new HashMap<>();
    static{
        myCvmCodeMap.put("00","Fail CVM processing");
        myCvmCodeMap.put("01","Plaintext PIN verification performed by ICC");
        myCvmCodeMap.put("02","Enciphered PIN verified online");
        myCvmCodeMap.put("03","Plaintext PIN verification performed by ICC and signature (paper)");
        myCvmCodeMap.put("04","Enciphered PIN verification performed by ICC");
        myCvmCodeMap.put("05","Enciphered PIN verification performed by ICC and signature (paper)");
        myCvmCodeMap.put("1E","Signature (paper)");
        myCvmCodeMap.put("1F","No CVM required");
    }
    public static String getCvmCodeName(String cvmCode) {

        String desc = myCvmCodeMap.get(cvmCode);
        return desc==null?"":desc;
    }

    static Map<String, String> myCvmCondMap = new HashMap<String, String>();
    static{
        myCvmCondMap.put("00","Always");
        myCvmCondMap.put("01","If unattended cash");
        myCvmCondMap.put("02","If not unattended cash and not manual cash and not purchase with cashback");
        myCvmCondMap.put("03","If terminal supports the CVM");
        myCvmCondMap.put("04","If manual cash");
        myCvmCondMap.put("05","If purchase with cashback");
        myCvmCondMap.put("06","If transaction is in the application currency and is under X value");
        myCvmCondMap.put("07","If transaction is in the application currency and is over X value");
        myCvmCondMap.put("08","If transaction is in the application currency and is under Y value");
        myCvmCondMap.put("09","If transaction is in the application currency and is over Y value");
    }
    public static String getCvmCondName(String cvmCode) {

        String desc = myCvmCondMap.get(cvmCode);
        return desc==null?"":desc;
    }

}
