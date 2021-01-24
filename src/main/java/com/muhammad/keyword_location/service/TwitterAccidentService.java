package com.muhammad.keyword_location.service;

import com.muhammad.keyword_location.domain.Accident;
import com.muhammad.keyword_location.repository.IAccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.DoubleStream;

@Service
public class TwitterAccidentService implements IAccidentService{

    @Autowired
    protected IAccidentRepository iAccidentRepository;
    @Autowired
    protected OpenCageService openCageService;

    @Scheduled(cron = "0 0/2 * * *  *")
    public void saveAccidentFromTwitter(){
        System.out.println("RRRR");
        Set<Accident> accidents = new HashSet<>();
        int total  = 5, counter = 0;
        QueryResult result = null;
        String tweetURL;

        ConfigurationBuilder cb = new ConfigurationBuilder();
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        //searching for the following keywords
        Query query = new Query("accidente OR accident");

        //GeoLocation of Spain
        GeoLocation spainGeoLocation = new GeoLocation(39.3262345, -4.8380649);
        query.setGeoCode(spainGeoLocation, 900, Query.Unit.km);

        do {
            try {
                result = twitter.search(query);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) {
                double[] coordinates = openCageService.getLatNLngByName(tweet.getUser().getLocation());
                if (tweet.getUser().isGeoEnabled() && tweet.getUser().getLocation() != null && !DoubleStream.of(coordinates).anyMatch(x -> x == 0.0)) {
                    tweetURL = "https://twitter.com/"+ tweet.getUser().getScreenName() + "/status/" + tweet.getId();
                    System.out.println("Tweet ULR : " + tweetURL);
                    Accident accident = new Accident();
                    accident.setTweetId(tweet.getId());
                    accident.setTweetUser(tweet.getUser().getName());
                    accident.setTweetUserLocation(tweet.getUser().getLocation());
                    accident.setTweetText(tweet.getText());
                    accident.setTweetURL(tweetURL);
                    accident.setTweetLat(openCageService.getLatNLngByName(accident.getTweetUserLocation())[0]);
                    accident.setTweetLng(openCageService.getLatNLngByName(accident.getTweetUserLocation())[1]);
                    System.out.println(accident.getTweetText());

                    accidents.add(accident);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                counter++;
            }
        }
        while ((query = result.nextQuery()) != null && counter < total);

        try {
            accidents.stream().forEach(accident -> {
                iAccidentRepository.save(accident);
            });
        }catch (DataIntegrityViolationException exception){
            System.out.println("The tweet has long text.");
        }
    }

    @Override
    public List<Accident> retrieveAllAccident() {
        return iAccidentRepository.findAll();
    }

    @Override
    public Accident addNew(Accident accident) {
        return iAccidentRepository.save(accident);
    }

    @Override
    public Accident updateAccident(long id, Accident accident) {
        return iAccidentRepository.save(accident);
    }

    @Override
    public boolean removeAccident(long id) {
        try {
            iAccidentRepository.deleteById(id);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}