package top.tangtian.week03.nio.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author tangtian
 * @version 1.0
 * @className HttpEndpointRouterImpl
 * @description
 * @date 2020/11/3 7:06 AM
 **/
public class HttpEndpointRouterRandom implements HttpEndpointRouter {
    private static final Logger LOG = LoggerFactory.getLogger(HttpEndpointRouterRandom.class);
    static Random random = new Random();
    /**
     * 路由算法(随机法/加权随机法)
     * @param endpoints
     * @return
     */
    @Override
    public String route(List<String> endpoints) {
        return this.fullRandom(endpoints);
    }

    /**
     * 随机法
     */
    private String fullRandom(List<String> endpoints){
        int number = random.nextInt(endpoints.size());
        return endpoints.get(number);
    }
    /**
     * 加权随机法
     */
    private String weightRandom(List<String> endpoints){
        Map<String,Integer> endpointsMap = new HashMap<>();
        for (int i = 0; i < endpoints.size(); i++){
            endpointsMap.put(endpoints.get(i),i);
        }
        ArrayList ipList = new ArrayList();
        for (Map.Entry item : endpointsMap.entrySet()) {
            for (int i = 0; i < (Integer) item.getValue(); i++) {
                ipList.add(item.getKey());
            }
        }
        int allWeight = endpointsMap.values().stream().mapToInt(a -> a).sum();
        int number = random.nextInt(allWeight);
        return (String) ipList.get(number);
    }
}
