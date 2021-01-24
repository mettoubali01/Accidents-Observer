package com.muhammad.keyword_location.domain;

import java.util.ArrayList;
import java.util.List;

public class GeoJson {
    private String type;
    private List<Feature> features = new ArrayList<>();

    public GeoJson(String type) {
        this.type = type;
    }

    public void addFeature(Feature feature){
        this.features.add(feature);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "{" +
                "type:'" + type + '\'' +
                ", \nfeatures:" + features +
                "}";
    }
}
