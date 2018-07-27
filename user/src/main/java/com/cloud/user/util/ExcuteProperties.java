package com.cloud.user.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author lwt
 * @date 2018/7/24 9:33
 */
public class ExcuteProperties {
    private static Properties properties;
    static {
        Resource resource = new ClassPathResource("Ini.properties");
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getPro() {
        return properties;
    }
}
