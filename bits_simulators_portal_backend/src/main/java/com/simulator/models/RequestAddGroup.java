package com.simulator.models;

import com.simulator.entities.GroupHabilitation;
import com.simulator.entities.Protocole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddGroup {
    String code;
    List<HabiGroupStruct> listOfMenus;
    String typeRole;

    public RequestAddGroup(String code, List<String> listOfGroups, List<GroupHabilitation> groupHabilitations, List<Protocole> allUProtocols, List<Protocole> UProtocolsNotHave) {
        System.out.println("Code: "+code +" menus: "+listOfGroups);
        this.code = code;
        List<HabiGroupStruct> habiGroupStructs=new ArrayList<>();

        for (String s :
                listOfGroups) {
            habiGroupStructs.add(new HabiGroupStruct(s,groupHabilitations,allUProtocols,UProtocolsNotHave));
        }
        this.listOfMenus = habiGroupStructs;
        this.typeRole = "G";
    }
}
