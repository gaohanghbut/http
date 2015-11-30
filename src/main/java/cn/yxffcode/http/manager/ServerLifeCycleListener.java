package cn.yxffcode.http.manager;

/**
 * Created by hang.gao on 2015/6/10.
 */
public interface ServerLifeCycleListener {
    void beforeServerStop();

    void afterServerStart();
}
