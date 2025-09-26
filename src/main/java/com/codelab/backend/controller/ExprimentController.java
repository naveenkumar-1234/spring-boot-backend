package com.codelab.backend.controller;

import com.codelab.backend.model.Experiments;
import com.codelab.backend.response.ResponseMessage;
import com.codelab.backend.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("expriments")
public class ExprimentController {
    @Autowired
    private ExperimentService exprimentService;
    @GetMapping("/getAll")
    public ResponseEntity<List<Experiments>> getAllExpriment(){
        return ResponseEntity.ok()
                .body(exprimentService.getAllExperiments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExprimentById(@PathVariable Integer id) {
        Experiments exprimentDetails = exprimentService.getExperimentByNo(id);

        if (exprimentDetails == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("Experiment Not Found"));
        }
        return ResponseEntity.ok(exprimentDetails);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addExpriment(@RequestBody Experiments expriments){
        ResponseMessage responseMessage = exprimentService.addExperiment(expriments);
        if (responseMessage.getMessage().contains("Successfully")){
            return ResponseEntity.ok().body(responseMessage);
        }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);

    }

}
