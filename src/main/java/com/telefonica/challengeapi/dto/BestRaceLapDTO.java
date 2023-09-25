package com.telefonica.challengeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BestRaceLapDTO {
    private String superHero;
    private String strBestLap;
    private LocalDateTime bestLap;
}
