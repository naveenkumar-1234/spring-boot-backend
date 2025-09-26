package com.codelab.backend.service;

import com.codelab.backend.model.Experiments;
import com.codelab.backend.repository.ExperimentRepository;
import com.codelab.backend.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperimentService {

    @Autowired
    private ExperimentRepository experimentRepository;

    public List<Experiments> getAllExperiments() {
        return experimentRepository.findAll();
    }

    public Experiments getExperimentByNo(Integer expNo) {
        return experimentRepository.findAll()
                .stream()
                .filter(e -> e.getExperimentNo().equals(expNo))
                .findFirst()
                .orElse(null);
    }

    public ResponseMessage addExperiment(Experiments experimentDetails) {
        if (experimentDetails.getExperimentNo() == null) {
            return new ResponseMessage("Experiment number is required");
        } else if (experimentDetails.getExperimentName() == null || experimentDetails.getExperimentName().trim().isEmpty()) {
            return new ResponseMessage("Experiment name is required");
        } else if (experimentDetails.getDescription() == null || experimentDetails.getDescription().trim().isEmpty()) {
            return new ResponseMessage("Experiment description is required");
        } else if (experimentDetails.getTestCasesList() == null || experimentDetails.getTestCasesList().isEmpty()) {
            return new ResponseMessage("Experiment test cases are required");
        } else if (experimentDetails.getSubjectCode() == null || experimentDetails.getSubjectCode().trim().isEmpty()) {
            return new ResponseMessage("Subject code is required");
        }

        experimentRepository.save(experimentDetails);
        return new ResponseMessage("Experiment added successfully");
    }
}
