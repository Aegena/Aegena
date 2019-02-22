package com.campanula.aegena.service;

import com.campanula.aegena.model.BaseModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * package com.campanula.aegena.service
 *
 * @author campanula
 * date 2019/1/16
 * desc
 **/
public interface HttpService {

    @GET("/query")
    Observable<BaseModel<List<Object>>> getExpress(@Query("type") String type, @Query("postid") String postid);

}
