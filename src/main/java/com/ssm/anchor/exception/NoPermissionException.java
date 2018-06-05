package com.ssm.anchor.exception;

//没有权限异常
public class NoPermissionException extends Exception {
    public NoPermissionException(String message) {
        super(message);
    }
}
