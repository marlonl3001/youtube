package br.com.mdr.youtube.api;

import br.com.mdr.youtube.model.QueryResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Marlon D. Rocha on 15/04/2019.
 */
public interface YoutubeService {

    /**
     *  https://www.googleapis.com/youtube/v3/
     *  search
     *  ?part=snippet
     *  &order=date
     *  &maxResults=20
     *  &key=AIzaSyD2TJgXWxgdMrahkxgk_U3L8Ypklr6Em-U
     *  &channelId=UCVHFbqXqoYvEWM1Ddxl0QDg
     * **/

    @GET("search")
    Call<QueryResult> getVideos(@Query("part") String part, @Query("order") String order,
                                @Query("maxResults") String maxResults, @Query("key") String key,
                                @Query("channelId") String channelId,
                                @Query("q") String q);
}
