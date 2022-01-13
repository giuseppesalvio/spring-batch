package com.fincons.classic.config;

import com.fincons.classic.domain.CreditCard;
import com.fincons.classic.domain.CreditCardRisk;
import com.fincons.classic.job.CreditCardItemProcessor;
import com.fincons.classic.job.CreditCardItemReader;
import com.fincons.classic.job.CreditCardItemWriter;
import com.fincons.classic.listener.CreditCardIItemReaderListener;
import com.fincons.classic.listener.CreditCardIItemWriterListener;
import com.fincons.classic.listener.CreditCardItemProcessListener;
import com.fincons.classic.listener.CreditCardJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobBatchCreditCardConfiguration {

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    public final CreditCardItemReader reader;
    public final CreditCardItemWriter writer;
    public final CreditCardItemProcessor processor;
    public final CreditCardJobExecutionListener jobExecutionListener;
    public final CreditCardIItemReaderListener readerListener;
    public final CreditCardItemProcessListener creditCardItemProcessListener;
    public final CreditCardIItemWriterListener writerListener;

    public JobBatchCreditCardConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, CreditCardItemReader reader, CreditCardItemWriter writer, CreditCardItemProcessor processor, CreditCardJobExecutionListener jobExecutionListener, CreditCardIItemReaderListener readerListener, CreditCardItemProcessListener creditCardItemProcessListener, CreditCardIItemWriterListener writerListener) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.reader = reader;
        this.writer = writer;
        this.processor = processor;
        this.jobExecutionListener = jobExecutionListener;
        this.readerListener = readerListener;
        this.creditCardItemProcessListener = creditCardItemProcessListener;
        this.writerListener = writerListener;
    }

    @Bean
    public Job job(Step step, CreditCardJobExecutionListener jobExecutionListener) {
        return jobBuilderFactory.get("job-1")
                .listener(jobExecutionListener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step() {

        TaskletStep step = stepBuilderFactory.get("step-1")
                .<CreditCard, CreditCardRisk>chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(creditCardItemProcessListener)
                .listener(writerListener)
                .build();
        return step;
    }

}
