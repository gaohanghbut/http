package com.qunar.hotel.transport.netty;

import com.qunar.hotel.transport.AbstractRequestContext;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Created by hang.gao on 2015/6/8.
 */
public abstract class NettyRequestContext extends AbstractRequestContext {

    private final HttpRequest request;
    private final Channel channel;
    private HttpResponseStatus responseStatus;

    protected NettyRequestContext(HttpRequest request,
                                  Channel channel) {
        this.request = request;
        this.channel = channel;
    }

    public static NettyRequestContext from(HttpRequest req,
                                           Channel channel) {
        if (req.getMethod() == HttpMethod.POST) {
            return new HttpPostRequestContext(req, channel);
        } else if (req.getMethod() == HttpMethod.GET) {
            return new HttpGetRequestContext(req, channel);
        }
        return null;
    }

    protected void setAttachments(Map<String, Object> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String getRemoteAddress() {
        return ((InetSocketAddress) channel.remoteAddress()).getAddress()
                                                            .getHostAddress();
    }

    public void error(int code,
                      String msg) {
        super.error(code, msg);
        responseStatus = HttpResponseStatus.valueOf(code);
    }

    public HttpResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public HttpRequest getRequest() {
        return request;
    }
}
