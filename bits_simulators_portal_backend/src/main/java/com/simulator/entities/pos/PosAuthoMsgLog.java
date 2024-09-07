package com.simulator.entities.pos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity

public class PosAuthoMsgLog {
    @EmbeddedId
    private PosAuthoMsgLogId authoMsgLogId;
    @Column(name = "SYSTEM_MODE",length = 3)
    private String systemMode;
    @Column(name = "MTI_REQ",length = 4)
    private String mtiReq;
    @Column(name = "MTI_REP",length = 4)
    private String mtiRes;
    @Column(name = "AUT_CASE_NO",length = 50)
    private String autCaseNo;
    @Column(name = "AUT_CASE_SET_ID",length = 50)
    private String autCaseSetId;
    @Column(name = "AUT_CASE_CARD_PRF",length = 50)
    private String autCaseCardPrf;
    @Column(name = "AUT_CASE_MRC_PRF",length = 50)
    private String autCaseMrcPrf;
    @Column(name = "AUT_CASE_TERM_PRF",length = 50)
    private String autCaseTermPrf;
    @Lob
    @Column(name = "AUT_BUFFER_REQ",length = 2000)
    private String autBufferReq;
    @Lob
    @Column(name = "AUT_BUFFER_REP",length = 2000)
    private String autBufferRep;
    @Column(name = "AUT_ACTION_CODE",length = 3)
    private String autActionCode;
    @Column(name = "AUT_REFERENCE",length = 20)
    private String autReference;
    @Column(name = "AUT_STAN",length = 11)
    private String autStan;

    @Column(name = "AUT_USER",length = 20)
    private String autUser;


    @Column(name = "CHECK_VERIF",length = 130)
    private String checkVerif;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PosAuthoMsgLog that = (PosAuthoMsgLog) o;
        return getAuthoMsgLogId() != null && Objects.equals(getAuthoMsgLogId(), that.getAuthoMsgLogId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(authoMsgLogId);
    }
}
