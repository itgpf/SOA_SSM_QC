package com.qingcheng.service.impl;

import com.qingcheng.service.goods.CategoryService;
import com.qingcheng.service.goods.SkuService;
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
    private CategoryService categoryService;
    @Autowired
    private SkuService skuService;

    public void afterPropertiesSet() throws Exception {
        System.out.println("缓存预热");
        categoryService.saveCategoryTreeToRedis();
        skuService.saveAllPriceToRedis();
    }


}