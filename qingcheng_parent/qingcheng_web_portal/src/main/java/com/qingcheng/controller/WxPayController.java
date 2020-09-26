package com.qingcheng.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.pojo.order.Order;
import com.qingcheng.service.order.OrderService;
import com.qingcheng.service.order.WxPayService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-24 22:35
 */
@RestController
@RequestMapping("/wxpay")
public class WxPayController {

    @Reference
    private OrderService orderService;
    @Reference
    private WxPayService wxPayService;

    @GetMapping("/createNative")
    public Map createNative(String orderId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Order order = orderService.findById(orderId);
        if (order!=null){
            if ("0".equals(order.getPayStatus())&&"0".equals(order.getOrderStatus())&&username.equals(order.getUsername())){
                return wxPayService.createNative(orderId,order.getPayMoney(),"http://www.baidu.com");//"http://qingcheng.e asy.echosite.cn/wxpay/notify.do"
            }else {
                return null;
            }
        }else {
            return null;
        }
    }
    //接受支付平台的回调信息
    @RequestMapping("/notify")
    public void notifyLogic(HttpServletRequest request){

        try {
            InputStream inStream = (InputStream) request.getInputStream();
            ByteArrayOutputStream outStream =new ByteArrayOutputStream();

            byte[] buffer=new byte[1024];
            int len=0;
            while ((len = inStream.read(buffer))!=-1){
                outStream.write(buffer,0,len);
            }
            outStream.close();
            inStream.close();
            String result = new String(outStream.toByteArray(),"utf-8");
            System.out.println(result);
            wxPayService.notifyLogic(result);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}