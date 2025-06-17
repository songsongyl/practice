package org.example.practice.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrehotLoadUsernameCpnfig {

    //任务详情对象
    @Bean
    public JobDetail jobDetail() {
      return   JobBuilder.newJob(PrehotLoadUsernameJob.class)
                .storeDurably(true)
                .build();
    }

    //任务何时触发
    @Bean
    public Trigger trigger() {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withSchedule(CronScheduleBuilder.cronSchedule("0 06 16 * * ? *"))
                .startNow()
                .build();
    }
}
