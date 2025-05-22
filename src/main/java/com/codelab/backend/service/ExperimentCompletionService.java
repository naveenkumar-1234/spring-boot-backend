package com.codelab.backend.service;

import com.codelab.backend.exceptions.ConflictException;
import com.codelab.backend.exceptions.ResourceNotFoundException;
import com.codelab.backend.model.StudentExperiment;
import com.codelab.backend.repository.ExperimentRepository;
import com.codelab.backend.repository.StudentExperimentRepository;
import com.codelab.backend.repository.StudentRepository;
import com.codelab.backend.request.CompletionRequestDTO;
import com.codelab.backend.response.CompletionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperimentCompletionService {

    private final StudentExperimentRepository completionRepository;
    private final StudentRepository studentRepository;
    private final ExperimentRepository experimentRepository;

    public CompletionResponseDTO markExperimentCompleted(CompletionRequestDTO request) {
        validateStudentAndExperiment(request.getStudentId(), request.getExperimentId());

        if (completionRepository.existsByStudentIdAndExperimentId(
                request.getStudentId(), request.getExperimentId())) {
            throw new ConflictException("Experiment already completed by this student");
        }

        StudentExperiment completion = new StudentExperiment(
                request.getStudentId(),
                request.getExperimentId(),
                new Date(),
                true
        );

        StudentExperiment saved = completionRepository.save(completion);
        return mapToDTO(saved);
    }

    public List<CompletionResponseDTO> getStudentCompletions(Long studentId) {
        return completionRepository.findByStudentId(studentId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<CompletionResponseDTO> getExperimentCompletions(Long experimentId) {
        return completionRepository.findByExperimentId(experimentId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private void validateStudentAndExperiment(Long studentId, Long experimentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found");
        }
        if (!experimentRepository.existsById(experimentId)) {
            throw new ResourceNotFoundException("Experiment not found");
        }
    }

    private CompletionResponseDTO mapToDTO(StudentExperiment entity) {
        return new CompletionResponseDTO(
                entity.getId(),
                entity.getStudentId(),
                entity.getExperimentId(),
                entity.getCompletionDate(),
                entity.getIsCompleted()
        );
    }
}
