package com.example.SpringGit;

import com.example.SpringGit.controller.EntryController;
import com.example.SpringGit.service.ApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntryControllerOldWayTest {

    @InjectMocks
    private EntryController controller;

    @Mock
    private ApiService service;

    @Test
    void shouldReturnResponseEntityDirectly() {
//        when(service.simulateArithmeticException("10"))
//                .thenReturn(ResponseEntity.ok("Success"));
        when(service.simulateArithmeticException("10"))
                .thenReturn(new ResponseEntity<>("Success", HttpStatus.CREATED));

        ResponseEntity<String> result = controller.simulateArithmeticException("10");

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Success", result.getBody());
        verify(service).simulateArithmeticException("10");
    }

    @Test
    void shouldReturnResponseEntityDirectlyFailure() {
//        when(service.simulateArithmeticException("10"))
//                .thenReturn(ResponseEntity.ok("Success"));
        when(service.simulateArithmeticException("5"))
                .thenReturn(new ResponseEntity<>("Recovery: Invalid arithmetic value. ", HttpStatus.BAD_REQUEST));

        ResponseEntity<String> result = controller.simulateArithmeticException("5");

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Recovery: Invalid arithmetic value. ", result.getBody());
        verify(service).simulateArithmeticException("5");
    }
}
