package com.imara.shipping.model;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.imara.shipping.model.core.AbstractObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "[users]")
@SequenceGenerator(name = "user_seq", allocationSize = 1)
public class User extends AbstractObject implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private long id;

    @NotNull
    private String fullName;
    
    @Column(unique = true)
    @NotNull
    private String username;
    private String phoneNumber;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean active;
    private long refId;

    @Transient
    private String passwordN; // This is used to send new password from client to server

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.userRole.getDescription());
        authorities.add(authority);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // return LocalDate.now().isBefore(getExpiryDate());
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

    public boolean isMasterAdmin() {
        return UserRole.MASTER_ADMIN == this.userRole;
    }

    public boolean isCompany() {
        return UserRole.COMPANY == this.userRole;
    }

    public boolean isCustomer() {
        return UserRole.CUSTOMER == this.userRole;
    }

    public boolean isDriver() {
        return UserRole.DRIVER == this.userRole;
    }
}
