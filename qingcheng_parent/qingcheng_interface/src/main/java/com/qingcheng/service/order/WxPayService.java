package com.qingcheng.service.order;

import java.util.Map;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-24 22:24
 */
public interface WxPayService {

    public Map createNative(String orderId,Integer money,String notifyUrl);
    /*
    * 微信支付回调
    * */
    public void notifyLogic(String xml);
}