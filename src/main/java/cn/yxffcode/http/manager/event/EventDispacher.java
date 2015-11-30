package cn.yxffcode.http.manager.event;

import cn.yxffcode.http.api.ApplicationListener;
import cn.yxffcode.http.api.RequestListener;
import cn.yxffcode.http.api.RequestContext;

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
