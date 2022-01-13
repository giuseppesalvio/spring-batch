package com.fincons.classic.config;

import com.classic.domain.CreditCard;
import com.classic.domain.CreditCardRisk;
import com.classic.job.CreditCardCsvItemReader;
import com.classic.job.CreditCardItemProcessor;
import com.classic.job.CreditCardItemWriter;
import com.classic.listener.CreditCardIItemReaderListener;
import com.classic.listener.CreditCardIItemWriterListener;
import com.classic.listener.CreditCardItemProcessListener;
import com.classic.listener.CreditCardJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;

//@Configuration
//@EnableBatchProcessing
public class JobBatchCreditCardCsvConfiguration {

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    public final CreditCardCsvItemReader reader;
    public final CreditCardItemWriter writer;
    public final CreditCardItemProcessor processor;
    public final CreditCardJobExecutionListener jobExecutionListener;
    public final CreditCardIItemReaderListener readerListener;
    public final CreditCardItemProcessListener creditCardItemProcessListener;
    public final CreditCardIItemWriterListener writerListener;

    public JobBatchCreditCardCsvConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, CreditCardCsvItemReader reader, CreditCardItemWriter writer, CreditCardItemProcessor processor, CreditCardJobExecutionListener jobExecutionListener, CreditCardIItemReaderListener readerListener, CreditCardItemProcessListener creditCardItemProcessListener, CreditCardIItemWriterListener writerListener) {
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
