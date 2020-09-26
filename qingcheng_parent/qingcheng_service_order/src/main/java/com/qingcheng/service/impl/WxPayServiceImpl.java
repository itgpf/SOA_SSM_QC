package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.wxpay.sdk.Config;
import com.github.wxpay.sdk.WXPayRequest;
import com.github.wxpay.sdk.WXPayUtil;
import com.qingcheng.service.order.OrderService;
import com.qingcheng.service.order.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-24 22:27
 */
@Service
public class WxPayServiceImpl implements WxPayService {
   @Autowired
   private Config config;
    @Override
    public Map createNative(String orderId, Integer money, String notifyUrl) {

        try {
            //封装请求参数
            Map<String,String> map = new HashMap();
            map.put("appid",config.getAppID());
            map.put("mch_id",config.getMchID());
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            map.put("body","青橙测试");
            map.put("out_trade_no",orderId);
            map.put("total_fee",money+"");
            map.put("spbill_create_ip","127.0.0.1");
            map.put("notify_url",notifyUrl);
            map.put("trade_type","NATIVE");
            //xml格式的参数
            String xmlParam = WXPayUtil.generateSignedXml(map, config.getKey());
            //发送请求
            WXPayRequest wxPayRequest =new WXPayRequest(config);
            String xmlResult = wxPayRequest.requestWithCert("/pay/unifiedorder", null, xmlParam, false);
            System.out.println("结果"+xmlResult);
            //解析结果
            Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);
            Map m = new HashMap();
            m.put("code_url",mapResult.get("code_url"));
            m.put("total_fee",money+"");
            m.put("out_trade_no",orderId);

            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }

    }
    @Autowired
    private OrderService orderService;

    @Override
    public void notifyLogic(String xml) {
        try {
            //对xml进行解析
            Map<String,String> map = WXPayUtil.xmlToMap(xml);
            boolean signatureValid = WXPayUtil.isSignatureValid(map,config.getKey());
            System.out.println("验证名是否正确："+signatureValid);
            System.out.println(map.get("out_trade_no"));
            System.out.println(map.get("result_code"));
            if (signatureValid){
                if ("success".equals(map.get("result_code"))){
                    orderService.updatePayStatus(map.get("out_trade_no"),map.get("transaction_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}