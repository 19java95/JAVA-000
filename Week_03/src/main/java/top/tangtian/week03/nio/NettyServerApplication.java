package top.tangtian.week03.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.tangtian.week03.nio.inbound.HttpInboundServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangtian
 * @version 1.0
 * @className NettyServerApplication
 * @description
 * @date 2020/10/31 5:49 PM
 **/
public class NettyServerApplication {
    private static final Logger LOG = LoggerFactory.getLogger(NettyServerApplication.class);
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        String proxyServer1 = System.getProperty("proxyServer1","http://localhost:8088");//真实地址1
        String proxyServer2 = System.getProperty("proxyServer2","http://localhost:8089");//真实地址2
        String proxyServer3 = System.getProperty("proxyServer3","http://localhost:8090");//真实地址3
        String proxyPort = System.getProperty("proxyPort","8888");//代理地址

        //  http://localhost:8888/api/hello  ==> gateway API
        //  http://localhost:8088/api/hello  ==> backend service

        int port = Integer.parseInt(proxyPort);
        List<String> proxyServerList = new ArrayList<>();
        proxyServerList.add(proxyServer1);
        proxyServerList.add(proxyServer2);
        proxyServerList.add(proxyServer3);
        HttpInboundServer server = new HttpInboundServer(port, proxyServerList);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + proxyServerList);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
