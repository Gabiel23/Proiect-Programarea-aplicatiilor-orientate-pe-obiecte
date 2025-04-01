package main;

public class ImageSetupException extends Exception {
    public ImageSetupException(String message) {
        super(message);
    }

    public ImageSetupException(String message, Throwable cause) {
        super(message, cause);
    }
}
