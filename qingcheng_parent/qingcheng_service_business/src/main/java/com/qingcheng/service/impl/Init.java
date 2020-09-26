package com.qingcheng.service.impl;

import com.qingcheng.service.business.AdService;
import com.qingcheng.service.goods.CategoryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-11 16:36
 */
@Component
public class Init implements InitializingBean {
    @Autowired
    private AdService adService;

    public void afterPropertiesSet() throws Exception {
        System.out.println("缓存预热");
        adService.saveAllAdToRedis();
    }


}