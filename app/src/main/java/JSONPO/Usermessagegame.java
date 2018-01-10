package JSONPO;

import JSONPO.*;

import java.io.Serializable;
import java.sql.Timestamp;

public class Usermessagegame implements Serializable, Comparable<Usermessagegame> {
	private static final long serialVersionUID = 1L;
	private int umgId;
	private Timestamp messageTime;
	private String messageContent;
	private Gameinfo gameinfo;
	private Userinfo userinfo;

	@Override
	public int compareTo(Usermessagegame o) {
		if (getMessageTime() == null || o.getMessageTime() == null)
			return 0;
		return getMessageTime().compareTo(o.getMessageTime());
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public int getUmgId() {
		return umgId;
	}

	public void setUmgId(int umgId) {
		this.umgId = umgId;
	}

	public Timestamp getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Timestamp messageTime) {
		this.messageTime = messageTime;
	}

	public Gameinfo getGameinfo() {
		return gameinfo;
	}

	public void setGameinfo(Gameinfo gameinfo) {
		this.gameinfo = gameinfo;
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
