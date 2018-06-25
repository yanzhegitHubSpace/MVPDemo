package service;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public class RetrofitHelper {

    private Context mContxt;

    OkHttpClient client = new OkHttpClient();
    private Retrofit mRetrofit = null;
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());

    private static RetrofitHelper instence = null;

    public static RetrofitHelper getInstence(Context context){
        if(instence == null){
            instence = new RetrofitHelper(context);
        }
        return instence;
    }

    private RetrofitHelper(Context mContxt){
        this.mContxt = mContxt;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava
                .build();
    }

    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }

}
