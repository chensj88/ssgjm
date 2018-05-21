package cn.com.winning.ssgj.base.exception;

public class SSGJException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String responseMsg;

    private String responseCode;

    public SSGJException(String message) {
        super(message);
    }
    public SSGJException(String message,String responseCode,String responseMsg) {
        super(message);
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
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


    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
