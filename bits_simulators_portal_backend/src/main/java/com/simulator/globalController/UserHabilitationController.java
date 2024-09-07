package com.simulator.globalController;

import com.simulator.dtos.UserHabilitationValidBody;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.globalService.GroupHabilitationService;
import com.simulator.models.RequestAddGroup;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/api/sa/userHab")
@SecurityRequirement(name = "Bearer Authentication")
public class UserHabilitationController {

    @Autowired
    private GroupHabilitationService groupHabilitationService;
    private static final Logger logger = LogManager.getLogger(UserHabilitationController.class);

    @PostMapping("/GroupsInUser")
    public ResponseApiJson<RequestAddGroup> getMenusInUser(@RequestBody UserHabilitationValidBody userHabilitationValidBody, HttpServletResponse resp){
        logger.info("######## Start / Add  ##########");
        logger.info("E7M" + userHabilitationValidBody);
        logger.info("(userHabilitationValidBody.getUserCode()) ==> ", userHabilitationValidBody.getUserCode());
        String method="Y";
        HttpServletRequest reqHttp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ResponseApiJson<RequestAddGroup> logResp =groupHabilitationService.groupInUserY(userHabilitationValidBody.getUserCode());
        logger.info("######## End / Add  ##########");
        return logResp;
    }

    @PostMapping(value="/GroupsNotInUser")
    public ResponseApiJson<RequestAddGroup> getMenusNotInUser(@RequestBody UserHabilitationValidBody userHabilitationValidBody, HttpServletResponse resp){
        logger.info("######## Start / Add  ##########");

        String method="Y";
        HttpServletRequest reqHttp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ResponseApiJson<RequestAddGroup> logResp =groupHabilitationService.groupInUserN(userHabilitationValidBody.getUserCode());
        logger.info("######## End / Add  ##########");
        return logResp;
    }

    @GetMapping("/allGroups")
    public ResponseApiJson<?> getAllGroups(){
        ResponseApiJson<?> logRes = groupHabilitationService.getAllGroups();
        logger.info("GROUP LIST :: " + logRes.getResult().toString());
        return logRes;
    }


    @GetMapping("/allMenus")
    public ResponseApiJson<?> getAllMenus(){
        ResponseApiJson<?> logRes = groupHabilitationService.getAllMenus();
        logger.info("MENU LIST :: " + logRes.getResult().toString());
        return logRes;
    }

    /*@PostMapping(value="/addGroup")
    public ResponseApiJson<?> addGroup(@RequestBody GroupHabilitation groupHabilitation, HttpServletResponse resp){
        logger.info("######## Start / Add  ##########");
        logger.info("ADD NEW GROUP");


        String method="Y";
        HttpServletRequest reqHttp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ResponseApiJson<?> logResp =groupHabilitationService.createGroup(groupHabilitation.getGroupId().getGroupCode(),
                groupHabilitation.getGroupName(),groupHabilitation.getGroupId().getMenuCode(),groupHabilitation.getGroupId().getProtoId());
        logger.info("######## End / Add  ##########");
        return logResp;
    }*/


    @PostMapping(value="/validate")
    public ResponseApiJson validerMenus(@RequestBody UserHabilitationValidBody userHabilitationValidBody, HttpServletResponse resp){
        logger.info("######## Start / VALIDATION  ##########");
        logger.info("VALIDATE STAGE  :: userHabilitationValidBody ===> " + userHabilitationValidBody);

        String method="V";
        HttpServletRequest reqHttp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ResponseApiJson logResp =groupHabilitationService.userHabilitationValider(userHabilitationValidBody  , reqHttp,method);
        logger.info("######## End / VALIDATION  ##########");
        return logResp;
    }

    @PostMapping("/addGroup")
    public ResponseApiJson<?> addGroup( @RequestParam String groupCode,
                                     @RequestParam String groupName,
                                     @RequestParam String menuCode){
        logger.info("groupCode add ==> " + groupCode);
        ResponseApiJson<?> logResp = groupHabilitationService.createGroup(groupCode,groupName,menuCode);
        return logResp;
    }

    @PostMapping("/updateGroup")
    public ResponseApiJson<?> modifyGroup( @RequestParam String groupCode,
                                        @RequestParam String groupName,
                                        @RequestParam String menuCode){
        logger.info("groupCode update ==> " + groupCode);
        ResponseApiJson<?> logResp = groupHabilitationService.modifyGroup(groupCode,groupName,menuCode);
        return logResp;
    }

    @PostMapping("/removeGroup")
    public ResponseApiJson<?> removeGroup( @RequestParam String groupCode){
        ResponseApiJson<?> logResp = groupHabilitationService.removeGroup(groupCode);
        return logResp;
    }
}
