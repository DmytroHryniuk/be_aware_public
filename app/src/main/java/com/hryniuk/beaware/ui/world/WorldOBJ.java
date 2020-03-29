package com.hryniuk.beaware.ui.world;

public class WorldOBJ {
    private String Cases;
    private String Death;
    private String Recovered;

    public WorldOBJ(){}

    public WorldOBJ(String cases, String death, String recovered) {
        Cases = cases;
        Death = death;
        Recovered = recovered;
    }

    public String getCases() {
        return Cases;
    }

    public void setCases(String cases) {
        Cases = cases;
    }

    public String getDeath() {
        return Death;
    }

    public void setDeath(String death) {
        Death = death;
    }

    public String getRecovered() {
        return Recovered;
    }

    public void setRecovered(String recovered) {
        Recovered = recovered;
    }
}
