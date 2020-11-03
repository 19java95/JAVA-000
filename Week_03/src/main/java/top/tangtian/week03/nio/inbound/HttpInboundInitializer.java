package top.tangtian.week03.nio.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author tangtian
 * @version 1.0
 * @className HttpInboundInitializer
 * @description
 * @date 2020/10/31 2:02 PM
 **/
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger LOG = LoggerFactory.getLogger(HttpInboundInitializer.class);
    private List<String> proxyServer;
    public HttpInboundInitializer(List<String> proxyServerList){
        this.proxyServer = proxyServerList;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024*1024));
        p.addLast(new HttpInboundHandler(this.proxyServer));
    }
}
