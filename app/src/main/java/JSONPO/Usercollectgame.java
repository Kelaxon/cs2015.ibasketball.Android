package JSONPO;

import JSONPO.*;

import java.io.Serializable;
import java.sql.Timestamp;

public class Usercollectgame implements Serializable, Comparable<Usercollectgame> {

	private static final long serialVersionUID = 1L;
	private int ucgId;
	private Timestamp collectionTime;
	private Gameinfo gameinfo;
	private Userinfo userinfo;

	@Override
	public int compareTo(Usercollectgame o) {
		if (getCollectionTime() == null || o.getCollectionTime() == null)
			return 0;
		return getCollectionTime().compareTo(o.getCollectionTime());
	}

	public int getUcgId() {
		return ucgId;
	}

	public void setUcgId(int ucgId) {
		this.ucgId = ucgId;
	}

	public Timestamp getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Timestamp collectionTime) {
		this.collectionTime = collectionTime;
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
