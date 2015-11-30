package cn.yxffcode.http.manager.lifecycle;

import cn.yxffcode.http.api.WebComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class DefaultLifeCycleManager implements LifeCycleManager {

    private final Object wrappersLock = new Object();
    private Map<Class<?>, WebComponentWrapper> wrappers =
            new HashMap<Class<?>, WebComponentWrapper>();

    public void destroy() {
        for (WebComponentWrapper webComponentWrapper : wrappers.values()) {
            webComponentWrapper.destroy();
        }
    }

    public WebComponent loadWebComponent(Class<?> type) {
        if (! wrappers.containsKey(type)) {
            synchronized (wrappersLock) {
                if (! wrappers.containsKey(type)) {
                    try {
                        WebComponentWrapper wrapper =
                                new WebComponentWrapper((WebComponent) type.newInstance());
                        wrapper.init();
                        wrappers.put(type, wrapper);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return wrappers.get(type)
                       .getWebComponent();
    }

    public void recycleWebComponent(WebComponent component) {
        WebComponentWrapper remove;
        synchronized (wrappersLock) {
            remove = wrappers.remove(component.getClass());
        }
        if (remove == null) {
            return;
        }
        remove.destroy();
    }
}
