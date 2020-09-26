package com.qingcheng.controller.order;


import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.service.order.CategoryReportService;
import com.qingcheng.service.order.OrderService;
import com.qingcheng.service.order.StatisticsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-04 17:05
 */
@Component
public class OrderTask {
    @Reference
    private OrderService orderService;
    @Reference
    private CategoryReportService categoryReportService;
    @Reference
    private StatisticsService statisticsService;
    @Scheduled(cron = "0 0/2 * * * ?")
    public void orderTimeOutLogic(){
        System.out.println("每两分钟间隔执行一次任务"+new Date());
        orderService.orderTimeOutLogic();
    }
    @Scheduled(cron = "* * 1 * * ?")
    public void createCategoryReportDate(){
        System.out.println("createCategoryReportData");
        categoryReportService.createData();
    }
    @Scheduled(cron = "* * * 1 * *")
    public void dayMeasurement(){
        System.out.println("查询当天用户量");
        statisticsService.insertInformation();
    }
}