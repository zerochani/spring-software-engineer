package com.amigoscode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description="엔지니어 등록 및 수정 요청 DTO")
public class SoftwareEngineerRequest {

    @Schema(description="엔지니어 이름", example = "chani")
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;

    @Schema(description="기술 스택", example = "Java, Spring Boot")
    @NotBlank(message = "기술 스택은 공백일 수 없습니다.")
    private String techStack;

    public SoftwareEngineerRequest() {
    }

    public SoftwareEngineerRequest(String name, String techStack) {
        this.name = name;
        this.techStack = techStack;
    }

    public String getName() {
        return name;
    }

    public String getTechStack() {
        return techStack;
    }
}
