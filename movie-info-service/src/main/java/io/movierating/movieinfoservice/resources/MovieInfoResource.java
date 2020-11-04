package io.movierating.movieinfoservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.movierating.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {

	@GetMapping("/getmovieinfo/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		return new Movie(movieId, "Movie Name");
		
	}
}
