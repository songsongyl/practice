package org.example.practice.job;

import lombok.extern.slf4j.Slf4j;
import org.example.practice.entity.User;
import org.example.practice.service.IUserService;
import org.example.practice.util.BoolmFilterUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class PrehotLoadUsernameJob extends QuartzJobBean {

    @Autowired
    private IUserService userService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.debug("定时任务：{}",new Date());
        List<User> list = userService.findAll();
        list.forEach(user -> {
            BoolmFilterUtil.getInstance().add(user.getUsername());
            log.debug("布隆过滤器放入数据成功");
        });
    }
}
