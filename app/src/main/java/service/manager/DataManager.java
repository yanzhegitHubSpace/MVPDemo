package service.manager;

import android.content.Context;


import rx.Observable;
import service.RetrofitHelper;
import service.RetrofitService;
import service.entity.Book;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public class DataManager {

    private RetrofitService mRetrofirService;

    public DataManager(Context mContext){
        this.mRetrofirService = RetrofitHelper.getInstence(mContext).getServer();
    }

    public Observable<Book> getSerachBook(String name,String tag,int start,int count){
        return mRetrofirService.getSerachBook(name,tag,start,count);
    }

}
