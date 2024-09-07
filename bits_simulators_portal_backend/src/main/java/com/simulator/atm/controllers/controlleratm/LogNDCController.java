package com.simulator.atm.controllers.controlleratm;

import com.simulator.atm.services.atmm.LogNDCService;
import com.simulator.atm.services.atmm.NDCStateTypeService;
import com.simulator.entities.*;
import com.simulator.globalModels.ResponseApiJson;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/atm/log_ndc")
public class LogNDCController {
    @Autowired
    LogNDCService logNDCService;
    @Autowired
    NDCStateTypeService ndcStateTypeService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/configuration")
    private ResponseApiJson<List<LogNDCConfiguration>> getNDCConfig(@RequestBody Map<String, String> requestBody){
        String bankCode = requestBody.get("bankCode");
        ResponseApiJson<List<LogNDCConfiguration>> responseApiJson=logNDCService.getNDCConfiguration(bankCode);
        return responseApiJson;

    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/screen")
    private ResponseApiJson<List<LogNDCScreen>> getNDCScreen(@RequestBody Map<String, String> requestBody){
        String bankCode = requestBody.get("bankCode");
        ResponseApiJson<List<LogNDCScreen>> responseApiJson=logNDCService.getNDCScreen(bankCode);
        return responseApiJson;

    }



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/state")
    private ResponseApiJson<List<LogNDCState>> getNDCState(@RequestBody Map<String, String> requestBody){
        String bankCode = requestBody.get("bankCode");
        ResponseApiJson<List<LogNDCState>> responseApiJson=logNDCService.getNDCState(bankCode);
        return responseApiJson;

    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/fit")
    private ResponseApiJson<List<LogNDCFit>> getNDCFIT(@RequestBody Map<String, String> requestBody){
        String bankCode = requestBody.get("bankCode");
        ResponseApiJson<List<LogNDCFit>> responseApiJson=logNDCService.getNDCFIT(bankCode);
        return responseApiJson;

    }





    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/aid")
    private ResponseApiJson<List<LogNDCAID>> getNDCAID(@RequestBody Map<String, String> requestBody){
        String bankCode = requestBody.get("bankCode");
        ResponseApiJson<List<LogNDCAID>> responseApiJson=logNDCService.getNDCAID(bankCode);
        return responseApiJson;

    }






    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/state_type")
    private ResponseApiJson<List<NDCStateType>> getStateType(){
        ResponseApiJson<List<NDCStateType>> responseApiJson=ndcStateTypeService.getNDCType();
        return responseApiJson;

    }


    @GetMapping("/image/{imageId}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageId) throws MalformedURLException {
        String imageName = "pic" + imageId + ".jpg";
        //String imagePathString = "C:\\Users\\BITS-DEV-5530\\Documents\\Business IT Solutions\\SmartATM-SIM\\BitsPictures\\" + imageName;
        String imagePathString = "C:\\Users\\BITS-DEV-5530\\Documents\\bouazizi\\BitsPictures\\" + imageName;
        Path imagePath = Path.of(imagePathString);
        Resource resource = new UrlResource(imagePath.toUri());

        try {
            File file = resource.getFile();
            if (!file.exists() || !file.canRead()) {
                // Gérer l'erreur si le fichier n'existe pas ou n'est pas lisible
            }
        } catch (Exception e) {
            // Gérer l'exception si elle se produit lors de l'accès au fichier
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

}
