package com.codelab.backend.controller;

import com.codelab.backend.exceptions.ConflictException;
import com.codelab.backend.exceptions.ResourceNotFoundException;
import com.codelab.backend.request.CompletionRequestDTO;
import com.codelab.backend.response.ApiResponse;
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
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Success", response));
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse> getByStudent(@PathVariable Long studentId) {
        List<CompletionResponseDTO> completionResponseDTOS = completionService.getStudentCompletions(studentId);
        if (completionResponseDTOS.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Data Not Found", null));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", completionResponseDTOS));
    }

    @GetMapping("/experiment/{experimentId}")
    public ResponseEntity<ApiResponse> getByExperiment(@PathVariable Long experimentId) {
        List<CompletionResponseDTO> completionResponseDTOS = completionService.getExperimentCompletions(experimentId);
        if (completionResponseDTOS.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Data Not Found", null));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", completionResponseDTOS));
    }
}
