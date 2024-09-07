package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "comms_definition")
public class CommsDefinition {
    @EmbeddedId
    private CommsDefinitionId id;


    @Column(name ="com_local_port",length = 4)
    private String comLocalPort;

    @Column(name = "com_local_ip_adress",length = 20)
    private String comLocalIpAdress;

    @Column(name ="com_remote_port",length = 4)
    private String comRemotePort;

    @Column(name = "com_remote_ip_adress",length = 20)
    private String comRemoteIpAdress;

    @Column(name ="com_reconect_com")
    private String comReconectCom;

    @Column(name ="com_header_length")
    private int comHeaderLength;

    @Column(name ="Champ1",length = 255)
    private String champ1;



    public CommsDefinition() {
    }





    public String getComLocalPort() {
        return comLocalPort;
    }

    public void setComLocalPort(String comLocalPort) {
        this.comLocalPort = comLocalPort;
    }

    public String getComLocalIpAdress() {
        return comLocalIpAdress;
    }

    public void setComLocalIpAdress(String comLocalIpAdress) {
        this.comLocalIpAdress = comLocalIpAdress;
    }

    public String getComRemotePort() {
        return comRemotePort;
    }

    public void setComRemotePort(String comRemotePort) {
        this.comRemotePort = comRemotePort;
    }

    public String getComRemoteIpAdress() {
        return comRemoteIpAdress;
    }

    public void setComRemoteIpAdress(String comRemoteIpAdress) {
        this.comRemoteIpAdress = comRemoteIpAdress;
    }

    public String getComReconectCom() {
        return comReconectCom;
    }

    public void setComReconectCom(String comReconectCom) {
        this.comReconectCom = comReconectCom;
    }

    public int getComHeaderLength() {
        return comHeaderLength;
    }

    public void setComHeaderLength(int comHeaderLength) {
        this.comHeaderLength = comHeaderLength;
    }

    public String getChamp1() {
        return champ1;
    }

    public void setChamp1(String champ1) {
        this.champ1 = champ1;
    }

}
