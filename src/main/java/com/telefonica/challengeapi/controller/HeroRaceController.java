package com.telefonica.challengeapi.controller;

import com.telefonica.challengeapi.dto.RaceResultDTO;
import com.telefonica.challengeapi.service.HeroRaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get Hero Race Result", description = "Get Hero Race Result")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = RaceResultDTO.class)))
    public ResponseEntity<RaceResultDTO> getHeroRace() throws FileNotFoundException {
        LOGGER.info("HeroRaceController - getHeroRace()");
        return new ResponseEntity<>(heroRaceService.getRaceResult(), HttpStatus.OK);
    }
}
