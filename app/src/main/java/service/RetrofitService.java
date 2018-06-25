package service;

import service.entity.Book;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public interface RetrofitService {
    /**
     * 获取书本详情内容，其中Query就是将URL进行拼接的方式.
     * 请求方式上的差异剑见下
     * GET ----------查找资源（查）
     * POST --------修改资源（改）
     * PUT ----------上传文件（增）
     * DELETE ----删除文件（删）
     * HEAD--------只请求页面的首部
     * @param name
     * @param tag
     * @param start
     * @param count
     * @return
     *
     * 几种注解说明
     * QueryMap（）如果参数较多将他们放在Map中。
     * QueryPath（） 替换url中某个字段
     * Body（）post中指定请求体
     * @Field(POST请求): 表单传送
     * @Header/@Headers(POST请求): 用于添加请求头部
     *  配合RxJava时返回的是被观察者Observable，再创建一个观察者订阅被观察者就行了
     */
    @GET("book/search")
    Observable<Book> getSerachBook(@Query("q") String name,
                                   @Query("tag") String tag,
                                   @Query("start") int start,
                                   @Query("count") int count);



}
