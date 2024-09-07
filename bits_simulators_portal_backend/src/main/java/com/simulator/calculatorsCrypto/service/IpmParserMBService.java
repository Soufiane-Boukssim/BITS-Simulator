package com.simulator.calculatorsCrypto.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.simulator.calculatorsCrypto.model.IpmField;
import com.simulator.calculatorsCrypto.model.IpmRecord;
import com.simulator.config.GlobalVars;
import com.simulator.entities.FieldsDefinition;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.FieldsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IpmParserMBService {

    private final Logger logger = LogManager.getLogger(DESDE055Service.class);
    private List<IpmRecord> records = new ArrayList<>();

    @Autowired
    FieldsRepository fieldsRepository;


    public ResponseApiJson<?> uploadFile(MultipartFile file) {
        String idRequest = "Upload_File" + new Date().getTime() + (Math.random() * 9999);
        logger.info("Processing file: " + file.getOriginalFilename());
//        String divisor = "1644";
        String[] divisors = {"1644", "1240"};
        List<LineData> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            List<String> lines = reader.lines().collect(Collectors.toList());

            int lineNumber = 1;
            for (String line : lines) {
                List<String> dividedParts = divideLine(line, divisors);
                for (String part : dividedParts) {
                    part = cleanPart(part);  // Clean the part to remove unwanted characters
                    logger.info("part [" + part + "]: ");
                    logger.info("lineNumber [" + lineNumber + "]");
                    String hexContent = asciiToDecimal(part);
                    dataList.add(new LineData(lineNumber, hexContent));
                    lineNumber++;
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return new ResponseApiJson<>(idRequest, "001", "error", "error");
        }

        return new ResponseApiJson<>(idRequest, "000", "Data parsed successfully", dataList);
    }

    private List<String> divideLine(String line, String[] divisors) {
        List<String> dividedParts = new ArrayList<>();
        // Loop through each divisor
        for (String divisor : divisors) {
            // Split the line using the current divisor and retain the divisor with each part
            String[] parts = line.split(divisor);
            // Add the parts to the list, reattaching the divisor, and excluding the first empty part
            for (int i = 1; i < parts.length; i++) {
                dividedParts.add(divisor + parts[i]);
            }
        }
        return dividedParts;
    }

    private String cleanPart(String part) {
        // Remove unwanted trailing characters (assuming * is the unwanted sequence)
        return part.replaceAll("[  *]+$", "");
//        return part.replaceAll("[*]+$", "");
    };




    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public class LineData {
        private int lineNumber;
        private String part;

        public LineData() {
            // Constructeur par défaut nécessaire pour la sérialisation JSON
        }

        public LineData(int lineNumber, String part) {
            this.lineNumber = lineNumber;
            this.part = part;
        }

        // Getters and setters
        public int getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String getPart() {
            return part;
        }

        public void setPart(String part) {
            this.part = part;
        }
    }

    private String asciiToDecimal(String input) {
//        StringBuilder decimalString = new StringBuilder();
//        for (char c : input.toCharArray()) {
//            decimalString.append((int) c).append(" ");
//        }
//        return decimalString.toString().trim();
        StringBuilder hexString = new StringBuilder();
        for (char c : input.toCharArray()) {
            hexString.append(String.format("%02X", (int) c));
        }
        return hexString.toString();
    }

    public static class FileContent {
        private int line;
        private String content;

        public FileContent(int line, String content) {
            this.line = line;
            this.content = content;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }





    //// show  les donnee  de chaque ligne



    public  ResponseApiJson<?>  showDetails(String data){
        String idRequest = "Show_Details" + new Date().getTime() + (Math.random() * 9999);

        logger.info("data===>"+data);


        try {

            // Convert hex to ASCII
            String asciiData = hexToAscii(data);

            // Remove the "@" character
            String noAtSignData = asciiData.replace("@", "");
            // Remove specific characters (e.g., \u0002 which is a start of text character)
            String cleanedData = noAtSignData.replaceAll("[^\\x20-\\x7E]", "");

            logger.info("cleanedData===>" + cleanedData);

            // Parse the cleaned data into IpmField objects
            List<IpmField> tcList  = parseCleanedData(cleanedData);

            logger.info("parsedData===>" + tcList);

            List<FieldsDefinition> fieldsDefinitions = fieldsRepository.findByBankCodeAndFieldsProtocole("00100","SS");

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Data parsed successfully", tcList);

        }catch (Exception e){
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "An error occurred while parcer details", "");

        }
    }

    private String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    private List<IpmField> parseCleanedData(String data) {
        logger.info("data parseCleanedData ==>: " + data);
        logger.info("Length of data: " + data.length());

        List<IpmField> fields = new ArrayList<>();

        try {

            if (data.length() <590 ) {
                fields.add(new IpmField("000", "MTI", 0, 4, data.substring(0, 4)));
                fields.add(new IpmField("024", "Function code", 4, 3, data.substring(4, 7)));
                fields.add(new IpmField("048", "Additional data - private", 7, 237, data.substring(10, 247)));
                fields.add(new IpmField("048.0164", "PDS 0164", 244, 230, data.substring(17, 247)));
                fields.add(new IpmField("050", "Currency code, Reconciliation", 474, 3, data.substring(247, 250)));
                fields.add(new IpmField("071", "Message number", 477, 8, data.substring(251,258)));
                fields.add(new IpmField("093", "Transaction Dest. Inst. Id code", 485, 6, data.substring(261, 266)));
                fields.add(new IpmField("094", "Transaction Orig. Inst. Id code", 491, 6, data.substring(269, 274)));
                fields.add(new IpmField("100", "Receiving institution Id code", 497, 6, data.substring(277, 282)));

                logger.info("fields ==>: " + fields);
            }else {
                fields.add(new IpmField("000", "MTI", 0, 4, data.substring(0, 4)));
                fields.add(new IpmField("002", "Primary Account number", 4, 16, data.substring(7, 23)));
                fields.add(new IpmField("003", "Processing Code", 20, 6, data.substring(22, 28)));
                fields.add(new IpmField("004", "Amount, Transaction", 26, 12, data.substring(29, 41)));
                fields.add(new IpmField("005", "Amount, Reconciliation", 38, 12, data.substring(41, 53)));
                fields.add(new IpmField("006", "Amount, Cardholder billing", 50, 12, data.substring(53, 65)));
                fields.add(new IpmField("009", "Conversion rate, Reconciliation", 62, 8, data.substring(65, 73)));
                fields.add(new IpmField("010", "Conversion rate, Cardholder billing", 70, 8, data.substring(73, 81)));
                fields.add(new IpmField("012", "Date and time, Local transaction", 78, 12, data.substring(83, 95)));
                fields.add(new IpmField("022", "Point of service data code", 90, 12, data.substring(93, 105)));
                fields.add(new IpmField("024", "Function code", 102, 3, data.substring(81, 84)));
                fields.add(new IpmField("026", "Card acceptor business code", 105, 4, data.substring(108,112 )));
                fields.add(new IpmField("031", "Acquirer reference data", 109, 23, data.substring(114, 137)));
                fields.add(new IpmField("032", "Acquirer institution identification code", 132, 6, data.substring(139, 145)));
                fields.add(new IpmField("033", "Forwarding institution identification code", 138, 6, data.substring(147, 153)));
                fields.add(new IpmField("038", "Approval code", 144, 6, data.substring(153, 159)));
                fields.add(new IpmField("041", "Card acceptor terminal identification", 150, 8, data.substring(159, 167)));
                fields.add(new IpmField("042", "Card acceptor identification code", 158, 15, data.substring(167, 177)));
                fields.add(new IpmField("043", "Card acceptor name/location", 173, 61, data.substring(184, 245)));
                fields.add(new IpmField("048", "Additional data - private", 234, 292, data.substring(248, 540)));
                // Add parsing for PDS fields within "Additional data - private" if needed
                fields.add(new IpmField("048.0002", "Currency code, Transaction", 526, 3, data.substring(553, 556)));
                fields.add(new IpmField("048.0003", "PDS 0003", 529, 3, data.substring(553, 556)));
                fields.add(new IpmField("048.0015", "PDS 0015", 532, 3, data.substring(275, 282)));
                fields.add(new IpmField("048.0023", "PDS 0023", 535, 3, data.substring(289, 292)));


                fields.add(new IpmField("048.0052", "PDS 0052", 237, 3, data.substring(299, 302)));
                fields.add(new IpmField("048.0146", "PDS 0146", 240, 36, data.substring(309, 345)));
                fields.add(new IpmField("048.0148", "PDS 0148", 276, 12, data.substring(353, 365)));
                fields.add(new IpmField("048.0158", "PDS 0158", 288, 31, data.substring(371, 402)));
                fields.add(new IpmField("048.0159", "PDS 0159", 319, 67, data.substring(409, 476)));
                fields.add(new IpmField("048.0165", "PDS 0165", 386, 1, data.substring(483, 484)));
                fields.add(new IpmField("048.0177", "PDS 0177", 387, 2, data.substring(491, 493)));
                fields.add(new IpmField("048.0185", "PDS 0185", 389, 32, data.substring(500, 532)));
                fields.add(new IpmField("048.0191", "PDS 0191", 421, 1, data.substring(490, 491)));



                // Add parsing for PDS fields within "Additional data - private" if needed
                fields.add(new IpmField("049", "Currency code, Transaction", 526, 3, data.substring(315, 318)));
                fields.add(new IpmField("050", "Currency code, Reconciliation", 529, 3, data.substring(20, 23)));
                fields.add(new IpmField("051", "Currency code, Cardholder billing", 532, 3, data.substring(546, 549)));
                fields.add(new IpmField("063", "Reserved for private use", 535, 16, data.substring(553, 566)));
                fields.add(new IpmField("071", "Message number", 551, 8, data.substring(567, 576)));
                fields.add(new IpmField("093", "Transaction Dest. Inst. Id code", 559, 6, data.substring(578, 584)));
                fields.add(new IpmField("094", "Transaction Orig. Inst. Id code", 565, 6, data.substring(586, 592)));
                fields.add(new IpmField("100", "Receiving institution Id code", 571, 6, data.substring(594, 600)));

                logger.info("fields ==>: " + fields);
            }


        } catch (Exception e) {
            logger.error("Exception during parsing: ", e);
        }

        return fields;
    }

}

