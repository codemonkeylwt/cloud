package com.cloud.user.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

/**
 * @author lwt
 * @date 2018/7/22 13:58
 */
public class UserSignUpUtil {
    private static ThreadLocal<Md5Hash> threadLocal = new ThreadLocal<>();
    private static final String DIC = ExcuteProperties.getPro().getProperty("DIC");
    private static final int ITERATIONS = Integer.valueOf(ExcuteProperties.getPro().getProperty("MD5.ITERATIONS"));
    private static final int SALT_LENGTH = Integer.valueOf(ExcuteProperties.getPro().getProperty("SALT_LENGTH"));

    public static String getHexPassword(String password) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SALT_LENGTH; i++) {
            stringBuilder.append(DIC.charAt(random.nextInt(DIC.length())));
        }
        Md5Hash md5Hash = new Md5Hash(password, stringBuilder.toString(), ITERATIONS);
        threadLocal.set(md5Hash);
        return md5Hash.toHex();
    }

    public static String getSalt() {
        String salt = new String(threadLocal.get().getSalt().getBytes(), Charset.forName("UTF-8"));
        threadLocal.remove();
        return salt;
    }

    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
