package com.simulator.iso.cryptoKey.misc;


import com.simulator.iso.cryptoKey.CryproKeyService.DESSun;

public class HexTools {

    public static byte[] HexString2ByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String ByteArray2HexString(byte[] pb_input){
        String data="";

        for(int i=0; i<pb_input.length; ++i)
            data += String.format("%02X", pb_input[i]);

        return data;
    }

    public static byte xor(byte byte1, byte byte2) {

        int one = (int)byte1;
        int two = (int)byte2;
        int xor = one ^ two;

        return (byte)(0xff & xor);
    }

    public static String xorHex (String data1, String data2) throws Exception{
        String result="";

        if(data1.length()%16!=0 || data1.length()<16 || data1.length()>48 || data1.length()!=data2.length())
            throw new Exception ("data len should be a mulptiple of 16 and the 2 inputs should have the same length");

        byte[] resultByte = new byte[data1.length()/2];
        for(int i=0;i<resultByte.length;++i) {
            resultByte[i]=xor(HexString2ByteArray(data1)[i],HexString2ByteArray(data2)[i]);
        }

        return ByteArray2HexString(resultByte);
    }


    public static byte and(byte byte1, byte byte2) {

        int one = (int)byte1;
        int two = (int)byte2;
        int xor = one & two;

        return (byte)(0xff & xor);
    }

    public static String andHex (String data1, String data2) throws Exception{
        String result="";

        if(data1.length()%16!=0 || data1.length()<16 || data1.length()>48 || data1.length()!=data2.length())
            throw new Exception ("data len should be a mulptiple of 16 and the 2 inputs should have the same length");

        byte[] resultByte = new byte[data1.length()/2];
        for(int i=0;i<resultByte.length;++i) {
            resultByte[i]=and(HexString2ByteArray(data1)[i],HexString2ByteArray(data2)[i]);
        }

        return ByteArray2HexString(resultByte);
    }

    public static String hexPassword(String password) {
        String passwordHex = HexTools.ByteArray2HexString(password.getBytes());

        return passwordHex+String.format("%048d", 0).substring(0,48-passwordHex.length());
    }
    public static String generateEncyptedPINBlock (String pin, String pinBlockFormat,String accountNumber, String PINKey) throws Exception {
        DESSun Mydes = new DESSun();
        String clearPINBlock = calculateClearPINBlock(pin, pinBlockFormat, accountNumber);
        // Encrypt
        String translatedPINBlock = Mydes.encrypt(PINKey,clearPINBlock);
        return  translatedPINBlock;
    }
    public static String calculateClearPINBlock (String pin, String pinBlockFormat, String pCardNumber) throws Exception {
        String pinBlock = null;
        if (pin.length() > 12)
            throw  new Exception("Invalid PIN length: " + pin.length());
        if (pCardNumber.length() != 12)
            throw  new Exception("Invalid Account Number: " + pCardNumber + ". The length of the account number must be 12 (the 12 right-most digits of the account number excluding the check digit)");
        switch (pinBlockFormat) {
            case "00":
            case "01":
            {
                // Block 1
                String block1 = null;
                byte[] block1ByteArray;
                System.out.println("pin.length() ==>"+pin.length());
                switch (pin.length()) {
                    // pin length then pad with 'F'
                    case 4:
                        block1 = "04" + pin + "FFFFFFFFFF";
                        break;
                    case 5:
                        block1 = "05" + pin + "FFFFFFFFF";
                        break;
                    case 6:
                        block1 = "06" + pin + "FFFFFFFF";
                        break;
                    case 7:
                        block1 = "07" + pin + "FFFFFFF";
                        break;
                    case 8:
                        block1 = "08" + pin + "FFFFFF";
                        break;
                    case 9:
                        block1 = "09" + pin + "FFFFF";
                        break;
                    case 10:
                        block1 = "0A" + pin +"FFFF";
                        break;
                    case 11:
                        block1 = "0B" + pin +"FFF";
                        break;
                    case 12:
                        block1 = "0C" + pin +"FF";
                        break;
                    default:
                        throw  new Exception("Unsupported PIN Length: " +pin.length());
                }
                String block2 = "0000" + pCardNumber;
                // pinBlock
                pinBlock = xorHex(block1, block2);
            }
            ;
            break;
            case "03":
            {
                if(pin.length() < 4 || pin.length() > 12)
                    throw new Exception("Unsupported PIN Length: " + pin.length());
                pinBlock = pin + "FFFFFFFFFFFFFFFF".substring(pin.length(),16);
            }
            break;
            default:
                throw  new Exception("Unsupported PIN format: " + pinBlockFormat);
        }
        return  pinBlock;
    }
}