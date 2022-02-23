package responses;

/**
 * a frequently used response class for services. A few of the services eseentially return the same thing, this is a
 * response for them.
 */

/**
 * String message - the message for the response. If the service was successful then it returns a message saying so.
 *  If the service was not successful then an error message is included with a related error.
 */
public class defaultResponse {
    private String message;
    private boolean success;

    public defaultResponse(String message, boolean success){
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
