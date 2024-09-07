package com.simulator.globalController;

import com.simulator.config.GlobalVars;
import com.simulator.config.JwtService;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.globalService.AuthenticationService;
import com.simulator.globalService.UserManagementService;
import com.simulator.models.UpdatePasswordRequest;
import com.simulator.models.UserDeleteAndGetRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sa/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class UserManagementController {
    private static final Logger logger = LogManager.getLogger(UserManagementController.class);
    @Autowired
    UserManagementService userManagementService;

    // HAMZA 12.02.2024
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtService jwtService;
    @Autowired
    GlobalVars globalVars;


    @PostMapping("/getUserInfos")
    public ResponseApiJson getUserInfos(@RequestBody String userCode){
        logger.info("userCode passed ==> " + userCode);

        return userManagementService.getUserInfos(userCode);
    }

    @PostMapping("/getUserActivities")
    public ResponseApiJson getUserActivities(@RequestBody String userCode){
        logger.info("userCode activities ==> " + userCode);

        return userManagementService.getUserTracking(userCode);
    }

    @PostMapping("/changePassword")
    public ResponseApiJson<?> changePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest){
        logger.info("###################Start change Password#####################");
        return userManagementService.updatePassword(updatePasswordRequest);
    }

    @PostMapping("/updateUser")
    public ResponseApiJson<?> deleteUser(@RequestBody UserManagement userManagement){
        logger.info("###################Start update user#####################");
        logger.info("UPDATED USER DATA :::> " + userManagement);
        ResponseApiJson<?> apiJson=userManagementService.updateUser(userManagement);
        logger.info("#######################End update user#####################");
        return apiJson;
    }

    @PostMapping("/getListOfUsers")
    public ResponseApiJson<List<UserManagement>> getListOfUsers(@RequestBody UserDeleteAndGetRequest userDeleteAndGetRequest){
        logger.info("###################Start get list of users#####################");
        ResponseApiJson<List<UserManagement>> apiJson=userManagementService.getListOfUser(userDeleteAndGetRequest);
        logger.info("#######################End get list of users#####################");
        return apiJson;
    }
    @PostMapping("/deleteUser")
    public ResponseApiJson<?> deleteUser(@RequestBody UserDeleteAndGetRequest userDeleteAndGetRequest){
        logger.info("###################Start delete user#####################");
        ResponseApiJson<?> apiJson=userManagementService.deleteUser(userDeleteAndGetRequest.getUserCode());
        logger.info("#######################End delete user#####################");
        return apiJson;
    }

    /*@PostMapping("/GetUserNameFromToken")
    public Optional<UserManagement> getUserDT(@RequestBody String req){
        logger.info("req ::> " + req);
        logger.info("getBearerTokenHeader() == " + GlobalVars.getBearerTokenHeader());
        GlobalVars gv = new GlobalVars();
        Optional<UserManagement> user = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("USER Token ::> " + user.get());
        return user;
    }*/






}
