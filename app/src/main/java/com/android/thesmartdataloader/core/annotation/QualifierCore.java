package com.android.thesmartdataloader.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

public class QualifierCore {
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface JsonRepo {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MemoryRepo {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RoomRepo {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface BackgroundTasks {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LightweightTasks {}
}
