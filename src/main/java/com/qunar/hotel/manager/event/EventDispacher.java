package com.qunar.hotel.manager.event;

import com.qunar.hotel.api.ApplicationListener;
import com.qunar.hotel.api.RequestContext;
import com.qunar.hotel.api.RequestListener;

import java.util.List;

/**
 * Created by hang.gao on 2015/6/10.
 */
public interface EventDispacher {

    void addRequestListeners(List<ListenerWrapper<RequestListener>> listeners);

    void addApplicationListeners(List<ListenerWrapper<ApplicationListener>> listeners);

    void fireRequestStart(RequestContext ctx);

    void fireRequestEnd(RequestContext ctx);

    void fireApplicationInited();

    void fireApplicationDestroyed();

    void freeze();
}
