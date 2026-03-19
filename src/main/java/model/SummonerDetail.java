package model;

public class SummonerDetail {
    private final int summonerLevel;
    private final int profileIconId;

    public SummonerDetail(int summonerLevel, int profileIconId) {
        this.summonerLevel = summonerLevel;
        this.profileIconId = profileIconId;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }


    public int getProfileIconId() {
        return profileIconId;
    }


    @Override
    public String toString() {
        return "SummonerDetail{" +
                "summonerLevel=" + summonerLevel +
                ", profileIconId=" + profileIconId +
                '}';
    }
}
