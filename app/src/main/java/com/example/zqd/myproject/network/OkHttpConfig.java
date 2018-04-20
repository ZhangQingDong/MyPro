package com.example.zqd.myproject.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.Log;

import com.example.zqd.myproject.BuildConfig;
import com.example.zqd.myproject.base.BaseApplication;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * <p>Title: com.example.zqd.myproject.base</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 10:10
 */
public final class OkHttpConfig {

    private boolean doLog = BuildConfig.DEBUG;
    private final String TAG = getClass().getSimpleName();
    public OkHttpClient httpClient;
    private Builder builder;

    public Interceptor CACHE_CONTROL_NETWORK_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response res = chain.proceed(chain.request());
            Response.Builder resBuilder = res.newBuilder();
            if (builder.cacheType == CacheType.CACHE_THEN_NETWORK) {
                resBuilder.removeHeader("Pragma")
                        .header("Cache-Control", String.format("max-age=%d", builder.cacheSurvivalTime));
            }
            return resBuilder.build();
        }
    };

    public Interceptor CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (builder.cacheType == CacheType.FORCE_CACHE) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();

            } else if (builder.cacheType == CacheType.FORCE_NETWORK) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();

            } else if (builder.cacheType == CacheType.NETWORK_THEN_CACHE || builder.cacheType == CacheType.CACHE_THEN_NETWORK) {
                if (!isNetworkReachable(BaseApplication.getApp().getApplicationContext())) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }

            }
            return chain.proceed(request);
        }
    };
    private long timeStamp;

    private OkHttpConfig(Builder builder) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient.Builder clientBuilder =
                new OkHttpClient.Builder().connectTimeout(builder.connectTimeout, TimeUnit.SECONDS)
                        .readTimeout(builder.readTimeout, TimeUnit.SECONDS)
                        .writeTimeout(builder.writeTimeout, TimeUnit.SECONDS)
                        .cache(new Cache(builder.cachedDir, builder.maxCacheSize))
                        .retryOnConnectionFailure(builder.retryOnConnectionFailure)
                        .addInterceptor(logInterceptor)
                        .addInterceptor(CACHE_CONTROL_INTERCEPTOR)
                        .addNetworkInterceptor(CACHE_CONTROL_NETWORK_INTERCEPTOR);
        if (null != builder.networkInterceptors && !builder.networkInterceptors.isEmpty()) {
            clientBuilder.networkInterceptors().addAll(builder.networkInterceptors);
        }
        if (null != builder.interceptors && !builder.interceptors.isEmpty()) {
            clientBuilder.interceptors().addAll(builder.interceptors);
        }
        httpClient = clientBuilder.build();
        timeStamp = System.currentTimeMillis();
        this.builder = builder;
        switch (this.builder.cacheLevel) {
            case CacheLevel.FIRST_LEVEL:
                this.builder.cacheSurvivalTime = 0;
                break;
            case CacheLevel.SECOND_LEVEL:
                this.builder.cacheSurvivalTime = 15;
                break;
            case CacheLevel.THIRD_LEVEL:
                this.builder.cacheSurvivalTime = 30;
                break;
            case CacheLevel.FOURTH_LEVEL:
                this.builder.cacheSurvivalTime = 60;
                break;
            default:
                this.builder.cacheSurvivalTime = 0;
                break;
        }
        if (this.builder.cacheSurvivalTime > 0) {
            this.builder.cacheType = CacheType.CACHE_THEN_NETWORK;
        }
    }

    public static Builder Builder() {
        return new Builder();
    }

    /**
     * 判断网络是否可用
     *
     * @param context Context对象
     */
    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        return (current.isAvailable());
    }

    public HttpInfo doPostSync(HttpInfo info) {
        doRequestSync(info, Method.POST);
        return info;
    }

    public HttpInfo doGetSync(HttpInfo info) {
        doRequestSync(info, Method.GET);
        return info;
    }

    private HttpInfo doRequestSync(HttpInfo info, Method method) {
        try {
            String url = info.getUrl();
            if (TextUtils.isEmpty(url)) {
                return retInfo(info, info.CheckURL);
            }
            Response res = httpClient.newCall(fetchRequest(info, method)).execute();
            if (null != res && null != res.body()) {
                if (res.isSuccessful()) {
                    return retInfo(info, info.SUCCESS, res.body().string());
                } else {
                    showLog("HttpStatus: " + res.code());
                    /**
                     * 请求页面路径错误
                     */
                    if (res.code() == 404) {
                        return retInfo(info, info.CheckURL);
                    }
                    /**
                     * 服务器内部错误
                     */
                    if (res.code() == 500) {
                        return retInfo(info, info.NoResult);
                    }
                    /**
                     * 错误网关
                     */
                    if (res.code() == 502) {
                        return retInfo(info, info.CheckNet);
                    }
                    /**
                     * 网关超时
                     */
                    if (res.code() == 504) {
                        return retInfo(info, info.CheckNet);
                    }
                }
            }
            return retInfo(info, info.CheckURL);
        } catch (IllegalArgumentException e) {
            return retInfo(info, info.ProtocolException);
        } catch (SocketTimeoutException e) {
            if (null != e.getMessage()) {
                if (e.getMessage().contains("failed to connect to")) {
                    return retInfo(info, info.ConnectionTimeOut);
                }
                if (e.getMessage().equals("timeout")) {
                    return retInfo(info, info.WriteAndReadTimeOut);
                }
            }
            return retInfo(info, info.WriteAndReadTimeOut);
        } catch (UnknownHostException e) {
            return retInfo(info, info.CheckNet);
        } catch (Exception e) {
            return retInfo(info, info.NoResult);
        }
    }

    public void doPostAsync(HttpInfo info, Callback callback) {
        doRequestAsync(info, Method.POST, callback);
    }

    public void doGetAsync(HttpInfo info, Callback callback) {
        doRequestAsync(info, Method.GET, callback);
    }

    private void doRequestAsync(HttpInfo info, Method method, Callback callback) {
        httpClient.newCall(fetchRequest(info, method)).enqueue(callback);
    }

    private Request fetchRequest(HttpInfo info, Method method) {
        Request request = null;
        if (method == Method.POST) {
            FormBody.Builder builder = new FormBody.Builder();
            if (null != info.getParams() && !info.getParams().isEmpty()) {
                StringBuffer log = new StringBuffer("PostParams: ");
                for (String key : info.getParams().keySet()) {
                    builder.add(key, info.getParams().get(key));
                    log.append(key + " =" + info.getParams().get(key) + ", ");
                }
                showLog(log.toString());
            }
            request = new Request.Builder().url(info.getUrl()).post(builder.build()).build();
        } else {
            StringBuffer params = new StringBuffer();
            params.append(info.getUrl());
            if (null != info.getParams() && !info.getParams().isEmpty()) {
                for (String name : info.getParams().keySet()) {
                    params.append("&" + name + "=" + info.getParams().get(name));
                }
            }
            request = new Request.Builder().url(params.toString()).get().build();
        }
        return request;
    }

    private HttpInfo retInfo(HttpInfo info, int code) {
        retInfo(info, code, null);
        return info;
    }

    private HttpInfo retInfo(HttpInfo info, int code, String resDetail) {
        info.packInfo(code, resDetail);
        showLog("Response: " + info.getRetDetail());
        return info;
    }

    private void showLog(String msg) {
        if (this.doLog) {
            Log.e(TAG + "[" + timeStamp + "]", msg);
        }
    }

    private enum Method {
        GET, POST
    }

    @IntDef({
            CacheType.FORCE_NETWORK, CacheType.FORCE_CACHE, CacheType.NETWORK_THEN_CACHE,
            CacheType.CACHE_THEN_NETWORK
    })
    public @interface CacheType {
        int FORCE_NETWORK = 1;
        int FORCE_CACHE = 2;
        int NETWORK_THEN_CACHE = 3;
        int CACHE_THEN_NETWORK = 4;
    }

    @IntDef({
            CacheLevel.FIRST_LEVEL, CacheLevel.SECOND_LEVEL, CacheLevel.THIRD_LEVEL,
            CacheLevel.FOURTH_LEVEL
    })

    public @interface CacheLevel {
        /**
         * 默认无缓存
         */
        int FIRST_LEVEL = 1;
        /**
         * 缓存存活时间为15秒
         */
        int SECOND_LEVEL = 2;
        /**
         * 30秒
         */
        int THIRD_LEVEL = 3;
        /**
         * 60秒
         */
        int FOURTH_LEVEL = 4;
    }

    public static final class Builder {

        private int maxCacheSize = 10 * 1024 * 1024;
        private File cachedDir = BaseApplication.getApp().getApplicationContext().getCacheDir();
        private int connectTimeout = 10;
        private int readTimeout = 10;
        private int writeTimeout = 10;
        private boolean retryOnConnectionFailure = false;
        private List<Interceptor> networkInterceptors;
        private List<Interceptor> interceptors;
        private int cacheSurvivalTime = 0;
        private int cacheType = CacheType.NETWORK_THEN_CACHE;
        private int cacheLevel = CacheLevel.SECOND_LEVEL;

        public Builder() {
        }

        public OkHttpConfig build() {
            return new OkHttpConfig(this);
        }

        public Builder setMaxCacheSize(int maxCacheSize) {
            this.maxCacheSize = maxCacheSize;
            return this;
        }

        public Builder setCachedDir(File cachedDir) {
            this.cachedDir = cachedDir;
            return this;
        }

        public Builder setConnectTimeout(int connectTimeout) {
            if (connectTimeout <= 0) {
                throw new IllegalArgumentException("connectTimeout must be > 0");
            }
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setReadTimeout(int readTimeout) {
            if (readTimeout <= 0) {
                throw new IllegalArgumentException("readTimeout must be > 0");
            }
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setWriteTimeout(int writeTimeout) {
            if (writeTimeout <= 0) {
                throw new IllegalArgumentException("writeTimeout must be > 0");
            }
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public Builder setNetworkInterceptors(List<Interceptor> networkInterceptors) {
            this.networkInterceptors = networkInterceptors;
            return this;
        }

        public Builder setInterceptors(List<Interceptor> interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public Builder setCacheSurvivalTime(int cacheSurvivalTime) {
            if (cacheSurvivalTime < 0) {
                throw new IllegalArgumentException("cacheSurvivalTime must be >= 0");
            }
            this.cacheSurvivalTime = cacheSurvivalTime;
            return this;
        }

        public Builder setCacheType(@CacheType int cacheType) {
            this.cacheType = cacheType;
            return this;
        }

        public Builder setCacheLevel(@CacheLevel int cacheLevel) {
            this.cacheLevel = cacheLevel;
            return this;
        }
    }

    public static class HttpInfo {

        private String url;
        private Map<String, String> params;

        private int retCode;
        private String retDetail;

        public HttpInfo(Builder builder) {
            this.url = builder.url;
            this.params = builder.params;
        }

        public static Builder Builder() {
            return new Builder();
        }

        public static final class Builder {

            private String url;
            private Map<String, String> params;

            public Builder() {
            }

            public HttpInfo build() {
                return new HttpInfo(this);
            }

            public Builder setUrl(String url) {
                this.url = url;
                return this;
            }

            public Builder addParams(Map<String, String> params) {
                this.params = params;
                return this;
            }

            public Builder addParam(String key, String value) {
                if (null == this.params) {
                    this.params = new HashMap<>(16);
                }
                if (!TextUtils.isEmpty(key)) {
                    this.params.put(key, value);
                }
                return this;
            }
        }

        public final int NonNetwork = 1;
        private final String NonNetwork_Detail = "网络中断";
        public final int SUCCESS = 2;
        private final String SUCCESS_Detail = "发送请求成功";
        public final int ProtocolException = 3;
        private final String ProtocolException_Detail = "请检查协议类型是否正确";
        public final int NoResult = 4;
        private final String NoResult_Detail = "无法获取返回信息(服务器内部错误)";
        public final int CheckURL = 5;
        private final String CheckURL_Detail = "请检查请求地址是否正确";
        public final int CheckNet = 6;
        private final String CheckNet_Detail = "请检查网络连接是否正常";
        public final int ConnectionTimeOut = 7;
        private final String ConnectionTimeOut_Detail = "连接超时";
        public final int WriteAndReadTimeOut = 8;
        private final String WriteAndReadTimeOut_Detail = "读写超时";

        public HttpInfo packInfo(int retCode) {
            return packInfo(retCode, null);
        }

        public HttpInfo packInfo(int retCode, String retDetail) {
            this.retCode = retCode;
            switch (retCode) {
                case NonNetwork:
                    this.retDetail = NonNetwork_Detail;
                    break;
                case SUCCESS:
                    this.retDetail = SUCCESS_Detail;
                    break;
                case ProtocolException:
                    this.retDetail = ProtocolException_Detail;
                    break;
                case NoResult:
                    this.retDetail = NoResult_Detail;
                    break;
                case CheckURL:
                    this.retDetail = CheckURL_Detail;
                    break;
                case CheckNet:
                    this.retDetail = CheckNet_Detail;
                    break;
                case ConnectionTimeOut:
                    this.retDetail = ConnectionTimeOut_Detail;
                    break;
                case WriteAndReadTimeOut:
                    this.retDetail = WriteAndReadTimeOut_Detail;
                    break;
                default:
                    break;
            }
            if (!TextUtils.isEmpty(retDetail)) {
                this.retDetail = retDetail;
            }
            return this;
        }

        public boolean isSuccessful() {
            if (this.retCode == SUCCESS) {
                return true;
            }
            return false;
        }

        public String getUrl() {
            return url;
        }

        public String getRetDetail() {
            return retDetail;
        }

        public <T> T getRetDetail(Class<T> clazz) {
            return new Gson().fromJson(retDetail, clazz);
        }

        public void setRetDetail(String retDetail) {
            this.retDetail = retDetail;
        }

        public Map<String, String> getParams() {
            return params;
        }
    }
}
