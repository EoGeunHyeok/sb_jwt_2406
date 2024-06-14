package com.example.jwt.domain.member.entity;

import com.example.jwt.global.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PROTECTED;


@Getter
@Setter
@SuperBuilder
@Entity
@AllArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;

    @JsonIgnore // password는 json에서 무시 하게 하겠다.
    private String password;

    private String email;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        return authorities;
    }

    public Map<String, Object> toClaims() {
        return Map.of(
                "id", getId(),
                "username", getUsername()
        );
    }
}
