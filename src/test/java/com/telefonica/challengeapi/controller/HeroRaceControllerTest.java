package com.telefonica.challengeapi.controller;

import com.telefonica.challengeapi.dto.RaceResultDTO;
import com.telefonica.challengeapi.service.HeroRaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HeroRaceController.class)
public class HeroRaceControllerTest {

    @MockBean
    private HeroRaceService heroRaceService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                new HeroRaceController(heroRaceService)).build();
    }

    @Test
    public void should_returnHeroRaceResultDTO_when_getHeroRaceIsCalled() throws Exception {
        when(heroRaceService.getRaceResult()).thenReturn(new RaceResultDTO());
        mockMvc.perform(get("/v0/challenge/api/race"))
                .andExpect(status().isOk());
    }
}
