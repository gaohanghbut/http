package com.qunar.hotel.transport.netty;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by hang.gao on 2015/6/8.
 */
public class HttpGetRequestContext extends NettyRequestContext {

    private static final Logger logger = LoggerFactory.getLogger(NettyRequestContext.class);

    private final QueryStringDecoder decoder;

    private String resource;

    HttpGetRequestContext(HttpRequest request,
                          Channel channel) {
        super(request, channel);
        decoder = new QueryStringDecoder(request.getUri());
    }

    public String getParameter(String name) {
        List<String> params = decoder.parameters()
                                     .get(name);
        if (params == null || params.size() == 0) {
            return null;
        }
        return params.get(0);
    }

    public String getRequestUri() {
        if (resource != null) {
            return resource;
        }
        String uri   = getRequest().getUri();
        int    index = uri.indexOf('?');
        if (index < 0) {
            resource = uri;
        } else {
            resource = uri.substring(0, index);
        }
        return resource;
    }
}
