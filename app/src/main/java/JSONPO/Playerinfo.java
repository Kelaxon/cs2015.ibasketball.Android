package JSONPO;

import java.io.Serializable;

public class Playerinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int playerId;
	private String playerAvatar;
	private String playerPosition;
	private int playerHeight;
	private String playerName;
	private Teaminfo teaminfo;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerAvatar() {
		return playerAvatar;
	}

	public void setPlayerAvatar(String playerAvatar) {
		this.playerAvatar = playerAvatar;
	}

	public String getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}

	public int getPlayerHeight() {
		return playerHeight;
	}

	public void setPlayerHeight(int playerHeight) {
		this.playerHeight = playerHeight;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Teaminfo getTeaminfo() {
		return teaminfo;
	}

	public void setTeaminfo(Teaminfo teaminfo) {
		this.teaminfo = teaminfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
