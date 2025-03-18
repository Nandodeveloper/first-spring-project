package com.nando.screenmatch_spring;

import com.nando.screenmatch_spring.model.EpisodeData;
import com.nando.screenmatch_spring.model.SeasonData;
import com.nando.screenmatch_spring.model.SeriesData;
import com.nando.screenmatch_spring.service.APIConsumption;
import com.nando.screenmatch_spring.service.ConvertsData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchSpringApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			var apiConsumption = new APIConsumption();
			var convertsData = new ConvertsData();
			var jsonSeries = apiConsumption.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=e0ffa84d");
			SeriesData seriesData = convertsData.convertData(jsonSeries, SeriesData.class);
			System.out.println(seriesData);
			var jsonEp = apiConsumption.getData("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=e0ffa84d");
			EpisodeData episodeData = convertsData.convertData(jsonEp, EpisodeData.class);
			System.out.println(episodeData);

			List<SeasonData> seasonDataList = new ArrayList<>();

			for (int i = 1; i <= seriesData.totalSeasons(); i++) {
				var jsonSeason = apiConsumption.getData("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=e0ffa84d");
				SeasonData seasonData = convertsData.convertData(jsonSeason, SeasonData.class);
				seasonDataList.add(seasonData);
			}
			seasonDataList.forEach(System.out::println);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}