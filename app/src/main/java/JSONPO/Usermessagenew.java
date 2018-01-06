package JSONPO;

import java.io.Serializable;
import java.sql.Timestamp;

public class Usermessagenew implements Serializable {
	private static final long serialVersionUID = 1L;
	private int umnId;
	private Timestamp messageTime;
	private String messageContent;
	private Newsinfo newsinfo;
	private Userinfo userinfo;

	
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
		return "Usermessagenew{" +
				"umnId=" + umnId +
				", messageTime=" + messageTime +
				", messageContent='" + messageContent + '\'' +
				", newsinfo=" + newsinfo +
				", userinfo=" + userinfo +
				'}';
	}
}
