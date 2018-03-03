package org.dalol.ethiomixtube.presenter.base;

import org.dalol.ethiomixtube.ui.base.BaseView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by filippo on 05/11/2017.
 */

public abstract class BasePresenter<V extends BaseView> {

    private final V mView;
    private final CompositeDisposable mDisposables = new CompositeDisposable();

    public BasePresenter(V view) {
        mView = view;
    }

    public void onConstruct(){}

    protected <O> void subscribe(Observable<O> observable, DisposableObserver<O> observer) {
        observable.subscribeOn(Schedulers.computation())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .subscribe(observer);
        mDisposables.add(observer);
    }

    public V getView() {
        return mView;
    }

    public void onDestruct(){
        if (mDisposables != null && !mDisposables.isDisposed()) {
            mDisposables.dispose();
            mDisposables.clear();
        }
    }
}
