package JDBC.Beans;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class Usercollectnews implements Serializable {

    private static final long serialVersionUID = 1L;
    private int ucnId;
    private Timestamp collectionTime;
    private int userId;
    private int newsId;

    public int getUcnId() {
        return ucnId;
    }

    public void setUcnId(int ucnId) {
        this.ucnId = ucnId;
    }

    public Timestamp getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Timestamp collectionTime) {
        this.collectionTime = collectionTime;
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
