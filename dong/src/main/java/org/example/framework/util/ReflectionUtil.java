package org.example.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public final class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object getInstance(Class<?> cls) {
        Object o = null;
        try {
            o = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("实例化失败", e);
            throw new RuntimeException(e);
        }
        return o;
    }

    public static Object invokeMethod(Object obj, Method method, Object ...args) {
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            LOGGER.error("方法访问异常", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            LOGGER.error("方法调用失败", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("设置成员变量失败", e);
            throw new RuntimeException(e);
        }
    }
}
