package com.example.test.demo.nio.http;

import com.example.test.demo.nio.http.service.HttpService;
import com.example.test.demo.nio.http.service.SslService;
import com.example.test.demo.nio.http.service.impl.HttpServiceImpl;
import com.example.test.demo.nio.http.service.impl.SslServiceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NioHttpTest {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(50,
                50,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        SslService sslService = new SslServiceImpl();
        HttpService httpService = new HttpServiceImpl(sslService, threadPool);

        List<String> list = new ArrayList() {{
            add("http://ot-cnsz22-oss-002-tempurl.sit.sf-express.com/v1/AUTH_oss-SHIVA-SISP-WQS/sisp-export/fvpPullData%20-%20%E5%89%AF%E6%9C%AC%20%282%29.7z?temp_url_sig=798f03e606edbdfaa31f9ec89a6e753e67ee635d&temp_url_expires=1636617032");


        }};
        list.addAll(list);
        LocalDateTime start = LocalDateTime.now();
        AtomicInteger a = new AtomicInteger(0);

        list.stream().forEach(i -> {
            httpService.doGet(i, (httpResponseVO, e) -> {
                if (e != null) {
                    e.printStackTrace();
                } else {
                /*System.out.println(new String(httpResponseVO.getOriginHeader()));
                System.out.println(new String(httpResponseVO.getBody()));*/
                    System.out.println(a.incrementAndGet() + "用时：" + Duration.between(start, LocalDateTime.now()).toMillis());
                }
            }, new LinkedHashMap() {{
                put("Accept-Encoding", "gzip, deflate");
                //put("cookie", "_uuid=ED61076A-4384-3871-77CF-1D5DAC79E25F20193infoc; buvid3=A1F3EA2A-EEF0-4B9B-AD9C-5B92AEEAC7BE18553infoc; CURRENT_FNVAL=80; blackside_state=1; rpdid=|(u~|~~km~)R0J'uYuR~RlJll; buvid_fp=A1F3EA2A-EEF0-4B9B-AD9C-5B92AEEAC7BE18553infoc; buvid_fp_plain=328CBAF9-0B0F-4ED8-8F1B-AE9C1CA827F718564infoc; fingerprint=7a8d81b2349aee547658234afb8661fa; PVID=1; bsource=search_baidu; sid=b58ekf8m; CURRENT_QUALITY=0");
            }});
        });
    }
}
