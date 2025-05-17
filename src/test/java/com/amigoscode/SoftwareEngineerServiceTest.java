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
}
