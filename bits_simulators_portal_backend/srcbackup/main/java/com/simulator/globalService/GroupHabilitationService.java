package com.simulator.globalService;

import com.simulator.config.GlobalVars;
import com.simulator.entities.GroupHabilitation;
import com.simulator.entities.Menu;
import com.simulator.entities.UserHabilitationParam;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.models.RequestAddGroup;
import com.simulator.repository.GroupHabilitationRepository;
import com.simulator.repository.MenuRepository;
import com.simulator.repository.UserHabilitationParamRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class GroupHabilitationService {
    @Autowired
    GroupHabilitationRepository groupHabilitationRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    UserHabilitationParamRepository userHabilationParamRepository;
    private static final Logger logger = LogManager.getLogger(GroupHabilitationService.class);


    public ResponseApiJson<?> createGroup(RequestAddGroup requestAddGroup) {
        String idRequest = generateRequestId("MG");
        deleteExistingGroups(requestAddGroup.getCode());
        saveNewGroups(requestAddGroup.getListOfMenus(), requestAddGroup.getCode());
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Operation successful");
    }

    private String generateRequestId(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + (Math.random() * 9999);
    }

    private void deleteExistingGroups(String groupName) {
        List<GroupHabilitation> groupHabilitations = groupHabilitationRepository.findByGroupName(groupName);
        groupHabilitations.forEach(groupHabilitationRepository::delete);
    }

    private void saveNewGroups(List<String> menus, String groupName) {
        menus.forEach(menu -> groupHabilitationRepository.save(new GroupHabilitation(groupName, menu)));
    }

    private ResponseApiJson<RequestAddGroup> getListN(String userCode) {
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
    }

    private RequestAddGroup getJsonObject(String user, int size, List<String> list) {
        RequestAddGroup requestAddGroup = null;
        if (size > 0) {
            requestAddGroup = new RequestAddGroup(user, list);
            logger.info(requestAddGroup.toString());
        }

        return requestAddGroup;
    }

    private ResponseApiJson<RequestAddGroup> menuInUserY(String userCode) {
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





    private ResponseApiJson<RequestAddGroup> groupInUserY(String userCode) {
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
                if (groupHabilitation.getGroupName().equals(userHabilitationParam.getHabilitationCode())&&userHabilitationParam.getTypeRole()=='G') {
                    isOk = true;
                    logger.info("kandkhol");
                    break;
                }

            }
            if (isOk) {
                list.add(groupHabilitation.getGroupName());

//
                logger.info("groups in" + groupHabilitation);
            } isOk = false;
        }
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !", getJsonObject(userCode, list.size(), list));
    }

    private ResponseApiJson<RequestAddGroup> groupInUserN(String userCode) {
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
                if (groupHabilitation.getGroupName().equals(userHabilitationParam.getHabilitationCode())&&userHabilitationParam.getTypeRole()=='G') {
                    isOk = true;
                    logger.info("kandkhol");
                    break;
                }

            }
            if (!isOk) {
                list.add(groupHabilitation.getGroupName());

//
                logger.info("groups in" + groupHabilitation);
            } isOk = false;
        }
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "get successfully !", getJsonObject(userCode, list.size(), list));
    }







    private ResponseApiJson<RequestAddGroup> menuInGroupY(String groupName) {
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


}
