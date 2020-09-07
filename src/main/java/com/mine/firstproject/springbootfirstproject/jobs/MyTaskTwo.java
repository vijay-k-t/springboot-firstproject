package com.mine.firstproject.springbootfirstproject.jobs;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
 
public class MyTaskTwo implements Tasklet {
 
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception 
    {
        System.out.println("MyTaskTwo start..");
 
        // ... your code
        Thread.sleep(10000);
         
        System.out.println("MyTaskTwo done..");
        return RepeatStatus.FINISHED;
    }    
}
