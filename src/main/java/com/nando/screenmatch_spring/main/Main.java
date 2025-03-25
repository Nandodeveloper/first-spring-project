package com.nando.screenmatch_spring.main;

import com.nando.screenmatch_spring.exception.TitleNotFoundException;
import com.nando.screenmatch_spring.model.EpisodeData;
import com.nando.screenmatch_spring.model.SeasonData;
import com.nando.screenmatch_spring.model.SeriesData;
import com.nando.screenmatch_spring.service.APIConsumption;
import com.nando.screenmatch_spring.service.ConvertsData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    Scanner scanner = new Scanner(System.in);
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e0ffa84d";
    private String seriesName;
    private APIConsumption apiConsumption = new APIConsumption();
    private ConvertsData convertsData = new ConvertsData();

    public void runMenu() {
        System.out.println("Enter the name series for the search:");
        seriesName = scanner.nextLine();

        try {
            var json = apiConsumption.getData(ADDRESS + this.seriesName.replace(" ", "+") + API_KEY);

            SeriesData seriesData = convertsData.convertData(json, SeriesData.class);
            if(seriesData.title() == null) {
                throw new TitleNotFoundException("Title not found.");
            }

            List<SeasonData> seasonDataList = new ArrayList<>();

            for (int i = 1; i <= seriesData.totalSeasons(); i++) {
                var jsonSeason = apiConsumption.getData(ADDRESS +
                        this.seriesName.replace(" ", "+") +
                        "&season=" + i + API_KEY);
                SeasonData seasonData = convertsData.convertData(jsonSeason, SeasonData.class);
                seasonDataList.add(seasonData);
            }

            List<EpisodeData> episodesLists =
                    seasonDataList
                            .stream()
                            .flatMap(t -> t.episodeDataList().stream())
                            .collect(Collectors.toList());
            episodesLists
                    .stream()
                    .filter(e -> !e.evaluation().equalsIgnoreCase("N/A"))
                    .sorted(Comparator.comparing(EpisodeData::evaluation).reversed())
                    .limit(5)
                    .forEach(System.out::println);

        } catch (TitleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
