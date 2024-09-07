package com.simulator.calculatorsCrypto.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TCFactory {
    private static final Logger logger = LogManager.getLogger(TCFactory.class);
    private static final ListDescTC[] listDescTC = new ListDescTC[]{
            new ListDescTC("82", "ADVICE FOR ONLINE ACTIVITY"),
            new ListDescTC("46", "PAYMENT"),
            new ListDescTC("68", "CARDHOLDER FEE"),
            new ListDescTC("67", "CARDHOLDER SHORT-STATEMENT DATA"),
            new ListDescTC("66", "CARDHOLDER STATEMENT REQUEST "),
            new ListDescTC("66", "CARDHOLDER CHEQUEBOOK REQUEST"),
            new ListDescTC("64", "ADVICE FOR CARD EXPIRATION"),
            new ListDescTC("63", "CARD PERSONALISATION RESPONSE"),
            new ListDescTC("62", "CADHOLDER STAND-IN ACCOUNT DATA"),
            new ListDescTC("61", "CADHOLDER STAND-IN CARD DATA"),
            new ListDescTC("60", "CARD PERSONALISATION REQUEST"),
            new ListDescTC("57", "PREPAID-CARD PERSONALISATION RESPONSE"),
            new ListDescTC("56", "PREPAID-CARD PERSONALISATION REQUEST"),
            new ListDescTC("55", "STOP-LIST MANAGEMENT"),
            new ListDescTC("51", "RETRIEVAL REQUEST"),
            new ListDescTC("52", "RETRIEVAL REQUEST"),
            new ListDescTC("53", "RETRIEVAL REQUEST"),
            new ListDescTC("50", "MESSAGE"),
            new ListDescTC("49", "ADVICE FOR TRANSACTION ACQUISITION"),


            new ListDescTC("05", "PURCHASE ON ATM"),
            new ListDescTC("15", "PURCHASE ON ATM CHARGEBACK"),
            new ListDescTC("25", "REVERSAL OF PURCHASE ON ATM"),
            new ListDescTC("35", "REVERSAL OF PURCHASE ON ATM CHARGEBACK"),
            new ListDescTC("45", "PURCHASE ON ATM REPRESENTMENT"),
            new ListDescTC("55", "REVERSAL OF PURCHASE ON ATM REPRESENTMENT"),
            new ListDescTC("65", "PURCHASE ON ATM CREDIT ADJUSTMENT"),
            new ListDescTC("75", "PURCHASE ON ATM DEBIT ADJUSTMENT"),
            new ListDescTC("07", "CASH"),
            new ListDescTC("17", "CASH CHARGEBACK"),
            new ListDescTC("27", "REVERSAL OF CASH"),
            new ListDescTC("37", "REVERSAL OF CASH CHARGEBACK"),
            new ListDescTC("47", "CASH REPRESENTMENT"),
            new ListDescTC("57", "REVERSAL OF CASH REPRESENTMENT"),
            new ListDescTC("09", "CASH CREDIT ADJUSTMENT"),
            new ListDescTC("19", "CASH DEBIT ADJUSTMENT"),


            new ListDescTC("05", "PURCHASE ON ATM"),
            new ListDescTC("15", "PURCHASE ON ATM CHARGEBACK"),
            new ListDescTC("25", "REVERSAL OF PURCHASE ON ATM"),
            new ListDescTC("35", "REVERSAL OF PURCHASE ON ATM CHARGEBACK"),
            new ListDescTC("45", "PURCHASE ON ATM REPRESENTMENT"),
            new ListDescTC("55", "REVERSAL OF PURCHASE ON ATM REPRESENTMENT"),
            new ListDescTC("65", "PURCHASE ON ATM CREDIT ADJUSTMENT"),
            new ListDescTC("75", "PURCHASE ON ATM DEBIT ADJUSTMENT"),
            new ListDescTC("07", "CASH"),
            new ListDescTC("17", "CASH CHARGEBACK"),
            new ListDescTC("27", "REVERSAL OF CASH"),
            new ListDescTC("37", "REVERSAL OF CASH CHARGEBACK"),
            new ListDescTC("47", "CASH REPRESENTMENT"),
            new ListDescTC("57", "REVERSAL OF CASH REPRESENTMENT"),
            new ListDescTC("09", "CASH CREDIT ADJUSTMENT"),
            new ListDescTC("19", "CASH DEBIT ADJUSTMENT"),
            new ListDescTC("08", "WITHDRAWAL"),
            new ListDescTC("18", "WITHDRAWAL CHARGEBACK"),
            new ListDescTC("28", "REVERSAL OF WITHDRAWAL"),
            new ListDescTC("38", "REVERSAL OF WITHDRAWAL CHARGEBACK"),
            new ListDescTC("48", "WITHDRAWAL REPRESENTMENT"),
            new ListDescTC("58", "REVERSAL OF WITHDRAWAL REPRESENTMENT"),
            new ListDescTC("68", "WITHDRAWAL CREDIT ADJUSTMENT"),
            new ListDescTC("78", "WITHDRAWAL DEBIT ADJUSTMENT"),
            new ListDescTC("02", "PURCHASE"),
            new ListDescTC("12", "PURCHASE CHARGEBACK"),
            new ListDescTC("22", "REVERSAL OF PURCHASE"),
            new ListDescTC("32", "REVERSAL OF PURCHASE CHARGEBACK"),
            new ListDescTC("42", "PURCHASE REPRESENTMENT"),
            new ListDescTC("52", "REVERSAL OF PURCHASE REPRESENTMENT"),
            new ListDescTC("03", "CREDIT VOUCHER"),
            new ListDescTC("13", "CREDIT VOUCHER CHARGEBACK"),
            new ListDescTC("23", "REVERSAL OF CREDIT VOUCHER"),
            new ListDescTC("33", "REVERSAL OF CREDIT VOUCHER CHARGEBACK"),
            new ListDescTC("43", "CREDIT VOUCHER REPRESENTMENT"),
            new ListDescTC("53", "REVERSAL OF CREDIT VOUCHER REPRESENTMENT"),
            new ListDescTC("48", "ADVICE FOR AUTHORIZATION ACQUISITION"),
            new ListDescTC("47", "ADVICE FOR TOPUP ACQUISITION"),
            new ListDescTC("40", "ADVICE FOR FRAUD"),
            new ListDescTC("10", "DEBIT FEE"),
            new ListDescTC("20", "CREDIT FEE"),


            new ListDescTC("05", "PURCHASE ON ATM"),
            new ListDescTC("15", "PURCHASE ON ATM CHARGEBACK"),
            new ListDescTC("25", "REVERSAL OF PURCHASE ON ATM"),
            new ListDescTC("35", "REVERSAL OF PURCHASE ON ATM CHARGEBACK"),
            new ListDescTC("45", "PURCHASE ON ATM REPRESENTMENT"),
            new ListDescTC("55", "REVERSAL OF PURCHASE ON ATM REPRESENTMENT"),
            new ListDescTC("65", "PURCHASE ON ATM CREDIT ADJUSTMENT"),
            new ListDescTC("75", "PURCHASE ON ATM DEBIT ADJUSTMENT"),
            new ListDescTC("07", "CASH"),
            new ListDescTC("17", "CASH CHARGEBACK"),
            new ListDescTC("27", "REVERSAL OF CASH"),
            new ListDescTC("37", "REVERSAL OF CASH CHARGEBACK"),
            new ListDescTC("47", "CASH REPRESENTMENT"),
            new ListDescTC("57", "REVERSAL OF CASH REPRESENTMENT"),
            new ListDescTC("09", "CASH CREDIT ADJUSTMENT"),
            new ListDescTC("19", "CASH DEBIT ADJUSTMENT"),
            new ListDescTC("08", "WITHDRAWAL"),
            new ListDescTC("18", "WITHDRAWAL CHARGEBACK"),
            new ListDescTC("28", "REVERSAL OF WITHDRAWAL"),
            new ListDescTC("38", "REVERSAL OF WITHDRAWAL CHARGEBACK"),
            new ListDescTC("48", "WITHDRAWAL REPRESENTMENT"),
            new ListDescTC("58", "REVERSAL OF WITHDRAWAL REPRESENTMENT"),
            new ListDescTC("68", "WITHDRAWAL CREDIT ADJUSTMENT"),
            new ListDescTC("78", "WITHDRAWAL DEBIT ADJUSTMENT"),
            new ListDescTC("02", "PURCHASE"),
            new ListDescTC("12", "PURCHASE CHARGEBACK"),
            new ListDescTC("22", "REVERSAL OF PURCHASE"),
            new ListDescTC("32", "REVERSAL OF PURCHASE CHARGEBACK"),
            new ListDescTC("42", "PURCHASE REPRESENTMENT"),
            new ListDescTC("52", "REVERSAL OF PURCHASE REPRESENTMENT"),
            new ListDescTC("03", "CREDIT VOUCHER"),
            new ListDescTC("13", "CREDIT VOUCHER CHARGEBACK"),
            new ListDescTC("23", "REVERSAL OF CREDIT VOUCHER"),
            new ListDescTC("33", "REVERSAL OF CREDIT VOUCHER CHARGEBACK"),
            new ListDescTC("43", "CREDIT VOUCHER REPRESENTMENT"),
            new ListDescTC("53", "REVERSAL OF CREDIT VOUCHER REPRESENTMENT"),
            new ListDescTC("48", "ADVICE FOR AUTHORIZATION ACQUISITION"),
            new ListDescTC("47", "ADVICE FOR TOPUP ACQUISITION"),
            new ListDescTC("40", "ADVICE FOR FRAUD"),
            new ListDescTC("10", "DEBIT FEE"),
            new ListDescTC("20", "CREDIT FEE"),
            new ListDescTC("73", "ADVICE FOR REMITTANCE - PURCHASE ON ATM"),
            new ListDescTC("74", "ADVICE FOR REMITTANCE - CASH ADVANCE"),
            new ListDescTC("75", "ADVICE FOR REMITTANCE - CASH WITHDRAWAL"),
            new ListDescTC("76", "ADVICE FOR REMITTANCE - PURCHASE"),
            new ListDescTC("77", "ADVICE FOR REMITTANCE - CREDIT VOUCHER"),
            new ListDescTC("70", "MERCHANT SUM TRANSACTIONS PER EOD "),
            new ListDescTC("71", "MERCHANT ACTIVITY TOTAL DEBIT"),
            new ListDescTC("72", "MERCHANT ACTIVITY TOTAL CREDIT"),
            new ListDescTC("78", "MERCHANT ACTIVITY DETAILS"),
            new ListDescTC("03", "PREPAID ACCOUNT ADJUSTMENT"),
            new ListDescTC("02", "CARDHOLDER DEBIT (TRANSFER)"),
            new ListDescTC("01", "CARDHOLDER CREDIT (TOPUP)"),
            new ListDescTC("93", "END OF LOGICAL FILE - MERCHANT"),
            new ListDescTC("95", "END OF LOGICAL FILE - VISA"),
            new ListDescTC("97", "END OF LOGICAL FILE - MASTERCARD"),
            new ListDescTC("99", "END OF LOGICAL FILE - LOCAL"),
            new ListDescTC("81", "END OF LOGICAL FILE - SERVICES"),
            new ListDescTC("92", "START OF LOGICAL FILE - MERCHANT"),
            new ListDescTC("94", "START OF LOGICAL FILE - VISA"),
            new ListDescTC("96", "START OF LOGICAL FILE - MASTERCARD"),
            new ListDescTC("98", "START OF LOGICAL FILE - LOCAL"),
            new ListDescTC("80", "START OF LOGICAL FILE - SERVICES"),
            new ListDescTC("91", "END OF FILE"),
            new ListDescTC("90", "START OF FILE")


    };

    private static String getDescription(String code) {
        for (ListDescTC desc : listDescTC) {
            if (desc.getId().equals(code)) {
                return desc.getId() + " - " + desc.getDescription();
            }
        }
        return code + " - " + "";
    }


    static public List<IpmField> createTC(String pData) {
        System.out.println("pdata ["+pData+"]");
        List<IpmField> fields = new ArrayList<>();

        String transactionCode = pData.substring(0, 2);
        String description = getDescription(transactionCode);

        if (pData.substring(0, 2).equals("90")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 3, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 9, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "DESTINATION BANK CODE", 10, 6, pData.substring(9, 15)));
            fields.add(new IpmField(5, "FILE PROCESSING DATE", 16, 6, pData.substring(15, 21)));
            fields.add(new IpmField(6, "FILE SEQUENCE NUMBER", 22, 3, pData.substring(21, 24)));
            fields.add(new IpmField(7, "FILE STATUS INDICATOR", 25, 1, pData.substring(24, 25)));
            fields.add(new IpmField(8, "LIS VERSION", 26, 15,""));
            fields.add(new IpmField(9, "ORIGINATOR BANK", 41, 6, ""));
            fields.add(new IpmField(10, "FILLER", 47, 210, ""));

        } else if (pData.substring(0, 2).equals("91") && pData.substring(8, 9).equals("0")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 3, 6, pData.substring(4, 10)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 9, 1, pData.substring(10, 11)));
            fields.add(new IpmField(4, "TOTAL NUMBER OF TCR RECORDS", 10, 6, pData.substring(11, 17)));
            fields.add(new IpmField(5, "TOTAL TC70, (TCR0/F.003=0)", 16, 6, pData.substring(17, 23)));
            fields.add(new IpmField(6, "TOTAL TC71, (TCR0/F.003=0)", 22, 6, pData.substring(23, 29)));
            fields.add(new IpmField(7, "TOTAL TC72, (TCR0/F.003=0)", 28, 6, pData.substring(29, 35)));
            fields.add(new IpmField(8, "TOTAL TC73, (TCR0/F.003=0)", 34, 6, pData.substring(35, 41)));
            fields.add(new IpmField(9, "TOTAL TC74, (TCR0/F.003=0)", 40, 6, pData.substring(41, 47)));
            fields.add(new IpmField(10, "TOTAL TC74, (TCR0/F.009=0)", 46, 6, pData.substring(47, 53)));
            fields.add(new IpmField(11, "TOTAL TC05,06,07, (TCR0/F.014=1) AND (TCR1/F.010=' ')", 52, 6, pData.substring(53, 59)));
            fields.add(new IpmField(12, "TOTAL TC05,06,07, (TCR0/F.014=1) AND (TCR1/F.010='V')", 58, 6, pData.substring(59, 65)));
            fields.add(new IpmField(13, "TOTAL TC25,26,27, (TCR0/F.014=1)", 64, 6, pData.substring(65, 71)));
            fields.add(new IpmField(14, "TOTAL TC05,06,07, (TCR0/F.014=2)", 70, 6, pData.substring(71, 77)));
            fields.add(new IpmField(15, "TOTAL TC25,26,27, (TCR0/F.014=2)", 76, 6, pData.substring(77, 83)));
            fields.add(new IpmField(16, "TOTAL TC15,16,17, (TCR0/F.014=1)", 82, 6, pData.substring(83, 89)));
            fields.add(new IpmField(17, "TOTAL TC35,36,37, (TCR0/F.014=1)", 88, 6, pData.substring(89, 95)));
            fields.add(new IpmField(18, "TOTAL TC15,16,17, (TCR0/F.014=2)", 94, 6, pData.substring(95, 101)));
            fields.add(new IpmField(19, "TOTAL TC35,36,37, (TCR0/F.014=2)", 100, 6, pData.substring(101, 107)));
            fields.add(new IpmField(20, "TOTAL TC10, (TCR0/F.004=1)", 106, 4, pData.substring(107, 111)));
            fields.add(new IpmField(21, "TOTAL TC10, (TCR0/F.004=0)", 110, 4, pData.substring(111, 115)));
            fields.add(new IpmField(22, "TOTAL TC20, (TCR0/F.004=1)", 114, 4, pData.substring(115, 119)));
            fields.add(new IpmField(24, "TOTAL TC40", 122, 4, pData.substring(123, 127)));
            fields.add(new IpmField(25, "TOTAL TC48", 126, 4, pData.substring(127, 131)));
            fields.add(new IpmField(26, "TOTAL TC49", 130, 4, pData.substring(131, 135)));
            fields.add(new IpmField(27, "TOTAL TC50, (TCR0/F.004=1)", 134, 4, pData.substring(135, 139)));
            fields.add(new IpmField(28, "TOTAL TC50, (TCR0/F.004=0)", 138, 4, pData.substring(139, 143)));
            fields.add(new IpmField(29, "TOTAL TC51", 142, 4, pData.substring(143, 147)));
            fields.add(new IpmField(30, "TOTAL TC52", 146, 4, pData.substring(147, 151)));
            fields.add(new IpmField(31, "TOTAL TC53", 150, 4, pData.substring(151, 155)));
            fields.add(new IpmField(32, "TOTAL TC82", 154, 4, pData.substring(155, 159)));
            fields.add(new IpmField(33, "TOTAL TC46", 158, 4, pData.substring(159, 163)));
            fields.add(new IpmField(34, "TOTAL TC60", 162, 6, pData.substring(163, 169)));
            fields.add(new IpmField(35, "TOTAL TC61", 168, 6, pData.substring(169, 175)));
            fields.add(new IpmField(36, "TOTAL TC62", 174, 6, pData.substring(175, 181)));
            fields.add(new IpmField(37, "TOTAL TC63", 180, 6, pData.substring(181, 187)));
            fields.add(new IpmField(38, "TOTAL TC64", 186, 6, pData.substring(187, 193)));
          //  fields.add(new IpmField(39, "FILLER", 192, 65, pData.substring(193, 258)));

        } else if (pData.substring(0, 2).equals("92")
                || pData.substring(0, 2).equals("94")
                || pData.substring(0, 2).equals("96")
                || pData.substring(0, 2).equals("98")
                || pData.substring(0, 2).equals("80")
        ) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "FILLER", 9, 247, pData.substring(9, 256)));
        } else if (pData.substring(0, 2).equals("93")
                || pData.substring(0, 2).equals("95")
                || pData.substring(0, 2).equals("97")
                || pData.substring(0, 2).equals("99")
                || pData.substring(0, 2).equals("81")
        ) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "TOTAL NUMBER OF TCR RECORDS", 9, 6, pData.substring(9, 15)));
            fields.add(new IpmField(5, "TOTAL NUMBER OF MERCHANT CREDIT", 15, 6, pData.substring(15, 21)));
            fields.add(new IpmField(6, "TOTAL NUMBER OF CASH WITHDRAWAL ON ATM REMITTANCE CREDIT", 21, 6, pData.substring(21, 27)));
            fields.add(new IpmField(7, "TOTAL NUMBER OF CASH ADVANCE REMITTANCE CREDIT", 27, 6, pData.substring(27, 33)));
            fields.add(new IpmField(8, "TOTAL NUMBER OF MERCHANT DEBIT", 33, 6, pData.substring(33, 39)));
            fields.add(new IpmField(9, "TOTAL NUMBER OF CASH WITHDRAWAL ON ATM REMITTANCE DEBIT", 39, 6, pData.substring(39, 45)));
            fields.add(new IpmField(10, "TOTAL NUMBER OF CASH ADVANCE REMITTANCE DEBIT", 45, 6, pData.substring(45, 51)));
            fields.add(new IpmField(11, "TOTAL TC05, 06, 07, (TCR0/F.014=1) & (TCR1/F.010=' ')", 51, 6, pData.substring(51, 57)));
            fields.add(new IpmField(12, "TOTAL TC05 (TCR0/F.014=1) & (TCR1/F.010='V')", 57, 6, pData.substring(57, 63)));
            fields.add(new IpmField(13, "TOTAL TC25,26,27, (TCR0/F.014=1)", 63, 6, pData.substring(63, 69)));
            fields.add(new IpmField(14, "TOTAL TC05,06,07, (TCR0/F.014=2)", 69, 6, pData.substring(69, 75)));
            fields.add(new IpmField(15, "TOTAL TC25,26,27, (TCR0/F.014=2)", 75, 6, pData.substring(75, 81)));
            fields.add(new IpmField(16, "TOTAL TC15,16,17, (TCR0/F.014=1)", 81, 6, pData.substring(81, 87)));
            fields.add(new IpmField(17, "TOTAL TC35,36,37, (TCR0/F.014=1)", 87, 6, pData.substring(87, 93)));
            fields.add(new IpmField(18, "TOTAL TC15,16,17, (TCR0/F.014=2)", 93, 6, pData.substring(93, 99)));
            fields.add(new IpmField(19, "TOTAL TC35,36,37, (TCR0/F.014=2)", 99, 6, pData.substring(99, 105)));
            fields.add(new IpmField(20, "TOTAL TC10, (TCR0/F.004=1)", 105, 6, pData.substring(105, 111)));
            fields.add(new IpmField(21, "TOTAL TC10, (TCR0/F.004=0)", 111, 6, pData.substring(111, 117)));
            fields.add(new IpmField(22, "TOTAL TC20, (TCR0/F.004=1)", 117, 6, pData.substring(117, 123)));
            fields.add(new IpmField(23, "TOTAL TC20, (TCR0/F.004=0)", 123, 6, pData.substring(123, 129)));
            fields.add(new IpmField(24, "TOTAL TC40", 129, 6, pData.substring(129, 135)));
            fields.add(new IpmField(25, "TOTAL TC48", 135, 6, pData.substring(135, 141)));
            fields.add(new IpmField(26, "TOTAL TC49", 141, 6, pData.substring(141, 147)));
            fields.add(new IpmField(27, "TOTAL TC50, (TCR0/F.004=1)", 147, 6, pData.substring(147, 153)));
            fields.add(new IpmField(28, "TOTAL TC50, (TCR0/F.004=0)", 153, 6, pData.substring(153, 159)));
            fields.add(new IpmField(29, "TOTAL TC51", 159, 6, pData.substring(159, 165)));
            fields.add(new IpmField(30, "TOTAL TC52", 165, 6, pData.substring(165, 171)));
            fields.add(new IpmField(31, "TOTAL TC53", 171, 6, pData.substring(171, 177)));
            fields.add(new IpmField(32, "TOTAL TC82", 177, 6, pData.substring(177, 183)));
            fields.add(new IpmField(33, "TOTAL TC46", 183, 6, pData.substring(183, 189)));
            fields.add(new IpmField(34, "TOTAL NUMBER OF PURCHASE ON ATM REMITTANCE CREDIT", 189, 6, pData.substring(189, 195)));
            fields.add(new IpmField(35, "TOTAL NUMBER OF PURCHASE ON ATM REMITTANCE DEBIT", 195, 6, pData.substring(195, 201)));
            fields.add(new IpmField(36, "TOTAL NUMBER OF PAYMENT INCIDENT", 201, 6, pData.substring(201, 207)));
            fields.add(new IpmField(37, "TOTAL NUMBER OF PERSONNALISATION", 207, 6, pData.substring(207, 213)));
            fields.add(new IpmField(38, "TOTAL NUMBER OF CARD STAND IN PARAMETERS", 213, 6, pData.substring(213, 219)));
            fields.add(new IpmField(39, "TOTAL NUMBER OF ACCOUNT STAND IN PARAMETERS", 219, 6, pData.substring(219, 225)));
            fields.add(new IpmField(40, "TOTAL NUMBER OF CARD PERSONNALISATION CONFIRMATION", 225, 6, pData.substring(225, 231)));
            fields.add(new IpmField(41, "TOTAL NUMBER OF RENEWAL ADVICE", 231, 6, ""));
            fields.add(new IpmField(42, "FILLER", 237, 19, ""));


        } else if (pData.substring(0, 2).equals("01")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "RELOAD TYPE", 9, 2, pData.substring(9, 11)));
            fields.add(new IpmField(5, "ACQUIRER BANK CODE", 11, 6, pData.substring(11, 17)));
            fields.add(new IpmField(6, "ISSUER BANK CODE", 17, 6, pData.substring(17, 23)));
            fields.add(new IpmField(7, "ACQUIRER’S REFERENCE NUMBER", 23, 23, pData.substring(23, 46)));
            fields.add(new IpmField(8, "ACQUIRER’S REFERENCE SEQUENCE NUMBER", 46, 1, pData.substring(46, 47)));
            fields.add(new IpmField(9, "AUTHORIZATION CODE", 47, 6, pData.substring(47, 53)));
            fields.add(new IpmField(10, "FEES AMOUNT", 53, 12, pData.substring(53, 65)));
            fields.add(new IpmField(11, "PRODUCT TYPE", 65, 2, pData.substring(65, 67)));
            fields.add(new IpmField(12, "TRANSACTION TYPE", 67, 1, pData.substring(67, 68)));
            fields.add(new IpmField(13, "MERCHANT ESTABLISHMENT NUMBER", 68, 15, pData.substring(68, 83)));
            fields.add(new IpmField(14, "MERCHANT NAME", 83, 25, pData.substring(83, 108)));
            fields.add(new IpmField(15, "MERCHANT CITY", 108, 13, pData.substring(108, 121)));
            fields.add(new IpmField(16, "MERCHANT COUNTRY CODE", 121, 3, pData.substring(121, 124)));
            fields.add(new IpmField(17, "MERCHANT CATEGORY CODE", 124, 4, pData.substring(124, 128)));
            fields.add(new IpmField(18, "TRANSACTION AMOUNT", 128, 18, pData.substring(128, 146)));
            fields.add(new IpmField(19, "TRANSACTION CURRENCY", 146, 3, pData.substring(146, 149)));
            fields.add(new IpmField(20, "BILLING AMOUNT", 149, 18, pData.substring(149, 167)));
            fields.add(new IpmField(21, "BILLING CURRENCY", 167, 3, pData.substring(167, 170)));
            fields.add(new IpmField(22, "TRANSACTION DATE", 170, 8, pData.substring(170, 178)));
            fields.add(new IpmField(23, "SIGN", 178, 1, pData.substring(178, 179)));
            fields.add(new IpmField(24, "CARDHOLDER CARD NUMBER INVOLVED IN TRANSACTION", 179, 19, pData.substring(179, 198)));
            fields.add(new IpmField(25, "CARDHOLDER CARD NUMBER EXPIRY DATE", 198, 4, pData.substring(198, 202)));
            fields.add(new IpmField(26, "CARDHOLDER IDENTIFICATION METHOD INDICATOR", 202, 1, pData.substring(202, 203)));
            fields.add(new IpmField(27, "CARDHOLDER CARD NUMBER CAPTURE INDICATOR", 203, 1, pData.substring(203, 204)));
            fields.add(new IpmField(28, "TRANSACTION CODE HEADER", 204, 2, ""));
            fields.add(new IpmField(29, "FILLER", 206, 50, ""));
        }else if(pData.substring(0,2).equals("02")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 4, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 3, 6, pData.substring(4, 10)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 9, 1, pData.substring(10, 11)));
            fields.add(new IpmField(4, "DESTINATION BANK CODE", 10, 6, pData.substring(11, 17)));
            fields.add(new IpmField(5, "FILE PROCESSING DATE", 16, 6, pData.substring(17, 23)));
            fields.add(new IpmField(6, "FILE SEQUENCE NUMBER", 22, 3, pData.substring(23, 26)));
            fields.add(new IpmField(7, "FILE STATUS INDICATOR", 25, 1, pData.substring(26, 27)));
            fields.add(new IpmField(8, "ACQUIRER’S REFERENCE SEQUENCE NUMBER", 46, 1, pData.substring(46, 47)));
            fields.add(new IpmField(9, "AUTHORIZATION CODE", 47, 6, pData.substring(47, 53)));
            fields.add(new IpmField(10, "FEES AMOUNT", 53, 12, pData.substring(53, 65)));
            fields.add(new IpmField(11, "PRODUCT TYPE", 65, 2, pData.substring(65, 67)));
            fields.add(new IpmField(12, "TRANSACTION TYPE", 67, 1, pData.substring(67, 68)));
            fields.add(new IpmField(13, "MERCHANT ESTABLISHMENT NUMBER", 68, 15, pData.substring(68, 83)));
            fields.add(new IpmField(14, "MERCHANT NAME", 83, 25, pData.substring(83, 108)));
            fields.add(new IpmField(15, "MERCHANT CITY", 108, 13, pData.substring(108, 121)));
            fields.add(new IpmField(16, "MERCHANT COUNTRY CODE", 121, 3, pData.substring(121, 124)));
            fields.add(new IpmField(17, "MERCHANT CATEGORY CODE", 124, 4, pData.substring(124, 128)));
            fields.add(new IpmField(18, "TRANSACTION AMOUNT", 128, 18, pData.substring(128, 146)));
            fields.add(new IpmField(19, "TRANSACTION CURRENCY", 146, 3, pData.substring(146, 149)));
            fields.add(new IpmField(20, "BILLING AMOUNT", 149, 18, pData.substring(149, 167)));
            fields.add(new IpmField(21, "BILLING CURRENCY", 167, 3, pData.substring(167, 170)));
            fields.add(new IpmField(22, "TRANSACTION DATE", 170, 8, pData.substring(170, 178)));
            fields.add(new IpmField(23, "SIGN", 178, 1, pData.substring(178, 179)));
            fields.add(new IpmField(24, "CARDHOLDER CARD NUMBER INVOLVED IN TRANSACTION", 179, 19, pData.substring(179, 198)));
            fields.add(new IpmField(25, "CARDHOLDER CARD NUMBER EXPIRY DATE", 198, 4, pData.substring(198, 202)));
            fields.add(new IpmField(26, "CARDHOLDER IDENTIFICATION METHOD INDICATOR", 202, 1, pData.substring(202, 203)));
            fields.add(new IpmField(27, "CARDHOLDER CARD NUMBER CAPTURE INDICATOR", 203, 1, ""));
            fields.add(new IpmField(28, "TRANSACTION CODE HEADER", 204, 2,""));
            fields.add(new IpmField(29, "FILLER", 206, 50, ""));
        }else if(pData.substring(0,2).equals("03")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "ADJUSTMENT TYPE", 9, 2, pData.substring(9, 11)));
            fields.add(new IpmField(5, "ISSUER BANK CODE", 11, 6, pData.substring(11, 17)));
            fields.add(new IpmField(6, "ACQUIRER’S REFERENCE NUMBER", 17, 23, pData.substring(17, 40)));
            fields.add(new IpmField(7, "ACQUIRER’S REFERENCE SEQUENCE NUMBER", 40, 1, pData.substring(40, 41)));
            fields.add(new IpmField(8, "PRODUCT TYPE", 41, 2, pData.substring(41, 43)));
            fields.add(new IpmField(9, "CLIENT CODE", 43, 24, pData.substring(43, 67)));
            fields.add(new IpmField(10, "SHADOW ACCOUNT NUMBER", 67, 24, pData.substring(67, 91)));
            fields.add(new IpmField(11, "CARDHOLDER CARD NUMBER INVOLVED IN TRANSACTION", 91, 19, pData.substring(91, 110)));
            fields.add(new IpmField(12, "CARDHOLDER CARD NUMBER EXPIRY DATE", 110, 4, pData.substring(110, 114)));
            fields.add(new IpmField(13, "CARDHOLDER IDENTIFICATION METHOD INDICATOR", 114, 1, pData.substring(114, 115)));
            fields.add(new IpmField(14, "MERCHANT ESTABLISHMENT NUMBER", 115, 15, pData.substring(115, 130)));
            fields.add(new IpmField(15, "MERCHANT NAME", 130, 25, pData.substring(130, 155)));
            fields.add(new IpmField(16, "MERCHANT CITY", 155, 13, pData.substring(155, 168)));
            fields.add(new IpmField(17, "MERCHANT COUNTRY CODE", 168, 3, pData.substring(168, 171)));
            fields.add(new IpmField(18, "MERCHANT CATEGORY CODE", 171, 4, pData.substring(171, 175)));
            fields.add(new IpmField(19, "ADJUSTMENT AMOUNT", 175, 18, pData.substring(175, 193)));
            fields.add(new IpmField(19, "ADJUSTMENT AMOUNT", 175, 18, pData.substring(175, 193)));
            fields.add(new IpmField(20, "ADJUSTMENT CURRENCY", 193, 3, pData.substring(193, 196)));
            fields.add(new IpmField(21, "ADJUSTMENT SIGN", 196, 1, pData.substring(196, 197)));
            fields.add(new IpmField(22, "ADJUSTMENT DATE", 197, 8, pData.substring(197, 205)));
            fields.add(new IpmField(23, "TRANSACTION CODE HEADER", 205, 2, ""));
            fields.add(new IpmField(24, "FILLER", 207, 49, ""));
        }else if(
                (          pData.substring(0,2).equals("70")
                        || pData.substring(0,2).equals("71")
                        || pData.substring(0,2).equals("78")
                )
                        && pData.substring(8,9).equals("0")
        ){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "MERCHANT ESTABLISHMENT NUMBER", 9, 15, pData.substring(9, 24)));
            fields.add(new IpmField(5, "MERCHANT NAME", 24, 25, pData.substring(24, 49)));
            fields.add(new IpmField(6, "MERCHANT CITY", 49, 13, pData.substring(49, 62)));
            fields.add(new IpmField(7, "MERCHANT TERRITORY CODE", 62, 1, pData.substring(62, 63)));
            fields.add(new IpmField(8, "MERCHANT CATEGORY CODE", 63, 4, pData.substring(63, 67)));
            fields.add(new IpmField(9, "MERCHANT ACCOUNT NUMBER TO BE CREDITED/DEBITED", 67, 24, pData.substring(67, 91)));
            fields.add(new IpmField(10, "MERCHANT SETTLEMENT CURRENCY CODE (SCC)", 91, 3, pData.substring(91, 94)));
            fields.add(new IpmField(11, "SETTLEMENT CURRENCY CODE EXPONENT", 94, 1, pData.substring(94, 95)));
            fields.add(new IpmField(12, "AMOUNT IN MERCHANT SETTLEMENT CURRENCY", 95, 12, pData.substring(95, 107)));
            fields.add(new IpmField(13, "EXCHANGE RATE OF LOCAL CURRENCY (CFA) TO SETTLEMENT CURRENCY (SCC)", 107, 9, pData.substring(107, 116)));
            fields.add(new IpmField(14, "AMOUNT IN LOCAL CURRENCY (CURRENTLY CFA)", 116, 12, pData.substring(116, 128)));
            fields.add(new IpmField(15, "SETTLEMENT DATE", 128, 6, pData.substring(128, 134)));
            fields.add(new IpmField(16, "PART OF DOMESTIC LOCAL TRANSACTION - NET AMOUNT", 134, 12, pData.substring(134, 146)));
            fields.add(new IpmField(17, "PART OF NON DOMESTIC VISA � NET AMOUNT", 146, 12, pData.substring(146, 158)));
            fields.add(new IpmField(18, "PART OF NON DOMESTIC MASTERCARD � NET AMOUNT", 158, 12, pData.substring(158, 170)));
            fields.add(new IpmField(19, "PART OF NON DOMESTIC JCB � NET AMOUNT", 170, 12, pData.substring(170, 182)));
            fields.add(new IpmField(20, "PART OF DOMESTIC LOCAL TRANSACTION - NET AMOUNT SIGN", 182, 1, pData.substring(182, 183)));
            fields.add(new IpmField(21, "PART OF NON DOMESTIC VISA � NET AMOUNT SIGN", 183, 1, pData.substring(183, 184)));
            fields.add(new IpmField(22, "PART OF NON DOMESTIC MASTERCARD � NET AMOUNT SIGN", 184, 1, pData.substring(184, 185)));
            fields.add(new IpmField(23, "PART OF NON DOMESTIC JCB � NET AMOUNT SIGN", 185, 1, pData.substring(185, 186)));
            fields.add(new IpmField(24, "INTERNAL IDENTIFICATION", 186, 24, pData.substring(186, 210)));
            fields.add(new IpmField(25, "TRANSFERT IDENTIFICATION", 210, 10, pData.substring(210, 220)));
            fields.add(new IpmField(26, "FILLER", 220, 36, pData.substring(220, 256)));

        }else if(
                (          pData.substring(0,2).equals("70")
                        || pData.substring(0,2).equals("71")
                        || pData.substring(0,2).equals("78")
                )
                        && pData.substring(8,9).equals("1")
        ) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "MERCHANT ESTABLISHMENT NUMBER", 9, 15, pData.substring(9, 24)));
            fields.add(new IpmField(5, "REMITTANCE CURRENCY CODE (RCC)", 24, 3, pData.substring(24, 27)));
            fields.add(new IpmField(6, "REMITTANCE CURRENCY CODE EXPONENT", 27, 1, pData.substring(27, 28)));
            fields.add(new IpmField(7, "MERCHANT ACCOUNT NUMBER TO BE CREDITED/DEBITED", 28, 24, pData.substring(28, 52)));
            fields.add(new IpmField(8, "REASON CODE", 52, 2, pData.substring(52, 54)));
            fields.add(new IpmField(9, "MERCHANT DEPOSIT SLIP (MDS) NUMBER OR REMITTANCE NUMBER", 54, 6, pData.substring(54, 60)));
            fields.add(new IpmField(10, "REMITTANCE DEPOSIT DATE", 60, 6, pData.substring(60, 66)));
            fields.add(new IpmField(11, "AMOUNT IN REMITTANCE CURRENCY", 66, 12, pData.substring(66, 78)));
            fields.add(new IpmField(12, "REASON CODE", 78, 2, pData.substring(78, 80)));
            fields.add(new IpmField(13, "MERCHANT DEPOSIT SLIP (MDS) NUMBER OR REMITTANCE NUMBER", 80, 6, pData.substring(80, 86)));
            fields.add(new IpmField(14, "REMITTANCE DEPOSIT DATE", 86, 6, pData.substring(86, 92)));
            fields.add(new IpmField(15, "TRANSACTION AMOUNT IN REMITTANCE CURRENCY", 92, 12, pData.substring(92, 104)));
            fields.add(new IpmField(16, "REASON CODE", 104, 2, pData.substring(104, 106)));
            fields.add(new IpmField(17, "MERCHANT DEPOSIT SLIP (MDS) NUMBER OR REMITTANCE NUMBER", 106, 6, pData.substring(106, 112)));
            fields.add(new IpmField(18, "REMITTANCE DEPOSIT DATE", 112, 6, pData.substring(112, 118)));
            fields.add(new IpmField(19, "AMOUNT IN REMITTANCE CURRENCY", 118, 12, pData.substring(118, 130)));
            fields.add(new IpmField(20, "REASON CODE", 130, 2, pData.substring(130, 132)));
            fields.add(new IpmField(21, "MERCHANT DEPOSIT SLIP (MDS) NUMBER OR REMITTANCE NUMBER", 132, 6, pData.substring(132, 138)));
            fields.add(new IpmField(22, "REMITTANCE DEPOSIT DATE", 138, 6, pData.substring(138, 144)));
            fields.add(new IpmField(23, "AMOUNT IN REMITTANCE CURRENCY", 144, 12, pData.substring(144, 156)));
            fields.add(new IpmField(24, "REASON CODE", 156, 2, pData.substring(156, 158)));
            fields.add(new IpmField(25, "MERCHANT DEPOSIT SLIP (MDS) NUMBER OR REMITTANCE NUMBER", 158, 6, pData.substring(158, 164)));
            fields.add(new IpmField(26, "REMITTANCE DEPOSIT DATE", 164, 6, pData.substring(164, 170)));
            fields.add(new IpmField(27, "AMOUNT IN REMITTANCE CURRENCY", 170, 12, pData.substring(170, 182)));
            fields.add(new IpmField(28, "DISCOUNT AMOUNT 1", 182, 8, pData.substring(182, 190)));
            fields.add(new IpmField(29, "DISCOUNT AMOUNT 2", 190, 8, pData.substring(190, 198)));
            fields.add(new IpmField(30, "DISCOUNT AMOUNT 3", 198, 8, pData.substring(198, 206)));
            fields.add(new IpmField(31, "DISCOUNT AMOUNT 4", 206, 8, pData.substring(206, 214)));
            fields.add(new IpmField(32, "DISCOUNT AMOUNT 5", 214, 8, pData.substring(214, 222)));
            fields.add(new IpmField(33, "INFORMATIONS RECOUVREMENT 1", 222, 1, pData.substring(222,223)));
            fields.add(new IpmField(34, "INFORMATIONS RECOUVREMENT 2", 223, 1, pData.substring(223, 224)));
            fields.add(new IpmField(35, "INFORMATIONS RECOUVREMENT 3", 224, 1, pData.substring(224, 225)));
            fields.add(new IpmField(36, "INFORMATIONS RECOUVREMENT 4", 225, 1, pData.substring(225, 226)));
            fields.add(new IpmField(37, "INFORMATIONS RECOUVREMENT 5", 226, 1, pData.substring(226, 227)));
            fields.add(new IpmField(38, "FILLER", 227, 6, pData.substring(227, 233)));
            fields.add(new IpmField(39, "TYPE REMITTANCE 1", 233, 1, pData.substring(233, 234)));
            fields.add(new IpmField(40, "TYPE REMITTANCE 2", 234, 1, pData.substring(234, 235)));
            fields.add(new IpmField(41, "TYPE REMITTANCE 3", 235, 1, pData.substring(235, 236)));
            fields.add(new IpmField(42, "TYPE REMITTANCE 4", 236, 1, pData.substring(236, 237)));
            fields.add(new IpmField(43, "TYPE REMITTANCE 5", 237, 1, pData.substring(237, 238)));
            fields.add(new IpmField(44, "FILLER", 238, 18, pData.substring(238, 256)));




        }else if(pData.substring(0,2).equals("72")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "MERCHANT ESTABLISHMENT NUMBER", 9, 15, pData.substring(9, 24)));
            fields.add(new IpmField(5, "MERCHANT NAME", 24, 25, pData.substring(24, 49)));
            fields.add(new IpmField(6, "MERCHANT CITY", 49, 13, pData.substring(49, 62)));
            fields.add(new IpmField(7, "MERCHANT TERRITORY CODE", 62, 1, pData.substring(62, 63)));
            fields.add(new IpmField(8, "MERCHANT CATEGORY CODE", 63, 4, pData.substring(63, 67)));
            fields.add(new IpmField(9, "MERCHANT ACCOUNT NUMBER TO BE CREDITED/DEBITED", 67, 24, pData.substring(67, 91)));
            fields.add(new IpmField(10, "REASON CODE FOR PAYMENT INCIDENT", 91, 2, pData.substring(91, 93)));
            fields.add(new IpmField(11, "SETTLEMENT CURRENCY CODE", 93, 3, pData.substring(93, 96)));
            fields.add(new IpmField(12, "SETTLEMENT CURRENCY CODE EXPONENT", 96, 1, pData.substring(96, 97)));
            fields.add(new IpmField(13, "AMOUNT IN MERCHANT SETTLEMENT CURRENCY", 97, 12, pData.substring(97, 109)));
            fields.add(new IpmField(14, "SETTLEMENT DATE", 109, 6, pData.substring(109, 115)));
            fields.add(new IpmField(15, "INTERNAL IDENTIFICATION", 115, 24, pData.substring(115, 139)));
            fields.add(new IpmField(16, "TRANSFERT IDENTIFICATION", 139, 10, pData.substring(139, 149)));
            fields.add(new IpmField(17, "FILLER", 149, 107, pData.substring(149, 256)));

        }  else if( (pData.substring(0,2).equals("73")
                || pData.substring(0,2).equals("74")
                || pData.substring(0,2).equals("75")
                || pData.substring(0,2).equals("76")
                || pData.substring(0,2).equals("77"))
				&& pData.substring(8,9).equals("0"))
        {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "REMITTANCE CURRENCY CODE (RCC)", 9, 3, pData.substring(9, 12)));
            fields.add(new IpmField(5, "REMITTANCE CURRENCY CODE EXPONENT", 12, 1, pData.substring(12, 13)));
            fields.add(new IpmField(6, "REMITTANCE SIGN", 13, 1, pData.substring(13, 14)));
            fields.add(new IpmField(7, "MERCHANT ESTABLISHMENT NUMBER", 14, 10, pData.substring(14, 24)));
            fields.add(new IpmField(8, "MERCHANT ACRONYM", 24, 25, pData.substring(24, 49)));
            fields.add(new IpmField(9, "MERCHANT CITY", 49, 20, pData.substring(49, 69)));
            fields.add(new IpmField(10, "MERCHANT ACCOUNT NUMBER", 69, 24, pData.substring(69, 93)));
            fields.add(new IpmField(11, "REMITTANCE NUMBER", 93, 6, pData.substring(93, 99)));
            fields.add(new IpmField(12, "REMITTANCE DEPOSIT DATE", 99, 6, pData.substring(99, 105)));
            fields.add(new IpmField(13, "AMOUNT IN REMITTANCE CURRENCY", 105, 12, pData.substring(105, 117)));
            fields.add(new IpmField(14, "NUMBER OF TRANSACTION IN REMITTANCE", 117, 3, pData.substring(117, 120)));
            fields.add(new IpmField(15, "REMITTANCE NUMBER", 120, 6, pData.substring(120, 126)));
            fields.add(new IpmField(16, "REMITTANCE DEPOSIT DATE", 126, 6, pData.substring(126, 132)));
            fields.add(new IpmField(17, "AMOUNT IN REMITTANCE CURRENCY", 132, 12, pData.substring(132, 144)));
            fields.add(new IpmField(18, "NUMBER OF TRANSACTION IN REMITTANCE", 144, 3, pData.substring(144, 147)));
            fields.add(new IpmField(19, "REMITTANCE NUMBER", 147, 6, pData.substring(147, 153)));
            fields.add(new IpmField(20, "REMITTANCE DEPOSIT DATE", 153, 6, pData.substring(153, 159)));
            fields.add(new IpmField(21, "AMOUNT IN REMITTANCE CURRENCY", 159, 12, pData.substring(159, 171)));
            fields.add(new IpmField(22, "NUMBER OF TRANSACTION IN REMITTANCE", 171, 3, pData.substring(171, 174)));
            fields.add(new IpmField(23, "REMITTANCE NUMBER", 174, 6, pData.substring(174, 180)));
            fields.add(new IpmField(24, "REMITTANCE DEPOSIT DATE", 180, 6, pData.substring(180, 186)));
            fields.add(new IpmField(25, "AMOUNT IN REMITTANCE CURRENCY", 186, 12, pData.substring(186, 198)));
            fields.add(new IpmField(26, "NUMBER OF TRANSACTION IN REMITTANCE", 198, 3, pData.substring(198, 201)));
            fields.add(new IpmField(27, "REMITTANCE NUMBER", 201, 6, pData.substring(201, 207)));
            fields.add(new IpmField(28, "REMITTANCE DEPOSIT DATE", 207, 6, pData.substring(207, 213)));
            fields.add(new IpmField(29, "AMOUNT IN REMITTANCE CURRENCY", 213, 12, pData.substring(213, 225)));
            fields.add(new IpmField(30, "NUMBER OF TRANSACTION IN REMITTANCE", 225, 3, pData.substring(225, 228)));
            fields.add(new IpmField(31, "REMITTANCE NUMBER", 228, 6, pData.substring(228, 234)));
            fields.add(new IpmField(32, "REMITTANCE DEPOSIT DATE", 234, 6, pData.substring(234, 240)));
            fields.add(new IpmField(33, "AMOUNT IN REMITTANCE CURRENCY", 240, 12, pData.substring(240, 252)));
            fields.add(new IpmField(34, "NUMBER OF TRANSACTION IN REMITTANCE", 252, 3, pData.substring(252, 255)));
            fields.add(new IpmField(35, "FILLER", 255, 1, pData.substring(255, 256)));

        }else if(
                (          pData.substring(0,2).equals("05")
                        || pData.substring(0,2).equals("06")
                        || pData.substring(0,2).equals("07")
                        || pData.substring(0,2).equals("15")
                        || pData.substring(0,2).equals("16")
                        || pData.substring(0,2).equals("17")
                        || pData.substring(0,2).equals("25")
                        || pData.substring(0,2).equals("26")
                        || pData.substring(0,2).equals("27")
                        || pData.substring(0,2).equals("35")
                        || pData.substring(0,2).equals("36")
                        || pData.substring(0,2).equals("37")
                        || pData.substring(0,2).equals("09")
                        || pData.substring(0,2).equals("19")
                )
                        && pData.substring(8,9).equals("0")
        ){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "TRANSACTION ROUTE INDICATOR", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(5, "MERCHANT ESTABLISHMENT NUMBER", 10, 15, pData.substring(10, 25)));
            fields.add(new IpmField(6, "MERCHANT NAME", 25, 25, pData.substring(25, 50)));
            fields.add(new IpmField(7, "MERCHANT CITY", 50, 13, pData.substring(50, 63)));
            fields.add(new IpmField(8, "MERCHANT COUNTRY CODE", 63, 3, pData.substring(63, 66)));
            fields.add(new IpmField(9, "MERCHANT CATEGORY CODE", 66, 4, pData.substring(66, 70)));
            fields.add(new IpmField(10, "TYPE OF MERCHANT", 70, 1, pData.substring(70, 71)));
            fields.add(new IpmField(11, "SPECIAL MERCHANT CONDITION INDICATOR", 71, 2, pData.substring(71, 73)));
            fields.add(new IpmField(12, "ELECTRONIC TERMINAL INDICATOR", 73, 7, pData.substring(73, 80)));
            fields.add(new IpmField(13, "CARD ACCEPTOR TERMINAL IDENTIFICATION", 74, 8, pData.substring(74, 82)));
            fields.add(new IpmField(14, "USAGE CODE", 82, 1, pData.substring(82, 83)));
            fields.add(new IpmField(15, "RECONCILIATION INDICATOR", 83, 3, pData.substring(83, 86)));
            fields.add(new IpmField(16, "MEMBER MESSAGE TEXT", 86, 50, pData.substring(86, 136)));
            fields.add(new IpmField(17, "REASON CODE FOR CHARGEBACK AND REPRESENTATION", 136, 4, pData.substring(136, 140)));
            fields.add(new IpmField(18, "CHARGEBACK REFERENCE NUMBER", 140, 6, pData.substring(140, 146)));
            fields.add(new IpmField(19, "DOCUMENTATION INDICATOR", 146, 7, pData.substring(146, 153)));
            fields.add(new IpmField(20, "PAYMENT PRODUCT INDICATOR (PPI)", 147, 7, pData.substring(147, 154)));
            fields.add(new IpmField(21, "CARDHOLDER CARD NUMBER INVOLVED IN TRANSACTION", 148, 19, pData.substring(148, 167)));
            fields.add(new IpmField(22, "CARDHOLDER CARD NUMBER EXPIRY DATE", 167, 4, pData.substring(167, 171)));
            fields.add(new IpmField(23, "CARDHOLDER IDENTIFICATION METHOD INDICATOR", 171, 1, pData.substring(171, 172)));
            fields.add(new IpmField(24, "CARDHOLDER CARD NUMBER CAPTURE INDICATOR", 172, 1, pData.substring(172, 173)));
            fields.add(new IpmField(25, "ATM ACCOUNT SELECTION", 173, 2, pData.substring(173, 175)));
            fields.add(new IpmField(26, "TRANSACTION STATUS", 175, 5, pData.substring(175, 180)));
            fields.add(new IpmField(27, "TRANSACTION CODE HEADER", 180, 2, pData.substring(180, 182)));
            fields.add(new IpmField(28, "PRODUCT TYPE", 182, 2, pData.substring(182, 184)));
            fields.add(new IpmField(29, "TRANSACTION TYPE", 184, 1, pData.substring(184, 185)));
            fields.add(new IpmField(30, "FEES AMOUNT", 185, 18, pData.substring(185, 203)));
            fields.add(new IpmField(31, "FILLER", 203, 53, pData.substring(203, 256)));
        }else if(
                (          pData.substring(0,2).equals("05")
                        || pData.substring(0,2).equals("06")
                        || pData.substring(0,2).equals("07")
                        || pData.substring(0,2).equals("15")
                        || pData.substring(0,2).equals("16")
                        || pData.substring(0,2).equals("17")
                        || pData.substring(0,2).equals("25")
                        || pData.substring(0,2).equals("26")
                        || pData.substring(0,2).equals("27")
                        || pData.substring(0,2).equals("35")
                        || pData.substring(0,2).equals("36")
                        || pData.substring(0,2).equals("37")
                        || pData.substring(0,2).equals("09")
                        || pData.substring(0,2).equals("19")
                )
                        && pData.substring(8,9).equals("1")
        ){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "TRANSACTION DATE", 9, 6, pData.substring(9, 15)));
            fields.add(new IpmField(5, "AUTHORISATION CODE", 15, 6, pData.substring(15, 21)));
            fields.add(new IpmField(6, "AUTHORISATION CODE SOURCE INDICATOR", 21, 1, pData.substring(21, 22)));
            fields.add(new IpmField(7, "TRANSACTION TYPE", 22, 1, pData.substring(22, 23)));
            fields.add(new IpmField(8, "ACQUIRER’S REFERENCE NUMBER", 23, 23, pData.substring(23, 46)));
            fields.add(new IpmField(9, "FORWARDING INSTITUTION IDENTIFICATION (FID)", 46, 8, pData.substring(46, 54)));
            fields.add(new IpmField(10, "VOID INDICATOR", 54, 4, pData.substring(54, 58)));
            fields.add(new IpmField(11, "SOURCE CURRENCY EXPONENT", 55, 4, pData.substring(55, 59)));
            fields.add(new IpmField(12, "SOURCE AMOUNT", 56, 12, pData.substring(56, 68)));
            fields.add(new IpmField(13, "RECEIVING INSTITUTION IDENTIFICATION (RID)", 68, 8, pData.substring(68, 76)));
            fields.add(new IpmField(14, "SPECIAL CHARGEBACK INDICATOR", 76, 1, pData.substring(76, 77)));
            fields.add(new IpmField(15, "DESTINATION CURRENCY EXPONENT", 77, 1, pData.substring(77, 78)));
            fields.add(new IpmField(16, "DESTINATION AMOUNT", 78, 12, pData.substring(78, 90)));
            fields.add(new IpmField(17, "SOURCE OR DESTINATION AMOUNT IN LOCAL CURRENCY (CFA)", 90, 12, pData.substring(90, 102)));
            fields.add(new IpmField(18, "ISSUER REIMBURSEMENT FEE (IRF)", 102, 12, pData.substring(102, 114)));
            fields.add(new IpmField(19, "VALUE DATE", 114, 6, pData.substring(114, 120)));
            fields.add(new IpmField(20, "TRANSACTION INTERCHANGE PROCESSING DATE", 120, 6, pData.substring(120, 126)));
            fields.add(new IpmField(21, "MERCHANT STATE/PROVINCE CODE", 126, 3, pData.substring(126, 129)));
            fields.add(new IpmField(22, "FILLER", 129, 1, pData.substring(129, 130)));
            fields.add(new IpmField(23, "VOUCHER DEPOSITING BANK CODE", 130, 2, pData.substring(130, 132)));
            fields.add(new IpmField(24, "VOUCHER DEPOSITING BRANCH CODE", 132, 4, pData.substring(132, 136)));
            fields.add(new IpmField(25, "CARD SEQUENCE NUMBER", 136, 3, pData.substring(136, 139)));
            fields.add(new IpmField(26, "RECONCILIATION DATE", 139, 6, pData.substring(139, 145)));
            fields.add(new IpmField(27, "RETRIEVAL REFERENCE NUMBER", 145, 12, pData.substring(145, 157)));
            fields.add(new IpmField(28, "TRANSACTION TIME", 157, 6, pData.substring(157, 163)));
            fields.add(new IpmField(29, "SOURCE CURRENCY CODE", 163, 3, pData.substring(163, 166)));
            fields.add(new IpmField(30, "DESTINATION CURRENCY CODE", 166, 3, pData.substring(166, 169)));
            fields.add(new IpmField(31, "MERCHANT SERVICE CHARGE (MSC)", 169, 12, pData.substring(169, 181)));
            fields.add(new IpmField(32, "ACQUIRER MSC REVENUE", 181, 12, pData.substring(181, 193)));
            fields.add(new IpmField(33, "ELECTRONIC COMMERCE (EC) INDICATOR", 193, 1, pData.substring(193, 194)));
            fields.add(new IpmField(34, "CARDHOLDER BILLING AMOUNT", 194, 12, pData.substring(194, 206)));
            fields.add(new IpmField(35, "EXCHANGE RATE FROM SOURCE AMOUNT OR DESTINATION AMOUNT CURRENCY TO LOCAL CURRENCY (CFA)", 206, 9, pData.substring(206, 215)));
            fields.add(new IpmField(36, "EXCHANGE RATE FROM LOCAL CURRENCY(CFA) TO DESTINATION AMOUNT CURRENCY", 215, 9, pData.substring(215, 224)));
            fields.add(new IpmField(37, "FILLER", 224, 32, pData.substring(224, 256)));
        }else if(
                (          pData.substring(0,2).equals("05")
                        || pData.substring(0,2).equals("06")
                        || pData.substring(0,2).equals("15")
                        || pData.substring(0,2).equals("16")
                        || pData.substring(0,2).equals("25")
                        || pData.substring(0,2).equals("26")
                        || pData.substring(0,2).equals("35")
                        || pData.substring(0,2).equals("36")
                )
                        && pData.substring(8,9).equals("2")
        ){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "PASSENGER NAME", 9, 26, pData.substring(9, 35)));
            fields.add(new IpmField(5, "ORIGIN CITY/AIRPORT CODE", 35, 3, pData.substring(35, 38)));
            fields.add(new IpmField(6, "TRIP LEGS INFORMATION", 38, 72, pData.substring(38, 110)));
            fields.add(new IpmField(7, "TRAVEL AGENCY CODE", 110, 8, pData.substring(110, 118)));
            fields.add(new IpmField(8, "TRAVEL AGENCY NAME", 118, 25, pData.substring(118, 143)));
            fields.add(new IpmField(9, "TICKET NUMBER", 143, 14, pData.substring(143, 157)));
            fields.add(new IpmField(10, "RESTRICTED TICKET INDICATOR", 157, 1, pData.substring(157, 158)));
            fields.add(new IpmField(11, "ADDITIONAL DATA", 158, 12, pData.substring(158, 170)));
            fields.add(new IpmField(12, "FILLER", 170, 86, pData.substring(170, 256)));
        }else if(
                (          pData.substring(0,2).equals("05")
                        || pData.substring(0,2).equals("06")
                        || pData.substring(0,2).equals("07")
                        || pData.substring(0,2).equals("15")
                        || pData.substring(0,2).equals("16")
                        || pData.substring(0,2).equals("17")
                        || pData.substring(0,2).equals("25")
                        || pData.substring(0,2).equals("26")
                        || pData.substring(0,2).equals("27")
                        || pData.substring(0,2).equals("35")
                        || pData.substring(0,2).equals("36")
                        || pData.substring(0,2).equals("37")
                        || pData.substring(0,2).equals("09")
                        || pData.substring(0,2).equals("19")
                )
                        && pData.substring(8,9).equals("3")
        ){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "APPLICATION CRYPTOGRAM", 9, 16, pData.substring(9, 25)));
            fields.add(new IpmField(5, "CRYPTOGRAM INFORMATION DATA", 25, 2, pData.substring(25, 27)));
            fields.add(new IpmField(6, "ISSUER APPLICATION DATA", 27, 64, pData.substring(27, 91)));
            fields.add(new IpmField(7, "UNPREDICTABLE NUMBER", 91, 8, pData.substring(91, 99)));
            fields.add(new IpmField(8, "APPLICATION TRANSACTION COUNTER", 99, 4, pData.substring(99, 103)));
            fields.add(new IpmField(9, "TERMINAL VERIFICATION RESULTS", 103, 10, pData.substring(103, 113)));
            fields.add(new IpmField(10, "TRANSACTION DATE", 113, 6, pData.substring(113, 119)));
            fields.add(new IpmField(11, "CRYPTOGRAM AMOUNT", 119, 12, pData.substring(119, 131)));
            fields.add(new IpmField(12, "TRANSACTION CURRENCY CODE", 131, 3, pData.substring(131, 134)));
            fields.add(new IpmField(13, "APPLICATION INTERCHANGE PROFILE", 134, 4, pData.substring(134, 138)));
            fields.add(new IpmField(14, "TERMINAL COUNTRY CODE", 138, 3, pData.substring(138, 141)));
            fields.add(new IpmField(15, "AMOUNT OTHER (CASH BACK AMOUNT)", 141, 12, pData.substring(141, 153)));
            fields.add(new IpmField(16, "TRANSACTION TYPE", 153, 2, pData.substring(153, 155)));
            fields.add(new IpmField(17, "CARDHOLDER VERIFICATION METHOD", 155, 6, pData.substring(155, 161)));
            fields.add(new IpmField(18, "TERMINAL CAPABILITIES", 161, 6, pData.substring(161, 167)));
            fields.add(new IpmField(19, "TERMINAL TYPE", 167, 2, pData.substring(167, 169)));
            fields.add(new IpmField(20, "TRANSACTION CATEGORY CODE", 169, 1, pData.substring(169, 170)));
            fields.add(new IpmField(21, "TRANSACTION SEQUENCE NUMBER", 170, 8, pData.substring(170, 178)));
            fields.add(new IpmField(22, "ISSUER AUTHENTICATION DATA", 178, 32, pData.substring(178, 210)));
            fields.add(new IpmField(23, "ISSUER SCRIPT RESULTS", 210, 10, pData.substring(210, 220)));
            fields.add(new IpmField(24, "CARD SEQUENCE NUMBER", 220, 3, pData.substring(220, 223)));
            fields.add(new IpmField(25, "FILLER", 223, 33, pData.substring(223, 256)));
        }else if(
                (          pData.substring(0,2).equals("10")
                        || pData.substring(0,2).equals("20")
                )
                        && pData.substring(8,9).equals("0")
        ){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "TRANSACTION ROUTE INDICATOR", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(5, "PAYMENT PRODUCT INDICATOR", 10, 1, pData.substring(10, 11)));
            fields.add(new IpmField(6, "FORWARDING INSTITUTION IDENTIFICATION", 11, 8, pData.substring(11, 19)));
            fields.add(new IpmField(7, "FILLER", 19, 1, pData.substring(19, 20)));
            fields.add(new IpmField(8, "SOURCE CURRENCY EXPONENT", 20, 1, pData.substring(20, 21)));
            fields.add(new IpmField(9, "SOURCE AMOUNT", 21, 12, pData.substring(21, 33)));
            fields.add(new IpmField(10, "RECEIVING INSTITUTION IDENTIFICATION", 33, 8, pData.substring(33, 41)));
            fields.add(new IpmField(11, "PROCESSING AND AUTHORISATION FEES TYPE INDICATOR", 41, 1, pData.substring(41, 42)));
            fields.add(new IpmField(12, "DESTINATION CURRENCY CODE EXPONENT", 42, 1, pData.substring(42, 43)));
            fields.add(new IpmField(13, "DESTINATION AMOUNT", 43, 12, pData.substring(43, 55)));
            fields.add(new IpmField(14, "CARDHOLDER CARD NUMBER INVOLVED IN FEE", 55, 19, pData.substring(55, 74)));
            fields.add(new IpmField(15, "COUNTRY CODE OF FORWARDING INSTITUTION", 74, 3, pData.substring(74, 77)));
            fields.add(new IpmField(16, "REASON CODE FOR FEE", 77, 4, pData.substring(77, 81)));
            fields.add(new IpmField(17, "COLLECTION BRANCH CODE", 81, 4, pData.substring(81, 85)));
            fields.add(new IpmField(18, "NUMBER OF TRANSACTIONS", 85, 8, pData.substring(85, 93)));
            fields.add(new IpmField(19, "UNIT FEE", 93, 9, pData.substring(93, 102)));
            fields.add(new IpmField(20, "EVENT DATE", 102, 6, pData.substring(102, 108)));
            fields.add(new IpmField(21, "TRANSACTION INTERCHANGE PROCESSING DATE", 108, 6, pData.substring(108, 114)));
            fields.add(new IpmField(22, "FILLER", 114, 1, pData.substring(114, 115)));
            fields.add(new IpmField(23, "SOURCE AMOUNT IN CFA", 115, 12, pData.substring(115, 127)));
            fields.add(new IpmField(24, "VALUE DATE", 127, 6, pData.substring(127, 133)));
            fields.add(new IpmField(25, "CONTROL NUMBER", 133, 14, pData.substring(133, 147)));
            fields.add(new IpmField(26, "RECONCILIATION DATE", 147, 6, pData.substring(147, 153)));
            fields.add(new IpmField(27, "RECONCILIATION INDICATOR", 153, 3, pData.substring(153, 156)));
            fields.add(new IpmField(28, "ORIGINAL CARD SEQUENCE NUMBER", 156, 3, pData.substring(156, 159)));
            fields.add(new IpmField(29, "SOURCE CURRENCY CODE", 159, 3, pData.substring(159, 162)));
            fields.add(new IpmField(30, "DESTINATION CURRENCY CODE", 162, 3, pData.substring(162, 165)));
            fields.add(new IpmField(31, "FILLER", 165, 91, pData.substring(165, 256)));
        }else if(
                (          pData.substring(0,2).equals("10")
                        || pData.substring(0,2).equals("20")
                )
                        && pData.substring(8,9).equals("1")
        ){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "MEMBER MESSAGE TEXT", 9, 100, pData.substring(9, 109)));
            fields.add(new IpmField(5, "FILLER", 109, 147, pData.substring(109, 256)));
        }else if(pData.substring(0,2).equals("40")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "TRANSACTION ROUTE INDICATOR", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(5, "FORWARDING INSTITUTION IDENTIFICATION", 10, 8, pData.substring(10, 18)));
            fields.add(new IpmField(6, "RECEIVING INSTITUTION IDENTIFICATION", 18, 8, pData.substring(18, 26)));
            fields.add(new IpmField(7, "CARDHOLDER CARD NUMBER", 26, 19, pData.substring(26, 45)));
            fields.add(new IpmField(8, "ACQUIRER'S (MICROFILM) REFERENCE NUMBER", 45, 23, pData.substring(45, 68)));
            fields.add(new IpmField(9, "TRANSACTION DATE", 68, 6, pData.substring(68, 74)));
            fields.add(new IpmField(10, "MERCHANT NAME", 74, 25, pData.substring(74, 99)));
            fields.add(new IpmField(11, "MERCHANT CITY", 99, 13, pData.substring(99, 112)));
            fields.add(new IpmField(12, "MERCHANT COUNTRY CODE", 112, 3, pData.substring(112, 115)));
            fields.add(new IpmField(13, "MERCHANT CATEGORY CODE", 115, 4, pData.substring(115, 119)));
            fields.add(new IpmField(14, "MERCHANT STATE OR PROVINCE CODE", 119, 3, pData.substring(119, 122)));
            fields.add(new IpmField(15, "FRAUD AMOUNT", 122, 12, pData.substring(122, 134)));
            fields.add(new IpmField(16, "FRAUD CURRENCY CODE", 134, 3, pData.substring(134, 137)));
            fields.add(new IpmField(17, "VIC PROCESSING DATE", 137, 6, pData.substring(137, 143)));
            fields.add(new IpmField(18, "NOTIFICATION CODE", 143, 1, pData.substring(143, 144)));
            fields.add(new IpmField(19, "ACCOUNT SEQUENCE NUMBER", 144, 4, pData.substring(144, 148)));
            fields.add(new IpmField(20, "INSURANCE YEAR", 148, 2, pData.substring(148, 150)));
            fields.add(new IpmField(21, "FRAUD TYPE", 150, 1, pData.substring(150, 151)));
            fields.add(new IpmField(22, "CARDHOLDER CARD NUMBER EXPIRY DATE", 151, 4, pData.substring(151, 155)));
            fields.add(new IpmField(23, "DEBIT/CREDIT INDICATOR", 155, 1, pData.substring(155, 156)));
            fields.add(new IpmField(24, "TRANSACTION GENERATION METHOD", 156, 1, pData.substring(156, 157)));
            fields.add(new IpmField(25, "ELECTRONIC COMMERCE INDICATOR", 157, 1, pData.substring(157, 158)));
            fields.add(new IpmField(26, "FILLER", 158, 98, pData.substring(158, 256)));
        }else if(pData.substring(0,2).equals("47")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "TRANSACTION TYPE", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(5, "MERCHANT ESTABLISHMENT NUMBER", 10, 15, pData.substring(10, 25)));
            fields.add(new IpmField(6, "TERMINAL NUMBER", 25, 15, pData.substring(25, 40)));
            fields.add(new IpmField(7, "MERCHANT NAME", 40, 25, pData.substring(40, 65)));
            fields.add(new IpmField(8, "MERCHANT CITY", 65, 13, pData.substring(65, 78)));
            fields.add(new IpmField(9, "MERCHANT COUNTRY CODE", 78, 3, pData.substring(78, 81)));
            fields.add(new IpmField(10, "MERCHANT CATEGORY CODE", 81, 4, pData.substring(81, 85)));
            fields.add(new IpmField(11, "TYPE OF MERCHANT", 85, 1, pData.substring(85, 86)));
            fields.add(new IpmField(12, "CARDHOLDER CARD NUMBER INVOLVED IN TRANSACTION", 86, 19, pData.substring(86, 105)));
            fields.add(new IpmField(13, "CARDHOLDER CARD NUMBER EXPIRY DATE", 105, 4, pData.substring(105, 109)));
            fields.add(new IpmField(14, "CARDHOLDER IDENTIFICATION METHOD INDICATOR", 109, 1, pData.substring(109, 110)));
            fields.add(new IpmField(15, "CARDHOLDER CARD NUMBER CAPTURE INDICATOR", 110, 1, pData.substring(110, 111)));
            fields.add(new IpmField(16, "TRANSACTION DATE", 111, 8, pData.substring(111, 119)));
            fields.add(new IpmField(17, "TRANSACTION AMOUNT", 119, 12, pData.substring(119, 131)));
            fields.add(new IpmField(18, "ACQUIRER'S (MICROFILM) REFERENCE NUMBER", 131, 23, pData.substring(131, 154)));
            fields.add(new IpmField(19, "TRANSACTION TIME", 154, 6, pData.substring(154, 160)));
            fields.add(new IpmField(20, "TRANSACTION CURRENCY CODE", 160, 3, pData.substring(160, 163)));
        }else if(pData.substring(0,2).equals("48")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "TRANSACTION ROUTE INDICATOR", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(5, "FORWARDING INSTITUTION IDENTIFICATION", 10, 8, pData.substring(10, 18)));
            fields.add(new IpmField(6, "RECEIVING INSTITUTION IDENTIFICATION", 18, 8, pData.substring(18, 26)));
            fields.add(new IpmField(7, "CARDHOLDER CARD NUMBER", 26, 19, pData.substring(26, 45)));
            fields.add(new IpmField(8, "RESPONSE CODE", 45, 3, pData.substring(45, 48)));
            fields.add(new IpmField(9, "AUTHORISATION CODE", 48, 6, pData.substring(48, 54)));
            fields.add(new IpmField(10, "STAND-IN RESPONSE CODE", 54, 1, pData.substring(54, 55)));
            fields.add(new IpmField(11, "TRANSMISSION DATE AND TIME", 55, 10, pData.substring(55, 65)));
            fields.add(new IpmField(12, "TRANSACTION AMOUNTACQUIRER CURRENCY", 65, 12, pData.substring(65, 77)));
            fields.add(new IpmField(13, "ISSUER CURRENCY CODE", 77, 3, pData.substring(77, 80)));
            fields.add(new IpmField(14, "ACQUIRER CURRENCY CODE", 80, 3, pData.substring(80, 83)));
            fields.add(new IpmField(15, "CARDHOLDER BILLING CONVERSION RATE", 83, 8, pData.substring(83, 91)));
            fields.add(new IpmField(16, "CARDHOLDER CARDNUMBER EXPIRY DATE", 91, 4, pData.substring(91, 95)));
            fields.add(new IpmField(17, "ACQUIRING INSTITUTION IDENTIFICATION", 95, 11, pData.substring(95, 106)));
            fields.add(new IpmField(18, "ACQUIRING INSTITUTION COUNTRY CODE", 106, 3, pData.substring(106, 109)));
            fields.add(new IpmField(19, "MESSAGE TYPE", 109, 4, pData.substring(109, 113)));
            fields.add(new IpmField(20, "PROCESSING CODE", 113, 4, pData.substring(113, 117)));
            fields.add(new IpmField(21, "MERCHANT TYPE", 117, 4, pData.substring(117, 121)));
            fields.add(new IpmField(22, "POS ENTRY MODE", 121, 2, pData.substring(121, 123)));
            fields.add(new IpmField(23, "CARD ACCEPTOR TERMINAL ID", 123, 9, pData.substring(123, 132)));
            fields.add(new IpmField(24, "CARD ACCEPTOR ID", 132, 15, pData.substring(132, 147)));
            fields.add(new IpmField(25, "RETRIEVAL REFERENCE NUMBER", 147, 12, pData.substring(147, 159)));
            fields.add(new IpmField(26, "TRACE AUDIT NUMBER", 159, 6, pData.substring(159, 165)));
            fields.add(new IpmField(27, "ELECTRONIC COMMERCE (EC) INDICATOR", 165, 1, pData.substring(165, 166)));
            fields.add(new IpmField(28, "CVV RESULTS CODE", 166, 1, pData.substring(166, 167)));

        }else if(   pData.substring(0,2).equals("49")
                && pData.substring(8,9).equals("0")){
            fields.add(new IpmField(1, "FILE RECORD SEQUENCE NUMBER", 0, 6, description));
            fields.add(new IpmField(2, "TCR SEQUENCE NUMBER", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(3, "TRANSACTION TYPE", 10, 2, pData.substring(10, 12)));
            fields.add(new IpmField(4, "MERCHANT ESTABLISHMENT NUMBER", 12, 15, pData.substring(12, 27)));
            fields.add(new IpmField(5, "TERMINAL NUMBER", 27, 15, pData.substring(27, 42)));
            fields.add(new IpmField(6, "MERCHANT NAME", 42, 25, pData.substring(42, 67)));
            fields.add(new IpmField(7, "MERCHANT CITY", 67, 13, pData.substring(67, 80)));
            fields.add(new IpmField(8, "MERCHANT COUNTRY CODE", 80, 3, pData.substring(80, 83)));
            fields.add(new IpmField(9, "MERCHANT CATEGORY CODE", 83, 4, pData.substring(83, 87)));
            fields.add(new IpmField(10, "TYPE OF MERCHANT", 87, 1, pData.substring(87, 88)));
            fields.add(new IpmField(11, "REMITTANCE NUMBER", 88, 6, pData.substring(88, 94)));
            fields.add(new IpmField(12, "ELECTRONIC TERMINAL INDICATOR", 94, 1, pData.substring(94, 95)));
            fields.add(new IpmField(13, "FILLER", 95, 3, pData.substring(95, 98)));
            fields.add(new IpmField(14, "CARD INDICATOR", 98, 1, pData.substring(98, 99)));
            fields.add(new IpmField(15, "PAYMENT PRODUCT INDICATOR", 99, 1, pData.substring(99, 100)));
            fields.add(new IpmField(16, "CARDHOLDER CARD NUMBER INVOLVED IN TRANSACTION", 100, 19, pData.substring(100, 119)));
            fields.add(new IpmField(17, "CARDHOLDER CARD NUMBER EXPIRY DATE", 119, 4, pData.substring(119, 123)));
            fields.add(new IpmField(18, "CARDHOLDER IDENTIFICATION METHOD INDICATOR", 123, 1, pData.substring(123, 124)));
            fields.add(new IpmField(19, "CARDHOLDER CARD NUMBER CAPTURE INDICATOR", 124, 1, pData.substring(124, 125)));
            fields.add(new IpmField(20, "TRANSACTION DATE", 125, 6, pData.substring(125, 131)));
            fields.add(new IpmField(21, "AUTHORISATION CODE", 131, 6, pData.substring(131, 137)));
            fields.add(new IpmField(22, "AUTHORISATION CODE SOURCE INDICATOR", 137, 1, pData.substring(137, 138)));
            fields.add(new IpmField(23, "FILLER", 138, 1, pData.substring(138, 139)));
            fields.add(new IpmField(24, "TRANSACTION AMOUNT", 139, 12, pData.substring(139, 151)));
            fields.add(new IpmField(25, "RETRIEVAL REFERENCE NUMBER", 151, 12, pData.substring(151, 163)));
            fields.add(new IpmField(26, "ACQUIRER�S (MICROFILM) REFERENCE NUMBER", 163, 23, pData.substring(163, 186)));
            fields.add(new IpmField(27, "CARD ACCEPTOR TERMINAL IDENTIFICATION", 186, 8, pData.substring(186, 194)));
            fields.add(new IpmField(28, "VOUCHER NUMBER", 194, 8, pData.substring(194, 202)));
            fields.add(new IpmField(29, "TRANSACTION TIME", 202, 6, pData.substring(202, 208)));
            fields.add(new IpmField(30, "TRANSACTION CURRENCY CODE", 208, 3, pData.substring(208, 211)));
            fields.add(new IpmField(31, "DESTINATION AMOUNT", 211, 12, pData.substring(211, 223)));
            fields.add(new IpmField(32, "DESTINATION CURRENCY CODE", 223, 3, pData.substring(223, 226)));
            fields.add(new IpmField(33, "DESTINATION CURRENCY EXPONENT", 226, 1, pData.substring(226, 227)));
            fields.add(new IpmField(34, "INTERCHANGE FEES", 227, 12, pData.substring(227, 239)));
            fields.add(new IpmField(35, "INTERCHANGE FEES CURRENCY", 239, 3, pData.substring(239, 242)));
            fields.add(new IpmField(36, "INTERCHANGE FEES CURRENCY EXPONENT", 242, 1, pData.substring(242, 243)));
            fields.add(new IpmField(37, "INTERCHANGE FEES SIGN", 243, 1, pData.substring(243, 244)));
         //   fields.add(new IpmField(38, "FILLER", 244, 13, pData.substring(244, 257)));
        }else if(   pData.substring(0,2).equals("49")
                && pData.substring(8,9).equals("1")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "PASSENGER NAME", 9, 26, pData.substring(9, 35)));
            fields.add(new IpmField(5, "ORIGIN CITY/AIRPORT CODE", 35, 3, pData.substring(35, 38)));
            fields.add(new IpmField(6, "TRIP LEGS INFORMATION", 38, 72, pData.substring(38, 110)));
            fields.add(new IpmField(7, "TRAVEL AGENCY CODE", 110, 8, pData.substring(110, 118)));
            fields.add(new IpmField(8, "TRAVEL AGENCY NAME", 118, 25, pData.substring(118, 143)));
            fields.add(new IpmField(9, "TICKET NUMBER", 143, 14, pData.substring(143, 157)));
            fields.add(new IpmField(10, "RESTRICTED TICKET INDICATOR", 157, 1, pData.substring(157, 158)));
            fields.add(new IpmField(11, "ADDITIONAL DATA", 158, 12, pData.substring(158, 170)));
            fields.add(new IpmField(12, "FILLER", 170, 86, pData.substring(170, 256)));

        }	else if(   pData.substring(0,2).equals("49")
                && pData.substring(8,9).equals("2")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "APPLICATION CRYPTOGRAM", 9, 16, pData.substring(9, 25)));
            fields.add(new IpmField(5, "CRYPTOGRAM INFORMATION DATA", 25, 2, pData.substring(25, 27)));
            fields.add(new IpmField(6, "ISSUER APPLICATION DATA", 27, 64, pData.substring(27, 91)));
            fields.add(new IpmField(7, "UNPREDICTABLE NUMBER", 91, 8, pData.substring(91, 99)));
            fields.add(new IpmField(8, "APPLICATION TRANSACTION COUNTER", 99, 4, pData.substring(99, 103)));
            fields.add(new IpmField(9, "TERMINAL VERIFICATION RESULTS", 103, 10, pData.substring(103, 113)));
            fields.add(new IpmField(10, "TRANSACTION DATE", 113, 6, pData.substring(113, 119)));
            fields.add(new IpmField(11, "CRYPTOGRAM AMOUNT", 119, 12, pData.substring(119, 131)));
            fields.add(new IpmField(12, "TRANSACTION CURRENCY CODE", 131, 3, pData.substring(131, 134)));
            fields.add(new IpmField(13, "APPLICATION INTERCHANGE PROFILE", 134, 4, pData.substring(134, 138)));
            fields.add(new IpmField(14, "TERMINAL COUNTRY CODE", 138, 3, pData.substring(138, 141)));
            fields.add(new IpmField(15, "AMOUNT OTHER (CASH BACK AMOUNT)", 141, 12, pData.substring(141, 153)));
            fields.add(new IpmField(16, "TRANSACTION TYPE", 153, 2, pData.substring(153, 155)));
            fields.add(new IpmField(17, "CARDHOLDER VERIFICATION METHOD", 155, 6, pData.substring(155, 161)));
            fields.add(new IpmField(18, "TERMINAL CAPABILITIES", 161, 6, pData.substring(161, 167)));
            fields.add(new IpmField(19, "TERMINAL TYPE", 167, 2, pData.substring(167, 169)));
            fields.add(new IpmField(20, "TRANSACTION CATEGORY CODE", 169, 1, pData.substring(169, 170)));
            fields.add(new IpmField(21, "TRANSACTION SEQUENCE NUMBER", 170, 8, pData.substring(170, 178)));
            fields.add(new IpmField(22, "ISSUER AUTHENTICATION DATA", 178, 32, pData.substring(178, 210)));
            fields.add(new IpmField(23, "ISSUER SCRIPT RESULTS", 210, 10, pData.substring(210, 220)));
            fields.add(new IpmField(24, "CARD SEQUENCE NUMBER", 220, 3, pData.substring(220, 223)));
            fields.add(new IpmField(25, "FILLER", 223, 33, pData.substring(223, 256)));
        }else if(pData.substring(0,2).equals("50")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "TRANSACTION ROUTE INDICATOR", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(5, "FORWARDING INSTITUTION IDENTIFICATION", 10, 8, pData.substring(10, 18)));
            fields.add(new IpmField(6, "RECEIVING INSTITUTION IDENTIFICATION", 18, 8, pData.substring(18, 26)));
            fields.add(new IpmField(7, "CARDHOLDER CARD NUMBER", 26, 19, pData.substring(26, 45)));
            fields.add(new IpmField(8, "ACQUIRER'S (MICROFILM) REFERENCE NUMBER", 45, 23, pData.substring(45, 68)));
            fields.add(new IpmField(9, "TRANSACTION DATE", 68, 6, pData.substring(68, 74)));
            fields.add(new IpmField(10, "MERCHANT NAME", 74, 25, pData.substring(74, 99)));
            fields.add(new IpmField(11, "MERCHANT CITY", 99, 13, pData.substring(99, 112)));
            fields.add(new IpmField(12, "MERCHANT COUNTRY CODE", 112, 3, pData.substring(112, 115)));
            fields.add(new IpmField(13, "MERCHANT CATEGORY CODE", 115, 4, pData.substring(115, 119)));
            fields.add(new IpmField(14, "MERCHANT STATE OR PROVINCE CODE", 119, 3, pData.substring(119, 122)));
            fields.add(new IpmField(15, "FRAUD AMOUNT", 122, 12, pData.substring(122, 134)));
            fields.add(new IpmField(16, "FRAUD CURRENCY CODE", 134, 3, pData.substring(134, 137)));
            fields.add(new IpmField(17, "VIC PROCESSING DATE", 137, 6, pData.substring(137, 143)));
            fields.add(new IpmField(18, "NOTIFICATION CODE", 143, 1, pData.substring(143, 144)));
            fields.add(new IpmField(19, "ACCOUNT SEQUENCE NUMBER", 144, 4, pData.substring(144, 148)));
            fields.add(new IpmField(20, "INSURANCE YEAR", 148, 2, pData.substring(148, 150)));
            fields.add(new IpmField(21, "FRAUD TYPE", 150, 1, pData.substring(150, 151)));
            fields.add(new IpmField(22, "CARDHOLDER CARD NUMBER EXPIRY DATE", 151, 4, pData.substring(151, 155)));
            fields.add(new IpmField(23, "DEBIT/CREDIT INDICATOR", 155, 1, pData.substring(155, 156)));
            fields.add(new IpmField(24, "TRANSACTION GENERATION METHOD", 156, 1, pData.substring(156, 157)));
            fields.add(new IpmField(25, "ELECTRONIC COMMERCE INDICATOR", 157, 1, pData.substring(157, 158)));
            fields.add(new IpmField(26, "FILLER", 158, 98, pData.substring(158, 256)));
        }else if(   pData.substring(0,2).equals("51")
                || pData.substring(0,2).equals("52")
                || pData.substring(0,2).equals("53")
        ){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "TRANSACTION ROUTE INDICATOR", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(5, "DOCUMENT TYPE", 10, 1, pData.substring(10, 11)));
            fields.add(new IpmField(6, "CARDHOLDER CARD NUMBER INVOLVED IN REQUEST", 11, 19, pData.substring(11, 30)));
            fields.add(new IpmField(7, "ACQUIRER'S (MICROFILM) REFERENCE NUMBER", 30, 23, pData.substring(30, 53)));
            fields.add(new IpmField(8, "TRANSACTION DATE AND TIME", 53, 12, pData.substring(53, 65)));
            fields.add(new IpmField(9, "TRANSACTION AMOUNT", 65, 12, pData.substring(65, 77)));
            fields.add(new IpmField(10, "TRANSACTION CURRENCY CODE", 77, 3, pData.substring(77, 80)));
            fields.add(new IpmField(11, "CARD SEQUENCE NUMBER", 80, 3, pData.substring(80, 83)));
            fields.add(new IpmField(12, "CARD ISSUER REFERENCE NUMBER", 83, 9, pData.substring(83, 92)));
            fields.add(new IpmField(13, "CANCELLATION INDICATOR", 92, 1, pData.substring(92, 93)));
            fields.add(new IpmField(14, "REQUEST REASON CODE", 93, 2, pData.substring(93, 95)));
            fields.add(new IpmField(15, "POTENTIAL CHARGEBACK REASON CODE", 95, 4, pData.substring(95, 99)));
            fields.add(new IpmField(16, "ACCOUNT SELECTION", 99, 2, pData.substring(99, 101)));
            fields.add(new IpmField(17, "RETRIEVAL REFERENCE NUMBER", 101, 12, pData.substring(101, 113)));
            fields.add(new IpmField(18, "AUTHORISATION CODE", 113, 6, pData.substring(113, 119)));
            fields.add(new IpmField(19, "TRANSACTION INTERCHANGE PROCESSING DATE", 119, 6, pData.substring(119, 125)));
            fields.add(new IpmField(20, "FORWARDING INSTITUTION IDENTIFICATION", 125, 8, pData.substring(125, 133)));
            fields.add(new IpmField(21, "RECEIVING INSTITUTION IDENTIFICATION", 133, 8, pData.substring(133, 141)));
            fields.add(new IpmField(22, "TYPE OF RESPONSE", 141, 1, pData.substring(141, 142)));
            fields.add(new IpmField(23, "FILLER", 142, 114, pData.substring(142, 256)));
        }else if(   pData.substring(0,2).equals("55"))
        {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "RANGE START", 9, 22, pData.substring(9, 31)));
            fields.add(new IpmField(5, "RANGE END", 31, 22, pData.substring(31, 53)));
            fields.add(new IpmField(6, "ACTION FLAG", 53, 1, pData.substring(53, 54)));
            fields.add(new IpmField(7, "STOP LIST TYPE", 54, 1, pData.substring(54, 55)));
            fields.add(new IpmField(8, "START DATE", 55, 6, pData.substring(55, 61)));
            fields.add(new IpmField(9, "END DATE", 61, 6, pData.substring(61, 67)));
            fields.add(new IpmField(10, "REASON CODE", 67, 4, pData.substring(67, 71)));
            fields.add(new IpmField(11, "USAGE CODE", 71, 1, pData.substring(71, 72)));
            fields.add(new IpmField(12, "INCIDENT PLACE", 72, 30, pData.substring(72, 102)));
            fields.add(new IpmField(13, "INCIDENT REGION", 102, 20, pData.substring(102, 122)));
            fields.add(new IpmField(14, "INCIDENT COUNTRY", 122, 3, pData.substring(122, 125)));
            fields.add(new IpmField(15, "MEMO", 125, 120, pData.substring(125, 245)));
            fields.add(new IpmField(16, "FILLER", 245, 11, pData.substring(245, 256)));

        }else if(   (pData.substring(0,2).equals("56") || pData.substring(0,2).equals("60"))
                && pData.substring(8,9).equals("0"))
        {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "CARD NUMBER", 9, 22, pData.substring(9, 31)));
            fields.add(new IpmField(5, "CLIENT CODE", 31, 24, pData.substring(31, 55)));
            fields.add(new IpmField(6, "CLIENT HOST ID", 55, 24, pData.substring(55, 79)));
            fields.add(new IpmField(7, "BANK CODE", 79, 6, pData.substring(79, 85)));
            fields.add(new IpmField(8, "BRANCH CODE", 85, 6, pData.substring(85, 91)));
            fields.add(new IpmField(9, "CARD PRODUCT CODE", 91, 3, pData.substring(91, 94)));
            fields.add(new IpmField(10, "CARD FEES CODE", 94, 3, pData.substring(94, 97)));
            fields.add(new IpmField(11, "BASIC CARD NUMBER", 97, 22, pData.substring(97, 119)));
            fields.add(new IpmField(12, "VIP LEVEL", 119, 1, pData.substring(119, 120)));
            fields.add(new IpmField(13, "OWNER CODE", 120, 2, pData.substring(120, 122)));
            fields.add(new IpmField(14, "RELATIONSHIP CODE", 122, 1, pData.substring(122, 123)));
            fields.add(new IpmField(15, "TITLE CODE", 123, 2, pData.substring(123, 125)));
            fields.add(new IpmField(16, "GENDER", 125, 1, pData.substring(125, 126)));
            fields.add(new IpmField(17, "FAMILY NAME", 126, 40, pData.substring(126, 166)));
            fields.add(new IpmField(18, "FIRST NAME", 166, 40, pData.substring(166, 206)));
            fields.add(new IpmField(19, "FILE NUMBER", 206, 20, pData.substring(206, 226)));
            fields.add(new IpmField(20, "REPLACEMENT REASON CODE", 226, 2, pData.substring(226, 228)));
            fields.add(new IpmField(21, "EMBOSSED NAME", 228, 26, pData.substring(228, 254)));
            fields.add(new IpmField(22, "SENSITIVE OPERATION RECORD", 254, 1, pData.substring(254, 255)));
            fields.add(new IpmField(23, "FILLER", 255, 1, pData.substring(255, 256)));
        }	else if(   (pData.substring(0,2).equals("56") || pData.substring(0,2).equals("60"))
                && pData.substring(8,9).equals("1"))
        {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "BIRTH DATE", 9, 8, pData.substring(9, 17)));
            fields.add(new IpmField(5, "BIRTH CITY", 17, 30, pData.substring(17, 47)));
            fields.add(new IpmField(6, "BIRTH COUNTRY", 47, 30, pData.substring(47, 77)));
            fields.add(new IpmField(7, "FAMILY STATUS", 77, 1, pData.substring(77, 78)));
            fields.add(new IpmField(8, "EMPLOYMENT STATUS", 78, 1, pData.substring(78, 79)));
            fields.add(new IpmField(9, "TAKING ON DATE", 79, 8, pData.substring(79, 87)));
            fields.add(new IpmField(10, "DOCUMENT CODE", 87, 2, pData.substring(87, 89)));
            fields.add(new IpmField(11, "ISSUING DOCUM DATE", 89, 8, pData.substring(89, 97)));
            fields.add(new IpmField(12, "ISSUING DOCUM CITY", 97, 30, pData.substring(97, 127)));
            fields.add(new IpmField(13, "LEGAL ID", 127, 30, pData.substring(127, 157)));
            fields.add(new IpmField(14, "NATIONALITY CODE", 157, 3, pData.substring(157, 160)));
            fields.add(new IpmField(15, "LANGUAGE CODE", 160, 3, pData.substring(160, 163)));
            fields.add(new IpmField(16, "PREF MAILING ADDRESS CARE OF", 163, 32, pData.substring(163, 195)));
            fields.add(new IpmField(17, "PREF MAILING ADDRESS 1", 195, 30, pData.substring(195, 225)));
            fields.add(new IpmField(18, "FILLER", 225, 31, pData.substring(225, 256)));
        }else if(  (pData.substring(0,2).equals("56") || pData.substring(0,2).equals("60"))
                && pData.substring(8,9).equals("2"))
        {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "PREF MAILING ADDRESS 2", 9, 30, pData.substring(9, 39)));
            fields.add(new IpmField(5, "PREF MAILING ADDRESS 3", 39, 30, pData.substring(39, 69)));
            fields.add(new IpmField(6, "PREF MAILING ADDRESS 4", 69, 30, pData.substring(69, 99)));
            fields.add(new IpmField(7, "PREF MAILING ADDRESS ZIP CODE", 99, 10, pData.substring(99, 109)));
            fields.add(new IpmField(8, "PREF MAILING ADDRESS CITY CODE", 109, 5, pData.substring(109, 114)));
            fields.add(new IpmField(9, "PREF MAILING ADDRESS REGION CODE", 114, 3, pData.substring(114, 117)));
            fields.add(new IpmField(10, "PREF MAILING ADDRESS COUNTRY CODE", 117, 3, pData.substring(117, 120)));
            fields.add(new IpmField(11, "PREF MAILING ADDRESS PHONE 1", 120, 20, pData.substring(120, 140)));
            fields.add(new IpmField(12, "PREF MAILING ADDRESS PHONE 2", 140, 20, pData.substring(140, 160)));
            fields.add(new IpmField(13, "PREF MAILING ADDRESS FAX", 160, 20, pData.substring(160, 180)));
            fields.add(new IpmField(14, "PREF MAILING ADDRESS EMAIL", 180, 40, pData.substring(180, 220)));
            fields.add(new IpmField(15, "FILLER", 220, 36, pData.substring(220, 256)));
        }	else if(   (pData.substring(0,2).equals("56") || pData.substring(0,2).equals("60"))
                && pData.substring(8,9).equals("3"))
        {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "PREF MAILING ADDRESS 2", 9, 30, pData.substring(9, 39)));
            fields.add(new IpmField(5, "PREF MAILING ADDRESS 3", 39, 30, pData.substring(39, 69)));
            fields.add(new IpmField(6, "PREF MAILING ADDRESS 4", 69, 30, pData.substring(69, 99)));
            fields.add(new IpmField(7, "PREF MAILING ADDRESS ZIP CODE", 99, 10, pData.substring(99, 109)));
            fields.add(new IpmField(8, "PREF MAILING ADDRESS CITY CODE", 109, 5, pData.substring(109, 114)));
            fields.add(new IpmField(9, "PREF MAILING ADDRESS REGION CODE", 114, 3, pData.substring(114, 117)));
            fields.add(new IpmField(10, "PREF MAILING ADDRESS COUNTRY CODE", 117, 3, pData.substring(117, 120)));
            fields.add(new IpmField(11, "PREF MAILING ADDRESS PHONE 1", 120, 20, pData.substring(120, 140)));
            fields.add(new IpmField(12, "PREF MAILING ADDRESS PHONE 2", 140, 20, pData.substring(140, 160)));
            fields.add(new IpmField(13, "PREF MAILING ADDRESS FAX", 160, 20, pData.substring(160, 180)));
            fields.add(new IpmField(14, "PREF MAILING ADDRESS EMAIL", 180, 40, pData.substring(180, 220)));
            fields.add(new IpmField(15, "FILLER", 220, 36, pData.substring(220, 256)));
        }else if ( pData.substring(0,2).equals("46")
                        && pData.substring(8,9).equals("0")
        ){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "PAYMENT PRODUCT INDICATOR", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(5, "RECEIVING INSTITUTION IDENTIFICATION", 10, 8, pData.substring(10, 18)));
            fields.add(new IpmField(6, "TYPE OF REPORTING", 18, 5, pData.substring(18, 23)));
            fields.add(new IpmField(7, "SETTLEMENT DATE", 23, 6, pData.substring(23, 29)));
            fields.add(new IpmField(8, "SETTLEMENT CURRENCY CODE", 29, 3, pData.substring(29, 32)));
            fields.add(new IpmField(9, "OUTGOING AMOUNT IN SETTLEMENT CURRENCY", 32, 15, pData.substring(32, 47)));
            fields.add(new IpmField(10, "OUTGOING AMOUNT SIGN", 47, 2, pData.substring(47, 49)));
            fields.add(new IpmField(11, "OUTGOING COUNT", 49, 8, pData.substring(49, 57)));
            fields.add(new IpmField(12, "INCOMING AMOUNT IN SETTLEMENT CURRENCY", 57, 15, pData.substring(57, 72)));
            fields.add(new IpmField(13, "INCOMING AMOUNT SIGN", 72, 2, pData.substring(72, 74)));
            fields.add(new IpmField(14, "INCOMING COUNT", 74, 8, pData.substring(74, 82)));
            fields.add(new IpmField(15, "NET AMOUNT IN SETTLEMENT CURRENCY", 82, 15, pData.substring(82, 97)));
            fields.add(new IpmField(16, "EXCHANGE RATE", 97, 9, pData.substring(97, 106)));
            fields.add(new IpmField(17, "NET AMOUNT IN CFA", 106, 15, pData.substring(106, 121)));
            fields.add(new IpmField(18, "NET AMOUNT SIGN", 121, 2, pData.substring(121, 123)));
            fields.add(new IpmField(19, "RECONCILIATION DATE", 123, 6, pData.substring(123, 129)));
            fields.add(new IpmField(20, "RECONCILIATION INDICATOR", 129, 3, pData.substring(129, 132)));
            fields.add(new IpmField(21, "COUNTERPART MEMBER IDENTIFICATION", 132, 8, pData.substring(132, 140)));
            fields.add(new IpmField(22, "RECONCILIATION AMOUNT", 140, 15, pData.substring(140, 155)));
            fields.add(new IpmField(23, "RECONCILIATION CURRENCY CODE", 155, 3, pData.substring(155, 158)));
            fields.add(new IpmField(24, "RECONCILIATION CURRENCY EXPONENT", 158, 1, pData.substring(158, 159)));
            fields.add(new IpmField(25, "EXCHANGE RATE OF RECONCILIATION CURRENCY TO SETTLEMENT CURRENCY", 159, 10, pData.substring(159, 169)));
            fields.add(new IpmField(26, "RECONCILIATION CURRENCY EXCHANGE RATE EXPONENT", 169, 1, pData.substring(169, 170)));
            fields.add(new IpmField(27, "VALUE DATE", 170, 6, pData.substring(170, 176)));
            fields.add(new IpmField(28, "REJECTED TRANSFERS", 176, 12, pData.substring(176, 188)));
            fields.add(new IpmField(29, "FILLER", 188, 68, pData.substring(188, 256)));

        }else if( pData.substring(0,2).equals("57")){
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "CARD TYPE", 9, 2, pData.substring(9, 11)));
            fields.add(new IpmField(5, "ACTION CODE", 11, 1, pData.substring(11, 12)));
            fields.add(new IpmField(6, "CARD NUMBER", 12, 22, pData.substring(12, 34)));
            fields.add(new IpmField(7, "CLIENT CODE", 34, 24, pData.substring(34, 58)));
            fields.add(new IpmField(8, "ACCOUNT NUMBER", 58, 24, pData.substring(58, 82)));
            fields.add(new IpmField(9, "FAMILY NAME", 82, 40, pData.substring(82, 122)));
            fields.add(new IpmField(10, "FIRST NAME", 122, 40, pData.substring(122, 162)));
            fields.add(new IpmField(11, "BIRTH DATE", 162, 6, pData.substring(162, 168)));
            fields.add(new IpmField(12, "RETURN CODE", 168, 2, pData.substring(168, 170)));
            fields.add(new IpmField(13, "ERROR TYPE", 170, 2, pData.substring(170, 172)));
            fields.add(new IpmField(14, "ERRONEOUS FIELD", 172, 30, pData.substring(172, 202)));
            fields.add(new IpmField(15, "BRANCH CODE", 202, 6, pData.substring(202, 208)));
            fields.add(new IpmField(16, "RECORD NUMBER", 208, 20, pData.substring(208, 228)));
            fields.add(new IpmField(17, "EXPIRY DATE", 228, 4, pData.substring(228, 232)));
            fields.add(new IpmField(18, "FILLER", 232, 24, pData.substring(232, 256))); // Adjust the length if needed


        } else if ( pData.substring(0,2).equals("61")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "UPDATE FLAG", 9, 1, pData.substring(9, 10)));
            fields.add(new IpmField(5, "CARD NUMBER", 10, 19, pData.substring(10, 29)));
            fields.add(new IpmField(6, "EXPIRY DATE", 29, 4, pData.substring(29, 33)));
            fields.add(new IpmField(7, "CARDHOLDER NAME", 33, 26, pData.substring(33, 59)));
            fields.add(new IpmField(8, "DOMESTIC CASH LIMIT", 59, 6, pData.substring(59, 65)));
            fields.add(new IpmField(9, "DOMESTIC PURCHASE LIMIT", 65, 6, pData.substring(65, 71)));
            fields.add(new IpmField(10, "DOMESTIC E-COMM LIMIT", 71, 6, pData.substring(71, 77)));
            fields.add(new IpmField(11, "TOTAL LIMIT DOMESTIC", 77, 6, pData.substring(77, 83)));
            fields.add(new IpmField(12, "INTERNATIONAL CASH LIMIT", 83, 6, pData.substring(83, 89)));
            fields.add(new IpmField(13, "INTERNATIONAL PURCHASE LIMIT", 89, 6, pData.substring(89, 95)));
            fields.add(new IpmField(14, "INTERNATIONAL E-COMM LIMIT", 95, 6, pData.substring(95, 101)));
            fields.add(new IpmField(15, "TOTAL INTERNATIONAL LIMIT", 101, 6, pData.substring(101, 107)));
            fields.add(new IpmField(16, "TOTAL LIMIT", 107, 6, pData.substring(107, 113)));
            fields.add(new IpmField(17, "FILLER", 113, 18, pData.substring(113, 131)));
            fields.add(new IpmField(18, "UPDATE FLAG", 131, 1, pData.substring(131, 132)));
            fields.add(new IpmField(19, "CARD NUMBER", 132, 19, pData.substring(132, 151)));
            fields.add(new IpmField(20, "EXPIRY DATE", 151, 4, pData.substring(151, 155)));
            fields.add(new IpmField(21, "CARDHOLDER NAME", 155, 26, pData.substring(155, 181)));
            fields.add(new IpmField(22, "DOMESTIC CASH LIMIT", 181, 6, pData.substring(181, 187)));
            fields.add(new IpmField(23, "DOMESTIC PURCHASE LIMIT", 187, 6, pData.substring(187, 193)));
            fields.add(new IpmField(24, "DOMESTIC E-COMM LIMIT", 193, 6, pData.substring(193, 199)));
            fields.add(new IpmField(25, "TOTAL LIMIT DOMESTIC", 199, 6, pData.substring(199, 205)));
            fields.add(new IpmField(26, "INTERNATIONAL CASH LIMIT", 205, 6, pData.substring(205, 211)));
            fields.add(new IpmField(27, "INTERNATIONAL PURCHASE LIMIT", 211, 6, pData.substring(211, 217)));
            fields.add(new IpmField(28, "INTERNATIONAL E-COMM LIMIT", 217, 6, pData.substring(217, 223)));
            fields.add(new IpmField(29, "TOTAL INTERNATIONAL LIMIT", 223, 6, pData.substring(223, 229)));
            fields.add(new IpmField(30, "TOTAL LIMIT", 229, 6, pData.substring(229, 235)));
            fields.add(new IpmField(31, "FILLER", 235, 21, pData.length() >= 256 ? pData.substring(235, 256) : pData.substring(235)));


        } else if ( pData.substring(0,2).equals("62")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2,description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "ACCOUNT", 9, 24, pData.substring(9, 33)));
            fields.add(new IpmField(5, "BANK CODE", 33, 6, pData.substring(33, 39)));
            fields.add(new IpmField(6, "ACCOUNT STATUS", 39, 1, pData.substring(39, 40)));
            fields.add(new IpmField(7, "CURRENCY CODE", 40, 3, pData.substring(40, 43)));
            fields.add(new IpmField(8, "BALANCE", 43, 15, pData.substring(43, 58)));
            fields.add(new IpmField(9, "SIGN", 58, 1, pData.substring(58, 59)));
            fields.add(new IpmField(10, "FACILITIES", 59, 15, pData.substring(59, 74)));
            fields.add(new IpmField(11, "BLOCKED AMOUNT", 74, 15, pData.substring(74, 89)));
            fields.add(new IpmField(12, "CUTOFF BAL DATE", 89, 14, pData.substring(89, 103)));
            fields.add(new IpmField(13, "CUTOFF BAL ID", 103, 10, pData.substring(103, 113)));
            fields.add(new IpmField(14, "CUTOFF TRANS DATE", 113, 14, pData.substring(113, 127)));
            fields.add(new IpmField(15, "CUTOFF TRANS ID", 127, 10, pData.substring(127, 137)));
            fields.add(new IpmField(16, "FILLER", 137, 119, pData.length() >= 256 ? pData.substring(137, 256) : pData.substring(137)));

        } else if ( pData.substring(0,2).equals("63")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "CARD TYPE", 9, 2, pData.substring(9, 11)));
            fields.add(new IpmField(5, "ACTION CODE", 11, 1, pData.substring(11, 12)));
            fields.add(new IpmField(6, "CARD NUMBER", 12, 22, pData.substring(12, 34)));
            fields.add(new IpmField(7, "FAMILY NAME", 34, 40, pData.substring(34, 74)));
            fields.add(new IpmField(8, "FIRST NAME", 74, 40, pData.substring(74, 114)));
            fields.add(new IpmField(9, "BIRTH DATE", 114, 6, pData.substring(114, 120)));
            fields.add(new IpmField(10, "RETURN CODE", 120, 2, pData.substring(120, 122)));
            fields.add(new IpmField(11, "ERROR TYPE", 122, 2, pData.substring(122, 124)));
            fields.add(new IpmField(12, "ERRONEOUS FIELD", 124, 30, pData.substring(124, 154)));
            fields.add(new IpmField(13, "BRANCH CODE", 154, 6, pData.substring(154, 160)));
            fields.add(new IpmField(14, "RECORD NUMBER", 160, 20, pData.substring(160, 180)));
            fields.add(new IpmField(15, "EXPIRY DATE", 180, 4, pData.substring(180, 184)));
            fields.add(new IpmField(16, "FILLER", 184, 72, pData.length() >= 256 ? pData.substring(184, 256) : pData.substring(184)));


        } else if ( pData.substring(0,2).equals("64")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "BRANCH CODE", 9, 6, pData.substring(9, 15)));
            fields.add(new IpmField(5, "CARD NUMBER", 15, 19, pData.substring(15, 34)));
            fields.add(new IpmField(6, "EXPIRY DATE", 34, 4, pData.substring(34, 38)));
            fields.add(new IpmField(7, "CARD NUMBER", 38, 19, pData.substring(38, 57)));
            fields.add(new IpmField(8, "EXPIRY DATE", 57, 4, pData.substring(57, 61)));
            fields.add(new IpmField(9, "CARD NUMBER", 61, 19, pData.substring(61, 80)));
            fields.add(new IpmField(10, "EXPIRY DATE", 80, 4, pData.substring(80, 84)));
            fields.add(new IpmField(11, "CARD NUMBER", 84, 19, pData.substring(84, 103)));
            fields.add(new IpmField(12, "EXPIRY DATE", 103, 4, pData.substring(103, 107)));
            fields.add(new IpmField(13, "CARD NUMBER", 107, 19, pData.substring(107, 126)));
            fields.add(new IpmField(14, "EXPIRY DATE", 126, 4, pData.substring(126, 130)));
            fields.add(new IpmField(15, "CARD NUMBER", 130, 19, pData.substring(130, 149)));
            fields.add(new IpmField(16, "EXPIRY DATE", 149, 4, pData.substring(149, 153)));
            fields.add(new IpmField(17, "CARD NUMBER", 153, 19, pData.substring(153, 172)));
            fields.add(new IpmField(18, "EXPIRY DATE", 172, 4, pData.substring(172, 176)));
            fields.add(new IpmField(19, "CARD NUMBER", 176, 19, pData.substring(176, 195)));
            fields.add(new IpmField(20, "EXPIRY DATE", 195, 4, pData.substring(195, 199)));
            fields.add(new IpmField(21, "CARD NUMBER", 199, 19, pData.substring(199, 218)));
            fields.add(new IpmField(22, "EXPIRY DATE", 218, 4, pData.substring(218, 222)));
            fields.add(new IpmField(23, "CARD NUMBER", 222, 19, pData.substring(222, 241)));
            fields.add(new IpmField(24, "EXPIRY DATE", 241, 4, pData.substring(241, 245)));
            fields.add(new IpmField(25, "FILLER", 245, 11, pData.length() >= 256 ? pData.substring(245, 256) : pData.substring(245)));


        } else if ( pData.substring(0,2).equals("65")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2,description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "REF NUMBER", 9, 12, pData.substring(9, 21)));
            fields.add(new IpmField(5, "TERMINAL NUMBER", 21, 15, pData.substring(21, 36)));
            fields.add(new IpmField(6, "CARD NUMBER", 36, 22, pData.substring(36, 58)));
            fields.add(new IpmField(7, "CHEQUE TYPE", 58, 1, pData.substring(58, 59)));
            fields.add(new IpmField(8, "REQUEST DATE", 59, 14, pData.substring(59, 73)));
            fields.add(new IpmField(9, "BRANCH CODE", 73, 6, pData.substring(73, 79)));
            fields.add(new IpmField(10, "ACCOUNT NUMBER", 79, 24, pData.substring(79, 103)));
            fields.add(new IpmField(11, "FILLER", 103, 153, pData.length() >= 256 ? pData.substring(103, 256) : pData.substring(103)));


        } else if ( pData.substring(0,2).equals("66")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "ACCOUNT", 9, 24, pData.substring(9, 33)));
            fields.add(new IpmField(5, "BANK CODE", 33, 6, pData.substring(33, 39)));
            fields.add(new IpmField(6, "BRANCH CODE", 39, 6, pData.substring(39, 45)));
            fields.add(new IpmField(7, "CARD NUMBER", 45, 22, pData.substring(45, 67)));
            fields.add(new IpmField(8, "REQUEST DATE", 67, 14, pData.substring(67, 81)));
            fields.add(new IpmField(9, "STARTING DATE", 81, 6, pData.substring(81, 87)));
            fields.add(new IpmField(10, "END DATE", 87, 6, pData.substring(87, 93)));
            fields.add(new IpmField(11, "DELIVERY MODE", 93, 1, pData.substring(93, 94)));
            fields.add(new IpmField(12, "FILLER", 94, 162, pData.length() >= 256 ? pData.substring(94, 256) : pData.substring(94)));


        } else if ( pData.substring(0,2).equals("67")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "ACCOUNT", 9, 24, pData.substring(9, 33)));
            fields.add(new IpmField(5, "BANK CODE", 33, 6, pData.substring(33, 39)));
            fields.add(new IpmField(6, "AMOUNT", 39, 15, pData.substring(39, 54)));
            fields.add(new IpmField(7, "SIGN", 54, 1, pData.substring(54, 55)));
            fields.add(new IpmField(8, "CURRENCY CODE", 55, 3, pData.substring(55, 58)));
            fields.add(new IpmField(9, "OPERATION DESC", 58, 20, pData.substring(58, 78)));
            fields.add(new IpmField(10, "OPERATION CODE", 78, 6, pData.substring(78, 84)));
            fields.add(new IpmField(11, "OPERATION DATE", 84, 6, pData.substring(84, 90)));
            fields.add(new IpmField(12, "FILE TYPE", 90, 1, pData.substring(90, 91)));
            fields.add(new IpmField(13, "FILLER", 91, 165, pData.length() >= 256 ? pData.substring(91, 256) : pData.substring(91)));


        } else if ( pData.substring(0,2).equals("68")
                && pData.substring(8,9).equals("0")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "MICROFILM REFERENCE NUMBER", 9, 23, pData.substring(9, 32)));
            fields.add(new IpmField(5, "MICROFILM REF SEQUENCE", 32, 4, pData.substring(32, 36)));
            fields.add(new IpmField(6, "LINKUP CODE", 36, 12, pData.substring(36, 48)));
            fields.add(new IpmField(7, "BATCH NUMBER", 48, 8, pData.substring(48, 56)));
            fields.add(new IpmField(8, "REMITTANCE SEQUENCE", 56, 4, pData.substring(56, 60)));
            fields.add(new IpmField(9, "VOUCHER SEQUENCE", 60, 4, pData.substring(60, 64)));
            fields.add(new IpmField(10, "REMITTANCE NUMBER", 64, 8, pData.substring(64, 72)));
            fields.add(new IpmField(11, "VOUCHER NUMBER", 72, 8, pData.substring(72, 80)));
            fields.add(new IpmField(12, "ISSUER CENTER CODE", 80, 2, pData.substring(80, 82)));
            fields.add(new IpmField(13, "ISSUER GOURPING CODE", 82, 1, pData.substring(82, 83)));
            fields.add(new IpmField(14, "ISSUER BANK CODE", 83, 6, pData.substring(83, 89)));
            fields.add(new IpmField(15, "ISSUER CORPORATE ID", 89, 15, pData.substring(89, 104)));
            fields.add(new IpmField(16, "ISSUER PARTNER BRAND ID", 104, 15, pData.substring(104, 119)));
            fields.add(new IpmField(17, "ISSUER CHANNEL NUMBER", 119, 15, pData.substring(119, 134)));
            fields.add(new IpmField(18, "CARD NUMBER", 134, 22, pData.substring(134, 156)));
            fields.add(new IpmField(19, "CARD SEQUENCE NUMBER", 156, 3, pData.substring(156, 159)));
            fields.add(new IpmField(20, "CARD FEES CODE", 159, 3, pData.substring(159, 162)));
            fields.add(new IpmField(21, "CLIENT CODE", 162, 24, pData.substring(162, 186)));
            fields.add(new IpmField(22, "PRODUCT CODE", 186, 3, pData.substring(186, 189)));
            fields.add(new IpmField(23, "PRODUCT TYPE", 189, 2, pData.substring(189, 191)));
            fields.add(new IpmField(24, "BASIC CARD FLAG", 191, 1, pData.substring(191, 192)));
            fields.add(new IpmField(25, "NETWORK CODE", 192, 2, pData.substring(192, 194)));
            fields.add(new IpmField(26, "NETWORK CARD TYPE", 194, 2, pData.substring(194, 196)));
            fields.add(new IpmField(27, "VIP LEVEL", 196, 1, pData.substring(196, 197)));
            fields.add(new IpmField(28, "CARD TYPE", 197, 1, pData.substring(197, 198)));
            fields.add(new IpmField(29, "EXPIRY DATE", 198, 4, pData.substring(198, 202)));
            fields.add(new IpmField(30, "FILLER", 202, 540, pData.substring(202, 742))); // Assuming pData has at least 742 characters


        } else if (pData.substring(0,2).equals("68")
                && pData.substring(8,9).equals("1")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "CARD ACCOUNT ENTITY CODE", 9, 2, pData.substring(9, 11)));
            fields.add(new IpmField(5, "CARD ACCOUNT ENTITY ID", 11, 24, pData.substring(11, 35)));
            fields.add(new IpmField(6, "CARD ACCOUNT SEQUENCE", 35, 1, pData.substring(35, 36)));
            fields.add(new IpmField(7, "CARD ACCOUNT TYPE", 36, 2, pData.substring(36, 38)));
            fields.add(new IpmField(8, "CARD ACCOUNT NUMBER", 38, 24, pData.substring(38, 62)));
            fields.add(new IpmField(9, "CARD ACCOUNT BANK CODE", 62, 6, pData.substring(62, 68)));
            fields.add(new IpmField(10, "TRANSACTION CODE", 68, 2, pData.substring(68, 70)));
            fields.add(new IpmField(11, "TRANSACTION SIGN", 70, 1, pData.substring(70, 71)));
            fields.add(new IpmField(12, "REVERSAL FLAG", 71, 1, pData.substring(71, 72)));
            fields.add(new IpmField(13, "REVERSAL REASON CODE", 72, 2, pData.substring(72, 74)));
            fields.add(new IpmField(14, "TRANSACTION AMOUNT", 74, 18, pData.substring(74, 92)));
            fields.add(new IpmField(15, "TRANSACTION CURRENCY", 92, 3, pData.substring(92, 95)));
            fields.add(new IpmField(16, "TRANSACTION CURRENCY EXP", 95, 1, pData.substring(95, 96)));
            fields.add(new IpmField(17, "BILLING AMOUNT", 96, 18, pData.substring(96, 114)));
            fields.add(new IpmField(18, "BILLING CURRENCY", 114, 3, pData.substring(114, 117)));
            fields.add(new IpmField(19, "BILLING CURRENCY EXP", 117, 1, pData.substring(117, 118)));
            fields.add(new IpmField(20, "TRANSACTION DATE", 118, 8, pData.substring(118, 126)));
            fields.add(new IpmField(21, "PROCESSING DATE", 126, 8, pData.substring(126, 134)));
            fields.add(new IpmField(22, "REMITTANCE DATE", 134, 8, pData.substring(134, 142)));
            fields.add(new IpmField(23, "VALUE DATE CARDHOLDER", 142, 8, pData.substring(142, 150)));
            fields.add(new IpmField(24, "PRODUCT IDENTIFIER", 150, 6, pData.substring(150, 156)));
            fields.add(new IpmField(25, "INSTITUTION CODE", 156, 15, pData.substring(156, 171)));
            fields.add(new IpmField(26, "CONTRACT NUMBER", 171, 16, pData.substring(171, 187)));
            fields.add(new IpmField(27, "SUB CONTRACT NUMBER", 187, 16, pData.substring(187, 203)));
            fields.add(new IpmField(28, "FILLER", 203, 53, pData.length() >= 256 ? pData.substring(203, 256) : pData.substring(203)));


        } else if (pData.substring(0,2).equals("82")) {
            fields.add(new IpmField(1, "TRANSACTION CODE", 0, 2, description));
            fields.add(new IpmField(2, "FILE RECORD SEQUENCE NUMBER", 2, 6, pData.substring(2, 8)));
            fields.add(new IpmField(3, "TCR SEQUENCE NUMBER", 8, 1, pData.substring(8, 9)));
            fields.add(new IpmField(4, "ISO 8583 MESSAGE TYPE", 9, 4, pData.substring(9, 13)));
            fields.add(new IpmField(5, "ORIGINAL DATA ELEMENTS", 13, 28, pData.substring(13, 41)));
            fields.add(new IpmField(6, "PROCESSING CODE", 41, 6, pData.substring(41, 47)));
            fields.add(new IpmField(7, "ACQUIRING INSTITUTION IDENTIFICATION", 47, 11, pData.substring(47, 58)));
            fields.add(new IpmField(8, "CARD ACCEPTOR IDENTIFICATION CODE", 58, 15, pData.substring(58, 73)));
            fields.add(new IpmField(9, "MERCHANT TYPE (MERCHANT CATEGORY CODE)", 73, 4, pData.substring(73, 77)));
            fields.add(new IpmField(10, "PRIMARY ACCOUNT (CARDHOLDER CARD) NUMBER", 77, 19, pData.substring(77, 96)));
            fields.add(new IpmField(11, "POINT OF SERVICE ENTRY MODE", 96, 12, pData.substring(96, 108)));
            fields.add(new IpmField(12, "LOCAL TRANSACTION DATE AND TIME", 108, 12, pData.substring(108, 120)));
            fields.add(new IpmField(13, "TRANSACTION AMOUNT IN TRANSACTION CURRENCY", 120, 12, pData.substring(120, 132)));
            fields.add(new IpmField(14, "TRANSACTION CURRENCY", 132, 3, pData.substring(132, 135)));
            fields.add(new IpmField(15, "REPLACEMENT AMOUNT IN TRANSACTION CURRENCY", 135, 12, pData.substring(135, 147)));
            fields.add(new IpmField(16, "BILLING AMOUNT IN BILLING CURRENCY", 147, 12, pData.substring(147, 159)));
            fields.add(new IpmField(17, "BILLING CURRENCY", 159, 3, pData.substring(159, 162)));
            fields.add(new IpmField(18, "SETTLEMENT AMOUNT IN SETTLEMENT CURRENCY", 162, 12, pData.substring(162, 174)));
            fields.add(new IpmField(19, "MONNAIE DE REGLEMENT", 174, 3, pData.substring(174, 177)));
            fields.add(new IpmField(20, "BILLING CONVERSION RATE", 177, 9, pData.substring(177, 186)));
            fields.add(new IpmField(21, "SETTLEMENT CONVERSION RATE", 186, 9, pData.substring(186, 195)));
            fields.add(new IpmField(22, "CHANGE DATE", 195, 4, pData.substring(195, 199)));
            fields.add(new IpmField(23, "RETRIEVAL REFERENCE NUMBER", 199, 12, pData.substring(199, 211)));
            fields.add(new IpmField(24, "SYSTEM TRACE AUDIT NUMBER", 211, 6, pData.substring(211, 217)));
            fields.add(new IpmField(25, "AUTHORISATION IDENTIFICATION RESPONSE", 217, 6, pData.substring(217, 223)));
            fields.add(new IpmField(26, "RESPONSE CODE", 223, 3, pData.substring(223, 226)));
            fields.add(new IpmField(27, "STAND-IN FLAG", 226, 1, pData.substring(226, 227)));
            fields.add(new IpmField(28, "ELECTRONIC COMMERCE INDICATOR", 227, 1, pData.substring(227, 228)));
            fields.add(new IpmField(29, "DATE ACTION", 228, 6, pData.substring(228, 234)));
            fields.add(new IpmField(30, "FUNCTION CODE", 234, 3, pData.substring(234, 237)));
            fields.add(new IpmField(31, "MESSAGE REASON CODE", 237, 4, pData.substring(237, 241)));
            fields.add(new IpmField(32, "FEES AMOUNT", 241, 6, pData.substring(241, 247)));
            fields.add(new IpmField(33, "FILLER", 247, 9, pData.substring(247, 256))); // Assuming pData has at least 256 characters

        }


        logger.info("fields: " + fields);

       return  fields;


    };
}
