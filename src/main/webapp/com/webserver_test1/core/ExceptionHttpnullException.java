package main.webapp.com.webserver_test1.core;

public class ExceptionHttpnullException extends Exception{

    public ExceptionHttpnullException() {
        super();
    }

    public ExceptionHttpnullException(String message) {
        super(message);
    }

    public ExceptionHttpnullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionHttpnullException(Throwable cause) {
        super(cause);
    }

    protected ExceptionHttpnullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
