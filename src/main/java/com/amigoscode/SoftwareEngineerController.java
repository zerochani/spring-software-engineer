package com.amigoscode;

import com.amigoscode.dto.SoftwareEngineerRequest;
import com.amigoscode.dto.SoftwareEngineerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// http 요청을 받아 서비스 로직을 호출하고, 클라이언트에게 응답을 반환
@RestController //반환 값이 json 형태로 직렬화되어 클라이언트에 전달됨.
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    //의존성주입 방식으로 service를 주입받는다. 컨트롤러는 단순히 요청-응답을 중계
    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    //전체 조회(DTO 응답)
    @GetMapping //데이터베이스에 저장된 모든 객체들을 반환.
    public List<SoftwareEngineerResponse> getEngineers() {
        return softwareEngineerService.getSoftwareEngineers();
    }

    //특정 id의 소프트웨어 엔지니어 조회(DTO 응답)
    //@PathVariable: url 경로 변수에서 id 값을 추출하여 메서드 파라미터로 전달.
    @GetMapping("{id}")
    public SoftwareEngineerResponse getEngineersById(@PathVariable Integer id) {
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    //새 소프트웨어 엔지니어 추가(DTO 요청 + 등록된 ID 반환)
    @PostMapping
    public Integer addNewSoftwareEngineer(@Valid @RequestBody SoftwareEngineerRequest request) {
        return softwareEngineerService.insertSoftwareEngineer(request);
    }

    //수정(DTO 요청)
    @PutMapping("{id}")
    public void updateEngineer(
            @PathVariable Integer id,
            @Valid @RequestBody SoftwareEngineerRequest request
    ){
        softwareEngineerService.updateSoftwareEngineer(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteEngineer(@PathVariable Integer id){
        softwareEngineerService.deleteSoftwareEngineer(id);
    }

    //techStack만 부분 수정
    @PatchMapping("{id}/tech-stack")
    public void updateTechStack(
            @PathVariable Integer id,
            @RequestBody Map<String, String> update
    ){
        String newTechStack = update.get("techStack");
        softwareEngineerService.updateTechStack(id, newTechStack);
    }

    // 리소스 존재 여부만 반환
    @RequestMapping(value= "{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkExistence(@PathVariable Integer id){
        boolean exists = softwareEngineerService.existsById(id);
        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // options: 루트 경로에 허용 메서드 안내
    @RequestMapping(method=RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsRoot(){
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                .build();
    }

    // options: id 경로에 허용 메서드 안내
    @RequestMapping(value= " {id}", method=RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsById(){
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS, HttpMethod.HEAD)
                .build();
    }

}
