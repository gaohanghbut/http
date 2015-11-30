package cn.yxffcode.http.transport.netty;

import cn.yxffcode.http.transport.RequestHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hang.gao on 2015/6/8.
 */
public class NettyChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyChannelHandler.class);

    private final RequestHandler requestHandler;

    public NettyChannelHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) {
        if (msg instanceof HttpRequest) {
            NettyRequestContext requestContext = NettyRequestContext.from((HttpRequest) msg, ctx.channel());
            //handle request
            try {
                requestHandler.handleRequest(requestContext);
            } catch (Throwable throwable) {
                logger.error("handle request error", throwable);
                StringBuilder sb = new StringBuilder("<h1>500</h1><br>");
                sb.append(throwable.getClass()
                                   .getName())
                  .append(":")
                  .append(throwable.getMessage())
                  .append("<br>");
                for (StackTraceElement stackTrace : throwable.getStackTrace()) {
                    sb.append(stackTrace)
                      .append("<br>");
                }
                DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                                               HttpResponseStatus.INTERNAL_SERVER_ERROR,
                                                                               Unpooled.wrappedBuffer(sb.toString()
                                                                                                        .getBytes()));
                response.headers()
                        .set("content-type", requestContext.getContentType());
                response.headers()
                        .set("content-length", response.content()
                                                       .readableBytes());
                ctx.write(response)
                   .addListener(ChannelFutureListener.CLOSE);
                return;
            }
            DefaultFullHttpResponse response;
            if (! requestContext.isError()) {
                response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                       HttpResponseStatus.OK,
                                                       Unpooled.wrappedBuffer(requestContext.getResponseContent()));
            } else {
                response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                       requestContext.getResponseStatus(),
                                                       Unpooled.wrappedBuffer(requestContext.getResponseContent()));
            }
            response.headers()
                    .set("content-type", requestContext.getContentType());
            response.headers()
                    .set("content-length", response.content()
                                                   .readableBytes());
            ctx.write(response)
               .addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
