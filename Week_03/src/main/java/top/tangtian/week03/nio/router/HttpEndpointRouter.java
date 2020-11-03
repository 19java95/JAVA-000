package top.tangtian.week03.nio.router;

import java.util.List;

/**
 * @author tangtian
 * @version 1.0
 * @className HttpEndpointRouter
 * @description
 * @date 2020/10/31 5:46 PM
 **/
public interface HttpEndpointRouter {
    String route(List<String> endpoints);
}
