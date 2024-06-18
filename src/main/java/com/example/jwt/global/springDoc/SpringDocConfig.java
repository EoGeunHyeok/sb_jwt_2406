package com.example.jwt.global.springDoc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration // 클래스의 구성 클래스이다 표기.
@OpenAPIDefinition(info = @Info(title = "REST API", version = "v1")) // OpenAPI 의 전반적인 정보를 정의
public class SpringDocConfig {
}
