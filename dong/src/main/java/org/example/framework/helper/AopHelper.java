package org.example.framework.helper;

import org.example.framework.annotation.Aspect;
import org.example.framework.proxy.AspectProxy;
import org.example.framework.proxy.Proxy;
import org.example.framework.proxy.ProxyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

public final class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry: targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.setBean(targetClass,proxy);
            }
        } catch (Exception e) {
            LOGGER.error("aop失败");
        }
    }

    /**
     * 获取所有带有@Aspect注解但是value不是Aspcet.class的类
     * @param aspect
     * @return
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) {
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation!=null && !annotation.equals(Aspect.class)) {
            targetClassSet.addAll(ClassHelper.getClassByAnnotation(annotation));
        }
        return targetClassSet;
    }

    /**
     * 获取代理类和目标类的映射
     * @return
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap() {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass: proxyClassSet) {
            Aspect aspect = proxyClass.getAnnotation(Aspect.class);
            Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
            proxyMap.put(proxyClass,targetClassSet);
        }
        return proxyMap;
    }

    /**
     * 返回目标类和所有该类的增强类的映射
     * @param proxyMap
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws IllegalAccessException, InstantiationException {
        Map<Class<?>,List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry: proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for (Class<?> targetClass: targetClassSet) {
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                }else {
                    List<Proxy> list = new ArrayList<>();
                    list.add(proxy);
                    targetMap.put(targetClass, list);
                }
            }
        }
        return targetMap;
    }
}
