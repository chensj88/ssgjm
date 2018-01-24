package cn.com.winning.ssgj.base.exception;

public class SSGJException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SSGJException(String message) {
        super(message);
    }

    public SSGJException() {
        super();
    }

    public SSGJException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SSGJException(String message, Throwable cause) {
        super(message, cause);
    }

    public SSGJException(Throwable cause) {
        super(cause);
    }


}
