package org.example.framework;

import org.example.framework.helper.*;
import org.example.framework.util.ClassUtil;

public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls: classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
