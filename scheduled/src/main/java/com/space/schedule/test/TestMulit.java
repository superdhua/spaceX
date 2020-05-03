package com.space.schedule.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
@EnableAsync
public class TestMulit {

    @Async
    @Scheduled(cron = "* 5 * * * ?")
    public void first(){
        System.out.println("第一个线程开始了哦,每 5 秒执行一次哦" + System.currentTimeMillis());
    }
    @Async
    @Scheduled(cron = "* 6 * * * ?")
    public void second(){
        System.out.println("第一个线程开始了哦,每 6 秒执行一次哦" + System.currentTimeMillis());
    }
}
