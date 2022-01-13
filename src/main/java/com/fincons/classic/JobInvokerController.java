package com.fincons.classic;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokerController {

  final JobLauncher jobLauncher;
  final Job jobBatchCreditCard;

  public JobInvokerController(JobLauncher jobLauncher, Job jobBatchCreditCard) {
    this.jobLauncher = jobLauncher;
    this.jobBatchCreditCard = jobBatchCreditCard;
  }

  @RequestMapping("/invokejob")
  public String handle() throws Exception {

    JobParameters jobParameters =
        new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
    jobLauncher.run(jobBatchCreditCard, jobParameters);

    return "Batch job has been invoked";
  }

}
