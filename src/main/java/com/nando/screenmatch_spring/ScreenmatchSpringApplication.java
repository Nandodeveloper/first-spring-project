package com.nando.screenmatch_spring;

import com.nando.screenmatch_spring.model.SeriesData;
import com.nando.screenmatch_spring.service.APIConsumption;
import com.nando.screenmatch_spring.service.ConvertsData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var apiConsumption = new APIConsumption();
		var json = apiConsumption.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=e0ffa84d");
		ConvertsData convertsData = new ConvertsData();
		var converts = convertsData.convertData(json, SeriesData.class);
		System.out.println(converts);
	}
}
