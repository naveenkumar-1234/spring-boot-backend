package com.codelab.backend.controller;

import com.codelab.backend.dto.UserDTO;
import com.codelab.backend.model.Experiments;
import com.codelab.backend.request.ExperimentRequest;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;
    @PostMapping("/addexperiment")
    public ResponseEntity<ApiResponse> addExperiment(@Valid @RequestBody ExperimentRequest req){
        try {

          Experiments experiments=staffService.addExperiment(req);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Exp added Successfully", experiments));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/experiments")
    public ResponseEntity<ApiResponse> getAllExpriments(){
        try {

            List<Experiments> experiments=staffService.getAllExpriments();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("Success", experiments));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/experiment/{id}")
    public ResponseEntity<ApiResponse> getExprimentById(@PathVariable Long id){
        try {

            Optional<Experiments> experiments=staffService.getExprementById(id);
            if (experiments!=null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("Success", experiments));
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Not Found", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/experiments/{subcode}")
    public ResponseEntity<ApiResponse> getExprimentsbySubCode(@PathVariable String subcode){
        try {

            List<Experiments> experiments=staffService.getExperimentsBySubjectCode(subcode);
            if (experiments!=null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("Success", experiments));

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Not Found", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

}
