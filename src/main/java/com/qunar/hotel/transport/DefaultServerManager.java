package com.qunar.hotel.transport;

import com.qunar.hotel.bootstrap.Configuration;
import com.qunar.hotel.common.ExtensionLoaders;
import com.qunar.hotel.manager.EventDispatcherListener;
import com.qunar.hotel.manager.lifecycle.LifeCycleManager;
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
                .getExtension().createServer(configuration, new EventDispatcherListener());
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
            List<LifeCycleManager> extensions = ExtensionLoaders.getExtensionLoader(LifeCycleManager.class).getExtensions();
            for (LifeCycleManager extension : extensions) {
                extension.destroy();
            }
            server.stop();
        }
    }
}
