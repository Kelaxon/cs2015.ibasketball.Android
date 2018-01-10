package JSONPO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

public class Gameinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int gameId;
	private Timestamp gameTime;
	private String gameLocation;
	private int gameTeam1result;
	private int gameTeam2result;
	private String gameComment;
	private Teaminfo teaminfoByGameTeam1Id;
	private Teaminfo teaminfoByGameTeam2Id;
	private Set<Usermessagegame> usermessagegames;
	private Set<Usercollectgame> usercollectgames;

	public Teaminfo getTeaminfoByGameTeam1Id() {
		return teaminfoByGameTeam1Id;
	}

	public void setTeaminfoByGameTeam1Id(Teaminfo teaminfoByGameTeam1Id) {
		this.teaminfoByGameTeam1Id = teaminfoByGameTeam1Id;
	}

	public Teaminfo getTeaminfoByGameTeam2Id() {
		return teaminfoByGameTeam2Id;
	}

	public void setTeaminfoByGameTeam2Id(Teaminfo teaminfoByGameTeam2Id) {
		this.teaminfoByGameTeam2Id = teaminfoByGameTeam2Id;
	}

	public Set<Usermessagegame> getUsermessagegames() {
		return usermessagegames;
	}

	public void setUsermessagegames(Set<Usermessagegame> usermessagegames) {
		this.usermessagegames = usermessagegames;
	}

	public Set<Usercollectgame> getUsercollectgames() {
		return usercollectgames;
	}

	public void setUsercollectgames(Set<Usercollectgame> usercollectgames) {
		this.usercollectgames = usercollectgames;
	}

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

	public int getGameTeam1result() {
		return gameTeam1result;
	}

	public void setGameTeam1result(int gameTeam1result) {
		this.gameTeam1result = gameTeam1result;
	}

	public int getGameTeam2result() {
		return gameTeam2result;
	}

	public void setGameTeam2result(int gameTeam2result) {
		this.gameTeam2result = gameTeam2result;
	}

	public String getGameComment() {
		return gameComment;
	}

	public void setGameComment(String gameComment) {
		this.gameComment = gameComment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
