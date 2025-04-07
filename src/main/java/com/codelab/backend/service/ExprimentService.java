package com.codelab.backend.service;

import com.codelab.backend.model.ExprimentDetails;
import com.codelab.backend.response.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExprimentService {
    private static List<ExprimentDetails> exprimentDetailsList = new ArrayList<>();

    public List<ExprimentDetails> getAllExpriments(){
        return exprimentDetailsList;
    }
    public ExprimentDetails getExprimentByNo(Integer expNo){
        for(ExprimentDetails i : exprimentDetailsList){
            if (i.getExprimentNo()==expNo)return i;
        }
        return null;
    }
    public ResponseMessage addExpriment(ExprimentDetails exprimentDetails){
        if (exprimentDetails.getExprimentNo()==null){
            return new ResponseMessage("Expriment number is required");
        } else if (exprimentDetails.getExprimentName()==null || exprimentDetails.getExprimentName().trim().length()==0) {
            return new ResponseMessage("Expriment Name is Required");
        } else if (exprimentDetails.getDescription()==null || exprimentDetails.getDescription().trim().length()==0) {
            return new ResponseMessage("Expriment Description is required");
        } else if (exprimentDetails.getTestCasesList().size()<=0) {
            return new ResponseMessage("Expriment Testcase is required");
        }
        else {
            exprimentDetailsList.add(exprimentDetails);

            return new ResponseMessage("Expriment Added Successfully");
        }
    }
}
