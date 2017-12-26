package JDBC.Beans;

import java.io.Serializable;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class Scoreinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int scoreId;
    private int scoreRanking;
    private int scoreWins;
    private int scoreLoses;
    private float scorePpg;
    private float scorePlp;
    private int scoreTg;
    private Teaminfo teaminfo;

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getScoreRanking() {
        return scoreRanking;
    }

    public void setScoreRanking(int scoreRanking) {
        this.scoreRanking = scoreRanking;
    }

    public int getScoreWins() {
        return scoreWins;
    }

    public void setScoreWins(int scoreWins) {
        this.scoreWins = scoreWins;
    }

    public int getScoreLoses() {
        return scoreLoses;
    }

    public void setScoreLoses(int scoreLoses) {
        this.scoreLoses = scoreLoses;
    }

    public float getScorePpg() {
        return scorePpg;
    }

    public void setScorePpg(float scorePpg) {
        this.scorePpg = scorePpg;
    }

    public float getScorePlp() {
        return scorePlp;
    }

    public void setScorePlp(float scorePlp) {
        this.scorePlp = scorePlp;
    }

    public int getScoreTg() {
        return scoreTg;
    }

    public void setScoreTg(int scoreTg) {
        this.scoreTg = scoreTg;
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
