package me.daylight.talk.http;

public interface OnHttpCallBack<T> {
    void onSuccess(T t);

    void onFailed(String errorMsg);
}
