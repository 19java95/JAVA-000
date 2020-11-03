package top.tangtian.week03.nio.inbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.tangtian.week03.nio.filter.HttpRequestFilter;
import top.tangtian.week03.nio.filter.HttpRequestFilterHeaders;
import top.tangtian.week03.nio.outbound.httpClient4.HttpOutboundHandler;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author tangtian
 * @version 1.0
 * @className HttpInboundHandler
 * @description
 * @date 2020/10/31 1:45 PM
 **/
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

  private static final Logger LOG = LoggerFactory.getLogger(HttpInboundHandler.class);
  private final List<String> proxyServer;
  private HttpOutboundHandler handler;

  public HttpInboundHandler (List<String> proxyServerList) {
    this.proxyServer = proxyServerList;
    handler = new HttpOutboundHandler(this.proxyServer);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext context){
    context.flush();
  }
  @Override
  public void channelRead(ChannelHandlerContext context, Object msg){
    LOG.info("channelRead流量接口请求开始");
    FullHttpRequest fullHttpRequest = (FullHttpRequest)msg;
    String uri = fullHttpRequest.uri();
    LOG.info("接收到的请求URL为{}",uri);
      //增加过滤器
    HttpRequestFilter httpRequestFilter = new HttpRequestFilterHeaders();
    httpRequestFilter.filter(fullHttpRequest,context);
    handler.handle(fullHttpRequest,context);
  }

  private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            String value = "hello,kimmking";
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            LOG.error("处理测试接口出错", e);
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
