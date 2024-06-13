package com.example.jwt.domain.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @PostMapping("/login")
    // @ResponseBody -> RestController 달면 굳이 안써도 상관없음
    public String login(){
        return "성공";
    }
}
