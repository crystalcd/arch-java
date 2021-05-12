package org.example.framework.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.example.framework.annotation.Inject;
import org.example.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * 依赖注入api
 */
public final class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (!beanMap.isEmpty()) {
            for (Map.Entry<Class<?>, Object> beanEntry: beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    for (Field beanField: beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldType = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldType);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanFieldInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
