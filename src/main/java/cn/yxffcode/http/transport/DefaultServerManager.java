package cn.yxffcode.http.transport;

import cn.yxffcode.http.common.ExtensionLoaders;
import cn.yxffcode.http.manager.EventDispatcherListener;
import cn.yxffcode.http.manager.lifecycle.LifeCycleManager;
import cn.yxffcode.http.bootstrap.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by hang.gao on 2015/6/10.
 */
public class DefaultServerManager implements ServerManager {

    private static final Logger logger = LoggerFactory.getLogger(DefaultServerManager.class);

    private Server server;

    public void serverStart(Configuration configuration) {
        Server server = ExtensionLoaders.getExtensionLoader(ServerFactory.class)
                                        .getExtension()
                                        .createServer(configuration, new EventDispatcherListener());
        this.server = server;
        try {
            logger.info("server is starting...");
            server.start();
        } catch (Exception e) {
            logger.error("start server error", e);
            serverStop();
        }
    }

    public void serverStop() {
        if (server != null) {
            //destroy all web components
            List<LifeCycleManager> extensions = ExtensionLoaders.getExtensionLoader(LifeCycleManager.class)
                                                                .getExtensions();
            for (LifeCycleManager extension : extensions) {
                extension.destroy();
            }
            server.stop();
        }
    }
}
