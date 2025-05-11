package com.amigoscode.dto;

public class SoftwareEngineerResponse {

    private Integer id;
    private String name;
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
