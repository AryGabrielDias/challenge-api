package com.telefonica.challengeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RacePositionDTO {
    private Integer position;
    private String superHero;
    private String bestLap;
    private Double averageRaceSpeed;
}
