package JSONPO;

import java.io.Serializable;
import java.sql.Timestamp;

public class Usercollectnew implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ucnId;
	private Timestamp collectionTime;
	private Newsinfo newsinfo;
	private Userinfo userinfo;

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

	public Newsinfo getNewsinfo() {
		return newsinfo;
	}

	public void setNewsinfo(Newsinfo newsinfo) {
		this.newsinfo = newsinfo;
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

	@Override
	public String toString() {
		return "Usercollectnew{" +
				"ucnId=" + ucnId +
				", collectionTime=" + collectionTime +
				", newsinfo=" + newsinfo +
				", userinfo=" + userinfo +
				'}';
	}
}
