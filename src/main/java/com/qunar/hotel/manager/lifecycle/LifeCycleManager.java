package com.qunar.hotel.manager.lifecycle;

import com.qunar.hotel.api.WebComponent;

/**
 * Created by hang.gao on 2015/6/9.
 */
public interface LifeCycleManager {
    void destroy();

    WebComponent loadWebComponent(Class<?> type);

    void recycleWebComponent(WebComponent component);
}
