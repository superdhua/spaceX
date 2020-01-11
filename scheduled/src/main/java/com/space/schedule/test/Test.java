package com.space.schedule.test;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.Locale;

@Configuration
@EnableScheduling
public class Test implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
            () ->System.out.println("执行动态定时任务 :" + System.currentTimeMillis()),
            triggerContext -> {
                String cron = "15 * * * * ?";
                return new CronTrigger(cron).nextExecutionTime(triggerContext);
            }
        );
    }
}
