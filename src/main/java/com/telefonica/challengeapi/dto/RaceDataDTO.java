package com.telefonica.challengeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceDataDTO {
    private String lapHour;
    private String superHero;
    private String lapNumber;
    private String lapTime;
    private String averageLapSpeed;
}
