package JDBC.Beans;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by ChrisYoung on 2017/12/26.
 *
 * DAO接口
 */

public class Announceinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int announceId;
    private String announceTitle;
    private String announceContent;
    private Timestamp announceTime;
    private int userId;

    public int getAnnounceId() {
        return announceId;
    }

    public void setAnnounceId(int announceId) {
        this.announceId = announceId;
    }

    public String getAnnounceTitle() {
        return announceTitle;
    }

    public void setAnnounceTitle(String announceTitle) {
        this.announceTitle = announceTitle;
    }

    public String getAnnounceContent() {
        return announceContent;
    }

    public void setAnnounceContent(String announceContent) {
        this.announceContent = announceContent;
    }

    public Timestamp getAnnounceTime() {
        return announceTime;
    }

    public void setAnnounceTime(Timestamp announceTime) {
        this.announceTime = announceTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
