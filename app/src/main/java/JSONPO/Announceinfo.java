package JSONPO;

import java.io.Serializable;
import java.sql.Timestamp;

public class Announceinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int announceId;
	private String announceTitle;
	private String announceContent;
	private Timestamp announceTime;
	private Userinfo userinfo;

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

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
