package com.pingpongx.fx;

/**
 * @author zhengkk
 * @since 2019-03-18 14:28
 **/
public class MessageNotFitException extends RuntimeException {

    public MessageNotFitException() {
        super("message type not fit");
    }

    public MessageNotFitException(String message) {
        super(message);
    }

    public MessageNotFitException(String message, Throwable cause) {
        super(message, cause);
    }
}
