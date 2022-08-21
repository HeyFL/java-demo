package com.example.test.demo.nio.http;

import com.example.test.demo.nio.http.utils.Assert;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTest {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        //test2Simple();
        //System.out.println(System.currentTimeMillis() - start);
        //start = System.currentTimeMillis();
        //test1ZeroCopy();
        //System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        try {
            test1Socket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void test2Simple() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream("d:\\source.txt");
            fos = new FileOutputStream("d:\\copy.txt");

            byte[] bb = new byte[1024];
            while (-1 != fis.read(bb)) {
                fos.write(bb);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void test1ZeroCopy() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream("d:\\source.txt");
            fos = new FileOutputStream("d:\\ZeroCopy.txt");

            FileChannel fci = fis.getChannel();
            FileChannel fco = fos.getChannel();

            fci.transferTo(0,Integer.MAX_VALUE,fco);
            //while (-1 != fci.read(bb)) {
            //    bb.flip();
            //    fco.write(bb);
            //    bb.clear();
            //}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    final static String BOUNDARY = "---------------------------123821742118716";
    private static void test1Socket() throws IOException {
        String method = "GET";

        String uri = "http://ot-cnsz22-oss-002-tempurl.sit.sf-express.com/v1/AUTH_oss-SHIVA-SISP-WQS/sisp-export/fvpPullData%20-%20%E5%89%AF%E6%9C%AC%20%282%29.7z?temp_url_sig=daac2db414d1c12493357dbc493866acf35f4f66&temp_url_expires=1636708936";
        Pattern httpPattern = Pattern.compile("http[s]{0,1}://.*");

        Matcher matcher = httpPattern.matcher(uri);
        Assert.isTrue(matcher.matches(), "uri格式错误");
        int pIndex = uri.indexOf(":");
        String protocol = uri.substring(0, pIndex);
        uri = uri.substring(pIndex + 3);
        uri = uri.contains("/") ? uri : uri + "/";
        int pathIndex = uri.indexOf("/");
        String host = uri.substring(0, pathIndex);
        int portIndex = host.indexOf(":");
        int port = "HTTP".equalsIgnoreCase(protocol) ? 80 : 443;
        if (portIndex > -1) {
            port = Integer.parseInt(host.substring(portIndex + 1));
            host = host.substring(0, portIndex);
        }


        StringBuffer sb = new StringBuffer(method+" "+uri.substring(uri.indexOf("/"))+" HTTP/1.1\r\n");
        // 以下为请求头
        sb.append("Host: "+host+"\r\n");
        sb.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0\r\n");
        sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
        sb.append("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        // 注意这里不要使用压缩 否则返回乱码
        sb.append("Accept-Encoding: \r\n");
        sb.append("Connection: keep-alive\r\n");
        sb.append("Upgrade-Insecure-Requests: 1\r\n");
        // 注意这里要换行结束请求头
        sb.append("\r\n");
        Socket socket = new Socket(host, port);
        try {
            OutputStream os = socket.getOutputStream();
            os.write(sb.toString().getBytes());
            FileOutputStream fos = new FileOutputStream("d:\\httpFile.7z");
            InputStream is = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = is.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            System.out.println(new String(baos.toByteArray()));

            //fos.getChannel().transferFrom(socket.getChannel(),0,Integer.MAX_VALUE);
        }catch (Exception e){

        } finally {
            socket.close();
        }


    }



}
