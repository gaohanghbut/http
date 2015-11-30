package com.qunar.hotel.manager.lifecycle;

import com.qunar.hotel.api.WebComponent;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class WebComponentWrapper implements LifeCycle {
    private final WebComponent webComponent;

    public WebComponentWrapper(WebComponent webComponent) {
        this.webComponent = webComponent;
    }

    public void init() {
        webComponent.init();
    }

    public void destroy() {
        webComponent.destroy();
    }

    public WebComponent getWebComponent() {
        return webComponent;
    }
}
