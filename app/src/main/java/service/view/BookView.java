package service.view;

import service.entity.Book;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public interface BookView extends View{

    void onSuccess(Book mBook);
    void onError(String msg);
}
