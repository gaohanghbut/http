package cn.yxffcode.http.transport.netty;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/8.
 */
public class HttpPostRequestContext extends NettyRequestContext {

    private static final Logger logger = LoggerFactory.getLogger(NettyRequestContext.class);

    private final HttpPostRequestDecoder decoder;

    HttpPostRequestContext(HttpRequest request,
                           Channel channel) {
        super(request, channel);
        decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
    }

    public String getParameter(String name) {
        InterfaceHttpData param = decoder.getBodyHttpData(name);
        if (param.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
            Attribute attribute = (Attribute) param;
            try {
                return attribute.getValue();
            } catch (IOException e) {
                logger.error("get parameter error,parameter name is {}", name, e);
            }
        }
        return null;
    }

    public String getRequestUri() {
        return getRequest().getUri();
    }
}
