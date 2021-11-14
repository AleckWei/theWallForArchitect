package com.example.rxtest.rxcode.day3;

import android.util.Log;

import com.example.rxcore.abstractClz.Observable;
import com.example.rxcore.imp.Emitter;
import com.example.rxcore.imp.Function;
import com.example.rxcore.imp.ObservableOnSubscribe;
import com.example.rxcore.imp.ObservableSource;
import com.example.rxcore.imp.Observer;
import com.example.rxcore.operator.scheduler.Schedulers;


public class RxJavaByMySelf {

    public static void testCreateMyself() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("sb");
                emitter.onError(new Throwable("我是sb"));
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                Log.i("wwj", "onSubscribe");
            }

            @Override
            public void onNext(String str) {
                Log.d("wwj", "onNext：" + str);
            }

            @Override
            public void onComplete() {
                Log.d("wwj", "onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.w("wwj", "onError" + throwable.getMessage());
            }
        });
    }

    public static void testMapMyself() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("sb");
                emitter.onError(new Throwable("我是sb"));
                emitter.onComplete();
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + " After Map Operator";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                Log.i("wwj", "onSubscribe");
            }

            @Override
            public void onNext(String str) {
                Log.d("wwj", "onNext：" + str);
            }

            @Override
            public void onComplete() {
                Log.d("wwj", "onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.w("wwj", "onError" + throwable.getMessage());
            }
        });
    }

    public static void testSelfFlatMap() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("sb1");
                emitter.onNext("sb2");
                emitter.onNext("sb3");
                emitter.onNext("sb4");
                emitter.onNext("sb5");
                emitter.onNext("sb6");
                emitter.onNext("sb7");
                // emitter.onError(new Throwable("我是sb"));
                emitter.onComplete();
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final String s) {
                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(Emitter<String> emitter) {
                        emitter.onNext(s + "  --  FlatMap");
                    }
                });
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                Log.i("wwj", "onSubscribe");
            }

            @Override
            public void onNext(String str) {
                Log.d("wwj", "onNext：" + str);
            }

            @Override
            public void onComplete() {
                Log.d("wwj", "onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.w("wwj", "onError" + throwable.getMessage());
            }
        });
    }

    public static void testScheduler() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
//                Log.d("wwj", "subscribe Thread: " + Thread.currentThread());
                emitter.onNext("sb1");
                emitter.onNext("sb2");
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        Log.d("wwj", "apply Thread: " + Thread.currentThread());
                        return s + " After Map Operator";
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {
                        Log.d("wwj", "onSubscribe Thread: " + Thread.currentThread());
//                Log.i("wwj", "onSubscribe");
                    }

                    @Override
                    public void onNext(String str) {
                        Log.d("wwj", "onNext Thread: " + Thread.currentThread());
//                Log.d("wwj", "onNext：" + str);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("wwj", "onComplete Thread: " + Thread.currentThread());
//                Log.d("wwj", "onComplete");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("wwj", "onError Thread: " + Thread.currentThread());
//                Log.w("wwj", "onError" + throwable.getMessage());
                    }
                });
    }

}
