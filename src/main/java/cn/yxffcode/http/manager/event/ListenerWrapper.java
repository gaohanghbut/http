package cn.yxffcode.http.manager.event;

/**
 * Created by hang.gao on 2015/6/10.
 */
public final class ListenerWrapper<T> {
    public final T       listener;
    public final boolean sync;

    public ListenerWrapper(T listener,
                           boolean sync) {
        this.listener = listener;
        this.sync = sync;
    }
}