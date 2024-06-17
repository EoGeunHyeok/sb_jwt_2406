package com.example.jwt.domain.member;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class MemberControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("POST /member/login 은 로그인 처리 URL 이다.")
    void t1() throws Exception {
        // When
        ResultActions resultActions = mvc
                .perform(
                        post("/api/v1/member/login")
                                .content("""
                                        {
                                            "username": "user1",
                                            "password": "1234"
                                        }
                                        """.stripIndent())
                                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                )
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().is2xxSuccessful()) // 성공했는지 확인
                .andExpect(jsonPath("$.resultCode").value("S-1"))// 벨류가 있는지 확인
                .andExpect(jsonPath("$.msg").exists()) // 필드가 있는지 확인
                .andExpect(jsonPath("$.data.accessToken").exists()); // 필드가 있는지 확인
    }

    @Test
    @WithUserDetails("user1")
    @DisplayName("GET /member/me => 내 정보를 확인하는 URL 이다.")
    void t2() throws Exception {
        // When
        ResultActions resultActions = mvc
                .perform(
                        get("/api/v1/member/me")
                )
                .andDo(print());
        // Then
        resultActions
                .andExpect(status().is2xxSuccessful()) // 성공했는지 확인
                .andExpect(jsonPath("$.resultCode").value("S-2"))// 벨류가 있는지 확인
                .andExpect(jsonPath("$.msg").exists()) // 필드가 있는지 확인
                .andExpect(jsonPath("$.data.member.id").exists()) // 필드가 있는지 확인
                .andExpect(jsonPath("$.data.member.username").exists())
                .andExpect(jsonPath("$.data.member.username").value("user1"));

    }
}