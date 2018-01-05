package JSONPO;

/**
 * Created by 莫林立 on 2018/1/4.
 */

public class UserinfoMessage {
    private Userinfo userinfo;
    private String message;

    public String getMessage() {
        return message;
    }

    public Userinfo getUserinfo() {

        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserinfoMessage{" +
                "userinfo=" + userinfo +
                ", message='" + message + '\'' +
                '}';
    }
}
