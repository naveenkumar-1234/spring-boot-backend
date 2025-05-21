package com.codelab.backend.repository;

import com.codelab.backend.model.Experiments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperimentRepository extends JpaRepository<Experiments,Long> {
    List<Experiments> findBySubjectCode(String subjectCode);
}
