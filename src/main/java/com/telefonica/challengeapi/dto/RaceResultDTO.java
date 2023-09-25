package com.telefonica.challengeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaceResultDTO {
    private Integer position;
    private String raceWinner;
    private String winnerBestLap;
    private Double winnerAverageRaceSpeed;
    private String raceBestLap;
    private List<RacePositionDTO> positions;
}
