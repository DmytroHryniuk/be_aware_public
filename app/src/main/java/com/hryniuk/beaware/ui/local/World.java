package com.hryniuk.beaware.ui.local;

public class World {

    private String countryother;
    private String totalcases;
    private String newcases;
    private String totaldeaths;
    private String newdeaths;
    private String totalrecovered;
    private String activecases;

    /*public World(String countryName, String totalCases, String newCases, String totalDeaths,
                 String newDeaths, String totalRecovered, String activerCases) {

        this.countryName = countryName;
        this.totalCases = totalCases;
        //  this.newCases = newCases;
        this.totalDeaths = totalDeaths;
        //  this.newDeaths = newDeaths;
        this.totalRecovered = totalRecovered;
        //  this.activerCases = activerCases;

    }*/

    public String getCountryother() {
        return countryother;
    }

    public void setCountryother(String countryother) {
        this.countryother = countryother;
    }

    public String getTotalcases() {
        return totalcases;
    }

    public void setTotalcases(String totalcases) {
        this.totalcases = totalcases;
    }

    public String getNewcases() {
        return newcases;
    }

    public void setNewcases(String newcases) {
        this.newcases = newcases;
    }

    public String getTotaldeaths() {
        return totaldeaths;
    }

    public void setTotaldeaths(String totaldeaths) {
        this.totaldeaths = totaldeaths;
    }

    public String getNewdeaths() {
        return newdeaths;
    }

    public void setNewdeaths(String newdeaths) {
        this.newdeaths = newdeaths;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(String totalrecovered) {
        this.totalrecovered = totalrecovered;
    }

    public String getActivecases() {
        return activecases;
    }

    public void setActivecases(String activecases) {
        this.activecases = activecases;
    }
}