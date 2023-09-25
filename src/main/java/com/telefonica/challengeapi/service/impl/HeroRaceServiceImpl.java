package com.telefonica.challengeapi.service.impl;

import com.telefonica.challengeapi.dto.*;
import com.telefonica.challengeapi.service.HeroRaceService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class HeroRaceServiceImpl implements HeroRaceService {

    @Override
    public RaceResultDTO getRaceResult() throws FileNotFoundException {

        var raceData = this.readTextFileAndGetRaceData();

        var raceResult = new RaceResultDTO();

        var raceWinner = this.getRaceWinner(raceData);

        var winnerBestLap = this.getHeroBestLap(raceWinner, raceData);

        var winnerAverageSpeed = this.getHeroAverageRaceSpeed(raceWinner, raceData);

        var raceBestLap = this.getRaceBestLap(raceData);

        raceResult.setPosition(1);
        raceResult.setRaceWinner(raceWinner);
        raceResult.setWinnerBestLap(winnerBestLap);
        raceResult.setWinnerAverageRaceSpeed(winnerAverageSpeed);
        raceResult.setRaceBestLap(raceBestLap);
        raceResult.setPositions(this.getRacePositions(raceData));

        return raceResult;
    }

    private List<RacePositionDTO> getRacePositions(List<RaceDataDTO> raceData) {

        var racePositionList = new ArrayList<RacePositionDTO>();

        var lastLapList = new ArrayList<RacerDTO>();

        for (RaceDataDTO dataDTO : raceData) {

            if (dataDTO.getLapNumber().equals("4")) {
                var racer = new RacerDTO();
                racer.setSuperHero(dataDTO.getSuperHero());
                racer.setLastLapHour(this
                        .convertStringHourToLocalDateTime(dataDTO.getLapHour()));
                lastLapList.add(racer);
            }
        }

        var listSize = lastLapList.size();

        for (int i = 0; i < listSize; i++) {

            var hero = Collections
                    .min(lastLapList, Comparator.comparing(RacerDTO::getLastLapHour));

            var racePosition = new RacePositionDTO();

            racePosition.setPosition(i + 1);
            racePosition.setSuperHero(hero.getSuperHero());
            racePosition.setBestLap(this.getHeroBestLap(hero.getSuperHero(), raceData));
            racePosition.setAverageRaceSpeed(
                    this.getHeroAverageRaceSpeed(hero.getSuperHero(), raceData));

            racePositionList.add(racePosition);

            lastLapList.remove(hero);
        }

        racePositionList.remove(0);

        return racePositionList;

    }

    private String getRaceWinner(List<RaceDataDTO> raceData) {

        var lastLapList = new ArrayList<RacerDTO>();

        for (RaceDataDTO dataDTO : raceData) {

            if (dataDTO.getLapNumber().equals("4")) {
                var racer = new RacerDTO();
                racer.setSuperHero(dataDTO.getSuperHero());
                racer.setLastLapHour(this
                        .convertStringHourToLocalDateTime(dataDTO.getLapHour()));
                lastLapList.add(racer);
            }
        }

        var raceWinner = Collections.min(lastLapList, Comparator.comparing(RacerDTO::getLastLapHour));

        System.out.println(raceWinner.getSuperHero());

        return raceWinner.getSuperHero();
    }

    private String getHeroBestLap(String racer, List<RaceDataDTO> raceData) {

        var heroLapList = new ArrayList<BestRaceLapDTO>();

        for (RaceDataDTO dataDTO : raceData) {

            if (dataDTO.getSuperHero().equals(racer)) {
                var bestRaceLap = new BestRaceLapDTO();
                bestRaceLap.setSuperHero(racer);
                bestRaceLap.setStrBestLap(dataDTO.getLapTime());
                bestRaceLap.setBestLap(this
                        .convertStringMinuteToLocalDateTime(dataDTO.getLapTime()));

                heroLapList.add(bestRaceLap);
            }
        }

        var racerBestLap = Collections.min(heroLapList, Comparator.comparing(BestRaceLapDTO::getBestLap));

        System.out.println(racerBestLap.getStrBestLap());

        return racerBestLap.getStrBestLap();
    }

    private Double getHeroAverageRaceSpeed(String racer, List<RaceDataDTO> raceData) {

        var averageSpeed = 0.0;

        for (RaceDataDTO dataDTO : raceData) {

            if (dataDTO.getSuperHero().equals(racer)) {
                var speed = dataDTO.getAverageLapSpeed().replace(",", ".");
                averageSpeed = averageSpeed + Double.parseDouble(speed);
            }
        }

        return averageSpeed / 4;
    }

    private String getRaceBestLap(List<RaceDataDTO> raceData) {

        var raceLapList = new ArrayList<BestRaceLapDTO>();

        for (RaceDataDTO dataDTO : raceData) {
            var bestRaceLap = new BestRaceLapDTO();
            bestRaceLap.setSuperHero(dataDTO.getSuperHero());
            bestRaceLap.setStrBestLap(dataDTO.getLapTime());
            bestRaceLap.setBestLap(this
                    .convertStringMinuteToLocalDateTime(dataDTO.getLapTime()));

            raceLapList.add(bestRaceLap);
        }

        var raceBestLap = Collections.min(raceLapList, Comparator.comparing(BestRaceLapDTO::getBestLap));

        return raceBestLap.getStrBestLap().concat(" - ").concat(raceBestLap.getSuperHero());
    }

    private LocalDateTime convertStringHourToLocalDateTime(String hour) {
        var dateHour = "2023-09-23 ".concat(hour);
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.parse(dateHour, formatter);
    }

    private LocalDateTime convertStringMinuteToLocalDateTime(String minute) {
        var dateHour = "2023-09-23 00:0".concat(minute);
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.parse(dateHour, formatter);
    }

    private List<RaceDataDTO> readTextFileAndGetRaceData() throws FileNotFoundException {

        var inputFile = new FileInputStream("C:\\KDI\\Cognizant\\heroRaceList.txt");
        var readFile = new Scanner(inputFile, StandardCharsets.UTF_8);
        var raceData = new ArrayList<RaceDataDTO>();

        while (readFile.hasNext()) {

            var line = readFile.nextLine();

            if (line != null && !line.isEmpty()) {

                String[] data = line.split("\\;");

                var raceDataDTO = new RaceDataDTO();
                raceDataDTO.setLapHour(data[0]);
                raceDataDTO.setSuperHero(data[1]);
                raceDataDTO.setLapNumber(data[2]);
                raceDataDTO.setLapTime(data[3]);
                raceDataDTO.setAverageLapSpeed(data[4]);

                raceData.add(raceDataDTO);
            }
        }

        return raceData;
    }
}
