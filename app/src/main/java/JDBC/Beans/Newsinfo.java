package JDBC.Beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class Newsinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int newsId;
    private String newsTitle;
    private String newsContent;
    private Timestamp newsTime;
    private String newsPic;
    private int newsCount;
    private String magId;

    public void setMagId(String magId) {
        this.magId = magId;
    }

    public String getMagId() {
        return magId;

    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Timestamp getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Timestamp newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsPic() {
        return newsPic;
    }

    public void setNewsPic(String newsPic) {
        this.newsPic = newsPic;
    }

    public int getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(int newsCount) {
        this.newsCount = newsCount;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
