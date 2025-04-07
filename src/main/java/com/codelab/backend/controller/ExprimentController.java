package com.codelab.backend.controller;

import com.codelab.backend.model.ExprimentDetails;
import com.codelab.backend.response.ResponseMessage;
import com.codelab.backend.service.ExprimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("expriments")
public class ExprimentController {
    @Autowired
    private ExprimentService exprimentService;
    @GetMapping("/getAll")
    public ResponseEntity<List<ExprimentDetails>> getAllExpriment(){
        return ResponseEntity.ok()

                .body(exprimentService.getAllExpriments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExprimentById(@PathVariable Integer id) {
        ExprimentDetails exprimentDetails = exprimentService.getExprimentByNo(id);
        if (exprimentDetails == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("Experiment Not Found"));
        }
        return ResponseEntity.ok(exprimentDetails);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addExpriment(@RequestBody ExprimentDetails exprimentDetails){
        ResponseMessage responseMessage = exprimentService.addExpriment(exprimentDetails);
        if (responseMessage.getMessage().contains("Successfully")){
            return ResponseEntity.ok().body(responseMessage);
        }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);

    }

}
