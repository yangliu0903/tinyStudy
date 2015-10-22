package org.tinygroup.dtslaunch.exception;

public class TransactionFailException extends RuntimeException {

    String    msg;
    Throwable cause;

    /**
     * @param string
     */
    public TransactionFailException(String msg) {
        this.msg = msg;
    }

    public TransactionFailException(String msg, Throwable e) {
        this.msg = msg;
        this.cause = e;
    }

    public String getMessage() {
        return msg;
    }

    public Throwable getCause() {
        return cause;
    }

    /**  */
    private static final long serialVersionUID = 3131449184945578458L;

}
