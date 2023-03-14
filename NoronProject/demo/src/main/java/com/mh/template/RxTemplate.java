package com.mh.template;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RxTemplate {
    public static <T> Single<T> rxSchedulerIo(SupplierThrowable<T> supplier) {
        return Single.just("io")
                .subscribeOn(Schedulers.io())
                .flatMap(s -> rxBlockingAsync(supplier));
    }

    private static <T> Single<T> rxBlockingAsync(SupplierThrowable<T> supplier) {
        return Single.create(emitter -> {
            try {
                System.out.println(String.format("[THREAD] %s, supplier: %s", Thread.currentThread().getName(), supplier.getClass().getName()));
                final T value = supplier.get();
                emitter.onSuccess(value);
            } catch (Exception exception) {
                System.out.println(String.format("[RX-BLOCKING-ASYNC] cause", exception));
                emitter.onError(exception);
            }
        });
    }
}
