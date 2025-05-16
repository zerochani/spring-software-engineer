package com.amigoscode.exception;

// 도메인에 특화된 커스텀 예외 클래스
public class SoftwareEngineerNotFoundException extends RuntimeException{
    public SoftwareEngineerNotFoundException(Integer id){
        super("소프트웨어 엔지니어 ID " + id + "를 찾을 수 없습니다.");
    }
}


