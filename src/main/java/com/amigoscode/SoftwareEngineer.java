package com.amigoscode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
// software_engineer 테이블과 연결되어,
// spring data jpa를 통해 데이터베이스에 객체 형태로 데이터를 저장하고 조회할 수 있게 해주는 도메인 모델
@Entity // 이 클래스는 데이터베이스의 테이블로 매핑된다.
public class SoftwareEngineer {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 값이 데이터베이스에서 자동 생성됨
    private Integer id;
    private String name;
    private String techStack;

    public SoftwareEngineer(Integer id,
                            String name,
                            String techStack) {
        this.id = id;
        this.name = name;
        this.techStack = techStack;
    }

    public SoftwareEngineer() {
        //jpa는 내부적으로 객체를 생성할 때 기본 생성자가 반드시 필요.
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareEngineer that = (SoftwareEngineer) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(techStack, that.techStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, techStack);
    }
}
