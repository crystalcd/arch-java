package org.example.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置加载工具类
 */
public final class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName){
        Properties properties = new Properties();
        InputStream in = PropsUtil.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error("加载配置文件报错",e);
        }
        return properties;
    }

    public static String getString(Properties props, String propName) {
        return props.getProperty(propName);
    }

    public static String getString(Properties props, String propName, String defaultValue) {
        return props.getProperty(propName, defaultValue);
    }
}
