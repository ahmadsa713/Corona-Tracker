package com.Corona.CoronaTrack.controllers;

import com.Corona.CoronaTrack.models.Stats2;
import com.Corona.CoronaTrack.services.CoronaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Home {

    @Autowired
    CoronaDataService coronaDataService;

    @GetMapping("/")
    public String home(Model model){
        List<Stats2> allStats2 = coronaDataService.getAllStats2();
        int latestTotalAmountOfDeaths=allStats2.stream().mapToInt(stats2 -> Integer.parseInt(stats2.getTotalAmountOfDeaths())).max().getAsInt();
        int latestTotalAmountOfCases=allStats2.stream().mapToInt(stats2 -> Integer.parseInt(stats2.getTotalAmountOfCases())).max().getAsInt();

        model.addAttribute("stats", coronaDataService.getAllStats());
        model.addAttribute("latestTotalAmountOfCases", latestTotalAmountOfCases);
        model.addAttribute("latestTotalAmountOfDeaths", latestTotalAmountOfDeaths);
        return "home";
    }
}
