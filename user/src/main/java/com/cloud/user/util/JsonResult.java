package com.cloud.user.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author lwt
 * @date 2018/7/23 17:31
 */
@Getter
@Setter
@Accessors(chain = true)
public class JsonResult {
    private boolean isSuccess = true;
    private String msg = "正常";
    private Object result;
}
