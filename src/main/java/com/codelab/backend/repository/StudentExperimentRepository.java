package com.codelab.backend.repository;

import com.codelab.backend.model.StudentExperiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentExperimentRepository extends JpaRepository<StudentExperiment, Long> {
    boolean existsByStudentIdAndExperimentId(Long studentId, Long experimentId);
    List<StudentExperiment> findByStudentId(Long studentId);
    List<StudentExperiment> findByExperimentId(Long experimentId);
    Optional<StudentExperiment> findByStudentIdAndExperimentId(Long studentId, Long experimentId);
}
