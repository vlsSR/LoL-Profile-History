package model;

public class League {
    private String type;
    private String tier;
    private String rank;
    private int lp;
    private int wins;
    private int losses;

    public League(String type, String tier, String rank, int lp, int wins, int losses) {
        this.type = type;
        this.tier = tier;
        this.rank = rank;
        this.lp = lp;
        this.wins = wins;
        this.losses = losses;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}
