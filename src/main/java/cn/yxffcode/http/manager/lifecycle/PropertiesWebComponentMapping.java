package cn.yxffcode.http.manager.lifecycle;

import cn.yxffcode.http.api.ResourceWebComponent;
import cn.yxffcode.http.manager.ApplicationConfiguration;

import java.util.Map;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class PropertiesWebComponentMapping implements WebComponentMapping {

    private final Map<String, Class<?>> mappings;

    public PropertiesWebComponentMapping() {
        this.mappings = ApplicationConfiguration.getInstance()
                                                .getWebComponentMappings();
    }

    public Class<?> map(String resource) {
        Class<?> type = mappings.get(resource);
        return type == null ?
               ResourceWebComponent.class :
               type;
    }
}
