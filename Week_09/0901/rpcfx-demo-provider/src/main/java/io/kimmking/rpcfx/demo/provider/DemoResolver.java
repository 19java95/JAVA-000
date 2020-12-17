package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.annotation.RpcfxService;
import io.kimmking.rpcfx.api.RpcfxResolver;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DemoResolver implements RpcfxResolver {

    private final static Map<String, Object> SERVICE_CACHE = new ConcurrentHashMap<>();

    @Override
    public Object resolve(String serviceClass) {
        return SERVICE_CACHE.get(serviceClass);
    }

    @Override
    public void initService(String servicePath) {
        Reflections reflections = new Reflections(servicePath);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(RpcfxService.class);
        classes.forEach(c -> {
            try {
                SERVICE_CACHE.put(c.getInterfaces()[0].getName(), c.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
