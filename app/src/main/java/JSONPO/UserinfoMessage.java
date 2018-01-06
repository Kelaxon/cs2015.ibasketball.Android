package JSONPO;

/**
 * Created by 莫林立 on 2018/1/4.
 */

public class UserinfoMessage {
    private Userinfo currentUser;
    private String message;

    public Userinfo getCurrentUser() {
        return currentUser;
    }

    public String getMessage() {
        return message;
    }

    public void setCurrentUser(Userinfo currentUser) {
        this.currentUser = currentUser;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserinfoMessage{" +
                "currentUser=" + currentUser +
                ", message='" + message + '\'' +
                '}';
    }

}
