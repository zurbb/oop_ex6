package oop.ex6.main.exception;


/**
 * Base exception
 *
 *
 * @author itayyamin zurbb
 */
public abstract class BaseException extends Exception {

    private String message;

    public BaseException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}