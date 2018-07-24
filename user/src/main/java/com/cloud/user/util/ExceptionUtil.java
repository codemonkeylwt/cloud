package com.cloud.user.util;

/**
 * @author lwt
 * @date 2018/7/23 17:43
 */
public class ExceptionUtil {
    public static String getExceptionInfo(Exception e) {
        e.printStackTrace();
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Cause:").append(e.getCause().toString());
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.getClassName().startsWith("com.cloud") && stackTraceElement.getFileName().endsWith(".java")) {
                stringBuilder.append("  ClassName:").append(stackTraceElement.getClassName());
                stringBuilder.append("  MethodName:").append(stackTraceElement.getMethodName());
                stringBuilder.append("  LineNumber:").append(stackTraceElement.getLineNumber());
            }
        }
        return stringBuilder.toString();
    }
}
