package com.example.busmanage.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "u_username", columnNames = {"username"}))
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private Boolean isEnabled;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private String authorities;
}
