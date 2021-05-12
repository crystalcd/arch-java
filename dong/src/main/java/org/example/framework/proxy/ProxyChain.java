package org.example.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ProxyChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyChain.class);

    private final Class<?> targetClass;

    private final Object targetObject;

    private final Method targetMethod;

    private final MethodProxy methodProxy;

    private final Object[] methodParams;

    private List<Proxy> proxyList = new ArrayList<>();

    private int proxyIndex = 0;

    public ProxyChain(Class<?> targetClass,
                      Object targetObject,
                      Method targetMethod,
                      MethodProxy methodProxy,
                      Object[] methodParams,
                      List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
        LOGGER.debug("proxyList:{}",proxyList.toString());
    }

    public Object[] getMethodParams() {
        return this.methodParams;
    }

    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public Method getTargetMethod() {
        return this.targetMethod;
    }

    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()) {
            methodResult = this.proxyList.get(proxyIndex++).doProxy(this);
        }else {
            // 执行目标对象的业务逻辑
            methodResult = this.methodProxy.invokeSuper(this.targetObject,this.methodParams);
        }
        return methodResult;
    }
}
