package com.amigoscode;

import org.springframework.data.jpa.repository.JpaRepository;
//spring data jpa가 해주는 일
//jparepository를 상속한 이 인터페이스를 스프링이 자동으로 감지해서,
// 내부적으로 프록시 객체를 생성하고, crud 기능을 자동으로 제공한다.


public interface SoftwareEngineerRepository
        extends JpaRepository<SoftwareEngineer, Integer> {
}
