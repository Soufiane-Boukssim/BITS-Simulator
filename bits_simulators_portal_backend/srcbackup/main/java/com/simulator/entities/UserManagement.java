package com.simulator.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserManagement implements UserDetails {
    @Id
    @Column(name = "USER_CODE")
    private String userCode;

    private String userName;
    @Column(name = "USER_BANK_CODE")
    private String userBankCode;

    @Enumerated(EnumType.STRING)
    private Role userType;
    private String address;
    private String password;

    private Integer numberOfTries;

    @Column(name = "FIRST_CONNECTION")
    private Character firstConnection;

    private Integer numberOfTriesAllowed;

    private String ipAddress;

    private String connected;

    private Integer nbrSessionAllowed;

    private Integer nbrSessionConnected;

    private Integer lengthPassword;

    private Character complexityFlag;

    private Integer expirationPassword;

    private String expiredPassword;

    private Date dateStartPass;

    private Date dateEndPass;

    private Character blockAccess;

    private String languageCode;

    private String last4Pwd;

    private String email;

    private String mobileNumber;

    private Character forgotPwdRequest;
    @Column(length = 1)
    private String status;//(A = approval / P = padding)

    private String secretKey;

    @Column(name = "ACCESSED_MODULES")
    private String accessedModules;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userType.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.userCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserManagement that = (UserManagement) o;
        return getUserCode() != null && Objects.equals(getUserCode(), that.getUserCode());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
