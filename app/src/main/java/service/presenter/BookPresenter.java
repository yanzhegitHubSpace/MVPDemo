package service.presenter;

import android.content.Context;
import android.content.Intent;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import service.entity.Book;
import service.manager.DataManager;
import service.view.BookView;
import service.view.View;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public class BookPresenter implements Presenter{

    private DataManager manager;
    // 用于存放订阅关系，使用完需要将订阅关系清除，不然会发生内存泄漏.
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mBookView;
    private Book mBook;
    public BookPresenter (Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        // 如果有订阅关系就需要清除订阅关系.
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mBookView = (BookView)view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchBooks(String name,String tag,int start,int count) {
        mCompositeSubscription.add(manager.getSerachBook(name, tag, start, count)
                // 定义请求事件发生在io线程中.
                .subscribeOn(Schedulers.io())
                // 定义请求事件在主线程消费.
                .observeOn(AndroidSchedulers.mainThread())
                // 订阅者中有三个方法，onNext,onComplete,onError 请求成功就会调用onNext,并传入请求返回的实体类
                // 到把请求下来的Book实体类存在内存中，就会调用onCompleted，这时就可以将数据交给BookView处理了
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if (mBook != null) {
                            mBookView.onSuccess(mBook);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBookView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }
                })
        );

    }
}
