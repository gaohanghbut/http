package com.qunar.hotel.common;

import java.io.File;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class WebAppConstants {
    private WebAppConstants() {
    }

    public static final String WEBAPP_BASE = "webapp" + File.separatorChar;

    public static final String RESOURCE_BASE = WEBAPP_BASE + "webroot" + File.separatorChar;

    public static final String COMPONENT_MAPPING_CONF = WEBAPP_BASE + "mapping.conf";
}
