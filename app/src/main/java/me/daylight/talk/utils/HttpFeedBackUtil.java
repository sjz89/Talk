package me.daylight.talk.utils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import me.daylight.talk.http.OnHttpCallBack;
import me.daylight.talk.http.RetResult;
import retrofit2.HttpException;

public class HttpFeedBackUtil {
    public static void handleException(Throwable e, OnHttpCallBack callBack){
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            if (code == 500 || code == 404) {
                callBack.onFailed("服务器出错");
            }
        } else if (e instanceof ConnectException) {
            callBack.onFailed("网络断开,请打开网络!");
        } else if (e instanceof SocketTimeoutException) {
            callBack.onFailed("网络连接超时!!");
        } else if (!(e instanceof NullPointerException))
            callBack.onFailed("发生未知错误" + e.getMessage());
    }
    public static void handleRetResult(RetResult retResult, OnHttpCallBack<RetResult> callBack){
        if (retResult.getCode()== RetResult.RetCode.SUCCESS.code) {
            callBack.onSuccess(retResult);
        } else if(retResult.getCode()== RetResult.RetCode.FAIL.code)
            callBack.onFailed(retResult.getMsg());
    }
}
