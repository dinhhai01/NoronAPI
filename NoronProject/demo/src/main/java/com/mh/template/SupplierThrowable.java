package com.mh.template;

@FunctionalInterface
public interface SupplierThrowable<T> {
    T get() throws Exception;
}
