package com.amigoscode;

import com.amigoscode.dto.SoftwareEngineerRequest;
import com.amigoscode.dto.SoftwareEngineerResponse;
import com.amigoscode.exception.SoftwareEngineerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SoftwareEngineerServiceTest {
    @Mock
    private SoftwareEngineerRepository repository;

    @InjectMocks
    private SoftwareEngineerService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldReturnEngineerById(){
        //given
        SoftwareEngineer engineer = new SoftwareEngineer(1, "chani", "java");
        when(repository.findById(1)).thenReturn(Optional.of(engineer));

        //when
        SoftwareEngineerResponse result = service.getSoftwareEngineerById(1);

        //then
        assertThat(result.getName()).isEqualTo("chani");
        assertThat(result.getTechStack()).isEqualTo("java");
    }

    @Test
    void shouldThrowWhenEngineerNotFound(){
        //given
        when(repository.findById(999)).thenReturn(Optional.empty());

        //when & then
        assertThatThrownBy(()->service.getSoftwareEngineerById(999))
                .isInstanceOf(SoftwareEngineerNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    void shouldInsertEngineer(){
        //given
        SoftwareEngineerRequest request = new SoftwareEngineerRequest("Alex", "Spring");
        SoftwareEngineer saved = new SoftwareEngineer(1, "Alex", "Spring");
        when(repository.save(any())).thenReturn(saved);

        //when
        Integer id = service.insertSoftwareEngineer(request);

        //then
        assertThat(id).isEqualTo(1);
    }

    @Test
    void shouldDeleteExistingEngineer(){
        when(repository.existsById(1)).thenReturn(true);
        service.deleteSoftwareEngineer(1);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void shouldFailToDeleteIfNotExists(){
        when(repository.existsById(2)).thenReturn(false);
        assertThatThrownBy(()-> service.deleteSoftwareEngineer(2))
                .isInstanceOf(SoftwareEngineerNotFoundException.class);
    }

    @Test
    //수정 요청이 정상적으로 기존 데이터를 업데이트하는지 확인
    void shouldUpdateEngineerSuccessfully(){
        //given
        SoftwareEngineer existing = new SoftwareEngineer(1,"Old", "C++");
        SoftwareEngineerRequest request = new SoftwareEngineerRequest("New", "Rust");

        when(repository.findById(1)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(existing);

        //when
        service.updateSoftwareEngineer(1, request);

        //then
        assertThat(existing.getName()).isEqualTo("New");
        assertThat(existing.getTechStack()).isEqualTo("Rust");
    }

    @Test
    //전체 객체가 아니라 부분 필드만 수정하는 API 흐름이 잘 작동하는지 검증
    void shouldUpdateTechStackOnly(){
        //given
        SoftwareEngineer existing = new SoftwareEngineer(1,"Dana", "Java");
        when(repository.findById(1)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(existing);

        //when
        service.updateTechStack(1,"Go");

        //then
        assertThat(existing.getTechStack()).isEqualTo("Go");
        assertThat(existing.getName()).isEqualTo("Dana");
    }

    @Test
    void shouldReturnTrueIfEngineerExists(){
        when(repository.existsById(1)).thenReturn(true);
        assertThat(service.existsById(1)).isTrue();
    }

    @Test
    void shouldReturnFalseIfEngineerNotExists(){
        when(repository.existsById(999)).thenReturn(false);
        assertThat(service.existsById(999)).isFalse();
    }

}
