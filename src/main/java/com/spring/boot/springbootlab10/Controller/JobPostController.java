package com.spring.boot.springbootlab10.Controller;

import com.spring.boot.springbootlab10.Api.ApiResponse;
import com.spring.boot.springbootlab10.Model.JobPost;
import com.spring.boot.springbootlab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-post/")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/list")
    public ResponseEntity<?> getJobPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(jobPostService.getJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addJobPost(@Valid @RequestBody JobPost jobPost, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("JobPost added successfully"));
    }

    @PutMapping("/update/{jobPostId}")
    public ResponseEntity<?> updateJobPost(@PathVariable Integer jobPostId, @Valid @RequestBody JobPost jobPost, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        if (jobPostService.updateJobPost(jobPostId, jobPost)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("JobPost updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Error, jobPost does not exist"));
        }
    }

    @DeleteMapping("/delete/{jobPostId}")
    public ResponseEntity<?> deleteJobPost(@PathVariable Integer jobPostId){
        if (jobPostService.deleteJobPost(jobPostId)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("JobPost deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Error, jobPost does not exist"));
        }
    }
}
