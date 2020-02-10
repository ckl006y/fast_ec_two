package com.example.ck_core.net;

import com.example.ck_core.app.ConfigType;
import com.example.ck_core.app.Configurator;
import com.example.ck_core.app.Latte;

import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {


    public static final class ParamsHolder {
        public static final HashMap<String, Object> PARAMS = new HashMap<>();

    }

    public static HashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }


    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder {
//        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST);
        private static final String BASE_URL = Configurator.getInstance().getConfiguration(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())//转换器,
                .build();
    }

    private static final class OkHttpHolder {

        private static final int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
