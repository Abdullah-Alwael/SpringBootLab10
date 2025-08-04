package com.spring.boot.springbootlab10.Service;

import com.spring.boot.springbootlab10.Model.JobPost;
import com.spring.boot.springbootlab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getJobPosts() {
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost) {
        jobPost.setPostingDate(LocalDate.now()); // set the time of posting
        jobPostRepository.save(jobPost);
    }

    public Boolean updateJobPost(Integer jobPostId, JobPost jobPost) {

        if (!jobPostRepository.existsById(jobPostId)) {
            return false; // jobPost does not exist
        }

        JobPost oldJobPost = jobPostRepository.getById(jobPostId);

        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setSalary(jobPost.getSalary());
        oldJobPost.setLocation(jobPost.getLocation());

        jobPostRepository.save(oldJobPost);
        return true; // jobPost updated
    }

    public Boolean deleteJobPost(Integer jobPostId) {

        if (!jobPostRepository.existsById(jobPostId)) {
            return false; // jobPost does not exist
        }

        JobPost oldJobPost = jobPostRepository.getById(jobPostId);

        jobPostRepository.delete(oldJobPost);
        return true; // jobPost deleted
    }
}
