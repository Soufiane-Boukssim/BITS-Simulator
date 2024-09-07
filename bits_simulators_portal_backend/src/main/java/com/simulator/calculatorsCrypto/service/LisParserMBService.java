package com.simulator.calculatorsCrypto.service;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.simulator.calculatorsCrypto.model.IpmField;
import com.simulator.calculatorsCrypto.model.TCFactory;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.FieldsDefinitionRepository;
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
public class LisParserMBService {
    private final Logger logger = LogManager.getLogger(DESDE055Service.class);
    @Autowired
    FieldsDefinitionRepository fieldsDefinitionRepository ;


            public ResponseApiJson<?> uploadFile(MultipartFile file) throws Exception {
        String idRequest = "Upload_File" + new Date().getTime() + (Math.random() * 9999);
        logger.info("Processing file: " + file.getOriginalFilename());
                List<LineData> dataList = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            lines = reader.lines().collect(Collectors.toList());
        }


                logger.info("lines ==>["+lines+" ]");
                int lineNumber = 1;
                for (String part : lines) {
                    logger.info("part [" + part + "]: ");
                    dataList.add(new LineData(lineNumber, part));
                    lineNumber++;
                }

        return new ResponseApiJson<>(idRequest, "000", "Data parsed successfully", dataList);
    }

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







    //// show  les donnee  de chaque ligne



            public  ResponseApiJson<?>  showDetails(String data) {
                String idRequest = "Show_Details" + new Date().getTime() + (Math.random() * 9999);

                logger.info("data===>" + data);
                List<IpmField> dataShow_Details = TCFactory.createTC(data);

                logger.info("dataShow_Details ["+dataShow_Details +"]");


                try {
                    return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Data parsed successfully", dataShow_Details);
                } catch (Exception e) {
                    return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "An error occurred while parcer details", "");
                }


            }
}