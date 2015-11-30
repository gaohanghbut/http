package com.qunar.hotel.common;

import java.util.List;

/**
 * Created by hang.gao on 2015/6/9.
 */
public interface ExtensionLoader<T> {
    List<T> getExtensions();

    T getExtension();
}
