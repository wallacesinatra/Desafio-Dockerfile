package estudo.desafio.exeptions;

public class RequiredFieldMissingException extends RuntimeException{

    public RequiredFieldMissingException() {
    }

    public RequiredFieldMissingException(String message) {
        super(message);
    }

    public RequiredFieldMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredFieldMissingException(Throwable cause) {
        super(cause);
    }

    public RequiredFieldMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
