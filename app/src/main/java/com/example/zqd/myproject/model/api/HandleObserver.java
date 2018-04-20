package com.example.zqd.myproject.model.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <p>Title: com.example.zqd.myproject.model.api</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 10:50
 */
public abstract class HandleObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        //todo 集成bugly
        onMyError(e);
    }

    @Override
    public void onComplete() {
        onFinally();
    }

    /**
     * 在{@link #onError}和{@link #onComplete()}后回调
     */
    public void onFinally() {

    }

    protected abstract void onMyError(Throwable e);

}
