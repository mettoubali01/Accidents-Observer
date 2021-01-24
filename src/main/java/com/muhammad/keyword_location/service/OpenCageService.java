package com.muhammad.keyword_location.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class OpenCageService {
    final static String OPEN_CAGE_API_URL = "https://api.opencagedata.com/geocode/v1/json";
    final static String OPEN_CAGE_API_KEY = "Your-api-key";

    public String treatPlaceName(String placeName){
        if (placeName.contains(","))
            return placeName.substring(0, placeName.indexOf(","));
        else if (placeName.contains("-"))
            return placeName.substring(0, placeName.indexOf("-")).trim();
        else if (placeName == null)
            return "spain";
        else
            return placeName;
    }

    public double[] getLatNLngByName(String placeName) {
        placeName = treatPlaceName(placeName);
        double[] coordinates = new double[2];
        String url = OPEN_CAGE_API_URL + "?q=" + placeName + "&key=" + OPEN_CAGE_API_KEY;

        try {
            InputStream is = new URL(url).openStream();

            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray jsonArray1 = (JSONArray) jsonObject.get("results");

            jsonObject = (JSONObject) jsonArray1.get(0);

            JSONObject geometryObject = (JSONObject) jsonObject.get("geometry");

            coordinates[0] = (Double) geometryObject.get("lat");
            coordinates[1] = (Double) geometryObject.get("lng");

        } catch (IOException | ParseException e) {
            coordinates[0] = 0.0;
            coordinates[1] = 0.0;
            return coordinates;
        }

        return coordinates;
    }
}
