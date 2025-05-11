package com.amigoscode;

import org.springframework.stereotype.Service;

import java.util.List;
//비즈니스 로직
//데이터 처리의 중심 역할, 컨트롤러와 리포지토리 사이에서 데이터를 조율하는 계층

@Service
public class SoftwareEngineerService {

    //의존성 주입(생성자를 통해 주입받는다.-> 안전성과 불변성을 보장하는 방식)
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    //테이블 모든 레코드를 조회.
    public List<SoftwareEngineer> getSoftwareEngineers(){
        return softwareEngineerRepository.findAll();
    }

    //새 엔티티를 저장하거나 기존 엔티티를 업데이트.
    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    //findById(id)는 Optional을 반환하므로, 값이 없을 경우 orElseThrow로 예외를 발생.
    //IllegalStateException은 클라이언트에게 적절한 에러 응답을 줄 수 있음.
    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(id +" not found"));
    }

    public void updateSoftwareEngineer(Integer id, SoftwareEngineer updatedEngineer){
        SoftwareEngineer existing = softwareEngineerRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(id + " not found"));

        existing.setName(updatedEngineer.getName());
        existing.setTechStack(updatedEngineer.getTechStack());

        softwareEngineerRepository.save(existing);
    }

    public void deleteSoftwareEngineer(Integer id){
        if(!softwareEngineerRepository.existsById(id)){
            throw new IllegalStateException(id + " not found");
        }
        softwareEngineerRepository.deleteById(id);
    }
}
