package model;

public class League implements Comparable<League> {
    private final String queueType;
    private final String tier;
    private final String rank;
    private final int leaguePoints;
    private final int wins;
    private final int losses;

    public League(String queueType, String tier, String rank, int leaguePoints, int wins, int losses) {
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
    }

    public String getQueueType() {
        return queueType;
    }


    public String getTier() {
        return tier;
    }


    public String getRank() {
        return rank;
    }


    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }


    @Override
    public String toString() {
        return "League{" +
                "queueType='" + queueType + '\'' +
                ", tier='" + tier + '\'' +
                ", rank='" + rank + '\'' +
                ", leaguePoints=" + leaguePoints +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }

    @Override
    public int compareTo(League o) {
        return o.queueType.compareTo(this.queueType);
    }
}
