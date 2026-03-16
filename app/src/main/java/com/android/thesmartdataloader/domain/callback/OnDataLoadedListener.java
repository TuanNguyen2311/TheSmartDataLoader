package com.android.thesmartdataloader.domain.callback;

import java.util.List;

public interface OnDataLoadedListener<V> {
    void onLoaded(List<V> result);
}
