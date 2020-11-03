package top.tangtian.week03.nio.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangtian
 * @version 1.0
 * @className HttpRequestFilterImpl
 * @description
 * @date 2020/11/2 7:53 PM
 **/
public class HttpRequestFilterHeaders implements HttpRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(HttpRequestFilterHeaders.class);

    /**
     * 对客户端请求的过滤
     * @param fullHttpRequest
     * @param ctx
     */
    @Override
    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        //通过拦截器给客户端请求增加自定义头
        fullHttpRequest.headers().set("nio","tangtian");
    }
}
