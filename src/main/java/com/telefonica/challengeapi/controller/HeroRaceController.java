package com.telefonica.challengeapi.controller;

import com.telefonica.challengeapi.dto.RaceResultDTO;
import com.telefonica.challengeapi.service.HeroRaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/v0/challenge/api/race")
public class HeroRaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeroRaceController.class);

    private final HeroRaceService heroRaceService;

    @Autowired
    public HeroRaceController(HeroRaceService heroRaceService) {
        this.heroRaceService = heroRaceService;
    }

    @GetMapping
    public ResponseEntity<RaceResultDTO> getHeroRace() throws FileNotFoundException {
        return new ResponseEntity<>(heroRaceService.getRaceResult(), HttpStatus.OK);
    }
}
