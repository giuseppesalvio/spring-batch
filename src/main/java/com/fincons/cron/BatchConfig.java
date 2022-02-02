package com.fincons.cron;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory steps) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = steps;
    }

    @Bean
    public Step stepOne(){
        return stepBuilderFactory
                .get("stepOne")
                .tasklet(new MyTaskOne())
                .build();
    }

    @Bean
    public Step stepTwo(){
        return stepBuilderFactory.get("stepTwo")
                .tasklet(new MyTaskTwo())
                .build();
    }

    @Bean
    public Step stepThree(){
        return stepBuilderFactory.get("stepTwo")
                .tasklet(new MyTaskThree())
                .build();
    }

    @Bean
    public Job demoJob(){
        return jobBuilderFactory
                .get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .next(stepTwo())
                .next(stepThree())
                .build();
    }
}
