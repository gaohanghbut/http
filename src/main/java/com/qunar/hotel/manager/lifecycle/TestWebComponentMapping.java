package com.qunar.hotel.manager.lifecycle;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class TestWebComponentMapping implements WebComponentMapping {
    public Class<?> map(String resource) {
        return com.qunar.hotel.api.ResourceWebComponent.class;
    }
}
