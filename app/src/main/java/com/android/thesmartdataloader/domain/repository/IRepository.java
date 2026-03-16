package com.android.thesmartdataloader.domain.repository;

import java.util.List;

public interface IRepository<V> {
    void add(V value);
    void clearAll();
    List<V> getData();
}
