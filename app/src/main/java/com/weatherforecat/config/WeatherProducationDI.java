package com.weatherforecat.config;

import android.app.Application;
import android.util.Log;

import com.weatherforecat.BuildConfig;
import com.weatherforecat.domain.repository.WeatherRepository;
import com.weatherforecat.domain.repository.WeatherRepositoryImpl;
import com.weatherforecat.domain.repository.remote.api.WeatherService.WeatherService;
import com.weatherforecat.module.base.WeatherForecastViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherProducationDI implements DI {
    public static final String HEADER_AUTHORIZTION = "Authorization";
    protected final long REQUEST_TIME_OUT_S = 180L;
    protected final long HTTP_CACHE_SIZE_MB = 10 * 1024 * 1024;

    protected static WeatherForecastViewModelFactory weatherForecastViewModelFactory;
    protected static Retrofit singletonRetrofit;
    protected final String HEADER_CONTENT_TYPE = "Content-Type";
    protected final String JSON_CONTENT = "application/json";
    protected final Application application;

    public WeatherProducationDI(Application application) {
        this.application = application;
    }

    /* @Override
     public HashMap<String, String> getAuthHeader() {
         HashMap<String, String> authHeader = new HashMap<>();
         ;
         authHeader.put(HEADER_CONTENT_TYPE, "text/plain");
         return authHeader;
     }
 */
    @Override
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.readTimeout(REQUEST_TIME_OUT_S, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(REQUEST_TIME_OUT_S, TimeUnit.SECONDS);
        okHttpClientBuilder.connectTimeout(REQUEST_TIME_OUT_S, TimeUnit.SECONDS);


        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader(HEADER_AUTHORIZTION,
                                "")
                        .addHeader(HEADER_CONTENT_TYPE, JSON_CONTENT)
                        .build();
                return chain.proceed(request);
            }
        });

        return okHttpClientBuilder.build();
    }


    @Override
    public Application provideApplication() {
        return application;
    }

    @Override
    public Retrofit provideRetrofit() {
        if (singletonRetrofit == null) {
            synchronized (Retrofit.class) {
                singletonRetrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(provideOkHttpClient())
                        .baseUrl(BuildConfig.API_SERVER)
                        .build();
                Log.d("DataDailyForcast", "" + singletonRetrofit);
            }
        }
        return singletonRetrofit;
    }

    @Override
    public WeatherForecastViewModelFactory provideViewModelFactory() {
        if (weatherForecastViewModelFactory == null) {
            synchronized ((WeatherForecastViewModelFactory.class)) {
                weatherForecastViewModelFactory = new WeatherForecastViewModelFactory(provideApplication());
            }
        }
        return weatherForecastViewModelFactory;
    }


    @Override
    public WeatherRepository provideWeatherRepository() {
        return new WeatherRepositoryImpl(provideWeatherService());
    }

    @Override
    public WeatherService provideWeatherService() {
        return provideRetrofit().create(WeatherService.class);
    }

}
