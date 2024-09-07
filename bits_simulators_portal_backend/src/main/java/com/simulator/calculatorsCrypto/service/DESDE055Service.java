package com.simulator.calculatorsCrypto.service;

import com.simulator.calculatorsCrypto.model.TagMeaningList;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.EmvTag;
import com.simulator.smartICC.repositories.EmvTagRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class DESDE055Service {

    private final Logger logger = LogManager.getLogger(DESDE055Service.class);

    @Autowired
    private EmvTagRepository emvTagRepository;

    public ResponseApiJson<List<Map<String, Object>>> parseData(String data) {
        String idRequest = "DESDE055_" + new Date().getTime() + (Math.random() * 9999);

        List<Map<String, Object>> responseList = new ArrayList<>();
        logger.info("data: " + data);

        int i = 0;
        while (i < data.length()) {
            String tag = data.substring(i, i + 2);
            if (tag.equals("5F") || tag.equals("9F") || (tag.length() > 1 && tag.substring(1, 2).equals("F"))) {
                tag = data.substring(i, i + 4);
                i += 2;
            }
            i += 2;
            if (i + 2 > data.length()) {
                break;
            }

            // Get the length
            String length = data.substring(i, i + 2);
            i += 2;

            // Check if length is '81' or '82' and adjust i accordingly
            if (length.equals("81")) {
                length = data.substring(i, i + 2);
                i += 2;
            } else if (length.equals("82")) {
                length = data.substring(i, i + 4);
                i += 4;
            }

            // Check if the length is a valid hexadecimal number
            if (!length.matches("[0-9A-Fa-f]+")) {
                return new ResponseApiJson<>(idRequest, "001", "Invalid length for tag: " + tag, responseList);
            }

            // Convert length to integer
            int len = Integer.parseInt(length, 16);

            // Extract value based on length
            if (data.length() < i + len * 2) {
                return new ResponseApiJson<>(idRequest, "001", "Insufficient data for tag: " + tag, responseList);
            }
            String value = data.substring(i, i + len * 2); // Multiplying by 2 because length is in bytes
            i += len * 2;

            logger.info("value: " + value);
            logger.info("length: " + length);
            logger.info("tag: " + tag);
            Optional<EmvTag> emvTagOptional = emvTagRepository.findByTag(tag);
            logger.info("emvTagOptional: " + emvTagOptional);
            Map<String, Object> tagInfo = new HashMap<>();
            tagInfo.put("tag", tag);
            tagInfo.put("name", emvTagOptional.isPresent() ? emvTagOptional.get().getName() : "Unknown");
            tagInfo.put("length", length);
            tagInfo.put("value", value);
            tagInfo.put("dataTag", new ArrayList<>());

            if (tag.equals("82")) {
                String binaryValue = hexToBinary(value);
                List<Map<String, Object>> tagsAIP = new ArrayList<>();

                int numBytes = value.length() / 2;
                for (int byteIndex = 0; byteIndex < numBytes; byteIndex++) {
                    String currentByte = value.substring(byteIndex * 2, byteIndex * 2 + 2);
                    for (int bitIndex = 0; bitIndex < 8; bitIndex++) {
                        Map<String, Object> bitInfo = new HashMap<>();
                        bitInfo.put("aipByte", byteIndex + 1);
                        bitInfo.put("bitIndex", bitIndex);
                        bitInfo.put("bitValue", binaryValue.charAt(byteIndex * 8 + bitIndex));
                        bitInfo.put("present", isBitSet(binaryValue, byteIndex * 8 + bitIndex) ? "true" : "false");
                        bitInfo.put("meaning", TagMeaningList.getBitMeaning(Integer.toString(byteIndex + 1), Integer.toString(bitIndex + 1)));
                        tagsAIP.add(bitInfo);
                    }
                }
                tagInfo.put("dataTag", tagsAIP);
            }

            if (tag.equals("95")) {
                String binaryValue = hexToBinary(value);
                List<Map<String, Object>> tagsAIP = new ArrayList<>();

                int numBytes = value.length() / 2;
                for (int byteIndex = 0; byteIndex < numBytes; byteIndex++) {
                    String currentByte = value.substring(byteIndex * 2, byteIndex * 2 + 2);
                    for (int bitIndex = 0; bitIndex < 8; bitIndex++) {
                        Map<String, Object> bitInfo = new HashMap<>();
                        bitInfo.put("aipByte", byteIndex + 1);
                        bitInfo.put("bitIndex", bitIndex);
                        bitInfo.put("bitValue", binaryValue.charAt(byteIndex * 8 + bitIndex));
                        bitInfo.put("present", isBitSet(binaryValue, byteIndex * 8 + bitIndex) ? "true" : "false");
                        bitInfo.put("meaning", TagMeaningList.getBitMeaningTVR(Integer.toString(byteIndex + 1), Integer.toString(bitIndex + 1)));
                        tagsAIP.add(bitInfo);
                    }
                }
                tagInfo.put("dataTag", tagsAIP);
            }

            if (tag.equals("9F33")) {
                String binaryValue = hexToBinary(value);
                List<Map<String, Object>> tagsAIP = new ArrayList<>();

                int numBytes = value.length() / 2;
                for (int byteIndex = 0; byteIndex < numBytes; byteIndex++) {
                    String currentByte = value.substring(byteIndex * 2, byteIndex * 2 + 2);
                    for (int bitIndex = 0; bitIndex < 8; bitIndex++) {
                        Map<String, Object> bitInfo = new HashMap<>();
                        bitInfo.put("aipByte", byteIndex + 1);
                        bitInfo.put("bitIndex", bitIndex);
                        bitInfo.put("bitValue", binaryValue.charAt(byteIndex * 8 + bitIndex));
                        bitInfo.put("present", isBitSet(binaryValue, byteIndex * 8 + bitIndex) ? "true" : "false");
                        bitInfo.put("meaning", TagMeaningList.getBitMeaningTCAP(Integer.toString(byteIndex + 1), Integer.toString(bitIndex + 1)));
                        tagsAIP.add(bitInfo);
                    }
                }
                tagInfo.put("dataTag", tagsAIP);
            }
            responseList.add(tagInfo);
        }

        if (responseList.isEmpty()) {
            return new ResponseApiJson<>(idRequest, "001", "No records found.", responseList);
        }

        return new ResponseApiJson<>(idRequest, "000", "Data parsed successfully", responseList);
    }

    // Method to convert a hexadecimal string to binary using BigInteger
    private String hexToBinary(String hex) {
        BigInteger num = new BigInteger(hex, 16);
        String binary = num.toString(2);
        while (binary.length() < hex.length() * 4) {
            binary = "0" + binary;
        }
        return binary;
    }

    // Method to check if a bit in a binary string is set
    private boolean isBitSet(String binary, int bitIndex) {
        return binary.charAt(bitIndex) == '1';
    }
}
