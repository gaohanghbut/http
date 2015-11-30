package cn.yxffcode.http.manager.lifecycle;

import cn.yxffcode.http.api.ResourceWebComponent;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class TestWebComponentMapping implements WebComponentMapping {
    public Class<?> map(String resource) {
        return ResourceWebComponent.class;
    }
}
