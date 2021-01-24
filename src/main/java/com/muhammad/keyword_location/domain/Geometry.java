package com.muhammad.keyword_location.domain;

import java.util.Arrays;

public class Geometry {
    private String type;
    private double[] coordinates;

    public Geometry(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "{" +
                "type:'" + type + '\'' +
                ", coordinates:" + Arrays.toString(coordinates) +
                '}';
    }
}
