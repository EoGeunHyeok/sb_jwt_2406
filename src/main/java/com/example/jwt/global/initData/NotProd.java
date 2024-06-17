package com.example.jwt.global.initData;

import com.example.jwt.article.service.ArticleService;
import com.example.jwt.domain.member.entity.Member;
import com.example.jwt.domain.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile({"dev","test"})
public class NotProd {
    @Bean
    CommandLineRunner initData(MemberService memberService, ArticleService articleService, PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode("1234");
        return args -> {
            Member admin = memberService.join("admin", password, "admin@test.com");
            Member member1 = memberService.join("user2", password, "user2@test.com");
            Member member2 = memberService.join("황예지", password, "user3@test.com");

            articleService.write(admin, "Itzy" ,"있지");
            articleService.write(admin, "Itzy" ,"예지");
            articleService.write(member1, "Itzy" ,"유나");
            articleService.write(member1, "Itzy" ,"채령");
            articleService.write(member2, "Itzy" ,"리아");
            articleService.write(member2, "Itzy" ,"류진");

        };
    }
}
