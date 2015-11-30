package com.qunar.hotel.bootstrap;

import com.qunar.hotel.common.ExtensionLoaders;
import com.qunar.hotel.transport.ServerManager;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class Bootstrap {

    private Configuration configuration;

    private ServerManager serverManager;

    public Bootstrap config(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    public void bind() {
        serverManager = ExtensionLoaders.getExtensionLoader(ServerManager.class).getExtension();
        serverManager.serverStart(configuration);
    }

    public static void main(String[] args) {
        new Bootstrap().config(new SimpleConfiguration()).bind();
    }
}
