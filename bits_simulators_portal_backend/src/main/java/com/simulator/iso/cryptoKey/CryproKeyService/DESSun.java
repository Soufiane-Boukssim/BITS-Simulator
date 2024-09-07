package com.simulator.iso.cryptoKey.CryproKeyService;


import com.simulator.iso.cryptoKey.misc.HexTools;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Provider;
import java.security.Security;
import java.util.Date;


@Service
public class DESSun {

    byte[] ivBytes = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

    public String encrypt(String key, String data) throws Exception{
        String idRequest = "ENCRYPT_" + new Date().getTime() + (Math.random() * 9999);
        ivBytes = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

        if(key.length() != 16 && key.length()!= 32 && key.length()!= 48)
            throw new Exception("KEY LENGTH ERROR. SHOULD BE 16, 32 or 48");

        if((data.length() % 16) != 0)
            throw new Exception("DATA LENGTH ERROR. SHOULD BE A MULTIPLE OF 16");

        try{

            String output = "";
            Provider provider = Security.getProvider("SunJCE");

            if(key.length()==32)
                key = key + key.substring(0,0+16);


            for (int i=0; i<data.length(); i+=16){
                byte[] binKey = HexTools.HexString2ByteArray(key);

                String vTempData = data.substring(i, i+16);
                byte[] bindata = HexTools.HexString2ByteArray(vTempData);

                SecretKeySpec secKey = new SecretKeySpec(binKey, key.length()==16?"DES":"DESede");

                Cipher cipher = Cipher.getInstance(key.length()==16?"DES/CBC/NoPadding":"DESede/CBC/NoPadding", provider);

                IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                cipher.init(Cipher.ENCRYPT_MODE, secKey, ivSpec);

                byte[] cipherText = new byte[cipher.getOutputSize(bindata.length)];
                int ctLength = cipher.update(bindata, 0, bindata.length, cipherText, 0);
                ctLength += cipher.doFinal(cipherText, ctLength);
                output = output + HexTools.ByteArray2HexString(cipherText);

                ivBytes = cipherText; // MAW20211028
            }

            return output;



        } catch (Exception e) {
            throw e;
        }
    }

    public String encryptECB(String key, String data) throws Exception{

        if(key.length() != 16 && key.length()!= 32 && key.length()!= 48)
            throw new Exception("KEY LENGTH ERROR. SHOULD BE 16, 32 or 48");

        if((data.length() % 16) != 0)
            throw new Exception("DATA LENGTH ERROR. SHOULD BE A MULTIPLE OF 16");

        try{

            String output = "";
            Provider provider = Security.getProvider("SunJCE");

            if(key.length()==32)
                key = key + key.substring(0,0+16);


            for (int i=0; i<data.length(); i+=16){
                byte[] binKey = HexTools.HexString2ByteArray(key);

                String vTempData = data.substring(i, i+16);
                byte[] bindata = HexTools.HexString2ByteArray(vTempData);

                SecretKeySpec secKey = new SecretKeySpec(binKey, key.length()==16?"DES":"DESede");

                Cipher cipher = Cipher.getInstance(key.length()==16?"DES/ECB/NoPadding":"DESede/ECB/NoPadding", provider);

                cipher.init(Cipher.ENCRYPT_MODE, secKey);

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

    public String decrypt(String key, String data) throws Exception{

        ivBytes = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

        if(key.length() != 16 && key.length()!= 32 && key.length()!= 48)
            throw new Exception("KEY LENGTH ERROR. SHOULD BE 16, 32 or 48");

        if((data.length() % 16) != 0)
            throw new Exception("DATA LENGTH ERROR. SHOULD BE A MULTIPLE OF 16");

        try{

            String output = "";

            Provider provider = Security.getProvider("SunJCE");

            if(key.length()==32)
                key = key + key.substring(0,0+16);

            byte[] binKey = HexTools.HexString2ByteArray(key);

            for(int i=0; i<data.length(); i+=16){
                String tempData = data.substring(i, i+16);

                byte[] bindata = HexTools.HexString2ByteArray(tempData);

                SecretKeySpec secKey = new SecretKeySpec(binKey, key.length()==16?"DES":"DESede");

                Cipher cipher = Cipher.getInstance(key.length()==16?"DES/CBC/NoPadding":"DESede/CBC/NoPadding", provider);

                IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                cipher.init(Cipher.DECRYPT_MODE, secKey, ivSpec);

                byte[] plainText = new byte[cipher.getOutputSize(bindata.length)];
                int length = cipher.update(bindata, 0, bindata.length, plainText, 0);
                length += cipher.doFinal(plainText, length);

                output = output + HexTools.ByteArray2HexString(plainText);

                ivBytes = bindata; // MAW20211028

            }


            return output;

        } catch (Exception e) {
            throw e;
        }
    }

    public String decryptECB(String key, String data) throws Exception{

        if(key.length() != 16 && key.length()!= 32 && key.length()!= 48)
            throw new Exception("KEY LENGTH ERROR. SHOULD BE 16, 32 or 48");

        if((data.length() % 16) != 0)
            throw new Exception("DATA LENGTH ERROR. SHOULD BE A MULTIPLE OF 16");

        try{

            String output = "";

            Provider provider = Security.getProvider("SunJCE");

            if(key.length()==32)
                key = key + key.substring(0,0+16);

            byte[] binKey = HexTools.HexString2ByteArray(key);

            for(int i=0; i<data.length(); i+=16){
                String tempData = data.substring(i, i+16);

                byte[] bindata = HexTools.HexString2ByteArray(tempData);

                SecretKeySpec secKey = new SecretKeySpec(binKey, key.length()==16?"DES":"DESede");

                Cipher cipher = Cipher.getInstance(key.length()==16?"DES/ECB/NoPadding":"DESede/ECB/NoPadding", provider);

                cipher.init(Cipher.DECRYPT_MODE, secKey);

                byte[] plainText = new byte[cipher.getOutputSize(bindata.length)];
                int length = cipher.update(bindata, 0, bindata.length, plainText, 0);
                length += cipher.doFinal(plainText, length);

                output = output + HexTools.ByteArray2HexString(plainText);

            }


            return output;

        } catch (Exception e) {
            throw e;
        }
    }

    public String computeKCV(String key) throws Exception{
        return encrypt(key, String.format("%016d", 0));
    }



    protected String generateKey(int keyLength) throws Exception{

        if(keyLength != 16 &&keyLength!= 32 && keyLength!= 48)
            throw new Exception("KEY LENGTH ERROR. SHOULD BE 16, 32 or 48");


        try{

            String output = "";
            Provider provider = Security.getProvider("SunJCE");

            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");

            for(int i=0;i<keyLength;i+=16) {
                keyGenerator.init(56);
                SecretKey secretKey = keyGenerator.generateKey();
                output = output + HexTools.ByteArray2HexString(secretKey.getEncoded());
            }

            return  output;

        } catch (Exception e) {
            throw e;
        }
    }

}
