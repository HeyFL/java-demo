package com.example.test.demo.nio.http.service;


import com.example.test.demo.nio.http.domain.HttpResponseVO;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author caojiancheng
 * @date 2021/4/20
 * @description
 */
public interface HttpService {

    /**
     * get 请求
     *
     * @param uri      地址
     * @param callBack 回调
     * @param headers  请求头
     */
    void doGet(String uri, BiConsumer<HttpResponseVO, Exception> callBack, LinkedHashMap... headers);

    /**
     * http 请求
     *
     * @param uri      地址
     * @param method   请求方法
     * @param body     报文体
     * @param callBack 回调
     * @param headers  请求头
     */
    void doRequest(String uri, String method, byte[] body, BiConsumer<HttpResponseVO, Exception> callBack, LinkedHashMap... headers);
}
