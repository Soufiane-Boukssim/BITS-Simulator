package com.simulator.models;

import com.simulator.entities.GroupHabilitation;
import com.simulator.entities.Protocole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class HabiGroupStruct {
    private String groupCode;

    private List<String> listRoles=new ArrayList<>();

    private List<Protocole> listProtocols=new ArrayList<>();

    private List<Protocole> listProtocolsNHave=new ArrayList<>();



    public HabiGroupStruct() {

    }

    public HabiGroupStruct(String groupCode, List<GroupHabilitation> groupHabilitations,List<Protocole> allUserProtocols,List<Protocole> userProtocolsNHave){

        List<GroupHabilitation> groupList = groupHabilitations;
        List<Protocole> protoList = allUserProtocols;
        System.out.println("Final list ::> " + groupList);
        System.out.println("Final Proto list IN ::> " + protoList);
        if(groupList.size() > 0) {
            for (GroupHabilitation group :
                    groupList) {
                System.out.println(group.getGroupId().getGroupCode().equals(groupCode) + " " + group.getGroupId().getGroupCode() + " " + groupCode);
                if (group.getGroupId().getGroupCode().equals(groupCode)) {
                    this.listRoles.add(group.getGroupId().getMenuCode());
                }
                if (group.getGroupId().getGroupCode().equals("ISO")) {
                    this.listProtocolsNHave = userProtocolsNHave;
                }
            }
        }
        for (Protocole p :
                protoList) {
            System.out.println("In number of protocoles +++ " + p);
                        /*if (group.getGroupId().getProtoId() == p.getIdProtocole()) {
                            this.listProtocols.add(p.getProtoWording());
                        }*/
            this.listProtocols.add(p);
        }
        this.groupCode = groupCode;
    }
}
