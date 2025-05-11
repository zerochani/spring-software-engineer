package com.amigoscode.dto;

import jakarta.validation.constraints.NotBlank;

public class SoftwareEngineerRequest {

    @NotBlank(message="이름은 필수")
    private String name;

    @NotBlank(message = "기술 스택은 필수")
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
