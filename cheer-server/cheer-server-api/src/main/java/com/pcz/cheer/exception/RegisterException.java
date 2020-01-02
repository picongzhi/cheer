package com.pcz.cheer.exception;

import com.pcz.cheer.common.BaseException;
import com.pcz.cheer.common.Status;

/**
 * @author picongzhi
 */
public class RegisterException extends BaseException {
    public RegisterException(Status status) {
        super(status);
    }

    public RegisterException(Status status, Object data) {
        super(status, data);
    }

    public RegisterException(Integer code, String message) {
        super(code, message);
    }

    public RegisterException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
