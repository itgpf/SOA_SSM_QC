package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.service.goods.SkuSearchService;
import com.qingcheng.util.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@Controller
public class SearchController {

    @Reference
    private SkuSearchService skuSearchService;

    @GetMapping("/search")
    public String search(Model model,@RequestParam Map<String, String> searchMap ) throws Exception {
        //字符集处理(解决中文乱码问题)
        //searchMap=WebUtil.convertCharsetToUTF8(searchMap);
        //没有页码的传递 设置默认为1
        if (searchMap.get("pageNo")==null){
            searchMap.put("pageNo","1");
        }
        //页面传递给后端两个参数 sort排序字段 sortOrder排序规则（升序/降序）
        if (searchMap.get("sort")==null){
            searchMap.put("sort","");
        }
        if (searchMap.get("sortOrder")==null){
            searchMap.put("sortOrder","DESC");
        }


        Map result = skuSearchService.search(searchMap);
        model.addAttribute("result",result);

        //url处理
        StringBuffer url = new StringBuffer("/search.do?");
        for (String key:searchMap.keySet()){
            url.append("&"+key+"="+searchMap.get(key));
        }
        model.addAttribute("url",url);
        model.addAttribute("searchMap",searchMap);
        //页码
        int pageNo=Integer.parseInt(searchMap.get("pageNo"));
        model.addAttribute("pageNo",pageNo);

        Long totalPages = (Long) result.get("totalPages");//总页数

        //开始页码
        int startPage=1;
        //截止页码
        int endPage=totalPages.intValue();
        if (totalPages>5){
            startPage=pageNo-2;
            if (startPage<1){
                startPage=1;
            }
            endPage=startPage+4;
        }

        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "search";
    }

}
