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

    public List<JobPost> getJobPosts(){
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost){
        jobPost.setPostingDate(LocalDate.now()); // set the time of posting
        jobPostRepository.save(jobPost);
    }

    public Boolean updateJobPost(Integer jobPostId, JobPost jobPost){
        JobPost oldJobPost = jobPostRepository.getById(jobPostId);

        if (oldJobPost == null){
            return false; // jobPost does not exist
        }

        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setSalary(jobPost.getSalary());
        oldJobPost.setLocation(jobPost.getLocation());
        oldJobPost.setPostingDate(jobPost.getPostingDate());

        jobPostRepository.save(oldJobPost);
        return true; // jobPost updated
    }

    public Boolean deleteJobPost(Integer jobPostId){
        JobPost oldJobPost = jobPostRepository.getById(jobPostId);

        if (oldJobPost == null){
            return false; // jobPost does not exist
        }

        jobPostRepository.delete(oldJobPost);
        return true; // jobPost deleted
    }
}
