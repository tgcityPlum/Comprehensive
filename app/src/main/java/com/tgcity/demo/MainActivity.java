package com.tgcity.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private Disposable timer;

    private Button tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvTime = findViewById(R.id.tv_time);
//        onRxBook1();
//        onRxBook2();
//        onRxTime1();
//        onRxTime2(5);

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRxTime3(5);
            }
        });
    }

    private void onRxTime3(final int time) {

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(time + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return time - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        tvTime.setText("发送验证码");
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                tvTime.setText("还剩" + aLong + "秒");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                tvTime.setText("发送验证码");
            }
        });
    }

    private void onRxTime2(final int time) {

        timer = Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .take(time + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (timer == null) return;

                        tvTime.setText(String.valueOf(time - aLong));

                        if (time - aLong == 0) {
                            startActivity(new Intent(MainActivity.this, TestWebActivity.class));
                        }
                    }
                });


    }

    /**
     * 计时器
     */
    private void onRxTime1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(123);
                sleep(5000);
                emitter.onNext(456);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("accept-integer", integer.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("accept-throwable", throwable.toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e("Action", "run");
                    }
                });
    }

    /**
     * 利用Rx常规写法处理书籍更新时的推送逻辑
     */
    private void onRxBook2() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                for (int i = 0; i < 5; i++) {
                    emitter.onNext("更新章节" + i);
                }
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("TAG", "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("TAG", "onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "onComplete");
                    }
                });
    }

    /**
     * 利用Rx方式来处理书籍更新时的推送逻辑
     */
    private void onRxBook1() {
        //定义被观察者
        Observable book = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //添加更新的章节
                for (int i = 0; i < 5; i++) {
                    emitter.onNext("更新章节" + i);
                }
                emitter.onComplete();
            }
        });
        //定义观察者
        Observer<String> person = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("TAG", "onSubscribe");
            }

            @Override
            public void onNext(String message) {
                Log.e("TAG", "onNext:" + message);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError");
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete");
            }
        };
        //被观察者  被订阅  观察者
        book.subscribe(person);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer != null && !timer.isDisposed()) {
            timer.dispose();
            timer = null;
        }
    }
}
