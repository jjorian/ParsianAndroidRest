package ir.parsianandroid.parsianandroidres.Global;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import ir.parsianandroid.parsianandroidres.AppSettings;
import ir.parsianandroid.parsianandroidres.Interface.APIService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JavAd on 2018/02/08.
 */

public class RetrofitInitialize {
    Context vContext;
    Retrofit retrofit;
    static String baseurl="";
    public APIService webServices;
    public static RetrofitInitialize Instance;

    public RetrofitInitialize(Context ctx)
    {


        vContext=ctx;
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request().newBuilder().addHeader("DeviceId", GlobalUtils.getDeviceId()).addHeader("UserID", AppSettings.with(vContext).GeID()+"").addHeader("UserName", AppSettings.with(vContext).GetUserName()).build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();

        retrofit= new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        webServices = retrofit.create(APIService.class);

    }
    public static RetrofitInitialize With(Context ctx)
    {
        baseurl=AppSettings.with(ctx).GeServerBaseUrl();
        Instance=new RetrofitInitialize(ctx);
        return Instance;
    }
    public static RetrofitInitialize With(Context ctx,String urlinput)
    {
        baseurl=urlinput;
        Instance=new RetrofitInitialize(ctx);


        return Instance;
    }


}
