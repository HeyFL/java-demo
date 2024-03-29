package com.example.test.demo.nio.http.service.impl;

import com.example.test.demo.nio.http.domain.HttpRequestVO;
import com.example.test.demo.nio.http.domain.HttpResponseVO;
import com.example.test.demo.nio.http.domain.NioAddressVO;
import com.example.test.demo.nio.http.service.HttpService;
import com.example.test.demo.nio.http.service.SslService;
import com.example.test.demo.nio.http.utils.Assert;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author caojiancheng
 * @date 2021/4/20
 * @description
 */
public class HttpServiceImpl extends HttpNioImpl implements HttpService {


    private Pattern httpPattern = Pattern.compile("http[s]{0,1}://.*");

    public HttpServiceImpl(SslService sslService, ExecutorService threadPool) {
        super(sslService, threadPool);
    }

    @Override
    public void doGet(String uri, BiConsumer<HttpResponseVO, Exception> callBack, LinkedHashMap... headers) {
        doRequest(uri, "GET", null, callBack, headers);
    }

    @Override
    public void doRequest(String uri, String method, byte[] body, BiConsumer<HttpResponseVO, Exception> callBack, LinkedHashMap... headers) {
        try {
            Matcher matcher = httpPattern.matcher(uri);
            Assert.isTrue(matcher.matches(), "uri格式错误");
            int pIndex = uri.indexOf(":");
            String protocol = uri.substring(0, pIndex);
            uri = uri.substring(pIndex + 3);
            uri = uri.contains("/") ? uri : uri + "/";
            int pathIndex = uri.indexOf("/");
            String host = uri.substring(0, pathIndex);
            int portIndex = host.indexOf(":");
            int port = HTTP.equalsIgnoreCase(protocol) ? 80 : 443;
            if (portIndex > -1) {
                port = Integer.parseInt(host.substring(portIndex + 1));
                host = host.substring(0, portIndex);
            }
            HttpRequestVO httpRequestVO = new HttpRequestVO();
            httpRequestVO.setBody(body);
            httpRequestVO.setPort(port);
            httpRequestVO.setHost(host);
            httpRequestVO.setMethod(method);
            httpRequestVO.setProtocol(protocol);
            httpRequestVO.setCallBack(callBack);
            httpRequestVO.setPath(uri.substring(pathIndex));
            Optional.ofNullable(headers).filter(h -> h.length > 0).map(h -> h[0]).ifPresent(httpRequestVO::setHeaders);

            NioAddressVO address = new NioAddressVO();
            address.setPort(port);
            address.setHost(host);
            address.setAtt(httpRequestVO);
            socketChannelPool.submit(address);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
