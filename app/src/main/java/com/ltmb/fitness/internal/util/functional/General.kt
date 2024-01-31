package com.ltmb.fitness.internal.util.functional

fun <T> lazyThreadSafetyNone(initializer: () -> T):
        Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)