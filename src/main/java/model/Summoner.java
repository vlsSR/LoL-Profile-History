package model;

public class Summoner {
    private String summonerName;
    private SummonerDetail details;
    private League soloQ;
    private League flexQ;

    public Summoner(String summonerName) {
        this.summonerName = summonerName;
    }

    public Summoner(String summonerName, SummonerDetail details, League soloQ, League flexQ) {
        this.summonerName = summonerName;
        this.details = details;
        this.soloQ = soloQ;
        this.flexQ = flexQ;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }


    public League getSoloQ() {
        return soloQ;
    }

    public void setSoloQ(League soloQ) {
        this.soloQ = soloQ;
    }

    public League getFlexQ() {
        return flexQ;
    }

    public void setFlexQ(League flexQ) {
        this.flexQ = flexQ;
    }

    public SummonerDetail getDetails() {
        return details;
    }

    public void setDetails(SummonerDetail details) {
        this.details = details;
    }
}
