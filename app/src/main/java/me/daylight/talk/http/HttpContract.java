package me.daylight.talk.http;

import java.util.List;

import io.reactivex.Observable;
import me.daylight.talk.bean.FriendList;
import me.daylight.talk.bean.User;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface HttpContract {
    @POST("userLogin/login")
    Observable<RetResult<Integer>> login(@Query("phone") String phone, @Query("password") String password);

    @POST("userLogin/register")
    Observable<RetResult<String>> register(@Query("phone") String phone,@Query("password") String password);

    @POST("user/getInfo")
    Observable<RetResult<User>> getUserInfo(@Query("phone")String phone);

    @POST("user/getFriendsInfo")
    Observable<RetResult<List<User>>> getFriendsInfo(@Query("phone") String phone, @Query("mills") long time);

    @POST("user/changeInfo")
    Observable<RetResult<String>> saveUserInfo(@Body User user);

    @POST("friend/getFriendList")
    Observable<RetResult<List<FriendList>>> getFriendList(@Query("phone")String phone,@Query("mills")long time);

    @POST("user/updateInfo")
    Observable<RetResult<List<User>>> updateUserInfo(@Query("phone")String phone,@Query("mills")long time);

    @POST("user/uploadHead")
    @Multipart
    Observable<RetResult> uploadHeadImage(@Part MultipartBody.Part file,@Query("phone")String phone);
}
