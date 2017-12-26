package JDBC.Beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class Newsinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String newsId;
    private String newsTitle;
    private String newsContent;
    private Timestamp newsTime;
    private String newsPic;
    private int newsCount;
    private Managerinfo managerinfo;
    private Set<Usermessagenew> usermessagenews;
    private Set<Usercollectnew> usercollectnews;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
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

    public Managerinfo getManagerinfo() {
        return managerinfo;
    }

    public void setManagerinfo(Managerinfo managerinfo) {
        this.managerinfo = managerinfo;
    }

    public Set<Usermessagenew> getUsermessagenews() {
        return usermessagenews;
    }

    public void setUsermessagenews(Set<Usermessagenew> usermessagenews) {
        this.usermessagenews = usermessagenews;
    }

    public Set<Usercollectnew> getUsercollectnews() {
        return usercollectnews;
    }

    public void setUsercollectnews(Set<Usercollectnew> usercollectnews) {
        this.usercollectnews = usercollectnews;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
