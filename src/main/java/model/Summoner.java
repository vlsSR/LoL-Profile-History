package model;

public class Summoner {
    private String summonerName;
    private SummonerDetail details;
    private League soloQ;
    private League flexQ;

    public Summoner() {
    }

    public Summoner(String summonerName) {
        this.summonerName = summonerName;
    }

    public Summoner(String summonerName, SummonerDetail details, League soloQ, League flexQ) {
        this.summonerName = summonerName.replace("%20", " ");
        this.details = details;
        this.soloQ = soloQ;
        this.flexQ = flexQ;
    }

    public Summoner(String summonerName, SummonerDetail details, League league) {
        this.summonerName = summonerName.replace("%20", " ");
        this.details = details;
        if (league.getQueueType().equals("RANKED_SOLO_5x5")) {
            this.soloQ = league;
        } else {
            this.flexQ = league;
        }
    }

    public Summoner(String summonerName, SummonerDetail details) {
        this.summonerName = summonerName.replace("%20", " ");
        this.details = details;
    }

    public int getLeagueCount() {
        if (soloQ == null && flexQ == null) return 0;
        else if (soloQ == null || flexQ == null) return 1;
        else return 2;
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

    @Override
    public String toString() {
        return "Summoner{" +
                "summonerName='" + summonerName + '\'' +
                ", details=" + details +
                ", soloQ=" + soloQ +
                ", flexQ=" + flexQ +
                '}';
    }
}
