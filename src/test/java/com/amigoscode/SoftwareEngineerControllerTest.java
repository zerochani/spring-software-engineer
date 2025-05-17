package com.amigoscode;

import com.amigoscode.dto.SoftwareEngineerRequest;
import com.amigoscode.dto.SoftwareEngineerResponse;
import com.amigoscode.exception.SoftwareEngineerNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(SoftwareEngineerController.class) //컨트롤러 단위테스트
public class SoftwareEngineerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean //controller에 주입되는 service를 가짜로 만듦.
    private SoftwareEngineerService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllEngineers() throws Exception{
        List<SoftwareEngineerResponse> mockList = List.of(
                new SoftwareEngineerResponse(1, "chani", "Java"),
                new SoftwareEngineerResponse(2, "alex", "Spring")
        );

        when(service.getSoftwareEngineers()).thenReturn(mockList);

        mockMvc.perform(get("/api/v1/software-engineers"))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("chani"));
    }

    @Test
    void shouldReturnEngineerById() throws Exception{
        SoftwareEngineerResponse response = new SoftwareEngineerResponse(1,"chani","Java");

        when(service.getSoftwareEngineerById(1)).thenReturn(response);

        mockMvc.perform(get("/api/v1/software-engineers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("chani"));
    }

    @Test
    void shouldReturn404WhenEngineerNotFound() throws Exception{
        when(service.getSoftwareEngineerById(999)).thenThrow(new SoftwareEngineerNotFoundException(999));

        mockMvc.perform(get("/api/v1/software-engineers/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("소프트웨어 엔지니어 ID 999를 찾을 수 없습니다."));
    }

    @Test
    void shouldAddNewEngineer() throws Exception{
        SoftwareEngineerRequest request = new SoftwareEngineerRequest("chan", "Go");
        when(service.insertSoftwareEngineer(any())).thenReturn(10);

        mockMvc.perform(post("/api/v1/software-engineers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateEngineer() throws Exception{
        SoftwareEngineerRequest request = new SoftwareEngineerRequest("lee", "Kotlin");

        mockMvc.perform(put("/api/v1/software-engineers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(service).updateSoftwareEngineer(eq(1),any());
    }

    @Test
    void shouldDeleteEngineer() throws Exception{
        mockMvc.perform(delete("/api/v1/software-engineers/1"))
                .andExpect(status().isOk());

        verify(service).deleteSoftwareEngineer(1);
    }
}
