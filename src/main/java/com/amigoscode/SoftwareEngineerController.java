package com.amigoscode;

import com.amigoscode.dto.SoftwareEngineerRequest;
import com.amigoscode.dto.SoftwareEngineerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.Map;

@Tag(name="Software Engineer API", description = "소프트웨어 엔지니어 CRUD API")
@RestController //반환 값이 json 형태로 직렬화되어 클라이언트에 전달됨.
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    //의존성주입 방식으로 service를 주입받는다. 컨트롤러는 단순히 요청-응답을 중계
    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @Operation(summary = "전체 엔지니어 조회", description = "DB에 등록된 모든 엔지니어를 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "조회 성공"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    @GetMapping //데이터베이스에 저장된 모든 객체들을 반환.
    public List<SoftwareEngineerResponse> getEngineers() {
        return softwareEngineerService.getSoftwareEngineers();
    }

    @Operation(summary = "엔지니어 단건 조회", description = "ID를 기준으로 엔지니어 정보를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "조회 성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 ID"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    @GetMapping("{id}")
    public SoftwareEngineerResponse getEngineersById(@PathVariable Integer id) {
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    @Operation(
            summary = "엔지니어 등록",
            description = "새로운 엔지니어 정보를 등록합니다.",
            requestBody = @RequestBody(
                    description = "등록할 엔지니어 정보 예시",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "등록 성공"),
                    @ApiResponse(responseCode = "400", description = "요청 형식 오류"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    @PostMapping
    public Integer addNewSoftwareEngineer(@Valid @RequestBody SoftwareEngineerRequest request) {
        return softwareEngineerService.insertSoftwareEngineer(request);
    }

    @Operation(
            summary = "엔지니어 수정",
            description = "ID를 기준으로 엔지니어 정보를 수정합니다.",
            requestBody = @RequestBody(
                    description = "수정할 엔지니어 정보 예시",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "수정 성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 ID"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    @PutMapping("{id}")
    public void updateEngineer(
            @PathVariable Integer id,
            @Valid @RequestBody SoftwareEngineerRequest request
    ){
        softwareEngineerService.updateSoftwareEngineer(id, request);
    }

    @Operation(
            summary = "엔지니어 삭제",
            description = "ID를 기준으로 엔지니어 정보를 삭제합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 ID"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    @DeleteMapping("{id}")
    public void deleteEngineer(@PathVariable Integer id){
        softwareEngineerService.deleteSoftwareEngineer(id);
    }

    @Operation(
            summary = "기술 스택 부분 수정",
            description = "엔지니어의 techStack 필드만 부분 수정합니다.",
            requestBody = @RequestBody(
                    description = "변경할 기술 스택 값 (예: { \"techStack\": \"Spring, Kotlin\" })",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "수정 성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 ID"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    @PatchMapping("{id}/tech-stack")
    public void updateTechStack(
            @PathVariable Integer id,
            @RequestBody Map<String, String> update
    ){
        String newTechStack = update.get("techStack");
        softwareEngineerService.updateTechStack(id, newTechStack);
    }

    @Operation(
            summary = "엔지니어 존재 여부 확인",
            description = "HEAD 요청으로 ID에 해당하는 엔지니어 존재 여부만 확인합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "존재함"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않음")
            }
    )
    @RequestMapping(value= "{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkExistence(@PathVariable Integer id){
        boolean exists = softwareEngineerService.existsById(id);
        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "지원 메서드 조회 (루트)",
            description = "루트 경로에서 지원하는 HTTP 메서드 목록을 반환합니다."
    )
    @RequestMapping(method=RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsRoot(){
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                .build();
    }

    @Operation(
            summary = "지원 메서드 조회 (ID 경로)",
            description = "ID 경로에서 지원하는 HTTP 메서드 목록을 반환합니다."
    )
    @RequestMapping(value= " {id}", method=RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsById(){
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS, HttpMethod.HEAD)
                .build();
    }

}
