package com.example.jwt.article.entity;

import com.example.jwt.domain.member.entity.Member;
import com.example.jwt.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@SuperBuilder
@Entity
@AllArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public class Article extends BaseEntity {
    @ManyToOne
    private Member author;
    private String subject;
    private String content;
}
