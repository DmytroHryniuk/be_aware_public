package com.hryniuk.beaware.ui.local;

public class World {

    private String countryName;
    private String totalCases;
    // private String newCases;
    private String totalDeaths;
    // private String newDeaths;
    private String totalRecovered;
    // private String activerCases;

    public World(String countryName, String totalCases, String newCases, String totalDeaths,
                 String newDeaths, String totalRecovered, String activerCases) {

        this.countryName = countryName;
        this.totalCases = totalCases;
        //  this.newCases = newCases;
        this.totalDeaths = totalDeaths;
        //  this.newDeaths = newDeaths;
        this.totalRecovered = totalRecovered;
        //  this.activerCases = activerCases;

    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }



    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }



   /* public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    } */



    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }



    /*public String getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }*/



    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }



   /* public String getActiverCases() {
        return activerCases;
    }

    public void setActiverCases(String activerCases) {
        this.activerCases = activerCases;
    }*/
}