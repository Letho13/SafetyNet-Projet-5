package com.SafetyNet.Projet5.model;

public class FireStation {

    private String station;
    private String address;

    public FireStation() {
    }

    public FireStation(String station, String address) {
        this.station = station;
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "FireStation{" +
                "station='" + station + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
