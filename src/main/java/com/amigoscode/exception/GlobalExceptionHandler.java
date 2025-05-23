package com.amigoscode.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//애플리케이션 전체 예외를 한곳에서 관리하고 응답 포맷 통일
@ControllerAdvice
public class GlobalExceptionHandler {

    //커스텀 예외 처리
    @ExceptionHandler(SoftwareEngineerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(SoftwareEngineerNotFoundException ex){
        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Swagger 요청은 예외 처리하지 않도록 분기
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex, HttpServletRequest request){
        String uri = request.getRequestURI();

        if(uri.contains("/swagger")|| uri.contains("/api-docs") || uri.contains("/v3/api-docs")){
            throw new RuntimeException(ex); //그대로 예외 던지기(Swagger가 처리하도록)
        }
        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "알 수 없는 서버 오류가 발생했습니다."
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
