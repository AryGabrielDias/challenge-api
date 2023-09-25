package com.telefonica.challengeapi.service;

import com.telefonica.challengeapi.service.impl.HeroRaceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;

@ExtendWith(MockitoExtension.class)
public class HeroRaceServiceImplTest {

    @InjectMocks
    private HeroRaceServiceImpl heroRaceService;

    @Test
    public void should_returnHeroRaceResultDTO_when_getRaceResultIsCalled() throws FileNotFoundException {
        var raceResultDTO = heroRaceService.getRaceResult();
        Assertions.assertEquals("038–Superman", raceResultDTO.getRaceWinner());
        Assertions.assertEquals("1:02.769", raceResultDTO.getWinnerBestLap());
    }
}
