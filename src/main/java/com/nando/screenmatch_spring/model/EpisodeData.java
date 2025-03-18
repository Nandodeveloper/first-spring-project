package com.nando.screenmatch_spring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") Integer epNumber,
                          @JsonAlias("Released") String releaseDate,
                          @JsonAlias("imdbRating") String evaluation) {
}
