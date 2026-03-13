package model;

public class SummonerDetail {
    private int summonerLevel;
    private int profileIconId;

    public SummonerDetail(int summonerLevel, int profileIconId) {
        this.summonerLevel = summonerLevel;
        this.profileIconId = profileIconId;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    @Override
    public String toString() {
        return "SummonerDetail{" +
                "summonerLevel=" + summonerLevel +
                ", profileIconId=" + profileIconId +
                '}';
    }
}
