package com.github.wxpay.sdk;

import java.io.InputStream;

/**
 * @program: wxpay_Demo
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-23 21:40
 */
public class Config extends WXPayConfig {
    public String getAppID() {
        return "wx8397f8696b538317";
    }

    public String getMchID() {
        return "1473426802";
    }

    public String getKey() {
        return "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb";
    }

    InputStream getCertStream() {
        return null;
    }

    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api2.mch.weixin.qq.com",true);
            }
        };
    }
}