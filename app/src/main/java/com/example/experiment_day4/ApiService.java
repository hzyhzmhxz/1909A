package com.example.experiment_day4;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService  {
    String banneruri="https://www.wanandroid.com/";
    @GET("banner/json")
    Observable<BannerBean> getban();

    String meiUrl="http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/20/20")
    Observable<MeiBean> geturi();
}
