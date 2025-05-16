package com.amigoscode;

import com.amigoscode.dto.SoftwareEngineerRequest;
import com.amigoscode.dto.SoftwareEngineerResponse;
import com.amigoscode.exception.SoftwareEngineerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
//비즈니스 로직
//데이터 처리의 중심 역할, 컨트롤러와 리포지토리 사이에서 데이터를 조율하는 계층

@Service
public class SoftwareEngineerService {

    //의존성 주입(생성자를 통해 주입받는다.-> 안전성과 불변성을 보장하는 방식)
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    //전체 목록 조회 -> 응답 DTO로 변환
    public List<SoftwareEngineerResponse> getSoftwareEngineers(){
        return softwareEngineerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    //새 엔티티를 저장하거나 기존 엔티티를 업데이트.
    public Integer insertSoftwareEngineer(SoftwareEngineerRequest request) {
        SoftwareEngineer engineer = new SoftwareEngineer(null, request.getName(), request.getTechStack());
        return softwareEngineerRepository.save(engineer).getId();
    }

    //ID로 단건 조회(Entity->DTO 변환)
    public SoftwareEngineerResponse getSoftwareEngineerById(Integer id) {
        SoftwareEngineer engineer =  softwareEngineerRepository.findById(id)
                .orElseThrow(()-> new SoftwareEngineerNotFoundException(id));
        return toResponse(engineer);
    }

    //엔지니어 수정
    public void updateSoftwareEngineer(Integer id, SoftwareEngineerRequest request){
        SoftwareEngineer existing = softwareEngineerRepository.findById(id)
                .orElseThrow(()-> new SoftwareEngineerNotFoundException(id));

        existing.setName(request.getName());
        existing.setTechStack(request.getTechStack());

        softwareEngineerRepository.save(existing);
    }

    public void deleteSoftwareEngineer(Integer id){
        if(!softwareEngineerRepository.existsById(id)){
            throw new SoftwareEngineerNotFoundException(id);
        }
        softwareEngineerRepository.deleteById(id);
    }

    //엔티티를 응답 DTO로 변환
    private SoftwareEngineerResponse toResponse(SoftwareEngineer engineer){
        return new SoftwareEngineerResponse(
                engineer.getId(),
                engineer.getName(),
                engineer.getTechStack()
        );
    }

    //techStack만 부분 수정
    public void updateTechStack(Integer id, String techStack){
        SoftwareEngineer engineer = softwareEngineerRepository.findById(id)
                .orElseThrow(()-> new SoftwareEngineerNotFoundException(id));
        engineer.setTechStack(techStack);
        softwareEngineerRepository.save(engineer);
    }

    //head 메서드용: 존재 여부 확인
    public boolean existsById(Integer id){
        return softwareEngineerRepository.existsById(id);
    }


}
