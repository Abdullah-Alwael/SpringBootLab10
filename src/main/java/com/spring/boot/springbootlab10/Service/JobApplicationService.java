package com.spring.boot.springbootlab10.Service;

import com.spring.boot.springbootlab10.Model.JobApplication;
import com.spring.boot.springbootlab10.Repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;

    private final UserService userService;
    private final JobPostService jobPostService;

    public List<JobApplication> getJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public Integer applyForJob(JobApplication jobApplication) {
        if (!userService.userExists(jobApplication.getUserId())){
            return -100;
        }
        if (!jobPostService.jobPostExists(jobApplication.getJobPostId())){
            return -200;
        }

        jobApplicationRepository.save(jobApplication);
        return 0; // applied successfully, not needed
    }

    public Boolean withdrawJobApplication(Integer jobApplicationId) {
        if (!jobApplicationRepository.existsById(jobApplicationId)) {
            return false; // job application does not exist
        }

        JobApplication oldJobApplication = jobApplicationRepository.getById(jobApplicationId);

        jobApplicationRepository.delete(oldJobApplication);
        return true; // job application deleted
    }
}
