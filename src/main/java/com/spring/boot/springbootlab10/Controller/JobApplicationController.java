package com.spring.boot.springbootlab10.Controller;

import com.spring.boot.springbootlab10.Api.ApiResponse;
import com.spring.boot.springbootlab10.Model.JobApplication;
import com.spring.boot.springbootlab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-application/")
@RequiredArgsConstructor
public class JobApplicationController {
    
    private final JobApplicationService jobApplicationService;

    @GetMapping("/list")
    public ResponseEntity<?> getJobApplications(){
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getJobApplications());
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyForJob(@Valid @RequestBody JobApplication jobApplication, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        jobApplicationService.applyForJob(jobApplication);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("JobApplication successful"));
    }

    @DeleteMapping("/withdraw/{jobApplicationId}")
    public ResponseEntity<?> withdrawJobApplication(@PathVariable Integer jobApplicationId){
        if (jobApplicationService.withdrawJobApplication(jobApplicationId)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("JobApplication withdrawn successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Error, jobApplication does not exist"));
        }
    }
}
