package cn.yxffcode.http.proxy;

import cn.yxffcode.http.api.WebComponent;
import cn.yxffcode.http.common.ExtensionLoaders;
import cn.yxffcode.http.manager.lifecycle.LifeCycleManager;
import cn.yxffcode.http.manager.lifecycle.WebComponentMapping;
import cn.yxffcode.http.api.RequestContext;
import cn.yxffcode.http.manager.lifecycle.CompositeWebComponentMapping;

import java.io.IOException;
import java.util.List;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class DefaultInvoker implements Invoker {

    private final LifeCycleManager lifeCycleManager;

    private final WebComponentMapping webComponentMapping;

    public DefaultInvoker() {
        List<LifeCycleManager> managers = ExtensionLoaders.
                                                                  getExtensionLoader(LifeCycleManager.class)
                                                          .getExtensions();
        if (managers.size() == 1) {
            lifeCycleManager = managers.get(0);
        } else {
            throw new RuntimeException("LifeCycleManager more than one");
        }
        List<WebComponentMapping> mappings = ExtensionLoaders.
                                                                     getExtensionLoader(WebComponentMapping.class)
                                                             .getExtensions();
        if (managers.size() == 1) {
            webComponentMapping = mappings.get(0);
        } else {
            webComponentMapping = new CompositeWebComponentMapping(mappings);
        }
    }

    public void invoke(RequestContext ctx) throws IOException {
        WebComponent webComponent = lifeCycleManager.
                                                            loadWebComponent(
                                                                    webComponentMapping.map(ctx.getRequestUri()));
        webComponent.onRequest(ctx);
    }
}
