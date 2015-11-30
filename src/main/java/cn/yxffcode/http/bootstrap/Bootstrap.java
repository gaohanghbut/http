package cn.yxffcode.http.bootstrap;

import cn.yxffcode.http.common.ExtensionLoaders;
import cn.yxffcode.http.transport.ServerManager;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class Bootstrap {

    private Configuration configuration;

    private ServerManager serverManager;

    public static void main(String[] args) {
        new Bootstrap().config(new SimpleConfiguration())
                       .bind();
    }

    public void bind() {
        serverManager = ExtensionLoaders.getExtensionLoader(ServerManager.class)
                                        .getExtension();
        serverManager.serverStart(configuration);
    }

    public Bootstrap config(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
}
