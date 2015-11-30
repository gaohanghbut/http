package cn.yxffcode.http.manager;

import cn.yxffcode.http.common.ExtensionLoaders;
import cn.yxffcode.http.manager.event.EventDispacher;

/**
 * Created by hang.gao on 2015/6/10.
 */
public class EventDispatcherListener implements ServerLifeCycleListener {
    public void beforeServerStop() {
        ExtensionLoaders.getExtensionLoader(EventDispacher.class)
                        .getExtension()
                        .fireApplicationDestroyed();
    }

    public void afterServerStart() {
        EventDispacher eventDispacher = ExtensionLoaders.getExtensionLoader(EventDispacher.class)
                                                        .getExtension();
        eventDispacher.addApplicationListeners(ApplicationConfiguration.getInstance()
                                                                       .getApplicationListener());
        eventDispacher.addRequestListeners(ApplicationConfiguration.getInstance()
                                                                   .getRequestListeners());
        eventDispacher.freeze();
        eventDispacher.fireApplicationInited();
    }
}
