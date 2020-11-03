package top.tangtian.week03.nio.outbound.httpClient4;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.tangtian.week03.nio.router.HttpEndpointRouter;
import top.tangtian.week03.nio.router.HttpEndpointRouterRandom;
import top.tangtian.week03.nio.utils.HttpclientUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author tangtian
 * @version 1.0
 * @className HttpOutboundHandler
 * @description
 * @date 2020/10/31 2:23 PM
 **/
public class HttpOutboundHandler {
    private static final Logger LOG = LoggerFactory.getLogger(HttpOutboundHandler.class);
    private CloseableHttpAsyncClient httpAsyncClient;
    private ExecutorService proxyService;
    private String backendUrl;

    public HttpOutboundHandler(List<String> endpoints){
        //运用路由实现简单的负载均衡
        HttpEndpointRouter httpEndpointRouter = new HttpEndpointRouterRandom();
        String backendUrl = httpEndpointRouter.route(endpoints);
        this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(0,backendUrl.length() - 1) : backendUrl;
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(cores,cores,keepAliveTime, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"),handler);

        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpAsyncClient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioReactorConfig)
                .setKeepAliveStrategy(((httpResponse, httpContext) -> 6000))
                .build();
        httpAsyncClient.start();
    }
    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext context){
        final String url = this.backendUrl + fullHttpRequest.uri();
        System.out.println(url);
        proxyService.submit(() -> fetchGet(fullHttpRequest,context,url));
    }

    private void fetchGet(final  FullHttpRequest inbound, final ChannelHandlerContext context, final String url){
        Map<String,String> headers = new HashMap();
        HttpHeaders httpHeaders = inbound.headers();
        //获取客户端请求头给真实的请求
        httpHeaders.names().stream().forEach(e -> headers.put(e, httpHeaders.get(e)));
        headers.put(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        //用上节课提交的请求工具(HttpclientUtils)请求
        httpAsyncClient.execute(HttpclientUtils.getServe(headers,url), new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse httpResponse) {
                httpResponse(inbound,context,httpResponse);
            }
            @Override
            public void failed(final Exception e) {
//                httpGet.abort();
                e.printStackTrace();
            }
            @Override
            public void cancelled() {
//                httpGet.abort();
            }
        });
    }

    private void httpResponse(final FullHttpRequest inbound, final ChannelHandlerContext context,final HttpResponse httpResponse) {
        FullHttpResponse response = null;
        try {
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
            response = new DefaultFullHttpResponse(HTTP_1_1,OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(httpResponse.getFirstHeader("Content-Length").getValue()));
        } catch (IOException e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(context, e);
        }finally {
            if (inbound != null) {
                if (!HttpUtil.isKeepAlive(inbound)) {
                    context.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    context.write(response);
                }
            }
            context.flush();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
