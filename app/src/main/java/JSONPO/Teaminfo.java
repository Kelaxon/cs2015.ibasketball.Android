package JSONPO;

import JSONPO.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

public class Teaminfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int teamId;
	private String teamName;
	private Timestamp teamTime;
	private String teamCoachers;
	private String teamPlayers;
	private String teamLogo;
	private String teamHonors;
	private Set<Scoreinfo> scoreinfos;
	private Set<Playerinfo> playerinfos;
	private Set<Gameinfo> gameinfosByGameTeam2Id;
	private Set<Gameinfo> gameinfosByGameTeam1Id;

	public Set<Gameinfo> getGameinfosByGameTeam2Id() {
		return gameinfosByGameTeam2Id;
	}

	public void setGameinfosByGameTeam2Id(Set<Gameinfo> gameinfosByGameTeam2Id) {
		this.gameinfosByGameTeam2Id = gameinfosByGameTeam2Id;
	}

	public Set<Gameinfo> getGameinfosByGameTeam1Id() {
		return gameinfosByGameTeam1Id;
	}

	public void setGameinfosByGameTeam1Id(Set<Gameinfo> gameinfosByGameTeam1Id) {
		this.gameinfosByGameTeam1Id = gameinfosByGameTeam1Id;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Timestamp getTeamTime() {
		return teamTime;
	}

	public void setTeamTime(Timestamp teamTime) {
		this.teamTime = teamTime;
	}

	public String getTeamCoachers() {
		return teamCoachers;
	}

	public void setTeamCoachers(String teamCoachers) {
		this.teamCoachers = teamCoachers;
	}

	public String getTeamPlayers() {
		return teamPlayers;
	}

	public void setTeamPlayers(String teamPlayers) {
		this.teamPlayers = teamPlayers;
	}

	public String getTeamLogo() {
		return teamLogo;
	}

	public void setTeamLogo(String teamLogo) {
		this.teamLogo = teamLogo;
	}

	public String getTeamHonors() {
		return teamHonors;
	}

	public void setTeamHonors(String teamHonors) {
		this.teamHonors = teamHonors;
	}

	public Set<Scoreinfo> getScoreinfos() {
		return scoreinfos;
	}

	public void setScoreinfos(Set<Scoreinfo> scoreinfos) {
		this.scoreinfos = scoreinfos;
	}

	public Set<Playerinfo> getPlayerinfos() {
		return playerinfos;
	}

	public void setPlayerinfos(Set<Playerinfo> playerinfos) {
		this.playerinfos = playerinfos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
