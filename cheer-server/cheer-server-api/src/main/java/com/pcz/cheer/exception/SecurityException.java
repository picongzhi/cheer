package com.pcz.cheer.exception;

import com.pcz.cheer.common.BaseException;
import com.pcz.cheer.common.Status;

/**
 * @author picongzhi
 */
public class SecurityException extends BaseException {
    public SecurityException(Status status) {
        super(status);
    }

    public SecurityException(Status status, Object data) {
        super(status, data);
    }

    public SecurityException(Integer code, String message) {
        super(code, message);
    }

    public SecurityException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
