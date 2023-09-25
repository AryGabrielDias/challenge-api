package com.telefonica.challengeapi.service;

import com.telefonica.challengeapi.dto.RaceDataDTO;
import com.telefonica.challengeapi.dto.RaceResultDTO;

import java.io.FileNotFoundException;

public interface HeroRaceService {

    RaceResultDTO getRaceResult() throws FileNotFoundException;
}
