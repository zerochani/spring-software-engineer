package com.amigoscode.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "기술 스택 부분 수정 요청 DTO")
public class TechStackUpdateRequest {

    @Schema(description="기술 스택", example="Spring Boot, Kotlin")
    private String techStack;

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }
}
