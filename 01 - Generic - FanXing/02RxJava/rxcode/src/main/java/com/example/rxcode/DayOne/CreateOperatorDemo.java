package com.example.rxcode.DayOne;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * rxJava
 * 创建操作符
 * 具体有哪些操作符，请翻看 《各种操作符及其作用》 文档
 */
public class CreateOperatorDemo {

    public static Observer myObserver = new Observer<Object>() {
        @Override
        public void onSubscribe(Disposable d) {
            // 订阅关系一旦建立，就会回调到这里
            Log.d("wwj", "建立订阅");
        }

        @Override
        public void onNext(Object o) {
            // 上面emitter的 onNext 事件就会回调这里
            Log.d("wwj", "onNext回调：" + o.toString());
        }

        @Override
        public void onError(Throwable e) {
            Log.d("wwj", "onError：" + e.getMessage());
        }

        @Override
        public void onComplete() {
            Log.d("wwj", "onComplete");
        }
    };

    /**
     * create操作符，多用于网络请求的封装，我们只需处理这个Observable就可以了
     */
    public static void test() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                // 这样就建立了一个 观察者 和 被观察者 订阅关系
                // 这里就是事件产生的地方
                emitter.onNext("wwj1");
                emitter.onNext("wwj2");
                emitter.onNext("wwj3");

                // 加入onError的回调之后，就会发现onComplete不会被回调了
                emitter.onError(new Throwable("你猜是什么错？"));
                // onError和onComplete有你没我
                emitter.onComplete();
            }
        }).subscribe(myObserver);
    }

    /**
     * 实际上在开发过程中，使用的较多的情况还是
     * new一个consumer，因为只用实现正常的情况
     * 而如果要进行异常处理，就可以多添加一个Throwable的consumer
     */
    public static void test1() {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                // 这样就建立了一个 观察者 和 被观察者 订阅关系
                // 这里就是事件产生的地方
                emitter.onNext("wwj1");
                emitter.onNext("wwj2");
                emitter.onNext("wwj3");

                // 加入onError的回调之后，就会发现onComplete不会被回调了
                emitter.onError(new Throwable("你猜是什么错？"));
                // onError和onComplete有你没我
//                emitter.onComplete();
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                // 第一个consumer不会处理异常
                Log.d("wwj", o.toString());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                // 第二个consumer才会捕捉异常
                Log.d("wwj", "捕捉异常：" + throwable.getMessage());
            }
        });
    }

    /**
     * just操作符的内部实现思路：
     * 实际上就是在subscribe当中使用emitter，调用emitter的onNext事件
     * 将传入的参数分发下去
     */
    public static void testJust() {
        // 这里有10个重载方法，目的就是为了限制使用者
        // 最多只能传入10个参数
        Observable.just("1", "aaa", "2")
                .subscribe(myObserver);
    }

    /**
     * FromArray操作符是just的代码实现基础
     * fromArray 是可以传入无线个参数的
     */
    public static void testFromArray() {
        Observable.fromArray("123", "3251", "a阿尔偶发", "eopifaewonfewapf", "安慰破费烷发泡", "pe饿哦你发文", "1221312e121221").
                subscribe(myObserver);
    }

    public static void testFromIterable() {
        ArrayList<String> myStrList = new ArrayList<>();
        myStrList.add("123");
        myStrList.add("wwj");
        myStrList.add("napenf");
        myStrList.add("o2epfenfa ");
        myStrList.add("我阿文肥胖纹安分");
        myStrList.add("配方跑去问");
        myStrList.add("12weno饿哦年份aaefc");
        Observable.fromIterable(myStrList).
                subscribe(myObserver);
    }

    public static void testFromFuture() {
        Observable.fromFuture(new Future<Object>() {
            @Override
            public boolean cancel(boolean b) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws ExecutionException, InterruptedException {
                return "我叫future";
            }

            @Override
            public Object get(long l, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
                return null;
            }
        }).subscribe(myObserver);
    }

    public static void testFromCallable() {
        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "callable回调";
            }
        }).subscribe(myObserver);
    }
}
