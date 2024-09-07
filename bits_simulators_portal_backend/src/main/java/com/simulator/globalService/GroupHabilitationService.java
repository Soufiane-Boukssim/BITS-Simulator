package com.simulator.globalService;

import com.simulator.config.GlobalVars;
import com.simulator.dtos.UserHabilitationValidBody;
import com.simulator.entities.*;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.models.RequestAddGroup;
import com.simulator.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupHabilitationService {
    @Autowired
    GroupHabilitationRepository groupHabilitationRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    UserHabilitationParamRepository userHabilationParamRepository;

    @Autowired
    ProtocoleRepository protocoleRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;
    private static final Logger logger = LogManager.getLogger(GroupHabilitationService.class);




    public ResponseApiJson<?> createGroup(String groupCode,String groupName,String menuCode) {
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            String idRequest = generateRequestId("MG");
            GroupHabilitation group = new GroupHabilitation();
            GroupHabilitationId groupId = new GroupHabilitationId();
            groupId.setGroupCode(groupCode);
            groupId.setMenuCode(menuCode);
            //groupId.setProtoId(protoId);
            group.setGroupName(groupName);
            group.setGroupId(groupId);

            groupHabilitationRepository.save(group);

            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "GroupHabilitations","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add Group","Group Habilitation added successfully !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Create Group was Successful");
        }catch (Exception e) {
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "GroupHabilitations","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add Group","Group Habilitation addition Failed !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            logger.info("Exception of Update password " + e.getMessage());

            String idRequest = generateRequestId("MG");
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Create Group was UnSuccessful");

        }
    }


    public ResponseApiJson<?> modifyGroup(String groupCode,String groupName,String menuCode) {
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        //logger.info("groupCode ==> '" +groupCode+ "' // groupName ==> '"+groupName+"' menuCode ==> '" +menuCode+ "'");
        String idRequest = generateRequestId("MG");
        int res = groupHabilitationRepository.updateGroup(groupCode,groupName,menuCode);
        //logger.info("update response ::> " + res);

        // user Activity tracking Hamza 29.02.2024 start
        UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "GroupHabilitations","000.000.00.00", "Success", GlobalVars.SUCCESS,"Update Group","Group Habilitation Updated successfully !",new Date());
        userActivityTrackingRepository.save(userTrace);
        // user Activity tracking Hamza 29.02.2024 end
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update Operation Successful");
    }

    public ResponseApiJson<?> removeGroup(String groupCode) {
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info(" Delete groupCode ==> " +groupCode);
        String idRequest = generateRequestId("MG");
        groupHabilitationRepository.deleteGroup(groupCode);
        // user Activity tracking Hamza 29.02.2024 start
        UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "GroupHabilitations","000.000.00.00", "Success", GlobalVars.SUCCESS,"Remove Group","Group Habilitation Deleted successfully !",new Date());
        userActivityTrackingRepository.save(userTrace);
        // user Activity tracking Hamza 29.02.2024 end
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete Operation Successful");
    }

    /*public ResponseApiJson<?> createGroup(RequestAddGroup requestAddGroup) {
        String idRequest = generateRequestId("MG");
        deleteExistingGroups(requestAddGroup.getCode());
        saveNewGroups(requestAddGroup.getListOfMenus(), requestAddGroup.getCode());
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Operation successful");
    }*/

    private String generateRequestId(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + (Math.random() * 9999);
    }
/*
    private void deleteExistingGroups(String groupName) {
        List<GroupHabilitation> groupHabilitations = groupHabilitationRepository.findByGroupName(groupName);
        groupHabilitations.forEach(groupHabilitationRepository::delete);
    }
*/
    /*private ResponseApiJson<?> addNewGroup(GroupHabilitation groupHabilitation,GroupHabilitationId groupHabilitationId) {
        String groupCode = groupHabilitationId.getGroupCode();
        String menuCode = groupHabilitationId.getMenuCode();

        var group = GroupHabilitation.builder().groupId(groupHabilitationId)
                .groupName(groupHabilitation.getGroupName()).build();

        return ResponseApiJson<?> resp =  groupHabilitationRepository.save(group);
    }*/

    /*private ResponseApiJson<RequestAddGroup> getListN(String userCode) {
        String idRequest = "MNI_" + new Date().getTime() + (Math.random() * 9999);
        List<Menu> menus;
        List<Menu> menuNotInUser = new ArrayList<>();
        List<UserHabilitationParam> menuInUser;
        List<String> list = new ArrayList<>();
        Boolean isOk = false;


        menus = menuRepository.findAll();
        menuInUser = userHabilationParamRepository.findByUserCode(userCode);
        logger.info(menus.toString());
        logger.info("----------------------------------------------------------------------------");
        logger.info(menuInUser.toString());
        for (Menu menu : menus) {
            for (UserHabilitationParam userHabilitationParam : menuInUser) {
                logger.info(menu.getId() + " ________-------- " + userHabilitationParam.getHabilitationCode());
                if (menu.getId().equals(userHabilitationParam.getHabilitationCode())&&userHabilitationParam.getTypeRole()=='M') {
                    isOk = true;
                    logger.info("kandkhol");
                    break;
                }
            }
            if (!isOk) {
                list.add(menu.getId());
//
                logger.info("menu not in" + menu.toString());
            }
            isOk = false;
        }
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Account created successfully !", getJsonObject(userCode, list.size(), list));
    }*/

    private RequestAddGroup getJsonObject(String user, int size, List<String> list) {
        RequestAddGroup requestAddGroup = new RequestAddGroup();
        List<Protocole> listOfProto = new ArrayList<>();
        List<Protocole> listOfProtoNotIn = new ArrayList<>();
        String wording = "";
        List<GroupHabilitation> groupHabilitations=groupHabilitationRepository.findAll();
        List<Protocole> protocolsL=protocoleRepository.findAll();
        List<String> userProtocols = userHabilationParamRepository.getUserIsoProtocols(user);
       // logger.info("userProtocols =====> " + userProtocols);
       // logger.info("groupHabilitations =====> " + protocolsL);
        boolean ok = false;
        /*for (Protocole p:
                protocolsL) {*/
        if (userProtocols.size() > 0) {
            if (userProtocols.get(0) == null) {
                logger.info("empty");
                listOfProto = new ArrayList<>();
            } else {
                for (String up :
                        userProtocols) {
                    //logger.info("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU ::> " + up);
                    //logger.info("in");
                    wording = protocoleRepository.getWordingById(up);
                    listOfProto.add(new Protocole(up, wording));

                }
            }
        }else {
            listOfProto = new ArrayList<>();
        }


        logger.info("protocolsL " + protocolsL);
        int c = 0;
        List<Protocole> filteredProtocols = protocolsL.stream()
                .filter(protocol -> !userProtocols.contains(protocol.getIdProtocole()))
                .collect(Collectors.toList());
        logger.info("filteredProtocols " + filteredProtocols);


        //}
        logger.info("LIST OF PROTOCOLS" + listOfProto);
        if (size > 0) {

            requestAddGroup = new RequestAddGroup(user, list,groupHabilitations,listOfProto,filteredProtocols);
            logger.info("getJsonObject insid if :: > " +requestAddGroup.toString());
        }

        return requestAddGroup;
    }

    /*public ResponseApiJson<RequestAddGroup> menuInUserY(String userCode) {
        String idRequest = "MI_" + new Date().getTime() + (Math.random() * 9999);
        List<Menu> menus;
        List<Menu> menuNotInUser = new ArrayList<>();
        List<UserHabilitationParam> menuInUser;
        List<String> list = new ArrayList<>();

        boolean isOk = false;
        menus = menuRepository.findAll();
        menuInUser = userHabilationParamRepository.findByUserCode(userCode);
        logger.info(menus.toString());
        logger.info("----------------------------------------------------------------------------");
        logger.info(menuInUser.toString());
        for (Menu menu : menus) {
            for (UserHabilitationParam userHabilitationParam : menuInUser) {
                logger.info(menu.getId() + " ________-------- " + userHabilitationParam.getHabilitationCode());
                if (menu.getId().equals(userHabilitationParam.getHabilitationCode())&&userHabilitationParam.getTypeRole()=='M') {
                    isOk = true;
                    logger.info("kandkhol");
                    break;
                }

            }
            if (isOk) {
                list.add(menu.getId());

//
                logger.info("menu in" + menu);
            } isOk = false;
        }
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !", getJsonObject(userCode, list.size(), list));
    }


*/
    public ResponseApiJson<?> getAllGroups(){
        String idRequest = "GALL" + new Date().getTime() + (Math.random() * 9999);
        List<GroupHabilitation> groupList = groupHabilitationRepository.findAll();
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !",groupList);
    }

    public ResponseApiJson<?> getAllMenus(){
        String idRequest = "GALL" + new Date().getTime() + (Math.random() * 9999);
        List<Menu> groupList = menuRepository.findAll();
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !",groupList);
    }


    public ResponseApiJson<RequestAddGroup> groupInUserY(String userCode) {
        String idRequest = "GIUY" + new Date().getTime() + (Math.random() * 9999);
        List<GroupHabilitation> groupHabilitations;
        List<GroupHabilitation> groupNotInUser = new ArrayList<>();
        List<UserHabilitationParam> groupInUser;
        List<String> list = new ArrayList<>();

        boolean isOk = false;
        groupHabilitations = groupHabilitationRepository.findAll();
        groupInUser = userHabilationParamRepository.findByUserCode(userCode);
        logger.info(groupHabilitations.toString());
        logger.info("----------------------------------------------------------------------------");
        logger.info(groupInUser.toString());
        for (GroupHabilitation groupHabilitation : groupHabilitations) {
            for (UserHabilitationParam userHabilitationParam : groupInUser) {
                logger.info(groupHabilitation.getGroupName() + " ________-------- " + userHabilitationParam.getHabilitationCode());
                logger.info("boolen "+(groupHabilitation.getGroupId().getGroupCode().equals(userHabilitationParam.getHabilitationCode())&&userHabilitationParam.getTypeRole()=='G'));

                if (groupHabilitation.getGroupId().getGroupCode().equals(userHabilitationParam.getHabilitationCode())&&userHabilitationParam.getTypeRole()=='G') {
                    isOk = true;

                    logger.info("kandkhol");
                    break;
                }

            }
            if (isOk) {
                list.add(groupHabilitation.getGroupId().getGroupCode());

                logger.info("List Groups :: " + list);
//
                logger.info("groups in" + groupHabilitation);
            } isOk = false;
        }
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !", getJsonObject(userCode, list.size(), list));
    }

    public ResponseApiJson<RequestAddGroup> groupInUserN(String userCode) {
        String idRequest = "GIUN_" + new Date().getTime() + (Math.random() * 9999);
        List<GroupHabilitation> groupHabilitations;
        List<GroupHabilitation> groupNotInUser = new ArrayList<>();
        List<UserHabilitationParam> groupInUser;
        List<String> list = new ArrayList<>();

        boolean isOk = false;
        groupHabilitations = groupHabilitationRepository.findAll();
        groupInUser = userHabilationParamRepository.findByUserCode(userCode);
        logger.info(groupHabilitations.toString());
        logger.info("----------------------------------------------------------------------------");
        logger.info(groupInUser.toString());
        for (GroupHabilitation groupHabilitation : groupHabilitations) {
            for (UserHabilitationParam userHabilitationParam : groupInUser) {
                logger.info(groupHabilitation.getGroupName() + " ________-------- " + userHabilitationParam.getHabilitationCode());
                logger.info("boolen "+(groupHabilitation.getGroupName().equals(userHabilitationParam.getHabilitationCode())&&userHabilitationParam.getTypeRole()=='G'));
                if (groupHabilitation.getGroupId().getGroupCode().equals(userHabilitationParam.getHabilitationCode())&&userHabilitationParam.getTypeRole()=='G') {
                    isOk = true;
                    logger.info("kandkhol");
                    break;
                }

            }
            logger.info("isOk "+isOk);
            if (!isOk) {
                list.add(groupHabilitation.getGroupId().getGroupCode());

//
                logger.info("groups in" + groupHabilitation);
            } isOk = false;
        }
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !", getJsonObject(userCode, list.size(), list));
    }







   /* private ResponseApiJson<RequestAddGroup> menuInGroupY(String groupName) {
        String idRequest = "MIGY_" + new Date().getTime() + (Math.random() * 9999);
        List<Menu> menus;
        List<Menu> menuNotInUser = new ArrayList<>();
        List<GroupHabilitation> menuInGroup;
        List<String> list = new ArrayList<>();

        boolean isOk = false;
        menus = menuRepository.findAll();
        menuInGroup = groupHabilitationRepository.findByGroupName(groupName);
        logger.info(menus.toString());
        logger.info("----------------------------------------------------------------------------");
        logger.info(menuInGroup.toString());
        for (Menu menu : menus) {
            for (GroupHabilitation groupHabilitation : menuInGroup) {
                logger.info(menu.getId() + " ________-------- " + groupHabilitation.getMenuCode());
                if (menu.getId().equals(groupHabilitation.getMenuCode())) {
                    isOk = true;
                    logger.info("kandkhol");
                    break;
                }

            }
            if (isOk) {
                list.add(menu.getId());

//
                logger.info("menu in" + menu);
            } isOk = false;
        }
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !", getJsonObject(groupName, list.size(), list));
    }

    private ResponseApiJson<RequestAddGroup> menuInGroupN(String groupName) {
        String idRequest = "MIGN_" + new Date().getTime() + (Math.random() * 9999);
        List<Menu> menus;
        List<Menu> menuNotInUser = new ArrayList<>();
        List<GroupHabilitation> menuInGroup;
        List<String> list = new ArrayList<>();

        boolean isOk = false;
        menus = menuRepository.findAll();
        menuInGroup = groupHabilitationRepository.findByGroupName(groupName);
        logger.info(menus.toString());
        logger.info("----------------------------------------------------------------------------");
        logger.info(menuInGroup.toString());
        for (Menu menu : menus) {
            for (GroupHabilitation groupHabilitation : menuInGroup) {
                logger.info(menu.getId() + " ________-------- " + groupHabilitation.getMenuCode());
                if (menu.getId().equals(groupHabilitation.getMenuCode())) {
                    isOk = true;
                    logger.info("kandkhol");
                    break;
                }

            }
            if (isOk) {
                list.add(menu.getId());

//
                logger.info("menu in" + menu);
            } isOk = false;
        }
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !", getJsonObject(groupName, list.size(), list));
    }


    public ResponseApiJson<?> userHabilitationValider(RequestAddGroup reqB2W) {
        String idRequest = generateRequestId("UHV");
        //String idRequest = generateRequestId("Valid");
        boolean isOk = false;
        int i = userHabilationParamRepository.maxValue();
        UserHabilitationParam habilitationId;

        userHabilationParamRepository.deleteByhabilitationCode(reqB2W.getCode());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 1);
        for (String habilitationValidBody : reqB2W.getListOfMenus()) {
            i++;
            habilitationId = new UserHabilitationParam(i, habilitationValidBody,
                    reqB2W.getCode(), new Date(), c.getTime(), reqB2W.getTypeRole().charAt(0));


            isOk = validerRoles(habilitationId);;
        }
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !");



    }

    public Boolean validerRoles(UserHabilitationParam userHabilitationParam) {

        userHabilationParamRepository.save(userHabilitationParam);
        return true;

    }


    /*public ResponseApiJson<Menu> getMenu(String userCode) throws JSONException {

        List<UserHabilitationParam> userHabilationParams;
        List<Menu> menus1 = new ArrayList<>();
        List<String>
        userHabilationParams = userHabilationParamRepository.findByUserCode(userCode);
        List<Menu> menus = menuRepository.findAll();
        for (Menu dto : menus) {
            for(UserHabilitationParam userHabilationParam: userHabilationParams){
                if(dto.getId().equals(userHabilationParam.getHabilitationCode())&&userHabilationParam.getTypeRole()=='M'){
                    menus1.add(dto);
                }
                if ()
            }
        }


        try {


            menuStr = Obj.writeValueAsString(menus1);
            System.out.println(menuStr);
            menus1.clear();
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        System.out.println(menus1.size()+" \n");
        resp.setMessage("successfull");


        JSONArray jsonArray = new JSONArray(menuStr);
        json = new JSONObject();
        json.put("test",menuStr);
        resp.setResult( menuStr );
        menuStr="";
        json.remove("test");
        return resp;
    }*/


    public ResponseApiJson userHabilitationValider(UserHabilitationValidBody reqB2W, HttpServletRequest reqHttp, String method) {
        ResponseApiJson respB2W = new ResponseApiJson();
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
       // logger.info("Starting Service userManagment");

        String reId = "UHP"+(int)(Math.random()*9999);
        //logger.info("Request id generated is " + reId);
       // logger.info("Protocols list ::> " + reqB2W.getProtocols());
        respB2W.setIdRequest(reId);

        boolean isOk = false;
        boolean isGood = false;
        List<UserHabilitationParam> tableValue = userHabilationParamRepository.findAll();
        int i= 0;
        if (tableValue.size() > 0) {
            i = userHabilationParamRepository.maxValue();
            logger.info("TABLE MAX VALUE ::> " + i);
        }
        else{
            i = 10;
        }
        UserHabilitationParam habilitationId;

        userHabilationParamRepository.deleteByhabilitationCode(reqB2W.getUserCode());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 1);
        for (String habilitationValidBody : reqB2W.getMenuName()) {
            i++;
            logger.info("i++ " + i);
            habilitationId = new UserHabilitationParam(i, habilitationValidBody,
                    reqB2W.getUserCode(), new Date(), c.getTime(), 'G',reqB2W.getProtocol());
            if(habilitationValidBody.equals("ISO")){
                for (String proto:
                        reqB2W.getProtocols()) {
                    habilitationId = new UserHabilitationParam(i++, habilitationValidBody,
                            reqB2W.getUserCode(), new Date(), c.getTime(), 'G',proto);
                    isGood = validerGroups(habilitationId);
                }
            }

            isOk = validerGroups(habilitationId);
        }


        if (isOk && isGood) {
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "UserHabilitations","000.000.00.00", "Success", GlobalVars.SUCCESS,"Updating Habilitations of the user: "+ reqB2W.getUserCode() +"","user Habilitations updated successfully !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end

            respB2W.setResult("done");
            respB2W.setRespCode(GlobalVars.SUCCESS);
            respB2W.setRespMsg("add user habilitation finished successfully");
            //addUserActivityTracking(GlobalVars.USER_MANAGEMENT, respB2W.getStatus(), respB2W.getRespCode(), GlobalVars.getClientIP(reqHttp), reqHttp.getHeader("User-Agent"), respB2W.getAuthCode(), respB2W.toString());
            return respB2W;
        }
        else {
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "UserHabilitations","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Updating Habilitations of the user: "+ reqB2W.getUserCode() +"","user Habilitations updated Unsuccessfully !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            respB2W.setResult("ERROR ON INSERT");
            respB2W.setRespCode(GlobalVars.EXCEPTIONSISSUE);
            respB2W.setRespMsg("add user habilitation finished successfully");
            //addUserActivityTracking(GlobalVars.USER_MANAGEMENT, respB2W.getStatus(), respB2W.getRespCode(), GlobalVars.getClientIP(reqHttp), reqHttp.getHeader("User-Agent"), respB2W.getAuthCode(), respB2W.toString());
            return respB2W;
        }
    }

    public Boolean validerGroups(UserHabilitationParam user) {


        userHabilationParamRepository.save(user);
        return true;

    }

}
