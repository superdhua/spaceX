package com.alone.task.Schedul;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("task")
public class Schedul {

    @RequestMapping("/Test")
    @Scheduled(cron = "0/10 * * * * ?")
    public void testSchedul(){
        int z = 0;
        for (int i = 0; i <100 ; i++) {
            z++;
        }
        System.out.println(z);
    }
}
