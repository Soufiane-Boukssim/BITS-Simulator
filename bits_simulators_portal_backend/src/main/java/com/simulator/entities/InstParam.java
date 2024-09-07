package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "inst_param")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class InstParam {
    @EmbeddedId
    private InstParamId id;



    @Column(name = "INST_TRANS_CUR", length = 3)
    private String instTransCur1;


    @Column(name = "INST_SETT_CURR", length = 3)
    private String instSettCurr;


    @Column(name = "PIN_KEY", length = 256)
    private String pinKey;

    @Column(name = "MAC_KEY", length = 256)
    private String macKey;

    @Column(name = "Pin_format")
    private String pinFormat;

    @Column(name = "MASTER_KEY", length = 256)
    private String masterKey;

    @Column(name = "chipSupported")
    private Character chipSupported;

    @Column(name = "TIME_OUT_COMMS")
    private Integer timeOutComms;

    @Column(name = "ACQ_CODE")
    private String acqCode;

    @Column(name = "FWD_CODE")
    private String fwdCode;

    @Column(name = "station_id", length = 6)
    private String stationId;

    @Column(name = "acquirer_id", length = 11)
    private String acquirerId;

    @Column(name = "ica", length = 6)
    private String ica;

    @Column(name = "processor_id", length = 11)
    private String processorId;



    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        InstParam instParam = (InstParam) o;
        return getId() != null && Objects.equals(getId(), instParam.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
