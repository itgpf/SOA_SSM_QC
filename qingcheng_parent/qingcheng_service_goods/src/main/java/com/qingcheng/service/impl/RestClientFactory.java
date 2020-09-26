package com.qingcheng.service.impl;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-14 15:25
 */
public class RestClientFactory {
    public static RestHighLevelClient getRestHighLevelClient(String hostname,int port){
        //连接rest接口
        HttpHost httpHost = new HttpHost(hostname,port,"http");
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
        return  new RestHighLevelClient(restClientBuilder);//高级客户端对象（连接）

    }

}