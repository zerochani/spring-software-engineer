# 📘 Software Engineer CRUD API


![GitHub repo visits](https://visitor-badge.laobi.icu/badge?page_id=zerochani.spring-software-engineer)

---

## 📝 프로젝트 정보

* **프로젝트 제목**: Software Engineer CRUD API
* **배포 주소**: *(배포 후 기입 예정)*
* **개인 소개**: 백엔드 개발자로 성장 중인 chani의 개인 실습 프로젝트입니다.
* **프로젝트 소개**:

    * RESTful 방식으로 구현한 소프트웨어 엔지니어 정보 관리 API입니다.
    * 등록/조회/수정/삭제 기능을 포함하며 Swagger 문서화, 글로벌 예외 처리, 단위 및 통합 테스트까지 모두 반영한 실전 백엔드 구조를 구현하였습니다.

---

## 🚀 시작 가이드

### ✅ 요구사항

* Java 21 이상
* Maven 3.x
* PostgreSQL (혹은 로컬 테스트용 H2)

### ✅ 설치 및 실행

```bash
# 1. 프로젝트 클론
$ git clone https://github.com/zerochani/spring-software-engineer.git

# 2. 실행
$ cd spring-software-engineer
$ ./mvnw spring-boot:run
```

---

### ✅ 기술 스택

* Java 21
* Spring Boot 3.4.5
* Spring Data JPA + PostgreSQL
* Swagger (springdoc-openapi-starter-webmvc-ui 2.2.0)
* Hibernate Validator (JSR-380)
* Mockito, JUnit5, AssertJ, MockMvc
* Jackson (Swagger 대응 모듈: `jackson-module-parameter-names` 등)

---

### ✅ 주요 화면 구성 및 API 주소

#### 📍 Swagger 문서:

* [`/swagger-ui.html`](http://localhost:8080/swagger-ui.html)

#### 📍 엔드포인트 구성

| Method  | URI                                          | 설명         |
| ------- | -------------------------------------------- | ---------- |
| GET     | `/api/v1/software-engineers`                 | 전체 엔지니어 조회 |
| GET     | `/api/v1/software-engineers/{id}`            | 단건 조회      |
| POST    | `/api/v1/software-engineers`                 | 등록         |
| PUT     | `/api/v1/software-engineers/{id}`            | 전체 수정      |
| PATCH   | `/api/v1/software-engineers/{id}/tech-stack` | 기술 스택만 수정  |
| DELETE  | `/api/v1/software-engineers/{id}`            | 삭제         |
| HEAD    | `/api/v1/software-engineers/{id}`            | 존재 여부 확인   |
| OPTIONS | `/`, `/{id}`                                 | 지원 메서드 조회  |

---

### ✅ 구현 기능 요약

#### 🔸 REST API 설계

* 계층 분리된 구조 (Controller → Service → Repository)
* DTO 기반 요청/응답 모델링 (`SoftwareEngineerRequest`, `SoftwareEngineerResponse`, `TechStackUpdateRequest`)

#### 🔸 문서화

* springdoc-openapi 기반 자동 문서 생성
* @Tag, @Operation, @Schema, @RequestBody, @ApiResponse 등 사용

#### 🔸 예외 처리

* `@ControllerAdvice` 기반 전역 예외 처리기 구성
* 커스텀 예외 `SoftwareEngineerNotFoundException`
* 공통 에러 응답 포맷 `ApiErrorResponse`

#### 🔸 Swagger 충돌 해결

* Swagger + ControllerAdvice 충돌 해결 (jackson 관련 모듈 의존성 추가)
* `springdoc-openapi` 호환성 문제 해결 완료 (버전 고정 및 디펜던시 정리)

#### 🔸 데이터베이스 연동

* Spring Data JPA 사용하여 CRUD 자동화
* PostgreSQL 사용 (H2 대체 가능)

---

### ✅ 테스트 커버리지

#### 📌 Controller 테스트 (통합 테스트)

* `@WebMvcTest` 기반 MockMvc 테스트 수행
* MockBean으로 Service 계층 mocking
* GET/POST/PUT/PATCH/DELETE/예외 케이스 전부 테스트

#### 📌 Service 테스트 (단위 테스트)

* Mockito 기반 `@Mock`, `@InjectMocks` 구성
* 실제 Repository 없이 Service 비즈니스 로직 검증
* 예외 상황도 검증

---

### ✅ 아키텍처

```plaintext
Presentation Layer
├── SoftwareEngineerController.java

Application Layer
├── SoftwareEngineerService.java

Domain Layer
├── SoftwareEngineer.java (Entity)
├── DTOs (Request / Response / Patch)

Infrastructure Layer
├── SoftwareEngineerRepository.java
```

* 계층간 역할 분리 명확
* 엔티티와 DTO는 완전히 분리된 책임

---

## 📈 향후 확장 계획

| 항목           | 설명                                                |
| ------------ | ------------------------------------------------- |
| 🔒 인증 및 인가   | Spring Security + JWT 기반 인증 체계 도입                 |
| 🔄 테스트 자동화   | Postman, Spring REST Docs, RestAssured 기반 테스트 자동화 |
| 🚀 CI/CD     | GitHub Actions 기반 자동 배포 파이프라인 구축 예정               |
| 🐳 Docker 배포 | Dockerfile 작성 및 Docker Hub 배포 예정                  |
| 📃 README 확장 | 배포 주소 및 환경 설정 포함한 운영 버전 문서화 예정                    |

---

## 📫 Contact

* **작성자**: chani ([youngchan0510@gmail.com](mailto:youngchan0510@gmail.com))
* **GitHub**: [zerochani/spring-software-engineer](https://github.com/zerochani/spring-software-engineer)
