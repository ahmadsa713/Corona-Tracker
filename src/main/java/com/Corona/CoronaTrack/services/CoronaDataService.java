package com.Corona.CoronaTrack.services;


import com.Corona.CoronaTrack.models.Stats;
import com.Corona.CoronaTrack.models.Stats2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaDataService {

    private static String VirusDataURL = "https://raw.githubusercontent.com/nytimes/covid-19-data/master/us-states.csv";
    private static String VirusDataURL2= "https://raw.githubusercontent.com/nytimes/covid-19-data/master/us.csv";

    private List<Stats> allStats = new ArrayList<>();

    public List<Stats> getAllStats() {
        return allStats;
    }

    private List<Stats2> allStats2 = new ArrayList<>();

    public List<Stats2> getAllStats2() {
        return allStats2;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void GetCornonaData() throws IOException, InterruptedException {
        List<Stats> newStats = new ArrayList<>();
        List<Stats2> newStats2 = new ArrayList<>();


        HttpClient client = HttpClient.newHttpClient();
        HttpClient client2 = HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder().uri(URI.create(VirusDataURL)).build();
        HttpRequest request2= HttpRequest.newBuilder().uri(URI.create(VirusDataURL2)).build();

        HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> httpResponse2=client.send(request2, HttpResponse.BodyHandlers.ofString());

        StringReader  csvBody2 = new StringReader(httpResponse2.body());
        Iterable<CSVRecord> records2 = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBody2);
        for (CSVRecord record2 : records2) {
            Stats2 locationStat2 = new Stats2();
            //locationStat2.setTotalAmountOfCases(record2.get("cases"));
            locationStat2.setTotalAmountOfCases(record2.get(record2.size()-2));

            //locationStat2.setTotalAmountOfDeaths(record2.get("deaths"));
            locationStat2.setTotalAmountOfDeaths(record2.get(record2.size()-1));
           // System.out.println(locationStat2);
            newStats2.add(locationStat2);
        }
        this.allStats2 = newStats2;


        StringReader  csvBody = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBody);
        for (CSVRecord record : records) {
            Stats locationStat = new Stats();
            locationStat.setDate(record.get("date"));
            locationStat.setState(record.get("state"));
            locationStat.setCases(record.get("cases"));
            locationStat.setDeaths(record.get("deaths"));
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }
}
