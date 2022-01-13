package com.fincons.cron;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import static java.lang.Thread.sleep;

public class MyTaskOne implements Tasklet {

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        System.out.println("MyTaskOne start..");

        // ... your code
        sleep(20000);
        System.out.println("MyTaskOne done..");
        return RepeatStatus.FINISHED;
    }
}
