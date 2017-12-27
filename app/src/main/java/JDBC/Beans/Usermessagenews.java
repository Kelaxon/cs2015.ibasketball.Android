package JDBC.Beans;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class Usermessagenews implements Serializable {
    private static final long serialVersionUID = 1L;
    private int umnId;
    private Timestamp messageTime;
    private String messageContent;
    private int userId;
    private int newsId;

    public int getUmnId() {
        return umnId;
    }

    public void setUmnId(int umnId) {
        this.umnId = umnId;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }


    public int getUserId() {
        return userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
