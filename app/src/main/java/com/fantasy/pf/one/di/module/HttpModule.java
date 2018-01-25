package com.fantasy.pf.one.di.module;

import com.fantasy.pf.one.BuildConfig;
import com.fantasy.pf.one.di.qualifier.OneUrl;
import com.fantasy.pf.one.model.http.CacheInterceptor;
import com.fantasy.pf.one.model.http.api.OneApis;
import com.fantasy.pf.one.utils.Constants;
import com.fantasy.pf.one.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.fantasy.pf.one.utils.Constants.CACHE_SIZE;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 */

@Module
public class HttpModule {

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkhttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
//        File cacheFile = new File(Constants.PATH_CACHE);
//        Cache cache = new Cache(cacheFile, CACHE_SIZE);
//
//        Interceptor cacheInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                if (!Utils.isNetworkConnected()) {
//                    request = request.newBuilder()
//                            .cacheControl(CacheControl.FORCE_CACHE)
//                            .build();
//                }
//                Response response = chain.proceed(request);
//                if (Utils.isNetworkConnected()) {
//                    int maxAge = 0;
//                    //有网络时，不缓存，最大保存时长为0
//                    response.newBuilder()
//                            .header("Cache-Control", "public, max-age=" + maxAge)
//                            .removeHeader("Pragma")
//                            .build();
//                } else {
//                    //无网络时，超时为4周
//                    int maxStale = 60 * 60 * 24 * 28;
//                    response.newBuilder()
//                            .header("Cache-Control", "public, only-if-cache, max-stale=" + maxStale)
//                            .removeHeader("Pragma")
//                            .build();
//
//                }
//
//                return response;
//            }
//        };
        Cache cache = new Cache(new File(Constants.PATH_CACHE), CACHE_SIZE);
        //设置缓存
//        builder.addNetworkInterceptor(cacheInterceptor);
//        builder.addInterceptor(cacheInterceptor);
        builder.addInterceptor(new CacheInterceptor());
        builder.addNetworkInterceptor(new CacheInterceptor());
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    /**类名 HttpModule
     * @date 创建时间 2018/1/24 下午5:08
     * @author pforlove
     * 注解 @Provides
     * @description   retrofit 网络类依赖了retrofit,ok
     * @param builder,client
     */

    @Provides
    @Singleton
    @OneUrl
    Retrofit provideOneRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder,client, OneApis.HOST);
    }

    @Provides
    @Singleton
    OneApis provideOneService(@OneUrl Retrofit retrofit){
        return retrofit.create(OneApis.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
