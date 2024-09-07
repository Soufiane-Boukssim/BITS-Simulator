package com.simulator.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "USER_HABILITATION_PARAM")
@NamedQuery(name="UserHabilitationParam.deleteByhabilitationCode" , query="delete from UserHabilitationParam y where y.userCode = ?1")
public class UserHabilitationParam {
    @Id
    @Column(name = "USER_HABILITATION_ID", nullable = false)
    private int userHabilitationId;
    @Column(name = "HABILITATION_CODE", nullable = false)
    private String habilitationCode;
    @Column(name = "USER_CODE", nullable = false)
    private String userCode;
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;
    @Column(name = "TYPE_ROLE", nullable = true)
    private char typeRole;

    public UserHabilitationParam(int userHabilitationId, String habilitationCode, String userCode, Date startDate, Date endDate, char typeRole) {
        this.userHabilitationId = userHabilitationId;
        this.habilitationCode = habilitationCode;
        this.userCode = userCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeRole = typeRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserHabilitationParam that = (UserHabilitationParam) o;
        return  Objects.equals(userHabilitationId, that.userHabilitationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
