package com.mine.firstproject.springbootfirstproject.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    
    @Autowired
    JobLauncher jobLauncher;
     
    @Autowired
    @Qualifier("demoJob")
    Job job;

    public void run(String... args)  {
        try {
        JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
        jobLauncher.run(job, params);
        }catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

}
