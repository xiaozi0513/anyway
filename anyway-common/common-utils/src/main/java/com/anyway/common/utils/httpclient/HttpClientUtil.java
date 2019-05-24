package com.anyway.common.utils.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: wang_hui
 * @date: 2019/5/22 下午8:43
 */
@Slf4j
public class HttpClientUtil {
    private static final int DEFAULT_MAX_TOTAL = 200;
    private static final int DEFAULT_MAX_PER_ROUTE = 200;
    private static final int DEFAULT_CONNECT_TIMEOUT = 500;
    private static final int DEFAULT_SOCKET_TIMEOUT = 2000;
    private static final int DEFAULT_CONNECT_REQUEST_TIMEOUT = 500;

    private static PoolingHttpClientConnectionManager connManager;
    private static CloseableHttpClient httpClient;
    private static ConnectionMonitorThread monitorThread;

    private final int maxTotal;
    private final int maxPerRoute;
    private final int connectTimeout;
    private final int socketTimeout;
    private final int connectRequestTimeout;

    public HttpClientUtil() {
        this(DEFAULT_MAX_TOTAL, DEFAULT_MAX_PER_ROUTE, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT, DEFAULT_CONNECT_REQUEST_TIMEOUT);
    }

    /**
     * @param maxTotal              最大连接数
     * @param maxPerRoute           每个路由的最大连接数
     * @param connectTimeout        连接超时时间，单位：毫秒
     * @param socketTimeout         读取超时时间，单位：毫秒
     * @param connectRequestTimeout 从连接池获取连接实例超时时间，单位：毫秒
     */
    public HttpClientUtil(int maxTotal, int maxPerRoute, int connectTimeout, int socketTimeout, int connectRequestTimeout) {
        this.maxTotal = maxTotal;
        this.maxPerRoute = maxPerRoute;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
        this.connectRequestTimeout = connectRequestTimeout;

        //pool configuration
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        connManager = new PoolingHttpClientConnectionManager(registry);
        connManager.setMaxTotal(maxTotal);
        connManager.setDefaultMaxPerRoute(maxPerRoute);
        //request configuration
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)//设置连接超时
                .setSocketTimeout(socketTimeout)//设置读取超时
                .setConnectionRequestTimeout(connectRequestTimeout)//设置从连接池获取连接实例的超时
                .build();
        //create http client
        httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        //start a monitor thread to release idle connection
        monitorThread = new ConnectionMonitorThread(connManager);
        monitorThread.start();

        log.info("init http client success.");
    }

    public void shutdown() {
        monitorThread.shutdown();
        connManager.shutdown();
    }

    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    public static String doGet(String url, Map<String, Object> params) {
        return doGet(url, null, params);
    }

    public static String doGet(String url, Map<String, String> headers, Map<String, Object> params) {
        HttpGet httpGet = new HttpGet();
        //set header
        if (headers != null && headers.size() > 0) {
            headers.entrySet().forEach(entry -> httpGet.addHeader(entry.getKey(), entry.getValue()));
        }
        //set url
        String apiUrl = getUrlWithParams(url, params);
        httpGet.setURI(URI.create(apiUrl));

        return resultResponse(httpGet);
    }

    public static String doPost(String url, Map<String, Object> params) {
        return doPost(url, null, params);
    }

    public static String doPost(String url, Map<String, String> headers, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(url);
        //set header
        if (headers != null && headers.size() > 0) {
            headers.entrySet().forEach(entry -> httpPost.addHeader(entry.getKey(), entry.getValue()));
        }
        //set body
        if (params != null && params.size() > 0) {
            HttpEntity reqEntity = getUrlEncodedFormEntity(params);
            httpPost.setEntity(reqEntity);
        }

        return resultResponse(httpPost);
    }

    public static String doPostOfJson(String url, String json) {
        return doPostOfJson(url, null, json);
    }

    public static String doPostOfJson(String url, Map<String, String> headers, String json) {
        HttpPost httpPost = new HttpPost(url);
        //set header
        if (headers != null && headers.size() > 0) {
            headers.entrySet().forEach(entry -> httpPost.addHeader(entry.getKey(), entry.getValue()));
        }
        //set body
        HttpEntity reqEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(reqEntity);

        return resultResponse(httpPost);
    }

    private static String resultResponse(HttpUriRequest request) {
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            String reason = response.getStatusLine().getReasonPhrase();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, StandardCharsets.UTF_8);
            } else {
                log.error("http client response status: {}, reason: {}, url: {}", statusCode, reason, request.getURI().toString());
            }
        } catch (SocketTimeoutException e) {
            log.error("http client response timeout. url: {}", request.getURI().toString(), e);
        } catch (IOException e) {
            log.error("http client response error. url: {}", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String getUrlWithParams(String url, Map<String, Object> params) {
        boolean first = true;
        StringBuilder sb = new StringBuilder(url);
        for (String key : params.keySet()) {
            char ch = '&';
            if (first) {
                ch = '?';
                first = false;
            }
            String value = params.get(key).toString();
            try {
                String encodeValue = URLEncoder.encode(value, StandardCharsets.UTF_8.name());
                sb.append(ch).append(key).append("=").append(encodeValue);
            } catch (UnsupportedEncodingException e) {
            }
        }
        return sb.toString();
    }

    private static HttpEntity getUrlEncodedFormEntity(Map<String, Object> params) {
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        params.entrySet().forEach(entry -> {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
            pairList.add(pair);
        });
        return new UrlEncodedFormEntity(pairList, StandardCharsets.UTF_8);
    }

    private static class ConnectionMonitorThread extends Thread {
        private final PoolingHttpClientConnectionManager connMgr;
        private volatile boolean shutdown = false;

        public ConnectionMonitorThread(PoolingHttpClientConnectionManager connManager) {
            this.connMgr = connManager;
            setDaemon(true);
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    log.debug("http client pool status: {}", connMgr.getTotalStats().toString());
                    synchronized (this) {
                        this.wait(60000);
                        //关闭失效的连接
                        connMgr.closeExpiredConnections();
                        //关闭30秒内不活动的连接
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void shutdown() {
            this.shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }

}
