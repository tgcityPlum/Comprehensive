package com.tgcity.network.retrofit;

/**
 * @author TGCity
 */
public interface SubscriberOnNextListener<T> {
    /**
     * next
     *
     * @param t T
     */
    void onNext(T t);

    /**
     * error
     *
     * @param e Throwable
     */
    void onError(Throwable e);

    /**
     * complete
     */
    void onCompleted();
}
