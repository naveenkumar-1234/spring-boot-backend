package com.codelab.backend.service;

import com.codelab.backend.model.Experiments;
import com.codelab.backend.repository.ExperimentRepository;
import com.codelab.backend.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExprimentService {

    @Autowired
    private ExperimentRepository experimentRepository;

    public List<Experiments> getAllExpriments() {
        return experimentRepository.findAll();
    }

    public Experiments getExprimentByNo(Integer expNo) {
        return experimentRepository.findAll()
                .stream()
                .filter(e -> e.getExperimentNo().equals(expNo))
                .findFirst()
                .orElse(null);
    }

    public ResponseMessage addExpriment(Experiments exprimentDetails) {
        if (exprimentDetails.getExperimentNo() == null) {
            return new ResponseMessage("Experiment number is required");
        } else if (exprimentDetails.getExperimentName() == null || exprimentDetails.getExperimentName().trim().isEmpty()) {
            return new ResponseMessage("Experiment name is required");
        } else if (exprimentDetails.getDescription() == null || exprimentDetails.getDescription().trim().isEmpty()) {
            return new ResponseMessage("Experiment description is required");
        } else if (exprimentDetails.getTestCasesList() == null || exprimentDetails.getTestCasesList().isEmpty()) {
            return new ResponseMessage("Experiment test cases are required");
        }

        experimentRepository.save(exprimentDetails);
        return new ResponseMessage("Experiment added successfully");
    }
}
