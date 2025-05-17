package com.amigoscode.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "엔지니어 응답 DTO")
public class SoftwareEngineerResponse {

    @Schema(description="엔지니어 ID", example = "1")
    private Integer id;
    @Schema(description= "이름", example="chani")
    private String name;
    @Schema(description="기술 스택", example= "Java, Spring Boot")
    private String techStack;

    public SoftwareEngineerResponse(Integer id, String name, String techStack) {
        this.id = id;
        this.name = name;
        this.techStack = techStack;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTechStack() {
        return techStack;
    }
}
