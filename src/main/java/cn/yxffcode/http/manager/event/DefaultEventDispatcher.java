package cn.yxffcode.http.manager.event;

import cn.yxffcode.http.api.RequestListener;
import cn.yxffcode.http.api.ApplicationListener;
import cn.yxffcode.http.api.RequestContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hang.gao on 2015/6/10.
 */
public class DefaultEventDispatcher implements EventDispacher {

    private List<ListenerWrapper<RequestListener>> requestListeners;

    private List<ListenerWrapper<ApplicationListener>> applicationListeners;

    private ExecutorService exec = Executors.newSingleThreadExecutor();

    private volatile boolean frozen;

    public void addRequestListeners(List<ListenerWrapper<RequestListener>> listeners) {
        checkState();
        requestListeners = listeners;
    }

    private void checkState() {
        if (frozen) {
            throw new IllegalStateException("the dispacher has been frozed");
        }
    }

    public void addApplicationListeners(List<ListenerWrapper<ApplicationListener>> listeners) {
        applicationListeners = listeners;
    }

    public void fireRequestStart(final RequestContext ctx) {
        for (final ListenerWrapper<RequestListener> requestListener : requestListeners) {
            if (! requestListener.sync) {
                exec.submit(new Runnable() {
                    public void run() {
                        requestListener.listener.onRequestStart(ctx);
                    }
                });
            }
        }
        for (ListenerWrapper<RequestListener> requestListener : requestListeners) {
            if (requestListener.sync) {
                requestListener.listener.onRequestStart(ctx);
            }
        }
    }

    public void fireRequestEnd(final RequestContext ctx) {
        for (final ListenerWrapper<RequestListener> requestListener : requestListeners) {
            if (! requestListener.sync) {
                exec.submit(new Runnable() {
                    public void run() {
                        requestListener.listener.onRequestEnd(ctx);
                    }
                });
            }
        }
        for (ListenerWrapper<RequestListener> requestListener : requestListeners) {
            if (requestListener.sync) {
                requestListener.listener.onRequestEnd(ctx);
            }
        }
    }

    public void fireApplicationInited() {
        for (final ListenerWrapper<ApplicationListener> applicationListener : applicationListeners) {
            if (! applicationListener.sync) {
                exec.submit(new Runnable() {
                    public void run() {
                        applicationListener.listener.onApplicationInit();
                    }
                });
            }
        }
        for (final ListenerWrapper<ApplicationListener> applicationListener : applicationListeners) {
            if (applicationListener.sync) {
                applicationListener.listener.onApplicationInit();
            }
        }
    }

    public void fireApplicationDestroyed() {
        for (final ListenerWrapper<ApplicationListener> applicationListener : applicationListeners) {
            if (! applicationListener.sync) {
                exec.submit(new Runnable() {
                    public void run() {
                        applicationListener.listener.onApplicationDestroy();
                    }
                });
            }
        }
        for (final ListenerWrapper<ApplicationListener> applicationListener : applicationListeners) {
            if (applicationListener.sync) {
                applicationListener.listener.onApplicationDestroy();
            }
        }
        exec.shutdown();
    }

    public void freeze() {
        frozen = true;
    }

}
