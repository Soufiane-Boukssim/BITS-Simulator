package com.simulator.calculatorsCrypto.service;

import com.simulator.calculatorsCrypto.controller.DesMBDTO;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.cryptoKey.misc.HexTools;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Provider;
import java.security.Security;
import java.util.Date;

@Service
public class HexToolsService {

    public ResponseApiJson<?> generateEncyptedPINBlock(String pin, String pinBlockFormat, String accountNumber, String PINKey) {
        String idRequest = "PIN_BLOCK" + new Date().getTime() + (Math.random() * 9999);

        try {
            String clearPINBlock = calculateClearPINBlock(pin, pinBlockFormat, accountNumber);
            if(clearPINBlock != null){
                return new ResponseApiJson<>(idRequest, "000", "Data parsed successfully", clearPINBlock);

            }else {
                return new ResponseApiJson<>("001", "An error occurred while generating encrypted PIN block", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>("001", "An error occurred while generating encrypted PIN block", null);
        }
    }

    private String calculateClearPINBlock(String pin, String pinBlockFormat, String pCardNumber) {
        try {
            String pinBlock = null;
            if (pin.length() > 12)
                throw new Exception("Invalid PIN length: " + pin.length());
            if (pCardNumber.length() != 12)
                throw new Exception("Invalid Account Number: " + pCardNumber + ". The length of the account number must be 12 (the 12 right-most digits of the account number excluding the check digit)");

            switch (pinBlockFormat) {
                case "00":
                case "01":
                {
                    // Block 1
                    String block1 = null;
                    byte[] block1ByteArray;
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
                            throw new Exception("Unsupported PIN Length: " + pin.length());
                    }
                    String block2 = "0000" + pCardNumber;
                    // pinBlock
                    pinBlock = xorHex(block1, block2);
                }
                ;
                break;
                case "03":
                {
                    if (pin.length() < 4 || pin.length() > 12)
                        throw new Exception("Unsupported PIN Length: " + pin.length());
                    pinBlock = pin + "FFFFFFFFFFFFFFFF".substring(pin.length(), 16);
                }
                break;
                default:
                    throw new Exception("Unsupported PIN format: " + pinBlockFormat);
            }
            return pinBlock;
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }

    private byte xor(byte byte1, byte byte2) {
        int one = (int) byte1;
        int two = (int) byte2;
        int xor = one ^ two;
        return (byte) (0xff & xor);
    }

    private String xorHex(String data1, String data2) {
        String result = "";
        try {
            if (data1.length() % 16 != 0 || data1.length() < 16 || data1.length() > 48 || data1.length() != data2.length())
                throw new Exception("data len should be a mulptiple of 16 and the 2 inputs should have the same length");

            byte[] resultByte = new byte[data1.length() / 2];
            for (int i = 0; i < resultByte.length; ++i) {
                resultByte[i] = xor(HexString2ByteArray(data1)[i], HexString2ByteArray(data2)[i]);
            }

            return ByteArray2HexString(resultByte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Ajoutez ici les méthodes HexString2ByteArray et ByteArray2HexString si nécessaire

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



    ///*******************************





    public ResponseApiJson<?> desMB(String data, String key) {
        String idRequest = "desMB" + new Date().getTime() + (Math.random() * 9999);

        try {

            if(data != null &&  data != key ){

                String cipheredData = encrypt(key, data);
                String decipheredData = decrypt(key, data);
                String decipheredDataAsciiTohex = converterASCIItoHex(decipheredData);

                DesMBDTO desMBDTO = new DesMBDTO(cipheredData, decipheredDataAsciiTohex);
                return new ResponseApiJson<>(idRequest, "000", "Data Calculators successfully", desMBDTO);

            }else {
                return new ResponseApiJson<>("001", "An error occurred while Data Calculators successfully", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>("001", "An error occurred while Data Calculators successfully", null);
        }
    }


    private byte[] ivBytes = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

    public String encrypt(String key, String data) throws Exception {
        if (key.length() != 16 && key.length() != 32 && key.length() != 48) {
            throw new Exception("KEY LENGTH ERROR. SHOULD BE 16, 32 or 48");
        }

        if ((data.length() % 16) != 0) {
            throw new Exception("DATA LENGTH ERROR. SHOULD BE A MULTIPLE OF 16");
        }

        try {
            String output = "";
            Provider provider = Security.getProvider("SunJCE");

            if (key.length() == 32) {
                key = key + key.substring(0, 16);
            }

            for (int i = 0; i < data.length(); i += 16) {
                byte[] binKey = HexTools.HexString2ByteArray(key);

                String vTempData = data.substring(i, i + 16);
                byte[] bindata = HexTools.HexString2ByteArray(vTempData);

                SecretKeySpec secKey = new SecretKeySpec(binKey, key.length() == 16 ? "DES" : "DESede");

                Cipher cipher = Cipher.getInstance(key.length() == 16 ? "DES/CBC/NoPadding" : "DESede/CBC/NoPadding", provider);

                IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                cipher.init(Cipher.ENCRYPT_MODE, secKey, ivSpec);

                byte[] cipherText = new byte[cipher.getOutputSize(bindata.length)];
                int ctLength = cipher.update(bindata, 0, bindata.length, cipherText, 0);
                ctLength += cipher.doFinal(cipherText, ctLength);
                output = output + HexTools.ByteArray2HexString(cipherText);
            }

            return output;

        } catch (Exception e) {
            throw e;
        }
    }

    public String decrypt(String key, String data) throws Exception {
        if (key.length() != 16 && key.length() != 32 && key.length() != 48) {
            throw new Exception("KEY LENGTH ERROR. SHOULD BE 16, 32 or 48");
        }

        if ((data.length() % 16) != 0) {
            throw new Exception("DATA LENGTH ERROR. SHOULD BE A MULTIPLE OF 16");
        }

        try {
            String output = "";
            Provider provider = Security.getProvider("SunJCE");

            if (key.length() == 32) {
                key = key + key.substring(0, 16);
            }

            for (int i = 0; i < data.length(); i += 16) {
                byte[] binKey = HexTools.HexString2ByteArray(key);

                String tempData = data.substring(i, i + 16);
                byte[] bindata = HexTools.HexString2ByteArray(tempData);

                SecretKeySpec secKey = new SecretKeySpec(binKey, key.length() == 16 ? "DES" : "DESede");

                Cipher cipher = Cipher.getInstance(key.length() == 16 ? "DES/CBC/NoPadding" : "DESede/CBC/NoPadding", provider);

                IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                cipher.init(Cipher.DECRYPT_MODE, secKey, ivSpec);

                byte[] plainText = cipher.doFinal(bindata);
                output += new String(plainText);
            }

            return output.trim(); // Trim to remove any extra padding or whitespace

        } catch (Exception e) {
            throw e;
        }
    }
    public String converterASCIItoHex(String asciiString) {
        StringBuilder hexString = new StringBuilder();

        // Parcourir chaque caractère de la chaîne ASCII
        for (char character : asciiString.toCharArray()) {
            // Convertir le caractère en représentation hexadécimale
            String hex = String.format("%02X", (int) character);
            // Ajouter la représentation hexadécimale au StringBuilder
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
