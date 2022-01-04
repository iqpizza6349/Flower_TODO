package com.github.iqpizza6349.flower_tood.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autoID;        // PK

    @Column(unique = true, nullable = false)
    private String email;       // email / ID

    @Column(nullable = false)
    private String password;    // password

    @Column(nullable = false)
    private String auth;        // role

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;    // 사용자의 password 변환
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부 확인
        return true;        // 만료되지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠김 여부 반환
        return true;        // 잠기지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호 만료 여부 반환
        return true;        // 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        // 계정의 활성화 여부 반환
        return true;        // 활성화 됨
    }
}
