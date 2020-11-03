package top.tangtian.week03.nio.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author tangtian
 * @version 1.0
 * @className HttpInboundServer
 * @description netty 的服务端
 *
 * 1.bootstrap为启动引导器。
 *
 * 2.指定了使用两个时间循环器。EventLoopGroup
 *
 * 3.指定使用Nio模式。（NioServerSocketChannel.class）
 *
 * 4.初始化器为HttpInboundInitializer
 * @date 2020/10/31 2:10 PM
 **/
public class HttpInboundServer {
    private static final Logger LOG = LoggerFactory.getLogger(HttpInboundServer.class);
    private int port;
    private List<String> proxyServer;
    public HttpInboundServer(int port, List<String> proxyServerList){
        this.port=port;
        this.proxyServer = proxyServerList;
    }
    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);
        try {

            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG,128)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.SO_REUSEADDR,true)
                    .option(ChannelOption.SO_RCVBUF,32 * 1024)
                    .option(ChannelOption.SO_SNDBUF,32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG)).childHandler(new HttpInboundInitializer(this.proxyServer));

            Channel ch = b.bind(new InetSocketAddress(port)).sync().channel();
            LOG.info("开启netty http服务器，监听的地址和端口为 http://127.0.0.1:" + port + '/');
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
