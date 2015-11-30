package com.qunar.hotel.manager.lifecycle;

import com.qunar.hotel.api.ResourceWebComponent;
import com.qunar.hotel.manager.ApplicationConfiguration;

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
