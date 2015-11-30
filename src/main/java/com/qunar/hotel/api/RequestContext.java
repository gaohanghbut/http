package com.qunar.hotel.api;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/8.
 */
public interface RequestContext {

    String getParameter(String name);

    String getRemoteAddress();

    String getRequestUri();

    void writeString(String content) throws IOException;

    void error(int code, String msg);

    void setContentType(String contentType);

    boolean isError();

    String getContentType();
}
