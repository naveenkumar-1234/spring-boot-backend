package com.codelab.backend.controller;

import com.codelab.backend.exceptions.ConflictException;
import com.codelab.backend.exceptions.ResourceNotFoundException;
import com.codelab.backend.request.CompletionRequestDTO;
import com.codelab.backend.response.CompletionResponseDTO;
import com.codelab.backend.service.ExperimentCompletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/completions")
@RequiredArgsConstructor
public class ExperimentCompletionController {

    private final ExperimentCompletionService completionService;

    @PostMapping
    public ResponseEntity<?> markCompleted(@RequestBody CompletionRequestDTO request) {
        try {
            CompletionResponseDTO response = completionService.markExperimentCompleted(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CompletionResponseDTO>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(completionService.getStudentCompletions(studentId));
    }

    @GetMapping("/experiment/{experimentId}")
    public ResponseEntity<List<CompletionResponseDTO>> getByExperiment(@PathVariable Long experimentId) {
        return ResponseEntity.ok(completionService.getExperimentCompletions(experimentId));
    }
}
