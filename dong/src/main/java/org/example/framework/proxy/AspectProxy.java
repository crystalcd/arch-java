package org.example.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public abstract class AspectProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] methodParams = proxyChain.getMethodParams();
        beign();
        try {
            if (intercept(cls, method, methodParams)) {
                before(cls, method, methodParams);
                result = proxyChain.doProxyChain();
                after(cls, method, methodParams);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Throwable throwable) {
            LOGGER.error("代理失败", throwable);
            error(cls, method, methodParams);
            throw throwable;
        } finally {
            end();
        }
        return result;
    }

    public void beign() {

    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) {
        return true;
    }

    public void before(Class<?> cls, Method method, Object[] params) {

    }

    public void after(Class<?> cls, Method method, Object[] params) {

    }

    public void error(Class<?> cls, Method method, Object[] params) {

    }

    public void end() {

    }
}

