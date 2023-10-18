package com.example.israpp2;
import java.util.ArrayList;

public class ListOfStations {
    private ArrayList<Station> stationList = new ArrayList<Station>();
    public int calculateAvgTime() {
        int sum = 0;
        int count = stationList.size();
        for (int i = 0; i < count; i++) {
            sum += stationList.get(i).getTime();
        }
        return sum / count;
    }
    public double calculateAvgRating() {
        double sum = 0;
        double count = stationList.size();
        for (int i = 0; i < count; i++) {
            sum += stationList.get(i).getQuality();
        }
        return sum / count;
    }
    public String getReviewMessages() {
        String toReturn = "";
        for (int i = 0; i < stationList.size(); i++) {
            toReturn += stationList.get(i).getDescription();
            toReturn += "\n";
        }
        return toReturn;
    }
    public void add(Station station) {
        stationList.add(station);
    }
    public int size() {
        return stationList.size();
    }
    public Station get(int i) {
        return stationList.get(i);
    }
    public ArrayList<Station> getStationList() {
        return stationList;
    }
}
