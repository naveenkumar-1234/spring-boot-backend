package com.codelab.backend.service;

import com.codelab.backend.model.Experiments;
import com.codelab.backend.model.TestCases;
import com.codelab.backend.repository.ExperimentRepository;
import com.codelab.backend.request.ExperimentRequest;
import com.codelab.backend.request.TestCaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final ExperimentRepository experimentRepository;

    public Experiments addExperiment(ExperimentRequest req){
        Experiments exp = new Experiments();
        exp.setExperimentNo(req.getExperimentNo());
        exp.setExperimentName(req.getExperimentName());
        exp.setDescription(req.getDescription());
        exp.setConstains(req.getConstains());
        exp.setSubjectCode(req.getSubjectCode());
        exp.setCompletedDate(req.getCompletedDate());
        exp.setTestCasesList(convertTestCases(req.getTestCasesList()));

        return experimentRepository.save(exp);
    }
    public List<Experiments> getAllExpriments(){
        return experimentRepository.findAll();
    }
    public Optional<Experiments> getExprementById(Long id){
        return experimentRepository.findById(id);
    }
    public List<Experiments> getExperimentsBySubjectCode(String subjectCode) {
        return experimentRepository.findBySubjectCode(subjectCode);
    }

    private List<TestCases> convertTestCases(List<TestCaseRequest> testCaseRequests) {
        if (testCaseRequests == null) return null;

        List<TestCases> list = new ArrayList<>();
        for (TestCaseRequest req : testCaseRequests) {
            TestCases tc = new TestCases();
            tc.setInput(req.getInput());
            tc.setExpectedOutput(req.getExpectedOutput());
            list.add(tc);
        }
        return list;
    }
}

