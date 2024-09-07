package com.simulator.calculatorsCrypto.model;

public class TagMeaningList {

    private static final Tags[] tagsAIP = new Tags[]{
            new Tags("1", "1", "CDA supported", false),
            new Tags("1", "2", "RFU", false),
            new Tags("1", "3", "Issuer authentication is supported", false),
            new Tags("1", "4", "Terminal risk management is to be performed", false),
            new Tags("1", "5", "Cardholder verification is supported", false),
            new Tags("1", "6", "DDA supported", false),
            new Tags("1", "7", "SDA supported", false),
            new Tags("1", "8", "RFU", false),
            new Tags("2", "1", "RFU", false),
            new Tags("2", "2", "RFU", false),
            new Tags("2", "3", "RFU", false),
            new Tags("2", "4", "RFU", false),
            new Tags("2", "5", "RFU", false),
            new Tags("2", "6", "RFU", false),
            new Tags("2", "7", "RFU", false),
            new Tags("2", "8", "Reserved for use by the EMV Contactless Specifications", false)
    };

    public static String getBitMeaning(String byteIndex, String bitIndex) {
        for (Tags tag : tagsAIP) {
            if (tag.getAipByte().equals(byteIndex) && tag.getAipBit().equals(bitIndex)) {
                return tag.getMeaning();
            }
        }
        return "Unknown";
    }




    final static Tags[] tagsTVR = new Tags[]{
            new Tags("1","1","RFU",false),
            new Tags("1","2","RFU",false),
            new Tags("1","3","CDA failed",false),
            new Tags("1","4","DDA failed",false),
            new Tags("1","5","Card appears on terminal exception file",false),
            new Tags("1","6","ICC data missing",false),
            new Tags("1","7","SDA failed",false),
            new Tags("1","8","Offline data authentication was not performed",false),

            new Tags("2","1","RFU",false),
            new Tags("2","2","RFU",false),
            new Tags("2","3","RFU",false),
            new Tags("2","4","New card",false),
            new Tags("2","5","Requested service not allowed for card product",false),
            new Tags("2","6","Application not yet effective",false),
            new Tags("2","7","Expired application",false),
            new Tags("2","8","ICC and terminal have different application versions",false),

            new Tags("3","1","RFU",false),
            new Tags("3","2","RFU",false),
            new Tags("3","3","Online PIN entered",false),
            new Tags("3","4","PIN entry required, PIN pad present, but PIN was not entered",false),
            new Tags("3","5","PIN entry required and PIN pad not present or not working",false),
            new Tags("3","6","PIN Try Limit exceeded",false),
            new Tags("3","7","Unrecognised CVM",false),
            new Tags("3","8","Cardholder verification was not successful",false),

            new Tags("4","1","RFU",false),
            new Tags("4","2","RFU",false),
            new Tags("4","3","RFU",false),
            new Tags("4","4","Merchant forced transaction online",false),
            new Tags("4","5","Transaction selected randomly for online processing",false),
            new Tags("4","6","Upper consecutive offline limit exceeded",false),
            new Tags("4","7","Lower consecutive offline limit exceeded",false),
            new Tags("4","8","Transaction exceeds floor limit",false),

            new Tags("5","1","RFU",false),
            new Tags("5","2","RFU",false),
            new Tags("5","3","RFU",false),
            new Tags("5","4","RFU",false),
            new Tags("5","5","Script processing failed after final GENERATE AC",false),
            new Tags("5","6","Script processing failed before final GENERATE AC",false),
            new Tags("5","7","Issuer authentication failed",false),
            new Tags("5","8","Default TDOL used",false),


    };



    public static String getBitMeaningTVR(String byteIndex, String bitIndex) {
        for (Tags tag : tagsTVR) {
            if (tag.getAipByte().equals(byteIndex) && tag.getAipBit().equals(bitIndex)) {
                return tag.getMeaning();
            }
        }
        return "Unknown";
    }




    final static Tags[] tagTCAP = new Tags[]{
            new Tags("1","1","RFU",false),
            new Tags("1","2","RFU",false),
            new Tags("1","3","RFU",false),
            new Tags("1","4","RFU",false),
            new Tags("1","5","RFU",false),
            new Tags("1","6","IC with contacts",false),
            new Tags("1","7","Magnetic stripe",false),
            new Tags("1","8","Manual key entry",false),

            new Tags("2","1","RFU",false),
            new Tags("2","2","RFU",false),
            new Tags("2","3","RFU",false),
            new Tags("2","4","No CVM Required",false),
            new Tags("2","5","Enciphered PIN for offline verification",false),
            new Tags("2","6","Signature (paper)",false),
            new Tags("2","7","Enciphered PIN for online verification",false),
            new Tags("2","8","Plaintext PIN for ICC verification",false),

            new Tags("3","1","RFU",false),
            new Tags("3","2","RFU",false),
            new Tags("3","3","RFU",false),
            new Tags("3","4","CDA",false),
            new Tags("3","5","RFU",false),
            new Tags("3","6","Card capture",false),
            new Tags("3","7","DDA",false),
            new Tags("3","8","SDA",false),


    };


    public static String getBitMeaningTCAP(String byteIndex, String bitIndex) {
        for (Tags tag : tagTCAP) {
            if (tag.getAipByte().equals(byteIndex) && tag.getAipBit().equals(bitIndex)) {
                return tag.getMeaning();
            }
        }
        return "Unknown";
    }



    ///CVR

    final static Tags[] tagCVR = new Tags[]{
            new Tags("1", "1", "Offline data authentication was performed", false),
            new Tags("1", "2", "Cardholder verification was performed", false),
            new Tags("1", "3", "Card risk management was performed", false),
            new Tags("1", "4", "Issuer authentication was performed", false),
            new Tags("1", "5", "Issuer authentication was performed", false),
            new Tags("1", "6", "Script processing was performed" ,false),
            new Tags("1", "7", "RFU", false),
            new Tags("1", "8", "RFU", false),

            new Tags("2", "1", "RFU", false),
            new Tags("2", "2", "RFU", false),
            new Tags("2", "3", "RFU", false),
            new Tags("2", "4", "RFU", false),
            new Tags("2", "5", "RFU", false),
            new Tags("2", "6", "RFU", false),
            new Tags("2", "7", "RFU", false),
            new Tags("2", "8", "RFU", false),

    };


    public static String getBitMeaningCVR(String byteIndex, String bitIndex) {
        for (Tags tag : tagTCAP) {
            if (tag.getAipByte().equals(byteIndex) && tag.getAipBit().equals(bitIndex)) {
                return tag.getMeaning();
            }
        }
        return "Unknown";
    }


}
