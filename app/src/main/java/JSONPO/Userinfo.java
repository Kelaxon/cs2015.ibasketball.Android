package JSONPO;

import JSONPO.*;

import java.io.Serializable;
import java.util.Set;

public class Userinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String userPassword;
	private String userAddr;
	private String userEmail;
	private String userTel;
	private String userAvatar;
	private String userTruname;
	private String userIntro;
	private Set<Announceinfo> announceinfos;
	private Set<Usermessagenew> usermessagenews;
	private Set<Usercollectnew> usercollectnews;
	private Set<Usercollectgame> usercollectgames;
	private Set<Usermessagegame> usermessagegames;

	public Set<Usercollectgame> getUsercollectgames() {
		return usercollectgames;
	}

	public void setUsercollectgames(Set<Usercollectgame> usercollectgames) {
		this.usercollectgames = usercollectgames;
	}

	public Set<Usermessagegame> getUsermessagegames() {
		return usermessagegames;
	}

	public void setUsermessagegames(Set<Usermessagegame> usermessagegames) {
		this.usermessagegames = usermessagegames;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getUserTruname() {
		return userTruname;
	}

	public void setUserTruname(String userTruname) {
		this.userTruname = userTruname;
	}

	public String getUserIntro() {
		return userIntro;
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}

	public Set<Announceinfo> getAnnounceinfos() {
		return announceinfos;
	}

	public void setAnnounceinfos(Set<Announceinfo> announceinfos) {
		this.announceinfos = announceinfos;
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

}
