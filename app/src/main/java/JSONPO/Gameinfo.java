package JSONPO;

import java.io.Serializable;
import java.sql.Timestamp;

public class Gameinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int gameId;
	private Timestamp gameTime;
	private String gameLocation;
	private String gameTeam1;
	private String gameTeam2;
	private String gameResult;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Timestamp getGameTime() {
		return gameTime;
	}

	public void setGameTime(Timestamp gameTime) {
		this.gameTime = gameTime;
	}

	public String getGameLocation() {
		return gameLocation;
	}

	public void setGameLocation(String gameLocation) {
		this.gameLocation = gameLocation;
	}

	public String getGameTeam1() {
		return gameTeam1;
	}

	public void setGameTeam1(String gameTeam1) {
		this.gameTeam1 = gameTeam1;
	}

	public String getGameTeam2() {
		return gameTeam2;
	}

	public void setGameTeam2(String gameTeam2) {
		this.gameTeam2 = gameTeam2;
	}

	public String getGameResult() {
		return gameResult;
	}

	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
