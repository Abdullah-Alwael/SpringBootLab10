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

    public List<JobApplication> getJobApplications(){
        return jobApplicationRepository.findAll();
    }

    public void applyForJob(JobApplication jobApplication){
        jobApplicationRepository.save(jobApplication);
    }

    public Boolean withdrawJobApplication(Integer jobApplicationId){
        JobApplication oldJobApplication = jobApplicationRepository.getById(jobApplicationId);

        if (oldJobApplication == null){
            return false; // job application does not exist
        }

        jobApplicationRepository.delete(oldJobApplication);
        return true; // job application deleted
    }
}
