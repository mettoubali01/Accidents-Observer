package com.muhammad.keyword_location.controller;

import com.muhammad.keyword_location.domain.*;
import com.muhammad.keyword_location.service.TwitterAccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class AccidentController {

    @Autowired
    protected TwitterAccidentService twitterAccidentService;

    @RequestMapping(value = {"/get", "/get/"}, method = RequestMethod.GET)
    public Collection<Accident> get(){
        return twitterAccidentService.retrieveAllAccident();
    }

    @RequestMapping(value = {"/geojson", "/geojson/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GeoJson accidentsInGetGEoJson(){
        GeoJson geoJson = new GeoJson("FeatureCollection");

        twitterAccidentService.retrieveAllAccident().forEach(accident -> {
            Feature feature = new Feature("Feature");

            Geometry geometry = new Geometry("Point");
            geometry.setCoordinates(new double[]{accident.getTweetLng(), accident.getTweetLat()});

            Properties properties = new Properties(accident.getTweetUser());
            properties.setDescription(accident.getTweetURL());

            feature.setGeometry(geometry);
            feature.setProperties(properties);

            geoJson.addFeature(feature);

        });

        System.out.println(geoJson);

        return geoJson;
    }
}
