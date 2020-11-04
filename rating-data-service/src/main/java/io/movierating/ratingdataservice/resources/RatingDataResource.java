package io.movierating.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.movierating.ratingdataservice.models.Rating;
import io.movierating.ratingdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {

	@GetMapping("/getRating/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {

		return new Rating(movieId, 2);

	}

	@GetMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {

		List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 3));
		
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);

		return userRating;
	}

}
